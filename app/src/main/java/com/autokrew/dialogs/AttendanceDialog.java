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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.autokrew.R;
import com.autokrew.interfaces.AttendanceDialogInterface;
import com.autokrew.models.AddDeviationModel;
import com.autokrew.models.AddDeviationParams;
import com.autokrew.models.AttendanceModelParams;
import com.autokrew.models.CommonDetailModel;
import com.autokrew.network.ApiListener;
import com.autokrew.network.WebServices;
import com.autokrew.utils.CommonUtils;
import com.autokrew.utils.Constant;
import com.autokrew.utils.Pref;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.autokrew.utils.AppController.getAppContext;


public class AttendanceDialog extends AppCompatDialog implements View.OnClickListener ,ApiListener {

    private static final String TAG = AttendanceDialog.class.getSimpleName();
    private Context mContext;
    private Activity mActivity;
    Button btn_save;
    ImageView iv_dialog_cancel;
    RelativeLayout rv_parent;
    EditText edt_remarks;

    TextView txt_name ,txt_reporting_person,txt_date_time,txt_status;

    AddDeviationModel model;
  //  String list[]={"Jan","Feb","March","April"};
    CommonDetailModel modelCommon;
    AddDeviationModel modelDeviationAdd;
    int mAddendancePK;

    String _year;
    int _month;
    Spinner mySpinner;

    AttendanceDialogInterface mInterface;

    public AttendanceDialog(Context context, int position , AddDeviationModel model , CommonDetailModel modelCommon
            ,int mAddendancePK ,AttendanceDialogInterface mInterface ,String _year ,int _month) {

        super(context);
        this.mContext = context;
        mActivity = (Activity) mContext;
        this.model = model;
        this.modelCommon = modelCommon;
        this.mAddendancePK = mAddendancePK;

        this.mInterface = mInterface;
        this._year = _year;
        this._month = _month;
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
        setContentView(R.layout.dialog_attendance_deviation);

        getData();
        findView();
        setData();
        setListner();

    }

    private void getData() {
    }


    private void setData() {

        txt_name.setText(model.getTable().get(0).getName());
        txt_reporting_person.setText(model.getTable().get(0).getReportingPerson());
        txt_date_time.setText(model.getTable().get(0).getAttDate());
        txt_status.setText(model.getTable().get(0).getStatus());

        spinnermeth();

    }

    private void spinnermeth() {

        ArrayList<String> stringArrayList = new ArrayList<String>();
        stringArrayList.add("Please Select");

        for (int i = 0; i <modelCommon.getTable21().size() ; i++) {

            /*if(i==0){
                stringArrayList.add("Please select");
            }
            else{

            }*/
            stringArrayList.add(modelCommon.getTable21().get(i).getDeviation());

        }
        String [] list = stringArrayList.toArray(new String[stringArrayList.size()]);

        mySpinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,R.layout.selected_item, list);
        mySpinner.setAdapter(adapter);
    }

    private void setListner() {
        btn_save.setOnClickListener(this);
        iv_dialog_cancel.setOnClickListener(this);
    }

    private void findView() {
        btn_save = (Button) this.findViewById(R.id.btn_save);
        iv_dialog_cancel = (ImageView) this.findViewById(R.id.iv_dialog_cancel);
        rv_parent = (RelativeLayout) this.findViewById(R.id.rv_parent);

        txt_name = (TextView)this.findViewById(R.id.txt_name);
        txt_reporting_person = (TextView)this.findViewById(R.id.txt_reporting_person);
        txt_date_time = (TextView)this.findViewById(R.id.txt_date_time);
        txt_status = (TextView)this.findViewById(R.id.txt_status);

        edt_remarks = (EditText)this.findViewById(R.id.edt_remarks);


        Typeface copperplateGothicLight = Typeface.createFromAsset(getAppContext().getAssets(), "GillSans-SemiBold.ttf");
        btn_save.setTypeface(copperplateGothicLight);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btn_save:
                //api calls for save deviation..
                //{"AttendancePK":306011,"DeviationFK":10,"EmpRemarks":"test","Month":10,"Year":"2017","Flag":"Update"}

               /* for (int i = 0; i <modelCommon.getTable21().size() ; i++) {
                    Log.e(TAG, ">>>>"+modelCommon.getTable21().get(i).getDeviationPK());
                }*/

                CommonUtils.getInstance().displayToast(mContext,modelCommon.getTable21().get(mySpinner.getSelectedItemPosition()).getDeviationPK());
               if(mySpinner.getSelectedItem().toString()!="Please Select"){
                   if(edt_remarks.getText().toString().length()>0 ){
                       AddDeviationParams params = new AddDeviationParams();
                       params.setAttendancePK(mAddendancePK);
                       params.setDeviationFK(Integer.parseInt(modelCommon.getTable21().get(mySpinner.getSelectedItemPosition()).getDeviationPK()));
                       params.setEmpRemarks(edt_remarks.getText().toString());
                       params.setMonth(_month); //monthFK
                       params.setYear(_year); //year name in string
                       params.setFlag("Update");

                       String mToken = Pref.getValue(mContext, Constant.PREF_TOKEN,"");
                       //call common detail api here..
                       new WebServices(mContext, this ,
                               false ,true).
                               callAddDeviationAPI(mToken,params);

                       CommonUtils.getInstance().displayToast(mContext,"yup");

                   }

                   else {
                       CommonUtils.getInstance().displayToast(mContext,"Please insert remarks!");
                   }
               }
               else {
                   CommonUtils.getInstance().displayToast(mContext,"Please Deviation remarks!");
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
                Log.e("", "onApiSuccess: save deviation json >>  " + jsonObj.toString());
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
