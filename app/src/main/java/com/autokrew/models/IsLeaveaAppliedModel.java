package com.autokrew.models;

import java.util.List;

/**
 * Created by SONY on 03-08-2016.
 */
public class IsLeaveaAppliedModel {


    private List<TableBean> Table;

    public List<TableBean> getTable() {
        return Table;
    }

    public void setTable(List<TableBean> Table) {
        this.Table = Table;
    }

    public static class TableBean {
        /**
         * returnValue : false
         */

        private boolean returnValue;

        public boolean isReturnValue() {
            return returnValue;
        }

        public void setReturnValue(boolean returnValue) {
            this.returnValue = returnValue;
        }
    }
}
