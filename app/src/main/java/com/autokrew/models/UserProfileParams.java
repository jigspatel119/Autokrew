package com.autokrew.models;

/**
 * Created by SONY on 03-08-2016.
 */
public class UserProfileParams {


    /**
     * flag : Basic
     * EmployeeFK : 8816
     */

    private String flag;
    private int EmployeeFK;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getEmployeeFK() {
        return EmployeeFK;
    }

    public void setEmployeeFK(int EmployeeFK) {
        this.EmployeeFK = EmployeeFK;
    }
}
