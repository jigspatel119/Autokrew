package com.autokrew.models;

import java.util.List;

/**
 * Created by SONY on 03-08-2016.
 */
public class SandwichFormDateModel {


    private List<TableBean> Table;

    public List<TableBean> getTable() {
        return Table;
    }

    public void setTable(List<TableBean> Table) {
        this.Table = Table;
    }

    public static class TableBean {
        /**
         * FromDate : 29/11/2017
         * Result : 1
         */

        private String FromDate;
        private int Result;

        public String getFromDate() {
            return FromDate;
        }

        public void setFromDate(String FromDate) {
            this.FromDate = FromDate;
        }

        public int getResult() {
            return Result;
        }

        public void setResult(int Result) {
            this.Result = Result;
        }
    }
}
