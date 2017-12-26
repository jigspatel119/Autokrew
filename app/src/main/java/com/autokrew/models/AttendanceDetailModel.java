package com.autokrew.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SONY on 03-08-2016.
 */
public class AttendanceDetailModel {
    private List<TableBean> Table;

    public List<TableBean> getTable() {
        return Table;
    }

    public void setTable(List<TableBean> Table) {
        this.Table = Table;
    }

    public static class TableBean {
        /**
         * PenMonth : 8
         * PenYear : 2017
         * Pending For Month : Aug - 2017
         * Pending Attendance : 5
         */

        private int PenMonth;
        private int PenYear;
        @SerializedName("Pending For Month")
        private String _$PendingForMonth150; // FIXME check this code
        @SerializedName("Pending Attendance")
        private int _$PendingAttendance320; // FIXME check this code

        public int getPenMonth() {
            return PenMonth;
        }

        public void setPenMonth(int PenMonth) {
            this.PenMonth = PenMonth;
        }

        public int getPenYear() {
            return PenYear;
        }

        public void setPenYear(int PenYear) {
            this.PenYear = PenYear;
        }

        public String get_$PendingForMonth150() {
            return _$PendingForMonth150;
        }

        public void set_$PendingForMonth150(String _$PendingForMonth150) {
            this._$PendingForMonth150 = _$PendingForMonth150;
        }

        public int get_$PendingAttendance320() {
            return _$PendingAttendance320;
        }

        public void set_$PendingAttendance320(int _$PendingAttendance320) {
            this._$PendingAttendance320 = _$PendingAttendance320;
        }
    }  // Getter and Setter model for recycler view items

}