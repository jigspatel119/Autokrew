package com.autokrew.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SONY on 03-08-2016.
 */
public class AttendanceDeviationTGModel {
    @SerializedName("Table")
    @Expose
    private List<Table> table = null;

    public List<Table> getTable() {
        return table;
    }

    public void setTable(List<Table> table) {
        this.table = table;
    }


    //------Table class------

    public class Table {


        @SerializedName("AttendancePK")
        @Expose
        private Integer attendancePK;
        @SerializedName("Name")
        @Expose
        private String name;
        @SerializedName("Date")
        @Expose
        private String date;
        @SerializedName("First In")
        @Expose
        private String firstIn;
        @SerializedName("Last Out")
        @Expose
        private String lastOut;
        @SerializedName("Working Hours")
        @Expose
        private String workingHours;
        @SerializedName("Status")
        @Expose
        private String status;
        @SerializedName("Deviation")
        @Expose
        private String deviation;
        @SerializedName("Employee Remarks")
        @Expose
        private String employeeRemarks;
        @SerializedName("Approval Status")
        @Expose
        private String approvalStatus;
        @SerializedName("ReportingPersonStatusID")
        @Expose
        private Integer reportingPersonStatusID;
        @SerializedName("ReportingPersonRemarks")
        @Expose
        private String reportingPersonRemarks;
        @SerializedName("LockAttendance")
        @Expose
        private String lockAttendance;

        public Integer getAttendancePK() {
            return attendancePK;
        }

        public void setAttendancePK(Integer attendancePK) {
            this.attendancePK = attendancePK;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getFirstIn() {
            return firstIn;
        }

        public void setFirstIn(String firstIn) {
            this.firstIn = firstIn;
        }

        public String getLastOut() {
            return lastOut;
        }

        public void setLastOut(String lastOut) {
            this.lastOut = lastOut;
        }

        public String getWorkingHours() {
            return workingHours;
        }

        public void setWorkingHours(String workingHours) {
            this.workingHours = workingHours;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDeviation() {
            return deviation;
        }

        public void setDeviation(String deviation) {
            this.deviation = deviation;
        }

        public String getEmployeeRemarks() {
            return employeeRemarks;
        }

        public void setEmployeeRemarks(String employeeRemarks) {
            this.employeeRemarks = employeeRemarks;
        }

        public String getApprovalStatus() {
            return approvalStatus;
        }

        public void setApprovalStatus(String approvalStatus) {
            this.approvalStatus = approvalStatus;
        }

        public Integer getReportingPersonStatusID() {
            return reportingPersonStatusID;
        }

        public void setReportingPersonStatusID(Integer reportingPersonStatusID) {
            this.reportingPersonStatusID = reportingPersonStatusID;
        }

        public String getReportingPersonRemarks() {
            return reportingPersonRemarks;
        }

        public void setReportingPersonRemarks(String reportingPersonRemarks) {
            this.reportingPersonRemarks = reportingPersonRemarks;
        }

        public String getLockAttendance() {
            return lockAttendance;
        }

        public void setLockAttendance(String lockAttendance) {
            this.lockAttendance = lockAttendance;
        }
    }
}
