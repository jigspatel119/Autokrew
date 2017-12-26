package com.autokrew.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SONY on 03-08-2016.
 */
public class AddDeviationTeamParams {


   /* private List<AddDeviationTeamParams> mList = new ArrayList<>();

    public List<AddDeviationTeamParams> getmList() {
        return mList;
    }

    public void setmList(List<AddDeviationTeamParams> mList) {
        this.mList = mList;
    }*/

    @SerializedName("AttendancePK")
    @Expose
    private String attendancePK;
    @SerializedName("AppStatus")
    @Expose
    private String appStatus;
    @SerializedName("Remarks")
    @Expose
    private String remarks;
    @SerializedName("SessionEmployeeFK")
    @Expose
    private String sessionEmployeeFK;

    public AddDeviationTeamParams(String attendancePK,String appStatus,String remarks,String sessionEmployeeFK) {
        this.attendancePK = attendancePK;
        this.appStatus = appStatus;
        this.remarks = remarks;
        this.sessionEmployeeFK=sessionEmployeeFK;
    }

    public String getAttendancePK() {
        return attendancePK;
    }

    public void setAttendancePK(String attendancePK) {

        this.attendancePK = attendancePK;
    }

    public String getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSessionEmployeeFK() {
        return sessionEmployeeFK;
    }

    public void setSessionEmployeeFK(String sessionEmployeeFK) {
        this.sessionEmployeeFK = sessionEmployeeFK;
    }
}
