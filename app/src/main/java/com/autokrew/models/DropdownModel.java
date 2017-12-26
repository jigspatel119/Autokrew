package com.autokrew.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by SONY on 03-08-2016.
 */
public class DropdownModel {

    private String CompanyPK;
    private String Company;
    private String VerticalPK;
    private String vertical;
    private String DesignationPK;
    private String Designation;
    private String LocationPK;
    private String Location;
    private String SubLocationPK;
    private String SubLocation;

    private String DepartmentPK;
    private String Department;

    private String SubDepartmentPK;
    private String SubDepartment;

    private String EmployeePK;
    private String Employee;

    private String YearPk;
    private int Year;

    private String MonthPk;
    private String Month;

    private String LeaveStatusPK;
    private String LeaveStatus;

    public String getLeaveStatusPK() {
        return LeaveStatusPK;
    }

    public void setLeaveStatusPK(String LeaveStatusPK) {
        this.LeaveStatusPK = LeaveStatusPK;
    }

    public String getLeaveStatus() {
        return LeaveStatus;
    }

    public void setLeaveStatus(String LeaveStatus) {
        this.LeaveStatus = LeaveStatus;
    }


    public String getCompanyPK() {
        return CompanyPK;
    }

    public void setCompanyPK(String companyPK) {
        CompanyPK = companyPK;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getVerticalPK() {
        return VerticalPK;
    }

    public void setVerticalPK(String verticalPK) {
        VerticalPK = verticalPK;
    }

    public String getVertical() {
        return vertical;
    }

    public void setVertical(String vertical) {
        this.vertical = vertical;
    }

    public String getDesignationPK() {
        return DesignationPK;
    }

    public void setDesignationPK(String designationPK) {
        DesignationPK = designationPK;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public String getLocationPK() {
        return LocationPK;
    }

    public void setLocationPK(String locationPK) {
        LocationPK = locationPK;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getSubLocationPK() {
        return SubLocationPK;
    }

    public void setSubLocationPK(String subLocationPK) {
        SubLocationPK = subLocationPK;
    }

    public String getSubLocation() {
        return SubLocation;
    }

    public void setSubLocation(String subLocation) {
        SubLocation = subLocation;
    }

    public String getDepartmentPK() {
        return DepartmentPK;
    }

    public void setDepartmentPK(String departmentPK) {
        DepartmentPK = departmentPK;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getSubDepartmentPK() {
        return SubDepartmentPK;
    }

    public void setSubDepartmentPK(String subDepartmentPK) {
        SubDepartmentPK = subDepartmentPK;
    }

    public String getSubDepartment() {
        return SubDepartment;
    }

    public void setSubDepartment(String subDepartment) {
        SubDepartment = subDepartment;
    }

    public String getEmployeePK() {
        return EmployeePK;
    }

    public void setEmployeePK(String employeePK) {
        EmployeePK = employeePK;
    }

    public String getEmployee() {
        return Employee;
    }

    public void setEmployee(String employee) {
        Employee = employee;
    }

    public String getYearPk() {
        return YearPk;
    }

    public void setYearPk(String yearPk) {
        YearPk = yearPk;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public String getMonthPk() {
        return MonthPk;
    }

    public void setMonthPk(String monthPk) {
        MonthPk = monthPk;
    }

    public String getMonth() {
        return Month;
    }

    public void setMonth(String month) {
        Month = month;
    }
}
