package com.autokrew.activity;

import android.app.Activity;
import android.graphics.PointF;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.autokrew.R;
import com.autokrew.models.ApplyAttendanceParam;
import com.autokrew.models.OutSideAttendanceModel;
import com.autokrew.network.ApiListener;
import com.autokrew.network.WebServices;
import com.autokrew.utils.CommonUtils;
import com.autokrew.utils.Constant;
import com.autokrew.utils.GPSTracker;
import com.autokrew.utils.Permissions;
import com.autokrew.utils.Pref;
import com.autokrew.utils.PreferenceHelper;
import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;


public class QRScanActivity extends AppCompatActivity implements ApiListener {

    //General Variables
    private static final String TAG = QRScanActivity.class.getSimpleName();
    Activity mActivity = QRScanActivity.this;

    Toolbar mToolbar;
    QRCodeReaderView mQrCodeReaderView;
    OutSideAttendanceModel mOutsideSideAttendanceModel;

    boolean isCodeRead = false;
    String mLat ,mLong ,mAddress;
    private PreferenceHelper mPreferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscan);
        mPreferenceHelper = new PreferenceHelper(this);

       if (Permissions.getInstance().isLocationPermissionGranted(mActivity)) {

           Location location =  new GPSTracker(mActivity).getLocation();

           if(location!=null){
              //  CommonUtils.getInstance().displayToast(QRScanActivity.this,"lat >> "+location.getLatitude());
               mLat = String.valueOf(location.getLatitude());
               mLong= String.valueOf(location.getLongitude());
               //callOutSideAttendanceFromMobileAppAPI("123456",mLat,mLong);
           }
           else{
               //new GPSTracker(mActivity).showAlert();
               CommonUtils.getInstance().displayToast(this,"Please enable your GPS");
               finish();
           }

       }

       else {
           finish();
       }


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


        mQrCodeReaderView = (QRCodeReaderView) this.findViewById(R.id.QRCodeScanner);
        mToolbar = (Toolbar) this.findViewById(R.id.toolbar);
        //Initialize toolbar
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("QR code scan");
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
        mQrCodeReaderView.setQRDecodingEnabled(true);
        mQrCodeReaderView.setAutofocusInterval(1000L);
        mQrCodeReaderView.setOnQRCodeReadListener(new QRCodeReaderView.OnQRCodeReadListener() {
            @Override
            public void onQRCodeRead(String text, PointF[] points) {

                if (!isCodeRead) {
                    isCodeRead = true;
                   // CommonUtils.getInstance().displayToast(QRScanActivity.this, text);
                    /*CommonUtils.getInstance().displayToast(QRScanActivity.this, text);
                    finish();*/

                    mAddress = mPreferenceHelper.getAddress();
                    callOutSideAttendanceFromMobileAppAPI(text,mLat,mLong ,mAddress);
                }
            }
        });
        mQrCodeReaderView.startCamera();

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
                CommonUtils.getInstance().displayToast(QRScanActivity.this,""+mOutsideSideAttendanceModel.getTable().get(0).getResult());
                finish();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onApiFailure(Throwable mThrowable) {

    }
}
