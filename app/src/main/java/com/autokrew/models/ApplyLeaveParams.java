package com.autokrew.models;

import java.util.List;

/**
 * Created by SONY on 03-08-2016.
 */
public class ApplyLeaveParams {


    /**
     * EmployeeFK : 9644
     * LeaveTypeFK : 8
     * LeaveDayTypeFK : 2
     * LeaveFromDate : 23/11/2017
     * LeaveToDate : 23/11/2017
     * Remarks : APITest
     * ContactNo : 123456789
     * DocumentUrl :
     * dtCompoff : [{"AttendancePK":306067,"Balance":"1"}]
     */

    private int EmployeeFK;
    private int LeaveTypeFK;
    private int LeaveDayTypeFK;
    private String LeaveFromDate;
    private String LeaveToDate;
    private String Remarks;
    private String ContactNo;
    private String DocumentUrl;
    private List<DtCompoffBean> CompoffDay;

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

    public String getLeaveToDate() {
        return LeaveToDate;
    }

    public void setLeaveToDate(String LeaveToDate) {
        this.LeaveToDate = LeaveToDate;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String Remarks) {
        this.Remarks = Remarks;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String ContactNo) {
        this.ContactNo = ContactNo;
    }

    public String getDocumentUrl() {
        return DocumentUrl;
    }

    public void setDocumentUrl(String DocumentUrl) {
        this.DocumentUrl = DocumentUrl;
    }

    public List<DtCompoffBean> getDtCompoff() {
        return CompoffDay;
    }

    public void setDtCompoff(List<DtCompoffBean> CompoffDay) {
        this.CompoffDay = CompoffDay;
    }

    public static class DtCompoffBean {
        /**
         * AttendancePK : 306067
         * Balance : 1
         */

        private int AttendancePK;
        private String Balance;

        public int getAttendancePK() {
            return AttendancePK;
        }

        public void setAttendancePK(int AttendancePK) {
            this.AttendancePK = AttendancePK;
        }

        public String getBalance() {
            return Balance;
        }

        public void setBalance(String Balance) {
            this.Balance = Balance;
        }
    }
}
