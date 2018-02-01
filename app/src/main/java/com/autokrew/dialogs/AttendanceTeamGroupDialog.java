package com.autokrew.dialogs;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.autokrew.R;
import com.autokrew.interfaces.AttendanceDialogInterface;
import com.autokrew.models.AddDeviationModel;
import com.autokrew.models.AddDeviationParams;
import com.autokrew.models.AddDeviationTeamParams;
import com.autokrew.models.TempParams;
import com.autokrew.network.ApiListener;
import com.autokrew.network.WebServices;
import com.autokrew.utils.CommonUtils;
import com.autokrew.utils.Constant;
import com.autokrew.utils.Pref;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.autokrew.utils.AppController.getAppContext;


public class AttendanceTeamGroupDialog extends AppCompatDialog implements View.OnClickListener,ApiListener {

    private static final String TAG = AttendanceTeamGroupDialog.class.getSimpleName();
    private Context mContext;
    private Activity mActivity;
    Button btn_save;
    ImageView iv_dialog_cancel;
    RelativeLayout rv_parent;

    RadioButton rb_fullday,rb_halfday,rb_rejected;
    EditText edt_remarks;
    RadioGroup rb_group;

    TextView txt_name,txt_date_time;
    String mDate ,mName ;
    int mAttendancePK ;

    AddDeviationModel model;


    AttendanceDialogInterface mInterface;

    public AttendanceTeamGroupDialog(Context context, int position ,String mName, String mDate ,int mAttendancePK ,AttendanceDialogInterface mInterface) {

        super(context);
        this.mContext = context;
        mActivity = (Activity) mContext;
        this.mDate = mDate;
        this.mName = mName;
        this.mAttendancePK = mAttendancePK;
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
        setContentView(R.layout.dialog_team_group_attendance);

        getData();
        findView();
        setData();
        setListner();

    }

    private void getData() {
    }


    private void setData() {

        txt_date_time.setText(mDate);
        txt_name.setText(mName);

        RadioGroup rb = (RadioGroup) findViewById(R.id.rb_group);
        rb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {

                    case R.id.rb_fullday:
                        if (rb_fullday.isChecked())
                            edt_remarks.setText("Approved Full Day");
                            break;
                    case R.id.rb_halfday:
                        if (rb_halfday.isChecked())
                            edt_remarks.setText("Approved Half Day");
                            break;

                    case R.id.rb_rejected:
                        if (rb_rejected.isChecked())
                            edt_remarks.setText("Rejected");
                        break;

                }
            }

        });
    }

    private void setListner() {
        btn_save.setOnClickListener(this);
        iv_dialog_cancel.setOnClickListener(this);

    }

    private void findView() {
        btn_save = (Button) this.findViewById(R.id.btn_save);
        iv_dialog_cancel = (ImageView) this.findViewById(R.id.iv_dialog_cancel);
       // rv_parent = (RelativeLayout) this.findViewById(R.id.rv_parent);

        txt_date_time = (TextView)this.findViewById(R.id.txt_date_time);
        txt_name = (TextView)this.findViewById(R.id.txt_name);



        rb_fullday = (RadioButton)this.findViewById(R.id.rb_fullday);
        rb_halfday = (RadioButton)this.findViewById(R.id.rb_halfday);
        rb_rejected = (RadioButton)this.findViewById(R.id.rb_rejected);
        edt_remarks = (EditText)this.findViewById(R.id.edt_remarks);
        rb_group = (RadioGroup)this.findViewById(R.id.rb_group);

     /*   Typeface copperplateGothicLight = Typeface.createFromAsset(getAppContext().getAssets(), "GillSans-SemiBold.ttf");
        btn_save.setTypeface(copperplateGothicLight);*/

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btn_save:

                if(edt_remarks.getText().toString().length()>0 ){

                    // [{"AttendancePK":306021,"AppStatus":"3","Remarks":"Rejected","SessionEmployeeFK":"8816"}]

                   // ArrayList<AddDeviationTeamParams> mList = new ArrayList();
                  //  TempParams params2 = new TempParams();
                    AddDeviationTeamParams params = null;

                   // params.setAttendancePK(mAttendancePK);

                    if(edt_remarks.getText().toString().equalsIgnoreCase("Approved Full Day") || rb_fullday.isChecked()){
                       // params.setAppStatus("1");
                         params = new AddDeviationTeamParams(String.valueOf(mAttendancePK),"1",edt_remarks.getText().toString(),String.valueOf(Pref.getValue(mContext,Constant.PREF_SESSION_EMPLOYEE_FK,0)));//8816

                    }
                    if(edt_remarks.getText().toString().equalsIgnoreCase("Approved Half Day") || rb_halfday.isChecked()){
                       // params.setAppStatus("2");
                        params = new AddDeviationTeamParams(String.valueOf(mAttendancePK),"2",edt_remarks.getText().toString(),
                                String.valueOf(Pref.getValue(mContext,Constant.PREF_SESSION_EMPLOYEE_FK,0)));


                    }
                    if(edt_remarks.getText().toString().equalsIgnoreCase("Rejected") || rb_rejected.isChecked()){
                        //params.getmList().get(0).setAppStatus("3");
                        params = new AddDeviationTeamParams(String.valueOf(mAttendancePK),"3",edt_remarks.getText().toString(),
                                String.valueOf(Pref.getValue(mContext,Constant.PREF_SESSION_EMPLOYEE_FK,0)));

                    }

                   // params.setRemarks(edt_remarks.getText().toString());
                   // params.setSessionEmployeeFK("8816");

                    String mToken = Pref.getValue(mContext, Constant.PREF_TOKEN,"");

                   /* mList.add(params);
                    params2.setParam(mList);

                    Gson gson = new Gson();
                    String element = gson.toJson(
                            mList,
                            new TypeToken<ArrayList<AddDeviationTeamParams>>() {}.getType());*/
                  //  String element = gson.toJson(params);

                    //Log.e(TAG, "onClick:>>> "+element);


                    //call common detail api here..
                    new WebServices(mContext/* ActivityContext */, this /* ApiListener */,
                            false /* show progress dialog */,true).
                            callAddDeviationTeamAPI(mToken,params); //from_last = ""
                }

                else {
                    CommonUtils.getInstance().displayToast(mContext,"Please insert remarks!");
                }

                break;

            case R.id.iv_dialog_cancel:
                dismiss();
                break;
        }

    }

    @Override
    public void onApiSuccess(Object mObject) {

        if (mObject instanceof String) {
            //LoginModel model = (LoginModel) mObject;
            // Log.e("", "onApiSuccess: 123 >>  "+mObject.toString() );
            try {
                JSONObject jsonObj = new JSONObject(mObject.toString());

                //parse common detail data
                Log.e("", "onApiSuccess: save daviation json >>  " + jsonObj.toString());
                Gson gson = new Gson();
                model = gson.fromJson(mObject.toString(), AddDeviationModel.class);


                if(model.getTable().get(0).getResult()==1){

                    CommonUtils.getInstance().displayToast(mContext,"Deviation added successfully.");

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
