package com.autokrew.models;

/**
 * Created by SONY on 03-08-2016.
 */
public class DashboardModuleModel {  // Getter and Setter model for recycler view items
    private String title;
    private int count;
    public DashboardModuleModel() {

        this.title = title;

        this.count = count;
    }
    public DashboardModuleModel(String title, int count) {

        this.title = title;

        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}