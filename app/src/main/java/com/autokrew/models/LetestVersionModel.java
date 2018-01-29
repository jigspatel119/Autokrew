package com.autokrew.models;

/**
 * Created by Admin on 17-10-2017.
 */

public class LetestVersionModel {


    /**
     * Status : Success
     * Message : Success
     * Data : {"Id":1,"Version":"1","VersionCreatedDate":"2018-01-06T12:05:03.697"}
     */

    private String Status;
    private String Message;
    private DataBean Data;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * Id : 1
         * Version : 1
         * VersionCreatedDate : 2018-01-06T12:05:03.697
         */

        private int Id;
        private String Version;
        private String VersionCreatedDate;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getVersion() {
            return Version;
        }

        public void setVersion(String Version) {
            this.Version = Version;
        }

        public String getVersionCreatedDate() {
            return VersionCreatedDate;
        }

        public void setVersionCreatedDate(String VersionCreatedDate) {
            this.VersionCreatedDate = VersionCreatedDate;
        }
    }
}
