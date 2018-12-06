package com.autokrew.fragments;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.autokrew.R;
import com.autokrew.activity.MainActivity;
import com.autokrew.models.CommonDetailModel;
import com.autokrew.models.CommonDetailModelParams;
import com.autokrew.models.MyprofileModel;
import com.autokrew.models.MyprofileParams;
import com.autokrew.models.UpdateUserProfileParams;
import com.autokrew.network.ApiListener;
import com.autokrew.network.WebServices;
import com.autokrew.utils.BlurTransformation;
import com.autokrew.utils.CommonUtils;
import com.autokrew.utils.Constant;
import com.autokrew.utils.Pref;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment implements ApiListener, View.OnClickListener {

    String mToken;
    MyprofileModel model;

    private Toolbar toolbar;
    private MainActivity appCompatActivity;
    String form_last = "";
    CommonDetailModel modelCommon;

    String _default_please_select = "Please select";



    TextView txt_name, txt_employee_code, txt_nationality, txt_update_profile;

    EditText txt_gender, txt_dob, txt_address, txt_mobile, txt_email;

    //EditText txt_marital_status ,txt_height ,txt_weight ,txt_blood_group;

    Spinner edt_marital_status ,edt_height ,edt_weight ,edt_blood_group;
    ImageView mAppCompactImageView ;
    CircleImageView mIvCenter;

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        appCompatActivity = (MainActivity) activity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile2, container, false);
       /* RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new MyItemRecyclerViewAdapter(DummyContent.ITEMS));*/
        findView(view);
        getData();

        //for recycler view


        setListner();

        return view;
    }

    private void getData() {
        //api calls for profile data...

        mToken = Pref.getValue(getActivity(), Constant.PREF_TOKEN, "");

        getCommonDetail();


    }

    private void getCommonDetail() {
        CommonDetailModelParams params = new CommonDetailModelParams();
        params.setFlag("common");
        params.setTranTypes(-1);
        form_last = "common";

        //call common detail api here..
        new WebServices(getActivity()/* ActivityContext */, this /* ApiListener */,
                true /* show progress dialog */, true).
                callGetCommonDetailAPI(mToken, params); //from_last = ""
    }


    private void findView(View view) {

       /* toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        ((CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar)).setTitle("My Profile");
        setupToolbar();*/


        txt_name = (TextView) view.findViewById(R.id.txt_name);
        txt_employee_code = (TextView) view.findViewById(R.id.txt_employee_code);
        txt_nationality = (TextView) view.findViewById(R.id.txt_nationality);
        txt_update_profile = (TextView) view.findViewById(R.id.txt_update_profile);

        txt_gender = (EditText) view.findViewById(R.id.txt_gender);
        txt_email = (EditText) view.findViewById(R.id.txt_email);
        txt_dob = (EditText) view.findViewById(R.id.txt_dob);
        txt_address = (EditText) view.findViewById(R.id.txt_address);
        txt_mobile = (EditText) view.findViewById(R.id.txt_mobile);
       // txt_marital_status = (EditText) view.findViewById(R.id.txt_marital_status);
        //txt_height = (EditText) view.findViewById(R.id.txt_height);
       // txt_weight = (EditText) view.findViewById(R.id.txt_weight);
        //txt_blood_group = (EditText) view.findViewById(R.id.txt_blood_group);


        //dropdown/spiner
        edt_marital_status = (Spinner) view.findViewById(R.id.edt_marital_status);
        edt_height =  (Spinner) view.findViewById(R.id.edt_height);
        edt_weight =  (Spinner) view.findViewById(R.id.edt_weight);
        edt_blood_group =  (Spinner) view.findViewById(R.id.edt_blood_group);

        mAppCompactImageView = view.findViewById(R.id.mAppCompactImageView);

        mIvCenter = (CircleImageView)view.findViewById(R.id.mIvCenter);




        Glide.with(this)
                .load(Pref.getValue(getActivity(), "profile_pic_server", ""))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.drawable.profile_image)
                .transform(new BlurTransformation(getActivity()))
                .error(R.drawable.profile_image)
                .into(mAppCompactImageView);

        Glide.with(this)
                .load(Pref.getValue(getActivity(), "profile_pic_server", ""))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.drawable.profile_image)
                .error(R.drawable.profile_image)
                .into(mIvCenter);




    }


    private void setupToolbar() {
        toolbar.setTitle("");
        appCompatActivity.setSupportActionBar(toolbar);
    }

    private void setData() {
        txt_name.setText(model.getTable().get(0).getFirstName() + " " + model.getTable().get(0).getLastName());
        if (model.getTable().get(0).getGenderPK() == 2) {
            txt_gender.setText("Female");
        } else {
            txt_gender.setText("Male");
        }
        txt_dob.setText(model.getTable().get(0).getDateOfBirth());


        if (model.getTable().get(0).getPersonalEmail()!= null ) {

            txt_email.setText(model.getTable().get(0).getPersonalEmail());
        } else {
            txt_email.setText("-");
        }

        if (model.getTable().get(0).getPerAddress().equalsIgnoreCase("")) {
            txt_address.setText("-");
        } else {
            txt_address.setText(model.getTable().get(0).getPerAddress());
        }


        if (model.getTable().get(0).getMobileModel()!=null) {

            txt_mobile.setText(model.getTable().get(0).getMobileModel());
        } else {
            txt_mobile.setText("-");
        }

        //marital status //-1 for pk
        if (model.getTable().get(0).getMaritalStatus()!=null) {
            ArrayList<String> mMaterialStatus = new ArrayList<>();
            mMaterialStatus.clear();
            mMaterialStatus.add("-");
            for (int i = 0; i <modelCommon.getTable6().size(); i++) {
                mMaterialStatus.add(modelCommon.getTable6().get(i).getMaritalStatus());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.selected_item, mMaterialStatus);
            edt_marital_status.setAdapter(adapter);
            edt_marital_status.setSelection(model.getTable().get(0).getMaritalStatusPK()); // 1,2,3,4 not 0

            if(model.getTable().get(0).getMaritalStatusPK()==-1){
                edt_marital_status.setSelection(0);
            }
        } else {
           // txt_marital_status.setText(model.getTable().get(0).getMaritalStatus());
            //txt_marital_status.setText("-");

        }

        //for height //-1 for pk
        if (model.getTable().get(0).getHeight()!=null) {
            ArrayList<String> mHeight = new ArrayList<>();
            mHeight.clear();
            mHeight.add("-");
            for (int i = 0; i <modelCommon.getTable3().size(); i++) {
                mHeight.add(modelCommon.getTable3().get(i).getHeight());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.selected_item, mHeight);
            edt_height.setAdapter(adapter);
            edt_height.setSelection(model.getTable().get(0).getHeightPK()-1); //2,3,4 ...

            if(model.getTable().get(0).getHeightPK()==-1){
                edt_height.setSelection(0);
            }
        } else {
          //  txt_height.setText(model.getTable().get(0).getHeight());
        }


        if (model.getTable().get(0).getWeight()!= null) {

            ArrayList<String> mWight = new ArrayList<>();
            mWight.clear();
            mWight.add("-");
            for (int i = 0; i <modelCommon.getTable4().size(); i++) {
                mWight.add(modelCommon.getTable4().get(i).getWeight());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.selected_item, mWight);
            edt_weight.setAdapter(adapter);
            edt_weight.setSelection(model.getTable().get(0).getWeightPK());

            if(model.getTable().get(0).getWeightPK()==-1){
                edt_weight.setSelection(0);
            }
           // txt_weight.setText(model.getTable().get(0).getWeight());
        } else {
            //txt_weight.setText("-");
        }


        if (model.getTable().get(0).getBloodGroup()!= null) {
            //txt_blood_group.setText("-");
            ArrayList<String> mBloodGroup = new ArrayList<>();
            mBloodGroup.clear();
            mBloodGroup.add("-");
            for (int i = 0; i <modelCommon.getTable5().size(); i++) {
                mBloodGroup.add(modelCommon.getTable5().get(i).getBloodGroup());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.selected_item, mBloodGroup);
            edt_blood_group.setAdapter(adapter);
            edt_blood_group.setSelection(model.getTable().get(0).getBloodGroupPK()); //1,2,3,4

            if(model.getTable().get(0).getBloodGroupPK()==-1){
                edt_blood_group.setSelection(0);
            }
        } else {
           // txt_blood_group.setText(model.getTable().get(0).getBloodGroup());
        }


        if (model.getTable().get(0).getNationality().equalsIgnoreCase("")) {
            txt_nationality.setText("-");
        } else {
            txt_nationality.setText(model.getTable().get(0).getNationality());
        }


        txt_employee_code.setText(model.getTable().get(0).getEmployeeCode());


    }


    private void setListner() {
        txt_update_profile.setOnClickListener(this);
    }


    @Override
    public void onApiSuccess(Object mObject) {
        try {

            if (form_last.equalsIgnoreCase("common")) {
                Log.e("", "onApiSuccess: common json >>  " + mObject.toString());
                Gson gson = new Gson();
                modelCommon = gson.fromJson(mObject.toString(), CommonDetailModel.class);

                getMyprofile();

            } else if (form_last.equalsIgnoreCase("profile")) {
                //parse common detail data
                Log.e("", "onApiSuccess: my profile json >>  " + mObject.toString());
                Gson gson = new Gson();
                model = gson.fromJson(mObject.toString(), MyprofileModel.class);
                //set data
                setData();
            }

            else if (form_last.equalsIgnoreCase("update_profile")) {
                //parse common detail data
                Log.e("", "onApiSuccess: update_profile json >>  " + mObject.toString());
                CommonUtils.getInstance().displayToast(getActivity(),""+mObject.toString());

            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void getMyprofile() {

        MyprofileParams params = new MyprofileParams();
        params.setEmployeeFK(Pref.getValue(getActivity(), Constant.PREF_SESSION_EMPLOYEE_FK, 0));
        params.setFlag("Basic");
        form_last = "profile";

        new WebServices(getActivity(), ProfileFragment.this,
                true, true).
                callMyprofileAPI(mToken, params);
    }

    @Override
    public void onApiFailure(Throwable mThrowable) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_update_profile:

                UpdateUserProfileParams params = new UpdateUserProfileParams();
                params.setFlag("MobileUpdate");
                params.setEmployeeFK(String.valueOf(Pref.getValue(getActivity(), Constant.PREF_SESSION_EMPLOYEE_FK, 0)));
                params.setLoginEployeeFk(Pref.getValue(getActivity(), Constant.PREF_SESSION_EMPLOYEE_FK, 0));

                params.setFname("");
                params.setMiddelName("");
                params.setLastName("");


                params.setBirthDate("01/01/1990");

                params.setResidencePhone("");


                params.setPhotoURL("");
                params.setNational("-1");
                params.setReligion("-1");
                params.setCurAddress("");
                params.setCurState("-1");
                params.setCurCity("-1");
                params.setCurPin("0");

                params.setIsAddSame(0);
                params.setPerAddress("");
                params.setPerState("-1");
                params.setPerCity("-1");
                params.setPerPin("0");

                if(txt_gender.getText().toString().equalsIgnoreCase("Male")){
                    params.setGender(String.valueOf("1")); //??
                }
                else if(txt_gender.getText().toString().equalsIgnoreCase("Female")){
                    params.setGender(String.valueOf("2")); //??
                }


               if(txt_email.getText().toString()!=null){
                   params.setEmail(txt_email.getText().toString());
               }
               else{
                   params.setEmail("-");
               }
               if(txt_mobile.getText().toString()!=null){
                   params.setMobile(txt_mobile.getText().toString());
               }
               else{
                   params.setMobile("-");
               }

                if(edt_marital_status.getSelectedItemPosition()>0){
                    params.setMaritalStatus(modelCommon.getTable6().get(edt_marital_status.getSelectedItemPosition()-1).getMaritalStatusPK());
                }
                else{
                    params.setMaritalStatus("1");
                }


                if(edt_height.getSelectedItemPosition()>0){
                    params.setHeight(modelCommon.getTable3().get(edt_height.getSelectedItemPosition()-1).getHeightPK());
                }
                else{
                    params.setHeight("-1");
                }

                if(edt_weight.getSelectedItemPosition()>0){
                    params.setWeight(modelCommon.getTable4().get(edt_weight.getSelectedItemPosition()-1).getWeightPK());
                }
                else{
                    params.setWeight("-1");
                }

                if(edt_blood_group.getSelectedItemPosition()>0){
                    params.setBloodGroup(modelCommon.getTable5().get(edt_blood_group.getSelectedItemPosition()-1).getBloodGroupPK());
                }else{
                    params.setBloodGroup("-1");
                }




                params.setIsIllness("0");
                params.setIllnessText("");
                params.setIllessFile("");
                params.setOtherEmployeeCode("");
                params.setRemark("");

                form_last = "update_profile";

                //call update detail api here..
                new WebServices(getActivity(), this ,
                        true, true).
                        callUpdateMyProfileAPI(mToken, params);


                break;
        }

    }
}
