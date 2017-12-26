package com.autokrew.models;

/**
 * Created by SONY on 03-08-2016.
 */
public class CancleLeaveParams {


    /**
     * LeaveDetailPK : 6707
     * SessionEmployeeFK : 8816
     */

    private String LeaveDetailPK;
    private String SessionEmployeeFK;

    public String getLeaveDetailPK() {
        return LeaveDetailPK;
    }

    public void setLeaveDetailPK(String LeaveDetailPK) {
        this.LeaveDetailPK = LeaveDetailPK;
    }

    public String getSessionEmployeeFK() {
        return SessionEmployeeFK;
    }

    public void setSessionEmployeeFK(String SessionEmployeeFK) {
        this.SessionEmployeeFK = SessionEmployeeFK;
    }
}
