package com.autokrew.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.autokrew.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.List;
import java.util.Locale;

import static com.autokrew.utils.AppController.getAppContext;

/**
 * Created by Kumarpalsinh Rana on 6/5/16.
 */
public class CommonUtils {

    private static String TAG = CommonUtils.class.getSimpleName();
    private static CommonUtils singleton;


    public static double lattitude = 0.0;
    public static double logitude = 0.0;


    private static PreferenceHelper mPreferenceHelper;

    /**
     * This function is used to get instance of CommonUtils class
     */
    public static CommonUtils getInstance() {
        if (singleton == null) {
            singleton = new CommonUtils();
        }
        return singleton;
    }


    public static boolean isMyServiceRunning(Context context, Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static void turnOffGPS(Context context){
        Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
        intent.putExtra("enabled", false);
        context.sendBroadcast(intent);
    }





    /**
     * This function is used for hiding soft keyboard
     */
    public void hideSoftKeyboard(Activity mActivity) {
        // Check if no view has focus:
        View view = mActivity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * This function is used to get Unique device ID
     */
    public String getDeviceID(Context mContext) {
        String android_id = Settings.Secure.getString(mContext.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return android_id;
    }

    /**
     * This function is used to get Application version code
     */
    public int getAppVersionCode(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (Exception e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    /**
     * This function is used to get Application version name
     */
    public String getAppVersionName(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (Exception e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    /**
     * Start Activity with stack and without bundle data
     */
    public void startActivity(Context mContext, Class<?> activityClass) {
        try {

            Intent intent = new Intent(mContext, activityClass);
            mContext.startActivity(intent);
        } catch (Exception e) {
            AppLog.loadStackTrace(e);
        }
    }

    /**
     * Start Activity with stack and with bundle data
     */
    public void startActivity(Context mContext, Class<?> activityClass, Bundle mBundle) {
        try {
            Intent intent = new Intent(mContext, activityClass);
            intent.putExtras(mBundle);
            mContext.startActivity(intent);
        } catch (Exception e) {
            AppLog.loadStackTrace(e);
        }
    }

    /**
     * Start Activity without stack and without bundle data
     */
    public void startActivityWithoutStack(Context mContext, Class<?> activityClass) {
        try {

            Intent intent = new Intent(mContext, activityClass);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mContext.startActivity(intent);
        } catch (Exception e) {
            AppLog.loadStackTrace(e);
        }
    }

    /**
     * Start Activity without stack and with bundle data
     */
    public void startActivityWithoutStack(Context mContext, Class<?> activityClass, Bundle mBundle) {
        try {
            Intent intent = new Intent(mContext, activityClass);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtras(mBundle);
            mContext.startActivity(intent);
        } catch (Exception e) {
            AppLog.loadStackTrace(e);
        }
    }


    /**
     * This function is used to show SnackBar at bottom
     */
    public void displaySnackBar(View view, String message) {
        view.getId();
        Snackbar snackbar = Snackbar
                .make(view, message, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    /**
     * This function is used to show Toast Message
     */
    public void displayToast(final Context context, String respMsg) {
        try {
            if (context != null) {
                Toast.makeText(context, respMsg, Toast.LENGTH_SHORT)
                        .show();
            }
        } catch (Exception e) {
            AppLog.loadStackTrace(e);
        }

    }

    /**
     * This function is used to display Alert Dialog
     */
    public void displayAlert(final Context context, String message) {

        try {
            final Dialog mDialog = getCustomDialogWithOneAction(context, message, "Ok");
            Button btnPositive;
            btnPositive = (Button) mDialog.findViewById(R.id.btnCustomPopupPositive);
            btnPositive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog.dismiss();
                }
            });
        } catch (Exception e) {
            AppLog.loadStackTrace(e);
        }

    }

    /**
     * Check for email format validation
     */
    public boolean isValidEmail(CharSequence target) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(target)
                .matches();
    }

    /**
     * This function is used for checking internet connection
     */
    public boolean isNetworkAvailable(Context mContext) {

       /* try {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        } catch (Exception e) {
            AppLog.loadStackTrace(e);
            return false;
        }*/

        if (NetworkUtil.getConnectivityStatus(mContext) == NetworkUtil.TYPE_NOT_CONNECTED) {
            return false;
        } else {
            return true;
        }
    }


    public boolean isConnectionAvailable(Context mContext) {


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);


        ConnectivityManager cm = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            try {
                URL url = new URL("http://www.google.com/");
                HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                urlc.setRequestProperty("User-Agent", "test");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(5000); // mTimeout is in seconds
                urlc.connect();
                if (urlc.getResponseCode() == 200) {
                    return true;
                } else {
                    return false;
                }
            } catch (IOException e) {
                Log.i("warning", "Error checking internet connection", e);
                return false;
            }
        }

        return false;
    }

    /**
     * This function is used show dialog with one action
     */
    public Dialog getCustomDialogWithOneAction(Context mContext, String strMessage,
                                               String btnPositiveMessage) {

        Dialog dialogCustom;
        TextView txtCustomPopupMessage;
        Button btnCustomPopupPositive;

        dialogCustom = new Dialog(mContext);
        dialogCustom.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogCustom.setCancelable(false);
        dialogCustom.getWindow().setBackgroundDrawable(new ColorDrawable
                (android.graphics.Color.TRANSPARENT));
        dialogCustom.setContentView(R.layout.custom_dialog_one_actions);


        txtCustomPopupMessage = (TextView) dialogCustom.findViewById(R.id.txtCustomPopupMessage);
        txtCustomPopupMessage.setText(strMessage);

        btnCustomPopupPositive = (Button) dialogCustom.findViewById(R.id.btnCustomPopupPositive);
        btnCustomPopupPositive.setText(btnPositiveMessage);

        dialogCustom.show();
        return dialogCustom;

    }


    /**
     * This function is used show dialog with two actions
     */
    public Dialog getCustomDialogWithTwoActions(Context mContext, String strMessage,
                                                String btnPositiveMessage,
                                                String btnNegativeMessage) {

        Dialog dialogCustom;
        TextView txtCustomPopupMessage;
        Button btnCustomPopupPositive, btnCustomPopupNegative;

        dialogCustom = new Dialog(mContext);
        dialogCustom.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogCustom.setCancelable(false);
        dialogCustom.getWindow().setBackgroundDrawable(new
                ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialogCustom.setContentView(R.layout.custom_dialog_two_actions);

        txtCustomPopupMessage = (TextView) dialogCustom.findViewById(R.id.txtCustomPopupMessage);
        txtCustomPopupMessage.setText(strMessage);

        btnCustomPopupPositive = (Button) dialogCustom.findViewById(R.id.btnCustomPopupPositive);
        btnCustomPopupPositive.setText(btnPositiveMessage);

        btnCustomPopupNegative = (Button) dialogCustom.findViewById(R.id.btnCustomPopupNegative);
        btnCustomPopupNegative.setText(btnNegativeMessage);

        dialogCustom.show();
        return dialogCustom;

    }

    /**
     * This function is used to Print Hash Key
     */
    public String printKeyHash(Activity context) {
        PackageInfo packageInfo;
        String key = null;
        try {
            // getting application package name, as defined in manifest
            String packageName = context.getApplicationContext()
                    .getPackageName();

            // Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(
                    packageName, PackageManager.GET_SIGNATURES);

            AppLog.debugD("Package Name = " + context.getApplicationContext()
                    .getPackageName());

            for (Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));

                // String key = new String(Base64.encodeBytes(md.digest()));
                AppLog.debugD("Key Hash = " + key);
            }
        } catch (Exception e) {
            AppLog.debugD("Exception = " + e.toString());
        }

        return key;
    }

    public static String decodeString(String encoded) {

        String decodedString = "";
        try {
            byte[] dataDec = Base64.decode(encoded, Base64.NO_WRAP);
            decodedString = new String(dataDec, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return encoded;
        } catch (Exception e) {
            e.printStackTrace();
            return encoded;
        }finally {
            return decodedString;
        }

    }

    public static String encodeString(String s) {
        byte[] data = new byte[0];
        try {
            data = s.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            String base64Encoded = Base64.encodeToString(data, Base64.NO_WRAP);
            return base64Encoded;
        }
    }


    public static Animation getAnimation(Context ctx){
        Animation animBounce;
        // load the animation


        animBounce = AnimationUtils.loadAnimation(ctx,
                R.anim.bounce_interpolator);

        return animBounce;
    }


    public  static void setupCustomToolbar(Toolbar toolbar){
        //set custom font to the toolbar
        TextView toolbarTitle = null;
        for (int i = 0; i < toolbar.getChildCount(); ++i) {
            View child = toolbar.getChildAt(i);
            // assuming that the title is the first instance of TextView
            // you can also check if the title string matches
            if (child instanceof TextView) {
                toolbarTitle = (TextView) child;
                Typeface copperplateGothicLight = Typeface.createFromAsset(getAppContext().getAssets(), "GillSans-SemiBold.ttf");
                toolbarTitle.setTypeface(copperplateGothicLight);
                break;
            }
        }
    }


    /**
     * check for gps
     *
     * @param mContext
     * @return
     */
    public static boolean isGpsEnabled(Context mContext) {
        LocationManager lm = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        boolean gpsEnabled = false;
        boolean networkEnabled = false;

        try {
            gpsEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
            Log.e(TAG, "isGpsEnabled >> " + ex);
        }

        try {
            networkEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
            Log.e(TAG, "showGPSDisabledAlertToUser >> " + ex);
        }
        Log.d(TAG, "isGpsEnabled >> " + gpsEnabled + "," + networkEnabled);
        return gpsEnabled;
    }

    public static void displayLocationSettingsRequest(final Context context) {

        final Activity act = (Activity) context;
        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API).build();
        googleApiClient.connect();

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(10000 / 2);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        Log.i(TAG, "All location settings are satisfied.");
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        Log.i(TAG, "Location settings are not satisfied. Show the user a dialog to upgrade location settings ");

                        try {
                            // Show the dialog by calling startResolutionForResult(), and check the result
                            // in onActivityResult().
                            status.startResolutionForResult(act, Constant.GPS_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            Log.i(TAG, "PendingIntent unable to execute request.");
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        Log.i(TAG, "Location settings are inadequate, and cannot be fixed here. Dialog not created.");
                        break;
                    case LocationSettingsStatusCodes.CANCELED:
                        Log.i(TAG, "Location settings canceled.");
                        break;
                }
            }
        });
    }




    public static void getAddressFromLatLong(Context mContext, double latitude, double longitude) {

        mPreferenceHelper = new PreferenceHelper(mContext);

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

                AppLog.debugE("address >>>>  " + address);
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



    public String getIMEI(Context mContext){

        TelephonyManager telephonyManager = (TelephonyManager)mContext.getSystemService(Context.TELEPHONY_SERVICE);

        return telephonyManager.getDeviceId();
    }



}
