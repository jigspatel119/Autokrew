package com.autokrew.models;

/**
 * Created by SONY on 03-08-2016.
 */
public class AddDeviationParams {


    /**
     * AttendancePK : 306011
     * DeviationFK : -1
     * EmpRemarks :
     * Month : 10
     * Year : 2017
     * Flag : Detail
     *
     */

    private int AttendancePK;
    private int DeviationFK;

    private String EmpRemarks;
    private int Month;
    private String Year;
    private String Flag;



    public int getAttendancePK() {
        return AttendancePK;
    }

    public void setAttendancePK(int AttendancePK) {
        this.AttendancePK = AttendancePK;
    }

    public int getDeviationFK() {
        return DeviationFK;
    }

    public void setDeviationFK(int DeviationFK) {
        this.DeviationFK = DeviationFK;
    }

    public String getEmpRemarks() {
        return EmpRemarks;
    }

    public void setEmpRemarks(String EmpRemarks) {
        this.EmpRemarks = EmpRemarks;
    }

    public int getMonth() {
        return Month;
    }

    public void setMonth(int Month) {
        this.Month = Month;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String Year) {
        this.Year = Year;
    }

    public String getFlag() {
        return Flag;
    }

    public void setFlag(String Flag) {
        this.Flag = Flag;
    }
}
