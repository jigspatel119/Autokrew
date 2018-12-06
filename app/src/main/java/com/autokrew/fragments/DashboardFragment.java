package com.autokrew.fragments;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.autokrew.R;
import com.autokrew.activity.MainActivity;
import com.autokrew.activity.QRScanActivity;
import com.autokrew.activity.QRScanActivity2;
import com.autokrew.activity.SigninActivity;
import com.autokrew.activity.WebViewActivity;
import com.autokrew.adapter.AnnouncementAdapter;
import com.autokrew.adapter.BirthdayAdapter;
import com.autokrew.adapter.DashbordModuleAdapter;
import com.autokrew.dialogs.AttendanceDetailDialog;
import com.autokrew.dialogs.GreetingsDialog;
import com.autokrew.dialogs.ResetPasswordDialog;
import com.autokrew.dialogs.VersionUpgradeDialog;
import com.autokrew.interfaces.RecyclerViewDashBoardListener;
import com.autokrew.listner.RecyclerItemClickListener;
import com.autokrew.models.ApplyAttendanceParam;
import com.autokrew.models.AttendanceDetailModel;
import com.autokrew.models.DashboardModuleModel;
import com.autokrew.models.DashbordModel;
import com.autokrew.models.DashbordModelParam;
import com.autokrew.models.DeviceTokenParam;
import com.autokrew.models.LeaveDetailModel;
import com.autokrew.models.LetestVersionModel;
import com.autokrew.models.LoginModel;
import com.autokrew.models.LoginModelParams;
import com.autokrew.models.OutSideAttendanceModel;
import com.autokrew.network.ApiListener;
import com.autokrew.network.WebServices;
import com.autokrew.utils.CommonUtils;
import com.autokrew.utils.Constant;

import com.autokrew.utils.FusedLocationAPIService;
import com.autokrew.utils.Permissions;
import com.autokrew.utils.Pref;
import com.autokrew.utils.PreferenceHelper;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.fabric.sdk.android.services.common.Crash;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;


public class DashboardFragment extends Fragment implements RecyclerViewDashBoardListener, ApiListener
       {

    //General Variables
    private static final String TAG = DashboardFragment.class.getSimpleName();
    Activity mActivity = getActivity();


    //Root view of fragment
    View root_view;
    RecyclerView rv_birthday_today, rv_announcements, rv_top_modules;

    BirthdayAdapter mBirthdateAdapter;
    AnnouncementAdapter mAnnocumentAdapter;
    DashbordModuleAdapter mDashbordModuleAdapter;

    CardView card_view2, card_view1, card_view_birthday, card_view_announcement;

    //LinearLayout ll_birthday,ll_announcement;

    Dialog dialog;


    FloatingActionMenu materialDesignFAM;
    FloatingActionButton fab_attendance, fab_qrcode;

    //dialog for sms/phonecall
    GreetingsDialog mDialog;
    Button btn_date, btn_announcement;
    List<DashboardModuleModel> mModuleList;

    VersionUpgradeDialog mVersionDialog;

    AttendanceDetailDialog mAttendanceDetailDialog;
    String mLat, mLong, mAddress;


    DashbordModel model;
    DashboardModuleModel mModel;
    AttendanceDetailModel attendanceDetailModel;
    LeaveDetailModel leaveDetailModel;
    String mToken;

    OutSideAttendanceModel mOutsideSideAttendanceModel;
    LetestVersionModel mLetestVersionModel;
    private PreferenceHelper mPreferenceHelper;

    String from_last = "";

    String currentVersion;
    int is_outside_allow;
    private static final int LOCATION_REQUEST_CODE = 1;

    FirebaseRemoteConfig firebaseRemoteConfig;
    long cacheExpiration = 3600;

    String IMEINumber = "";


    //=========================
    private LocationRequest mLocationRequest;
    private long LOCATION_UPDATE_INTERVAL = 2000;// 2 sec  [60000 = 1 min]
    private GoogleApiClient mGoogleApiClient;

    //==========================

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root_view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        mPreferenceHelper = new PreferenceHelper(getActivity());


        getData();
        findView(root_view);
        setData(root_view);
        setListner();


        return root_view;
    }

    private void getData() {
        mToken = Pref.getValue(getActivity(), Constant.PREF_TOKEN, "");

    }

    protected void displayTuto() {

        // single example
        new MaterialShowcaseView.Builder(getActivity())
                .setTarget(materialDesignFAM)
                .setDismissText("GOT IT!")
                .setContentText("You can add attendance from here.")
                .setDelay(1000) // optional but starting animations immediately in onCreate can make them choppy
                .singleUse("fab") // provide a unique ID used to ensure it is only shown once
                .show();

    }

    private void findView(View v) {


        rv_birthday_today = (RecyclerView) v.findViewById(R.id.rv_birthday_today);
        rv_announcements = (RecyclerView) v.findViewById(R.id.rv_announcements);
        rv_top_modules = (RecyclerView) v.findViewById(R.id.rv_top_modules);


        card_view2 = (CardView) v.findViewById(R.id.card_view2);
        card_view1 = (CardView) v.findViewById(R.id.card_view1);

        // ll_birthday = (LinearLayout)v.findViewById(R.id.ll_birthday);
        // ll_announcement = (LinearLayout)v.findViewById(R.id.ll_announcement);

        card_view_birthday = (CardView) v.findViewById(R.id.card_view_birthday);
        card_view_announcement = (CardView) v.findViewById(R.id.card_view_announcement);

        btn_date = (Button) v.findViewById(R.id.btn_date);
        btn_announcement = (Button) v.findViewById(R.id.btn_announcement);



        /*Typeface copperplateGothicLight = Typeface.createFromAsset(getAppContext().getAssets(), "GillSans-SemiBold.ttf");
        btn_date.setTypeface(copperplateGothicLight);
        btn_announcement.setTypeface(copperplateGothicLight);*/

    }

    private void setData(View v) {

        checkVersionUpgrade();
        setupRecyclerView();
        is_outside_allow = Pref.getValue(getActivity(), "is_outside_allow", 0);
        setupFAB(v);
        displayTuto();

        //check for very first time reset password

      /*  if(String.valueOf(Pref.getValue(getActivity(),Constant.PREF_RESPONSE_CODE ,0)).equalsIgnoreCase("7")){
            ResetPasswordDialog mDialog = new ResetPasswordDialog(getActivity(),"dashbord_frag");
            mDialog.setCancelable(false);
            mDialog.setCanceledOnTouchOutside(false);
            mDialog.show();
        }*/

    }

    private void checkVersionUpgrade() {
       /* from_last = "LatestVersion";
        new WebServices(getActivity()*//* ActivityContext *//*, DashboardFragment.this *//* ApiListener *//*, true *//* show progress dialog *//*,
                false*//*for new retrofitclient*//*).
                callLetestVersionAPI();*/

        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();

        FirebaseRemoteConfigSettings remoteConfigSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(true)
                .build();
        firebaseRemoteConfig.setConfigSettings(remoteConfigSettings);
        firebaseRemoteConfig.setDefaults(R.xml.remote_config_defaults);


        //expire the cache immediately for development mode.
        if (firebaseRemoteConfig.getInfo().getConfigSettings().isDeveloperModeEnabled()) {
            cacheExpiration = 0;
        }

        firebaseRemoteConfig.fetch(0).addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // Once the config is successfully fetched it must be activated before newly fetched
                    // values are returned.
                    firebaseRemoteConfig.activateFetched();

                    String version_name = firebaseRemoteConfig.getString(getString(R.string.version_name));

                    try {
                        PackageInfo pInfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
                        String version = pInfo.versionName;
                        if (!version_name.equalsIgnoreCase(version)) {
                            //display version upgrade dialog
                            mVersionDialog = new VersionUpgradeDialog(getActivity());
                            mVersionDialog.setCancelable(false);
                            mVersionDialog.setCanceledOnTouchOutside(false);
                            mVersionDialog.show();
                        } else {

                        }

                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e("TAG", "Fetch failed");
                }
            }
        });


    }

    private void callDashbordDetailAPI(String flag) {
        DashbordModelParam params = new DashbordModelParam();
        params.setFlag(flag);
        params.setEmployeeFK(String.valueOf(Pref.getValue(getActivity(), Constant.PREF_SESSION_EMPLOYEE_FK, 0)));

        from_last = flag;
        new WebServices(getActivity()/* ActivityContext */, DashboardFragment.this /* ApiListener */, false /* show progress dialog */,
                true/*for new retrofitclient*/).
                callDashboardDetailAPI(mToken, params);
        //  }
    }

    private void setupFAB(View v) {

        materialDesignFAM = (FloatingActionMenu) v.findViewById(R.id.material_design_android_floating_action_menu);
        fab_attendance = (FloatingActionButton) v.findViewById(R.id.fab_attendance);
        fab_qrcode = (FloatingActionButton) v.findViewById(R.id.fab_qrcode);


        /*if (is_outside_allow == 0) {
            fab_attendance.setVisibility(View.GONE);
        } else {
            fab_attendance.setVisibility(View.VISIBLE);
        }*/

        fab_attendance.setVisibility(View.VISIBLE);

        //materialDesignFAM.setVisibility(View.GONE);
        fab_attendance.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked


                if (!CommonUtils.isGpsEnabled(getActivity())) {
                    CommonUtils.displayLocationSettingsRequest(getActivity());
                } else {
                  //  if (Permissions.getInstance().isReadPhoneStatePermissionGranted(getActivity())) {
                        if (Permissions.getInstance().isLocationPermissionGranted(getActivity())) {
                            requestPermissionForLocation();
                        }
                   // }

                }

            }
        });


        fab_qrcode.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked
                // Crashlytics.getInstance().crash(); // Force a crash
                if (!CommonUtils.isGpsEnabled(getActivity())) {
                    CommonUtils.displayLocationSettingsRequest(getActivity());
                } else {
                    if (Permissions.getInstance().isCameraPermissionGranted(getActivity())) {
                        if (Permissions.getInstance().isReadPhoneStatePermissionGranted(getActivity())) {
                            //open camera and api calls for qrcode..
                            CommonUtils.getInstance().startActivity(getActivity(), QRScanActivity.class);
                            getActivity().overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_scale);
                            materialDesignFAM.close(true);
                        }
                    }
                }
            }
        });

    }


    private void requestPermissionForLocation() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                startLocationService();

            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        LOCATION_REQUEST_CODE);
            }
        } else {
            startLocationService();
        }
    }

    private void startLocationService() {

        if (!CommonUtils.isMyServiceRunning(getActivity(), FusedLocationAPIService.class)) {
            Intent intent = new Intent(getActivity(), FusedLocationAPIService.class);
            getActivity().startService(intent);
        }


        if(CommonUtils.lattitude != 0.0 && CommonUtils.logitude != 0.0 ){
            if (String.valueOf(CommonUtils.lattitude) != null && String.valueOf(CommonUtils.logitude) != null) {
                mLat = String.valueOf(CommonUtils.lattitude);
                mLong = String.valueOf(CommonUtils.logitude);
                mAddress = CommonUtils.getAddressFromLatLong(getActivity(),
                        CommonUtils.lattitude,
                        CommonUtils.logitude);
                IMEINumber = CommonUtils.getInstance().getIMEI(getActivity());

              //  CommonUtils.getInstance().displayToast(getActivity(),"mAddress >> " +mAddress);

                if (IMEINumber != null && IMEINumber.length() > 0) {
                    callOutSideAttendanceFromMobileAppAPI("", mLat, mLong, mAddress, IMEINumber);
                    materialDesignFAM.close(true);
                    CommonUtils.lattitude = 0.0 ;
                    CommonUtils.logitude = 0.0 ;
                }
            }
        }
        else{
            CommonUtils.getInstance().displayToast(getActivity(),"gps is enabling! Please Wait.");
        }

    }

    private void stopLocationService() {

        if (CommonUtils.isMyServiceRunning(getActivity(), FusedLocationAPIService.class)) {
            Log.e(TAG, "stopLocationService: called ");
            Intent intent = new Intent(getActivity(), FusedLocationAPIService.class);
            getActivity().stopService(intent);
        }
    }

    private void callOutSideAttendanceFromMobileAppAPI(String QR_text, String mLat, String mLong, String mAddress, String IMEINumber) {

        ApplyAttendanceParam params = new ApplyAttendanceParam();
        params.setAtt_Lat(mLat);
        params.setAtt_Long(mLong);
        params.setIsQrCode("0");//change flag
        params.setQrCode(QR_text);
        params.setAtt_PhisicalAddress(mAddress);
        params.setEmployeePk(String.valueOf(Pref.getValue(getActivity(), Constant.PREF_SESSION_EMPLOYEE_FK, 0)));

        params.setIMEINumber(IMEINumber);

        from_last = "OutSideAttendance";
        // String mToken = Pref.getValue(getActivity(), Constant.PREF_TOKEN, "");

        new WebServices(getActivity()/* ActivityContext */, DashboardFragment.this /* ApiListener */, true /* show progress dialog */,
                true/*for new retrofitclient*/).
                callOutSideAttendanceFromMobileAppAPI(mToken, params);
        //  }
    }

    private void setupRecyclerView() {

        rv_top_modules.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rv_top_modules.setHasFixedSize(true);

        //birthday recycler view
        rv_birthday_today.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rv_birthday_today.setHasFixedSize(true);

        //announcement recycler view
        rv_announcements.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_announcements.setHasFixedSize(true);

        rv_announcements.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // do whatever
                        String title = model.getTable12().get(position).getTitle();
                        int mNewsPk = model.getTable12().get(position).getNewsPK();
                        //Create the bundle
                        Bundle bundle = new Bundle();
                        bundle.putString("title", title);
                        bundle.putInt("mNewsPk", mNewsPk);

                        CommonUtils.getInstance().startActivity(getActivity(), WebViewActivity.class, bundle);
                        getActivity().overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_scale);

                    }
                })
        );

        dialog = new Dialog(getActivity(), R.style.progressDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progress_dialog);
        dialog.setCancelable(false);
        //Show progress dialog
        dialog.show();

        //check device tocken and send it to server
        //String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        sendDeviceTocken("refreshedToken");


        //1st api calls
        callDashbordDetailAPI("Employee");
    }

    private void sendDeviceTocken(String flag) {

        DeviceTokenParam params = new DeviceTokenParam();
        params.setFlag(flag);
        params.setDeviceId(FirebaseInstanceId.getInstance().getToken());
        params.setEmployeeFK(String.valueOf(Pref.getValue(getActivity(), Constant.PREF_SESSION_EMPLOYEE_FK, 0)));

        new WebServices(getActivity()/* ActivityContext */, DashboardFragment.this /* ApiListener */, false /* show progress dialog */,
                true/*for new retrofitclient*/).
                callSendDeviceTockenAPI(mToken, params);
    }

    private void setListner() {


    }


    @Override
    public void recyclerViewListClicked(View v, int position) {

        if(mDialog!=null && mDialog.isShowing()){
            //check for multiple dialogs open
        }
        else {

            mDialog = new GreetingsDialog(getActivity(), position);
            mDialog.setCancelable(false);
            mDialog.setCanceledOnTouchOutside(true);
            mDialog.show();
        }

    }

    @Override
    public void recyclerViewModuleClicked(View v, int position) {

        if (mModuleList.get(position).getTitle().equalsIgnoreCase("Pending Attendance \nApproval")) {
            //CommonUtils.getInstance().displayToast(getActivity(), mModuleList.get(position).getTitle());
            callDashbordDetailAPI("AttendanceDetail");
        } else if (mModuleList.get(position).getTitle().equalsIgnoreCase("Pending Leave \nApproval")) {
            //CommonUtils.getInstance().displayToast(getActivity(), mModuleList.get(position).getTitle());
            callDashbordDetailAPI("LeaveDetail");
        }
    }


    @Override
    public void onApiSuccess(Object mObject) {

        //for token expired
        if (mObject instanceof LoginModel) {
            LoginModel model = (LoginModel) mObject;
            Log.e("", "onApiSuccess: token >>  " + model.getToken());
            if (model.getResponse().equalsIgnoreCase("Invalid Username or Password, Please try again.")) {
                //redirect to login screen
                Pref.setValue(getActivity(), "auto_login", "false");
                CommonUtils.getInstance().startActivityWithoutStack(getActivity(), SigninActivity.class);
            } else {
                //success response..
                if (model.getToken() != null) {
                    //add bearer_ (space before token....)
                    Pref.setValue(getActivity(), Constant.PREF_TOKEN, "bearer " + model.getToken());
                    Pref.setValue(getActivity(), Constant.PREF_SESSION_EMPLOYEE_FK, model.getEmployeeFK());

                    Pref.setValue(getActivity(), Constant.PREF_ROLE_FK, model.getRoleFK());
                    //api calls for get user profile
                    //getUserProfile(model.getEmployeeFK());

                    CommonUtils.getInstance().startActivityWithoutStack(getActivity(), MainActivity.class);
                } else {
                    //
                    CommonUtils.getInstance().displayToast(getActivity(), model.getResponse());
                }
            }

        }


        //for existing token..
        else if (mObject instanceof String) {
            try {
                JSONObject jsonObj = new JSONObject(mObject.toString());
                //parse original data
                Log.e("", "onApiSuccess: dashboard json >>  " + jsonObj.toString());
                Gson gson = new Gson();

                if (from_last.equalsIgnoreCase("Employee")) {
                    model = gson.fromJson(mObject.toString(), DashbordModel.class);

                    // ll_announcement.setVisibility(View.VISIBLE);
                    // ll_birthday.setVisibility(View.VISIBLE);

                    card_view_birthday.setVisibility(View.VISIBLE);
                    card_view_announcement.setVisibility(View.VISIBLE);

                    //set announcement adapter
                    if (model.getTable12().size() > 0) {
                        rv_announcements.setVisibility(View.VISIBLE);
                        card_view2.setVisibility(View.GONE);
                        mAnnocumentAdapter = new AnnouncementAdapter(getActivity(), model, DashboardFragment.this);
                        rv_announcements.setAdapter(mAnnocumentAdapter);
                        rv_announcements.setNestedScrollingEnabled(false);
                    } else {
                        rv_announcements.setVisibility(View.GONE);
                        card_view2.setVisibility(View.VISIBLE);
                    }

                    if (model.getTable4().size() > 0) {
                        rv_birthday_today.setVisibility(View.VISIBLE);
                        card_view1.setVisibility(View.GONE);
                        mBirthdateAdapter = new BirthdayAdapter(getActivity(), model, DashboardFragment.this);
                        rv_birthday_today.setAdapter(mBirthdateAdapter);
                        rv_birthday_today.setNestedScrollingEnabled(false); //for smooth nested scroll
                    } else {
                        rv_birthday_today.setVisibility(View.GONE);
                        card_view1.setVisibility(View.VISIBLE);
                    }


                    if (model.getTable11().get(0).getHRRights() == 1) {
                        //show
                        rv_top_modules.setVisibility(View.VISIBLE);
                        mModuleList = new ArrayList<>();
                        //table = Attendance  , table 1 = Leave
                        for (int i = 0; i < 2; i++) {
                            mModel = new DashboardModuleModel();
                            if (i == 0) {
                                mModel.setTitle("Pending Attendance \nApproval");
                                mModel.setCount(model.getTable().get(0).getPendingAttendance());
                            } else if (i == 1) {
                                mModel.setTitle("Pending Leave \nApproval");
                                mModel.setCount(model.getTable1().get(0).getPendingLeave());
                            }

                            mModuleList.add(mModel);
                        }
                        mDashbordModuleAdapter = new DashbordModuleAdapter(getActivity(), mModuleList, DashboardFragment.this);

                        rv_top_modules.setAdapter(mDashbordModuleAdapter);
                        rv_top_modules.setNestedScrollingEnabled(false); //for smooth nested scroll

                    } else {
                        //hide
                        rv_top_modules.setVisibility(View.GONE);
                    }

                } else if (from_last.equalsIgnoreCase("AttendanceDetail")) {

                    if(mAttendanceDetailDialog!=null && mAttendanceDetailDialog.isShowing()){
                        //check for multiple dialogs open
                    }
                    else{
                    attendanceDetailModel = gson.fromJson(mObject.toString(), AttendanceDetailModel.class);
                    mAttendanceDetailDialog = new AttendanceDetailDialog(getActivity(), attendanceDetailModel, null, "Pending Attendance Approval", "Pending Attendance");
                    mAttendanceDetailDialog.setCancelable(false);
                    mAttendanceDetailDialog.setCanceledOnTouchOutside(true);
                    mAttendanceDetailDialog.show();
                    }

                } else if (from_last.equalsIgnoreCase("LeaveDetail")) {

                    if(mAttendanceDetailDialog!=null && mAttendanceDetailDialog.isShowing()){
                        //check for multiple dialogs open
                    }
                    else {

                        leaveDetailModel = gson.fromJson(mObject.toString(), LeaveDetailModel.class);
                        mAttendanceDetailDialog = new AttendanceDetailDialog(getActivity(), null, leaveDetailModel, "Pending Leave Approval", "Pending Leave");
                        mAttendanceDetailDialog.setCancelable(false);
                        mAttendanceDetailDialog.setCanceledOnTouchOutside(true);
                        mAttendanceDetailDialog.show();

                    }

                } else if (from_last.equalsIgnoreCase("OutSideAttendance")) {

                    mOutsideSideAttendanceModel = gson.fromJson(mObject.toString(), OutSideAttendanceModel.class);
                    CommonUtils.getInstance().displayToast(getActivity(), "" + mOutsideSideAttendanceModel.getTable().get(0).getResult());
                    stopLocationService();
                }


                //dismiss dialog
                dialog.dismiss();

            } catch (JSONException e) {
                e.printStackTrace();
                //dismiss dialog
                if (dialog != null) {
                    if (dialog.isShowing())
                        dialog.dismiss();
                }

            }
        } else if (mObject instanceof LetestVersionModel) {
            LetestVersionModel model = (LetestVersionModel) mObject;

            try {

                Log.e(TAG, "onApiSuccess:  >>>> " + model.getData().getVersion());

                PackageInfo pInfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
                int verCode = pInfo.versionCode;

                if (model.getStatus().equals("Success")) {
                    if (Integer.parseInt(model.getData().getVersion()) > verCode) {
                        //show popup
                        mVersionDialog = new VersionUpgradeDialog(getActivity());
                        mVersionDialog.setCancelable(false);
                        mVersionDialog.setCanceledOnTouchOutside(false);
                        mVersionDialog.show();


                    } else {
                        //nothing to do

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    @Override
    public void onApiFailure(Throwable mThrowable) {

        Log.e(TAG, "onApiFailure:>>> ");
        //call login api
        //get new access token
        loginAPICalls();

    }

    private void loginAPICalls() {
        LoginModelParams params = new LoginModelParams();
        params.setUsername(Pref.getValue(getActivity(), "user_id", ""));
        params.setPassword(Pref.getValue(getActivity(), "user_password", ""));
        params.setMobileURL(Pref.getValue(getActivity(), Constant.PREF_MOBILE_URL, ""));

        new WebServices(getActivity()/* ActivityContext */, this /* ApiListener */, false /* show progress dialog */,
                true/*for new retrofitclient*/).
                callLoginAPI(params);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (Constant.LAST_ACTIVITY.equalsIgnoreCase("AttendanceDeviationActivity") || Constant.LAST_ACTIVITY.equalsIgnoreCase("GroupLeaveActivity")) {

            Constant.LAST_ACTIVITY = "";
            // callDashbordDetailAPI("Employee");
        }

    }






 /*   @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e(TAG, "onConnectionFailed: Called");
        buildGoogleApiClient();
    }*/


    @Override
    public void onDestroy() {
        super.onDestroy();

        stopLocationService();
        Log.e(TAG, "mGoogleApiClient: onDestroy : *****client disconnect***" );

    }
}
