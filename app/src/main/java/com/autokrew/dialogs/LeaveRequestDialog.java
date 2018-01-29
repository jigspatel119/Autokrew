package com.autokrew.dialogs;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.autokrew.R;
import com.autokrew.interfaces.AttendanceDialogInterface;
import com.autokrew.models.ApplyLeaveModel;
import com.autokrew.models.GroupLeaveModel;
import com.autokrew.models.ManageLeaveDetailModel;
import com.autokrew.models.ManageLeaveParams;
import com.autokrew.models.TeamOrGroupLeaveModelParams;
import com.autokrew.network.ApiListener;
import com.autokrew.network.WebServices;
import com.autokrew.utils.CommonUtils;
import com.autokrew.utils.Constant;
import com.autokrew.utils.Pref;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;


public class LeaveRequestDialog extends AppCompatDialog implements View.OnClickListener,ApiListener {

    private Context mContext;
    private Activity mActivity;
    Button btn_approve,btn_reject;
    ImageView iv_dialog_cancel;

    int leaveDetailPK;
    String mToken;
    String from_last;
    ManageLeaveDetailModel model_detail;

    TextView txt_name,txt_reporting_person,txt_leave_type,txt_leave_day_type,
            txt_duration,txt_contact_no ,txt_reason;
    EditText edt_remarks;

    int position;
    AttendanceDialogInterface mInterface;
    GroupLeaveModel model ;


    public LeaveRequestDialog(Context context, int position , int leaveDetailPK , AttendanceDialogInterface mInterface
            , GroupLeaveModel model) {

        super(context);

        this.mContext = context;
        mActivity = (Activity) mContext;
        this.leaveDetailPK= leaveDetailPK;
        this.position = position;
        this.mInterface = mInterface;
        this.model = model;

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
        setContentView(R.layout.dialog_leave_request);


        getData();
        findView();

        setListner();

    }

    private void getData() {
        mToken = Pref.getValue(mContext,Constant.PREF_TOKEN,"");

        //api calls
        // {"Flag":"Detail","SessionEmployeeFk":8816,"LeaveDetailPK":6719,
        // "ApprovalStatus":null,"ApprovalRemarks":null}

        ManageLeaveParams params = new ManageLeaveParams();
        params.setFlag("Detail");
        params.setSessionEmployeeFk(Pref.getValue(mContext, Constant.PREF_SESSION_EMPLOYEE_FK,0));
        params.setLeaveDetailPK(leaveDetailPK);
        params.setApprovalRemarks("");
        params.setApprovalStatus("");

        from_last = "Detail";
        new WebServices(mContext/* ActivityContext */, this /* ApiListener */,
                false /* show progress dialog */,true).
                callManageLeaveAPI(mToken,params);
    }


    private void setData() {


        txt_name.setText(model_detail.getTable().get(0).getName());
        txt_reporting_person.setText(model_detail.getTable().get(0).getReportingPerson());
        txt_leave_type.setText(model_detail.getTable().get(0).getLeaveType());
       // txt_leave_day_type.setText(model_detail.getTable().get(position).getlea);
        txt_duration.setText(String.valueOf(model_detail.getTable().get(0).getTotalDays()));
        txt_contact_no.setText(model_detail.getTable().get(0).getContactNo());
        txt_reason.setText(model_detail.getTable().get(0).getRemarks());

    }

    private void setListner() {
        btn_approve.setOnClickListener(this);
        btn_reject.setOnClickListener(this);
        iv_dialog_cancel.setOnClickListener(this);

    }

    private void findView() {
        btn_approve = (Button) this.findViewById(R.id.btn_approve);
        iv_dialog_cancel = (ImageView) this.findViewById(R.id.iv_dialog_cancel);
        btn_reject = (Button) this.findViewById(R.id.btn_reject);

        txt_reason = (TextView)this.findViewById(R.id.txt_reason);
        txt_name = (TextView)this.findViewById(R.id.txt_name);
        txt_reporting_person = (TextView)this.findViewById(R.id.txt_reporting_person);
        txt_leave_type = (TextView)this.findViewById(R.id.txt_leave_type);
        txt_leave_day_type = (TextView)this.findViewById(R.id.txt_leave_day_type);
        txt_duration = (TextView)this.findViewById(R.id.txt_duration);
        txt_contact_no = (TextView)this.findViewById(R.id.txt_contact_no);

        edt_remarks = (EditText)this.findViewById(R.id.edt_remarks);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btn_approve:
                if(edt_remarks.getText().toString().length()==0){
                    CommonUtils.getInstance().displayToast(mContext,"Please enter remarks!");

                }else{
                    ManageLeaveParams params = new ManageLeaveParams();
                    params.setFlag("Approve");
                    params.setSessionEmployeeFk(Pref.getValue(mContext, Constant.PREF_SESSION_EMPLOYEE_FK,0));
                    params.setLeaveDetailPK(leaveDetailPK);
                    params.setApprovalRemarks(edt_remarks.getText().toString());
                    params.setApprovalStatus(2); //2  for approve

                    from_last = "Approve";
                    new WebServices(mContext/* ActivityContext */, this /* ApiListener */,
                            false /* show progress dialog */,true).
                            callManageLeaveAPI(mToken,params);
                }

                break;

            case R.id.iv_dialog_cancel:


                dismiss();
                break;

            case R.id.btn_reject:
                if(edt_remarks.getText().toString().length()==0){
                    CommonUtils.getInstance().displayToast(mContext,"Please enter remarks!");

                }else {
                    ManageLeaveParams params2 = new ManageLeaveParams();
                    params2.setFlag("Approve");
                    params2.setSessionEmployeeFk(Pref.getValue(mContext, Constant.PREF_SESSION_EMPLOYEE_FK, 0));
                    params2.setLeaveDetailPK(leaveDetailPK);
                    params2.setApprovalRemarks(edt_remarks.getText().toString());
                    params2.setApprovalStatus(3); //3 for reject

                    from_last = "Reject";
                    new WebServices(mContext/* ActivityContext */, this /* ApiListener */,
                            true /* show progress dialog */, true).
                            callManageLeaveAPI(mToken, params2);

                }

                break;
        }

    }

    @Override
    public void onApiSuccess(Object mObject) {
        if (mObject instanceof String) {

            try {
                JSONObject jsonObj = new JSONObject(mObject.toString());

                //parse common detail data
                Gson gson = new Gson();
                if(from_last.equalsIgnoreCase("Detail")){
                    Log.e("", "onApiSuccess:Detail json >>  " + mObject.toString());
                    model_detail = gson.fromJson(mObject.toString(), ManageLeaveDetailModel.class);
                    setData();
                }

                if(from_last.equalsIgnoreCase("Approve")){
                    Log.e("", "onApiSuccess:Approve  json >>  " + mObject.toString());
                    model_detail = gson.fromJson(mObject.toString(), ManageLeaveDetailModel.class);
                    dismiss();
                }

                if(from_last.equalsIgnoreCase("Reject")){
                    Log.e("", "onApiSuccess:Reject  json >>  " + mObject.toString());
                    model_detail = gson.fromJson(mObject.toString(), ManageLeaveDetailModel.class);
                    dismiss();

                }

                //callback to fragment and reload all apis
                mInterface.recallAllAPI();


            } catch (JSONException e) {

                e.printStackTrace();
            }
        }
    }

    @Override
    public void onApiFailure(Throwable mThrowable) {

    }
}
