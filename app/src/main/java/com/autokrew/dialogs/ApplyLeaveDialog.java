package com.autokrew.dialogs;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.autokrew.R;
import com.autokrew.adapter.CompOffAdapter;
import com.autokrew.interfaces.AttendanceDialogInterface;
import com.autokrew.models.ApplyLeaveModel;
import com.autokrew.models.ApplyLeaveParams;
import com.autokrew.models.BindWeekOffParams;
import com.autokrew.models.CompoffLeaveModel;
import com.autokrew.models.CompoffLeaveParams;
import com.autokrew.models.IsDocumentRequire;
import com.autokrew.models.IsDocumentRequireParams;
import com.autokrew.models.IsLeaveAppliedParams;
import com.autokrew.models.IsLeaveaAppliedModel;
import com.autokrew.models.SandwichFormDateModel;
import com.autokrew.models.SandwichParams;
import com.autokrew.models.SandwichToDateModel;
import com.autokrew.models.WeekOffModel;
import com.autokrew.network.ApiListener;
import com.autokrew.network.WebServices;
import com.autokrew.utils.CommonUtils;
import com.autokrew.utils.Constant;
import com.autokrew.utils.Pref;
import com.google.gson.Gson;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class ApplyLeaveDialog extends AppCompatDialog implements View.OnClickListener,
        DatePickerDialog.OnDateSetListener ,
        ApiListener {

    private static final String TAG = ApplyLeaveDialog.class.getSimpleName();
    private Context mContext;
    private Activity mActivity;
    Button btn_save,btn_upload_doc;
    ImageView iv_dialog_cancel, iv_to_date, iv_from_date;

    int isCompoff;

    String flag = "" ,mTitle = "",mLeaveType,emp_name,reporting_person,mLeaveTypeFK,mBalance,mToken;

    TextView edt_from_date, edt_to_date;
    EditText edt_reason,edt_contact;
    TextView txt_title;
    TextView txt_name,txt_reporting_person,txt_leave_type ,txt_duration;

    RadioButton rb_fullday ,rb_halfday;
    RadioGroup rb_group;
    AttendanceDialogInterface mInterface;
    WeekOffModel model_weekoff;

    IsDocumentRequire model_require_doc;
    IsLeaveaAppliedModel model_Leave_applay;
    CompoffLeaveModel model_compoff;


    LinearLayout ll_duration ,ll_compoff_leave;
    String is_api_for;
    RecyclerView rv_comp_off;
    CompOffAdapter mCompOffAdapter;

    Integer _day_temp;
    ArrayList<String> mListCompoffCheck = new ArrayList<>();
    ArrayList<CompoffLeaveModel.TableBean> mListCompoffCheckModel = new ArrayList<>();
    CompoffLeaveModel.TableBean model_compoff_check;

    public ApplyLeaveDialog(Context context, int position ,String mTitle) {
        super(context);
        this.mContext = context;
        mActivity = (Activity) mContext;
        this.mTitle = mTitle;
    }


    public ApplyLeaveDialog(Context context ,AttendanceDialogInterface mInterface, String mTitle ,String mLeaveType
            ,String emp_name ,String reporting_person ,String mLeaveTypeFK,String mBalance ,int isCompoff,String mToken) {
        super(context);
        this.mContext = context;
        mActivity = (Activity) mContext;
        this.mTitle = mTitle;
        this.reporting_person = reporting_person;
        this.mLeaveType = mLeaveType;
        this.emp_name = emp_name;
        this.mLeaveTypeFK = mLeaveTypeFK;
        this.mInterface = mInterface;
        this.mBalance = mBalance;
        this.mToken = mToken;
        this.isCompoff= isCompoff;

    }

    @Override
    protected void onStart() {
        super.onStart();
        getWindow().setWindowAnimations(R.style.animation_slide_from_right);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_leave_apply);

        getData();
        findView();
        setData();
        setListner();
    }

    private void getData() {

        BindWeekOffParams params = new BindWeekOffParams();
        params.setSessionUserFk(Pref.getValue(mContext,Constant.PREF_SESSION_EMPLOYEE_FK,0));
        //call common detail api here..
        new WebServices(mContext/* ActivityContext */, this /* ApiListener */,
                false /* show progress dialog */,true).
                callGetBindWeekOff(mToken,params); //from_last = ""

    }


    private void setData() {

        txt_title.setText(mTitle);
        txt_name.setText(emp_name);
        txt_reporting_person.setText(reporting_person);
        txt_leave_type.setText(mLeaveType);

        //check compoff
        checkIsCompoff();
    }

    private void checkIsCompoff() {
        if (isCompoff == 1) {
            ll_compoff_leave.setVisibility(View.VISIBLE);
             BindCompOffGrid(); //14 number api
        } else {
            ll_compoff_leave.setVisibility(View.GONE);
        }

        if(mBalance.equalsIgnoreCase("0.5")){

            rb_halfday.setChecked(true);
            rb_fullday.setEnabled(false);
            rb_fullday.setChecked(false);

            edt_to_date.setFocusable(false);
            edt_to_date.setText(""); //to date hide
            //upload doc hide
            btn_upload_doc.setVisibility(View.GONE);
            //duration / total days hide
            ll_duration.setVisibility(View.GONE);
        }
        else{

            rb_halfday.setChecked(false);
            rb_fullday.setChecked(true);
            rb_fullday.setEnabled(true);
        }
    }

    private void BindCompOffGrid() {

        CompoffLeaveParams params = new CompoffLeaveParams();
        // String mToken = Pref.getValue(mContext, Constant.PREF_TOKEN,"");
        params.setSessionUserFk(String.valueOf(Pref.getValue(mContext,Constant.PREF_SESSION_EMPLOYEE_FK,0)));
        params.setFlag("Grid");

        is_api_for="CompoffLeave";

        new WebServices(mContext/* ActivityContext */, this /* ApiListener */,
                false /* show progress dialog */,true).
                callGetCompoffLeaveAPI(mToken,params); //from_last = ""
    }

    private void setListner() {
        btn_save.setOnClickListener(this);
        iv_dialog_cancel.setOnClickListener(this);
        //enable click event for apply leave dialog only
        if(mTitle.equalsIgnoreCase("Apply Leave")){
            edt_from_date.setOnClickListener(this);
            edt_to_date.setOnClickListener(this);


        }

    }

    private void findView() {
        btn_save = (Button) this.findViewById(R.id.btn_save);
        btn_upload_doc = (Button)this.findViewById(R.id.btn_upload_doc);
        rv_comp_off = (RecyclerView)this.findViewById(R.id.rv_comp_off);
        //birthday recycler view
        rv_comp_off.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rv_comp_off.setHasFixedSize(true);




        ll_duration = (LinearLayout)this.findViewById(R.id.ll_duration);
        ll_compoff_leave = (LinearLayout)this.findViewById(R.id.ll_compoff_leave);

        iv_dialog_cancel = (ImageView) this.findViewById(R.id.iv_dialog_cancel);
        iv_to_date = (ImageView) this.findViewById(R.id.iv_to_date);
        iv_from_date = (ImageView) this.findViewById(R.id.iv_from_date);
        edt_from_date = (TextView) this.findViewById(R.id.edt_from_date);
        edt_to_date = (TextView) this.findViewById(R.id.edt_to_date);

        edt_contact = (EditText)this.findViewById(R.id.edt_contact);
        edt_reason = (EditText)this.findViewById(R.id.edt_contact);

        rb_halfday = (RadioButton)this.findViewById(R.id.rb_halfday);
        rb_fullday = (RadioButton)this.findViewById(R.id.rb_fullday);
        rb_group = (RadioGroup)this.findViewById(R.id.rb_group);

        txt_title = (TextView) this.findViewById(R.id.txt_title);
        txt_name = (TextView)this.findViewById(R.id.txt_name);
        txt_reporting_person = (TextView)this.findViewById(R.id.txt_reporting_person);
        txt_leave_type = (TextView)this.findViewById(R.id.txt_leave_type);

        txt_duration = (TextView)this.findViewById(R.id.txt_duration);
/*
        Typeface copperplateGothicLight = Typeface.createFromAsset(getAppContext().getAssets(), "GillSans-SemiBold.ttf");
        btn_save.setTypeface(copperplateGothicLight);*/

        rb_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                edt_from_date.setText("From Date");
                edt_to_date.setText("To Date");
                txt_duration.setText("0");


            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_save:


                if(edt_from_date.getText().toString().length() == 0 |  edt_to_date.getText().toString().length() == 0
                        || edt_reason.getText().toString().length() == 0 || edt_contact.getText().toString().length() == 0 ){

                    CommonUtils.getInstance().displayToast(mContext,"Please fill up details!");
                }

                else{
                   // if(isValidateAll()
                    boolean is_validate = true;
                    if(isValidateAll()){

                        ApplyLeaveParams params = new ApplyLeaveParams();
                        params.setEmployeeFK(Pref.getValue(mContext,Constant.PREF_SESSION_EMPLOYEE_FK,0));
                        if(rb_halfday.isChecked()){
                            params.setLeaveDayTypeFK(1);
                        }else{
                            //for fullday
                            params.setLeaveDayTypeFK(2);
                        }

                        params.setLeaveTypeFK(Integer.parseInt(mLeaveTypeFK));
                        params.setLeaveFromDate(edt_from_date.getText().toString());
                        params.setLeaveToDate(edt_to_date.getText().toString());
                        params.setRemarks("APITest");
                        params.setContactNo(edt_contact.getText().toString());
                        params.setDocumentUrl("");


                        //if compoff grid bind or not
                        if(mCompOffAdapter!=null){
                            //from adapter public method called...
                            List<CompoffLeaveModel.TableBean> stList = (mCompOffAdapter).getEmployeeList();

                            List<ApplyLeaveParams.DtCompoffBean> stListTemp = new ArrayList<>();
                            ApplyLeaveParams.DtCompoffBean modelTemp;

                            for (int i = 0; i < stList.size(); i++) {
                                CompoffLeaveModel.TableBean bean = stList.get(i);
                                if (bean.isSelected() == true) {
                                    modelTemp = new ApplyLeaveParams.DtCompoffBean();
                                    modelTemp.setBalance(String.valueOf(bean.getBalance()));
                                    modelTemp.setAttendancePK(bean.getAttendancePK());
                                    stListTemp.add(modelTemp);

                                    //  secemp = secemp + "\n" + bean.getDate().toString();
                                }
                            }


                            params.setDtCompoff(stListTemp); //<<<<<<<<<<<<
                        }
                        else{
                            params.setDtCompoff(null);
                        }


                        //call common detail api here..
                        new WebServices(mContext, this,
                                false, true).
                                callApplyLeaveAPI(mToken, params); //from_last = ""

                     }
                }

                break;

            case R.id.iv_dialog_cancel:
                dismiss();
                break;

            case R.id.edt_from_date:
                flag = "from_date";
                showDatePickerDialog();

                break;

            case R.id.edt_to_date:
                flag = "to_date";
                showDatePickerDialog();

                break;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void commonValidation() {
        try{
            if(edt_from_date.getText().toString().length()==0){
                CommonUtils.getInstance().displayToast(mContext,"From Date must be selected !");
            }
            else{
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String str1 = edt_from_date.getText().toString();
                Date date1 = formatter.parse(str1);

                String str2 = edt_to_date.getText().toString();
                Date date2 = formatter.parse(str2);

                long diff = date2.getTime() - date1.getTime();
                Integer _day = (int) (long)  TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                _day = _day+1; //not difference but actual day
                _day_temp = _day;

                if(mBalance.contains(".0")){
                    mBalance = mBalance.replace(".0","");
                }

               else if(mBalance.contains(".5")){
                    mBalance = mBalance.replace(".5","");
                }

                //CommonUtils.getInstance().displayToast(mContext," >>> "+TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));

                if (date2.compareTo(date1)<0)
                {
                    CommonUtils.getInstance().displayToast(mContext,"To Date Must Be Greater Than From Date");
                    edt_to_date.setText(""); //to date hide
                    //upload doc hide
                    btn_upload_doc.setVisibility(View.GONE);
                    //duration / total days hide
                    ll_duration.setVisibility(View.GONE);
                }
                else{
                    if(edt_to_date.getText().toString().length()>0&&edt_from_date.getText().toString().length()>0 ){

                        //show total days
                        ll_duration.setVisibility(View.VISIBLE);
                        txt_duration.setText(String.valueOf(_day));
                    }
                    else{
                        //hide total days
                        ll_duration.setVisibility(View.GONE);
                    }

                    if(_day> Integer.parseInt(mBalance)){
                        CommonUtils.getInstance().displayToast(mContext,"You can't apply more than your leave balance");
                        edt_to_date.setText("");
                        edt_from_date.setText("");
                        //duration / total days hide
                        ll_duration.setVisibility(View.GONE);
                    }

                    if(flag.equalsIgnoreCase("to_date")){
                        callIsDocumentRequireAPI();
                        //api call for isLeave applied or not
                    }
                    else
                    {
                        checkSandwichforFromDate(edt_from_date.getText().toString());
                    }

                }
            }

        }catch (ParseException e1){
            e1.printStackTrace();
            ll_duration.setVisibility(View.GONE);
            if(edt_to_date.getText().toString().length()==0){
                checkSandwichforFromDate(edt_from_date.getText().toString());

            }


        }

    }

    private void checkSandwichforFromDate(String date) {
       // {"EmployeeFK":8816,"LeaveTypeFK":6,"LeaveDayTypeFK":2,"LeaveFromDate":"22/11/2017"}
        SandwichParams params = new SandwichParams();
        //String mToken = Pref.getValue(mContext, Constant.PREF_TOKEN,"");
        params.setEmployeeFK(Pref.getValue(mContext,Constant.PREF_SESSION_EMPLOYEE_FK,0));
        if(rb_halfday.isChecked()){
            params.setLeaveDayTypeFK(1);
        }else{
            //for fullday
            params.setLeaveDayTypeFK(2);
        }
        params.setLeaveTypeFK(Integer.parseInt(mLeaveTypeFK));
        params.setLeaveFromDate(date); //from date


        //call common detail api here..
        new WebServices(mContext/* ActivityContext */, this /* ApiListener */,
                false /* show progress dialog */,true).
                checkLeaveForSandwhich(mToken,params ,"from_date"); //from_last = ""

    }


    private void checkSandwichforToDate(String date) {
        // {"EmployeeFK":8816,"LeaveTypeFK":6,"LeaveDayTypeFK":2,"LeaveFromDate":"22/11/2017"}
        SandwichParams params = new SandwichParams();
        //String mToken = Pref.getValue(mContext, Constant.PREF_TOKEN,"");
        params.setEmployeeFK(Pref.getValue(mContext,Constant.PREF_SESSION_EMPLOYEE_FK,0));
        if(rb_halfday.isChecked()){
            params.setLeaveDayTypeFK(1);
        }else{
            //for fullday
            params.setLeaveDayTypeFK(2);
        }
        params.setLeaveTypeFK(Integer.parseInt(mLeaveTypeFK));
        params.setLeaveToDate(date); //from date

        //call common detail api here..
        new WebServices(mContext/* ActivityContext */, this /* ApiListener */,
                false /* show progress dialog */,true).
                checkLeaveForSandwhich(mToken,params,"to_date"); //from_last = ""

    }



    public void showDatePickerDialog() {
//        DialogFragment newFragment = new DatePickerFragment();
//        newFragment.show(getSupportFragmentManager(), "datePicker");
        Calendar now = Calendar.getInstance();
        com.wdullaer.materialdatetimepicker.date.DatePickerDialog startDpd =
                com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(
                        this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)

                );

        //for disable previous date
      //  Calendar minAge = Calendar.getInstance();
      //  startDpd.setMinDate(minAge);


        //   minAge.set(Calendar.YEAR, now.get(Calendar.YEAR));
        //startDpd.setMaxDate(calendarEndDate);

        // startDpd.setMaxDate(minAge);
        // startDpd.setMinDate();

        desableDate(startDpd,model_weekoff); //pass week-off day here...
        
        startDpd.setAccentColor(mContext.getResources().getColor(R.color.colorPrimary));
        startDpd.show(mActivity.getFragmentManager(), "Datepickerdialog");

    }

    private void desableDate(DatePickerDialog dpd , WeekOffModel model_weekoff) {


        Calendar sunday;//0
        Calendar monday; //1
        Calendar tuesday;//2
        Calendar wednesday;//3
        Calendar thursday;//4
        Calendar friday;//5
        Calendar saturday ;//6

        List<Calendar> weekends = new ArrayList<>();
        List<Calendar> weekends_previous= new ArrayList<>();
        List<Calendar> weekends_current = new ArrayList<>();
        int weeks = 600;



        for (int i = 0; i <model_weekoff.getTable1().size() ; i++) {

           if(model_weekoff.getTable1().get(i).getWeekOff().equalsIgnoreCase("0")){

               for (int j = 0; j < (weeks * 7) ; j = j + 7) {
                   sunday = Calendar.getInstance();
                   sunday.add(Calendar.DAY_OF_YEAR, (Calendar.SUNDAY - sunday.get(Calendar.DAY_OF_WEEK) + 7 + j));
                   weekends.add(sunday);
               }
           }

            if(model_weekoff.getTable1().get(i).getWeekOff().equalsIgnoreCase("1")){
                for (int j = 0; j < (weeks * 7) ; j = j + 7) {
                    monday = Calendar.getInstance();
                    monday.add(Calendar.DAY_OF_YEAR, (Calendar.MONDAY - monday.get(Calendar.DAY_OF_WEEK) + 7 + j));
                    weekends.add(monday);
                }
            }


            if(model_weekoff.getTable1().get(i).getWeekOff().equalsIgnoreCase("2")){
                for (int j = 0; j < (weeks * 7) ; j = j + 7) {
                    tuesday = Calendar.getInstance();
                    tuesday.add(Calendar.DAY_OF_YEAR, (Calendar.TUESDAY - tuesday.get(Calendar.DAY_OF_WEEK) + 7 + j));
                    weekends.add(tuesday);
                }
            }


            if(model_weekoff.getTable1().get(i).getWeekOff().equalsIgnoreCase("3")){
                for (int j = 0; j < (weeks * 7) ; j = j + 7) {
                    wednesday = Calendar.getInstance();
                    wednesday.add(Calendar.DAY_OF_YEAR, (Calendar.WEDNESDAY - wednesday.get(Calendar.DAY_OF_WEEK) + 7 + j));
                    weekends.add(wednesday);
                }
            }



            if(model_weekoff.getTable1().get(i).getWeekOff().equalsIgnoreCase("4")){
                for (int j = 0; j < (weeks * 7) ; j = j + 7) {
                    thursday = Calendar.getInstance();
                    thursday.add(Calendar.DAY_OF_YEAR, (Calendar.THURSDAY - thursday.get(Calendar.DAY_OF_WEEK) + 7 + j));
                    weekends.add(thursday);
                }
            }



            if(model_weekoff.getTable1().get(i).getWeekOff().equalsIgnoreCase("5")){
                for (int j = 0; j < (weeks * 7) ; j = j + 7) {
                    friday = Calendar.getInstance();
                    friday.add(Calendar.DAY_OF_YEAR, (Calendar.FRIDAY - friday.get(Calendar.DAY_OF_WEEK) + 7 + j));
                    weekends.add(friday);
                }
            }


            if(model_weekoff.getTable1().get(i).getWeekOff().equalsIgnoreCase("6")){
                for (int j = 0; j < (weeks * 7) ; j = j + 7) {
                    saturday = Calendar.getInstance();
                    saturday.add(Calendar.DAY_OF_YEAR, (Calendar.SATURDAY - saturday.get(Calendar.DAY_OF_WEEK) + 7 + j));
                    weekends.add(saturday);
                }
            }


            //disable previous dates....for week off...


            if(model_weekoff.getTable1().get(i).getWeekOff().equalsIgnoreCase("0")){

                for (int j = 0; j < (weeks * 7) ; j = j + 7) {
                    sunday = Calendar.getInstance();
                    sunday.add(Calendar.DAY_OF_YEAR, (Calendar.SUNDAY - sunday.get(Calendar.DAY_OF_WEEK) - 7 - j));
                    weekends_previous.add(sunday);
                }
            }

            if(model_weekoff.getTable1().get(i).getWeekOff().equalsIgnoreCase("1")){
                for (int j = 0; j < (weeks * 7) ; j = j + 7) {
                    monday = Calendar.getInstance();
                    monday.add(Calendar.DAY_OF_YEAR, (Calendar.MONDAY - monday.get(Calendar.DAY_OF_WEEK) - 7 - j));
                    weekends_previous.add(monday);
                }
            }


            if(model_weekoff.getTable1().get(i).getWeekOff().equalsIgnoreCase("2")){
                for (int j = 0; j < (weeks * 7) ; j = j + 7) {
                    tuesday = Calendar.getInstance();
                    tuesday.add(Calendar.DAY_OF_YEAR, (Calendar.TUESDAY - tuesday.get(Calendar.DAY_OF_WEEK) - 7 - j));
                    weekends_previous.add(tuesday);
                }
            }


            if(model_weekoff.getTable1().get(i).getWeekOff().equalsIgnoreCase("3")){
                for (int j = 0; j < (weeks * 7) ; j = j + 7) {
                    wednesday = Calendar.getInstance();
                    wednesday.add(Calendar.DAY_OF_YEAR, (Calendar.WEDNESDAY - wednesday.get(Calendar.DAY_OF_WEEK) - 7 - j));
                    weekends_previous.add(wednesday);
                }
            }



            if(model_weekoff.getTable1().get(i).getWeekOff().equalsIgnoreCase("4")){
                for (int j = 0; j < (weeks * 7) ; j = j + 7) {
                    thursday = Calendar.getInstance();
                    thursday.add(Calendar.DAY_OF_YEAR, (Calendar.THURSDAY - thursday.get(Calendar.DAY_OF_WEEK) - 7 - j));
                    weekends_previous.add(thursday);
                }
            }



            if(model_weekoff.getTable1().get(i).getWeekOff().equalsIgnoreCase("5")){
                for (int j = 0; j < (weeks * 7) ; j = j + 7) {
                    friday = Calendar.getInstance();
                    friday.add(Calendar.DAY_OF_YEAR, (Calendar.FRIDAY - friday.get(Calendar.DAY_OF_WEEK) - 7 - j));
                    weekends_previous.add(friday);
                }
            }


            if(model_weekoff.getTable1().get(i).getWeekOff().equalsIgnoreCase("6")){
                for (int j = 0; j < (weeks * 7) ; j = j + 7) {
                    saturday = Calendar.getInstance();
                    saturday.add(Calendar.DAY_OF_YEAR, (Calendar.SATURDAY - saturday.get(Calendar.DAY_OF_WEEK) - 7 - j));
                    weekends_previous.add(saturday);
                }
            }


            //current week days disable
            if(model_weekoff.getTable1().get(i).getWeekOff().equalsIgnoreCase("0")){


                    sunday = Calendar.getInstance();
                    sunday.add(Calendar.DAY_OF_YEAR, (Calendar.SUNDAY - sunday.get(Calendar.DAY_OF_WEEK)));
                    weekends_previous.add(sunday);

            }

            if(model_weekoff.getTable1().get(i).getWeekOff().equalsIgnoreCase("1")){
                    monday = Calendar.getInstance();
                    monday.add(Calendar.DAY_OF_YEAR, (Calendar.MONDAY - monday.get(Calendar.DAY_OF_WEEK)));
                    weekends_previous.add(monday);
            }


            if(model_weekoff.getTable1().get(i).getWeekOff().equalsIgnoreCase("2")){
                    tuesday = Calendar.getInstance();
                    tuesday.add(Calendar.DAY_OF_YEAR, (Calendar.TUESDAY - tuesday.get(Calendar.DAY_OF_WEEK)));
                    weekends_previous.add(tuesday);
            }


            if(model_weekoff.getTable1().get(i).getWeekOff().equalsIgnoreCase("3")){
                    wednesday = Calendar.getInstance();
                    wednesday.add(Calendar.DAY_OF_YEAR, (Calendar.WEDNESDAY - wednesday.get(Calendar.DAY_OF_WEEK)));
                    weekends_previous.add(wednesday);

            }



            if(model_weekoff.getTable1().get(i).getWeekOff().equalsIgnoreCase("4")){
                    thursday = Calendar.getInstance();
                    thursday.add(Calendar.DAY_OF_YEAR, (Calendar.THURSDAY - thursday.get(Calendar.DAY_OF_WEEK)));
                    weekends_previous.add(thursday);

            }



            if(model_weekoff.getTable1().get(i).getWeekOff().equalsIgnoreCase("5")){
                    friday = Calendar.getInstance();
                    friday.add(Calendar.DAY_OF_YEAR, (Calendar.FRIDAY - friday.get(Calendar.DAY_OF_WEEK)));
                    weekends_previous.add(friday);

            }


            if(model_weekoff.getTable1().get(i).getWeekOff().equalsIgnoreCase("6")){
                    saturday = Calendar.getInstance();
                    saturday.add(Calendar.DAY_OF_YEAR, (Calendar.SATURDAY - saturday.get(Calendar.DAY_OF_WEEK)));
                    weekends_previous.add(saturday);
            }


        }



        /**
         *
         * combine previous and future dates that going to be disable as week off
        */


        weekends.addAll(weekends_previous);
       //weekends.addAll(weekends_current);

        Calendar[] disabledDays = weekends.toArray(new Calendar[weekends.size()]);
        dpd.setDisabledDays(disabledDays);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

        // String date = new StringBuilder().append(dayOfMonth).append("/").append(++monthOfYear).append("/").append(year).toString();


        String date = new StringBuilder().append(dayOfMonth).append("/").append(++monthOfYear).append("/").append(year).toString();
        // txtBirthday.setText(date);
        String[] separated = date.split("/");
        if(separated[0].length() == 1){
            separated[0] = "0" + separated[0];
        }
        if (separated[1].length() == 1) {
            separated[1] = "0" + separated[1];
        }
        if (separated[2].length() == 1) {
            separated[2] = "0" + separated[2];
        }

        date = separated[0] + "/" + separated[1] + "/" + separated[2];

        if (flag.equalsIgnoreCase("from_date")) {
            edt_from_date.setText(date);
            if(rb_halfday.isChecked()){
                //to_date = form_date + disable to_date
                edt_to_date.setText(date);
                edt_to_date.setFocusable(false);
                //  setDuration = 0.5
            }else{
                //for fullday
                edt_to_date.setText("");
                edt_to_date.setFocusable(true);
                commonValidation();

            }

        } else if (flag.equalsIgnoreCase("to_date")) {
            //d2>d1
            //common validation
            edt_to_date.setText(date);
            commonValidation();



        }
        flag = "";
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onApiSuccess(Object mObject) {

        if (mObject instanceof String) {

            try {
                JSONObject jsonObj = new JSONObject(mObject.toString());
                Log.e("", "onApiSuccess: json >>  " + mObject.toString());
                //parse common detail data
                Gson gson = new Gson();
                if(mObject.toString().contains("EmployeeEmail")){
                    ApplyLeaveModel model = gson.fromJson(mObject.toString(), ApplyLeaveModel.class);
                    if(model.getTable().get(0).getResult()==1){

                        CommonUtils.getInstance().displayToast(mContext,"Leave apply successfully.");
                        //callback to fragment and reload all apis
                        mInterface.recallAllAPI();
                        dismiss();
                    }
                }

                //sandwich from date
                else if(mObject.toString().contains("FromDate")){
                    SandwichFormDateModel model = gson.fromJson(mObject.toString(), SandwichFormDateModel.class);
                    if(model.getTable().get(0).getResult()==2){
                        //set response date as form date
                        edt_from_date.setText(model.getTable().get(0).getFromDate());
                        CommonUtils.getInstance().displayToast(mContext,"Due To Sandwich Rule From Date Changed by system.");
                    }
                    else{

                    }
                    //commonValidation2();
                }

                //sandwich to date
                else if(mObject.toString().contains("ToDate")){
                    SandwichToDateModel model = gson.fromJson(mObject.toString(), SandwichToDateModel.class);
                    if(model.getTable().get(0).getResult()==2){
                        //set response date as form date
                        edt_to_date.setText(model.getTable().get(0).getToDate());
                        CommonUtils.getInstance().displayToast(mContext,"Due To Sandwich Rule To Date Changed by system.");

                    }
                    else{

                    }
                    //commonValidation2();

                    if(edt_to_date.getText().toString().length()>0&&edt_from_date.getText().toString().length()>0 ){
                        //show total days
                        try {
                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                            String str1 = edt_from_date.getText().toString();
                            Date date1 = formatter.parse(str1);
                            String str2 = edt_to_date.getText().toString();
                            Date date2 = formatter.parse(str2);
                            long diff = date2.getTime() - date1.getTime();
                            Integer _day = (int) (long) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                            _day = _day+1; //not difference but actual day

                            _day_temp = _day;

                            ll_duration.setVisibility(View.VISIBLE);
                            txt_duration.setText(String.valueOf(_day));
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }
                    }
                    else{
                        //hide total days
                        ll_duration.setVisibility(View.GONE);
                    }

                }
                //for Bind WO
                else if(mObject.toString().contains("WeekOff")){
                    model_weekoff = gson.fromJson(mObject.toString(), WeekOffModel.class);
                   // CommonUtils.getInstance().displayToast(mContext,"Bind WO");
                    Log.e("", "onApiSuccess: Bind WO json >>  " + mObject.toString());
                }

                //is doc require ?
                else if(is_api_for.equalsIgnoreCase("IsDocumentRequire")){
                    Log.e("", "onApiSuccess: IsDocumentRequire json >> " + mObject.toString());
                    model_require_doc = gson.fromJson(mObject.toString(), IsDocumentRequire.class);
                    // CommonUtils.getInstance().displayToast(mContext,"Bind WO");
                    if(model_require_doc.getTable().get(0).getResult()==1){
                        //show upload button
                        btn_upload_doc.setVisibility(View.VISIBLE);
                    }
                    else{
                        //hide upload button
                        btn_upload_doc.setVisibility(View.GONE);
                    }

                    //2nd call
                    callIsLeaveApplied();
                }
                else if(is_api_for.equalsIgnoreCase("IsLeaveApplied")){
                    Log.e("", "onApiSuccess: IsLeaveApply json >> " + mObject.toString());
                    model_Leave_applay = gson.fromJson(mObject.toString(), IsLeaveaAppliedModel.class);
                    if(model_Leave_applay.getTable().get(0).isReturnValue()){

                        CommonUtils.getInstance().displayToast(mContext,"You already apply leave or Present for this day");
                        edt_from_date.setText("");
                        edt_to_date.setText("");
                        ll_duration.setVisibility(View.GONE);
                    }
                    else{
                        //3rd call
                        checkSandwichforToDate(edt_to_date.getText().toString());
                    }
                }

                else if(is_api_for.equalsIgnoreCase("CompoffLeave")){
                    Log.e("", "onApiSuccess: CompoffLeave json >> " + mObject.toString());
                    model_compoff = gson.fromJson(mObject.toString(), CompoffLeaveModel.class);
                    //set compoff recycler view..

                    ArrayList<CompoffLeaveModel.TableBean> mCheckList = new ArrayList<>();
                    //by default all false
                    for (int i = 0; i <model_compoff.getTable().size() ; i++) {
                        model_compoff.getTable().get(i).setSelected(false);
                    }
                    //--------------

                    mCheckList.addAll(model_compoff.getTable());

                    mCompOffAdapter = new CompOffAdapter(mContext,model_compoff,mCheckList);

                    rv_comp_off.setAdapter(mCompOffAdapter);
                    rv_comp_off.setNestedScrollingEnabled(false); //for smooth nested scroll

                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onApiFailure(Throwable mThrowable) {

    }


    private void callIsLeaveApplied() {
        // isleave apply or not
        // {"EmployeeFK":8816,"FromDate":"24/11/2017","ToDate":"24/11/2017"}

        IsLeaveAppliedParams params2 = new IsLeaveAppliedParams();
        //   String mToken = Pref.getValue(mContext, Constant.PREF_TOKEN,"");
        params2.setEmployeeFK(Pref.getValue(mContext,Constant.PREF_SESSION_EMPLOYEE_FK,0));
        params2.setFromDate(edt_from_date.getText().toString());
        params2.setToDate(edt_to_date.getText().toString());

        is_api_for="IsLeaveApplied";

        //call common detail api here..
        new WebServices(mContext/* ActivityContext */, this /* ApiListener */,
                false /* show progress dialog */,true).
                callIsLeaveApplyAPI(mToken,params2); //from_last = ""
    }

    private void callIsDocumentRequireAPI() {
        //1st call is doc require
        //2nd call is leave already applied
        //3rd call check sandwich for todate

        //1st
        IsDocumentRequireParams params = new IsDocumentRequireParams();
        //   String mToken = Pref.getValue(mContext, Constant.PREF_TOKEN,"");
        params.setLeaveTypeFK(Integer.parseInt(mLeaveTypeFK));
        params.setTotalDays(txt_duration.getText().toString());
        is_api_for="IsDocumentRequire";

        //call common detail api here..
        new WebServices(mContext/* ActivityContext */, this /* ApiListener */,
                false /* show progress dialog */,true).
                callIsDocRequireAPI(mToken,params); //from_last = ""

    }
    private View.OnTouchListener otl = new View.OnTouchListener() {
        public boolean onTouch (View v, MotionEvent event) {
            return true; // the listener has consumed the event
        }
    };

    public boolean isValidateAll() {

        if(isCompoff == 1){
            mListCompoffCheck.clear();
            mListCompoffCheckModel.clear();
            List<CompoffLeaveModel.TableBean> stList = (mCompOffAdapter).getEmployeeList();
            for (int i = 0; i < stList.size(); i++) {
                CompoffLeaveModel.TableBean bean = stList.get(i);
                if (bean.isSelected() == true) {
                    mListCompoffCheck.add(String.valueOf(bean.isSelected()));
                }
            }

            if(mListCompoffCheck.size()==0){
               CommonUtils.getInstance().displayToast(mContext,"Please select a date against which you would like to apply comp off.");
                return false;
            }
            //half day && single check box must click not multiple
           else if(rb_halfday.isChecked() && mListCompoffCheck.size()>1 ){

                CommonUtils.getInstance().displayToast(mContext,"Please select a date (single selection) against which you would like to apply comp off.");
                return false;
            }

        }

        //half day checked _day_temp
        if(rb_halfday.isChecked()){
            double _day_temp_double = 0.5 ;
            //applied days> balance && halfday selected && iscompoff == 1
            if(_day_temp_double> Double.parseDouble(mBalance) && rb_halfday.isChecked() && isCompoff == 1){
                CommonUtils.getInstance().displayToast(mContext,"Comp off days (applied) should match with balance sum of days selected.");
                return false;
            }
        }



        if(rb_fullday.isChecked()){
            //applied days> balance && fullday  selected && iscompoff == 1
            if(_day_temp> Integer.parseInt(mBalance) && rb_fullday.isChecked() && isCompoff == 1){
                CommonUtils.getInstance().displayToast(mContext,"Comp off days (applied) should match with balance sum of days selected.");
                return false;
            }
        }


        return true;
    }
}
