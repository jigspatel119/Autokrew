package com.autokrew.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SONY on 03-08-2016.
 */
public class LeaveDetailModel {


    private List<TableBean> Table;

    public List<TableBean> getTable() {
        return Table;
    }

    public void setTable(List<TableBean> Table) {
        this.Table = Table;
    }

    public static class TableBean {
        /**
         * PenMonth : 7
         * PenYear : 2017
         * Pending For Month : Jul - 2017
         * Pending Leave : 2
         */

        private int PenMonth;
        private int PenYear;
        @SerializedName("Pending For Month")
        private String _$PendingForMonth8; // FIXME check this code
        @SerializedName("Pending Leave")
        private int _$PendingLeave251; // FIXME check this code

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

        public String get_$PendingForMonth8() {
            return _$PendingForMonth8;
        }

        public void set_$PendingForMonth8(String _$PendingForMonth8) {
            this._$PendingForMonth8 = _$PendingForMonth8;
        }

        public int get_$PendingLeave251() {
            return _$PendingLeave251;
        }

        public void set_$PendingLeave251(int _$PendingLeave251) {
            this._$PendingLeave251 = _$PendingLeave251;
        }
    }
}