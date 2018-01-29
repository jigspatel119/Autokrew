package com.autokrew.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.autokrew.R;
import com.autokrew.adapter.GroupLeaveAdapter;
import com.autokrew.dialogs.LeaveRequestDialog;
import com.autokrew.dialogs.RevisedDialog;
import com.autokrew.interfaces.AttendanceDialogInterface;
import com.autokrew.interfaces.RecyclerViewClickListener;
import com.autokrew.models.GroupLeaveModel;
import com.autokrew.models.TeamOrGroupLeaveModelParams;
import com.autokrew.network.ApiListener;
import com.autokrew.network.WebServices;
import com.autokrew.utils.CommonUtils;
import com.autokrew.utils.Constant;
import com.autokrew.utils.Pref;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class GroupLeaveActivity extends AppCompatActivity implements ApiListener,View.OnClickListener,AttendanceDialogInterface,
        RecyclerViewClickListener {



    private RecyclerView rv_group_leave;
    GroupLeaveAdapter mAdapter;
    Toolbar toolbar;
    List<GroupLeaveModel> mList;
    GroupLeaveModel model ;

    String LeaveStatusFK ;
    String mMonthPK,mYear;
    String mEmployeePK,CompanyFK,LocationFK,SubLocationFK,VerticalFK,DepartmentFK,SubDepartmentFK,DesignationFK;

    String from_last;
    CardView card_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_leave);

        Constant.LAST_ACTIVITY = "GroupLeaveActivity";

        getData();
        findView();
        setData();
        setListner();

    }

    private void getData() {
        if(Pref.getValue(this,"call_team_or_group_leave","").equalsIgnoreCase("group")){
            CompanyFK =  Pref.getValue(this,"CompanyFK","");
            LocationFK = Pref.getValue(this,"LocationFK","");
            SubLocationFK = Pref.getValue(this,"SubLocationFK","");
            VerticalFK =  Pref.getValue(this,"VerticalFK","");
            DepartmentFK = Pref.getValue(this,"DepartmentFK","");
            SubDepartmentFK =  Pref.getValue(this,"SubDepartmentFK","");
            DesignationFK = Pref.getValue(this,"DesignationFK","");

        }
        LeaveStatusFK =  Pref.getValue(this,"LeaveStatusFK","");
        mMonthPK =  Pref.getValue(this,"mMonthPK","");
        mYear =  Pref.getValue(this,"mYear","");
        mEmployeePK =  Pref.getValue(this,"mEmployeePK","");
    }

    private void setData() {

        //setupRecyclerView();

        //api calls for leave listing as per filter...
        TeamOrGroupLeaveModelParams params = new TeamOrGroupLeaveModelParams();
        params.setFlag("Grid");
        params.setSessionEmployeeFk(Pref.getValue(this,Constant.PREF_SESSION_EMPLOYEE_FK,0));
        params.setMonth(Integer.parseInt(mMonthPK)); //
        params.setYear(Integer.parseInt(mYear)); //
        params.setLeaveStatusFK(Integer.parseInt(LeaveStatusFK));
        params.setEmployeeFK(Integer.parseInt(mEmployeePK)); //team member fk
        params.setTabFlag("Team_Leave_Request");

        if(Pref.getValue(this,"call_team_or_group_leave","").equalsIgnoreCase("group")){
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
                callGetTeamLeaveAPI(mToken,params ,from_last);

    }

    private void setListner() {


    }

    private void findView() {
       // btn_signin = (MyTextView) this.findViewById(R.id.btn_signin);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Team Leave Request");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rv_group_leave = (RecyclerView) this.findViewById(R.id.rv_group_leave);
        card_view = (CardView) this.findViewById(R.id.card_view);
        card_view.setVisibility(View.GONE);


       // CommonUtils.setupCustomToolbar(toolbar);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btn_signin:

                break;
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {

            finish();
            this.overridePendingTransition(R.anim.activity_open_scale,R.anim.activity_close_translate);
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void setupRecyclerView(GroupLeaveModel model){

        rv_group_leave.setLayoutManager(new LinearLayoutManager(this));
        rv_group_leave.setHasFixedSize(true);

        mAdapter = new GroupLeaveAdapter(this, model,GroupLeaveActivity.this);
        rv_group_leave.setAdapter(mAdapter);

    }

    @Override
    public void recyclerViewListClicked(View v, int position) {
       // {"Flag":"Detail","SessionEmployeeFk":8816,
        // "LeaveDetailPK":6719,"ApprovalStatus":null,"ApprovalRemarks":null}

        String leave_button = Pref.getValue(this, Constant.PREF_LEAVE_BUTTON,"");
        if(leave_button.equalsIgnoreCase("iv_edit")){
            int leaveDetailPK =  model.getTable().get(position).getLeaveDetailPK();
            LeaveRequestDialog  mDialog = new LeaveRequestDialog(this,position ,leaveDetailPK ,GroupLeaveActivity.this ,model);
            mDialog.setCancelable(false);
            mDialog.setCanceledOnTouchOutside(true);
            mDialog.show();
        }

        else if(leave_button.equalsIgnoreCase("iv_revised")){
            int leaveDetailPK = model.getTable().get(position).getLeaveDetailPK();

            RevisedDialog mDialog = new RevisedDialog(this,GroupLeaveActivity.this, position ,leaveDetailPK);
            mDialog.setCancelable(false);
            mDialog.setCanceledOnTouchOutside(true);
            mDialog.show();


        }




    }

    @Override
    public void onApiSuccess(Object mObject) {

        if (mObject instanceof String) {
             Log.e("", "onApiSuccess: >>  "+mObject.toString() );
            try {
                JSONObject jsonObj = new JSONObject(mObject.toString());
                //Log.e("", "onApiSuccess: json >>  "+jsonObj);
                Gson gson = new Gson();
                model = gson.fromJson(mObject.toString(), GroupLeaveModel.class);

                //setupRecyclerView(model);

                if(model.getTable().size()==0){
                    //no record found
                    card_view.setVisibility(View.VISIBLE);
                    rv_group_leave.setVisibility(View.GONE);


                }else{
                    card_view.setVisibility(View.GONE);
                    rv_group_leave.setVisibility(View.VISIBLE);
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

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuitems,menu);
        MenuItem menuItem=menu.findItem(R.id.actionsearch);
        SearchView searchView=(SearchView) MenuItemCompat.getActionView(menuItem);
        search(searchView);

        return super.onCreateOptionsMenu(menu);
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
   private void search(SearchView searchView) {

     /*  searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String query) {

               return false;
           }

           @Override
           public boolean onQueryTextChange(String newText) {

               mAdapter.getFilter().filter(newText);
               return true;
           }
       });*/
   }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        this.overridePendingTransition(R.anim.activity_open_scale,R.anim.activity_close_translate);


    }

    @Override
    public void recallAllAPI() {

        setData();

    }
}

