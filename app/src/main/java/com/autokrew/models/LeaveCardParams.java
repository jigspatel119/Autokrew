package com.autokrew.models;

/**
 * Created by SONY on 03-08-2016.
 */
public class LeaveCardParams {

    /**
     * Flag : Grid
     * EmployeeFK : 8816
     * Month : 11
     * Year : 2017
     * LeaveStatusFK : -1
     */

    private String Flag;
    private String EmployeeFK;
    private String Month;
    private String Year;
    private String LeaveStatusFK;

    public String getFlag() {
        return Flag;
    }

    public void setFlag(String Flag) {
        this.Flag = Flag;
    }

    public String getEmployeeFK() {
        return EmployeeFK;
    }

    public void setEmployeeFK(String EmployeeFK) {
        this.EmployeeFK = EmployeeFK;
    }

    public String getMonth() {
        return Month;
    }

    public void setMonth(String Month) {
        this.Month = Month;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String Year) {
        this.Year = Year;
    }

    public String getLeaveStatusFK() {
        return LeaveStatusFK;
    }

    public void setLeaveStatusFK(String LeaveStatusFK) {
        this.LeaveStatusFK = LeaveStatusFK;
    }
}
