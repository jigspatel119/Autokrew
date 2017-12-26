package com.autokrew.models;

import java.util.List;

/**
 * Created by SONY on 03-08-2016.
 */
public class TeamMemberModel {

    private List<TableBean> Table;

    public List<TableBean> getTable() {
        return Table;
    }

    public void setTable(List<TableBean> Table) {
        this.Table = Table;
    }

    public static class TableBean {
        /**
         * EmployeePK : 8898
         * Name : Ajo  Jose (CA150000010)
         */

        private String EmployeePK;
        private String Name;

        public String getEmployeePK() {
            return EmployeePK;
        }

        public void setEmployeePK(String EmployeePK) {
            this.EmployeePK = EmployeePK;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }
    }
}
