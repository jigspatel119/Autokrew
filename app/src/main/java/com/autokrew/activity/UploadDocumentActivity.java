package com.autokrew.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.autokrew.R;
import com.autokrew.adapter.UploadDocAdapter;
import com.autokrew.interfaces.RecyclerViewDashBoardListener;
import com.autokrew.models.GetDocumentResponse;
import com.autokrew.models.UploadDocsParams;
import com.autokrew.network.ApiListener;
import com.autokrew.network.WebServices;
import com.autokrew.utils.CommonUtils;
import com.autokrew.utils.Constant;
import com.autokrew.utils.Pref;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Date;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;


public class UploadDocumentActivity extends AppCompatActivity implements View.OnClickListener, ApiListener, RecyclerViewDashBoardListener {

    //TextView btn_signin ;
    // EditText edt_usernanme,edt_password;
    // UserProfileModel model;

    Toolbar toolbar;
    RecyclerView rv_documents;
    UploadDocAdapter mAdapter;
    //  UploadDocsParams uploadDocsParams;
    Dialog dialog;

    File signature;

    int mPos = 0;
    String TAG = "UploadDocument :";
    GetDocumentResponse.TableBean modalGetDoc;

    GetDocumentResponse modalGetDoc2;

    private static final int PICKFILE_RESULT_CODE = 1;
    private static final int MY_PERMISSION_REQUEST_READ_STORAGE_PROFILE_IMAGE = 3;

    private void requestPermissionForReadStorageForProfileImage() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                //  openGalleryForProfilePicture();

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSION_REQUEST_READ_STORAGE_PROFILE_IMAGE);
            }
        } else {
            // openGalleryForProfilePicture();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        requestPermissionForReadStorageForProfileImage();

        getData();
        findView();
        setData();
        setListner();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {

            case MY_PERMISSION_REQUEST_READ_STORAGE_PROFILE_IMAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // openGalleryForProfilePicture();
                    CommonUtils.getInstance().displayToast(this, "permission granted!");
                } else {
                    //code for deny
                    CommonUtils.getInstance().displayToast(this, "permission deny!");
                }
                break;
        }
    }

    private void getData() {

        //api calls

        dialog = new Dialog(this, R.style.progressDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progress_dialog);
        dialog.setCancelable(false);
        //Show progress dialog
        dialog.show();


        Constant.mList.clear();
        UploadDocsParams params = new UploadDocsParams();
        params.setFlag("GridForList");
        params.setSessionUserFk(String.valueOf(Pref.getValue(this, Constant.PREF_SESSION_EMPLOYEE_FK, 0)));
        params.setDocument("");
        params.setDocumentPK("-1");

        //call common detail api here..
        new WebServices(this/* ActivityContext */, this /* ApiListener */,
                false /* show progress dialog */, true).
                callGetDocumentAPI(Pref.getValue(this, Constant.PREF_TOKEN, ""), params); //from_last = ""


        /*Constant.mList.clear();
        for (int i = 1; i <= 5; i++) {
            uploadDocsParams = new UploadDocsParams();
            uploadDocsParams.setId(i);
            // uploadDocsParams.setName(fName);
            Constant.mList.add(uploadDocsParams);
        }*/

    }

    private void setData() {
        Log.e(TAG, "setData: " + Constant.mList.size());

        mAdapter = new UploadDocAdapter(this, null, UploadDocumentActivity.this);
        rv_documents.setAdapter(mAdapter);
        rv_documents.setNestedScrollingEnabled(false);
    }

    private void setListner() {
        //btn_signin.setOnClickListener(this);

    }

    private void findView() {


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Joining Documents");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rv_documents = (RecyclerView) this.findViewById(R.id.rv_documents);
        rv_documents.setLayoutManager(new LinearLayoutManager(this));
        rv_documents.setHasFixedSize(true);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        this.overridePendingTransition(R.anim.activity_open_scale, R.anim.activity_close_translate);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //mAdapter.onActivityResult(requestCode, resultCode, data);
        Log.e("", "onActivityResult >> ");
        switch (requestCode) {
            case PICKFILE_RESULT_CODE:
                if (resultCode == RESULT_OK) {

                    String FilePath = data.getData().getPath();
                    String fName = FilePath.substring(FilePath.lastIndexOf("/") + 1);
                    String s[] = fName.split(".");
                    Log.e("FilePath", "Full >> " + FilePath);
                    Log.e("FilePath", "FilePath >> " + FilePath.substring(FilePath.lastIndexOf("/") + 1));
                    File file = new File(FilePath);
                    long fileSizeInBytes = file.length();
                    // Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
                    long fileSizeInKB = fileSizeInBytes / 1024;
                    // Convert the KB to MegaBytes (1 MB = 1024 KBytes)
                    long fileSizeInMB = fileSizeInKB / 1024;

                    if (fileSizeInMB > 2) {  //2mb validation
                        CommonUtils.getInstance().displayToast(this, "File size should be less than 2 MB.");
                    } else {
                        String extension = "";
                        int i = fName.lastIndexOf('.');
                        if (i > 0) {
                            extension = fName.substring(i + 1);
                        }

                        if (extension.equalsIgnoreCase("pdf") || extension.equalsIgnoreCase("doc") || extension.equalsIgnoreCase("docx")) {
                            // modalGetDoc2 = new GetDocumentResponse;
                            //uploadDocsParams.setId(mPos);
                            modalGetDoc2.getTable().get(mPos).setName(FilePath); //store entire file path for upload
                            // update value here
                            Constant.mList.set(mPos, modalGetDoc2.getTable().get(mPos));
                            mAdapter.notifyItemChanged(mPos);
                        } else {
                            CommonUtils.getInstance().displayToast(this, "Please select word or pdf format file(.doc,.docx,.pdf)");
                        }
                    }



                }
                break;
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
          /*  case R.id.btn_signin:


                break;*/
        }

    }


    @Override
    public void onApiSuccess(Object mObject) {

        if (mObject instanceof String) {
            try {
                if (mObject.toString().equalsIgnoreCase("Data Inserted Successfully.")) {

                    CommonUtils.getInstance().displayToast(this, "Document upload successfully.");
                } else {
                    JSONObject jsonObj = new JSONObject(mObject.toString());
                    Log.e("", "onApiSuccess: GetDocumentResponse json >>  " + jsonObj.toString());
                    Gson gson = new Gson();
                    modalGetDoc2 = gson.fromJson(mObject.toString(), GetDocumentResponse.class);
                    Constant.mList.addAll(modalGetDoc2.getTable());

                    Log.e(TAG, "onApiSuccess: " + Constant.mList.size());
                    mAdapter.notifyDataSetChanged();
                }


            } catch (JSONException e) {
                e.printStackTrace();

                //  Log.e("", "onApiSuccess: GetDocumentResponse json >>  "+mObject.toString());

                if (dialog != null) {
                    if (dialog.isShowing())
                        dialog.dismiss();
                }
            }

            if (dialog != null) {
                if (dialog.isShowing())
                    dialog.dismiss();
            }

        }

    }

    @Override
    public void onApiFailure(Throwable mThrowable) {

        //access token fail
        //call login api

    }


    @Override
    public void recyclerViewListClicked(View v, int position) {
        mPos = position;
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("file/*");
        startActivityForResult(intent, PICKFILE_RESULT_CODE);
    }

    @Override
    public void recyclerViewModuleClicked(View v, int position) {
        CommonUtils.getInstance().displayToast(this, "upload>>>!");

        dialog = new Dialog(this, R.style.progressDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progress_dialog);
        dialog.setCancelable(false);
        //  dialog.show();


        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("Flag", getParam("Insert"));
        map.put("EmployeeFK", getParam(String.valueOf(Pref.getValue(this, Constant.PREF_SESSION_EMPLOYEE_FK, 0))));
        map.put("LoginEployeeFk", getParam(String.valueOf(Pref.getValue(this, Constant.PREF_SESSION_EMPLOYEE_FK, 0))));
        // map.put("ImageAsString", getParam(""));
        //  map.put("FileName", getParam(""));
        map.put("DocumentDetailPK", getParam(String.valueOf(Constant.mList.get(position).getDocumentPK())));
        map.put("EmpDocument", getParam(Constant.mList.get(position).getDocument()));

        String extension = Constant.mList.get(position).getName();
        int i = extension.lastIndexOf('.');
        if (i > 0) {
            extension = extension.substring(i + 1);
        }
        if (extension.equalsIgnoreCase("pdf")) {
            map.put("signature\";filename=\"" + new Date().getTime() + ".pdf\"", getParam(new File(Constant.mList.get(position).getName())));  //for pdf
        } else if (extension.equalsIgnoreCase("doc")) {
            map.put("signature\";filename=\"" + new Date().getTime() + ".doc\"", getParam(new File(Constant.mList.get(position).getName())));  //for pdf

        } else if (extension.equalsIgnoreCase("docx")) {
            map.put("signature\";filename=\"" + new Date().getTime() + ".docx\"", getParam(new File(Constant.mList.get(position).getName())));  //for pdf

        }


        //signature";filename="" + new Date().getTime() + ".pdf"






      /*  UploadDocsParams params = new UploadDocsParams();
        params.setFlag("Insert");
        params.setEmployeeFK(String.valueOf(Pref.getValue(this, Constant.PREF_SESSION_EMPLOYEE_FK, 0)));
        params.setLoginEployeeFk(String.valueOf(Pref.getValue(this, Constant.PREF_SESSION_EMPLOYEE_FK, 0)));
        params.setImageAsString(""); //
        params.setFileName(Constant.mList.get(position).getName());
        params.setDocumentDetailPK(String.valueOf(Constant.mList.get(position).getDocumentPK())); //
        params.setEmpDocument(Constant.mList.get(position).getDocument());
*/
        new WebServices(this/* ActivityContext */, this /* ApiListener */,
                false /* show progress dialog */, true).


                callUploafDocumentAPI(Pref.getValue(this, Constant.PREF_TOKEN, ""), map
                ); //from_last = ""


    }

    public RequestBody getParam(int value) {
        return RequestBody.create(MediaType.parse("text/plain"), value + "");
    }

    public RequestBody getParam(String value) {
        return RequestBody.create(MediaType.parse("text/plain"), value);
    }


    public RequestBody getParam(File value) {

        return RequestBody.create(MediaType.parse(""), value);
    }


}
