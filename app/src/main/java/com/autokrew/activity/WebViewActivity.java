package com.autokrew.activity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.autokrew.R;
import com.autokrew.models.DashbordModel;
import com.autokrew.models.PreviewAnnouncementModel;
import com.autokrew.models.PreviewAnnouncementParam;
import com.autokrew.network.ApiListener;
import com.autokrew.network.WebServices;
import com.autokrew.utils.Constant;
import com.autokrew.utils.Pref;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class WebViewActivity extends AppCompatActivity implements ApiListener {
    private ProgressDialog dialog;
    //String html = "<p><strong>Apollo CV 2017 - CV Dealer of the Year - Cargo&nbsp;Motors&nbsp;</strong><strong>Pvt. Ltd.</strong></p>\n\n<p>&nbsp;</p>\n\n<p><strong><img alt=\"\" src=\"http://cargogroup.co.in/Upload/EmployeDocument/8700/e3e85dd5-5a0c-4a35-926c-e740483dd23e_CVAPOLLO2017.jpg\" style=\"height:480px; width:350px\" /></strong></p>\n\n<p>&nbsp;</p>\n\n<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>\n\n<p>&nbsp;</p>\n\n<p>&nbsp;</p>\n\n<p>&nbsp;</p>\n";

    String html;
    String TAG = "WebViewActivity";
    String flag = "";
    WebView webView;
    TextView txt_title ;
    final String mimeType = "text/html";
    final String encoding = "UTF-8";
    String title;
    int mNewsPk;
    String mToken;
    Toolbar toolbar;

    PreviewAnnouncementModel model;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        webView = (WebView) findViewById(R.id.webview);
        mToken = Pref.getValue(this, Constant.PREF_TOKEN, "");

        txt_title = (TextView)this.findViewById(R.id.txt_title);

        Bundle bundle = getIntent().getExtras();

        title = bundle.getString("title");
        mNewsPk = bundle.getInt("mNewsPk");

        txt_title.setText(title);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("News / Announcement Detail");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webView.setWebViewClient(new myWebClient());
        webView.getSettings().setJavaScriptEnabled(true);

        //{"flag":"PreviewDoc","LoginUserFk":"8816","Title":"","StartDate":"","EndDate":"","NewsPK":1}
        //api calls for preview announcement

        PreviewAnnouncementParam params = new PreviewAnnouncementParam();
        params.setFlag("PreviewDoc");
        params.setLoginUserFk(String.valueOf(Pref.getValue(this, Constant.PREF_SESSION_EMPLOYEE_FK, 0)));
        params.setTitle(title);
        params.setStartDate("");
        params.setEndDate("");
        params.setNewsPK(mNewsPk);

        new WebServices(this/* ActivityContext */, WebViewActivity.this /* ApiListener */, true /* show progress dialog */,
                true/*for new retrofitclient*/).
                callAnnouncementPreviewAPI(mToken, params);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {

            finish();
            this.overridePendingTransition(R.anim.activity_open_scale, R.anim.activity_close_translate);

        }
        return super.onOptionsItemSelected(menuItem);
    }


    @Override
    public void onBackPressed() {

        super.onBackPressed();
        finish();
        this.overridePendingTransition(R.anim.activity_open_scale, R.anim.activity_close_translate);

    }

    @Override
    public void onApiSuccess(Object mObject) {
        if (mObject instanceof String) {

            try {
                JSONObject jsonObj = new JSONObject(mObject.toString());
                //parse original data
                Log.e("", "onApiSuccess: preview announcement json >>  " + jsonObj.toString());

                Gson gson = new Gson();
                model = gson.fromJson(mObject.toString(), PreviewAnnouncementModel.class);
                html = model.getTable().get(0).getDescription();
                webView.loadDataWithBaseURL("", html, mimeType, encoding, "");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onApiFailure(Throwable mThrowable) {

    }


    public class myWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
          //  progressBar.setVisibility(View.VISIBLE);
            //view.loadUrl(url);
            return true;

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);

          //  progressBar.setVisibility(View.GONE);
        }
    }
}