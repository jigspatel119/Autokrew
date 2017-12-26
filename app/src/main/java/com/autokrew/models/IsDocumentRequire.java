package com.autokrew.models;

import java.util.List;

/**
 * Created by SONY on 03-08-2016.
 */
public class IsDocumentRequire {


    private List<TableBean> Table;

    public List<TableBean> getTable() {
        return Table;
    }

    public void setTable(List<TableBean> Table) {
        this.Table = Table;
    }

    public static class TableBean {
        /**
         * Result : 0
         */

        private int Result;

        public int getResult() {
            return Result;
        }

        public void setResult(int Result) {
            this.Result = Result;
        }
    }
}
