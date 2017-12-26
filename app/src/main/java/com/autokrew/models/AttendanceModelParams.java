package com.autokrew.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by SONY on 03-08-2016.
 */
public class AttendanceModelParams {

   //  {"SessionEmployeeFK":8816,"MonthFK":9,"YearFk":2017}

    //{"SessionUserFk":8816,"MonthFK":9,"YearFk":2017,"EmployeeFK":-1,"CompanyFK" :-1,"LocationFK":-1,"SubLocationFK":-1,"VerticalFK":-1,"DepartmentFK":-1,"SubDepartmentFK":-1,"DesignationFK":-1,"ApprovalStatus":"1","Flag":"Employee_Attendance_Register"}


    @SerializedName("SessionEmployeeFK")
    @Expose
    private Integer sessionEmployeeFK;
    @SerializedName("MonthFK")
    @Expose
    private Integer monthFK;
    @SerializedName("YearFk")
    @Expose
    private Integer yearFk;
    /**
     * CompanyFK : -1
     * LocationFK : -1
     * SubLocationFK : -1
     * VerticalFK : -1
     * DepartmentFK : -1
     * SubDepartmentFK : -1
     * DesignationFK : -1
     */

    private int CompanyFK;
    private int LocationFK;
    private int SubLocationFK;
    private int VerticalFK;
    private int DepartmentFK;
    private int SubDepartmentFK;
    private int DesignationFK;

    public Integer getSessionEmployeeFK() {
        return sessionEmployeeFK;
    }

    public void setSessionEmployeeFK(Integer sessionEmployeeFK) {
        this.sessionEmployeeFK = sessionEmployeeFK;
    }

    public Integer getMonthFK() {
        return monthFK;
    }

    public void setMonthFK(Integer monthFK) {
        this.monthFK = monthFK;
    }

    public Integer getYearFk() {
        return yearFk;
    }

    public void setYearFk(Integer yearFk) {
        this.yearFk = yearFk;
    }


    //{"SessionUserFk":8816,"MonthFK":8,"YearFk":2017,"EmployeeFK":-1,"ApprovalStatus":"1","Flag":"Employee_Attendance_Register"}

    //for team and group attendance input params

    @SerializedName("SessionUserFk")
    @Expose
    private Integer SessionUserFk;

    @SerializedName("EmployeeFK")
    @Expose
    private Integer EmployeeFK;

    @SerializedName("ApprovalStatus")
    @Expose
    private String ApprovalStatus;

    @SerializedName("Flag")
    @Expose
    private String Flag;


    public Integer getSessionUserFk() {
        return SessionUserFk;
    }

    public void setSessionUserFk(Integer sessionUserFk) {
        SessionUserFk = sessionUserFk;
    }

    public Integer getEmployeeFK() {
        return EmployeeFK;
    }

    public void setEmployeeFK(Integer employeeFK) {
        EmployeeFK = employeeFK;
    }

    public String getApprovalStatus() {
        return ApprovalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        ApprovalStatus = approvalStatus;
    }

    public String getFlag() {
        return Flag;
    }

    public void setFlag(String flag) {
        Flag = flag;
    }

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
}
