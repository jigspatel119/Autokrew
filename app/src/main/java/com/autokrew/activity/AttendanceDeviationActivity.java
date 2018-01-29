package com.autokrew.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.autokrew.R;
import com.autokrew.adapter.AttendanceAdapter;
import com.autokrew.adapter.AttendanceDeviationAdapter;
import com.autokrew.dialogs.AttendanceTeamGroupDialog;
import com.autokrew.fragments.GroupAttendanceFragment;
import com.autokrew.fragments.MyAttendanceFragment;
import com.autokrew.interfaces.AttendanceDialogInterface;
import com.autokrew.interfaces.RecyclerViewClickListener;
import com.autokrew.models.AttendanceDeviationTGModel;
import com.autokrew.models.AttendanceModel;
import com.autokrew.models.AttendanceModelParams;
import com.autokrew.models.AttendanceTeamGroupModel;
import com.autokrew.network.ApiListener;
import com.autokrew.dialogs.AttendanceDialog;
import com.autokrew.network.WebServices;
import com.autokrew.utils.CommonUtils;
import com.autokrew.utils.Constant;
import com.autokrew.utils.Pref;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.autokrew.utils.AppController.getAppContext;

public class AttendanceDeviationActivity extends AppCompatActivity implements ApiListener, View.OnClickListener,
        RecyclerViewClickListener,AttendanceDialogInterface {


    private RecyclerView rv_attendance_deviation;
    CardView card_view;
    AttendanceDeviationAdapter mAdapter;
    Toolbar toolbar;
    List<AttendanceTeamGroupModel> mList;
    AttendanceDeviationTGModel model;

    AttendanceTeamGroupDialog mDialog;
    String mApprovalStatus ;
    String mMonthPK,mYear;
    String mEmployeePK,CompanyFK,LocationFK,SubLocationFK,VerticalFK,DepartmentFK,SubDepartmentFK,DesignationFK;

    String from_last;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_deviation);

        Constant.LAST_ACTIVITY = "AttendanceDeviationActivity";


        getData();
        findView();
        setData();
        setListner();

    }

    private void getData() {

        if(Pref.getValue(this,"call_team_or_group","").equalsIgnoreCase("group")){
            CompanyFK =  Pref.getValue(this,"CompanyFK","");
            LocationFK = Pref.getValue(this,"LocationFK","");
            SubLocationFK = Pref.getValue(this,"SubLocationFK","");
            VerticalFK =  Pref.getValue(this,"VerticalFK","");
            DepartmentFK = Pref.getValue(this,"DepartmentFK","");
            SubDepartmentFK =  Pref.getValue(this,"SubDepartmentFK","");
            DesignationFK = Pref.getValue(this,"DesignationFK","");

        }
        mApprovalStatus =  Pref.getValue(this,"mApprovalStatus","");
        mMonthPK =  Pref.getValue(this,"mMonthPK","");
        mYear =  Pref.getValue(this,"mYear","");
        mEmployeePK =  Pref.getValue(this,"mEmployeePK","");

    }

    private void setData() {

        if (CommonUtils.getInstance().isNetworkAvailable(this)) {

            // callAPI(getActivity());
            //  if (validate()) {
            //{"SessionUserFk":8816,"MonthFK":8,"YearFk":2017,"EmployeeFK":-1,
            // "ApprovalStatus":"1","Flag":"Employee_Attendance_Register"}

            //Flag : (1)Attendance_Deviations  : For Deviation  list of Team Member
//                   (2)Employee_Attendance_Status : For Attendance Status
//                   (3) Employee_Attendance_Register : For Monthly Attendance Register

            AttendanceModelParams params = new AttendanceModelParams();
            params.setMonthFK(Integer.parseInt(mMonthPK)); //
            params.setSessionUserFk(Pref.getValue(this,Constant.PREF_SESSION_EMPLOYEE_FK,0));
            params.setYearFk(Integer.parseInt(mYear)); //
            params.setApprovalStatus(mApprovalStatus);
            params.setFlag("Attendance_Deviations");
            params.setEmployeeFK(Integer.parseInt(mEmployeePK)); //team member fk

            if(Pref.getValue(this,"call_team_or_group","").equalsIgnoreCase("group")){
                params.setCompanyFK(Integer.parseInt(CompanyFK));
                params.setLocationFK(Integer.parseInt(LocationFK));
                params.setSubLocationFK(Integer.parseInt(SubLocationFK));
                params.setVerticalFK(Integer.parseInt(VerticalFK));
                params.setDepartmentFK(Integer.parseInt(DepartmentFK));
                params.setSubDepartmentFK(Integer.parseInt(SubDepartmentFK));
                params.setDesignationFK(Integer.parseInt(DesignationFK));

                from_last = "GroupAttendance";
            }

            else{
                from_last = "TeamAttendance";
            }

            String mToken = Pref.getValue(this,Constant.PREF_TOKEN,"");


            new WebServices(this/* ActivityContext */, this /* ApiListener */,
                    true /* show progress dialog */,true).
                    callGetAttendanceAPI(mToken,params ,from_last);
            //  }


        } else {
            CommonUtils.getInstance().displayToast(this, Constant.INTERNET_FAILURE);
        }

    }

    private void setListner() {


    }

    private void findView() {
        // btn_signin = (MyTextView) this.findViewById(R.id.btn_signin);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Attendance Deviation");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rv_attendance_deviation = (RecyclerView) this.findViewById(R.id.rv_attendance_deviation);
        card_view = (CardView) this.findViewById(R.id.card_view);
        card_view.setVisibility(View.GONE);

       // CommonUtils.setupCustomToolbar(toolbar);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_signin:


                break;
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {

            finish();
            this.overridePendingTransition(R.anim.activity_open_scale, R.anim.activity_close_translate);

        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void setupRecyclerView(AttendanceDeviationTGModel model) {

        rv_attendance_deviation.setLayoutManager(new LinearLayoutManager(this));
        rv_attendance_deviation.setHasFixedSize(true);
       // mList = new ArrayList<>();
        mAdapter = new AttendanceDeviationAdapter(this, model, AttendanceDeviationActivity.this);
        rv_attendance_deviation.setAdapter(mAdapter);
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {
        // CommonUtils.getInstance().displayToast(this,"pos >>" +position);

        mDialog = new AttendanceTeamGroupDialog(this, position ,model.getTable().get(position).getName(),
                model.getTable().get(position).getDate(), model.getTable().get(position).getAttendancePK()
                ,AttendanceDeviationActivity.this);
        mDialog.setCancelable(false);
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.show();

    }

    @Override
    public void onApiSuccess(Object mObject) {
        //set adapter here

        if (mObject instanceof String) {
            //LoginModel model = (LoginModel) mObject;
            // Log.e("", "onApiSuccess: >>  "+mObject.toString() );
            try {
                JSONObject jsonObj = new JSONObject(mObject.toString());
                Log.e("", "onApiSuccess: json >>  "+jsonObj);
                Gson gson = new Gson();
                model = gson.fromJson(mObject.toString(), AttendanceDeviationTGModel.class);

                //setupRecyclerView(model);

                if(model.getTable().size()==0){
                    //no record found
                    card_view.setVisibility(View.VISIBLE);
                    rv_attendance_deviation.setVisibility(View.GONE);


                }else{
                    card_view.setVisibility(View.GONE);
                    rv_attendance_deviation.setVisibility(View.VISIBLE);

                    setupRecyclerView(model);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void onApiFailure(Throwable mThrowable) {

    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuitems, menu);
        MenuItem menuItem = menu.findItem(R.id.actionsearch);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

      //  search(searchView);

        //return super.onCreateOptionsMenu(menu);
        return true;
    }*/

    /* @Override
     public boolean onQueryTextSubmit(String query) {
         return false;
     }

     @Override
     public boolean onQueryTextChange(String newText) {
         newText=newText.toLowerCase();
         ArrayList<AttendanceModel>newlist=new ArrayList<>();
         for(AttendanceModel name : mList)
         {
             String getName=name.getDate_time().toLowerCase();
             if(getName.contains(newText)){
                 newlist.add(name);

             }
         }

         nameArrayList.clear();
         nameArrayList.addAll(newlist);
         mAdapter.notifyDataSetChanged();
         return true;
     }*/
   /* private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                mAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        this.overridePendingTransition(R.anim.activity_open_scale, R.anim.activity_close_translate);


    }

    @Override
    public void recallAllAPI() {
        //recall all apis here
        setData();
    }
}

