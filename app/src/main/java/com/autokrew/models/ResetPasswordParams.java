package com.autokrew.models;

/**
 * Created by SONY on 03-08-2016.
 */
public class ResetPasswordParams {

 /*   oldPassword : string
    newPassword : string
    LoginFK: string*/

    private String oldPassword;
    private String newPassword;
    private String LoginFK;


    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String  getLoginFK() {
        return LoginFK;
    }

    public void setLoginFK(String loginFK) {
        LoginFK = loginFK;
    }
}
