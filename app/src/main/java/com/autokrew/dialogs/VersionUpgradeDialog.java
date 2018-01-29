package com.autokrew.dialogs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.autokrew.R;
import com.autokrew.interfaces.AttendanceDialogInterface;
import com.autokrew.models.ManageLeaveDetailModel;


public class VersionUpgradeDialog extends AppCompatDialog implements View.OnClickListener {

    private Context mContext;
    Button btn_ok,btn_cancel;



    public VersionUpgradeDialog(Context context) {

        super(context);
        this.mContext = context;
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
        setContentView(R.layout.dialog_version_upgrade);


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



    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btn_ok:

                //open playstore
               Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
                //Copy App URL from Google Play Store.
                intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.youtube"));
                mContext.startActivity(intent);

              break;
            case R.id.btn_cancel:
                dismiss();
                break;
        }

    }


}
