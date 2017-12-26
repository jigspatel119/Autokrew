package com.autokrew.models;

import java.util.List;

/**
 * Created by SONY on 03-08-2016.
 */
public class UserProfileModel {


    private List<TableBean> Table;

    public List<TableBean> getTable() {
        return Table;
    }

    public void setTable(List<TableBean> Table) {
        this.Table = Table;
    }

    public static class TableBean {
        /**
         * EmpName : Devki Nanda
         * JoiningDate : 01 May 2010
         * Designation : BOD
         * ImageUrl : 8816/a9a46b04-76fd-4434-9f05-2cf78fff06b0_images.png
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
}
