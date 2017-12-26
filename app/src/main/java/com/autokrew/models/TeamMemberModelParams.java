package com.autokrew.models;

/**
 * Created by SONY on 03-08-2016.
 */
public class TeamMemberModelParams {
    /**
     * CompanyFK : -1
     * LocationFK : -1
     * SubLocationFK : -1
     * VerticalFK : -1
     * DepartmentFK : -1
     * SubDepartmentFK : -1
     * DesignationFK : -1
     * SessionUserFk : 8816
     * isDailyWages : 0
     */

    private int CompanyFK;
    private int LocationFK;
    private int SubLocationFK;
    private int VerticalFK;
    private int DepartmentFK;
    private int SubDepartmentFK;
    private int DesignationFK;
    private int SessionUserFk;
    private int isDailyWages;

    public int getCompanyFK() {
        return CompanyFK;
    }

    public void setCompanyFK(int CompanyFK) {
        this.CompanyFK = CompanyFK;
    }

    public int getLocationFK() {
        return LocationFK;
    }

    public void setLocationFK(int LocationFK) {
        this.LocationFK = LocationFK;
    }

    public int getSubLocationFK() {
        return SubLocationFK;
    }

    public void setSubLocationFK(int SubLocationFK) {
        this.SubLocationFK = SubLocationFK;
    }

    public int getVerticalFK() {
        return VerticalFK;
    }

    public void setVerticalFK(int VerticalFK) {
        this.VerticalFK = VerticalFK;
    }

    public int getDepartmentFK() {
        return DepartmentFK;
    }

    public void setDepartmentFK(int DepartmentFK) {
        this.DepartmentFK = DepartmentFK;
    }

    public int getSubDepartmentFK() {
        return SubDepartmentFK;
    }

    public void setSubDepartmentFK(int SubDepartmentFK) {
        this.SubDepartmentFK = SubDepartmentFK;
    }

    public int getDesignationFK() {
        return DesignationFK;
    }

    public void setDesignationFK(int DesignationFK) {
        this.DesignationFK = DesignationFK;
    }

    public int getSessionUserFk() {
        return SessionUserFk;
    }

    public void setSessionUserFk(int SessionUserFk) {
        this.SessionUserFk = SessionUserFk;
    }

    public int getIsDailyWages() {
        return isDailyWages;
    }

    public void setIsDailyWages(int isDailyWages) {
        this.isDailyWages = isDailyWages;
    }

    //  {"SessionEmployeeFK":8816,"MonthFK":9,"YearFk":2017}



}
