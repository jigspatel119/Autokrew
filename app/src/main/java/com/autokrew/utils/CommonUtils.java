package com.autokrew.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;

import static com.autokrew.utils.AppController.getAppContext;

/**
 * Created by Kumarpalsinh Rana on 6/5/16.
 */
public class CommonUtils {

    private String TAG = CommonUtils.class.getSimpleName();
    private static CommonUtils singleton;

    /**
     * This function is used to get instance of CommonUtils class
     */
    public static CommonUtils getInstance() {
        if (singleton == null) {
            singleton = new CommonUtils();
        }
        return singleton;
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
                urlc.setConnectTimeout(500); // mTimeout is in seconds
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


   /* public static int setMonthFK(String month) {

        int monthFK = 0;

        if(month.equalsIgnoreCase("January")){
            monthFK = 1;
        } if(month.equalsIgnoreCase("February")){
            monthFK = 2;
        } if(month.equalsIgnoreCase("March")){
            monthFK = 3;
        } if(month.equalsIgnoreCase("April")){
            monthFK = 4;
        } if(month.equalsIgnoreCase("May")){
            monthFK = 5;
        } if(month.equalsIgnoreCase("June")){
            monthFK = 6;
        } if(month.equalsIgnoreCase("July")){
            monthFK = 7;
        } if(month.equalsIgnoreCase("August")){
            monthFK = 8;
        } if(month.equalsIgnoreCase("September")){
            monthFK = 9;
        } if(month.equalsIgnoreCase("October")){
            monthFK = 10;
        } if(month.equalsIgnoreCase("November")){
            monthFK = 11;
        } if(month.equalsIgnoreCase("December")){
            monthFK = 12;
        }

        return monthFK;
    }*/

}
