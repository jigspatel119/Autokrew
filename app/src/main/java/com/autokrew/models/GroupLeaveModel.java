package com.autokrew.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SONY on 03-08-2016.
 */
public class GroupLeaveModel {


    private List<TableBean> Table;

    public List<TableBean> getTable() {
        return Table;
    }

    public void setTable(List<TableBean> Table) {
        this.Table = Table;
    }

    public static class TableBean {
        @SerializedName("Manage Status")
        private String _$ManageStatus230; // FIXME check this code
        @SerializedName("Revise Leave")
        private String _$ReviseLeave296; // FIXME check this code
        @SerializedName("View Document")
        private String _$ViewDocument278; // FIXME check this code
        @SerializedName("Employee Code")
        private String _$EmployeeCode276; // FIXME check this code
        private String Name;
        private String Department;
        private String Designation;
        @SerializedName("Leave Type")
        private String _$LeaveType236; // FIXME check this code
        @SerializedName("Leave Day Type")
        private String _$LeaveDayType84; // FIXME check this code
        @SerializedName("Leave From")
        private String _$LeaveFrom158; // FIXME check this code
        @SerializedName("Leave To")
        private String _$LeaveTo322; // FIXME check this code
        private double Duration;
        private int LeaveDetailPK;
        private String DocumentUrl;
        @SerializedName("Mobile Manage Status")
        private String _$MobileManageStatus30; // FIXME check this code
        @SerializedName("Mobile Revise Leave")
        private String _$MobileReviseLeave160; // FIXME check this code

        public String get_$ManageStatus230() {
            return _$ManageStatus230;
        }

        public void set_$ManageStatus230(String _$ManageStatus230) {
            this._$ManageStatus230 = _$ManageStatus230;
        }

        public String get_$ReviseLeave296() {
            return _$ReviseLeave296;
        }

        public void set_$ReviseLeave296(String _$ReviseLeave296) {
            this._$ReviseLeave296 = _$ReviseLeave296;
        }

        public String get_$ViewDocument278() {
            return _$ViewDocument278;
        }

        public void set_$ViewDocument278(String _$ViewDocument278) {
            this._$ViewDocument278 = _$ViewDocument278;
        }

        public String get_$EmployeeCode276() {
            return _$EmployeeCode276;
        }

        public void set_$EmployeeCode276(String _$EmployeeCode276) {
            this._$EmployeeCode276 = _$EmployeeCode276;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getDepartment() {
            return Department;
        }

        public void setDepartment(String Department) {
            this.Department = Department;
        }

        public String getDesignation() {
            return Designation;
        }

        public void setDesignation(String Designation) {
            this.Designation = Designation;
        }

        public String get_$LeaveType236() {
            return _$LeaveType236;
        }

        public void set_$LeaveType236(String _$LeaveType236) {
            this._$LeaveType236 = _$LeaveType236;
        }

        public String get_$LeaveDayType84() {
            return _$LeaveDayType84;
        }

        public void set_$LeaveDayType84(String _$LeaveDayType84) {
            this._$LeaveDayType84 = _$LeaveDayType84;
        }

        public String get_$LeaveFrom158() {
            return _$LeaveFrom158;
        }

        public void set_$LeaveFrom158(String _$LeaveFrom158) {
            this._$LeaveFrom158 = _$LeaveFrom158;
        }

        public String get_$LeaveTo322() {
            return _$LeaveTo322;
        }

        public void set_$LeaveTo322(String _$LeaveTo322) {
            this._$LeaveTo322 = _$LeaveTo322;
        }

        public double getDuration() {
            return Duration;
        }

        public void setDuration(double Duration) {
            this.Duration = Duration;
        }

        public int getLeaveDetailPK() {
            return LeaveDetailPK;
        }

        public void setLeaveDetailPK(int LeaveDetailPK) {
            this.LeaveDetailPK = LeaveDetailPK;
        }

        public String getDocumentUrl() {
            return DocumentUrl;
        }

        public void setDocumentUrl(String DocumentUrl) {
            this.DocumentUrl = DocumentUrl;
        }

        public String get_$MobileManageStatus30() {
            return _$MobileManageStatus30;
        }

        public void set_$MobileManageStatus30(String _$MobileManageStatus30) {
            this._$MobileManageStatus30 = _$MobileManageStatus30;
        }

        public String get_$MobileReviseLeave160() {
            return _$MobileReviseLeave160;
        }

        public void set_$MobileReviseLeave160(String _$MobileReviseLeave160) {
            this._$MobileReviseLeave160 = _$MobileReviseLeave160;
        }
    }
}
