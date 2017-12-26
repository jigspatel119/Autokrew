package com.autokrew.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 17-10-2017.
 */

public class PointingUrlModel {

    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Data")
    @Expose
    private Data data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }




    public class Data {

        @SerializedName("Id")
        @Expose
        private Integer id;
        @SerializedName("CompanyName")
        @Expose
        private String companyName;
        @SerializedName("MobileURL")
        @Expose
        private String mobileURL;
        @SerializedName("OTP")
        @Expose
        private String oTP;
        @SerializedName("IsActive")
        @Expose
        private Boolean isActive;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getMobileURL() {
            return mobileURL;
        }

        public void setMobileURL(String mobileURL) {
            this.mobileURL = mobileURL;
        }

        public String getOTP() {
            return oTP;
        }

        public void setOTP(String oTP) {
            this.oTP = oTP;
        }

        public Boolean getIsActive() {
            return isActive;
        }

        public void setIsActive(Boolean isActive) {
            this.isActive = isActive;
        }

    }

}
