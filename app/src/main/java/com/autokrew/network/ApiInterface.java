package com.autokrew.network;


import com.autokrew.models.AddDeviationParams;
import com.autokrew.models.AddDeviationTeamParams;
import com.autokrew.models.ApplyAttendanceParam;
import com.autokrew.models.ApplyLeaveParams;
import com.autokrew.models.AttendanceInDetailModelParams;
import com.autokrew.models.AttendanceModelParams;
import com.autokrew.models.BindWeekOffParams;
import com.autokrew.models.CancleLeaveParams;
import com.autokrew.models.CommonDetailModelParams;
import com.autokrew.models.CompoffLeaveParams;
import com.autokrew.models.DashbordModelParam;
import com.autokrew.models.IsDocumentRequireParams;
import com.autokrew.models.IsLeaveAppliedParams;
import com.autokrew.models.LeaveCardParams;
import com.autokrew.models.LetestVersionModel;
import com.autokrew.models.LoginModel;
import com.autokrew.models.LoginModelParams;
import com.autokrew.models.ManageLeaveParams;
import com.autokrew.models.MyprofileParams;
import com.autokrew.models.PointingUrlModel;
import com.autokrew.models.PreviewAnnouncementParam;
import com.autokrew.models.ProfileImageParams;
import com.autokrew.models.SandwichParams;
import com.autokrew.models.TeamMemberModelParams;
import com.autokrew.models.TeamOrGroupLeaveModelParams;
import com.autokrew.models.UserProfileParams;
import com.autokrew.utils.Constant;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by kumarpalsinh on 29/12/16.
 */

public interface ApiInterface {

    // variable to hold context


    //********* Live Server Developers******
    public static final String Main_URL = Constant.MAIN_URL;

    public static String BASE_URL = "http://79.143.188.202:94/";



    //APIs List
    String Login_URL = "/api/Login/CheckLoginFromMobileApp";
    String Signup_URL = "api/sign-up";
    String Sync_Contacts_URL = "http://192.168.1.231/Social_App/api/web/users/sync-phone-book";


    //Login
    @Headers("Content-Type: application/json")
    //@POST("http://ibotshr.ibots.tech/api/Login/CheckLoginFromMobileApp")
    @POST("{MAIN_URL}api/Login/CheckLoginFromMobileApp")
    Call<LoginModel> login(
            @Path(value = "MAIN_URL", encoded = true) String MAIN_URL,
            @Body LoginModelParams body);




    //get profile data...

    @Headers("Content-Type: application/json")
    //@POST("http://ibotshr.ibots.tech/api/Login/CheckLoginFromMobileApp")
    @POST("{MAIN_URL}api/Profile/ProfileMaster")
    Call<String> getProfile(
            @Header("Authorization") String token,
            @Path(value = "MAIN_URL", encoded = true) String MAIN_URL,
            @Body UserProfileParams body);


    //SignUp
    @Headers("Content-Type: application/json")
    @POST("{MAIN_URL}/api/Profile/UploadEmployeeDocumentForMobileApp")
    Call<String> setProfile(
            @Header("Authorization") String token,
            @Path(value = "MAIN_URL", encoded = true) String MAIN_URL,
            @Body ProfileImageParams body
    );


    //get Attendance
    @Headers("Content-Type: application/json")
    @POST("{MAIN_URL}/api/Attendance/EmpAttendanceDetails")
    Call<String> getAttendance(
            @Header("Authorization") String token,
            @Path(value = "MAIN_URL", encoded = true) String MAIN_URL,
            @Body AttendanceModelParams body
    );



    //get Attendance in detail
    @Headers("Content-Type: application/json")
    @POST("{MAIN_URL}/api/Attendance/AttDetail")
    Call<String> getAttendanceInDetail(
            @Header("Authorization") String token,
            @Path(value = "MAIN_URL", encoded = true) String MAIN_URL,
            @Body AttendanceInDetailModelParams body
    );


    //dashboard
    @Headers("Content-Type: application/json")
    @POST("{MAIN_URL}api/Profile/DashBoradEmployee")
    Call<String> getDashboardDetail(
            @Header("Authorization") String token,
            @Path(value = "MAIN_URL", encoded = true) String MAIN_URL,
            @Body DashbordModelParam body);


    //dashboard
    @Headers("Content-Type: application/json")
    @POST("{MAIN_URL}api/Attendance/OutSideAttendanceFromMobileApp")
    Call<String> applyAttendance(
            @Header("Authorization") String token,
            @Path(value = "MAIN_URL", encoded = true) String MAIN_URL,
            @Body ApplyAttendanceParam body);






    //announcement preview
    @Headers("Content-Type: application/json")
    @POST("{MAIN_URL}api/CompanyConfig/InsertOrgNewsGrid")
    Call<String> getAnnouncementPreview(
            @Header("Authorization") String token,
            @Path(value = "MAIN_URL", encoded = true) String MAIN_URL,
            @Body PreviewAnnouncementParam body);



    //get Team member
    @Headers("Content-Type: application/json")
    @POST("{MAIN_URL}/api/Profile/EmployeeListTeamWiseForAttendance")
    Call<String> getTeamMember(
            @Header("Authorization") String token,
            @Path(value = "MAIN_URL", encoded = true) String MAIN_URL,
            @Body TeamMemberModelParams body
    );


    //get Team member for team leave
    @Headers("Content-Type: application/json")
    @POST("{MAIN_URL}/api/Profile/EmployeeListTeamWise")
    Call<String> getTeamMemberForTeamLeave(
            @Header("Authorization") String token,
            @Path(value = "MAIN_URL", encoded = true) String MAIN_URL,
            @Body TeamMemberModelParams body
    );


    //get Team member team group leave
    @Headers("Content-Type: application/json")
    @POST("{MAIN_URL}/api/Profile/EmployeeListforMobileApp")
    Call<String> getTeamMemberForGroupLeave(
            @Header("Authorization") String token,
            @Path(value = "MAIN_URL", encoded = true) String MAIN_URL,
            @Body TeamMemberModelParams body
    );


    //get Team member
    @Headers("Content-Type: application/json")
    @POST("{MAIN_URL}/api/Profile/EmployeeListForAttendance")
    Call<String> getTeamMemberForGroup(
            @Header("Authorization") String token,
            @Path(value = "MAIN_URL", encoded = true) String MAIN_URL,
            @Body TeamMemberModelParams body
    );

    //get Common detail
    @Headers("Content-Type: application/json")
    @POST("{MAIN_URL}/api/Profile/CommonDetailsNew")
    Call<String> getCommonDetail(
            @Header("Authorization") String token,
            @Path(value = "MAIN_URL", encoded = true) String MAIN_URL,
            @Body CommonDetailModelParams body
    );

    //get WO
    @Headers("Content-Type: application/json")
    @POST("{MAIN_URL}/api/Leave/GetEmployeeWODaysForMobileApp")
    Call<String> getWeekOff(
            @Header("Authorization") String token,
            @Path(value = "MAIN_URL", encoded = true) String MAIN_URL,
            @Body BindWeekOffParams body
    );


    //add deviation
    @Headers("Content-Type: application/json")
    @POST("{MAIN_URL}/api/Attendance/EmpDetails")
    Call<String> addDeviation(
            @Header("Authorization") String token,
            @Path(value = "MAIN_URL", encoded = true) String MAIN_URL,
            @Body AddDeviationParams body
    );

    //apply leave
    @Headers("Content-Type: application/json")
    @POST("{MAIN_URL}/api/Leave/InsertLeaveRequest")
    Call<String> applyLeave(
            @Header("Authorization") String token,
            @Path(value = "MAIN_URL", encoded = true) String MAIN_URL,
            @Body ApplyLeaveParams body
    );


    //getCompoffLeave
    @Headers("Content-Type: application/json")
    @POST("{MAIN_URL}/api//Leave/CompoffGrid")
    Call<String> getCompoffLeave(
            @Header("Authorization") String token,
            @Path(value = "MAIN_URL", encoded = true) String MAIN_URL,
            @Body CompoffLeaveParams body
    );



    //is doc require or not
    @Headers("Content-Type: application/json")
    @POST("{MAIN_URL}/api/Leave/IsDocumentRequired")
    Call<String> isDocRequire(
            @Header("Authorization") String token,
            @Path(value = "MAIN_URL", encoded = true) String MAIN_URL,
            @Body IsDocumentRequireParams body
    );



    //is leave apply or not
    @Headers("Content-Type: application/json")
    @POST("{MAIN_URL}/api/Leave/isLeaveAlreadyApply")
    Call<String> isLeaveApply(
            @Header("Authorization") String token,
            @Path(value = "MAIN_URL", encoded = true) String MAIN_URL,
            @Body IsLeaveAppliedParams body
    );



    //check form sandwhich leave
    @Headers("Content-Type: application/json")
    @POST("{MAIN_URL}/api/Leave/LeaveFromDateSandwichCheck")
    Call<String> checkFromSandwich(
            @Header("Authorization") String token,
            @Path(value = "MAIN_URL", encoded = true) String MAIN_URL,
            @Body SandwichParams body
    );


    //check to sandwhich leave
    @Headers("Content-Type: application/json")
    @POST("{MAIN_URL}/api/Leave/LeaveToDateSandwichCheck")
    Call<String> checkToSandwich(
            @Header("Authorization") String token,
            @Path(value = "MAIN_URL", encoded = true) String MAIN_URL,
            @Body SandwichParams body
    );

    //cancel leave
    @Headers("Content-Type: application/json")
    @POST("{MAIN_URL}/api/Leave/DelLeaveRequest")
    Call<String> cancleLeave(
            @Header("Authorization") String token,
            @Path(value = "MAIN_URL", encoded = true) String MAIN_URL,
            @Body CancleLeaveParams body
    );


    //get reporting person
    @Headers("Content-Type: application/json")
    @POST("{MAIN_URL}/api/Leave/EmpDetail")
    Call<String> getReportingPerson(
            @Header("Authorization") String token,
            @Path(value = "MAIN_URL", encoded = true) String MAIN_URL,
            @Body LeaveCardParams body
    );

    //getMyprofile  person
    @Headers("Content-Type: application/json")
    @POST("{MAIN_URL}/api/Profile/ProfileBasicDetails")
    Call<String> getMyprofile(
            @Header("Authorization") String token,
            @Path(value = "MAIN_URL", encoded = true) String MAIN_URL,
            @Body MyprofileParams body
    );


    //add deviation for TEAM
    @Headers("Content-Type: application/json")
    @POST("{MAIN_URL}/api/Attendance/UpdateDeviationApprovalForMobileApp")
    Call<String> addDeviationTeam(
            @Header("Authorization") String token,
            @Path(value = "MAIN_URL", encoded = true) String MAIN_URL,
            @Body AddDeviationTeamParams body
    );


    //getTeamAttendance
    @Headers("Content-Type: application/json")
    @POST("{MAIN_URL}/api/Attendance/EmpDeviation")
    Call<String> getTeamAttendance(
            @Header("Authorization") String token,
            @Path(value = "MAIN_URL", encoded = true) String MAIN_URL,
            @Body AttendanceModelParams body
    );


    //getTeamAttendance for group
    @Headers("Content-Type: application/json")
    @POST("{MAIN_URL}/api/Attendance/TabEmpGroupDeviation")
    Call<String> getTeamAttendanceForGroup(
            @Header("Authorization") String token,
            @Path(value = "MAIN_URL", encoded = true) String MAIN_URL,
            @Body AttendanceModelParams body
    );


    @Headers("Content-Type: application/json")
    @POST("{MAIN_URL}/api/Leave/TeamEmpLeaveRequest")
    Call<String> getTeamLeave(
            @Header("Authorization") String token,
            @Path(value = "MAIN_URL", encoded = true) String MAIN_URL,
            @Body TeamOrGroupLeaveModelParams body
    );


    @Headers("Content-Type: application/json")
    @POST("{MAIN_URL}/api/Leave/GroupEmpLeaveRequest")
    Call<String> getGroupLeave(
            @Header("Authorization") String token,
            @Path(value = "MAIN_URL", encoded = true) String MAIN_URL,
            @Body TeamOrGroupLeaveModelParams body
    );




    //get Common detail
    @Headers("Content-Type: application/json")
    @POST("{MAIN_URL}/api/Leave/EmpLeaveCard")
    Call<String> getLeaveCard(
            @Header("Authorization") String token,
            @Path(value = "MAIN_URL", encoded = true) String MAIN_URL,
            @Body LeaveCardParams body
    );



    //manage leave
    @Headers("Content-Type: application/json")
    @POST("{MAIN_URL}/api/Leave/ManageLeaveStatus")
    Call<String> getManageLeave(
            @Header("Authorization") String token,
            @Path(value = "MAIN_URL", encoded = true) String MAIN_URL,
            @Body ManageLeaveParams body
    );



    //get getPointingUrl

    @GET("api/CommonAPI/CompanyDetail")
    Call<PointingUrlModel> getPointingUrl(@Query("OTP") String otp);


    @GET("api/CommonAPI/LatestVersion")
    Call<LetestVersionModel> getLetestVersion();

}
