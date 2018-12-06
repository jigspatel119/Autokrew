package com.autokrew.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.autokrew.R;
import com.autokrew.dialogs.AwesomeProgressDialog;
import com.autokrew.interfaces.DialogListener;
import com.autokrew.models.PointingUrlModel;
import com.autokrew.network.ApiListener;
import com.autokrew.network.WebServices;
import com.autokrew.utils.BaseActivity;
import com.autokrew.utils.CommonUtils;
import com.autokrew.utils.Constant;
import com.autokrew.utils.Pref;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.File;


public class SplashActivity extends BaseActivity implements ApiListener ,DialogListener {


    //General Variables
    static final String TAG = SplashActivity.class.getSimpleName();
    Activity mActivity = SplashActivity.this;

    Handler handler;
    Runnable runnable;
    String auto_login;
    AwesomeProgressDialog mDialog;
   // ProgressBar progressBar;

   AVLoadingIndicatorView avi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_splash);
        CommonUtils.getInstance().printKeyHash(mActivity);

       // progressBar = (ProgressBar)this.findViewById(R.id.progressBar);

        avi  = (AVLoadingIndicatorView)this.findViewById(R.id.avi);

        handler = new Handler();
        auto_login = Pref.getValue(this, "auto_login", "");
        createFileDirectory();
        //Initialize Views

        //after signin and exit
        if(auto_login.equalsIgnoreCase("true")){
            avi.setVisibility(View.VISIBLE);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
            }, 3000);
        }

        //after signout...
        else if(auto_login.equalsIgnoreCase("false")){
            initializeData();
        }

        //very first time...
        else{
            displayAlert();
        }

    }

    private void displayAlert() {

        //context ,listner
        mDialog =  new AwesomeProgressDialog(this ,SplashActivity.this);
        mDialog.show();



    }

    private void apiCalls(String OTP) {

            new WebServices(this/* ActivityContext */, this /* ApiListener */, false /* show progress dialog */,
                    false /*for new retrofitclient*/).
                    callGetPointingUrlAPI(OTP);
    }



    /**
     * Initialize data
     */
    private void initializeData() {
        // TODO ... Initialize your data here

            avi.setVisibility(View.VISIBLE);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    startActivity(new Intent(SplashActivity.this, SigninActivity.class));
                    finish();
                }
            }, 3000);


    }


    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        handler.removeCallbacks(runnable);
    }


    private void createFileDirectory() {

        File fileDirMain = new File(Constant.FILE_DIRECTORY_MAIN);
        if (!fileDirMain.exists()) {
            fileDirMain.mkdirs();
        }

        File fileDirMedia = new File(Constant.FILE_DIRECTORY_MEDIA);
        if (!fileDirMedia.exists()) {
            fileDirMedia.mkdirs();
        }

        File fileDirDoc = new File(Constant.FILE_DIRECTORY_DOC);
        if (!fileDirDoc.exists()) {
            fileDirDoc.mkdirs();
        }

    }


    @Override
    public void onApiSuccess(Object mObject) {

        if (mObject instanceof PointingUrlModel) {
            PointingUrlModel model = (PointingUrlModel) mObject;

            if (model.getStatus().equals("Success")) {
                //CommonUtils.getInstance().startActivityWithoutStack(mActivity, HomeActivity.class);
                Log.e(TAG, "onApiSuccess: "+model.getData().getMobileURL() );
                Pref.setValue(this,Constant.PREF_MOBILE_URL,model.getData().getMobileURL());
                mDialog.hide();
                initializeData();
            } else {
                //CommonUtils.getInstance().displaySnackBar(relativeParentLogin, mLogin_model.getMessage());
                mDialog.hide();
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                CommonUtils.getInstance().displayToast(this,model.getMessage());
                displayAlert();
              //  Log.e(TAG, "onApiSuccess: "+model.getMessage());
            }
        }
    }

    @Override
    public void onApiFailure(Throwable mThrowable) {
        mDialog.hide();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        CommonUtils.getInstance().displayToast(this,"Incorrect OTP!");
        displayAlert();
    }

    @Override
    public void dialogClick(String otp) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        apiCalls(otp);
    }
}
