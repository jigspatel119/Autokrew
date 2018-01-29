package com.autokrew.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SONY on 03-08-2016.
 */
public class AttendanceStatusModel {

    private List<TableBean> Table;

    public List<TableBean> getTable() {
        return Table;
    }

    public void setTable(List<TableBean> Table) {
        this.Table = Table;
    }

    public static class TableBean {
        @SerializedName("Employee Code")
        private String _$EmployeeCode299; // FIXME check this code
        private String Name;
        private int P;
        private int P2;
        private int A;
        private int L;
        private int H;
        private int WO;
        private int PL;
        private int PE;

        public String get_$EmployeeCode299() {
            return _$EmployeeCode299;
        }

        public void set_$EmployeeCode299(String _$EmployeeCode299) {
            this._$EmployeeCode299 = _$EmployeeCode299;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public int getP() {
            return P;
        }

        public void setP(int P) {
            this.P = P;
        }

        public int getP2() {
            return P2;
        }

        public void setP2(int P2) {
            this.P2 = P2;
        }

        public int getA() {
            return A;
        }

        public void setA(int A) {
            this.A = A;
        }

        public int getL() {
            return L;
        }

        public void setL(int L) {
            this.L = L;
        }

        public int getH() {
            return H;
        }

        public void setH(int H) {
            this.H = H;
        }

        public int getWO() {
            return WO;
        }

        public void setWO(int WO) {
            this.WO = WO;
        }

        public int getPL() {
            return PL;
        }

        public void setPL(int PL) {
            this.PL = PL;
        }

        public int getPE() {
            return PE;
        }

        public void setPE(int PE) {
            this.PE = PE;
        }
    }
}
