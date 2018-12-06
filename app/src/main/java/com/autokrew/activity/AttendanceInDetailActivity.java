package com.autokrew.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.autokrew.R;
import com.autokrew.adapter.AttendanceInDetailAdapter;
import com.autokrew.dialogs.EmployeeDetailDialog;
import com.autokrew.models.AttendanceInDetailModel;
import com.autokrew.models.AttendanceInDetailModelParams;
import com.autokrew.network.ApiListener;
import com.autokrew.network.WebServices;
import com.autokrew.utils.Constant;
import com.autokrew.utils.Pref;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;


public class AttendanceInDetailActivity extends AppCompatActivity  implements ApiListener{

    RelativeLayout backbtn ,re_user_info;
    private RecyclerView rv_data_attendance;
    CardView card_view;
    AttendanceInDetailAdapter mAdapter;
    AttendanceInDetailModel model;

    String mMonthPK,mYearPK,mEmployeePK,mApprovalStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendenceindetail);

        try {
            getData();

            backbtn = (RelativeLayout) this.findViewById(R.id.re_back);
            re_user_info = (RelativeLayout) this.findViewById(R.id.re_user_info);
            rv_data_attendance = (RecyclerView) this.findViewById(R.id.rv_data_attendance);
            card_view = (CardView)this.findViewById(R.id.card_view);
            setupRecyclerView(); //api calls
            re_user_info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {




                 //   Util.shareUrl(NewsWebViewActivity.this, url);
                    EmployeeDetailDialog mDialog = new EmployeeDetailDialog(AttendanceInDetailActivity.this,
                            model.getTable().get(0).getEmployeeCode(),
                            model.getTable().get(0).getName(),
                            model.getTable().get(0).getReprtingPerson(),
                            model.getTable().get(0).getCompany(),
                            model.getTable().get(0).getDepartment(),
                            model.getTable().get(0).getSubDepartment(),
                            model.getTable().get(0).getLocation(),
                            model.getTable().get(0).getSubLocation()
                            );

                    if(mDialog!=null && mDialog.isShowing()){
                        //check for multiple dialogs open
                    }
                    else {
                        mDialog.setCancelable(false);
                        mDialog.setCanceledOnTouchOutside(true);
                        mDialog.show();
                    }

                }
            });
            backbtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    // overridePendingTransition(R.anim.activity_open_scale, R.anim.activity_close_translate);
                     finish();
                }
            });

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    private void getData() {

        mApprovalStatus = getIntent().getStringExtra("approve_status");
        mEmployeePK  = getIntent().getStringExtra("employee_fk");

        //mApprovalStatus =  Pref.getValue(this,"mApprovalStatus","");
        mMonthPK =  Pref.getValue(this,"mMonthPK","");
        mYearPK =  Pref.getValue(this,"mYear","");  // yearPK = value of year

       // mEmployeePK =  Pref.getValue(this,"mEmployeePK","");
    }

    private void setupRecyclerView(){

        rv_data_attendance.setLayoutManager(new LinearLayoutManager(this));
        rv_data_attendance.setHasFixedSize(true);

        apicallsforStatus();
    }


    private void apicallsforStatus() {

        AttendanceInDetailModelParams params = new AttendanceInDetailModelParams();
        params.setMonthFK(Integer.parseInt(mMonthPK)); //
        params.setYearFk(Integer.parseInt(mYearPK)); //
        params.setApprovalStatus(mApprovalStatus);
        params.setEmployeeFK(mEmployeePK); //team member fk

        //{ EmployeeFK: "AN10000015", MonthFK: 3, YearFk: 49, ApprovalStatus: "P" }


        String mToken = Pref.getValue(this,Constant.PREF_TOKEN,"");

        new WebServices(this/* ActivityContext */, this /* ApiListener */,
                false /* show progress dialog */,true).
                callGetAttendanceInDetailAPI(mToken,params);
        //  }

    }



    @Override
    public void onApiSuccess(Object mObject) {

        if (mObject instanceof String) {
            //LoginModel model = (LoginModel) mObject;
            // Log.e("", "onApiSuccess: >>  "+mObject.toString() );
            try {
                JSONObject jsonObj = new JSONObject(mObject.toString());
                Log.e("", "onApiSuccess Attendance in Detail : json >>  "+jsonObj);
                Gson gson = new Gson();
                    model = gson.fromJson(mObject.toString(), AttendanceInDetailModel.class);
                    //setupRecyclerView(model);
                    if(model!=null){
                        if(model.getTable().size()==0){
                            //no record found
                            card_view.setVisibility(View.VISIBLE);
                            rv_data_attendance.setVisibility(View.GONE);
                        }else{
                            card_view.setVisibility(View.GONE);
                            rv_data_attendance.setVisibility(View.VISIBLE);
                            mAdapter = new AttendanceInDetailAdapter(this, model);
                            rv_data_attendance.setAdapter(mAdapter);
                            rv_data_attendance.setNestedScrollingEnabled(false);//for smooth nested scroll
                        }
                    }

                    else{
                        card_view.setVisibility(View.VISIBLE);
                        rv_data_attendance.setVisibility(View.GONE);
                    }




            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void onApiFailure(Throwable mThrowable) {

    }



}