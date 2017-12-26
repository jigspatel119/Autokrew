package com.autokrew.models;

/**
 * Created by SONY on 03-08-2016.
 */
public class IsLeaveAppliedParams {


    /**
     * EmployeeFK : 8816
     * FromDate : 24/11/2017
     * ToDate : 24/11/2017
     */

    private int EmployeeFK;
    private String FromDate;
    private String ToDate;

    public int getEmployeeFK() {
        return EmployeeFK;
    }

    public void setEmployeeFK(int EmployeeFK) {
        this.EmployeeFK = EmployeeFK;
    }

    public String getFromDate() {
        return FromDate;
    }

    public void setFromDate(String FromDate) {
        this.FromDate = FromDate;
    }

    public String getToDate() {
        return ToDate;
    }

    public void setToDate(String ToDate) {
        this.ToDate = ToDate;
    }
}
