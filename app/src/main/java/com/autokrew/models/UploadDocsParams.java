package com.autokrew.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SONY on 03-08-2016.
 */
public class UploadDocsParams {

/*    Flag: 'GridForList',
    SessionUserFk: SessionEmployeeFK,
    Document: "",
    DocumentPK: -1*/
//------------------------
    //get all docs..
    private String SessionUserFk;
    private String Document;
    private String DocumentPK;
    private String Flag; //common

//-------------------------
    //upload all docs..
  /*  Flag: "Insert",
    EmployeeFK: EmployeeFK,
    LoginEployeeFk: SessionEmployeeFK, DocumentDetailPK: DocumentDetailPK, EmpDocument: DoumentName,
    ImageAsString: ImageAsString
    FileName:FileName
    DocumentDetailPK: DocumentDetailPK,
EmpDocument: DoumentName,
*/
    private String EmployeeFK;
    private String LoginEployeeFk;
    private String ImageAsString;
    private String FileName;
    private String DocumentDetailPK;
    private String EmpDocument;
    //-----------------------------------


    public String getDocumentDetailPK() {
        return DocumentDetailPK;
    }

    public void setDocumentDetailPK(String documentDetailPK) {
        DocumentDetailPK = documentDetailPK;
    }

    public String getEmpDocument() {
        return EmpDocument;
    }

    public void setEmpDocument(String empDocument) {
        EmpDocument = empDocument;
    }

    public String getEmployeeFK() {
        return EmployeeFK;
    }

    public void setEmployeeFK(String employeeFK) {
        EmployeeFK = employeeFK;
    }

    public String getLoginEployeeFk() {
        return LoginEployeeFk;
    }

    public void setLoginEployeeFk(String loginEployeeFk) {
        LoginEployeeFk = loginEployeeFk;
    }

    public String getImageAsString() {
        return ImageAsString;
    }

    public void setImageAsString(String imageAsString) {
        ImageAsString = imageAsString;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getSessionUserFk() {
        return SessionUserFk;
    }

    public void setSessionUserFk(String sessionUserFk) {
        SessionUserFk = sessionUserFk;
    }

    public String getFlag() {
        return Flag;
    }

    public void setFlag(String flag) {
        Flag = flag;
    }

    public String getDocument() {
        return Document;
    }

    public void setDocument(String document) {
        Document = document;
    }

    public String getDocumentPK() {
        return DocumentPK;
    }

    public void setDocumentPK(String documentPK) {
        DocumentPK = documentPK;
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
