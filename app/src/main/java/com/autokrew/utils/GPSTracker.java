package com.autokrew.utils;



import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.view.WindowManager;

import java.util.List;
import java.util.Locale;

import static android.content.Context.LOCATION_SERVICE;


public class GPSTracker {

    private LocationManager locationManager;

    Context mContext;

    // flag for GPS Status
    boolean isGPSEnabled = false;

    // flag for network status
    boolean isNetworkEnabled = false;


    Location location;
    double latitude;
    double longitude;

    private PreferenceHelper mPreferenceHelper;

    // The minimum distance to change updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 1 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 60 * 1000;// 1 minutes

    public GPSTracker(Context mContext) {
        this.mContext = mContext;
        getLocation();

    }

    public Location getLocation() {
        try {

            mPreferenceHelper = new PreferenceHelper(mContext);

            if (ContextCompat.checkSelfPermission(mContext, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(mContext, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return null;

            } else {

                locationManager = (LocationManager) mContext
                        .getSystemService(LOCATION_SERVICE);

                // getting GPS status
                isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

                // getting network status
                isNetworkEnabled = locationManager
                        .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

                if (!isGPSEnabled && !isNetworkEnabled) {
                    // no network provider is enabled
                    AppLog.debugE("Location ==> ", "No provider is enabled");
                    showAlert();
                } else {
                    if (isNetworkEnabled) {
                        locationManager.requestLocationUpdates(
                                LocationManager.NETWORK_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, new LocationListener() {
                                    @Override
                                    public void onStatusChanged(String provider, int status, Bundle extras) {
                                    }

                                    @Override
                                    public void onProviderEnabled(String provider) {
                                    }

                                    @Override
                                    public void onProviderDisabled(String provider) {
                                    }

                                    @Override
                                    public void onLocationChanged(final Location location) {

                                        latitude = location.getLatitude();
                                        longitude = location.getLongitude();

                                        getAddressFromLatLong(mContext, latitude, longitude);

                                        AppLog.debugE("Network ==> ", "lat : " + latitude);
                                        AppLog.debugE("Network ==> ", "long : " + longitude);

                                        mPreferenceHelper.setLatitude(latitude + "");
                                        mPreferenceHelper.setLongitude(longitude + "");
                                    }
                                });

                        AppLog.debugD("Network", "Network Enabled");
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();

                                getAddressFromLatLong(mContext, latitude, longitude);

                                AppLog.debugE("Network ==> ", "lat : " + latitude);
                                AppLog.debugE("Network ==> ", "long : " + longitude);

                                mPreferenceHelper.setLatitude(latitude + "");
                                mPreferenceHelper.setLongitude(longitude + "");
                            }
                        }
                    }
                    // if GPS Enabled get lat/long using GPS Services
                    if (isGPSEnabled) {
                        if (location == null) {
                            locationManager.requestLocationUpdates(
                                    LocationManager.GPS_PROVIDER,
                                    MIN_TIME_BW_UPDATES,
                                    MIN_DISTANCE_CHANGE_FOR_UPDATES, new LocationListener() {
                                        @Override
                                        public void onStatusChanged(String provider, int status, Bundle extras) {
                                        }

                                        @Override
                                        public void onProviderEnabled(String provider) {
                                        }

                                        @Override
                                        public void onProviderDisabled(String provider) {
                                        }

                                        @Override
                                        public void onLocationChanged(final Location location) {

                                            latitude = location.getLatitude();
                                            longitude = location.getLongitude();

                                            getAddressFromLatLong(mContext, latitude, longitude);

                                            AppLog.debugE("GPS ==> ", "lat : " + latitude);
                                            AppLog.debugE("GPS ==> ", "long : " + longitude);

                                            mPreferenceHelper.setLatitude(latitude + "");
                                            mPreferenceHelper.setLongitude(longitude + "");
                                        }
                                    });
                            AppLog.debugE("GPS", "GPS Enabled");


                            if (locationManager != null) {

                                location = locationManager
                                        .getLastKnownLocation(LocationManager.GPS_PROVIDER);

                                if (location != null) {
                                    latitude = location.getLatitude();
                                    longitude = location.getLongitude();

                                    getAddressFromLatLong(mContext, latitude, longitude);

                                    AppLog.debugE("GPS ==> ", "lat : " + latitude);
                                    AppLog.debugE("GPS ==> ", "long : " + longitude);

                                    mPreferenceHelper.setLatitude(latitude + "");
                                    mPreferenceHelper.setLongitude(longitude + "");

                                }
                            }


                        }
                    }




                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return location;
    }


    public void showAlert() {

        try {
            final AlertDialog.Builder builder = new
                    AlertDialog.Builder(mContext);
            builder.setCancelable(false);
            builder.setPositiveButton("Settings", new
                    DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {

                            Intent intent = new Intent(
                                    Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            mContext.startActivity(intent);
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

    public void getAddressFromLatLong(Context mContext, double latitude, double longitude) {

        // Check for internet connection
        if (CommonUtils.getInstance().isNetworkAvailable(mContext)) {
            try {
                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(mContext, Locale.getDefault());

                addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName();


                mPreferenceHelper.setCity(city);
                mPreferenceHelper.setState(state);
                mPreferenceHelper.setCountry(country);
                mPreferenceHelper.setAddress(address);

               // AppLog.debugE("address >>>>  " + address);
                AppLog.debugD("city : " + city);
                AppLog.debugD("state : " + state);
                AppLog.debugD("country : " + country);
                AppLog.debugD("postalCode : " + postalCode);
                AppLog.debugD("knownName : " + knownName);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}