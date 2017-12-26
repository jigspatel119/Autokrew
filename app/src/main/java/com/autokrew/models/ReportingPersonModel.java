package com.autokrew.models;

import java.util.List;

/**
 * Created by SONY on 03-08-2016.
 */
public class ReportingPersonModel {


    private List<TableBean> Table;

    public List<TableBean> getTable() {
        return Table;
    }

    public void setTable(List<TableBean> Table) {
        this.Table = Table;
    }

    public static class TableBean {
        /**
         * Name : Devki Nanda
         * EmployeeCode : CA140000126
         * Company : Cargo Motors Pvt. Ltd.
         * Location : Ahmedabad
         * SubLocation : Cargo House
         * Department : Sales
         * SubDepartment : Sales & Service
         * ReportingPerson : Devki Nanda
         * PunchCardNo : 111
         */

        private String Name;
        private String EmployeeCode;
        private String Company;
        private String Location;
        private String SubLocation;
        private String Department;
        private String SubDepartment;
        private String ReportingPerson;
        private String PunchCardNo;

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

        public String getReportingPerson() {
            return ReportingPerson;
        }

        public void setReportingPerson(String ReportingPerson) {
            this.ReportingPerson = ReportingPerson;
        }

        public String getPunchCardNo() {
            return PunchCardNo;
        }

        public void setPunchCardNo(String PunchCardNo) {
            this.PunchCardNo = PunchCardNo;
        }
    }
}
