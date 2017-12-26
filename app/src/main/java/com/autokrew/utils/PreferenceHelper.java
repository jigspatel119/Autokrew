package com.autokrew.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Kuldeep on 2/1/17.
 */

public class PreferenceHelper {

    private static final String PREFS_NAME = "PreferenceHelper";

    private static final String IS_LOGIN = "isLogin";
    private static final String IS_XMPP_USER_LOGIN = "isXmppUserLogin";
    private static final String XMPP_LOGIN_USER_ID = "XmppLoginUserId";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String CITY = "city";
    private static final String STATE = "state";
    private static final String COUNTRY = "country";
    private static final String LIST_OF_SORTED_DATA_ID = "LIST_OF_SORTED_DATA_ID";


    private Context mContext;
    private SharedPreferences mSharedPreferences;


    public PreferenceHelper(Context context) {

        this.mContext = context;
        mSharedPreferences = context.getSharedPreferences(PREFS_NAME, context.MODE_PRIVATE);

    }

    //=======================( Clear Preferences )================================
    public void clearSharedPreference() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    //=======================( IS LOGIN )================================
    public boolean getIsLogin() {
        return mSharedPreferences.getBoolean(IS_LOGIN, false);
    }

    public void setIsLogin(boolean isLoggedIn) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(IS_LOGIN, isLoggedIn);
        editor.commit();
    }

    //=======================( IS_XMPP_USER_LOGIN )================================
    public boolean getIsXmppUserLogin() {
        return mSharedPreferences.getBoolean(IS_XMPP_USER_LOGIN, false);
    }

    public void setIsXmppUserLogin(boolean isXmppUserLogin) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(IS_XMPP_USER_LOGIN, isXmppUserLogin);
        editor.commit();
    }

    //=======================( XMPP_LOGIN_USER_ID )================================
    public String getXmppLoginUserId() {
        return mSharedPreferences.getString(XMPP_LOGIN_USER_ID, "");
    }

    public void setXmppLoginUserId(String XmppLoginUserId) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(XMPP_LOGIN_USER_ID, XmppLoginUserId);
        editor.commit();
    }

    //=======================( LATITUDE )================================
    public String getLatitude() {
        return mSharedPreferences.getString(LATITUDE, "");
    }

    public void setLatitude(String latitude) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(LATITUDE, latitude);
        editor.commit();
    }

    //=======================( LONGITUDE )================================
    public String getLongitude() {
        return mSharedPreferences.getString(LONGITUDE, "");
    }

    public void setLongitude(String longitude) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(LONGITUDE, longitude);
        editor.commit();
    }

    //=======================( CITY )================================
    public String getCity() {
        return mSharedPreferences.getString(CITY, "");
    }

    public void setCity(String City) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(CITY, City);
        editor.commit();
    }//=======================( STATE )================================

    public String getState() {
        return mSharedPreferences.getString(STATE, "");
    }

    public void setState(String State) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(STATE, State);
        editor.commit();
    }


    //=======================( COUNTRY )================================

    public String getCountry() {
        return mSharedPreferences.getString(COUNTRY, "");
    }

    public void setCountry(String Country) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(COUNTRY, Country);
        editor.commit();
    }


    //=======================( LIST_OF_SORTED_DATA_ID )================================

    public String getLIST_OF_SORTED_DATA_ID() {
        return mSharedPreferences.getString(LIST_OF_SORTED_DATA_ID, "");
    }

    public void setLIST_OF_SORTED_DATA_ID(String list_of_sorted_data_id) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(LIST_OF_SORTED_DATA_ID, list_of_sorted_data_id);
        editor.commit();
    }
}