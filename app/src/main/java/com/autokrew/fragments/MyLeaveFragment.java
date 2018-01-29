package com.autokrew.fragments;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.autokrew.R;
import com.autokrew.adapter.LeaveAdapter;
import com.autokrew.adapter.listviewAdapter;
import com.autokrew.dialogs.ApplyLeaveDialog;
import com.autokrew.dialogs.CancelDialog;
import com.autokrew.interfaces.AttendanceDialogInterface;
import com.autokrew.interfaces.RecyclerViewClickListener;
import com.autokrew.models.CommonDetailModel;
import com.autokrew.models.CommonDetailModelParams;
import com.autokrew.models.DropdownModel;
import com.autokrew.models.LeaveCardModel;
import com.autokrew.models.LeaveCardParams;
import com.autokrew.models.Model;
import com.autokrew.models.ReportingPersonModel;
import com.autokrew.network.ApiListener;
import com.autokrew.network.WebServices;
import com.autokrew.utils.Constant;
import com.autokrew.utils.MyListView;
import com.autokrew.utils.Pref;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MyLeaveFragment extends Fragment implements ApiListener,RecyclerViewClickListener ,
        View.OnClickListener ,AttendanceDialogInterface {

    View view;
    TextView  txt_child_item,
            txt_balance,txt_leave_type,txt_eligible,txt_available;
    ImageView iv_image;
    String str_name, str_disname, str_des, str_imagename;

    private RecyclerView rv_data_leave;
    LeaveAdapter mAdapter;

    ImageView iv_month ,iv_year;
    TextView txt_search;

    Spinner edt_month,edt_year,edt_leave_status;
    CancelDialog mDialog;
    ApplyLeaveDialog mDialogLeave;

    Button btn_apply_leave;

    private ArrayList<Model> productList = new ArrayList<>();
    listviewAdapter adapter;
    MyListView lview;

    String mToken;
    LeaveCardModel modelLeaveCard;
    CommonDetailModel modelCommon;
    ReportingPersonModel modelReportingPerson;
    ArrayList<DropdownModel> mYearList= new ArrayList<>();
    ArrayList<DropdownModel> mMonthList= new ArrayList<>();
    ArrayList<DropdownModel> mLeaveStatusList= new ArrayList<>();

    String mLeaveTypeFK = "" ;
    CardView card_view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_myleave, container, false);

        getData();
        findView(view);
        //for recycler view
        setupRecyclerView(modelLeaveCard);

        setData();
        setListner();

        return view;
    }

    private void getData() {

        mToken = Pref.getValue(getActivity(),Constant.PREF_TOKEN,"");
        str_name = getArguments().getString("name");
        str_disname = getArguments().getString("dish");
        //str_des = getArguments().getString("des");
        //str_imagename = getArguments().getString("image");

        //api calls for get reporting person name
        //leave card api
        LeaveCardParams params = new LeaveCardParams();
        params.setEmployeeFK(String.valueOf(Pref.getValue(getActivity(),Constant.PREF_SESSION_EMPLOYEE_FK,0)));
        new WebServices(getActivity()/* ActivityContext */, this /* ApiListener */,
                false /* show progress dialog */,true).
                callGetReportingPersonAPI(mToken,params); //from_last = ""



    }

    private void setData() {
        txt_child_item.setText(str_disname);

        //adapter.notifyDataSetChanged();

        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String mLeaveType = ((TextView)view.findViewById(R.id.mLeaveType)).getText().toString(); //1



                String mBalance = ((TextView)view.findViewById(R.id.mBalance)).getText().toString(); //1
                int isCompoff = modelLeaveCard.getTable().get(position).getIscompoff();


                String mApplyLeave =  modelLeaveCard.getTable().get(position).get_$ApplyLeave296();
               // Log.e("", "mApplyLeave >>  "+mApplyLeave);
                Pattern pattern = Pattern.compile("data-leavetypepk=(.*?)data-balance");
                Matcher matcher = pattern.matcher(mApplyLeave);
                while (matcher.find()) {
                    Log.e("",">>>>> "+matcher.group(1));
                    mLeaveTypeFK = matcher.group(1);
                }

               /* String mEligible = ((TextView)view.findViewById(R.id.mEligible)).getText().toString();
                String mAvailed = ((TextView)view.findViewById(R.id.mAvailed)).getText().toString();
                String price = ((TextView)view.findViewById(R.id.mBalance)).getText().toString();*/

                String emp_name = modelReportingPerson.getTable().get(0).getName();//2
                String reporting_person = modelReportingPerson.getTable().get(0).getReportingPerson();//3

                /*Toast.makeText(getActivity(), "mLeaveType: " + mLeaveType +"\n"
                       , Toast.LENGTH_SHORT).show();*/


                mDialogLeave = new ApplyLeaveDialog(getActivity(),MyLeaveFragment.this,
                        "Apply Leave",mLeaveType,emp_name,reporting_person ,mLeaveTypeFK.trim(),mBalance
                        ,isCompoff
                ,mToken);
                mDialogLeave.setCancelable(false);
                mDialogLeave.setCanceledOnTouchOutside(true);
                mDialogLeave.show();
            }
        });



    }

    private void setListner() {

        iv_year.setOnClickListener(this);
        iv_month.setOnClickListener(this);
        txt_search.setOnClickListener(this);
        btn_apply_leave.setOnClickListener(this);
    }

    private void findView(View v) {
        rv_data_leave = (RecyclerView) v.findViewById(R.id.rv_data_leave);
        btn_apply_leave = (Button)v.findViewById(R.id.btn_apply_leave);

        card_view = (CardView) v.findViewById(R.id.card_view);


        // txt_balance = (TextView) v.findViewById(R.id.txt_balance);
       // txt_available = (TextView) v.findViewById(R.id.txt_available);

        txt_child_item = (TextView)v.findViewById(R.id.txt_child_item);

        iv_month = (ImageView)v.findViewById(R.id.iv_month);
        iv_year = (ImageView)v.findViewById(R.id.iv_year);
        txt_search = (TextView) v.findViewById(R.id.txt_search);

        edt_month = (Spinner) v.findViewById(R.id.edt_month);
        edt_year = (Spinner) v.findViewById(R.id.edt_year);
        edt_leave_status = (Spinner)v.findViewById(R.id.edt_leave_status);

        //productList = new ArrayList<LeaveCardModel>();
        lview = (MyListView) v.findViewById(R.id.listview);


    }

    private void setupRecyclerView(LeaveCardModel modelLeaveCard){

        rv_data_leave.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_data_leave.setHasFixedSize(true);



        CommonDetailModelParams params = new CommonDetailModelParams();
        params.setFlag("common");
        params.setTranTypes(-1);
        //call common detail api here..
        new WebServices(getActivity()/* ActivityContext */, this /* ApiListener */,
                false /* show progress dialog */,true).
                callGetCommonDetailAPI(mToken,params); //from_last = ""

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
            //LoginModel model = (LoginModel) mObject;
           // Log.e("", "onApiSuccess: 123 >>  "+mObject.toString() );
            try {
                if(mObject.toString().contains("nationalPK")){
                    //parse common detail data
                    Log.e("", "onApiSuccess: common json >>  "+mObject.toString());
                    Gson gson = new Gson();
                    modelCommon = gson.fromJson(mObject.toString(), CommonDetailModel.class);
                    //api calls for original data...

                    setMonth(modelCommon);
                    setYear(modelCommon);
                    setLeaveStatus(modelCommon);

                    //leave card api
                    LeaveCardParams params = new LeaveCardParams();
                    params.setFlag("Grid");
                    params.setEmployeeFK(String.valueOf(Pref.getValue(getActivity(),Constant.PREF_SESSION_EMPLOYEE_FK,0)));
                    params.setMonth(mMonthList.get(edt_month.getSelectedItemPosition()).getMonthPk());
                    params.setYear(""+mYearList.get(edt_year.getSelectedItemPosition()).getYear());
                    params.setLeaveStatusFK("-1");
                    new WebServices(getActivity()/* ActivityContext */, this /* ApiListener */,
                            true /* show progress dialog */,true).
                            callLeaveCardAPI(mToken,params); //from_last = ""


                }

                else if(mObject.toString().contains("ReportingPerson")){
                    //parse common detail data
                    Log.e("", "onApiSuccess: ReportingPerson json >>  "+mObject.toString());
                    Gson gson = new Gson();
                    modelReportingPerson = gson.fromJson(mObject.toString(), ReportingPersonModel.class);
                }
                else{
                    JSONObject jsonObj = new JSONObject(mObject.toString());
                    Log.e("", "onApiSuccess: leave card >>  " + jsonObj.toString());
                    Gson gson = new Gson();
                    modelLeaveCard = gson.fromJson(mObject.toString(), LeaveCardModel.class);
                    setLeaveCard(modelLeaveCard);

                    //for leave card setup
                    adapter = new listviewAdapter(getActivity(), productList);
                    lview.setAdapter(adapter);

                    if(modelLeaveCard.getTable1().size()==0){
                        //no record found
                        card_view.setVisibility(View.VISIBLE);
                        rv_data_leave.setVisibility(View.GONE);

                    }else{
                        card_view.setVisibility(View.GONE);
                        rv_data_leave.setVisibility(View.VISIBLE);

                        //setup adapter and recycler view
                        mAdapter = new LeaveAdapter(getActivity(), modelLeaveCard,MyLeaveFragment.this);
                        rv_data_leave.setAdapter(mAdapter);
                        rv_data_leave.setNestedScrollingEnabled(false); //for smooth nested scroll
                    }


                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    private void setLeaveCard(LeaveCardModel modelLeaveCard) {
        productList.clear();
        Model model;
        for (int i = 0; i <modelLeaveCard.getTable().size(); i++) {

            model = new Model(
                    modelLeaveCard.getTable().get(i).get_$LeaveType123(),
                    String.valueOf(modelLeaveCard.getTable().get(i).getEligible()),
                    String.valueOf(modelLeaveCard.getTable().get(i).getAvailed()),
                    String.valueOf(modelLeaveCard.getTable().get(i).getBalance()),
                    modelLeaveCard.getTable().get(i).get_$ApplyLeave296()
            );

            productList.add(model);
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
        int leavePK = modelLeaveCard.getTable1().get(position).getLeaveDetailPK();

        mDialog = new CancelDialog(getActivity(),MyLeaveFragment.this, position ,leavePK);
        mDialog.setCancelable(false);
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.show();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.iv_month:
                //pickMonthAndYear();
            break;

            case R.id.iv_year:
              //  pickMonthAndYear();
            break;

            case R.id.txt_search:

                LeaveCardParams params = new LeaveCardParams();
                params.setFlag("Grid");
                params.setEmployeeFK(String.valueOf(Pref.getValue(getActivity(),Constant.PREF_SESSION_EMPLOYEE_FK,0)));
                params.setMonth(mMonthList.get(edt_month.getSelectedItemPosition()).getMonthPk());
                params.setYear(""+mYearList.get(edt_year.getSelectedItemPosition()).getYear());
                params.setLeaveStatusFK(mLeaveStatusList.get(edt_leave_status.getSelectedItemPosition()).getLeaveStatusPK());
                new WebServices(getActivity(), this ,
                        true ,true).
                        callLeaveCardAPI(mToken,params);

            break;

            case R.id.btn_apply_leave:
              /*  mDialogLeave = new ApplyLeaveDialog(getActivity(),"Apply Leave");
                mDialogLeave.setCancelable(false);
                mDialogLeave.setCanceledOnTouchOutside(true);
                mDialogLeave.show();*/

            break;

        }
    }


    private void setYear(CommonDetailModel modelCommon) {
        DropdownModel model ;
        mYearList.clear();

        for (int i = 0; i <modelCommon.getTable17().size() ; i++) {
            model = new DropdownModel();
            model.setYearPk(modelCommon.getTable17().get(i).getYearPk());
            model.setYear(modelCommon.getTable17().get(i).getYear());
            mYearList.add(model);
        }

        ArrayList<String> mName = new ArrayList<>();
        for (int i = 0; i <mYearList.size(); i++) {
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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.selected_item, mName);
        edt_year.setAdapter(adapter);
        edt_year.setSelection(c_year_index);

    }

    private void setMonth(CommonDetailModel modelCommon ) {

        DropdownModel model;
        mMonthList.clear();

        for (int i = 0; i <modelCommon.getTable16().size() ; i++) {
            model = new DropdownModel();
            model.setMonthPk(modelCommon.getTable16().get(i).getMonthPk());
            model.setMonth(modelCommon.getTable16().get(i).getMonth());
            mMonthList.add(model);
        }

        ArrayList<String> mName = new ArrayList<>();
        for (int i = 0; i <mMonthList.size(); i++) {
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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.selected_item, mName);
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

    private void setLeaveStatus(CommonDetailModel modelCommon) {
        DropdownModel model ;
        mLeaveStatusList.clear();

        for (int i = 0; i <modelCommon.getTable28().size()+1 ; i++) {
            model = new DropdownModel();
            if(i==0){
                model.setLeaveStatusPK("-1");
                model.setLeaveStatus("Please Select");
            }
            else{
                model.setLeaveStatusPK(modelCommon.getTable28().get(i-1).getLeaveStatusPK());
                model.setLeaveStatus(modelCommon.getTable28().get(i-1).getLeaveStatus());
            }
            mLeaveStatusList.add(model);
        }

        ArrayList<String> mName = new ArrayList<>();
        for (int i = 0; i <mLeaveStatusList.size(); i++) {
            mName.add(String.valueOf(mLeaveStatusList.get(i).getLeaveStatus()));
        }

        // String [] list = mCompanyList.toArray(new String[mCompanyList.size()]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.selected_item, mName);
        edt_leave_status.setAdapter(adapter);
    }

    @Override
    public void recallAllAPI() {

        LeaveCardParams params = new LeaveCardParams();
        params.setFlag("Grid");
        params.setEmployeeFK(String.valueOf(Pref.getValue(getActivity(),Constant.PREF_SESSION_EMPLOYEE_FK,0)));
        params.setMonth(mMonthList.get(edt_month.getSelectedItemPosition()).getMonthPk());
        params.setYear(""+mYearList.get(edt_year.getSelectedItemPosition()).getYear());
        params.setLeaveStatusFK(mLeaveStatusList.get(edt_leave_status.getSelectedItemPosition()).getLeaveStatusPK());
        new WebServices(getActivity(), this ,
                true ,true).
                callLeaveCardAPI(mToken,params);
    }
}
