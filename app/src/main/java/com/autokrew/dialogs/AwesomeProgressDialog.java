package com.autokrew.dialogs;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.autokrew.R;
import com.autokrew.interfaces.DialogListener;
import com.autokrew.network.WebServices;
import com.autokrew.utils.AwesomeDialogBuilder;
import com.autokrew.utils.CommonUtils;

/**
 * Created by blennersilva on 23/08/17.
 */

public class AwesomeProgressDialog extends AwesomeDialogBuilder<AwesomeProgressDialog>   {

    private ProgressBar progressBar;
    private RelativeLayout dialogBody;
    Button btn_ok;
    EditText edt_otp;
    Context ctx;
    DialogListener listner ;
    ImageView dialog_icon;

    public AwesomeProgressDialog(Context context , DialogListener listner) {
        super(context);

        ctx =context;
        setColoredCircle(R.color.color_primary);
        setProgressBarColor(R.color.white);
        this.listner = listner;
    }

    {
        progressBar = findView(R.id.dialog_progress_bar);
        dialogBody = findView(R.id.dialog_body);
        btn_ok = findView(R.id.btn_ok);
        edt_otp = findView(R.id.edt_otp);
        dialog_icon = findView(R.id.dialog_icon);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //hide key board ..

                String OTP = edt_otp.getEditableText().toString().trim();

                if(OTP.length()>0){
                    //listener
                    dialog_icon.setVisibility(View.GONE);
                    progressBar.setVisibility(View.VISIBLE);
                    listner.dialogClick(OTP);
                }
                else{
                    CommonUtils.getInstance().displayToast(ctx,"Please Insert Company Code!");
                }
            }
        });
    }

    public AwesomeProgressDialog setDialogBodyBackgroundColor(int color){
        if (dialogBody != null) {
            dialogBody.getBackground().setColorFilter(ContextCompat.getColor(getContext(), color), PorterDuff.Mode.SRC_IN);
        }

        return this;
    }

    public AwesomeProgressDialog setProgressBarColor(int color) {
        progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getContext(), R.color.white), PorterDuff.Mode.SRC_IN);
        return this;
    }




    @Override
    protected int getLayout() {
        return R.layout.dialog_progress;
    }


}
