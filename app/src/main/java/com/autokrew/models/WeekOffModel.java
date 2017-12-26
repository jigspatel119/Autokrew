package com.autokrew.models;

import java.util.List;

/**
 * Created by SONY on 03-08-2016.
 */
public class WeekOffModel {


    private List<Table1Bean> Table1;

    public List<Table1Bean> getTable1() {
        return Table1;
    }

    public void setTable1(List<Table1Bean> Table1) {
        this.Table1 = Table1;
    }

    public static class Table1Bean {
        /**
         * WeekOff : 0
         */

        private String WeekOff;

        public String getWeekOff() {
            return WeekOff;
        }

        public void setWeekOff(String WeekOff) {
            this.WeekOff = WeekOff;
        }
    }
}
