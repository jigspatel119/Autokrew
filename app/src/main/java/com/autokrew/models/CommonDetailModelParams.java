package com.autokrew.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by SONY on 03-08-2016.
 */
public class CommonDetailModelParams {

    @SerializedName("flag")
    @Expose
    private String flag;


    @SerializedName("tranTypes")
    @Expose
    private int tranTypes;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getTranTypes() {
        return tranTypes;
    }

    public void setTranTypes(int tranTypes) {
        this.tranTypes = tranTypes;
    }
}
