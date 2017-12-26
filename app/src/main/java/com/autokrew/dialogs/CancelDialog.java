package com.autokrew.dialogs;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.autokrew.R;
import com.autokrew.interfaces.AttendanceDialogInterface;
import com.autokrew.models.ApplyLeaveModel;
import com.autokrew.models.ApplyLeaveParams;
import com.autokrew.models.CancleLeaveModel;
import com.autokrew.models.CancleLeaveParams;
import com.autokrew.network.ApiListener;
import com.autokrew.network.WebServices;
import com.autokrew.utils.CommonUtils;
import com.autokrew.utils.Constant;
import com.autokrew.utils.Pref;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import static com.autokrew.utils.AppController.getAppContext;


public class CancelDialog extends AppCompatDialog implements View.OnClickListener,ApiListener {

    private static final String TAG = CancelDialog.class.getSimpleName();
    private Context mContext;
    private Activity mActivity;
    Button btn_ok,btn_cancel;
    int leavePK;
    AttendanceDialogInterface mInterface;


    public CancelDialog(Context context,AttendanceDialogInterface mInterface, int position ,int leavePK) {

        super(context);

        this.mContext = context;
        mActivity = (Activity) mContext;
        this.leavePK = leavePK;
        this.mInterface = mInterface;

    }

    @Override
    protected void onStart() {
        super.onStart();
        getWindow().setWindowAnimations(R.style.animation_slide_from_right);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_cancel_leave);


        getData();
        findView();
        setData();
        setListner();

    }

    private void getData() {
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


        Typeface copperplateGothicLight = Typeface.createFromAsset(getAppContext().getAssets(), "GillSans-SemiBold.ttf");
        btn_ok.setTypeface(copperplateGothicLight);
        btn_cancel.setTypeface(copperplateGothicLight);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btn_ok:
                //cancle leave api calls
                CancleLeaveParams params = new CancleLeaveParams();
                String mToken = Pref.getValue(mContext, Constant.PREF_TOKEN,"");
                params.setLeaveDetailPK(String.valueOf(leavePK));
                params.setSessionEmployeeFK(String.valueOf(Pref.getValue(mContext,Constant.PREF_SESSION_EMPLOYEE_FK,0)));

                //call common detail api here..
                new WebServices(mContext/* ActivityContext */, this /* ApiListener */,
                        false /* show progress dialog */,true).
                        callCancleLeaveAPI(mToken,params); //from_last = ""

                break;


            case R.id.btn_cancel:
                dismiss();
                break;
        }

    }

    @Override
    public void onApiSuccess(Object mObject) {
        if (mObject instanceof String) {

            try {
                JSONObject jsonObj = new JSONObject(mObject.toString());
                //parse common detail data
                Log.e("", "onApiSuccess: cancel leave json >>  " + jsonObj.toString());
                Gson gson = new Gson();
                CancleLeaveModel model = gson.fromJson(mObject.toString(), CancleLeaveModel.class);
                if(model.getTable().get(0).getResult()==1){

                    CommonUtils.getInstance().displayToast(mContext,"Leave delete successfully.");
                    //callback to fragment and reload all apis
                    mInterface.recallAllAPI();

                }

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
