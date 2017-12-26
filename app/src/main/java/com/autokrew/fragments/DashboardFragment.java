package com.autokrew.fragments;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.autokrew.R;
import com.autokrew.activity.WebViewActivity;
import com.autokrew.adapter.AnnouncementAdapter;
import com.autokrew.adapter.BirthdayAdapter;
import com.autokrew.adapter.DashbordModuleAdapter;
import com.autokrew.dialogs.AttendanceDetailDialog;
import com.autokrew.dialogs.GreetingsDialog;
import com.autokrew.interfaces.RecyclerViewDashBoardListener;
import com.autokrew.listner.RecyclerItemClickListener;
import com.autokrew.models.AttendanceDetailModel;
import com.autokrew.models.DashboardModuleModel;
import com.autokrew.models.DashbordModel;
import com.autokrew.models.DashbordModelParam;
import com.autokrew.models.LeaveDetailModel;
import com.autokrew.network.ApiListener;
import com.autokrew.network.WebServices;
import com.autokrew.utils.CommonUtils;
import com.autokrew.utils.Constant;
import com.autokrew.utils.Pref;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.autokrew.utils.AppController.getAppContext;


/**
 * Created by kumarpalsinh on 27/1/17.
 */

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


    FloatingActionMenu materialDesignFAM;
    FloatingActionButton fab_announcement, fab_birthaday;

    //dialog for sms/phonecall
    GreetingsDialog mDialog;
    Button btn_date, btn_announcement;
    List<DashboardModuleModel> mModuleList;

    AttendanceDetailDialog mAttendanceDetailDialog;


    DashbordModel model;
    DashboardModuleModel mModel;
    AttendanceDetailModel attendanceDetailModel;
    LeaveDetailModel leaveDetailModel;
    String mToken;

    String from_last = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root_view = inflater.inflate(R.layout.fragment_dashboard, container, false);


        getData();
        findView(root_view);
        setData(root_view);
        setListner();


        return root_view;
    }

    private void getData() {
        mToken = Pref.getValue(getActivity(), Constant.PREF_TOKEN, "");

    }

    private void findView(View v) {

        rv_birthday_today = (RecyclerView) v.findViewById(R.id.rv_birthday_today);
        rv_announcements = (RecyclerView) v.findViewById(R.id.rv_announcements);
        rv_top_modules = (RecyclerView) v.findViewById(R.id.rv_top_modules);


        btn_date = (Button) v.findViewById(R.id.btn_date);
        btn_announcement = (Button) v.findViewById(R.id.btn_announcement);

        Typeface copperplateGothicLight = Typeface.createFromAsset(getAppContext().getAssets(), "GillSans-SemiBold.ttf");
        btn_date.setTypeface(copperplateGothicLight);
        btn_announcement.setTypeface(copperplateGothicLight);

    }

    private void setData(View v) {

        setupRecyclerView();
        setupFAB(v);


    }

    private void callDashbordDetailAPI(String flag) {
        DashbordModelParam params = new DashbordModelParam();
        params.setFlag(flag);
        params.setEmployeeFK(String.valueOf(Pref.getValue(getActivity(), Constant.PREF_SESSION_EMPLOYEE_FK, 0)));

        from_last = flag;
        new WebServices(getActivity()/* ActivityContext */, DashboardFragment.this /* ApiListener */, true /* show progress dialog */,
                true/*for new retrofitclient*/).
                callDashboardDetailAPI(mToken, params);
        //  }
    }

    private void setupFAB(View v) {

        materialDesignFAM = (FloatingActionMenu) v.findViewById(R.id.material_design_android_floating_action_menu);
        fab_announcement = (FloatingActionButton) v.findViewById(R.id.fab_announcement);
        fab_birthaday = (FloatingActionButton) v.findViewById(R.id.fab_birthaday);

        materialDesignFAM.setVisibility(View.GONE  );
        fab_announcement.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked

                CommonUtils.getInstance().displayToast(getActivity(), "get more announcements!");

              // Fragment fragment = new TempFragment();
               //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment, "TEMP").addToBackStack("null").commit();

            }
        });


        fab_birthaday.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked

                CommonUtils.getInstance().displayToast(getActivity(), "get more birthdays!");
            }
        });

    }

    private void setupRecyclerView() {

        rv_top_modules.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rv_top_modules.setHasFixedSize(true);
        //set dummy data for top modules

       /* mModuleList = new ArrayList<>();
        DashboardModuleModel mModel;
        for (int i = 0; i < 2; i++) {
            mModel = new DashboardModuleModel();
            if (i == 0) {
                mModel.setTitle("Pending Attendance \nApproval");
                mModel.setCount(100);
            } else if (i == 1) {
                mModel.setTitle("Pending Leave \nApproval");
                mModel.setCount(200);
            }

            mModuleList.add(mModel);

        }
        mDashbordModuleAdapter = new DashbordModuleAdapter(getActivity(), mModuleList, DashboardFragment.this);
        rv_top_modules.setAdapter(mDashbordModuleAdapter);
        rv_top_modules.setNestedScrollingEnabled(false); //for smooth nested scroll*/


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
        if (mObject instanceof String) {

            try {
                JSONObject jsonObj = new JSONObject(mObject.toString());
                //parse original data
                Log.e("", "onApiSuccess: dashboard json >>  " + jsonObj.toString());
                Gson gson = new Gson();

                if(from_last.equalsIgnoreCase("Employee")){
                    model = gson.fromJson(mObject.toString(), DashbordModel.class);

                    //set announcement adapter
                    mAnnocumentAdapter = new AnnouncementAdapter(getActivity(), model, DashboardFragment.this);
                    rv_announcements.setAdapter(mAnnocumentAdapter);
                    rv_announcements.setNestedScrollingEnabled(false);

                    //set birthday adapter

                    //List<BirthdayModel> mList = new ArrayList<>();
                    mBirthdateAdapter = new BirthdayAdapter(getActivity(), model, DashboardFragment.this);
                    rv_birthday_today.setAdapter(mBirthdateAdapter);
                    rv_birthday_today.setNestedScrollingEnabled(false); //for smooth nested scroll

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


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onApiFailure(Throwable mThrowable) {

    }

    @Override
    public void onResume() {
        super.onResume();

        if(Constant.LAST_ACTIVITY.equalsIgnoreCase("AttendanceDeviationActivity") || Constant.LAST_ACTIVITY.equalsIgnoreCase("GroupLeaveActivity")){

            Constant.LAST_ACTIVITY = "";
            callDashbordDetailAPI("Employee");
        }

    }
}
