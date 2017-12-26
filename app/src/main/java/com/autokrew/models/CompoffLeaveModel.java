package com.autokrew.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SONY on 03-08-2016.
 */
public class CompoffLeaveModel {

    private List<TableBean> Table;

    public List<TableBean> getTable() {
        return Table;
    }

    public void setTable(List<TableBean> Table) {
        this.Table = Table;
    }

    public static class TableBean {
        /**
         * AttendancePK : 306067
         * Date : 08/10/2017
         * Day : Sunday
         * In Time : 09:45
         * Out Time : 18:15
         * Working Hours : 08:30
         * Status : WO
         * Balance : 1.0
         */

        private int AttendancePK;
        private String Date;
        private String Day;
        @SerializedName("In Time")
        private String _$InTime96; // FIXME check this code
        @SerializedName("Out Time")
        private String _$OutTime179; // FIXME check this code
        @SerializedName("Working Hours")
        private String _$WorkingHours298; // FIXME check this code
        private String Status;
        private double Balance;

        private boolean isSelected;

        public boolean isSelected() {
            return isSelected;
        }
        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public int getAttendancePK() {
            return AttendancePK;
        }

        public void setAttendancePK(int AttendancePK) {
            this.AttendancePK = AttendancePK;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String Date) {
            this.Date = Date;
        }

        public String getDay() {
            return Day;
        }

        public void setDay(String Day) {
            this.Day = Day;
        }

        public String get_$InTime96() {
            return _$InTime96;
        }

        public void set_$InTime96(String _$InTime96) {
            this._$InTime96 = _$InTime96;
        }

        public String get_$OutTime179() {
            return _$OutTime179;
        }

        public void set_$OutTime179(String _$OutTime179) {
            this._$OutTime179 = _$OutTime179;
        }

        public String get_$WorkingHours298() {
            return _$WorkingHours298;
        }

        public void set_$WorkingHours298(String _$WorkingHours298) {
            this._$WorkingHours298 = _$WorkingHours298;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String Status) {
            this.Status = Status;
        }

        public double getBalance() {
            return Balance;
        }

        public void setBalance(double Balance) {
            this.Balance = Balance;
        }
    }
}
