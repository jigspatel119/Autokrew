package com.autokrew.dialogs;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.autokrew.R;
import com.autokrew.activity.AttendanceDeviationActivity;
import com.autokrew.activity.GroupLeaveActivity;
import com.autokrew.activity.WebViewActivity;
import com.autokrew.adapter.AttendanceDetailAdapter;
import com.autokrew.adapter.BirthdayAdapter;
import com.autokrew.adapter.LeaveDetailAdapter;
import com.autokrew.fragments.DashboardFragment;
import com.autokrew.listner.RecyclerItemClickListener;
import com.autokrew.models.AttendanceDetailModel;
import com.autokrew.models.LeaveDetailModel;
import com.autokrew.utils.CommonUtils;
import com.autokrew.utils.Pref;

import java.util.ArrayList;

import static com.autokrew.utils.AppController.getAppContext;


public class AttendanceDetailDialog extends AppCompatDialog implements View.OnClickListener {

    private static final String TAG = AttendanceDetailDialog.class.getSimpleName();
    private Context mContext;
    private Activity mActivity;

    ImageView iv_dialog_cancel;


    AttendanceDetailModel model;
    LeaveDetailModel model2;
    RecyclerView rv_detail;
    AttendanceDetailAdapter mAttendanceDetailAdapter;
    LeaveDetailAdapter mLeaveDetailAdapter;

    TextView tv_pending_attendance, title;
    String dialog_title, sub_title;


    public AttendanceDetailDialog(Context context, AttendanceDetailModel model, LeaveDetailModel model2, String dialog_title, String sub_title) {

        super(context);

        this.mContext = context;
        mActivity = (Activity) mContext;
        this.model = model;
        this.dialog_title = dialog_title;
        this.sub_title = sub_title;
        this.model2 = model2;

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
        setContentView(R.layout.dialog_detail);


        getData();
        findView();
        setData();
        setListner();

    }

    private void getData() {
    }


    private void setData() {

        if (sub_title.equalsIgnoreCase("Pending Leave")) {
            mLeaveDetailAdapter = new LeaveDetailAdapter(mContext, model2);
            rv_detail.setAdapter(mLeaveDetailAdapter);
        } else {
            mAttendanceDetailAdapter = new AttendanceDetailAdapter(mContext, model);
            rv_detail.setAdapter(mAttendanceDetailAdapter);
        }

        rv_detail.setNestedScrollingEnabled(false); //for smooth nested scroll
        title.setText(dialog_title);
        tv_pending_attendance.setText(sub_title);


    }

    private void setListner() {

        iv_dialog_cancel.setOnClickListener(this);

    }

    private void findView() {

        title = (TextView) this.findViewById(R.id.title);
        tv_pending_attendance = (TextView) this.findViewById(R.id.tv_pending_attendance);
        iv_dialog_cancel = (ImageView) this.findViewById(R.id.iv_dialog_cancel);
        rv_detail = (RecyclerView) this.findViewById(R.id.rv_detail);
        rv_detail.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rv_detail.setHasFixedSize(true);

        rv_detail.addOnItemTouchListener(
                new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // do whatever

                        if (sub_title.equalsIgnoreCase("Pending Leave")) {
                            // CommonUtils.getInstance().displayToast(mContext,""+model2.getTable().get(position).get_$PendingLeave251());

                            Pref.setValue(mContext, "LeaveStatusFK", "1"); //for pending
                            Pref.setValue(mContext, "mYear", String.valueOf(model2.getTable().get(position).getPenYear()));
                            Pref.setValue(mContext, "mMonthPK", String.valueOf(model2.getTable().get(position).getPenMonth()));
                            Pref.setValue(mContext, "mEmployeePK", "-1");
                            Pref.setValue(mContext, "call_team_or_group", "team");

                            dismiss();
                            CommonUtils.getInstance().startActivity(mActivity, GroupLeaveActivity.class);
                            mActivity.overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_scale);

                        } else {
                            // CommonUtils.getInstance().displayToast(mContext,""+model.getTable().get(position).get_$PendingAttendance320());

                            Pref.setValue(mContext, "call_team_or_group", "team");
                            Pref.setValue(mContext, "mApprovalStatus", "-1"); //for pending
                            Pref.setValue(mContext, "mYear", String.valueOf(model.getTable().get(position).getPenYear()));
                            Pref.setValue(mContext, "mMonthPK", String.valueOf(model.getTable().get(position).getPenMonth()));
                            Pref.setValue(mContext, "mEmployeePK", "-1"); //for please select
                            dismiss();
                            CommonUtils.getInstance().startActivity(mActivity, AttendanceDeviationActivity.class);
                            mActivity.overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_scale);

                        }



                       /* String title = model.getTable12().get(position).getTitle();
                        int mNewsPk = model.getTable12().get(position).getNewsPK();
                        //Create the bundle
                        Bundle bundle = new Bundle();
                        bundle.putString("title", title);
                        bundle.putInt("mNewsPk",mNewsPk);*/

                        //CommonUtils.getInstance().startActivity(mContext, WebViewActivity.class,bundle);
                        // getActivity().overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);


                    }
                })
        );

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            /*case R.id.btn_call:

                from_last = "call";
                requestPermissionForCallAndSms(from_last);

                break;


            case R.id.btn_sms:
                from_last = "sms";
                requestPermissionForCallAndSms(from_last);

                break;*/

            case R.id.iv_dialog_cancel:
                dismiss();

                break;

        }

    }


}
