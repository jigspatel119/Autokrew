package com.autokrew.models;

import java.util.List;

/**
 * Created by SONY on 03-08-2016.
 */
public class CancleLeaveModel {


    private List<TableBean> Table;

    public List<TableBean> getTable() {
        return Table;
    }

    public void setTable(List<TableBean> Table) {
        this.Table = Table;
    }

    public static class TableBean {
        /**
         * Result : 1
         * EmployeeEmail : null
         * ReportingManger : null
         * EmployeeSubject : null
         * EmployeeString : null
         * loginfk : 8816
         * ReportingManagerSubject : null
         * ReportingManagerString : null
         * ReportingPersonEmail : null
         */

        private int Result;
        private Object EmployeeEmail;
        private Object ReportingManger;
        private Object EmployeeSubject;
        private Object EmployeeString;
        private int loginfk;
        private Object ReportingManagerSubject;
        private Object ReportingManagerString;
        private Object ReportingPersonEmail;

        public int getResult() {
            return Result;
        }

        public void setResult(int Result) {
            this.Result = Result;
        }

        public Object getEmployeeEmail() {
            return EmployeeEmail;
        }

        public void setEmployeeEmail(Object EmployeeEmail) {
            this.EmployeeEmail = EmployeeEmail;
        }

        public Object getReportingManger() {
            return ReportingManger;
        }

        public void setReportingManger(Object ReportingManger) {
            this.ReportingManger = ReportingManger;
        }

        public Object getEmployeeSubject() {
            return EmployeeSubject;
        }

        public void setEmployeeSubject(Object EmployeeSubject) {
            this.EmployeeSubject = EmployeeSubject;
        }

        public Object getEmployeeString() {
            return EmployeeString;
        }

        public void setEmployeeString(Object EmployeeString) {
            this.EmployeeString = EmployeeString;
        }

        public int getLoginfk() {
            return loginfk;
        }

        public void setLoginfk(int loginfk) {
            this.loginfk = loginfk;
        }

        public Object getReportingManagerSubject() {
            return ReportingManagerSubject;
        }

        public void setReportingManagerSubject(Object ReportingManagerSubject) {
            this.ReportingManagerSubject = ReportingManagerSubject;
        }

        public Object getReportingManagerString() {
            return ReportingManagerString;
        }

        public void setReportingManagerString(Object ReportingManagerString) {
            this.ReportingManagerString = ReportingManagerString;
        }

        public Object getReportingPersonEmail() {
            return ReportingPersonEmail;
        }

        public void setReportingPersonEmail(Object ReportingPersonEmail) {
            this.ReportingPersonEmail = ReportingPersonEmail;
        }
    }
}
