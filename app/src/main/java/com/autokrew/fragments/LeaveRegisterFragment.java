package com.autokrew.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.autokrew.R;
import com.autokrew.adapter.AttendanceRegisterAdapter;
import com.autokrew.adapter.GroupLeaveAdapter;
import com.autokrew.adapter.LeaveRegisterAdapter;
import com.autokrew.models.AttendanceModelParams;
import com.autokrew.models.AttendanceRegisterModel;
import com.autokrew.models.GroupLeaveModel;
import com.autokrew.models.LeaveRegisterTeamGroupModel;
import com.autokrew.models.TeamOrGroupLeaveModelParams;
import com.autokrew.network.ApiListener;
import com.autokrew.network.WebServices;
import com.autokrew.utils.Constant;
import com.autokrew.utils.Pref;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class LeaveRegisterFragment extends Fragment  implements ApiListener {

    RecyclerView rv_group_leave;
    LeaveRegisterTeamGroupModel model ;

    LeaveRegisterAdapter mAdapter;

    String LeaveStatusFK ;
    String mMonthPK,mYear;
    String mEmployeePK,CompanyFK,LocationFK,SubLocationFK,VerticalFK,DepartmentFK,SubDepartmentFK,DesignationFK;

    String from_last;
    CardView card_view;

    public LeaveRegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_leave_request_register, container, false);
        Constant.LAST_ACTIVITY = "GroupLeaveActivity";
        getData();
        findView(view);
        setData();

        return view;
    }


    private void getData() {

        if(Pref.getValue(getActivity(),"call_team_or_group_leave","").equalsIgnoreCase("group")){
            CompanyFK =  Pref.getValue(getActivity(),"CompanyFK","");
            LocationFK = Pref.getValue(getActivity(),"LocationFK","");
            SubLocationFK = Pref.getValue(getActivity(),"SubLocationFK","");
            VerticalFK =  Pref.getValue(getActivity(),"VerticalFK","");
            DepartmentFK = Pref.getValue(getActivity(),"DepartmentFK","");
            SubDepartmentFK =  Pref.getValue(getActivity(),"SubDepartmentFK","");
            DesignationFK = Pref.getValue(getActivity(),"DesignationFK","");

        }
        LeaveStatusFK =  Pref.getValue(getActivity(),"LeaveStatusFK","");
        mMonthPK =  Pref.getValue(getActivity(),"mMonthPK","");
        mYear =  Pref.getValue(getActivity(),"mYear","");
        mEmployeePK =  Pref.getValue(getActivity(),"mEmployeePK","");

    }
    private void findView(View view) {
        rv_group_leave = (RecyclerView) view.findViewById(R.id.rv_group_leave);
        card_view = (CardView) view.findViewById(R.id.card_view);
        card_view.setVisibility(View.GONE);
    }

    private void setData() {

        //setupRecyclerView();

        //api calls for leave listing as per filter...
        TeamOrGroupLeaveModelParams params = new TeamOrGroupLeaveModelParams();
        params.setFlag("Grid");
        params.setSessionEmployeeFk(Pref.getValue(getActivity(),Constant.PREF_SESSION_EMPLOYEE_FK,0));
        params.setMonth(Integer.parseInt(mMonthPK)); //
        params.setYear(Integer.parseInt(mYear)); //
        params.setLeaveStatusFK(Integer.parseInt(LeaveStatusFK));
        params.setEmployeeFK(Integer.parseInt(mEmployeePK)); //team member fk
        params.setTabFlag("Leave_Register_App");

        if(Pref.getValue(getActivity(),"call_team_or_group_leave","").equalsIgnoreCase("group")){
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

        String mToken = Pref.getValue(getActivity(),Constant.PREF_TOKEN,"");


        new WebServices(getActivity()/* ActivityContext */, this /* ApiListener */,
                true /* show progress dialog */,true).
                callGetTeamLeaveAPI(mToken,params ,from_last);

    }

    @Override
    public void onApiSuccess(Object mObject) {
        if (mObject instanceof String) {
            //LoginModel model = (LoginModel) mObject;
            // Log.e("", "onApiSuccess: >>  "+mObject.toString() );
            try {
                JSONObject jsonObj = new JSONObject(mObject.toString());
                Log.e("", "onApiSuccess: Leave_Register >>  "+jsonObj.toString());
                Gson gson = new Gson();
                model = gson.fromJson(mObject.toString(), LeaveRegisterTeamGroupModel.class);
                //setupRecyclerView(model);
                if(model!=null){
                    if(model.getTable().size()==0){
                        //no record found
                        card_view.setVisibility(View.VISIBLE);
                        rv_group_leave.setVisibility(View.GONE);
                    }else{
                        card_view.setVisibility(View.GONE);
                        rv_group_leave.setVisibility(View.VISIBLE);
                        //  apicallsforStatus();
                        setRecyclerView(model);
                    }
                }
                else{
                    card_view.setVisibility(View.VISIBLE);
                    rv_group_leave.setVisibility(View.GONE);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onApiFailure(Throwable mThrowable) {

    }

    private void setRecyclerView(LeaveRegisterTeamGroupModel model) {
        //setup recycler view
        rv_group_leave.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_group_leave.setHasFixedSize(true);
        // mList = new ArrayList<>();
        mAdapter = new LeaveRegisterAdapter(getActivity(), model);
        rv_group_leave.setAdapter(mAdapter);
    }
}
