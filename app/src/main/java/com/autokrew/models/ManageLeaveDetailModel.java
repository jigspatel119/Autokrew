package com.autokrew.models;

import java.util.List;

/**
 * Created by SONY on 03-08-2016.
 */
public class ManageLeaveDetailModel {


    private List<TableBean> Table;
    private List<?> Table1;

    public List<TableBean> getTable() {
        return Table;
    }

    public void setTable(List<TableBean> Table) {
        this.Table = Table;
    }

    public List<?> getTable1() {
        return Table1;
    }

    public void setTable1(List<?> Table1) {
        this.Table1 = Table1;
    }

    public static class TableBean {
        /**
         * Name : Devki  Nanda
         * ReportingPerson : Devki  Nanda
         * LeaveTypeFK : 6
         * LeaveType : Leaves
         * LeaveDayTypeFK : 2
         * LeaveFromDate : Mon, 20 Nov 2017
         * LeaveToDate : Mon, 20 Nov 2017
         * TotalDays : 1.0
         * Remarks : test
         * contactNo : 5824584156
         * IsCompensatory : 0
         */

        private String Name;
        private String ReportingPerson;
        private int LeaveTypeFK;
        private String LeaveType;
        private int LeaveDayTypeFK;
        private String LeaveFromDate;
        private String LeaveToDate;
        private double TotalDays;
        private String Remarks;
        private String contactNo;
        private int IsCompensatory;

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getReportingPerson() {
            return ReportingPerson;
        }

        public void setReportingPerson(String ReportingPerson) {
            this.ReportingPerson = ReportingPerson;
        }

        public int getLeaveTypeFK() {
            return LeaveTypeFK;
        }

        public void setLeaveTypeFK(int LeaveTypeFK) {
            this.LeaveTypeFK = LeaveTypeFK;
        }

        public String getLeaveType() {
            return LeaveType;
        }

        public void setLeaveType(String LeaveType) {
            this.LeaveType = LeaveType;
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

        public double getTotalDays() {
            return TotalDays;
        }

        public void setTotalDays(double TotalDays) {
            this.TotalDays = TotalDays;
        }

        public String getRemarks() {
            return Remarks;
        }

        public void setRemarks(String Remarks) {
            this.Remarks = Remarks;
        }

        public String getContactNo() {
            return contactNo;
        }

        public void setContactNo(String contactNo) {
            this.contactNo = contactNo;
        }

        public int getIsCompensatory() {
            return IsCompensatory;
        }

        public void setIsCompensatory(int IsCompensatory) {
            this.IsCompensatory = IsCompensatory;
        }
    }
}
