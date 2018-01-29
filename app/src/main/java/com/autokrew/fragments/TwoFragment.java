package com.autokrew.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.autokrew.R;
import com.autokrew.adapter.AttendanceStatusAdapter;
import com.autokrew.models.AttendanceModelParams;
import com.autokrew.models.AttendanceStatusModel;
import com.autokrew.network.ApiListener;
import com.autokrew.network.WebServices;
import com.autokrew.utils.Constant;
import com.autokrew.utils.Pref;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;


public class TwoFragment extends Fragment implements ApiListener {

    private RecyclerView rv_attendance_deviation;
    CardView card_view;

    AttendanceStatusAdapter mAdapter;

    String mApprovalStatus ;
    String mMonthPK,mYear;
    String mEmployeePK,CompanyFK,LocationFK,SubLocationFK,VerticalFK,DepartmentFK,SubDepartmentFK,DesignationFK;

    String from_last;
    String _flag;

    AttendanceStatusModel model;

    public TwoFragment() {
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
        View view = inflater.inflate(R.layout.fragment_attendance_deviation, container, false);
        Constant.LAST_ACTIVITY = "AttendanceDeviationActivity";


        getData();
        findView(view);
        apicallsforStatus();



        return view;
    }

    private void getData() {

        if(Pref.getValue(getActivity(),"call_team_or_group","").equalsIgnoreCase("group")){
            CompanyFK =  Pref.getValue(getActivity(),"CompanyFK","");
            LocationFK = Pref.getValue(getActivity(),"LocationFK","");
            SubLocationFK = Pref.getValue(getActivity(),"SubLocationFK","");
            VerticalFK =  Pref.getValue(getActivity(),"VerticalFK","");
            DepartmentFK = Pref.getValue(getActivity(),"DepartmentFK","");
            SubDepartmentFK =  Pref.getValue(getActivity(),"SubDepartmentFK","");
            DesignationFK = Pref.getValue(getActivity(),"DesignationFK","");

        }
        mApprovalStatus =  Pref.getValue(getActivity(),"mApprovalStatus","");
        mMonthPK =  Pref.getValue(getActivity(),"mMonthPK","");
        mYear =  Pref.getValue(getActivity(),"mYear","");
        mEmployeePK =  Pref.getValue(getActivity(),"mEmployeePK","");

    }

    private void setRecyclerView(AttendanceStatusModel mStatusModel) {

        if(mStatusModel.getTable()!=null){
            if(mStatusModel.getTable().size()==0){
                card_view.setVisibility(View.VISIBLE);
                rv_attendance_deviation.setVisibility(View.GONE);
            }
            else{
                card_view.setVisibility(View.GONE);
                rv_attendance_deviation.setVisibility(View.VISIBLE);
                //setup recycler view
                rv_attendance_deviation.setLayoutManager(new LinearLayoutManager(getActivity()));
                rv_attendance_deviation.setHasFixedSize(true);
                // mList = new ArrayList<>();
                mAdapter = new AttendanceStatusAdapter(getActivity(), mStatusModel);
                rv_attendance_deviation.setAdapter(mAdapter);
            }
        }



    }

    private void findView(View view) {
        rv_attendance_deviation = (RecyclerView) view.findViewById(R.id.rv_attendance_deviation);
        card_view = (CardView) view.findViewById(R.id.card_view);
        card_view.setVisibility(View.GONE);
    }


    private void apicallsforStatus() {

        AttendanceModelParams params = new AttendanceModelParams();
        params.setMonthFK(Integer.parseInt(mMonthPK)); //
        params.setSessionUserFk(Pref.getValue(getActivity(),Constant.PREF_SESSION_EMPLOYEE_FK,0));
        params.setYearFk(Integer.parseInt(mYear)); //
        params.setApprovalStatus(mApprovalStatus);
        params.setFlag("Employee_Attendance_Status");
        _flag = "Employee_Attendance_Status";
        params.setEmployeeFK(Integer.parseInt(mEmployeePK)); //team member fk

        if(Pref.getValue(getActivity(),"call_team_or_group","").equalsIgnoreCase("group")){
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
                false /* show progress dialog */,true).
                callGetAttendanceAPI(mToken,params ,from_last);
        //  }

    }



    @Override
    public void onApiSuccess(Object mObject) {
        if (mObject instanceof String) {
            //LoginModel model = (LoginModel) mObject;
            // Log.e("", "onApiSuccess: >>  "+mObject.toString() );
            try {
                JSONObject jsonObj = new JSONObject(mObject.toString());
                Log.e("", "onApiSuccess: json >>  "+jsonObj);
                Gson gson = new Gson();
                if(_flag.equalsIgnoreCase("Employee_Attendance_Status")){
                    model = gson.fromJson(mObject.toString(), AttendanceStatusModel.class);
                    //setupRecyclerView(model);
                    if(model!=null){
                        if(model.getTable().size()==0){
                            //no record found
                            card_view.setVisibility(View.VISIBLE);
                            rv_attendance_deviation.setVisibility(View.GONE);
                        }else{
                            card_view.setVisibility(View.GONE);
                            rv_attendance_deviation.setVisibility(View.VISIBLE);
                            //  apicallsforStatus();
                            setRecyclerView(model);
                        }
                    }

                    else{
                        card_view.setVisibility(View.VISIBLE);
                        rv_attendance_deviation.setVisibility(View.GONE);
                    }

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
