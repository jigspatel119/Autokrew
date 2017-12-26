package com.autokrew.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by SONY on 03-08-2016.
 */
public class LoginModel {

   // {"status":"Success","message":"Customer login successfully"}


    @SerializedName("loginPK")
    @Expose
    private Integer loginPK;
    @SerializedName("employeeFK")
    @Expose
    private Integer employeeFK;
    @SerializedName("response")
    @Expose
    private String response;
    @SerializedName("responseCode")
    @Expose
    private Integer responseCode;
    @SerializedName("roleFK")
    @Expose
    private Integer roleFK;
    @SerializedName("token")
    @Expose
    private String token;

    public Integer getLoginPK() {
        return loginPK;
    }

    public void setLoginPK(Integer loginPK) {
        this.loginPK = loginPK;
    }

    public Integer getEmployeeFK() {
        return employeeFK;
    }

    public void setEmployeeFK(Integer employeeFK) {
        this.employeeFK = employeeFK;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public Integer getRoleFK() {
        return roleFK;
    }

    public void setRoleFK(Integer roleFK) {
        this.roleFK = roleFK;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
