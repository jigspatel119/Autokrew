package com.autokrew.models;

/**
 * Created by SONY on 03-08-2016.
 */
public class SandwichParams {


    /**
     * EmployeeFK : 8816
     * LeaveTypeFK : 6
     * LeaveDayTypeFK : 2
     * LeaveFromDate : 22/11/2017
     */

    private int EmployeeFK;
    private int LeaveTypeFK;
    private int LeaveDayTypeFK;
    private String LeaveFromDate;
    private String LeaveToDate;

    public String getLeaveToDate() {
        return LeaveToDate;
    }

    public void setLeaveToDate(String leaveToDate) {
        LeaveToDate = leaveToDate;
    }

    public int getEmployeeFK() {
        return EmployeeFK;
    }

    public void setEmployeeFK(int EmployeeFK) {
        this.EmployeeFK = EmployeeFK;
    }

    public int getLeaveTypeFK() {
        return LeaveTypeFK;
    }

    public void setLeaveTypeFK(int LeaveTypeFK) {
        this.LeaveTypeFK = LeaveTypeFK;
    }

    public int getLeaveDayTypeFK() {
        return LeaveDayTypeFK;
    }

    public void setLeaveDayTypeFK(int LeaveDayTypeFK) {
        this.LeaveDayTypeFK = LeaveDayTypeFK;
    }

    public String getLeaveFromDate() {
        return LeaveFromDate;
    }

    public void setLeaveFromDate(String LeaveFromDate) {
        this.LeaveFromDate = LeaveFromDate;
    }
}
