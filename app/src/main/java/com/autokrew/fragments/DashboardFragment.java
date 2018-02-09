package com.autokrew.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
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
import com.autokrew.activity.SigninActivity;
import com.autokrew.activity.WebViewActivity;
import com.autokrew.adapter.AnnouncementAdapter;
import com.autokrew.adapter.BirthdayAdapter;
import com.autokrew.adapter.DashbordModuleAdapter;
import com.autokrew.dialogs.AttendanceDetailDialog;
import com.autokrew.dialogs.GreetingsDialog;
import com.autokrew.dialogs.VersionUpgradeDialog;
import com.autokrew.interfaces.RecyclerViewDashBoardListener;
import com.autokrew.listner.RecyclerItemClickListener;
import com.autokrew.models.ApplyAttendanceParam;
import com.autokrew.models.AttendanceDetailModel;
import com.autokrew.models.DashboardModuleModel;
import com.autokrew.models.DashbordModel;
import com.autokrew.models.DashbordModelParam;
import com.autokrew.models.LeaveDetailModel;
import com.autokrew.models.LetestVersionModel;
import com.autokrew.models.LoginModel;
import com.autokrew.models.LoginModelParams;
import com.autokrew.models.OutSideAttendanceModel;
import com.autokrew.network.ApiListener;
import com.autokrew.network.WebServices;
import com.autokrew.utils.CommonUtils;
import com.autokrew.utils.Constant;
import com.autokrew.utils.GPSTracker;
import com.autokrew.utils.Permissions;
import com.autokrew.utils.Pref;
import com.autokrew.utils.PreferenceHelper;
import com.crashlytics.android.Crashlytics;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.List;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;


public class DashboardFragment extends Fragment implements RecyclerViewDashBoardListener, ApiListener {

    //General Variables
    private static final String TAG = DashboardFragment.class.getSimpleName();
    Activity mActivity = getActivity();


    //Root view of fragment
    View root_view;
    RecyclerView rv_birthday_today, rv_announcements, rv_top_modules;

    BirthdayAdapter mBirthdateAdapter;
    AnnouncementAdapter mAnnocumentAdapter;
    DashbordModuleAdapter mDashbordModuleAdapter;

    CardView card_view2,card_view1 ,card_view_birthday,card_view_announcement;

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
    String mLat ,mLong,mAddress;


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


        card_view2 = (CardView)v.findViewById(R.id.card_view2);
        card_view1 = (CardView)v.findViewById(R.id.card_view1);

       // ll_birthday = (LinearLayout)v.findViewById(R.id.ll_birthday);
       // ll_announcement = (LinearLayout)v.findViewById(R.id.ll_announcement);

        card_view_birthday = (CardView)v.findViewById(R.id.card_view_birthday);
        card_view_announcement = (CardView)v.findViewById(R.id.card_view_announcement);

        btn_date = (Button) v.findViewById(R.id.btn_date);
        btn_announcement = (Button) v.findViewById(R.id.btn_announcement);

        try{
            currentVersion = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionName;
            new GetVersionCode().execute();
        }
        catch (Exception e){

        }

        /*Typeface copperplateGothicLight = Typeface.createFromAsset(getAppContext().getAssets(), "GillSans-SemiBold.ttf");
        btn_date.setTypeface(copperplateGothicLight);
        btn_announcement.setTypeface(copperplateGothicLight);*/

    }

    private void setData(View v) {

        //checkVersionUpgrade();
        setupRecyclerView();


        is_outside_allow = Pref.getValue(getActivity(),"is_outside_allow" ,0);
        setupFAB(v);

        displayTuto();



    }

    private void checkVersionUpgrade() {
       /* from_last = "LatestVersion";
        new WebServices(getActivity()*//* ActivityContext *//*, DashboardFragment.this *//* ApiListener *//*, true *//* show progress dialog *//*,
                false*//*for new retrofitclient*//*).
                callLetestVersionAPI();*/



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


        if(is_outside_allow == 0){
            fab_attendance.setVisibility(View.GONE);
        }
        else{
            fab_attendance.setVisibility(View.VISIBLE);
        }



        //materialDesignFAM.setVisibility(View.GONE);
        fab_attendance.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked

                if (Permissions.getInstance().isLocationPermissionGranted(getActivity())) {

                    Location location =  new GPSTracker(getActivity()).getLocation();

                    if(location!=null){
                        //  CommonUtils.getInstance().displayToast(QRScanActivity.this,"lat >> "+location.getLatitude());
                        mLat = String.valueOf(location.getLatitude());
                        mLong= String.valueOf(location.getLongitude());

                        //CommonUtils.getInstance().displayToast(getActivity(),">>> "+mLat);
                        mAddress = mPreferenceHelper.getAddress();

                        callOutSideAttendanceFromMobileAppAPI("",mLat,mLong ,mAddress);

                        materialDesignFAM.close(true);

                    }
                    else{
                        //new GPSTracker(mActivity).showAlert();
                        CommonUtils.getInstance().displayToast(getActivity(),"Please enable your GPS");
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                    }

                }


                // Fragment fragment = new TempFragment();
               //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment, "TEMP").addToBackStack("null").commit();

            }
        });


        fab_qrcode.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked
               // Crashlytics.getInstance().crash(); // Force a crash

                if (Permissions.getInstance().isCameraPermissionGranted(getActivity()) ) {
                    //open camera and api calls for qrcode..
                    CommonUtils.getInstance().startActivity(getActivity(), QRScanActivity.class);
                    getActivity().overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
                    materialDesignFAM.close(true);
                }
            }
        });

    }


    private void callOutSideAttendanceFromMobileAppAPI(String QR_text  ,String mLat ,String mLong ,String mAddress) {

        ApplyAttendanceParam params = new ApplyAttendanceParam();
        params.setAtt_Lat(mLat);
        params.setAtt_Long(mLong);
        params.setIsQrCode("0");//change flag
        params.setQrCode(QR_text);
        params.setAtt_PhisicalAddress(mAddress);
        params.setEmployeePk(String.valueOf(Pref.getValue(getActivity(), Constant.PREF_SESSION_EMPLOYEE_FK, 0)));

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
                        bundle.putInt("mNewsPk",mNewsPk);

                        CommonUtils.getInstance().startActivity(getActivity(), WebViewActivity.class,bundle);
                        getActivity().overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);

                    }
                })
        );

        dialog = new Dialog(getActivity(), R.style.progressDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progress_dialog);
        dialog.setCancelable(false);
        //Show progress dialog
        dialog.show();

        callDashbordDetailAPI("Employee");
    }

    private void setListner() {


    }


    public static DashboardFragment newInstance() {
        return new DashboardFragment();
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {

        mDialog = new GreetingsDialog(getActivity(), position);
        mDialog.setCancelable(false);
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.show();

    }

    @Override
    public void recyclerViewModuleClicked(View v, int position) {

        if(mModuleList.get(position).getTitle().equalsIgnoreCase("Pending Attendance \nApproval")){
            //CommonUtils.getInstance().displayToast(getActivity(), mModuleList.get(position).getTitle());
            callDashbordDetailAPI("AttendanceDetail");
        }else if(mModuleList.get(position).getTitle().equalsIgnoreCase("Pending Leave \nApproval")){
            //CommonUtils.getInstance().displayToast(getActivity(), mModuleList.get(position).getTitle());
            callDashbordDetailAPI("LeaveDetail");
        }
    }


    @Override
    public void onApiSuccess(Object mObject) {

        //for token expired
        if (mObject instanceof LoginModel) {
            LoginModel model = (LoginModel) mObject;
            Log.e("", "onApiSuccess: token >>  "+model.getToken() );
            if(model.getResponse().equalsIgnoreCase("Invalid Username or Password, Please try again.")){
                //redirect to login screen
                Pref.setValue(getActivity(), "auto_login","false");
                CommonUtils.getInstance().startActivityWithoutStack(getActivity(), SigninActivity.class);
            }
            else{
                //success response..
                if(model.getToken()!=null){
                    //add bearer_ (space before token....)
                    Pref.setValue(getActivity(),Constant.PREF_TOKEN,"bearer "+model.getToken());
                    Pref.setValue(getActivity(),Constant.PREF_SESSION_EMPLOYEE_FK,model.getEmployeeFK());

                    Pref.setValue(getActivity(),Constant.PREF_ROLE_FK,model.getRoleFK());
                    //api calls for get user profile
                    //getUserProfile(model.getEmployeeFK());

                    CommonUtils.getInstance().startActivityWithoutStack(getActivity(), MainActivity.class);
                }
                else{
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

                if(from_last.equalsIgnoreCase("Employee")){
                    model = gson.fromJson(mObject.toString(), DashbordModel.class);

                   // ll_announcement.setVisibility(View.VISIBLE);
                   // ll_birthday.setVisibility(View.VISIBLE);

                    card_view_birthday.setVisibility(View.VISIBLE);
                    card_view_announcement.setVisibility(View.VISIBLE);

                    //set announcement adapter
                    if(model.getTable12().size()>0){
                        rv_announcements.setVisibility(View.VISIBLE);
                        card_view2.setVisibility(View.GONE);
                        mAnnocumentAdapter = new AnnouncementAdapter(getActivity(), model, DashboardFragment.this);
                        rv_announcements.setAdapter(mAnnocumentAdapter);
                        rv_announcements.setNestedScrollingEnabled(false);
                    }
                    else{
                        rv_announcements.setVisibility(View.GONE);
                        card_view2.setVisibility(View.VISIBLE);
                    }

                    if(model.getTable4().size()>0){
                        rv_birthday_today.setVisibility(View.VISIBLE);
                        card_view1.setVisibility(View.GONE);
                        mBirthdateAdapter = new BirthdayAdapter(getActivity(), model, DashboardFragment.this);
                        rv_birthday_today.setAdapter(mBirthdateAdapter);
                        rv_birthday_today.setNestedScrollingEnabled(false); //for smooth nested scroll
                    }else{
                        rv_birthday_today.setVisibility(View.GONE);
                        card_view1.setVisibility(View.VISIBLE);
                    }




                    if(model.getTable11().get(0).getHRRights()==1){
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

                    }else{
                        //hide
                        rv_top_modules.setVisibility(View.GONE);
                    }

                }else if(from_last.equalsIgnoreCase("AttendanceDetail")){

                    attendanceDetailModel = gson.fromJson(mObject.toString(), AttendanceDetailModel.class);
                    mAttendanceDetailDialog = new AttendanceDetailDialog(getActivity(),attendanceDetailModel,null,"Pending Attendance Approval","Pending Attendance");
                    mAttendanceDetailDialog.setCancelable(false);
                    mAttendanceDetailDialog.setCanceledOnTouchOutside(true);
                    mAttendanceDetailDialog.show();

                }else if(from_last.equalsIgnoreCase("LeaveDetail")){

                    leaveDetailModel = gson.fromJson(mObject.toString(), LeaveDetailModel.class);
                    mAttendanceDetailDialog = new AttendanceDetailDialog(getActivity(),null,leaveDetailModel,"Pending Leave Approval","Pending Leave");
                    mAttendanceDetailDialog.setCancelable(false);
                    mAttendanceDetailDialog.setCanceledOnTouchOutside(true);
                    mAttendanceDetailDialog.show();


                }
                else if(from_last.equalsIgnoreCase("OutSideAttendance")){

                    mOutsideSideAttendanceModel = gson.fromJson(mObject.toString(), OutSideAttendanceModel.class);

                    CommonUtils.getInstance().displayToast(getActivity(),""+mOutsideSideAttendanceModel.getTable().get(0).getResult());
                }

               /* else if(from_last.equalsIgnoreCase("LatestVersion")){

                    try {
                        mLetestVersionModel =  gson.fromJson(mObject.toString(), LetestVersionModel.class);
                    Log.e(TAG, "onApiSuccess:  >>>> " +mLetestVersionModel.getData().getVersion() );

                        PackageInfo pInfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
                        int verCode = pInfo.versionCode;

                    if(Integer.parseInt(mLetestVersionModel.getData().getVersion())> verCode){
                        //show popup
                        mVersionDialog = new VersionUpgradeDialog(getActivity());
                        mVersionDialog.setCancelable(false);
                        mVersionDialog.setCanceledOnTouchOutside(true);
                        mVersionDialog.show();

                    }else{
                        //nothing
                        //show popup
                        mVersionDialog = new VersionUpgradeDialog(getActivity());
                        mVersionDialog.setCancelable(false);
                        mVersionDialog.setCanceledOnTouchOutside(true);
                        mVersionDialog.show();
                    }

                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }

                }*/


                //dismiss dialog
                dialog.dismiss();

            } catch (JSONException e) {
                e.printStackTrace();
                //dismiss dialog
                if(dialog!=null){
                    if(dialog.isShowing())
                    dialog.dismiss();
                }

            }
        }

        else if(mObject instanceof LetestVersionModel ){
            LetestVersionModel model = (LetestVersionModel) mObject;

                try {

                    Log.e(TAG, "onApiSuccess:  >>>> " +model.getData().getVersion() );

                    PackageInfo pInfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
                    int verCode = pInfo.versionCode;

                    if (model.getStatus().equals("Success")) {
                        if(Integer.parseInt(model.getData().getVersion())> verCode){
                            //show popup
                            mVersionDialog = new VersionUpgradeDialog(getActivity());
                            mVersionDialog.setCancelable(false);
                            mVersionDialog.setCanceledOnTouchOutside(false);
                            mVersionDialog.show();


                        }else{
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
            params.setUsername(Pref.getValue(getActivity(),"user_id",""));
            params.setPassword(Pref.getValue(getActivity(),"user_password",""));
                     params.setMobileURL(Pref.getValue(getActivity(),Constant.PREF_MOBILE_URL,""));

            new WebServices(getActivity()/* ActivityContext */, this /* ApiListener */, false /* show progress dialog */,
                    true/*for new retrofitclient*/ ).
                    callLoginAPI(params);
    }

    @Override
    public void onResume() {
        super.onResume();

        if(Constant.LAST_ACTIVITY.equalsIgnoreCase("AttendanceDeviationActivity") || Constant.LAST_ACTIVITY.equalsIgnoreCase("GroupLeaveActivity")){

            Constant.LAST_ACTIVITY = "";
            callDashbordDetailAPI("Employee");
        }

    }



    private class GetVersionCode extends AsyncTask<Void, String, String> {
        @Override
        protected String doInBackground(Void... voids) {

            String newVersion = null;
            try {
                newVersion = Jsoup.connect("https://play.google.com/store/apps/details?id=" + getActivity().getPackageName() + "&hl=en")
                        .timeout(30000)
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.google.com")
                        .get()
                        .select("div[itemprop=softwareVersion]")
                        .first()
                        .ownText();
                return newVersion;
            } catch (Exception e) {
                return newVersion;
            }
        }

        @Override
        protected void onPostExecute(String onlineVersion) {
            super.onPostExecute(onlineVersion);
            if (onlineVersion != null && !onlineVersion.isEmpty()) {
                if (Float.valueOf(currentVersion) < Float.valueOf(onlineVersion)) {
                    //show dialog
                    mVersionDialog = new VersionUpgradeDialog(getActivity());
                    mVersionDialog.setCancelable(false);
                    mVersionDialog.setCanceledOnTouchOutside(true);
                    mVersionDialog.show();
                }
            }
            //Toast.makeText(MainActivity.this,"playstore version >> " +onlineVersion,Toast.LENGTH_LONG).show();
            // Log.e("update", "Current version " + currentVersion + "playstore version " + onlineVersion);
        }
    }

}
