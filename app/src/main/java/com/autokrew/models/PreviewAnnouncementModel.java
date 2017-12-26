package com.autokrew.models;

import java.util.List;

/**
 * Created by SONY on 03-08-2016.
 */
public class PreviewAnnouncementModel {


    private List<TableBean> Table;
    private List<Table1Bean> Table1;

    public List<TableBean> getTable() {
        return Table;
    }

    public void setTable(List<TableBean> Table) {
        this.Table = Table;
    }

    public List<Table1Bean> getTable1() {
        return Table1;
    }

    public void setTable1(List<Table1Bean> Table1) {
        this.Table1 = Table1;
    }

    public static class TableBean {
        /**
         * NewsPK : 1
         * Title : Apollo CV Awards 2017
         * Description : <p><strong>Apollo CV 2017 - CV Dealer of the Year - Cargo&nbsp;Motors&nbsp;</strong><strong>Pvt. Ltd.</strong></p>

         <p>&nbsp;</p>

         <p><strong><img alt="" src="http://cargogroup.co.in/Upload/EmployeDocument/8700/e3e85dd5-5a0c-4a35-926c-e740483dd23e_CVAPOLLO2017.jpg" style="height:480px; width:350px" /></strong></p>

         <p>&nbsp;</p>

         <p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>

         <p>&nbsp;</p>

         <p>&nbsp;</p>

         <p>&nbsp;</p>

         */

        private int NewsPK;
        private String Title;
        private String Description;

        public int getNewsPK() {
            return NewsPK;
        }

        public void setNewsPK(int NewsPK) {
            this.NewsPK = NewsPK;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }
    }

    public static class Table1Bean {
        /**
         * ImageName : awards.jpg
         * ImageURL : 8816/5f8b8acf-4e21-45b3-aa51-72b56bce7642_awards.jpg
         */

        private String ImageName;
        private String ImageURL;

        public String getImageName() {
            return ImageName;
        }

        public void setImageName(String ImageName) {
            this.ImageName = ImageName;
        }

        public String getImageURL() {
            return ImageURL;
        }

        public void setImageURL(String ImageURL) {
            this.ImageURL = ImageURL;
        }
    }
}