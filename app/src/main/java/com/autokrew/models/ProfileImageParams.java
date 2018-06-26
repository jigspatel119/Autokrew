package com.autokrew.models;

/**
 * Created by SONY on 03-08-2016.
 */
public class ProfileImageParams {


    /**
     * EmployeeFK : 8816
     * ImageAsString : "asdasdsadz"
     * FileName :
     */

    private int EmployeeFK;
    private String ImageAsString;
    private String FileName;

    public int getEmployeeFK() {
        return EmployeeFK;
    }

    public void setEmployeeFK(int EmployeeFK) {
        this.EmployeeFK = EmployeeFK;
    }

    public String getImageAsString() {
        return ImageAsString;
    }

    public void setImageAsString(String ImageAsString) {
        this.ImageAsString = ImageAsString;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String FileName) {
        this.FileName = FileName;
    }
}
