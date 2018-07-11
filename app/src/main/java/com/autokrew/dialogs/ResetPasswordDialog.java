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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.autokrew.R;
import com.autokrew.activity.MainActivity;
import com.autokrew.activity.SigninActivity;
import com.autokrew.interfaces.AttendanceDialogInterface;
import com.autokrew.models.GroupLeaveModel;
import com.autokrew.models.ManageLeaveParams;
import com.autokrew.models.ResetPasswordParams;
import com.autokrew.network.ApiListener;
import com.autokrew.network.WebServices;
import com.autokrew.utils.CommonUtils;
import com.autokrew.utils.Constant;
import com.autokrew.utils.Pref;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;


public class ResetPasswordDialog extends AppCompatDialog implements View.OnClickListener,ApiListener {

    private Context mContext;
    private Activity mActivity;
    ImageView iv_dialog_cancel;

    int leaveDetailPK;
    String mToken;
   // String from_last;

    EditText edt_old_pass,edt_new_password,edt_renew_password;
    TextView txt_reset_pass;

    String form_last = "";


    public ResetPasswordDialog(Context context ,String from_last) {

        super(context);

        this.mContext = context;
        mActivity = (Activity) mContext;
        this.form_last = from_last;
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
        setContentView(R.layout.dialog_reset_pasword);


        getData();
        findView();

        setListner();

    }

    private void getData() {
        mToken = Pref.getValue(mContext,Constant.PREF_TOKEN,"");

        //api calls
        // {"Flag":"Detail","SessionEmployeeFk":8816,"LeaveDetailPK":6719,
        // "ApprovalStatus":null,"ApprovalRemarks":null}
    }


    private void setData() {

    }

    private void setListner() {
        txt_reset_pass.setOnClickListener(this);
        iv_dialog_cancel.setOnClickListener(this);

    }

    private void findView() {
        iv_dialog_cancel = (ImageView) this.findViewById(R.id.iv_dialog_cancel);
        edt_old_pass = (EditText)this.findViewById(R.id.edt_old_pass);
        edt_new_password = (EditText)this.findViewById(R.id.edt_new_password);
        edt_renew_password = (EditText)this.findViewById(R.id.edt_renew_password);
        txt_reset_pass = (TextView)this.findViewById(R.id.txt_reset_pass);        
        
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.txt_reset_pass:
                if(edt_old_pass.getText().toString().length()==0){
                    CommonUtils.getInstance().displayToast(mContext,"Please enter old password.");

                }
                else if(edt_new_password.getText().toString().length()==0){
                    CommonUtils.getInstance().displayToast(mContext,"Please enter new password.");

                }

                else if(edt_old_pass.getText().toString().equalsIgnoreCase(edt_new_password.getText().toString())){
                    CommonUtils.getInstance().displayToast(mContext,"Old password and New password must be different.");
                }

                else if(edt_renew_password.getText().toString().length()==0){
                    CommonUtils.getInstance().displayToast(mContext,"Please enter confirm password.");
                }
                else if(!edt_new_password.getText().toString().equals(edt_renew_password.getText().toString())){
                    CommonUtils.getInstance().displayToast(mContext,"New password and Re-Enter password not match.");
                }



                else{
                    ResetPasswordParams params = new ResetPasswordParams();
                    params.setOldPassword(edt_old_pass.getText().toString());
                    params.setNewPassword(edt_new_password.getText().toString());
                    params.setLoginFK(String.valueOf(Pref.getValue(mContext, Constant.PREF_LOGIN_PK,0)));

                   // from_last = "Approve";
                    new WebServices(mContext , this  ,
                            false  ,true).
                            callResetPasswordAPI(mToken,params);
                }

                break;

            case R.id.iv_dialog_cancel:

               if(form_last.equalsIgnoreCase("dashbord_frag")){

                    dismiss();
                    //signout directly... clear pref..
                    Pref.setValue(mContext, "auto_login","false");
                    //login credential
                    Pref.setValue(mContext,"user_id","");
                    Pref.setValue(mContext,"user_password","");
                    Pref.setValue(mContext,"profile_pic_server" ,""); //clear profile pic
                    Pref.setValue(mContext,"mApprovalStatus","");
                    Pref.setValue(mContext,"mYearPK","");
                    Pref.setValue(mContext,"mMonthPK","");
                    Pref.setValue(mContext,"mEmployeePK","");
                    CommonUtils.getInstance().startActivityWithoutStack(mContext, SigninActivity.class);

               }
                else{
                    dismiss();
                }

               
                break;


        }

    }

    @Override
    public void onApiSuccess(Object mObject) {
        if (mObject instanceof String) {

            try {
               // JSONObject jsonObj = new JSONObject(mObject.toString());
                Log.e("", "onApiSuccess: reset pass json >>  " + mObject.toString());

                CommonUtils.getInstance().displayToast(mContext,""+mObject.toString());

                dismiss();

                //parse common detail data
               // Gson gson = new Gson();


            } catch (Exception e) {

                dismiss();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onApiFailure(Throwable mThrowable) {

    }
}
