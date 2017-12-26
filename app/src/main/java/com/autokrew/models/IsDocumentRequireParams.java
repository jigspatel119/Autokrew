package com.autokrew.models;

/**
 * Created by SONY on 03-08-2016.
 */
public class IsDocumentRequireParams {


    /**
     * LeaveTypeFK : 6
     * TotalDays : 2
     */

    private int LeaveTypeFK;
    private String TotalDays;

    public int getLeaveTypeFK() {
        return LeaveTypeFK;
    }

    public void setLeaveTypeFK(int LeaveTypeFK) {
        this.LeaveTypeFK = LeaveTypeFK;
    }

    public String getTotalDays() {
        return TotalDays;
    }

    public void setTotalDays(String TotalDays) {
        this.TotalDays = TotalDays;
    }
}
