package com.autokrew.fragments;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.autokrew.R;
import com.autokrew.adapter.AttendanceAdapter;
import com.autokrew.dialogs.AttendanceDialog;
import com.autokrew.interfaces.AttendanceDialogInterface;
import com.autokrew.interfaces.RecyclerViewClickListener;
import com.autokrew.models.AddDeviationModel;
import com.autokrew.models.AddDeviationParams;
import com.autokrew.models.AttendanceModel;
import com.autokrew.models.AttendanceModelParams;
import com.autokrew.models.CommonDetailModel;
import com.autokrew.models.CommonDetailModelParams;
import com.autokrew.models.DropdownModel;
import com.autokrew.network.ApiListener;
import com.autokrew.network.WebServices;
import com.autokrew.utils.CommonUtils;
import com.autokrew.utils.Constant;
import com.autokrew.utils.Pref;
import com.google.gson.Gson;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;


public class MyAttendanceFragment extends Fragment implements ApiListener,RecyclerViewClickListener ,View.OnClickListener ,AttendanceDialogInterface {


    private static final String TAG = "MyAttendanceFragment";
    View view;
    TextView  txt_search,txt_child_item,txt_workingdays, txt_presentdays ,txt_absent ,txt_weekoff ,txt_halfdays,txt_leave,txt_holiday,txt_punchlate,txt_punchearly;
    ImageView iv_image;
    String str_name, str_disname, str_des, str_imagename;

    private RecyclerView rv_data_attendance;
    CardView card_view;
    AttendanceAdapter mAdapter;

    ImageView iv_month ,iv_year;
    Spinner edt_month,edt_year;
    AttendanceDialog mDialog;
    AttendanceModel model;

    AttendanceModel.Table2 mTable2;


    NestedScrollView root_view;
    CommonDetailModel modelCommon;
    AddDeviationModel modelDeviationAdd;
    String mToken;

    LinearLayout ll_root;
    int position_dialog = 0;
    int mAddendancePK = 0;
    Dialog dialog;

    ArrayList<DropdownModel> mYearList= new ArrayList<>();
    ArrayList<DropdownModel> mMonthList= new ArrayList<>();
    MaterialCalendarView widget;
    ArrayList<Date> mListP = new ArrayList<>();
    ArrayList<Date> mListA = new ArrayList<>();
    ArrayList<Date> mListWO = new ArrayList<>();
    ArrayList<Date> mListPL = new ArrayList<>();
    ArrayList<Date> mListPE = new ArrayList<>();
    ArrayList<Date> mListP2 = new ArrayList<>();
    ArrayList<Date> mListL= new ArrayList<>();
    ArrayList<Date> mListH = new ArrayList<>();

    SimpleDateFormat formatter;
    Calendar cal;

    private CaldroidFragment caldroidFragment;
    FragmentTransaction t;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_myattendance, container, false);

        getData();
        findView(view);
        setupRecyclerView(); //api calls
        setListner();

        return view;
    }

    private void getData() {

        mToken = Pref.getValue(getActivity(),Constant.PREF_TOKEN,"");
        str_name = getArguments().getString("name");
        str_disname = getArguments().getString("dish");
        //str_des = getArguments().getString("des");
        //str_imagename = getArguments().getString("image");
    }

    private void setData() {

        txt_child_item.setText(str_disname);

        if(model!=null){
            txt_workingdays.setText(""+model.getTable1().get(0).getWorkingDays());
            txt_presentdays.setText(""+model.getTable1().get(0).getPresentDays());
            txt_absent.setText(""+model.getTable1().get(0).getAbsentDays());
            txt_weekoff.setText(""+model.getTable1().get(0).getWeekOff());
            txt_halfdays.setText(""+model.getTable1().get(0).getHalfDays());
            txt_leave.setText(""+model.getTable1().get(0).getLeave());
          //  txt_holiday.setText(""+model.getTable1().get(0).getHoliday());
            txt_punchlate.setText(""+model.getTable1().get(0).getPunchLate());
            txt_punchearly.setText(""+model.getTable1().get(0).getPunchEarly());
        }

    }

    private void setListner() {

        iv_year.setOnClickListener(this);
        iv_month.setOnClickListener(this);
        txt_search.setOnClickListener(this);
    }

    private void findView(View v) {
        rv_data_attendance = (RecyclerView) v.findViewById(R.id.rv_data_attendance);
        card_view = (CardView)v.findViewById(R.id.card_view);

        formatter = new SimpleDateFormat("dd MMM yyyy");

        root_view = (NestedScrollView) v.findViewById(R.id.root_view);

        widget = (MaterialCalendarView)v.findViewById(R.id.calendarView);

        ll_root = (LinearLayout)v.findViewById(R.id.ll_root);
        ll_root.setVisibility(View.INVISIBLE);

        txt_workingdays = (TextView) v.findViewById(R.id.txt_workingdays);
        txt_presentdays = (TextView) v.findViewById(R.id.txt_presentdays);
        txt_absent = (TextView) v.findViewById(R.id.txt_absent);
        txt_weekoff = (TextView) v.findViewById(R.id.txt_weekoff);
        txt_halfdays = (TextView) v.findViewById(R.id.txt_halfdays);
        txt_leave = (TextView) v.findViewById(R.id.txt_leave);
       // txt_holiday = (TextView) v.findViewById(R.id.txt_holiday);
        txt_punchlate = (TextView) v.findViewById(R.id.txt_punchlate);
        txt_punchearly = (TextView) v.findViewById(R.id.txt_punchearly);
        txt_child_item = (TextView)v.findViewById(R.id.txt_child_item);

        iv_month = (ImageView)v.findViewById(R.id.iv_month);
        iv_year = (ImageView)v.findViewById(R.id.iv_year);
        txt_search = (TextView) v.findViewById(R.id.txt_search);

        edt_month = (Spinner) v.findViewById(R.id.edt_month);
        edt_year = (Spinner)v.findViewById(R.id.edt_year);

    }

    private void setCalender1Data() {
        Map<Date, Drawable> successMap = new HashMap<>();
       // Map<Date, Drawable> failureMap = new HashMap<>();
        successMap.clear();
        //For success days
        for(int i=0; i<mListP.size(); i++){
            successMap.put(mListP.get(i), getResources().getDrawable(R.drawable.bg_circular1));
        }

        for(int i=0; i<mListA.size(); i++){
            successMap.put(mListA.get(i), getResources().getDrawable(R.drawable.bg_circular3));
        }
        for(int i=0; i<mListH.size(); i++){
            successMap.put(mListH.get(i), getResources().getDrawable(R.drawable.bg_circular5));
        }
        for(int i=0; i<mListWO.size(); i++){
            successMap.put(mListWO.get(i), getResources().getDrawable(R.drawable.bg_circular6));
        }
        for(int i=0; i<mListPL.size(); i++){
            successMap.put(mListPL.get(i), getResources().getDrawable(R.drawable.bg_circular7));
        }
        for(int i=0; i<mListP2.size(); i++){
            successMap.put(mListP2.get(i), getResources().getDrawable(R.drawable.bg_circular2));
        }
        for(int i=0; i<mListPE.size(); i++){
            successMap.put(mListPE.get(i), getResources().getDrawable(R.drawable.bg_circular7));
        }
        for(int i=0; i<mListL.size(); i++){
            successMap.put(mListL.get(i), getResources().getDrawable(R.drawable.bg_circular4));
        }

      //  successMap.putAll(failureMap);
        caldroidFragment.setBackgroundDrawableForDates(successMap);
        caldroidFragment.refreshView();

    }

    private void setTestData(AttendanceModel model) {

        for (int i = 0; i <model.getTable2().size() ; i++) {
            if(model.getTable2().get(i).getStatus().equalsIgnoreCase("P")){
                Date d = funx(model.getTable2().get(i).getDate());
                mListP.add(d);
            }
            else if(model.getTable2().get(i).getStatus().equalsIgnoreCase("A")){
                Date d = funx(model.getTable2().get(i).getDate());
                mListA.add(d);
            }else if(model.getTable2().get(i).getStatus().equalsIgnoreCase("H")){
                Date d = funx(model.getTable2().get(i).getDate());
                mListH.add(d);
            }else if(model.getTable2().get(i).getStatus().equalsIgnoreCase("WO")){
                Date d = funx(model.getTable2().get(i).getDate());
                mListWO.add(d);
            }else if(model.getTable2().get(i).getStatus().equalsIgnoreCase("PL")){
                Date d = funx(model.getTable2().get(i).getDate());
                mListPL.add(d);
            }else if(model.getTable2().get(i).getStatus().equalsIgnoreCase("P2")){
                Date d = funx(model.getTable2().get(i).getDate());
                mListP2.add(d);
            }else if(model.getTable2().get(i).getStatus().equalsIgnoreCase("PE")){
                Date d = funx(model.getTable2().get(i).getDate());
                mListPE.add(d);
            }else if(model.getTable2().get(i).getStatus().equalsIgnoreCase("L")){
                Date d = funx(model.getTable2().get(i).getDate());
                mListL.add(d);
            }
        }

    }

    private void setCalender1() {
        Bundle args = new Bundle();
       // desableDate();
        args.putInt(CaldroidFragment.MONTH,Integer.parseInt(mMonthList.get(edt_month.getSelectedItemPosition()).getMonthPk()));// cal.get(Calendar.MONTH) + 1 (Month is not in the range 1..12. Value is:0)
        args.putInt(CaldroidFragment.YEAR,mYearList.get(edt_year.getSelectedItemPosition()).getYear()); //cal.get(Calendar.YEAR)

        args.putBoolean(CaldroidFragment.ENABLE_SWIPE, false);
       // args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, true);
        args.putBoolean(CaldroidFragment.SHOW_NAVIGATION_ARROWS, false);




        caldroidFragment.setArguments(args);
        CommonUtils.getInstance().displayToast(getActivity(),""+Integer.parseInt(mMonthList.get(edt_month.getSelectedItemPosition()).getMonthPk()));

    }

    private void setupRecyclerView(){

        rv_data_attendance.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_data_attendance.setHasFixedSize(true);

        if (CommonUtils.getInstance().isNetworkAvailable(getActivity())){
           // callAPI(getActivity());
          //  if (validate()) {

            dialog = new Dialog(getActivity(), R.style.progressDialog);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.progress_dialog);
            dialog.setCancelable(false);
            //Show progress dialog
            dialog.show();




            CommonDetailModelParams params = new CommonDetailModelParams();
            params.setFlag("common");
            params.setTranTypes(-1);

            //call common detail api here..
            new WebServices(getActivity()/* ActivityContext */, this /* ApiListener */,
                    false /* show progress dialog */,true).
                    callGetCommonDetailAPI(mToken,params); //from_last = ""
        }
        else{
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
            //LoginModel model = (LoginModel) mObject;
           // Log.e("", "onApiSuccess: 123 >>  "+mObject.toString() );

            ll_root.setVisibility(View.VISIBLE);

            try {
                JSONObject jsonObj = new JSONObject(mObject.toString());
                if(jsonObj.toString().contains("nationalPK")){
                    //parse common detail data
                    Log.e("", "onApiSuccess: common json >>  "+jsonObj.toString());
                    Gson gson = new Gson();
                    modelCommon = gson.fromJson(mObject.toString(), CommonDetailModel.class);
                    //api calls for original data...

                    setMonth(modelCommon);
                    setYear(modelCommon);

                    AttendanceModelParams params = new AttendanceModelParams();
                   // int monthFK = CommonUtils.setMonthFK(edt_month.getSelectedItem().toString());
                    params.setMonthFK(Integer.parseInt(mMonthList.get(edt_month.getSelectedItemPosition()).getMonthPk()));
                    params.setSessionEmployeeFK(Pref.getValue(getActivity(),Constant.PREF_SESSION_EMPLOYEE_FK,0));
                    params.setYearFk(mYearList.get(edt_year.getSelectedItemPosition()).getYear());

                    new WebServices(getActivity()/* ActivityContext */, this /* ApiListener */,
                            false /* show progress dialog */,true).
                            callGetAttendanceAPI(mToken,params,"MyAttendance"); //from_last = ""

                }

                else if(jsonObj.toString().contains("AttDate")){

                    Log.e("", "onApiSuccess: add deviation json >>  "+jsonObj.toString());
                    Gson gson = new Gson();
                    modelDeviationAdd = gson.fromJson(mObject.toString(), AddDeviationModel.class);
                    //api calls for original data...
                    String _year = String.valueOf(mYearList.get(edt_year.getSelectedItemPosition()).getYear());
                    int _month = Integer.parseInt(mMonthList.get(edt_month.getSelectedItemPosition()).getMonthPk());

                    mDialog = new AttendanceDialog(getActivity(), position_dialog ,
                            modelDeviationAdd ,modelCommon ,mAddendancePK ,MyAttendanceFragment.this
                    ,_year,_month);
                    mDialog.setCancelable(false);
                    mDialog.setCanceledOnTouchOutside(true);
                    mDialog.show();
                }

                else{
                    //parse original data
                    Log.e("", "onApiSuccess: original json >>  "+jsonObj);
                    Gson gson = new Gson();
                    model = gson.fromJson(mObject.toString(), AttendanceModel.class);
                    if(model.getTable2().size()==0){
                        card_view.setVisibility(View.VISIBLE);
                        rv_data_attendance.setVisibility(View.GONE);

                        setDefaultValue();
                    }
                    else{
                        card_view.setVisibility(View.GONE);
                        rv_data_attendance.setVisibility(View.VISIBLE);


                      /*  mAdapter = new AttendanceAdapter(getActivity(), model,MyAttendanceFragment.this);
                        rv_data_attendance.setAdapter(mAdapter);
                        rv_data_attendance.setNestedScrollingEnabled(false);//for smooth nested scroll*/
                       // setCalender(model);



                        // Setup caldroid fragment
                        // **** If you want normal CaldroidFragment, use below line ****
                        //init every time...
                        caldroidFragment = new CaldroidFragment();
                        caldroidFragment.setCaldroidListener(listener);

                        setCalender1();

                        // Attach to the activity
                        t = getActivity().getSupportFragmentManager().beginTransaction();
                        t.add(R.id.calendar1, caldroidFragment);
                        t.commit();

                        setTestData(model);

                        setCalender1Data();
                        //uncomment below
                        setData();

                    }
                    //dismiss dialog
                    dialog.dismiss();

                }

            } catch (JSONException e) {
                e.printStackTrace();
                if(dialog!=null){
                    if(dialog.isShowing())
                        dialog.dismiss();
                }
            }

        }

    }

    private void desableDate() {
        // Set disabled dates
        //Calendar cal = Calendar.getInstance();
        ArrayList<Date> disabledDates = new ArrayList<Date>();
        for (int i = 5; i < 8; i++) {
            cal = Calendar.getInstance();
            cal.add(Calendar.DATE, i);
            disabledDates.add(cal.getTime());
        }
        caldroidFragment.setDisableDates(disabledDates);

    }

    private void clearList() {
        mListP.clear();
        mListA.clear();
        mListWO.clear();
        mListPL.clear();
        mListPE.clear();
        mListP2.clear();
        mListL.clear();
        mListH.clear();
    }

    public static Date funx(String S) {

        String[] s2 = S.split(", ");

        String DateStr = s2[1].trim(); //S =>s2[1]
        DateStr = DateStr.replace(" ","-");
        Date d = null;
        try {
            d = new SimpleDateFormat("dd-MMM-yyyy").parse(DateStr);
            //YYYY is the week-year, not the calendar year. You want yyyy instead.
        } catch (ParseException e) {
            e.printStackTrace();
            Log.e(TAG, "setCalender: 2 >> "+ e.toString());
        }
        //java.sql.Date d1 = new java.sql.Date(d.getTime());

        return d;
    }


    private void setDefaultValue() {
        txt_workingdays.setText("0");
        txt_presentdays.setText("0");
        txt_absent.setText("0");
        txt_weekoff.setText("0");
        txt_halfdays.setText("0");
        txt_leave.setText("0");
        //  txt_holiday.setText(""+model.getTable1().get(0).getHoliday());
        txt_punchlate.setText("0");
        txt_punchearly.setText("0");
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

        position_dialog = position;
       // CommonUtils.getInstance().displayToast(getActivity(),"pos >>" +position);

        //api calls for add deviation
        if (CommonUtils.getInstance().isNetworkAvailable(getActivity())){

//   {"AttendancePK":306011,"DeviationFK":-1,"EmpRemarks":"","Month":10,"Year":"2017","Flag":"Detail"}
            AddDeviationParams params = new AddDeviationParams();
            params.setFlag("Detail");

            //check for davitaiton -----------
         /*   if(){

            }else{*/

        /* if(model.getTable2().get(position).getDeviationFK()>0){
             params.setDeviationFK(model.getTable2().get(position).getDeviationFK());
         }
         else{*/
             params.setDeviationFK(-1); //common for all
       //  }

           // }

             //no need to check for  attendenc ----------------
            //@kns : old model.getTable2().get(position) = > mTable2.get
           // mAddendancePK = model.getTable2().get(position).getAttendancePK();

            mAddendancePK = mTable2.getAttendancePK();
            params.setAttendancePK(mTable2.getAttendancePK());


            //check for remarks -------------
            if(model.getTable2().get(position).getEmployeeRemarks().equalsIgnoreCase("")){
                params.setEmpRemarks(""); //please select in inner popup
            }
            else{
                params.setEmpRemarks(mTable2.getEmployeeRemarks());
            }



          //  int monthFK = CommonUtils.setMonthFK(edt_month.getSelectedItem().toString());
            params.setMonth(Integer.parseInt(mMonthList.get(edt_month.getSelectedItemPosition()).getMonthPk())); //int value of month
            params.setYear(String.valueOf(mYearList.get(edt_year.getSelectedItemPosition()).getYear())); //string value of year


            //call common detail api here..
            new WebServices(getActivity()/* ActivityContext */, this /* ApiListener */,
                    false /* show progress dialog */,true).
                    callAddDeviationAPI(mToken,params); //from_last = ""
        }
        else{
            CommonUtils.getInstance().displayToast(getActivity(), Constant.INTERNET_FAILURE);

        }

    }


    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.iv_month:
               // pickMonthAndYear();
               // setMonth();

            break;

            case R.id.iv_year:
               // pickMonthAndYear();
               // setYear();

            break;

            case R.id.txt_search:

               // CommonUtils.getInstance().displayToast(getActivity(),"search ..");

                //clear list before search
                clearList();

                //remove calender fragment
                getActivity().getSupportFragmentManager().beginTransaction().remove(caldroidFragment).commit();


                AttendanceModelParams params = new AttendanceModelParams();
                //int monthFK = CommonUtils.setMonthFK(edt_month.getSelectedItem().toString());
                params.setMonthFK(Integer.parseInt(mMonthList.get(edt_month.getSelectedItemPosition()).getMonthPk()));
                params.setSessionEmployeeFK(Pref.getValue(getActivity(),Constant.PREF_SESSION_EMPLOYEE_FK,0));
                params.setYearFk(mYearList.get(edt_year.getSelectedItemPosition()).getYear()); //2017

                new WebServices(getActivity()/* ActivityContext */, this /* ApiListener */,
                        true /* show progress dialog */,true).
                        callGetAttendanceAPI(mToken,params,"MyAttendance"); //from_last = ""
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


    @Override
    public void recallAllAPI() {

        AttendanceModelParams params = new AttendanceModelParams();
        //int monthFK = CommonUtils.setMonthFK(edt_month.getSelectedItem().toString());
        params.setMonthFK(Integer.parseInt(mMonthList.get(edt_month.getSelectedItemPosition()).getMonthPk()));
        params.setSessionEmployeeFK(Pref.getValue(getActivity(),Constant.PREF_SESSION_EMPLOYEE_FK,0));
        params.setYearFk(mYearList.get(edt_year.getSelectedItemPosition()).getYear()); //2017

        new WebServices(getActivity()/* ActivityContext */, this /* ApiListener */,
                true /* show progress dialog */,true).
                callGetAttendanceAPI(mToken,params,"MyAttendance"); //from_last = ""

    }


    // Setup listener
    final CaldroidListener listener = new CaldroidListener() {

        @Override
        public void onSelectDate(Date date, View view) {


            for (int i = 0; i <model.getTable2().size() ; i++) {
                String[] parts = model.getTable2().get(i).getDate().split(", ");
                if(parts[1].equalsIgnoreCase(formatter.format(date))){
                    Toast.makeText(getActivity(), formatter.format(date),
                            Toast.LENGTH_SHORT).show();

                     mTable2 = model.getTable2().get(i);
                    //set adapter with one obj
                     mAdapter = new AttendanceAdapter(getActivity(), mTable2,MyAttendanceFragment.this);
                     rv_data_attendance.setAdapter(mAdapter);
                     rv_data_attendance.setNestedScrollingEnabled(false);//for smooth nested scroll


                    root_view.post(new Runnable() {
                        @Override
                        public void run() {
                            root_view.fullScroll(ScrollView.FOCUS_DOWN);
                        }
                    });
                }
            }
          //  mListmodel
        }

        @Override
        public void onChangeMonth(int month, int year) {
          /*  String text = "month: " + month + " year: " + year;

            Toast.makeText(getActivity(), text,
                    Toast.LENGTH_SHORT).show();*/
        }

        @Override
        public void onLongClickDate(Date date, View view) {
            /*Toast.makeText(getActivity(),
                    "Long click " + formatter.format(date),
                    Toast.LENGTH_SHORT).show();*/
        }

        @Override
        public void onCaldroidViewCreated() {
           /* if (caldroidFragment.getLeftArrowButton() != null) {
                Toast.makeText(getActivity(),
                        "Caldroid view is created", Toast.LENGTH_SHORT)
                        .show();
            }*/
        }

    };
}
