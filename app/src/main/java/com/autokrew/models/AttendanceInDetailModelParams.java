package com.autokrew.models;

/**
 * Created by SONY on 03-08-2016.
 */
public class AttendanceInDetailModelParams {

    /**
     * EmployeeFK : AN10000015
     * MonthFK : 3
     * YearFk : 49
     * ApprovalStatus : P
     */

    private String EmployeeFK;
    private int MonthFK;
    private int YearFk;
    private String ApprovalStatus;

    public String getEmployeeFK() {
        return EmployeeFK;
    }

    public void setEmployeeFK(String EmployeeFK) {
        this.EmployeeFK = EmployeeFK;
    }

    public int getMonthFK() {
        return MonthFK;
    }

    public void setMonthFK(int MonthFK) {
        this.MonthFK = MonthFK;
    }

    public int getYearFk() {
        return YearFk;
    }

    public void setYearFk(int YearFk) {
        this.YearFk = YearFk;
    }

    public String getApprovalStatus() {
        return ApprovalStatus;
    }

    public void setApprovalStatus(String ApprovalStatus) {
        this.ApprovalStatus = ApprovalStatus;
    }
}
