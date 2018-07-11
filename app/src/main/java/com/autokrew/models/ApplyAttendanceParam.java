package com.autokrew.models;

/**
 * Created by SONY on 03-08-2016.
 */
public class ApplyAttendanceParam {


    /**
     * EmployeePk : 8816
     * isQrCode : 1
     * QrCode : 123456
     * Att_Lat :
     * Att_Long :
     * IMEINumber :
     */

    private String EmployeePk;
    private String isQrCode;
    private String QrCode;
    private String Att_Lat;
    private String Att_Long;
    private String Att_PhisicalAddress;
    private String IMEINumber;

    public String getIMEINumber() {
        return IMEINumber;
    }

    public void setIMEINumber(String IMEINumber) {
        this.IMEINumber = IMEINumber;
    }

    public String getAtt_PhisicalAddress() {
        return Att_PhisicalAddress;
    }

    public void setAtt_PhisicalAddress(String att_PhisicalAddress) {
        Att_PhisicalAddress = att_PhisicalAddress;
    }

    public String getEmployeePk() {
        return EmployeePk;
    }

    public void setEmployeePk(String EmployeePk) {
        this.EmployeePk = EmployeePk;
    }

    public String getIsQrCode() {
        return isQrCode;
    }

    public void setIsQrCode(String isQrCode) {
        this.isQrCode = isQrCode;
    }

    public String getQrCode() {
        return QrCode;
    }

    public void setQrCode(String QrCode) {
        this.QrCode = QrCode;
    }

    public String getAtt_Lat() {
        return Att_Lat;
    }

    public void setAtt_Lat(String Att_Lat) {
        this.Att_Lat = Att_Lat;
    }

    public String getAtt_Long() {
        return Att_Long;
    }

    public void setAtt_Long(String Att_Long) {
        this.Att_Long = Att_Long;
    }
}