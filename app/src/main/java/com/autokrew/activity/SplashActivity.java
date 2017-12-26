package com.autokrew.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.autokrew.R;
import com.autokrew.adapter.AttendanceAdapter;
import com.autokrew.fragments.MyAttendanceFragment;
import com.autokrew.models.AttendanceModel;
import com.autokrew.models.PointingUrlModel;
import com.autokrew.network.ApiInterface;
import com.autokrew.network.ApiListener;
import com.autokrew.network.WebServices;
import com.autokrew.utils.AppController;
import com.autokrew.utils.BaseActivity;
import com.autokrew.utils.CommonUtils;
import com.autokrew.utils.Constant;
import com.autokrew.utils.Pref;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.autokrew.interfaces.Constants.SPLASH_TIME_OUT;


public class SplashActivity extends BaseActivity implements ApiListener {


    //General Variables
    static final String TAG = SplashActivity.class.getSimpleName();
    Activity mActivity = SplashActivity.this;

    Handler handler;
    Runnable runnable;
    String auto_login;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        CommonUtils.getInstance().printKeyHash(mActivity);

        progressBar = (ProgressBar)this.findViewById(R.id.progressBar);

        handler = new Handler();
        auto_login = Pref.getValue(this, "auto_login", "");
        createFileDirectory();
        //Initialize Views

        if(auto_login.equalsIgnoreCase("true")){
            progressBar.setVisibility(View.VISIBLE);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
            }, 3000);
        }
        else{
            displayAlert();
        }

    }

    private void displayAlert() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Enter Company Code"); //Set Alert dialog title here
       // alert.setMessage("Enter Your Name Here"); //Message here

        // Set an EditText view to get user input
        final EditText input = new EditText(this);
        input.setMaxLines(1);
        input.setLines(1);
        input.setSingleLine(true);
        alert.setView(input);


        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //You will get as string input data in this variable.
                // here we convert the input to a string and show in a toast.
                String OTP = input.getEditableText().toString().trim();

                if(OTP.length()>0){
                    apiCalls(OTP);
                }
                else{
                    CommonUtils.getInstance().displayToast(SplashActivity.this,"Please Insert OTP!");
                }


            } // End of onClick(DialogInterface dialog, int whichButton)
        });
        AlertDialog alertDialog = alert.create();
        alertDialog.show();
        alertDialog.setCancelable(false);
       /* Alert Dialog Code End*/
    }

    private void apiCalls(String OTP) {

            new WebServices(this/* ActivityContext */, this /* ApiListener */, true /* show progress dialog */,
                    false /*for new retrofitclient*/).
                    callGetPointingUrlAPI(OTP);

    }



    /**
     * Initialize data
     */
    private void initializeData() {
        // TODO ... Initialize your data here

            progressBar.setVisibility(View.VISIBLE);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    startActivity(new Intent(SplashActivity.this, SigninActivity.class));
                    finish();
                }
            }, 3000);


        /*runnable = new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub

                CommonUtils.getInstance().startActivityWithoutStack(getApplicationContext(), SigninActivity.class);
                if (AppController.getAppPref().getIsLogin()) {
                   // CommonUtils.getInstance().startActivityWithoutStack(getApplicationContext(), MainActivity.class);
                } else {
                    //CommonUtils.getInstance().startActivityWithoutStack(getApplicationContext(), SigninActivity.class);
                }

            }
        };
        handler.postDelayed(runnable, SPLASH_TIME_OUT);*/
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

        File fileDirCache = new File(Constant.FILE_DIRECTORY_MEDIA);
        if (!fileDirCache.exists()) {
            fileDirCache.mkdirs();
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

                initializeData();
            } else {
                //CommonUtils.getInstance().displaySnackBar(relativeParentLogin, mLogin_model.getMessage());
                CommonUtils.getInstance().displayToast(this,model.getMessage());
                displayAlert();
              //  Log.e(TAG, "onApiSuccess: "+model.getMessage());
            }
        }
    }

    @Override
    public void onApiFailure(Throwable mThrowable) {
        CommonUtils.getInstance().displayToast(this,"Incorrect OTP!");
        displayAlert();
    }
}
