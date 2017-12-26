package com.autokrew.models;

import java.util.List;

/**
 * Created by SONY on 03-08-2016.
 */
public class ApplyLeaveModel {


    private List<TableBean> Table;

    public List<TableBean> getTable() {
        return Table;
    }

    public void setTable(List<TableBean> Table) {
        this.Table = Table;
    }

    public static class TableBean {
        String EmployeeEmail ;
        String ReportingManger;
        String EmployeeSubject;
        String EmployeeString;
        String ReportingPersonEmail;
        int Result;

        public String getEmployeeEmail() {
            return EmployeeEmail;
        }

        public void setEmployeeEmail(String employeeEmail) {
            EmployeeEmail = employeeEmail;
        }

        public String getReportingManger() {
            return ReportingManger;
        }

        public void setReportingManger(String reportingManger) {
            ReportingManger = reportingManger;
        }

        public String getEmployeeSubject() {
            return EmployeeSubject;
        }

        public void setEmployeeSubject(String employeeSubject) {
            EmployeeSubject = employeeSubject;
        }

        public String getEmployeeString() {
            return EmployeeString;
        }

        public void setEmployeeString(String employeeString) {
            EmployeeString = employeeString;
        }

        public String getReportingPersonEmail() {
            return ReportingPersonEmail;
        }

        public void setReportingPersonEmail(String reportingPersonEmail) {
            ReportingPersonEmail = reportingPersonEmail;
        }

        public int getResult() {
            return Result;
        }

        public void setResult(int result) {
            Result = result;
        }
    }
}
