package com.autokrew.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.autokrew.R;
import com.autokrew.models.AttendanceModel;
import com.autokrew.models.LoginModelParams;
import com.autokrew.models.LoginModel;
import com.autokrew.models.UserProfileModel;
import com.autokrew.models.UserProfileParams;
import com.autokrew.network.ApiListener;
import com.autokrew.network.WebServices;
import com.autokrew.utils.CommonUtils;
import com.autokrew.utils.Constant;
import com.autokrew.utils.Pref;
import com.google.gson.Gson;

public class SigninActivity extends AppCompatActivity implements  View.OnClickListener ,ApiListener{

    TextView btn_signin ;
    EditText edt_usernanme,edt_password;
   // UserProfileModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

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
        btn_signin.setOnClickListener(this);

    }

    private void findView() {
        btn_signin = (TextView) this.findViewById(R.id.btn_signin);
        edt_usernanme = (EditText) this.findViewById(R.id.edt_usernanme);
        edt_password = (EditText)this.findViewById(R.id.edt_password);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btn_signin:

                if(edt_usernanme.getText().toString().length()>0 && edt_password.getText().toString().length()>0){
                    apiCalls();
                }
                 else{
                    CommonUtils.getInstance().displayToast(this,"Please enter details!");
                }

                break;
        }

    }

    private void apiCalls() {

        if (CommonUtils.getInstance().isNetworkAvailable(this)){
            // callAPI(getActivity());
            //  if (validate()) {
            LoginModelParams params = new LoginModelParams();
            params.setUsername(edt_usernanme.getText().toString());
            params.setPassword(edt_password.getText().toString());
          //  params.setMobileURL("http://192.168.1.14:81/");
            params.setMobileURL(Pref.getValue(this,Constant.PREF_MOBILE_URL,""));

            new WebServices(this/* ActivityContext */, this /* ApiListener */, true /* show progress dialog */,
                    true/*for new retrofitclient*/ ).
                    callLoginAPI(params);
            //  }
        }
        else{
            CommonUtils.getInstance().displayToast(this, Constant.INTERNET_FAILURE);

        }
    }


    @Override
    public void onApiSuccess(Object mObject) {

        if (mObject instanceof LoginModel) {
            LoginModel model = (LoginModel) mObject;
            Log.e("", "onApiSuccess: token >>  "+model.getToken() );

            if(model.getToken()!=null){
                //add bearer_ (space before token....)
                Pref.setValue(this,Constant.PREF_TOKEN,"bearer "+model.getToken());
                Pref.setValue(this,Constant.PREF_SESSION_EMPLOYEE_FK,model.getEmployeeFK());

                Pref.setValue(this,Constant.PREF_ROLE_FK,model.getRoleFK());
                //api calls for get user profile

                getUserProfile(model.getEmployeeFK());
            }
            else{
                //
                CommonUtils.getInstance().displayToast(this, model.getResponse());

            }


        }
        else if(mObject instanceof String){
            Log.e("", "onApiSuccess: user data  >>  "+mObject.toString());
            Pref.setValue(this,Constant.PREF_USER_DATA,mObject.toString());

            Gson gson = new Gson();
            UserProfileModel userProfileModel = gson.fromJson(mObject.toString(), UserProfileModel.class);
            String file_path = Pref.getValue(this,Constant.PREF_MOBILE_URL,"")
                    +"Upload/EmployeDocument/"+userProfileModel.getTable().get(0).getImageUrl();
            //save data in to pref
            Pref.setValue(this,"profile_pic_server" ,file_path);


            CommonUtils.getInstance().startActivityWithoutStack(this, MainActivity.class);
        }
    }

    private void getUserProfile(int EmployeeFK) {
        UserProfileParams params = new UserProfileParams();
        params.setEmployeeFK(EmployeeFK);
        params.setFlag("Basic");

        String mToken = Pref.getValue(this,Constant.PREF_TOKEN,"");
        new WebServices(this/* ActivityContext */, this /* ApiListener */, false /* show progress dialog */,
                true/*for new retrofitclient*/ ).
                callUserProfileAPI(mToken,params);
    }

    @Override
    public void onApiFailure(Throwable mThrowable) {

    }
}
