package com.autokrew.models;

import java.util.List;

/**
 * Created by SONY on 03-08-2016.
 */
public class SubLocationModel {


    private List<TableBean> Table;

    public List<TableBean> getTable() {
        return Table;
    }

    public void setTable(List<TableBean> Table) {
        this.Table = Table;
    }

    public static class TableBean {
        /**
         * SubLocationPK : 69
         * SubLocation : Khanpar
         */

        private String SubLocationPK;
        private String SubLocation;

        public String getSubLocationPK() {
            return SubLocationPK;
        }

        public void setSubLocationPK(String SubLocationPK) {
            this.SubLocationPK = SubLocationPK;
        }

        public String getSubLocation() {
            return SubLocation;
        }

        public void setSubLocation(String SubLocation) {
            this.SubLocation = SubLocation;
        }
    }
}
