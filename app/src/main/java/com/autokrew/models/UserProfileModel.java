package com.autokrew.models;

import java.util.List;

/**
 * Created by SONY on 03-08-2016.
 */
public class UserProfileModel {


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
         * EmpName : Devki Nanda
         * JoiningDate : 01 May 2010
         * Designation : BOD
         * ImageUrl : 8816/56ba5c7f-99f0-4816-bf19-20e65bfc2225_9f408dd9-8521-4fc6-aad2-72ef1e3c32a9_profile_pic_1512978204327.jpeg
         * EmployeeCode : CA140000126
         */

        private String EmpName;
        private String JoiningDate;
        private String Designation;
        private String ImageUrl;
        private String EmployeeCode;

        public String getEmpName() {
            return EmpName;
        }

        public void setEmpName(String EmpName) {
            this.EmpName = EmpName;
        }

        public String getJoiningDate() {
            return JoiningDate;
        }

        public void setJoiningDate(String JoiningDate) {
            this.JoiningDate = JoiningDate;
        }

        public String getDesignation() {
            return Designation;
        }

        public void setDesignation(String Designation) {
            this.Designation = Designation;
        }

        public String getImageUrl() {
            return ImageUrl;
        }

        public void setImageUrl(String ImageUrl) {
            this.ImageUrl = ImageUrl;
        }

        public String getEmployeeCode() {
            return EmployeeCode;
        }

        public void setEmployeeCode(String EmployeeCode) {
            this.EmployeeCode = EmployeeCode;
        }
    }

    public static class Table1Bean {
        /**
         * IsOutsideAttendaceAllow : 0
         */

        private int IsOutsideAttendaceAllow;

        public int getIsOutsideAttendaceAllow() {
            return IsOutsideAttendaceAllow;
        }

        public void setIsOutsideAttendaceAllow(int IsOutsideAttendaceAllow) {
            this.IsOutsideAttendaceAllow = IsOutsideAttendaceAllow;
        }
    }
}
