package com.autokrew.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by SONY on 03-08-2016.
 */
public class LoginModelParams {

   // {"status":"Success","message":"Customer login successfully"}


    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("MobileURL")
    @Expose
    private String mobileURL;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileURL() {
        return mobileURL;
    }

    public void setMobileURL(String mobileURL) {
        this.mobileURL = mobileURL;
    }
}
