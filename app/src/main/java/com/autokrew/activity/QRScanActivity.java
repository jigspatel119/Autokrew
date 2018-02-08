package com.autokrew.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.autokrew.R;
import com.autokrew.models.ApplyAttendanceParam;
import com.autokrew.models.OutSideAttendanceModel;
import com.autokrew.network.ApiListener;
import com.autokrew.network.WebServices;
import com.autokrew.utils.Constant;
import com.autokrew.utils.GPSTracker;
import com.autokrew.utils.Permissions;
import com.autokrew.utils.Pref;
import com.autokrew.utils.PreferenceHelper;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;


public class QRScanActivity extends AppCompatActivity implements ApiListener {

    //General Variables
    private static final String TAG = QRScanActivity.class.getSimpleName();
    Activity mActivity = QRScanActivity.this;

    Toolbar mToolbar;
    //QRCodeReaderView mQrCodeReaderView;

    //qr code scanner object
    private IntentIntegrator qrScan;

    OutSideAttendanceModel mOutsideSideAttendanceModel;
    TextView btn_signin;

    boolean isCodeRead = false;
    String mLat ,mLong ,mAddress;
    private PreferenceHelper mPreferenceHelper;
    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscan);
        mPreferenceHelper = new PreferenceHelper(this);




        //Initialize Views
        initializeViews();

        //Initialize Data
        initializeData();

    }

    /**
     * Initialize views
     */
    private void initializeViews() {
        // TODO ... Initialize your views here


       // mQrCodeReaderView = (QRCodeReaderView) this.findViewById(R.id.QRCodeScanner);


        btn_signin = (TextView)this.findViewById(R.id.btn_signin);
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
                //What to do on back clicked
                finish();
                QRScanActivity.this.overridePendingTransition(R.anim.activity_open_scale,R.anim.activity_close_translate);

            }
        });

        //Initialize QRCodeReaderView
/*
        mQrCodeReaderView.setQRDecodingEnabled(true);
        mQrCodeReaderView.setAutofocusInterval(1000L);   //1000L

        mQrCodeReaderView.setOnQRCodeReadListener(new QRCodeReaderView.OnQRCodeReadListener() {
            @Override
            public void onQRCodeRead(String text, PointF[] points) {

                if (!isCodeRead) {
                    isCodeRead = true;
                    mAddress = mPreferenceHelper.getAddress();
                    callOutSideAttendanceFromMobileAppAPI(text,mLat,mLong ,mAddress);
                }
            }
        });
        mQrCodeReaderView.startCamera();
*/

        qrScan = new IntentIntegrator(this);
        //qrScan.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
        qrScan.setPrompt("Scan QR Code");
        qrScan.setOrientationLocked(false);

        //qrScan.setCameraId(0);  // Use a specific camera of the device
        //qrScan.setBeepEnabled(false);
        //qrScan.setBarcodeImageEnabled(true);

        //intializing scan object
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Permissions.getInstance().isLocationPermissionGranted(mActivity)) {

                    location =  new GPSTracker(mActivity).getLocation();

                    if(location!=null){
                        mLat = String.valueOf(location.getLatitude());
                        mLong= String.valueOf(location.getLongitude());
                        qrScan.initiateScan();
                    }
                    else{

                    }

                }

                else {
                    //finish();
                }


            }
        });

    }

    private void callOutSideAttendanceFromMobileAppAPI(String QR_text  ,String mLat ,String mLong ,String mAddress) {

       // CommonUtils.getInstance().displayToast(this,QR_text);

        ApplyAttendanceParam params = new ApplyAttendanceParam();
        params.setAtt_Lat(mLat);
        params.setAtt_Long(mLong);
        params.setIsQrCode("1");
        params.setQrCode(QR_text);
        params.setAtt_PhisicalAddress(mAddress);
        params.setEmployeePk(String.valueOf(Pref.getValue(this, Constant.PREF_SESSION_EMPLOYEE_FK, 0)));

        String mToken = Pref.getValue(this, Constant.PREF_TOKEN, "");

        new WebServices(this/* ActivityContext */, QRScanActivity.this /* ApiListener */, true /* show progress dialog */,
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
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                try {
                    //converting the data to json
                    JSONObject obj = new JSONObject(result.getContents());
                    //setting values to textviews
                    //textViewName.setText(obj.getString("name"));
                   // textViewAddress.setText(obj.getString("address"));
                  //  Toast.makeText(this, "Result Found" + obj.getString("QR"), Toast.LENGTH_LONG).show();

                    mAddress = mPreferenceHelper.getAddress();
                    callOutSideAttendanceFromMobileAppAPI(obj.getString("QR"),mLat,mLong ,mAddress);

                    //api calls

                } catch (JSONException e) {
                    e.printStackTrace();
                    //if control comes here
                    //that means the encoded format not matches
                    //in this case you can display whatever data is available on the qrcode
                    //to a toast
                   // Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                    Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    public void showAlert() {

        try {
            final AlertDialog.Builder builder = new
                    AlertDialog.Builder(QRScanActivity.this);
            builder.setCancelable(false);
            builder.setPositiveButton("Settings", new
                    DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {

                            Intent intent = new Intent(
                                    Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(intent);
                            return;
                        }
                    });
            builder.setNegativeButton("Cancel", new
                    DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {

                            return;
                        }
                    });
            builder.setMessage("Please enable your GPS");
            builder.create().getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
            builder.create().show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
