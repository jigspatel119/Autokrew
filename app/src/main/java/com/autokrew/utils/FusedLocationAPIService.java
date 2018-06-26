package com.autokrew.utils;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;


public class FusedLocationAPIService extends Service implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private static final String TAG = FusedLocationAPIService.class.getSimpleName();
    public static double latitude = 0.0, longitude = 0.0;
    Location mLastLocation;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;


 /*   private SharedPreferenceClass mSharedPreferenceClass;
    private SharedPreferenceClass mSharedPrefs;
    private UpdateLatAndLonModel mUpdateLatAndLonModel;*/


    private Context mContext;
    //private long LOCATION_UPDATE_INTERVAL = 19 * 60 * 1000;// 19 minutes
    private long LOCATION_UPDATE_INTERVAL = 30 * 1000;//  30 sec

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {

        return longitude;
    }

    public void setLongitude(double longitude) {

        this.longitude = longitude;
    }

    @Override
    public void onConnected(Bundle bundle) {

        Log.e(TAG, "onConnected: Called");

        createLocationRequest();

        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if(mLastLocation!=null){
            Log.e(TAG, "onCreate: lat-->" + mLastLocation.getLatitude());
            Log.e(TAG, "onCreate: lon-->" + mLastLocation.getLongitude());
            CommonUtils.lattitude = mLastLocation.getLatitude();
            CommonUtils.logitude = mLastLocation.getLongitude();
           // CommonUtils.getAddressFromLatLong(mContext, latitude, longitude);

        }


    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.e(TAG, "onConnectionSuspended: Called");

    }

    @Override
    public void onLocationChanged(Location location) {

        latitude = location.getLatitude();
        longitude = location.getLongitude();


        setLatitude(latitude);
        setLongitude(longitude);


        CommonUtils.lattitude = latitude;
        CommonUtils.logitude = longitude;


        if(mLastLocation!=null){
            Log.e(TAG, "onLocationChanged: lat-->" + mLastLocation.getLatitude());
            Log.e(TAG, "onLocationChanged: lon-->" + mLastLocation.getLongitude());
        }

        CommonUtils.getAddressFromLatLong(mContext, latitude, longitude);
        Log.e(TAG, "onLocationChanged: latitude-->" + latitude);
        Log.e(TAG, "onLocationChanged: longitude-->" + longitude);



    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.e(TAG, "onConnectionFailed: Called");
        buildGoogleApiClient();
    }

    synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate: Called");

        mContext = FusedLocationAPIService.this;
        //mSharedPreferenceClass = new SharedPreferenceClass(FusedLocationAPIService.this);

        buildGoogleApiClient();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand: Called");

       // mSharedPrefs = new SharedPreferenceClass(getApplicationContext());

        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind: Called");
        return null;
    }

    private void createLocationRequest() {

        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(LOCATION_UPDATE_INTERVAL); // Update location every 19 minute
        mLocationRequest.setFastestInterval(LOCATION_UPDATE_INTERVAL);

    }




}
