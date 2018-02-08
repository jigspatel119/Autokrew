package com.autokrew.fragments;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.autokrew.R;
import com.autokrew.activity.GroupLeaveActivity;
import com.autokrew.activity.LeaveActivity;
import com.autokrew.interfaces.RecyclerViewClickListener;
import com.autokrew.models.CommonDetailModel;
import com.autokrew.models.CommonDetailModelParams;
import com.autokrew.models.DepartmentModel;
import com.autokrew.models.DropdownModel;
import com.autokrew.models.LocationModel;
import com.autokrew.models.SubDepartmentModel;
import com.autokrew.models.SubLocationModel;
import com.autokrew.models.TeamMemberModel;
import com.autokrew.models.TeamMemberModelParams;
import com.autokrew.network.ApiListener;
import com.autokrew.network.WebServices;
import com.autokrew.utils.CommonUtils;
import com.autokrew.utils.Constant;
import com.autokrew.utils.Pref;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;



public class GroupLeaveFragment extends Fragment implements ApiListener, RecyclerViewClickListener, View.OnClickListener {

    View view;
    String str_name, str_disname;

    TextView txt_search;

    TextView txt_child_item;

    ImageView iv_month, iv_company, iv_location, iv_sub_location, iv_vertical, iv_department, iv_sub_department, iv_designation, iv_year, iv_team_member, iv_leave_status;

    Spinner edt_month, edt_company, edt_location, edt_sub_location, edt_vertical, edt_department, edt_sub_department, edt_designation, edt_year, edt_team_member, edt_leave_status;

    LinearLayout ll_designation, ll_company, ll_location, ll_sublocation, ll_vertical, ll_department, ll_subdepartment;
    String call_team_or_group = "";
    String mToken;

    // response model class =====================================
    CommonDetailModelParams params;
    CommonDetailModel modelCommon;
    TeamMemberModel modelTeam;
    LocationModel modelLocation;
    SubLocationModel modelSubLocation;
    DepartmentModel modelDepartment;
    SubDepartmentModel modelSubDepartment;


    //res[ponse arrylist ==================================================
    ArrayList<DropdownModel> mYearList = new ArrayList<>();
    ArrayList<DropdownModel> mMonthList = new ArrayList<>();
    ArrayList<DropdownModel> mTeamMemberList = new ArrayList<>();
    ArrayList<DropdownModel> mLeaveStatusList = new ArrayList<>();


    ArrayList<DropdownModel> mCompanyList = new ArrayList<>();
    ArrayList<DropdownModel> mVerticalList = new ArrayList<>();
    ArrayList<DropdownModel> mDesignationList = new ArrayList<>();
    ArrayList<DropdownModel> mLocationList = new ArrayList<>();
    ArrayList<DropdownModel> mSubLocationList = new ArrayList<>();
    ArrayList<DropdownModel> mDepartmentList = new ArrayList<>();
    ArrayList<DropdownModel> mSubDepartmentList = new ArrayList<>();

    // for check ----
    int checkCompany = 0;
    int checkLocation = 0;
    int checkSubLocation = 0;

    int checkVertical = 0;
    int checkDepartment = 0;
    int checkSubDepartment = 0;
    int checkDesignation = 0;
    int checkYear = 0;

    //  String mApprovalStatus = "";


    Dialog dialog;
    Handler handler;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_group_leave, container, false);

        getData();
        findView(view);
        setData();
        setListner();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mToken = Pref.getValue(getActivity(), Constant.PREF_TOKEN, "");

        //clear pref...
        // Pref.setValue(getActivity(),"mApprovalStatus","");
        Pref.setValue(getActivity(), "mYearPK", "");
        Pref.setValue(getActivity(), "mMonthPK", "");
        Pref.setValue(getActivity(), "mEmployeePK", "");

        //call common detail first
        setupDropDown();


    }

    private void getData() {
        str_name = getArguments().getString("name");
        str_disname = getArguments().getString("dish");
        //str_des = getArguments().getString("des");
        //str_imagename = getArguments().getString("image");
    }

    private void setData() {
        txt_child_item.setText(str_disname);

        if (str_disname.equalsIgnoreCase("Team Leave")) {
            //hide some filters
            ll_designation.setVisibility(View.GONE);
            ll_company.setVisibility(View.GONE);
            ll_location.setVisibility(View.GONE);
            ll_sublocation.setVisibility(View.GONE);
            ll_vertical.setVisibility(View.GONE);
            ll_department.setVisibility(View.GONE);
            ll_subdepartment.setVisibility(View.GONE);

            call_team_or_group = "team";
        } else {
            call_team_or_group = "group";
        }

        setPleaseSelect("Location");
        setPleaseSelect("SubLocation");
        setPleaseSelect("Department");
        setPleaseSelect("SubDepartment");


        edt_company.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                if (++checkCompany > 1) {
                    // CommonUtils.getInstance().displayToast(getActivity(),mCompanyList.get(position).getCompanyPK()); //pk

                    if (mCompanyList.get(position).getCompanyPK().equalsIgnoreCase("-1")) {
                        setPleaseSelect("Location");
                    } else {
                        //api calls
                        params = new CommonDetailModelParams();
                        params.setFlag("Location");
                        params.setTranTypes(Integer.parseInt(mCompanyList.get(position).getCompanyPK()));
                        //call common detail api here..
                        new WebServices(getActivity()/* ActivityContext */, GroupLeaveFragment.this /* ApiListener */,
                                false /* show progress dialog */, true).
                                callGetCommonDetailAPI(mToken, params); //from_last = ""
                    }

                    //bind employee
                    bindEmployee("");

                } else {
                    //  CommonUtils.getInstance().displayToast(getActivity(),"its kns"); //pk
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        edt_location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                // CommonUtils.getInstance().displayToast(getActivity(),mLocationList.get(position).getLocationPK()); //pk
                if (++checkLocation > 1) {

                    if (mLocationList.get(position).getLocationPK().equalsIgnoreCase("-1")) {
                        setPleaseSelect("SubLocation");
                    } else {
                        params = new CommonDetailModelParams();
                        params.setFlag("SubLocation");
                        params.setTranTypes(Integer.parseInt(mLocationList.get(position).getLocationPK()));
                        //call common detail api here..
                        new WebServices(getActivity()/* ActivityContext */, GroupLeaveFragment.this /* ApiListener */,
                                false /* show progress dialog */, true).
                                callGetCommonDetailAPI(mToken, params); //from_last = ""
                        //bind employee
                        bindEmployee("");

                    }
                } else {
                    // CommonUtils.getInstance().displayToast(getActivity(),"its kns"); //pk
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        edt_sub_location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                //CommonUtils.getInstance().displayToast(getActivity(),mSubLocationList.get(position).getSubLocationPK()); //pk
                if (++checkSubLocation > 1) {
                    // bind employee
                    bindEmployee("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });


        edt_vertical.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                //  CommonUtils.getInstance().displayToast(getActivity(),mVerticalList.get(position).getVerticalPK()); //pk

                if (++checkVertical > 1) {

                    if (mVerticalList.get(position).getVerticalPK().equalsIgnoreCase("-1")) {
                        setPleaseSelect("Department");
                    } else {
                        //api calls
                        params = new CommonDetailModelParams();
                        params.setFlag("Department");
                        params.setTranTypes(Integer.parseInt(mVerticalList.get(position).getVerticalPK()));
                        new WebServices(getActivity()/* ActivityContext */, GroupLeaveFragment.this /* ApiListener */,
                                false /* show progress dialog */, true).
                                callGetCommonDetailAPI(mToken, params); //from_last = ""
                    }

                    //bind employee
                    bindEmployee("");
                } else {
                    //CommonUtils.getInstance().displayToast(getActivity(),"its kns"); //pk
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        edt_department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (++checkDepartment > 1) {

                    if (mDepartmentList.get(position).getDepartmentPK().equalsIgnoreCase("-1")) {

                        setPleaseSelect("SubDepartment");

                    } else {

                        params = new CommonDetailModelParams();
                        params.setFlag("SubDepartment");
                        params.setTranTypes(Integer.parseInt(mDepartmentList.get(position).getDepartmentPK()));
                        //call common detail api here..
                        new WebServices(getActivity()/* ActivityContext */, GroupLeaveFragment.this /* ApiListener */,
                                false /* show progress dialog */, true).
                                callGetCommonDetailAPI(mToken, params); //from_last = ""
                    }
                    //bind employee
                    bindEmployee("");
                } else {
                    // CommonUtils.getInstance().displayToast(getActivity(),"its kns"); //pk
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here

            }
        });

        edt_sub_department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (++checkSubDepartment > 1) {
                    // bind employee
                    bindEmployee("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        edt_designation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (++checkDesignation > 1) {
                    // bind employee
                    bindEmployee("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });


        edt_team_member.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                // CommonUtils.getInstance().displayToast(getActivity(),mTeamMemberList.get(position).getEmployeePK()); //pk
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
    }


    private void setPleaseSelect(String from_last) {

        if (from_last.equalsIgnoreCase("Location")) {
            mLocationList.clear();
            DropdownModel model;
            model = new DropdownModel();
            model.setLocationPK("-1");
            model.setLocation("Please Select");
            mLocationList.add(model);

            ArrayList<String> mName = new ArrayList<>();
            for (int i = 0; i < mLocationList.size(); i++) {
                mName.add(mLocationList.get(i).getLocation());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.selected_item, mName);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            edt_location.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }

        if (from_last.equalsIgnoreCase("SubLocation")) {
            mSubLocationList.clear();
            DropdownModel model;
            model = new DropdownModel();
            model.setSubLocationPK("-1");
            model.setSubLocation("Please Select");
            mSubLocationList.add(model);

            ArrayList<String> mName = new ArrayList<>();
            for (int i = 0; i < mSubLocationList.size(); i++) {
                mName.add(mSubLocationList.get(i).getSubLocation());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.selected_item, mName);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            edt_sub_location.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }


        if (from_last.equalsIgnoreCase("Department")) {
            mDepartmentList.clear();
            DropdownModel model;
            model = new DropdownModel();
            model.setDepartmentPK("-1");
            model.setDepartment("Please Select");
            mDepartmentList.add(model);

            ArrayList<String> mName = new ArrayList<>();
            for (int i = 0; i < mDepartmentList.size(); i++) {
                mName.add(mDepartmentList.get(i).getDepartment());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.selected_item, mName);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            edt_department.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }


        if (from_last.equalsIgnoreCase("SubDepartment")) {
            mSubDepartmentList.clear();
            DropdownModel model;
            model = new DropdownModel();
            model.setSubDepartmentPK("-1");
            model.setSubDepartment("Please Select");
            mSubDepartmentList.add(model);

            ArrayList<String> mName = new ArrayList<>();
            for (int i = 0; i < mSubDepartmentList.size(); i++) {
                mName.add(mSubDepartmentList.get(i).getSubDepartment());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.selected_item, mName);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            edt_sub_department.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }


    }

    private void setListner() {

        iv_company.setOnClickListener(this);
        iv_month.setOnClickListener(this);
        iv_location.setOnClickListener(this);
        iv_sub_location.setOnClickListener(this);
        iv_vertical.setOnClickListener(this);
        iv_department.setOnClickListener(this);
        iv_sub_department.setOnClickListener(this);
        iv_designation.setOnClickListener(this);
        iv_year.setOnClickListener(this);
        iv_team_member.setOnClickListener(this);
        iv_leave_status.setOnClickListener(this);

        txt_search.setOnClickListener(this);


    }

    private void findView(View v) {

        txt_child_item = (TextView) v.findViewById(R.id.txt_child_item);

        ll_designation = (LinearLayout) v.findViewById(R.id.ll_designation);
        ll_company = (LinearLayout) v.findViewById(R.id.ll_company);
        ll_location = (LinearLayout) v.findViewById(R.id.ll_location);
        ll_sublocation = (LinearLayout) v.findViewById(R.id.ll_sublocation);
        ll_vertical = (LinearLayout) v.findViewById(R.id.ll_vertical);
        ll_department = (LinearLayout) v.findViewById(R.id.ll_department);
        ll_subdepartment = (LinearLayout) v.findViewById(R.id.ll_subdepartment);


        iv_month = (ImageView) v.findViewById(R.id.iv_month);
        iv_company = (ImageView) v.findViewById(R.id.iv_company);
        iv_location = (ImageView) v.findViewById(R.id.iv_location);
        iv_sub_location = (ImageView) v.findViewById(R.id.iv_sub_location);
        iv_vertical = (ImageView) v.findViewById(R.id.iv_vertical);
        iv_department = (ImageView) v.findViewById(R.id.iv_department);
        iv_sub_department = (ImageView) v.findViewById(R.id.iv_sub_department);
        iv_designation = (ImageView) v.findViewById(R.id.iv_designation);
        iv_year = (ImageView) v.findViewById(R.id.iv_year);
        iv_team_member = (ImageView) v.findViewById(R.id.iv_team_member);
        iv_leave_status = (ImageView) v.findViewById(R.id.iv_leave_status);


        edt_month = (Spinner) v.findViewById(R.id.edt_month);
        edt_company = (Spinner) v.findViewById(R.id.edt_company);
        edt_location = (Spinner) v.findViewById(R.id.edt_location);
        edt_sub_location = (Spinner) v.findViewById(R.id.edt_sub_location);
        edt_vertical = (Spinner) v.findViewById(R.id.edt_vertical);
        edt_department = (Spinner) v.findViewById(R.id.edt_department);
        edt_sub_department = (Spinner) v.findViewById(R.id.edt_sub_department);
        edt_designation = (Spinner) v.findViewById(R.id.edt_designation);

        edt_year = (Spinner) v.findViewById(R.id.edt_year);
        edt_team_member = (Spinner) v.findViewById(R.id.edt_team_member);
        edt_leave_status = (Spinner) v.findViewById(R.id.edt_leave_status);

        txt_search = (TextView) v.findViewById(R.id.txt_search);

        /*Typeface copperplateGothicLight = Typeface.createFromAsset(getAppContext().getAssets(), "GillSans-SemiBold.ttf");
        btn_search.setTypeface(copperplateGothicLight);*/

        dialog = new Dialog(getActivity(), R.style.progressDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progress_dialog);
        dialog.show();

        if(call_team_or_group.equalsIgnoreCase("team")){
            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    dialog.dismiss();

                }
            }, 3000);
        }



    }

    private void setupDropDown() {


        if (CommonUtils.getInstance().isNetworkAvailable(getActivity())) {
            params = new CommonDetailModelParams();
            params.setFlag("common");
            params.setTranTypes(-1);

            //call common detail api here..
            new WebServices(getActivity()/* ActivityContext */, this /* ApiListener */,
                    false /* show progress dialog */, true).
                    callGetCommonDetailAPI(mToken, params); //from_last = ""
        } else {
            CommonUtils.getInstance().displayToast(getActivity(), Constant.INTERNET_FAILURE);

        }


    }

    /**
     * On Api success call
     *
     * @param mObject : Object of api model class
     */
    @Override
    public void onApiSuccess(Object mObject) {

        //set adapter here
        if (mObject instanceof String) {
            try {
                JSONObject jsonObj = new JSONObject(mObject.toString());
                Log.e("", "onApiSuccess: json >>  " + jsonObj.toString());

                if(jsonObj.getJSONArray("Table").length()==0){
                    setTeamMemberForEmptydata();
                }

                //for very first time
                if (jsonObj.toString().contains("nationalPK")) {
                    //parse common detail data
                    Log.e("", "onApiSuccess: common json >>  " + jsonObj.toString());
                    Gson gson = new Gson();
                    modelCommon = gson.fromJson(mObject.toString(), CommonDetailModel.class);
                    //api calls for original data...

                    setCompany(modelCommon);
                    setVertical(modelCommon);
                    setDesignation(modelCommon);
                    setMonth(modelCommon);
                    setYear(modelCommon);
                    setApprovalStatus(modelCommon);


                    //check from team or from group ?
                    // if team -> direct bind employee ,if group-> as per the filter bind employee
                    //pending...

                    bindEmployee("common");

                }

                //for company change...
                else if (jsonObj.toString().contains("LocationPK") && params.getFlag().equalsIgnoreCase("Location")) {
                    Log.e("", "onApiSuccess: company->location json >>  " + jsonObj.toString());
                    Gson gson = new Gson();
                    modelLocation = gson.fromJson(mObject.toString(), LocationModel.class);
                    setLocation(modelLocation);

                } else if (jsonObj.toString().contains("SubLocationPK") && params.getFlag().equalsIgnoreCase("SubLocation")) {
                    Log.e("", "onApiSuccess: location->sublocation json >>  " + jsonObj.toString());
                    Gson gson = new Gson();
                    modelSubLocation = gson.fromJson(mObject.toString(), SubLocationModel.class);
                    setSubLocation(modelSubLocation);

                } else if (jsonObj.toString().contains("DepartmentPK") && params.getFlag().equalsIgnoreCase("Department")) {
                    Log.e("", "onApiSuccess: vertical->department json >>  " + jsonObj.toString());
                    Gson gson = new Gson();
                    modelDepartment = gson.fromJson(mObject.toString(), DepartmentModel.class);
                    setDepartment(modelDepartment);

                } else if (jsonObj.toString().contains("SubDepartmentPK") && params.getFlag().equalsIgnoreCase("SubDepartment")) {
                    Log.e("", "onApiSuccess: department->sub department json >>  " + jsonObj.toString());
                    Gson gson = new Gson();
                    modelSubDepartment = gson.fromJson(mObject.toString(), SubDepartmentModel.class);
                    setSubDepartment(modelSubDepartment);

                } else if (jsonObj.toString().contains("EmployeePK")) {
                    // parse team member json
                    if(dialog!=null){
                        if(dialog.isShowing()){
                            dialog.dismiss();
                        }
                    }
                    Log.e("", "onApiSuccess: Team member json >>  " + jsonObj.toString());
                    Gson gson = new Gson();
                    modelTeam = gson.fromJson(mObject.toString(), TeamMemberModel.class);
                    setTeamMember(modelTeam);
                }

            } catch (JSONException e) {
                dialog.dismiss();
                e.printStackTrace();
            }
        }

    }

    /**
     * On Api failure call
     *
     * @param mThrowable : Object of api failure
     */
    @Override
    public void onApiFailure(Throwable mThrowable) {


    }

    @Override
    public void recyclerViewListClicked(View v, int position) {


        //CommonUtils.getInstance().displayToast(getActivity(),"pos >>" +position);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.txt_search:

                //CommonUtils.getInstance().startActivity(getActivity(), GroupLeaveActivity.class);
                // getActivity().overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);

              /*  if(edt_leave_status.getItemAtPosition(edt_leave_status.getSelectedItemPosition()).toString().equalsIgnoreCase("pending")){
                    mApprovalStatus = "-1";
                }
                else if(edt_leave_status.getItemAtPosition(edt_leave_status.getSelectedItemPosition()).toString().equalsIgnoreCase("All")){
                    mApprovalStatus = "0";
                }

                else if(edt_leave_status.getItemAtPosition(edt_leave_status.getSelectedItemPosition()).toString().equalsIgnoreCase("Approved Full Day")){
                    mApprovalStatus = "1";
                }
                else if(edt_leave_status.getItemAtPosition(edt_leave_status.getSelectedItemPosition()).toString().equalsIgnoreCase("Approved Half Day")){
                    mApprovalStatus = "2";
                }
                else if(edt_leave_status.getItemAtPosition(edt_leave_status.getSelectedItemPosition()).toString().equalsIgnoreCase("Rejected")){
                    mApprovalStatus = "3";
                }*/

                //CommonUtils.getInstance().displayToast(getActivity(),""+edt_approval_status.getItemAtPosition(edt_approval_status.getSelectedItemPosition()).toString());

                //Bundle mBundle = new Bundle();
                // mBundle.putString("mApprovalStatus",mApprovalStatus);
                //clear below pref....
                Pref.setValue(getActivity(), "LeaveStatusFK", mLeaveStatusList.get(edt_leave_status.getSelectedItemPosition()).getLeaveStatusPK());
                Pref.setValue(getActivity(), "mYear", edt_year.getSelectedItem().toString());
                Pref.setValue(getActivity(), "mMonthPK", mMonthList.get(edt_month.getSelectedItemPosition()).getMonthPk());
                Pref.setValue(getActivity(), "mEmployeePK", mTeamMemberList.get(edt_team_member.getSelectedItemPosition()).getEmployeePK());


                //for group
                if (call_team_or_group.equalsIgnoreCase("group")) {
                    Pref.setValue(getActivity(), "call_team_or_group_leave", "group");

                    Pref.setValue(getActivity(), "CompanyFK", mCompanyList.get(edt_company.getSelectedItemPosition()).getCompanyPK());
                    Pref.setValue(getActivity(), "LocationFK", mLocationList.get(edt_location.getSelectedItemPosition()).getLocationPK());
                    Pref.setValue(getActivity(), "SubLocationFK", mSubLocationList.get(edt_sub_location.getSelectedItemPosition()).getSubLocationPK());
                    Pref.setValue(getActivity(), "VerticalFK", mVerticalList.get(edt_vertical.getSelectedItemPosition()).getVerticalPK());
                    Pref.setValue(getActivity(), "DepartmentFK", mDepartmentList.get(edt_department.getSelectedItemPosition()).getDepartmentPK());
                    Pref.setValue(getActivity(), "SubDepartmentFK", mSubDepartmentList.get(edt_sub_department.getSelectedItemPosition()).getSubDepartmentPK());
                    Pref.setValue(getActivity(), "DesignationFK", mDesignationList.get(edt_designation.getSelectedItemPosition()).getDesignationPK());
                } else {
                    Pref.setValue(getActivity(), "call_team_or_group_leave", "team");
                }
                //for team no need to store above details.

                CommonUtils.getInstance().startActivity(getActivity(), LeaveActivity.class);
                //CommonUtils.getInstance().startActivity(getActivity(), GroupLeaveActivity.class);
                getActivity().overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_scale);

                break;

            case R.id.iv_month:

                break;


            case R.id.iv_company:

                break;
            case R.id.iv_location:

                break;

            case R.id.iv_sub_location:

                break;
            case R.id.iv_vertical:

                break;

            case R.id.iv_department:

                break;
            case R.id.iv_sub_department:

                break;

            case R.id.iv_designation:

                break;
            case R.id.iv_year:

                break;

            case R.id.iv_team_member:

                break;
            case R.id.iv_leave_status:

                break;

        }

    }

    private void bindEmployee(String from_last) {


        TeamMemberModelParams params = null;

        if (from_last.equalsIgnoreCase("common")) {
            params = new TeamMemberModelParams();
            params.setCompanyFK(-1);
            params.setLocationFK(-1);
            params.setSubLocationFK(-1);

            params.setVerticalFK(-1);
            params.setDepartmentFK(-1);
            params.setSubDepartmentFK(-1);
            // params.setIsDailyWages(0);

            params.setDesignationFK(-1);
            params.setSessionUserFk(Pref.getValue(getActivity(), Constant.PREF_SESSION_EMPLOYEE_FK, 0));

        } else {
            //for all filter from group...
            params = new TeamMemberModelParams();
            params.setCompanyFK(Integer.parseInt(mCompanyList.get(edt_company.getSelectedItemPosition()).getCompanyPK()));
            params.setLocationFK(Integer.parseInt(mLocationList.get(edt_location.getSelectedItemPosition()).getLocationPK()));
            params.setSubLocationFK(Integer.parseInt(mSubLocationList.get(edt_sub_location.getSelectedItemPosition()).getSubLocationPK()));

            params.setVerticalFK(Integer.parseInt(mVerticalList.get(edt_vertical.getSelectedItemPosition()).getVerticalPK()));
            params.setDepartmentFK(Integer.parseInt(mDepartmentList.get(edt_department.getSelectedItemPosition()).getDepartmentPK()));
            params.setSubDepartmentFK(Integer.parseInt(mSubDepartmentList.get(edt_sub_department.getSelectedItemPosition()).getSubDepartmentPK()));

            params.setDesignationFK(Integer.parseInt(mDesignationList.get(edt_designation.getSelectedItemPosition()).getDesignationPK()));

            // params.setIsDailyWages(0);
            params.setSessionUserFk(Pref.getValue(getActivity(), Constant.PREF_SESSION_EMPLOYEE_FK, 0));
        }


        //api calls with updated params
        new WebServices(getActivity(), this,
                false, true).
                callGetTeamMemberAPILeave(mToken, params, call_team_or_group);

    }


    //=====================set all dropdown for month/year /approval status==============================


    private void setYear(CommonDetailModel modelCommon) {
        DropdownModel model;
        mYearList.clear();

        for (int i = 0; i < modelCommon.getTable17().size(); i++) {
            model = new DropdownModel();
            model.setYearPk(modelCommon.getTable17().get(i).getYearPk());
            model.setYear(modelCommon.getTable17().get(i).getYear());
            mYearList.add(model);
        }

        ArrayList<String> mName = new ArrayList<>();
        for (int i = 0; i < mYearList.size(); i++) {
            mName.add(String.valueOf(mYearList.get(i).getYear()));
        }

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int c_year = calendar.get(Calendar.YEAR);

        int c_year_index = 0 ;
        for (int i = 0; i <mName.size() ; i++) {
            if(Integer.parseInt(mName.get(i)) == c_year){
                c_year_index = i;
            }
        }

        // String [] list = mCompanyList.toArray(new String[mCompanyList.size()]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.selected_item, mName);
        edt_year.setAdapter(adapter);
        edt_year.setSelection(c_year_index);

    }

    private void setMonth(CommonDetailModel modelCommon) {

        DropdownModel model;
        mMonthList.clear();

        for (int i = 0; i < modelCommon.getTable16().size(); i++) {
            model = new DropdownModel();
            model.setMonthPk(modelCommon.getTable16().get(i).getMonthPk());
            model.setMonth(modelCommon.getTable16().get(i).getMonth());
            mMonthList.add(model);
        }

        ArrayList<String> mName = new ArrayList<>();
        for (int i = 0; i < mMonthList.size(); i++) {
            mName.add(String.valueOf(mMonthList.get(i).getMonth()));
        }

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int c_month = calendar.get(Calendar.MONTH);
        String current_month = getMonthForInt(c_month);

        int c_month_index = 0 ;
        for (int i = 0; i <mName.size() ; i++) {
            if(mName.get(i).equalsIgnoreCase(current_month)){
                c_month_index = i;
            }
        }
        // String [] list = mCompanyList.toArray(new String[mCompanyList.size()]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.selected_item, mName);
        edt_month.setAdapter(adapter);
        edt_month.setSelection(c_month_index);

    }

    @TargetApi(Build.VERSION_CODES.N)
    String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11 ) {
            month = months[num];
        }
        return month;
    }


    private void setApprovalStatus(CommonDetailModel modelCommon) {
        DropdownModel model;
        mLeaveStatusList.clear();

        for (int i = 0; i < modelCommon.getTable28().size() + 1; i++) {
            model = new DropdownModel();
            if (i == 0) {
                model.setLeaveStatusPK("-1");
                model.setLeaveStatus("Please Select");
            } else {
                model.setLeaveStatusPK(modelCommon.getTable28().get(i - 1).getLeaveStatusPK());
                model.setLeaveStatus(modelCommon.getTable28().get(i - 1).getLeaveStatus());
            }
            mLeaveStatusList.add(model);
        }

        ArrayList<String> mName = new ArrayList<>();
        for (int i = 0; i < mLeaveStatusList.size(); i++) {
            mName.add(String.valueOf(mLeaveStatusList.get(i).getLeaveStatus()));
        }

        // String [] list = mCompanyList.toArray(new String[mCompanyList.size()]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.selected_item, mName);
        edt_leave_status.setAdapter(adapter);
    }

    private void setTeamMember(TeamMemberModel model) {

        DropdownModel model2;
        mTeamMemberList.clear();
        //   mCompanyList = new ArrayList<>();
        mTeamMemberList.clear();
        for (int i = 0; i < model.getTable().size() + 1; i++) {
            model2 = new DropdownModel();
            if (i == 0) {
                model2.setEmployeePK("-1");
                model2.setEmployee("Please Select");
            } else {
                model2.setEmployeePK(model.getTable().get(i - 1).getEmployeePK());
                model2.setEmployee(model.getTable().get(i - 1).getName());
            }
            mTeamMemberList.add(model2);
        }

        ArrayList<String> mName = new ArrayList<>();
        for (int i = 0; i < mTeamMemberList.size(); i++) {
            mName.add(mTeamMemberList.get(i).getEmployee());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.selected_item, mName);
        edt_team_member.setAdapter(adapter);
    }


    private void setTeamMemberForEmptydata() {

        DropdownModel model2;
        mTeamMemberList.clear();
        //   mCompanyList = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            model2 = new DropdownModel();
            if (i == 0) {
                model2.setEmployeePK("-1");
                model2.setEmployee("Please Select");

                mTeamMemberList.add(model2);
            }
        }

        ArrayList<String> mName = new ArrayList<>();
        for (int i = 0; i < mTeamMemberList.size(); i++) {
            mName.add(mTeamMemberList.get(i).getEmployee());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.selected_item, mName);
        edt_team_member.setAdapter(adapter);

    }


    // depended filter
    //company --> location --> sublocation
    private void setCompany(CommonDetailModel modelCommon) {
        DropdownModel model;
        mCompanyList.clear();
        //   mCompanyList = new ArrayList<>();

        for (int i = 0; i < modelCommon.getTable7().size() + 1; i++) {
            model = new DropdownModel();
            if (i == 0) {
                model.setCompanyPK("-1");
                model.setCompany("Please Select");
            } else {
                model.setCompanyPK(modelCommon.getTable7().get(i - 1).getCompanyPK());
                model.setCompany(modelCommon.getTable7().get(i - 1).getCompany());
            }
            mCompanyList.add(model);
        }

        ArrayList<String> mName = new ArrayList<>();
        for (int i = 0; i < mCompanyList.size(); i++) {
            mName.add(mCompanyList.get(i).getCompany());
        }

        // String [] list = mCompanyList.toArray(new String[mCompanyList.size()]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.selected_item, mName);
        edt_company.setAdapter(adapter);
    }

    private void setLocation(LocationModel modelLocation) {

        DropdownModel model;
        mLocationList.clear();
        //  mLocationList = new ArrayList<>();
        for (int i = 0; i < modelLocation.getTable().size() + 1; i++) {
            model = new DropdownModel();
            if (i == 0) {
                model.setLocationPK("-1");
                model.setLocation("Please Select");
            } else {
                model.setLocationPK(modelLocation.getTable().get(i - 1).getLocationPK());
                model.setLocation(modelLocation.getTable().get(i - 1).getLocation());
            }

            mLocationList.add(model);
        }

        ArrayList<String> mName = new ArrayList<>();
        for (int i = 0; i < mLocationList.size(); i++) {
            mName.add(mLocationList.get(i).getLocation());
        }

        // String [] list = mCompanyList.toArray(new String[mCompanyList.size()]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.selected_item, mName);
        edt_location.setAdapter(adapter);

    }

    private void setSubLocation(SubLocationModel modelSubLocation) {

        DropdownModel model;
        mSubLocationList.clear();
        // mSubLocationList = new ArrayList<>();
        for (int i = 0; i < modelSubLocation.getTable().size() + 1; i++) {
            model = new DropdownModel();
            if (i == 0) {
                model.setSubLocationPK("-1");
                model.setSubLocation("Please Select");
            } else {
                model.setSubLocationPK(modelSubLocation.getTable().get(i - 1).getSubLocationPK());
                model.setSubLocation(modelSubLocation.getTable().get(i - 1).getSubLocation());
            }

            mSubLocationList.add(model);
        }

        ArrayList<String> mName = new ArrayList<>();
        for (int i = 0; i < mSubLocationList.size(); i++) {
            mName.add(mSubLocationList.get(i).getSubLocation());
        }

        // String [] list = mCompanyList.toArray(new String[mCompanyList.size()]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.selected_item, mName);
        edt_sub_location.setAdapter(adapter);

    }


    // vertical --> dept. --> sub dept.

    private void setVertical(CommonDetailModel modelCommon) {

        DropdownModel model;
        mVerticalList.clear();
        // mVerticalList = new ArrayList<>();
        for (int i = 0; i < modelCommon.getTable8().size() + 1; i++) {
            model = new DropdownModel();
            if (i == 0) {
                model.setVerticalPK("-1");
                model.setVertical("Please Select");
            } else {
                model.setVerticalPK(modelCommon.getTable8().get(i - 1).getVerticalPK());
                model.setVertical(modelCommon.getTable8().get(i - 1).getVertical());
            }
            mVerticalList.add(model);
        }

        ArrayList<String> mName = new ArrayList<>();
        for (int i = 0; i < mVerticalList.size(); i++) {
            mName.add(mVerticalList.get(i).getVertical());
        }

        // String [] list = mCompanyList.toArray(new String[mCompanyList.size()]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.selected_item, mName);
        edt_vertical.setAdapter(adapter);
    }

    private void setDepartment(DepartmentModel modelDepartment) {

        DropdownModel model;
        mDepartmentList.clear();
        // mDepartmentList = new ArrayList<>();
        for (int i = 0; i < modelDepartment.getTable().size() + 1; i++) {
            model = new DropdownModel();
            if (i == 0) {
                model.setDepartmentPK("-1");
                model.setDepartment("Please Select");
            } else {
                model.setDepartmentPK(modelDepartment.getTable().get(i - 1).getDepartmentPK());
                model.setDepartment(modelDepartment.getTable().get(i - 1).getDepartment());
            }
            mDepartmentList.add(model);
        }

        ArrayList<String> mName = new ArrayList<>();
        for (int i = 0; i < mDepartmentList.size(); i++) {
            mName.add(mDepartmentList.get(i).getDepartment());
        }

        // String [] list = mCompanyList.toArray(new String[mCompanyList.size()]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.selected_item, mName);
        edt_department.setAdapter(adapter);

    }

    private void setSubDepartment(SubDepartmentModel modelSubDepartment) {

        DropdownModel model;
        mSubDepartmentList.clear();
        // mSubDepartmentList = new ArrayList<>();
        for (int i = 0; i < modelSubDepartment.getTable().size() + 1; i++) {
            model = new DropdownModel();
            if (i == 0) {
                model.setSubDepartmentPK("-1");
                model.setSubDepartment("Please Select");
            } else {
                model.setSubDepartmentPK(modelSubDepartment.getTable().get(i - 1).getSubDepartmentPK());
                model.setSubDepartment(modelSubDepartment.getTable().get(i - 1).getSubDepartment());
            }
            mSubDepartmentList.add(model);
        }

        ArrayList<String> mName = new ArrayList<>();
        for (int i = 0; i < mSubDepartmentList.size(); i++) {
            mName.add(mSubDepartmentList.get(i).getSubDepartment());
        }

        // String [] list = mCompanyList.toArray(new String[mCompanyList.size()]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.selected_item, mName);
        edt_sub_department.setAdapter(adapter);

    }

    private void setDesignation(CommonDetailModel modelCommon) {
        DropdownModel model;
        mDesignationList.clear();
        //mDesignationList = new ArrayList<>();
        for (int i = 0; i < modelCommon.getTable9().size() + 1; i++) {
            model = new DropdownModel();
            if (i == 0) {
                model.setDesignationPK("-1");
                model.setDesignation("Please Select");
            } else {
                model.setDesignationPK(modelCommon.getTable9().get(i - 1).getDesignationPK());
                model.setDesignation(modelCommon.getTable9().get(i - 1).getDesignation());
            }
            mDesignationList.add(model);
        }

        ArrayList<String> mName = new ArrayList<>();
        for (int i = 0; i < mDesignationList.size(); i++) {
            mName.add(mDesignationList.get(i).getDesignation());
        }

        // String [] list = mCompanyList.toArray(new String[mCompanyList.size()]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.selected_item, mName);
        edt_designation.setAdapter(adapter);
    }
}
