package com.autokrew.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SONY on 03-08-2016.
 */
public class GetDocumentResponse {


    private List<TableBean> Table;

    public List<TableBean> getTable() {
        return Table;
    }

    public void setTable(List<TableBean> Table) {
        this.Table = Table;
    }

    public static class TableBean {
        /**
         * DocumentPK : 1
         * Document : ID Proof
         */

        private int DocumentPK;
        private String Document;

        public int getDocumentPK() {
            return DocumentPK;
        }

        public void setDocumentPK(int DocumentPK) {
            this.DocumentPK = DocumentPK;
        }

        public String getDocument() {
            return Document;
        }

        public void setDocument(String Document) {
            this.Document = Document;
        }




        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private List<AddDeviationTeamParams> Param = new ArrayList<>();

        public List<AddDeviationTeamParams> getParam() {
            return Param;
        }

        public void setParam(List<AddDeviationTeamParams> param) {
            Param = param;
        }

    }
}
