package com.autokrew.models;

/**
 * Created by SONY on 03-08-2016.
 */
public class DeviceTokenParam {
    /**
     * flag : Employee
     * EmployeeFK : 8816
     *
     * flag
     EmployeeFK
     DeviceId
     */

    private String flag;
    private String EmployeeFK;
    private String DeviceId;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getEmployeeFK() {
        return EmployeeFK;
    }

    public void setEmployeeFK(String EmployeeFK) {
        this.EmployeeFK = EmployeeFK;
    }  // Getter and Setter model for recycler view items

    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String deviceId) {
        DeviceId = deviceId;
    }
}