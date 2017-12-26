package com.autokrew.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SONY on 03-08-2016.
 */
public class LeaveCardModel {


    private List<TableBean> Table;
    private List<Table1Bean> Table1;
    private List<Table2Bean> Table2;

    public List<TableBean> getTable() {
        return Table;
    }

    public void setTable(List<TableBean> Table) {
        this.Table = Table;
    }

    public List<Table1Bean> getTable1() {
        return Table1;
    }

    public void setTable1(List<Table1Bean> Table1) {
        this.Table1 = Table1;
    }

    public List<Table2Bean> getTable2() {
        return Table2;
    }

    public void setTable2(List<Table2Bean> Table2) {
        this.Table2 = Table2;
    }

    public static class TableBean {
        @SerializedName("Leave Type")
        private String _$LeaveType123; // FIXME check this code
        private float Eligible;
        private float Availed;
        private float Balance;
        private int iscompoff;

        public int getIscompoff() {
            return iscompoff;
        }

        public void setIscompoff(int iscompoff) {
            this.iscompoff = iscompoff;
        }

        @SerializedName("Apply Leave")
        private String _$ApplyLeave296; // FIXME check this code

        public String get_$LeaveType123() {
            return _$LeaveType123;
        }

        public void set_$LeaveType123(String _$LeaveType123) {
            this._$LeaveType123 = _$LeaveType123;
        }

        public float getEligible() {
            return Eligible;
        }

        public void setEligible(float Eligible) {
            this.Eligible = Eligible;
        }

        public float getAvailed() {
            return Availed;
        }

        public void setAvailed(float Availed) {
            this.Availed = Availed;
        }

        public float getBalance() {
            return Balance;
        }

        public void setBalance(float Balance) {
            this.Balance = Balance;
        }

        public String get_$ApplyLeave296() {
            return _$ApplyLeave296;
        }

        public void set_$ApplyLeave296(String _$ApplyLeave296) {
            this._$ApplyLeave296 = _$ApplyLeave296;
        }
    }

    public static class Table1Bean {
        //LeaveDetailPK
        @SerializedName("LeaveDetailPK")
        private int leaveDetailPK;

        public int getLeaveDetailPK() {
            return leaveDetailPK;
        }

        public void setLeaveDetailPK(int leaveDetailPK) {
            this.leaveDetailPK = leaveDetailPK;
        }

        @SerializedName("Leave Type")
        private String _$LeaveType97; // FIXME check this code
        @SerializedName("Leave Day Type")
        private String _$LeaveDayType232; // FIXME check this code
        @SerializedName("Leave From")
        private String _$LeaveFrom161; // FIXME check this code
        @SerializedName("Leave To")
        private String _$LeaveTo87; // FIXME check this code
        private float Duration;
        @SerializedName("Leave Status")
        private String _$LeaveStatus209; // FIXME check this code
        private String Cancel;

        public String get_$LeaveType97() {
            return _$LeaveType97;
        }

        public void set_$LeaveType97(String _$LeaveType97) {
            this._$LeaveType97 = _$LeaveType97;
        }

        public String get_$LeaveDayType232() {
            return _$LeaveDayType232;
        }

        public void set_$LeaveDayType232(String _$LeaveDayType232) {
            this._$LeaveDayType232 = _$LeaveDayType232;
        }

        public String get_$LeaveFrom161() {
            return _$LeaveFrom161;
        }

        public void set_$LeaveFrom161(String _$LeaveFrom161) {
            this._$LeaveFrom161 = _$LeaveFrom161;
        }

        public String get_$LeaveTo87() {
            return _$LeaveTo87;
        }

        public void set_$LeaveTo87(String _$LeaveTo87) {
            this._$LeaveTo87 = _$LeaveTo87;
        }

        public float getDuration() {
            return Duration;
        }

        public void setDuration(float Duration) {
            this.Duration = Duration;
        }

        public String get_$LeaveStatus209() {
            return _$LeaveStatus209;
        }

        public void set_$LeaveStatus209(String _$LeaveStatus209) {
            this._$LeaveStatus209 = _$LeaveStatus209;
        }

        public String getCancel() {
            return Cancel;
        }

        public void setCancel(String Cancel) {
            this.Cancel = Cancel;
        }
    }

    public static class Table2Bean {
        /**
         * Month-Year : Jan-2017
         *  1 : -
         *  2 : -
         *  3 : -
         *  4 : -
         *  5 : -
         *  6 : -
         *  7 : -
         *  8 : -
         *  9 : -
         *  10 : -
         *  11 : -
         *  12 : -
         *  13 : -
         *  14 : -
         *  15 : -
         *  16 : -
         *  17 : -
         *  18 : -
         *  19 : -
         *  20 : -
         *  21 : -
         *  22 : -
         *  23 : -
         *  24 : -
         *  25 : -
         *  26 : -
         *  27 : -
         *  28 : -
         *  29 : -
         *  30 : -
         *  31 : -
         */

        @SerializedName("Month-Year")
        private String MonthYear;
        @SerializedName(" 1")
        private String _$1312; // FIXME check this code
        @SerializedName(" 2")
        private String _$2291; // FIXME check this code
        @SerializedName(" 3")
        private String _$3236; // FIXME check this code
        @SerializedName(" 4")
        private String _$4298; // FIXME check this code
        @SerializedName(" 5")
        private String _$579; // FIXME check this code
        @SerializedName(" 6")
        private String _$6213; // FIXME check this code
        @SerializedName(" 7")
        private String _$73; // FIXME check this code
        @SerializedName(" 8")
        private String _$82; // FIXME check this code
        @SerializedName(" 9")
        private String _$9155; // FIXME check this code
        @SerializedName(" 10")
        private String _$10243; // FIXME check this code
        @SerializedName(" 11")
        private String _$11319; // FIXME check this code
        @SerializedName(" 12")
        private String _$12298; // FIXME check this code
        @SerializedName(" 13")
        private String _$13107; // FIXME check this code
        @SerializedName(" 14")
        private String _$1498; // FIXME check this code
        @SerializedName(" 15")
        private String _$1519; // FIXME check this code
        @SerializedName(" 16")
        private String _$16126; // FIXME check this code
        @SerializedName(" 17")
        private String _$17113; // FIXME check this code
        @SerializedName(" 18")
        private String _$18218; // FIXME check this code
        @SerializedName(" 19")
        private String _$19103; // FIXME check this code
        @SerializedName(" 20")
        private String _$20206; // FIXME check this code
        @SerializedName(" 21")
        private String _$21318; // FIXME check this code
        @SerializedName(" 22")
        private String _$2226; // FIXME check this code
        @SerializedName(" 23")
        private String _$23201; // FIXME check this code
        @SerializedName(" 24")
        private String _$24266; // FIXME check this code
        @SerializedName(" 25")
        private String _$25314; // FIXME check this code
        @SerializedName(" 26")
        private String _$26332; // FIXME check this code
        @SerializedName(" 27")
        private String _$2727; // FIXME check this code
        @SerializedName(" 28")
        private String _$28104; // FIXME check this code
        @SerializedName(" 29")
        private String _$29241; // FIXME check this code
        @SerializedName(" 30")
        private String _$30165; // FIXME check this code
        @SerializedName(" 31")
        private String _$31188; // FIXME check this code

        public String getMonthYear() {
            return MonthYear;
        }

        public void setMonthYear(String MonthYear) {
            this.MonthYear = MonthYear;
        }

        public String get_$1312() {
            return _$1312;
        }

        public void set_$1312(String _$1312) {
            this._$1312 = _$1312;
        }

        public String get_$2291() {
            return _$2291;
        }

        public void set_$2291(String _$2291) {
            this._$2291 = _$2291;
        }

        public String get_$3236() {
            return _$3236;
        }

        public void set_$3236(String _$3236) {
            this._$3236 = _$3236;
        }

        public String get_$4298() {
            return _$4298;
        }

        public void set_$4298(String _$4298) {
            this._$4298 = _$4298;
        }

        public String get_$579() {
            return _$579;
        }

        public void set_$579(String _$579) {
            this._$579 = _$579;
        }

        public String get_$6213() {
            return _$6213;
        }

        public void set_$6213(String _$6213) {
            this._$6213 = _$6213;
        }

        public String get_$73() {
            return _$73;
        }

        public void set_$73(String _$73) {
            this._$73 = _$73;
        }

        public String get_$82() {
            return _$82;
        }

        public void set_$82(String _$82) {
            this._$82 = _$82;
        }

        public String get_$9155() {
            return _$9155;
        }

        public void set_$9155(String _$9155) {
            this._$9155 = _$9155;
        }

        public String get_$10243() {
            return _$10243;
        }

        public void set_$10243(String _$10243) {
            this._$10243 = _$10243;
        }

        public String get_$11319() {
            return _$11319;
        }

        public void set_$11319(String _$11319) {
            this._$11319 = _$11319;
        }

        public String get_$12298() {
            return _$12298;
        }

        public void set_$12298(String _$12298) {
            this._$12298 = _$12298;
        }

        public String get_$13107() {
            return _$13107;
        }

        public void set_$13107(String _$13107) {
            this._$13107 = _$13107;
        }

        public String get_$1498() {
            return _$1498;
        }

        public void set_$1498(String _$1498) {
            this._$1498 = _$1498;
        }

        public String get_$1519() {
            return _$1519;
        }

        public void set_$1519(String _$1519) {
            this._$1519 = _$1519;
        }

        public String get_$16126() {
            return _$16126;
        }

        public void set_$16126(String _$16126) {
            this._$16126 = _$16126;
        }

        public String get_$17113() {
            return _$17113;
        }

        public void set_$17113(String _$17113) {
            this._$17113 = _$17113;
        }

        public String get_$18218() {
            return _$18218;
        }

        public void set_$18218(String _$18218) {
            this._$18218 = _$18218;
        }

        public String get_$19103() {
            return _$19103;
        }

        public void set_$19103(String _$19103) {
            this._$19103 = _$19103;
        }

        public String get_$20206() {
            return _$20206;
        }

        public void set_$20206(String _$20206) {
            this._$20206 = _$20206;
        }

        public String get_$21318() {
            return _$21318;
        }

        public void set_$21318(String _$21318) {
            this._$21318 = _$21318;
        }

        public String get_$2226() {
            return _$2226;
        }

        public void set_$2226(String _$2226) {
            this._$2226 = _$2226;
        }

        public String get_$23201() {
            return _$23201;
        }

        public void set_$23201(String _$23201) {
            this._$23201 = _$23201;
        }

        public String get_$24266() {
            return _$24266;
        }

        public void set_$24266(String _$24266) {
            this._$24266 = _$24266;
        }

        public String get_$25314() {
            return _$25314;
        }

        public void set_$25314(String _$25314) {
            this._$25314 = _$25314;
        }

        public String get_$26332() {
            return _$26332;
        }

        public void set_$26332(String _$26332) {
            this._$26332 = _$26332;
        }

        public String get_$2727() {
            return _$2727;
        }

        public void set_$2727(String _$2727) {
            this._$2727 = _$2727;
        }

        public String get_$28104() {
            return _$28104;
        }

        public void set_$28104(String _$28104) {
            this._$28104 = _$28104;
        }

        public String get_$29241() {
            return _$29241;
        }

        public void set_$29241(String _$29241) {
            this._$29241 = _$29241;
        }

        public String get_$30165() {
            return _$30165;
        }

        public void set_$30165(String _$30165) {
            this._$30165 = _$30165;
        }

        public String get_$31188() {
            return _$31188;
        }

        public void set_$31188(String _$31188) {
            this._$31188 = _$31188;
        }
    }
}
