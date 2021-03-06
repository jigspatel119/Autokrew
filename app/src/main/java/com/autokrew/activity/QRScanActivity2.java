package com.autokrew.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import com.autokrew.utils.Pref;
import com.autokrew.utils.PreferenceHelper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;


public class QRScanActivity2 extends AppCompatActivity implements ApiListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener  {

    //General Variables
    private static final String TAG = QRScanActivity2.class.getSimpleName();
    Activity mActivity = QRScanActivity2.this;

    Toolbar mToolbar;
    //QRCodeReaderView mQrCodeReaderView;

    //qr code scanner object
    private IntentIntegrator qrScan;

    OutSideAttendanceModel mOutsideSideAttendanceModel;
    TextView txt_qr_scan;

    boolean isCodeRead = false;
    String mLat2 ,mLong2 ,mAddress;
    private PreferenceHelper mPreferenceHelper;
  //  Location location;
    // Google client to interact with Google API

    private static final int LOCATION_REQUEST_CODE = 1;

    String IMEINumber = "";
    Location mLastLocation;


    //=========================
    private LocationRequest mLocationRequest;
    private long LOCATION_UPDATE_INTERVAL = 2000;// 2 sec  [60000 = 1 min]
    private GoogleApiClient mGoogleApiClient;
    //==========================




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscan);
        mPreferenceHelper = new PreferenceHelper(this);
        IMEINumber = CommonUtils.getInstance().getIMEI(this);


        Log.e(TAG, "IMEINumber ::"+CommonUtils.getInstance().getIMEI(this));




        //Initialize Views
        initializeViews();



    }

    /**
     * Initialize views
     */
    private void initializeViews() {
        // TODO ... Initialize your views here


       // mQrCodeReaderView = (QRCodeReaderView) this.findViewById(R.id.QRCodeScanner);


        txt_qr_scan = (TextView)this.findViewById(R.id.txt_qr_scan);
        mToolbar = (Toolbar) this.findViewById(R.id.toolbar);
        //Initialize toolbar
        setSupportActionBar(mToolbar);
        //mToolbar.setTitle("QR code scan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mToolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopLocationService();
                finish();
                QRScanActivity2.this.overridePendingTransition(R.anim.activity_open_scale,R.anim.activity_close_translate);

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

                if (!CommonUtils.isGpsEnabled(QRScanActivity2.this)) {
                    CommonUtils.displayLocationSettingsRequest(QRScanActivity2.this);
                } else {
                    requestPermissionForLocation();
                }


            }
        });
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        stopLocationService();
        //finish();
    }

    private void requestPermissionForLocation() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                startLocationService();

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        LOCATION_REQUEST_CODE);
            }
        } else {
            startLocationService();
        }
    }

    private void startLocationService() {


        buildGoogleApiClient();
        createLocationRequest();
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }
       LocationServices.getFusedLocationProviderClient(this).requestLocationUpdates(mLocationRequest, locationCallback,
                Looper.myLooper());

        CommonUtils.getInstance().displayToast(QRScanActivity2.this,"lat >>> " +CommonUtils.lattitude);

        /*if (String.valueOf(CommonUtils.lattitude) != null && String.valueOf(CommonUtils.logitude) != null) {

            mLat2 = String.valueOf(CommonUtils.lattitude);
            mLong2 = String.valueOf(CommonUtils.logitude) ;
            qrScan.initiateScan();
        }*/

    }


    private void callOutSideAttendanceFromMobileAppAPI(String QR_text  ,String mLat2 ,String mLong2 ,String mAddress) {

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

        new WebServices(this/* ActivityContext */, QRScanActivity2.this /* ApiListener */, true /* show progress dialog */,
                true/*for new retrofitclient*/).
                callOutSideAttendanceFromMobileAppAPI(mToken, params);
        //  }
    }

    /**
     * Initialize data
     */
    private void initializeData() {

        // TODO ... Initialize your data here




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
                Toast.makeText(this, ""+mOutsideSideAttendanceModel.getTable().get(0).getResult(), Toast.LENGTH_LONG).show();

                stopLocationService();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onApiFailure(Throwable mThrowable) {

    }


    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(requestCode == 123){

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

                    mAddress = mPreferenceHelper.getAddress();

                    //mLat1, mLong1 //from qrcode
                    //mLat2, mLong2 //device lat ,long

                    Log.e(TAG, "onActivityResult:mLat2 ## "+CommonUtils.lattitude);
                    Log.e(TAG, "onActivityResult:mLong2 ##  "+CommonUtils.logitude);

                    if(distance(Double.parseDouble(obj.getString("lat"))
                            , Double.parseDouble(obj.getString("long"))
                            , CommonUtils.lattitude
                            , CommonUtils.logitude) <= Double.parseDouble(obj.getString("Radius"))){  //  {0.01<=10 miter}  //


                        callOutSideAttendanceFromMobileAppAPI(obj.getString("QR"),mLat2,mLong2 ,mAddress);
                    }else{
                        Toast.makeText(this, "Wrong Location.", Toast.LENGTH_LONG).show();
                        stopLocationService();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    stopLocationService();
                    Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    /** calculates the distance between two locations in MILES */
    private double distance(double lat1, double lng1, double lat2, double lng2) {

        double earthRadius = 3958.75; // in miles, change to 6371 for kilometer output

        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);

        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);

        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        double dist = earthRadius * c;
        return dist ; // output distance, in MILES // 100 meters
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
        if (!CommonUtils.isGpsEnabled(this)) {
            CommonUtils.displayLocationSettingsRequest(this);
        }
        else {

        }

    }



    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e(TAG, "onConnectionFailed: Called");
        buildGoogleApiClient();

    }
    synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();


        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopLocationService();
        Log.e(TAG, "mGoogleApiClient: onDestroy : *****client disconnect***" );

    }

    private LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {

            for (Location location : locationResult.getLocations()) {
                Log.e(TAG, "Location: " + location.getLatitude() + " " + location.getLongitude() + " " + location.getBearing());
                Log.e(TAG, "onCreate: lat-->" + location.getLatitude());
                Log.e(TAG, "onCreate: lon-->" + location.getLongitude());
                CommonUtils.lattitude = location.getLatitude();
                CommonUtils.logitude = location.getLongitude();
              //  CommonUtils.getAddressFromLatLong(QRScanActivity2.this, location.getLatitude(), location.getLongitude());
            }


        }
    };

    private void createLocationRequest() {

        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(LOCATION_UPDATE_INTERVAL); // Update location every 19 minute
        mLocationRequest.setFastestInterval(LOCATION_UPDATE_INTERVAL);
        /**
         * To get location information consistently
         * mLocationRequest.setNumUpdates(1) Commented out
         * Uncomment the code below
         */
        // mLocationRequest.setNumUpdates(1);
        //mLocationRequest.setSmallestDisplacement()

    }

    private void stopLocationService() {

        if (mGoogleApiClient != null) {
            LocationServices.getFusedLocationProviderClient(this).removeLocationUpdates(locationCallback);
            mGoogleApiClient.disconnect();
        }
    }

}
