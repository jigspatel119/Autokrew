package com.autokrew.dialogs;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;



public class EmployeeDetailDialog extends AppCompatDialog implements View.OnClickListener{

    private Context mContext;
    private Activity mActivity;

    TextView txt_emp_code, txt_emp_name, txt_reporting_person,
            txt_company, txt_department, txt_sub_dept, txt_location, txt_sub_location;


    String mTxtEmpCode, mTxtEmpName, mTxtReportingPerson,
            mTxtCompany, mTxtDept, mTxtSubDept, mTxtLoc, mTxtSubLoc;


    ImageView iv_dialog_cancel;


    public EmployeeDetailDialog(Context context,
                                String mTxtEmpCode
                                , String mTxtEmpName
                                , String mTxtReportingPerson
                                , String mTxtCompany
                                , String mTxtDept
                                , String mTxtSubDept
                                , String mTxtLoc
                                , String mTxtSubLoc
    ) {
        super(context);
        this.mContext = context;
        mActivity = (Activity) mContext;
        this.mTxtEmpCode = mTxtEmpCode;
        this.mTxtEmpName = mTxtEmpName;
        this.mTxtReportingPerson = mTxtReportingPerson;
        this.mTxtCompany = mTxtCompany;
        this.mTxtDept = mTxtDept;
        this.mTxtSubDept = mTxtSubDept;
        this.mTxtLoc = mTxtLoc;
        this.mTxtSubLoc = mTxtSubLoc;
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
        setContentView(R.layout.dialog_employee_detail);

        getData();
        findView();
        setData();
    }

    private void getData() {


    }


    private void setData() {

        txt_emp_code.setText(mTxtEmpCode);
        txt_emp_name.setText(mTxtEmpName);
        txt_reporting_person.setText(mTxtReportingPerson);
        txt_company.setText(mTxtCompany);
        txt_department.setText(mTxtDept);
        txt_sub_dept.setText(mTxtSubDept);
        txt_location.setText(mTxtLoc);
        txt_sub_location.setText(mTxtSubLoc);


    }

    private void findView() {

        txt_emp_code = (TextView) findViewById(R.id.txt_emp_code);
        txt_emp_name = (TextView) findViewById(R.id.txt_emp_name);
        txt_reporting_person = (TextView) findViewById(R.id.txt_reporting_person);
        txt_company = (TextView) findViewById(R.id.txt_company);
        txt_department = (TextView) findViewById(R.id.txt_department);
        txt_sub_dept = (TextView) findViewById(R.id.txt_sub_dept);
        txt_location = (TextView) findViewById(R.id.txt_location);
        txt_sub_location = (TextView) findViewById(R.id.txt_sub_location);

        iv_dialog_cancel = (ImageView)findViewById(R.id.iv_dialog_cancel);
        iv_dialog_cancel.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.iv_dialog_cancel:
                dismiss();
                break;


        }

    }
}
