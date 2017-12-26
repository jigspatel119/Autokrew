package com.autokrew.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by SONY on 03-08-2016.
 */
public class BirthdayModel {  // Getter and Setter model for recycler view items
    private String title;
    private int image;

    public BirthdayModel(String title,  int image) {

        this.title = title;

        this.image = image;
    }

    public String getTitle() {
        return title;
    }



    public int getImage() {
        return image;
    }
}