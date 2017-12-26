package com.autokrew.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SONY on 03-08-2016.
 */
public class TempParams {


    private List<AddDeviationTeamParams> Param = new ArrayList<>();

    public List<AddDeviationTeamParams> getParam() {
        return Param;
    }

    public void setParam(List<AddDeviationTeamParams> param) {
        Param = param;
    }


}
