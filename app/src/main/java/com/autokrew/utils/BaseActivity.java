package com.autokrew.utils;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


public class BaseActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {

    //General Variables
    private static final String TAG = BaseActivity.class.getSimpleName();
    Activity mActivity = BaseActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // AppController.getInstance().setConnectivityListener(this);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

        if (isConnected) {
           // AppController.getInstance().getXmppConfiguration().connect();
          //  Toast.makeText(BaseActivity.this, "Network available", Toast.LENGTH_LONG).show();
        } else {
          //  AppController.getInstance().getXmppConfiguration().disconnect();
           // Toast.makeText(BaseActivity.this, "Network not available", Toast.LENGTH_LONG).show();
        }

    }


}
