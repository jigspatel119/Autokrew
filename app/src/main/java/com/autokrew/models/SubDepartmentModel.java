package com.autokrew.models;

import java.util.List;

/**
 * Created by SONY on 03-08-2016.
 */
public class SubDepartmentModel {

    private List<TableBean> Table;

    public List<TableBean> getTable() {
        return Table;
    }

    public void setTable(List<TableBean> Table) {
        this.Table = Table;
    }

    public static class TableBean {
        /**
         * SubDepartmentPK : 124
         * SubDepartment : Back Office
         */

        private String SubDepartmentPK;
        private String SubDepartment;

        public String getSubDepartmentPK() {
            return SubDepartmentPK;
        }

        public void setSubDepartmentPK(String SubDepartmentPK) {
            this.SubDepartmentPK = SubDepartmentPK;
        }

        public String getSubDepartment() {
            return SubDepartment;
        }

        public void setSubDepartment(String SubDepartment) {
            this.SubDepartment = SubDepartment;
        }
    }
}
