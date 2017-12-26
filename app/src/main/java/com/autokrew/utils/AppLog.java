package com.autokrew.utils;

import android.util.Log;

import com.autokrew.interfaces.Constants;


/**
 * Created by Kumarpalsinh Rana on 6/5/16.
 */
public class AppLog {

    public static final boolean APP_DEBUG = true;


    public static void debugE(String message) {
        if (APP_DEBUG) {
            Log.e(Constants.LOG_TAG, message);
        }
    }

    public static void debugE(String className, String message) {
        if (APP_DEBUG) {
            Log.e(Constants.LOG_TAG, className + " : " + message);
        }
    }


    public static void debugV(String message) {
        if (APP_DEBUG) {
            Log.v(Constants.LOG_TAG, message);
        }
    }

    public static void debugV(String className, String message) {
        if (APP_DEBUG) {
            Log.v(Constants.LOG_TAG, className + " : " + message);
        }
    }


    public static void debugD(String message) {
        if (APP_DEBUG) {
            Log.d(Constants.LOG_TAG, message);
        }
    }

    public static void debugD(String className, String message) {
        if (APP_DEBUG) {
            Log.d(Constants.LOG_TAG, className + " : " + message);
        }
    }


    public static void println(String message) {
        if (APP_DEBUG) {
            System.out.println(Constants.LOG_TAG + message);
        }
    }

    public static void loadStackTrace(Exception e) {
        if (APP_DEBUG) {
            e.printStackTrace();
        }
    }


}
