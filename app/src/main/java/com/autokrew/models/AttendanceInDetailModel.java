package com.autokrew.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SONY on 03-08-2016.
 */
public class AttendanceInDetailModel {


    private List<TableBean> Table;
    private List<Table1Bean> Table1;

    public List<TableBean> getTable() {
        return Table;
    }

    public void setTable(List<TableBean> Table) {
        this.Table = Table;
    }

    public List<Table1Bean> getTable1() {
        return Table1;
    }

    public void setTable1(List<Table1Bean> Table1) {
        this.Table1 = Table1;
    }

    public static class TableBean {
        /**
         * Name : Honey  Modi
         * EmployeeCode : AN10000017
         * Company : Anaha Innovations Pvt. Ltd.
         * Location : Ahmedabad
         * SubLocation : Mondeal
         * Department : Pre Sales
         * SubDepartment : Pre Sales
         * ReprtingPerson : Nikita Pandey
         * PunchCardNo : 23
         * Timing : 12:00AM To 12:00AM
         */

        private String Name;
        private String EmployeeCode;
        private String Company;
        private String Location;
        private String SubLocation;
        private String Department;
        private String SubDepartment;
        private String ReprtingPerson;
        private String PunchCardNo;
        private String Timing;

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getEmployeeCode() {
            return EmployeeCode;
        }

        public void setEmployeeCode(String EmployeeCode) {
            this.EmployeeCode = EmployeeCode;
        }

        public String getCompany() {
            return Company;
        }

        public void setCompany(String Company) {
            this.Company = Company;
        }

        public String getLocation() {
            return Location;
        }

        public void setLocation(String Location) {
            this.Location = Location;
        }

        public String getSubLocation() {
            return SubLocation;
        }

        public void setSubLocation(String SubLocation) {
            this.SubLocation = SubLocation;
        }

        public String getDepartment() {
            return Department;
        }

        public void setDepartment(String Department) {
            this.Department = Department;
        }

        public String getSubDepartment() {
            return SubDepartment;
        }

        public void setSubDepartment(String SubDepartment) {
            this.SubDepartment = SubDepartment;
        }

        public String getReprtingPerson() {
            return ReprtingPerson;
        }

        public void setReprtingPerson(String ReprtingPerson) {
            this.ReprtingPerson = ReprtingPerson;
        }

        public String getPunchCardNo() {
            return PunchCardNo;
        }

        public void setPunchCardNo(String PunchCardNo) {
            this.PunchCardNo = PunchCardNo;
        }

        public String getTiming() {
            return Timing;
        }

        public void setTiming(String Timing) {
            this.Timing = Timing;
        }
    }

    public static class Table1Bean {
        /**
         * Date : Thu, 01 Feb 2018
         * First In : 10:16
         * Last Out : 19:17
         * Working Hours : 09:01
         * Status : P
         * Deviation : -
         * Employee Remarks : -
         * Reporting Person Status : -
         * Reporting Person Remarks : -
         * Shift : General
         */

        private String Date;
        @SerializedName("First In")
        private String _$FirstIn301; // FIXME check this code
        @SerializedName("Last Out")
        private String _$LastOut183; // FIXME check this code
        @SerializedName("Working Hours")
        private String _$WorkingHours1; // FIXME check this code
        private String Status;
        private String Deviation;
        @SerializedName("Employee Remarks")
        private String _$EmployeeRemarks121; // FIXME check this code
        @SerializedName("Reporting Person Status")
        private String _$ReportingPersonStatus233; // FIXME check this code
        @SerializedName("Reporting Person Remarks")
        private String _$ReportingPersonRemarks188; // FIXME check this code
        private String Shift;

        public String getDate() {
            return Date;
        }

        public void setDate(String Date) {
            this.Date = Date;
        }

        public String get_$FirstIn301() {
            return _$FirstIn301;
        }

        public void set_$FirstIn301(String _$FirstIn301) {
            this._$FirstIn301 = _$FirstIn301;
        }

        public String get_$LastOut183() {
            return _$LastOut183;
        }

        public void set_$LastOut183(String _$LastOut183) {
            this._$LastOut183 = _$LastOut183;
        }

        public String get_$WorkingHours1() {
            return _$WorkingHours1;
        }

        public void set_$WorkingHours1(String _$WorkingHours1) {
            this._$WorkingHours1 = _$WorkingHours1;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String Status) {
            this.Status = Status;
        }

        public String getDeviation() {
            return Deviation;
        }

        public void setDeviation(String Deviation) {
            this.Deviation = Deviation;
        }

        public String get_$EmployeeRemarks121() {
            return _$EmployeeRemarks121;
        }

        public void set_$EmployeeRemarks121(String _$EmployeeRemarks121) {
            this._$EmployeeRemarks121 = _$EmployeeRemarks121;
        }

        public String get_$ReportingPersonStatus233() {
            return _$ReportingPersonStatus233;
        }

        public void set_$ReportingPersonStatus233(String _$ReportingPersonStatus233) {
            this._$ReportingPersonStatus233 = _$ReportingPersonStatus233;
        }

        public String get_$ReportingPersonRemarks188() {
            return _$ReportingPersonRemarks188;
        }

        public void set_$ReportingPersonRemarks188(String _$ReportingPersonRemarks188) {
            this._$ReportingPersonRemarks188 = _$ReportingPersonRemarks188;
        }

        public String getShift() {
            return Shift;
        }

        public void setShift(String Shift) {
            this.Shift = Shift;
        }
    }
}
