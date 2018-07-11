package com.autokrew.models;

/**
 * Created by SONY on 03-08-2016.
 */
public class ManageLeaveParams {

 /*   oldPassword : string
    newPassword : string
    LoginFK: string*/

    private String Flag;
    private int SessionEmployeeFk;
    private int LeaveDetailPK;
    private Object ApprovalStatus;
    private Object ApprovalRemarks;

    public String getFlag() {
        return Flag;
    }

    public void setFlag(String Flag) {
        this.Flag = Flag;
    }

    public int getSessionEmployeeFk() {
        return SessionEmployeeFk;
    }

    public void setSessionEmployeeFk(int SessionEmployeeFk) {
        this.SessionEmployeeFk = SessionEmployeeFk;
    }

    public int getLeaveDetailPK() {
        return LeaveDetailPK;
    }

    public void setLeaveDetailPK(int LeaveDetailPK) {
        this.LeaveDetailPK = LeaveDetailPK;
    }

    public Object getApprovalStatus() {
        return ApprovalStatus;
    }

    public void setApprovalStatus(Object ApprovalStatus) {
        this.ApprovalStatus = ApprovalStatus;
    }

    public Object getApprovalRemarks() {
        return ApprovalRemarks;
    }

    public void setApprovalRemarks(Object ApprovalRemarks) {
        this.ApprovalRemarks = ApprovalRemarks;
    }
}
