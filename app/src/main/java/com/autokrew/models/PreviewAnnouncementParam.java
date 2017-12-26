package com.autokrew.models;

/**
 * Created by SONY on 03-08-2016.
 */
public class PreviewAnnouncementParam {


    /**
     * flag : PreviewDoc
     * LoginUserFk : 8816
     * Title :
     * StartDate :
     * EndDate :
     * NewsPK : 1
     */

    private String flag;
    private String LoginUserFk;
    private String Title;
    private String StartDate;
    private String EndDate;
    private int NewsPK;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getLoginUserFk() {
        return LoginUserFk;
    }

    public void setLoginUserFk(String LoginUserFk) {
        this.LoginUserFk = LoginUserFk;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String StartDate) {
        this.StartDate = StartDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String EndDate) {
        this.EndDate = EndDate;
    }

    public int getNewsPK() {
        return NewsPK;
    }

    public void setNewsPK(int NewsPK) {
        this.NewsPK = NewsPK;
    }
}