package com.autokrew.models;

import java.util.List;

/**
 * Created by SONY on 03-08-2016.
 */
public class DepartmentModel {


    private List<TableBean> Table;

    public List<TableBean> getTable() {
        return Table;
    }

    public void setTable(List<TableBean> Table) {
        this.Table = Table;
    }

    public static class TableBean {
        /**
         * DepartmentPK : 41
         * Department : IT
         */

        private String DepartmentPK;
        private String Department;

        public String getDepartmentPK() {
            return DepartmentPK;
        }

        public void setDepartmentPK(String DepartmentPK) {
            this.DepartmentPK = DepartmentPK;
        }

        public String getDepartment() {
            return Department;
        }

        public void setDepartment(String Department) {
            this.Department = Department;
        }
    }
}
