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
import com.autokrew.adapter.AttendanceDeviationAdapter;
import com.autokrew.dialogs.AttendanceTeamGroupDialog;
import com.autokrew.interfaces.AttendanceDialogInterface;
import com.autokrew.interfaces.RecyclerViewClickListener;
import com.autokrew.models.AttendanceDeviationTGModel;
import com.autokrew.models.AttendanceModelParams;
import com.autokrew.models.AttendanceTeamGroupModel;
import com.autokrew.network.ApiListener;
import com.autokrew.network.WebServices;
import com.autokrew.utils.CommonUtils;
import com.autokrew.utils.Constant;
import com.autokrew.utils.Pref;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class AttendanceDeviationFragment extends Fragment implements ApiListener, View.OnClickListener,
        RecyclerViewClickListener,AttendanceDialogInterface {

    private RecyclerView rv_attendance_deviation;
    CardView card_view;
    AttendanceDeviationAdapter mAdapter;
    Toolbar toolbar;
    List<AttendanceTeamGroupModel> mList;
    AttendanceDeviationTGModel model;
    //AttendanceStatusModel mStatusModel;
   // AttendanceRegisterModel mRegisterModel;

    AttendanceTeamGroupDialog mDialog;
    String mApprovalStatus ;
    String mMonthPK,mYear;
    String mEmployeePK,CompanyFK,LocationFK,SubLocationFK,VerticalFK,DepartmentFK,SubDepartmentFK,DesignationFK;

    String from_last;
    String _flag;

    public AttendanceDeviationFragment() {
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
        setData();

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

    private void findView(View view) {
        rv_attendance_deviation = (RecyclerView) view.findViewById(R.id.rv_attendance_deviation);
        card_view = (CardView) view.findViewById(R.id.card_view);
        card_view.setVisibility(View.GONE);

    }

    private void setData() {

        if (CommonUtils.getInstance().isNetworkAvailable(getActivity())) {

            // callAPI(getActivity());
            //  if (validate()) {
            //{"SessionUserFk":8816,"MonthFK":8,"YearFk":2017,"EmployeeFK":-1,
            // "ApprovalStatus":"1","Flag":"Employee_Attendance_Register"}

            //Flag : (1)Attendance_Deviations  : For Deviation  list of Team Member
//                   (2)Employee_Attendance_Status : For Attendance Status
//                   (3) Employee_Attendance_Register : For Monthly Attendance Register

            AttendanceModelParams params = new AttendanceModelParams();
            params.setMonthFK(Integer.parseInt(mMonthPK)); //
            params.setSessionUserFk(Pref.getValue(getActivity(),Constant.PREF_SESSION_EMPLOYEE_FK,0));
            params.setYearFk(Integer.parseInt(mYear)); //
            params.setApprovalStatus(mApprovalStatus);
            params.setFlag("Attendance_Deviations");
            _flag = "Attendance_Deviations";
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


        } else {
            CommonUtils.getInstance().displayToast(getActivity(), Constant.INTERNET_FAILURE);
        }

    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public void onApiSuccess(Object mObject) {
        //set adapter here

        if (mObject instanceof String) {
            //LoginModel model = (LoginModel) mObject;
            // Log.e("", "onApiSuccess: >>  "+mObject.toString() );
            try {
                JSONObject jsonObj = new JSONObject(mObject.toString());
                Log.e("", "onApiSuccess:AttendanceDeviationTG json >>  "+jsonObj);
                Gson gson = new Gson();
                if(_flag.equalsIgnoreCase("Attendance_Deviations")){
                    model = gson.fromJson(mObject.toString(), AttendanceDeviationTGModel.class);
                    //setupRecyclerView(model);
                    if(model.getTable().size()==0){
                        //no record found
                        card_view.setVisibility(View.VISIBLE);
                        rv_attendance_deviation.setVisibility(View.GONE);
                    }else{
                        card_view.setVisibility(View.GONE);
                        rv_attendance_deviation.setVisibility(View.VISIBLE);
                      //  apicallsforStatus();
                        setupRecyclerView(model);
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

    private void setupRecyclerView(AttendanceDeviationTGModel model) {

        rv_attendance_deviation.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_attendance_deviation.setHasFixedSize(true);
        // mList = new ArrayList<>();
        mAdapter = new AttendanceDeviationAdapter(getActivity(), model, AttendanceDeviationFragment.this);
        rv_attendance_deviation.setAdapter(mAdapter);
    }

    @Override
    public void recallAllAPI() {
        setData();
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {

        mDialog = new AttendanceTeamGroupDialog(getActivity(), position ,model.getTable().get(position).getName(),
                model.getTable().get(position).getDate(), model.getTable().get(position).getAttendancePK()
                ,AttendanceDeviationFragment.this);
        mDialog.setCancelable(false);
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.show();
    }
}
