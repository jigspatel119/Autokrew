package com.autokrew.utils;

import com.autokrew.models.AttendanceRegisterModel;
import com.autokrew.models.AttendanceStatusModel;
import com.autokrew.models.CompoffLeaveModel;
import com.autokrew.models.GetDocumentResponse;
import com.autokrew.models.UploadDocsParams;

import java.util.ArrayList;

public class Constant {

    public static final String WEBSERVICE_FAILURE = "Sorry, something went wrong. Please try after sometime. ";
    public static final String INTERNET_FAILURE = "Please check your internet connection and then try again. ";
    public static final String NOT_LOGGED_IN = "Please login first to avail this feature. ";


    public static  String aap_name = "autokrew";
    /***************
     * File Directory
     */
    public static final String FILE_DIRECTORY_MAIN = "/sdcard/"+aap_name;
    public static final String FILE_DIRECTORY_MEDIA = "/sdcard/"+aap_name+"/Media";


    public static final String PREF_FILE = aap_name+"_PREF";


    public static final String PREF_MOBILE_URL = "mobile_url";
    public static final String PREF_TOKEN = "token";
    public static final String PREF_SESSION_EMPLOYEE_FK = "session_employee_fk";
    public static final String PREF_SESSION_EMPLOYEE_NAME = "session_employee_name";
    public  static  final String MAIN_URL = "http://79.143.188.202:94/api/CommonAPI/";

    public static final String PREF_USER_DATA = "user_data";


    public static ArrayList<CompoffLeaveModel> mCheckList = new ArrayList<>();

    public static final String PREF_LEAVE_BUTTON = "leave_item_button";

    public static final String PREF_ROLE_FK = "roleFK";

    public static String LAST_ACTIVITY = "";
    public  static  final int GPS_SETTINGS = 105;


    public static  AttendanceStatusModel mStatusModel = new AttendanceStatusModel();
    public static  AttendanceRegisterModel mRegisterModel = new AttendanceRegisterModel();
    public static  ArrayList<GetDocumentResponse.TableBean> mList = new ArrayList<>();



}
