package com.autokrew.models;

import java.util.List;

/**
 * Created by SONY on 03-08-2016.
 */
public class LocationModel {

    private List<TableBean> Table;

    public List<TableBean> getTable() {
        return Table;
    }

    public void setTable(List<TableBean> Table) {
        this.Table = Table;
    }

    public static class TableBean {
        /**
         * LocationPK : 14
         * Location : Ahmedabad
         */

        private String LocationPK;
        private String Location;



        public String getLocationPK() {
            return LocationPK;
        }

        public void setLocationPK(String LocationPK) {
            this.LocationPK = LocationPK;
        }

        public String getLocation() {
            return Location;
        }

        public void setLocation(String Location) {
            this.Location = Location;
        }
    }
}
