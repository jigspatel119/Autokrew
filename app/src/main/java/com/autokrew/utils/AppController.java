package com.autokrew.utils;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.danikula.videocache.HttpProxyCacheServer;


/**
 * Created by root on 6/3/17.
 */
public class AppController extends Application {

    private static AppController mAppController;
    private static PreferenceHelper mPreferenceHelper;
    //private static XmppConfiguration mXmppConfiguration;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppController = this;
        mPreferenceHelper = new PreferenceHelper(mAppController);
       // mXmppConfiguration = new XmppConfiguration(getAppContext());
    }

    public static synchronized PreferenceHelper getAppPref() {
        if (mPreferenceHelper == null) {
            mPreferenceHelper = new PreferenceHelper(getAppContext());
        }
        return mPreferenceHelper;
    }

  /*  public static synchronized XmppConfiguration getXmppConfiguration() {
        if (mXmppConfiguration == null) {
            mXmppConfiguration = new XmppConfiguration(getAppContext());
        }
        return mXmppConfiguration;
    }*/

    public static Context getAppContext() {
        return mAppController.getApplicationContext();
    }

    private HttpProxyCacheServer proxy;

    public static HttpProxyCacheServer getProxy(Context context) {
        AppController app = (AppController) context.getApplicationContext();
        return app.proxy == null ? (app.proxy = app.newProxy()) : app.proxy;
    }

    private HttpProxyCacheServer newProxy() {
        return new HttpProxyCacheServer(this);
    }

    public static synchronized AppController getInstance() {
        return mAppController;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }

}