package com.autokrew.dialogs;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDialog;
import android.telephony.SmsManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.autokrew.R;
import com.autokrew.utils.CommonUtils;

import java.util.ArrayList;



public class GreetingsDialog extends AppCompatDialog implements View.OnClickListener {

    private Context mContext;
    private Activity mActivity;
    Button btn_call,btn_sms;
    private static final int MY_PERMISSION_CALL_SMS = 5;
    String from_last = "";
    EditText edt_msg;
    ImageView iv_dialog_cancel;




    public GreetingsDialog(Context context, int position) {

        super(context);

        this.mContext = context;
        mActivity = (Activity) mContext;




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
        setContentView(R.layout.dialog_greetings);


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
        btn_call.setOnClickListener(this);
        btn_sms.setOnClickListener(this);
        iv_dialog_cancel.setOnClickListener(this);

    }

    private void findView() {
        btn_call = (Button) this.findViewById(R.id.btn_call);
        btn_sms = (Button) this.findViewById(R.id.btn_sms);
        edt_msg = (EditText)this.findViewById(R.id.edt_msg);

        iv_dialog_cancel = (ImageView)this.findViewById(R.id.iv_dialog_cancel);

        /*Typeface copperplateGothicLight = Typeface.createFromAsset(getAppContext().getAssets(), "GillSans-SemiBold.ttf");
        btn_call.setTypeface(copperplateGothicLight);
        btn_sms.setTypeface(copperplateGothicLight);*/



    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btn_call:

                from_last = "call";
                requestPermissionForCallAndSms(from_last);

                break;


            case R.id.btn_sms:
                from_last = "sms";
                requestPermissionForCallAndSms(from_last);

                break;

            case R.id.iv_dialog_cancel:
               dismiss();

                break;

        }

    }


    private void requestPermissionForCallAndSms(String from_last) {

        if (Build.VERSION.SDK_INT >= 23) {

            if(from_last.equalsIgnoreCase("call")){
                if(ContextCompat.checkSelfPermission(mContext,
                        Manifest.permission.CALL_PHONE)
                        == PackageManager.PERMISSION_GRANTED){

                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "9276505372"));
                    mContext.startActivity(intent);

                    dismiss();
                }

                else {
                    ActivityCompat.requestPermissions(mActivity,
                            new String[]{Manifest.permission.SEND_SMS, Manifest.permission.CALL_PHONE},
                            MY_PERMISSION_CALL_SMS);
                }
            }
             if(from_last.equalsIgnoreCase("sms")){

                if (ContextCompat.checkSelfPermission(mContext,
                        Manifest.permission.SEND_SMS)
                        == PackageManager.PERMISSION_GRANTED) {

                    if(edt_msg.getText().toString().length()>0){
                        //send sms here
                        sendMessage("Hi");
                        dismiss();
                    }
                    else{

                        CommonUtils.getInstance().displayToast(mContext,"Write message!");
                    }
                }

                else {
                    ActivityCompat.requestPermissions(mActivity,
                            new String[]{Manifest.permission.SEND_SMS, Manifest.permission.CALL_PHONE},
                            MY_PERMISSION_CALL_SMS);
                }
            }



        } else {
            if(from_last.equalsIgnoreCase("call")){
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "9276505372"));
                mContext.startActivity(intent);
                dismiss();
            }
            else if(from_last.equalsIgnoreCase("sms")){

                if(edt_msg.getText().toString().length()>0){
                    //send sms here
                    sendMessage("Hi");
                    dismiss();
                }
                else{
                    CommonUtils.getInstance().displayToast(mContext,"Write message!");
                }
            }
        }
    }

    private void sendMessage(String message){

        String number = "9276505372";


        try {
            SmsManager smsManager = SmsManager.getDefault();
            //smsManager.sendTextMessage(number, null, message, null, null);
            ArrayList<String> messageParts = smsManager.divideMessage(message);

            smsManager.sendMultipartTextMessage(number, null, messageParts, null, null);

            CommonUtils.getInstance().displayToast(mContext,"Message sent!");


        } catch (Exception ex) {
            Toast.makeText(mContext,ex.getMessage(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

}
