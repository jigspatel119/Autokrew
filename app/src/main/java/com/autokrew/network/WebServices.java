package com.autokrew.network;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import com.autokrew.R;
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
import com.autokrew.models.DeviceTokenParam;
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
import com.autokrew.models.ResetPasswordParams;
import com.autokrew.models.SandwichParams;
import com.autokrew.models.TeamMemberModelParams;
import com.autokrew.models.TeamOrGroupLeaveModelParams;
import com.autokrew.models.UpdateUserProfileParams;
import com.autokrew.models.UploadDocsParams;
import com.autokrew.models.UserProfileParams;
import com.autokrew.utils.CommonUtils;
import com.autokrew.utils.Constant;
import com.autokrew.utils.Pref;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kumarpalsinh on 29/12/16.
 */

public class WebServices {

    String TAG = "WebServices ::";
    //General Variables
    Context mContext;

    Dialog dialog;

    ApiListener mApiListener;

    ApiInterface mApiInterface ,mApiInterfaceForUrl;

    String device_type = "1", device_token = "", latitude = "", longitude = "";


    /**
     * This constructor is executed before any api call
     */
    public WebServices(Context mContext, ApiListener mApiListener, Boolean isShowProgressDialog , Boolean isNewRetrofitClient) {

        //Initialize Context
        this.mContext = mContext;
        this.mApiListener = mApiListener;

        //Initialize Variables
       // latitude = AppController.getAppPref().getLatitude();
       // longitude = AppController.getAppPref().getLongitude();


        //Initialize progress dialog
        dialog = new Dialog(mContext, R.style.progressDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progress_dialog);
        //dialog.setCancelable(false);

        if (isShowProgressDialog) {
            //Show progress dialog
            dialog.show();
        }

        //Initialize ApiInterface for webservice call
        //2 retrofit client runtime

        if(isNewRetrofitClient){
            mApiInterface = initializeWebServiceCall();
        }
        else{
            mApiInterfaceForUrl =  initializeWebServiceCallForGetPointingUrl();
        }



    }

    /**
     * Initialize Retrofit Call
     */
    private ApiInterface initializeWebServiceCall() {
        //Constant.Main_URL =  Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,"");

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""))
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface mRestAPI = retrofit.create(ApiInterface.class);
        return mRestAPI;
    }



    /**
     * Initialize Retrofit Call for get pointing urls
     */
    private ApiInterface initializeWebServiceCallForGetPointingUrl() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES).readTimeout(1, TimeUnit.MINUTES).
                addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface mRestAPI = retrofit.create(ApiInterface.class);
        return mRestAPI;
    }



    /**
     * This Function executed after any success call of API
     */
    private void onSuccessResponse(Object mObject) {

        try {
            //Dismiss progress dialog
            dialog.dismiss();

            //Call on success method of ApiListener Interface
            mApiListener.onApiSuccess(mObject);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * This Function executed after any failure call of API
     */
    private void onFailureResponse(Throwable mThrowable) {

        //Dismiss progress dialog
        dialog.dismiss();


        if(mThrowable==null){
            //case 401:
            //nothing to display
           // CommonUtils.getInstance().displayToast(mContext,"its me");
        }
        else{
            //Display message
            CommonUtils.getInstance().displayAlert(mContext, mContext.getResources().getString(R.string.alert_server_not_responding));
        }

        //Call on failure method of ApiListener Interface
        mApiListener.onApiFailure(mThrowable);

    }

    /**
     * This Function executed when internet connection is not available
     */
    private void onNoInternetConnection() {

        //Dismiss progress dialog
        dialog.dismiss();

        //Display message
        CommonUtils.getInstance().displayAlert(mContext, mContext.getResources().getString(R.string.alert_no_internet_connection));


    }

    /**
     * //===========================( Login API )==============================//
     */
    public void callLoginAPI(LoginModelParams parms) {

        // Check for internet connection
        if (CommonUtils.getInstance().isNetworkAvailable(mContext)) {

            //Log.e(TAG, "input_params : "+input_params );
            Call<LoginModel> call = mApiInterface.login(Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""),parms);
            call.enqueue(new Callback<LoginModel>() {
                @Override
                public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                    Object mObject = response.body();
                    if (mObject != null) {
                        onSuccessResponse(mObject);
                    }
                }

                @Override
                public void onFailure(Call<LoginModel> call, Throwable t) {
                    onFailureResponse(t);
                }
            });

        } else {
            // No Internet connection available
            onNoInternetConnection();
        }
    }



    /**
     * //===========================( vrersion check API )==============================//
     */
    public void callLetestVersionAPI() {

        // Check for internet connection
        if (CommonUtils.getInstance().isNetworkAvailable(mContext)) {
            Call<LetestVersionModel> call = mApiInterfaceForUrl.getLetestVersion();
            call.enqueue(new Callback<LetestVersionModel>() {
                @Override
                public void onResponse(Call<LetestVersionModel> call, Response<LetestVersionModel> response) {
                    Object mObject = response.body();
                    if (mObject != null) {
                        onSuccessResponse(mObject);
                    }
                    /*else{
                        onFailureResponse();
                    }*/
                }
                @Override
                public void onFailure(Call<LetestVersionModel> call, Throwable t) {
                    onFailureResponse(t);
                }
            });

        } else {
            // No Internet connection available
            onNoInternetConnection();
        }
    }


    /**
     * //===========================( DashboardDetail API )==============================//
     */
    public void callDashboardDetailAPI(String mToken,DashbordModelParam parms) {

        // Check for internet connection
        if (CommonUtils.getInstance().isNetworkAvailable(mContext)) {
            Call<String> call = mApiInterface.getDashboardDetail(mToken,Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""),parms);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    switch (response.code()) {
                        case 401:
                            onFailureResponse(null);
                            break;
                        case 200:
                            Object mObject = response.body();
                            if (mObject != null) {
                                onSuccessResponse(mObject);
                            }
                            break;
                    }
                    /*else{
                        onFailureResponse();
                    }*/
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    onFailureResponse(t)
                    ;
                }
            });

        } else {
            // No Internet connection available
            onNoInternetConnection();
        }
    }


    /**
     * //===========================( SendDeviceTocken API )==============================//
     */
    public void callSendDeviceTockenAPI(String mToken,DeviceTokenParam parms) {

        // Check for internet connection
        if (CommonUtils.getInstance().isNetworkAvailable(mContext)) {
            Call<String> call = mApiInterface.sendDeviceToken(mToken,Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""),parms);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    switch (response.code()) {
                        case 401:
                            onFailureResponse(null);
                            break;
                        case 200:
                            Object mObject = response.body();
                            if (mObject != null) {
                                onSuccessResponse(mObject);
                            }
                            break;
                    }
                    /*else{
                        onFailureResponse();
                    }*/
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    onFailureResponse(t)
                    ;
                }
            });

        } else {
            // No Internet connection available
            onNoInternetConnection();
        }
    }





    /**
     * //===========================( Login API )==============================//
     */
    public void callOutSideAttendanceFromMobileAppAPI(String mToken,ApplyAttendanceParam parms) {

        // Check for internet connection
        if (CommonUtils.getInstance().isNetworkAvailable(mContext)) {
            Call<String> call = mApiInterface.applyAttendance(mToken,Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""),parms);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Object mObject = response.body();
                    if (mObject != null) {
                        onSuccessResponse(mObject);
                    }
                    /*else{
                        onFailureResponse();
                    }*/
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    onFailureResponse(t);
                }
            });

        } else {
            // No Internet connection available
            onNoInternetConnection();
        }
    }



    /**
     * //===========================( preview  API )==============================//
     */
    public void callAnnouncementPreviewAPI(String mToken,PreviewAnnouncementParam parms) {

        // Check for internet connection
        if (CommonUtils.getInstance().isNetworkAvailable(mContext)) {
            Call<String> call = mApiInterface.getAnnouncementPreview(mToken,Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""),parms);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Object mObject = response.body();
                    if (mObject != null) {
                        onSuccessResponse(mObject);
                    }
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    onFailureResponse(t);
                }
            });

        } else {
            // No Internet connection available
            onNoInternetConnection();
        }
    }



    /**
     * //===========================( user profile  API )==============================//
     */
    public void callUserProfileAPI(String mToken,UserProfileParams parms) {


        // Check for internet connection
        if (CommonUtils.getInstance().isNetworkAvailable(mContext)) {

            //Log.e(TAG, "input_params : "+input_params );
            Call<String> call = mApiInterface.getProfile(mToken,Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""),parms);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Object mObject = response.body();
                    if (mObject != null) {
                        onSuccessResponse(mObject);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    onFailureResponse(t);
                }
            });

        } else {
            // No Internet connection available
            onNoInternetConnection();
        }
    }

    /**
     * //===========================( SignUp API )==============================//
     */

    public void callProfileImageAPI(String mToken , ProfileImageParams params) {

        // Check for internet connection
        if (CommonUtils.getInstance().isNetworkAvailable(mContext)) {

            Call<String> call = mApiInterface.setProfile(mToken,  Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""),params);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Object mObject = response.body();
                    if (mObject != null) {
                        onSuccessResponse(mObject);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    onFailureResponse(t);
                }
            });

        } else {
            // No Internet connection available
            onNoInternetConnection();
        }


    }



    /** //for my attandence /group attendance /team attandence
     * //===========================( get Attendance API )==============================//
     */
    public void callGetAttendanceAPI(String mToken , AttendanceModelParams params ,String from_last) {


        // Check for internet connection
        if (CommonUtils.getInstance().isNetworkAvailable(mContext)) {

            // Internet connection available
            // Call Webservice
            Call<String> call = null;
            if(from_last.equalsIgnoreCase("MyAttendance")){
                call = mApiInterface.getAttendance(mToken,
                        Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""),params);
            }
            else if(from_last.equalsIgnoreCase("TeamAttendance")){
                call = mApiInterface.getTeamAttendance(mToken,
                        Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""),params);
            }
            else if(from_last.equalsIgnoreCase("GroupAttendance")){
                call = mApiInterface.getTeamAttendanceForGroup(mToken,
                        Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""),params);
            }



            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Object mObject = response.body();
                    if (mObject != null) {
                        onSuccessResponse(mObject);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    onFailureResponse(t);
                }
            });

        } else {
            // No Internet connection available
            onNoInternetConnection();
        }
    }




    /**
     * //===========================( callPayslipAPI data)==============================//
     */
    public void callPayslipAPI(String mToken , AttendanceModelParams params) {

        // Check for internet connection
        if (CommonUtils.getInstance().isNetworkAvailable(mContext)) {

            Call<String> call = null;

            call = mApiInterface.getPayslip(mToken,
                    Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""),params);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Object mObject = response.body();
                    if (mObject != null) {
                        onSuccessResponse(mObject);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                    onFailureResponse(t);
                }
            });

        } else {
            // No Internet connection available
            onNoInternetConnection();
        }
    }




    /** //for attendance in detail
     * //===========================( get Attendance API )==============================//
     */
    public void callGetAttendanceInDetailAPI(String mToken , AttendanceInDetailModelParams params) {

        if (CommonUtils.getInstance().isNetworkAvailable(mContext)) {
            // Call Webservice
            Call<String> call = null;

            call = mApiInterface.getAttendanceInDetail(mToken,
                        Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""),params);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Object mObject = response.body();
                    if (mObject != null) {
                        onSuccessResponse(mObject);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    onFailureResponse(t);
                }
            });

        } else {
            // No Internet connection available
            onNoInternetConnection();
        }
    }





    /** //for team leave
     * //===========================( get GetTeamLeaveAPI API )==============================//
     */
    public void callGetTeamLeaveAPI(String mToken , TeamOrGroupLeaveModelParams params , String from_last) {

        // Check for internet connection
        if (CommonUtils.getInstance().isNetworkAvailable(mContext)) {
            // Internet connection available
            // Call Webservice
            Call<String> call = null;
            if(from_last.equalsIgnoreCase("MyAttendance")){
                /*call = mApiInterface.getAttendance(mToken,
                        Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""),params);*/
            }
            else if(from_last.equalsIgnoreCase("TeamAttendance")){
                call = mApiInterface.getTeamLeave(mToken,
                        Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""),params);
            }
            else if(from_last.equalsIgnoreCase("GroupAttendance")){
                call = mApiInterface.getGroupLeave(mToken,
                        Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""),params);
            }



            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Object mObject = response.body();
                    if (mObject != null) {
                        onSuccessResponse(mObject);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    onFailureResponse(t);
                }
            });

        } else {
            // No Internet connection available
            onNoInternetConnection();
        }
    }



    /** //for my attandence /group attendance /team attandence
     * //===========================( get Attendance API )==============================//
     */
    public void callLeaveCardAPI(String mToken , LeaveCardParams params ) {


        // Check for internet connection
        if (CommonUtils.getInstance().isNetworkAvailable(mContext)) {

            Call<String> call = null;

                call = mApiInterface.getLeaveCard(mToken,
                        Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""),params);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Object mObject = response.body();
                    if (mObject != null) {
                        onSuccessResponse(mObject);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    onFailureResponse(t);
                }
            });

        } else {
            // No Internet connection available
            onNoInternetConnection();
        }
    }




    /** //
     * //===========================( manage leave API )==============================//
     */
    public void callManageLeaveAPI(String mToken , ManageLeaveParams params ) {


        // Check for internet connection
        if (CommonUtils.getInstance().isNetworkAvailable(mContext)) {

            Call<String> call = null;

            call = mApiInterface.getManageLeave(mToken,
                    Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""),params);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Object mObject = response.body();
                    if (mObject != null) {
                        onSuccessResponse(mObject);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    onFailureResponse(t);
                }
            });

        } else {
            // No Internet connection available
            onNoInternetConnection();
        }
    }







    /** //
     * //===========================( manage leave API )==============================//
     */
    public void callResetPasswordAPI(String mToken , ResetPasswordParams params ) {


        // Check for internet connection
        if (CommonUtils.getInstance().isNetworkAvailable(mContext)) {

            Call<String> call = null;

            call = mApiInterface.resetPassword(mToken,
                    Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""),params);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Object mObject = response.body();
                    if (mObject != null) {
                        onSuccessResponse(mObject);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t)
                {
                    onFailureResponse(t);
                }
            });

        } else {
            // No Internet connection available
            onNoInternetConnection();
        }
    }







    /**
     * //===========================( get Team Menmber API for Attendance )==============================//
     */
    public void callGetTeamMemberAPI(String mToken , TeamMemberModelParams params, String call_team_or_group) {

        // Check for internet connection
        if (CommonUtils.getInstance().isNetworkAvailable(mContext)) {

            // Internet connection available
            // Call Webservice
            Call<String> call = null;

            if(call_team_or_group.equalsIgnoreCase("team")){
                call = mApiInterface.getTeamMember(mToken,
                        Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""),params);
            }
            else {
                call = mApiInterface.getTeamMemberForGroup(mToken,
                        Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""),params);
            }


            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Object mObject = response.body();
                    if (mObject != null) {
                        onSuccessResponse(mObject);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    onFailureResponse(t);
                }
            });

        } else {
            // No Internet connection available
            onNoInternetConnection();
        }
    }



    /**
     * //===========================( get Team Menmber API for Leave )==============================//
     */
    public void callGetTeamMemberAPILeave(String mToken ,TeamMemberModelParams params, String call_team_or_group) {

        // Check for internet connection
        if (CommonUtils.getInstance().isNetworkAvailable(mContext)) {

            // Internet connection available
            // Call Webservice
            Call<String> call = null;

            if(call_team_or_group.equalsIgnoreCase("team")){
                call = mApiInterface.getTeamMemberForTeamLeave(mToken,
                        Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""),params);
            }
            else {
                call = mApiInterface.getTeamMemberForGroupLeave(mToken,
                        Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""),params);
            }


            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Object mObject = response.body();
                    if (mObject != null) {
                        onSuccessResponse(mObject);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                    onFailureResponse(t);
                }
            });

        } else {
            // No Internet connection available
            onNoInternetConnection();
        }
    }

    /**
     * //===========================( getdocument from server API )==============================//
     */
    public void callGetDocumentAPI(String mToken , UploadDocsParams params) {

        // Check for internet connection
        if (CommonUtils.getInstance().isNetworkAvailable(mContext)) {

            Call<String> call = null;
            call = mApiInterface.getDocumentUpload(mToken,
                    Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""),params);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Object mObject = response.body();
                    if (mObject != null) {
                        onSuccessResponse(mObject);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    onFailureResponse(t);
                }
            });

        } else {
            // No Internet connection available
            onNoInternetConnection();
        }
    }






    /**
     * //===========================( document upload API )==============================//
     */
    public void callUploafDocumentAPI(String mToken ,  Map<String, RequestBody> map) {

        // Check for internet connection
        if (CommonUtils.getInstance().isNetworkAvailable(mContext)) {

         /*   RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), params.getFileName());
            MultipartBody.Part multipartBody = MultipartBody.Part.createFormData
                    (params.getFileName().substring(params.getFileName().lastIndexOf("/") + 1),   //file name
                    params.getFileName(),requestFile); //actual file path*/


            Call<String> call = null;
            call = mApiInterface.submitDocument(mToken,
                    Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""),map

                 /*   //params
                    Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""),
                   // params.getFlag(),
                    params.getEmployeeFK(),
                   // params.getLoginEployeeFk(),
                   // params.getImageAsString(),
                   // params.getFileName().substring(params.getFileName().lastIndexOf("/") + 1),
                   // params.getDocumentDetailPK(),
                   // params.getEmpDocument(), //doc name aadhar, pan, id etc

                    multipartBody  */

            );



            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Object mObject = response.body();
                    if (mObject != null) {
                        onSuccessResponse(mObject);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                    onFailureResponse(t);
                }
            });

        } else {
            // No Internet connection available
            onNoInternetConnection();
        }
    }













    /**
     * //===========================( update profile API )==============================//
     */
    public void callUpdateMyProfileAPI(String mToken , UpdateUserProfileParams params) {

        // Check for internet connection
        if (CommonUtils.getInstance().isNetworkAvailable(mContext)) {

            Call<String> call = null;
            call = mApiInterface.updateMyProfile(mToken,
                    Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""),params);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Object mObject = response.body();
                    if (mObject != null) {
                        onSuccessResponse(mObject);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                    onFailureResponse(t);
                }
            });

        } else {
            // No Internet connection available
            onNoInternetConnection();
        }
    }

















    /**
     * //===========================( get common detail API )==============================//
     */
    public void callGetCommonDetailAPI(String mToken , CommonDetailModelParams params) {

        // Check for internet connection
        if (CommonUtils.getInstance().isNetworkAvailable(mContext)) {

            Call<String> call = null;
            call = mApiInterface.getCommonDetail(mToken,
                        Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""),params);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Object mObject = response.body();
                    if (mObject != null) {
                        onSuccessResponse(mObject);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    onFailureResponse(t);
                }
            });

        } else {
            // No Internet connection available
            onNoInternetConnection();
        }
    }

    /**
     * //===========================( get common detail API )==============================//
     */
    public void callGetBindWeekOff(String mToken , BindWeekOffParams params) {

        // Check for internet connection
        if (CommonUtils.getInstance().isNetworkAvailable(mContext)) {

            Call<String> call = null;
            call = mApiInterface.getWeekOff(mToken,
                    Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""),params);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Object mObject = response.body();
                    if (mObject != null) {
                        onSuccessResponse(mObject);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    onFailureResponse(t);
                }
            });

        } else {
            // No Internet connection available
            onNoInternetConnection();
        }
    }


    /**
     * //===========================( get Add deviation API )==============================//
     */
    public void callAddDeviationAPI(String mToken , AddDeviationParams params) {

        // Check for internet connection
        if (CommonUtils.getInstance().isNetworkAvailable(mContext)) {

            Call<String> call = null;
            call = mApiInterface.addDeviation(mToken,
                    Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""),params);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Object mObject = response.body();
                    if (mObject != null) {
                        onSuccessResponse(mObject);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                    onFailureResponse(t);
                }
            });

        } else {
            // No Internet connection available
            onNoInternetConnection();
        }
    }


    /**
     * //===========================( is Doc require or not API )==============================//
     */
    public void callIsDocRequireAPI(String mToken , IsDocumentRequireParams params) {

        // Check for internet connection
        if (CommonUtils.getInstance().isNetworkAvailable(mContext)) {

            Call<String> call = null;
            call = mApiInterface.isDocRequire(mToken,
                    Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""),params);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Object mObject = response.body();
                    if (mObject != null) {
                        onSuccessResponse(mObject);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                    onFailureResponse(t);
                }
            });

        } else {
            // No Internet connection available
            onNoInternetConnection();
        }
    }





    /**
     * //===========================( is leave applied or not API )==============================//
     */
    public void callIsLeaveApplyAPI(String mToken , IsLeaveAppliedParams params) {

        // Check for internet connection
        if (CommonUtils.getInstance().isNetworkAvailable(mContext)) {

            Call<String> call = null;
            call = mApiInterface.isLeaveApply(mToken,
                    Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""),params);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Object mObject = response.body();
                    if (mObject != null) {
                        onSuccessResponse(mObject);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                    onFailureResponse(t);
                }
            });

        } else {
            // No Internet connection available
            onNoInternetConnection();
        }
    }



    /**
     * //===========================( get Add deviation API )==============================//
     */
    public void callApplyLeaveAPI(String mToken , ApplyLeaveParams params) {

        // Check for internet connection
        if (CommonUtils.getInstance().isNetworkAvailable(mContext)) {

            Call<String> call = null;
            call = mApiInterface.applyLeave(mToken,
                    Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""),params);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Object mObject = response.body();
                    if (mObject != null) {
                        onSuccessResponse(mObject);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                    onFailureResponse(t);
                }
            });

        } else {
            // No Internet connection available
            onNoInternetConnection();
        }
    }



    /**
     * //===========================( get compoff leave API )==============================//
     */
    public void callGetCompoffLeaveAPI(String mToken , CompoffLeaveParams params) {

        // Check for internet connection
        if (CommonUtils.getInstance().isNetworkAvailable(mContext)) {

            Call<String> call = null;
            call = mApiInterface.getCompoffLeave(mToken,
                    Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""),params);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Object mObject = response.body();
                    if (mObject != null) {
                        onSuccessResponse(mObject);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                    onFailureResponse(t);
                }
            });

        } else {
            // No Internet connection available
            onNoInternetConnection();
        }
    }


    /**
     * //===========================( get sendwhich for form date API )==============================//
     */
    public void checkLeaveForSandwhich(String mToken , SandwichParams params ,String from_last) {

        // Check for internet connection
        if (CommonUtils.getInstance().isNetworkAvailable(mContext)) {

            Call<String> call = null;
            if(from_last.equalsIgnoreCase("from_date")){
                call = mApiInterface.checkFromSandwich(mToken,
                        Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""),params);
            }
            else{
                //to date
                call = mApiInterface.checkToSandwich(mToken,
                        Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""),params);
            }


            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Object mObject = response.body();
                    if (mObject != null) {
                        onSuccessResponse(mObject);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                    onFailureResponse(t);
                }
            });

        } else {
            // No Internet connection available
            onNoInternetConnection();
        }
    }



    /**
     * //===========================( get Add deviation API )==============================//
     */
    public void callCancleLeaveAPI(String mToken , CancleLeaveParams params) {

        // Check for internet connection
        if (CommonUtils.getInstance().isNetworkAvailable(mContext)) {

            Call<String> call = null;
            call = mApiInterface.cancleLeave(mToken,
                    Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""),params);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Object mObject = response.body();
                    if (mObject != null) {
                        onSuccessResponse(mObject);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                    onFailureResponse(t);
                }
            });

        } else {
            // No Internet connection available
            onNoInternetConnection();
        }
    }


    /**
     * //===========================( get Reporting Person)==============================//
     */
    public void callGetReportingPersonAPI(String mToken , LeaveCardParams params) {

        // Check for internet connection
        if (CommonUtils.getInstance().isNetworkAvailable(mContext)) {

            Call<String> call = null;

            call = mApiInterface.getReportingPerson(mToken,
                    Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""),params);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Object mObject = response.body();
                    if (mObject != null) {
                        onSuccessResponse(mObject);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    onFailureResponse(t);
                }
            });

        } else {
            // No Internet connection available
            onNoInternetConnection();
        }
    }


    /**
     * //===========================( get my profile data)==============================//
     */
    public void callMyprofileAPI(String mToken , MyprofileParams params) {

        // Check for internet connection
        if (CommonUtils.getInstance().isNetworkAvailable(mContext)) {

            Call<String> call = null;

            call = mApiInterface.getMyprofile(mToken,
                    Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""),params);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Object mObject = response.body();
                    if (mObject != null) {
                        onSuccessResponse(mObject);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    onFailureResponse(t);
                }
            });

        } else {
            // No Internet connection available
            onNoInternetConnection();
        }
    }





    /**
     * //===========================( get Add deviation API for TEAM )==============================//
     */
    public void callAddDeviationTeamAPI(String mToken ,AddDeviationTeamParams params) {

        // Check for internet connection
        if (CommonUtils.getInstance().isNetworkAvailable(mContext)) {

            Call<String> call = null;
            call = mApiInterface.addDeviationTeam(mToken,
                    Pref.getValue(mContext, Constant.PREF_MOBILE_URL ,""),params);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Object mObject = response.body();
                    if (mObject != null) {
                        onSuccessResponse(mObject);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                    onFailureResponse(t);
                }
            });

        } else {
            // No Internet connection available
            onNoInternetConnection();
        }
    }




    /**
     * //===========================( get pointing Url API )==============================//
     */
    public void callGetPointingUrlAPI(String OTP) {
        // Check for internet connection
       if (CommonUtils.getInstance().isNetworkAvailable(mContext)) {
            // Internet connection available
            // Call Webservice
            Call<PointingUrlModel> call = mApiInterfaceForUrl.getPointingUrl(OTP);
            call.enqueue(new Callback<PointingUrlModel>() {
                @Override
                public void onResponse(Call<PointingUrlModel> call, Response<PointingUrlModel> response) {
                    Object mObject = response.body();
                    if (mObject != null) {
                        onSuccessResponse(mObject);
                    }
                }
                @Override
                public void onFailure(Call<PointingUrlModel> call, Throwable t) {
                    onFailureResponse(t);
                }
            });

        } else {
           //  No Internet connection available
            onNoInternetConnection();
        }
    }


}
