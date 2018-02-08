package com.autokrew.dialogs;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.autokrew.R;
import com.autokrew.interfaces.AttendanceDialogInterface;
import com.autokrew.models.ManageLeaveDetailModel;
import com.autokrew.models.ManageLeaveParams;
import com.autokrew.network.ApiListener;
import com.autokrew.network.WebServices;
import com.autokrew.utils.Constant;
import com.autokrew.utils.Pref;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;



public class RevisedDialog extends AppCompatDialog implements View.OnClickListener,ApiListener {

    private Context mContext;
    private Activity mActivity;
    Button btn_ok,btn_cancel;
    int leaveDetailPK;
    AttendanceDialogInterface mInterface;

    String mToken;
    ManageLeaveDetailModel model_detail;



    public RevisedDialog(Context context, AttendanceDialogInterface mInterface, int position , int leaveDetailPK) {

        super(context);

        this.mContext = context;
        mActivity = (Activity) mContext;
        this.leaveDetailPK = leaveDetailPK;
        this.mInterface = mInterface;

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
        setContentView(R.layout.dialog_revise_leave);


        getData();
        findView();
        setData();
        setListner();

    }

    private void getData() {
        mToken = Pref.getValue(mContext,Constant.PREF_TOKEN,"");

    }


    private void setData() {
    }

    private void setListner() {
        btn_ok.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);

    }

    private void findView() {
        btn_ok = (Button) this.findViewById(R.id.btn_ok);
        btn_cancel = (Button) this.findViewById(R.id.btn_cancel);


       /* Typeface copperplateGothicLight = Typeface.createFromAsset(getAppContext().getAssets(), "GillSans-SemiBold.ttf");
        btn_ok.setTypeface(copperplateGothicLight);
        btn_cancel.setTypeface(copperplateGothicLight);*/

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btn_ok:

                ManageLeaveParams params = new ManageLeaveParams();
                params.setFlag("Revise");
                params.setSessionEmployeeFk(Pref.getValue(mContext, Constant.PREF_SESSION_EMPLOYEE_FK,0));
                params.setLeaveDetailPK(leaveDetailPK);
                params.setApprovalRemarks("");
                params.setApprovalStatus(""); //2  for approve

                new WebServices(mContext/* ActivityContext */, this /* ApiListener */,
                        true /* show progress dialog */,true).
                        callManageLeaveAPI(mToken,params);


            case R.id.btn_cancel:
                dismiss();
                break;
        }

    }

    @Override
    public void onApiSuccess(Object mObject) {
        if (mObject instanceof String) {
            Log.e("", "onApiSuccess: json >>  " + mObject.toString());
            try {
                JSONObject jsonObj = new JSONObject(mObject.toString());
                //parse common detail data
                Log.e("", "onApiSuccess: revise leave json >>  " + jsonObj.toString());
                Gson gson = new Gson();
                model_detail = gson.fromJson(mObject.toString(), ManageLeaveDetailModel.class);
                dismiss();

                mInterface.recallAllAPI();

            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        dismiss();
    }

    @Override
    public void onApiFailure(Throwable mThrowable) {

    }
}
