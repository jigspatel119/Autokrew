package com.autokrew.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.autokrew.R;
import com.autokrew.models.ApplyAttendanceParam;
import com.autokrew.models.OutSideAttendanceModel;
import com.autokrew.network.ApiListener;
import com.autokrew.network.WebServices;
import com.autokrew.utils.CommonUtils;
import com.autokrew.utils.Constant;
import com.autokrew.utils.FusedLocationAPIService;
import com.autokrew.utils.Permissions;
import com.autokrew.utils.Pref;
import com.autokrew.utils.PreferenceHelper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class QRScanActivity extends AppCompatActivity implements ApiListener {

    //General Variables
    private static final String TAG = QRScanActivity.class.getSimpleName();

    Toolbar mToolbar;

    private IntentIntegrator qrScan;

    OutSideAttendanceModel mOutsideSideAttendanceModel;
    TextView txt_qr_scan;

    boolean isCodeRead = false;
    String mLat2, mLong2, mAddress;
    private PreferenceHelper mPreferenceHelper;
    private static final int LOCATION_REQUEST_CODE = 1;

    String IMEINumber = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscan);

        mPreferenceHelper = new PreferenceHelper(this);
        IMEINumber = CommonUtils.getInstance().getIMEI(this);
        Log.e(TAG, "IMEINumber ::" + CommonUtils.getInstance().getIMEI(this));

        //Initialize Views
        initializeViews();


    }

    /**
     * Initialize views
     */
    private void initializeViews() {
        // TODO ... Initialize your views here


        txt_qr_scan = (TextView) this.findViewById(R.id.txt_qr_scan);
        mToolbar = (Toolbar) this.findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        //mToolbar.setTitle("QR code scan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mToolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                QRScanActivity.this.overridePendingTransition(R.anim.activity_open_scale, R.anim.activity_close_translate);

            }
        });

        //Initialize QRCodeReaderView

        qrScan = new IntentIntegrator(this);
        //qrScan.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
        qrScan.setPrompt("Scan QR Code");
        qrScan.setOrientationLocked(false);


        //intializing scan object
        txt_qr_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!CommonUtils.isGpsEnabled(QRScanActivity.this)) {
                    CommonUtils.displayLocationSettingsRequest(QRScanActivity.this);
                } else {
                    if (Permissions.getInstance().isLocationPermissionGranted(QRScanActivity.this)) {
                       // requestPermissionForLocation();
                        startLocationService();
                    }

                }

            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        stopLocationService();
    }


    private void callOutSideAttendanceFromMobileAppAPI(String QR_text, String mLat2, String mLong2, String mAddress) {

        // CommonUtils.getInstance().displayToast(this,QR_text);

        ApplyAttendanceParam params = new ApplyAttendanceParam();
        params.setAtt_Lat(mLat2);
        params.setAtt_Long(mLong2);
        params.setIsQrCode("1");
        params.setQrCode(QR_text);
        params.setAtt_PhisicalAddress(mAddress);
        params.setEmployeePk(String.valueOf(Pref.getValue(this, Constant.PREF_SESSION_EMPLOYEE_FK, 0)));
        params.setIMEINumber(IMEINumber);

        String mToken = Pref.getValue(this, Constant.PREF_TOKEN, "");

        new WebServices(this/* ActivityContext */, QRScanActivity.this /* ApiListener */, true /* show progress dialog */,
                true/*for new retrofitclient*/).
                callOutSideAttendanceFromMobileAppAPI(mToken, params);
        //  }
    }


    @Override
    public void onApiSuccess(Object mObject) {

        if (mObject instanceof String) {

            try {
                JSONObject jsonObj = new JSONObject(mObject.toString());
                //parse original data
                Log.e("", "onApiSuccess: QRCode json >>  " + jsonObj.toString());

                Gson gson = new Gson();
                mOutsideSideAttendanceModel = gson.fromJson(mObject.toString(), OutSideAttendanceModel.class);
                // CommonUtils.getInstance().displayToast(QRScanActivity.this,""+mOutsideSideAttendanceModel.getTable().get(0).getResult());
                // finish();
                Toast.makeText(this, "" + mOutsideSideAttendanceModel.getTable().get(0).getResult(), Toast.LENGTH_LONG).show();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onApiFailure(Throwable mThrowable) {

    }

    private void stopLocationService() {

        if (CommonUtils.isMyServiceRunning(QRScanActivity.this, FusedLocationAPIService.class)) {
            Log.e(TAG, "stopLocationService: called ");
            Intent intent = new Intent(QRScanActivity.this, FusedLocationAPIService.class);
            stopService(intent);
        }
    }


    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (requestCode == 123) {

        }
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                try {
                    //converting the data to json
                    JSONObject obj = new JSONObject(result.getContents());

                    //mAddress = mPreferenceHelper.getAddress();

                    //mLat1, mLong1 //from qrcode
                    //mLat2, mLong2 //device lat ,long

                    Log.e(TAG, "onActivityResult:mLat2 ## " + CommonUtils.lattitude);
                    Log.e(TAG, "onActivityResult:mLong2 ##  " + CommonUtils.logitude);
                    mAddress = CommonUtils.getAddressFromLatLong(this,
                            CommonUtils.lattitude,
                            CommonUtils.logitude);

                    if (distance(Double.parseDouble(obj.getString("lat"))
                            , Double.parseDouble(obj.getString("long"))
                            , CommonUtils.lattitude
                            , CommonUtils.logitude) <= Double.parseDouble(obj.getString("Radius"))) {  //  {0.01<=10 miter}  //


                        callOutSideAttendanceFromMobileAppAPI(obj.getString("QR"), mLat2, mLong2, mAddress);

                        CommonUtils.lattitude = 0.0 ;
                        CommonUtils.logitude = 0.0 ;

                    } else {
                        Toast.makeText(this, "Wrong Location.", Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    /**
     * calculates the distance between two locations in MILES
     */
    private double distance(double lat1, double lng1, double lat2, double lng2) {

        double earthRadius = 3958.75; // in miles, change to 6371 for kilometer output

        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);

        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);

        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double dist = earthRadius * c;
        return dist; // output distance, in MILES // 100 meters
        // return round(dist, 2); // output distance, in MILES

    }


    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopLocationService();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }



    private void startLocationService() {

        if (!CommonUtils.isMyServiceRunning(QRScanActivity.this, FusedLocationAPIService.class)) {
            Intent intent = new Intent(QRScanActivity.this, FusedLocationAPIService.class);
            startService(intent);
        }


        if(CommonUtils.lattitude != 0.0 && CommonUtils.logitude != 0.0 ){
            if (String.valueOf(CommonUtils.lattitude) != null && String.valueOf(CommonUtils.logitude) != null) {

                mLat2 = String.valueOf(CommonUtils.lattitude);
                mLong2 = String.valueOf(CommonUtils.logitude) ;
                qrScan.initiateScan();
            }
        }
        else{
            CommonUtils.getInstance().displayToast(this,"gps is enabling! Please Wait.");
        }
    }

}
