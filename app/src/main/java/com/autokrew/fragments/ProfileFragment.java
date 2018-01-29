package com.autokrew.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.autokrew.R;
import com.autokrew.activity.MainActivity;
import com.autokrew.models.MyprofileModel;
import com.autokrew.models.MyprofileParams;
import com.autokrew.network.ApiListener;
import com.autokrew.network.WebServices;
import com.autokrew.utils.Constant;
import com.autokrew.utils.Pref;
import com.google.gson.Gson;


public class ProfileFragment extends Fragment implements ApiListener {

    String mToken;
    MyprofileModel model;

    private Toolbar toolbar;
    private MainActivity appCompatActivity;


    TextView txt_name,txt_gender,txt_dob,txt_address,txt_mobile,txt_email,txt_employee_code
            ,txt_marital_status,txt_height,txt_weight,txt_blood_group,txt_nationality;

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

        //str_des = getArguments().getString("des");
        //str_imagename = getArguments().getString("image");

        //api calls for get reporting person name
        //leave card api
        MyprofileParams params = new MyprofileParams();

        params.setEmployeeFK(Pref.getValue(getActivity(), Constant.PREF_SESSION_EMPLOYEE_FK, 0));
        params.setFlag("Basic");

        new WebServices(getActivity()/* ActivityContext */, ProfileFragment.this /* ApiListener */,
                true /* show progress dialog */, true).
                callMyprofileAPI(mToken, params); //from_last = ""

    }


    private void findView(View view) {

       /* toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        ((CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar)).setTitle("My Profile");
        setupToolbar();*/


        txt_name = (TextView)view.findViewById(R.id.txt_name);
        txt_gender = (TextView)view.findViewById(R.id.txt_gender);
        txt_email = (TextView)view.findViewById(R.id.txt_email);

        txt_dob = (TextView)view.findViewById(R.id.txt_dob);
        txt_address = (TextView)view.findViewById(R.id.txt_address);
        txt_mobile = (TextView)view.findViewById(R.id.txt_mobile);

        txt_employee_code = (TextView)view.findViewById(R.id.txt_employee_code);

        txt_marital_status = (TextView)view.findViewById(R.id.txt_marital_status);
        txt_height = (TextView)view.findViewById(R.id.txt_height);
        txt_weight = (TextView)view.findViewById(R.id.txt_weight);
        txt_blood_group = (TextView)view.findViewById(R.id.txt_blood_group);
        txt_nationality = (TextView)view.findViewById(R.id.txt_nationality);


    }




    private void setupToolbar() {
        toolbar.setTitle("");
        appCompatActivity.setSupportActionBar(toolbar);
    }

    private void setData() {
        txt_name.setText(model.getTable().get(0).getFirstName()+" "+model.getTable().get(0).getLastName());
        if(model.getTable().get(0).getGenderPK()==2){
            txt_gender.setText("Female");
        }else{
            txt_gender.setText("Male");
        }
        txt_dob.setText(model.getTable().get(0).getDateOfBirth());



        if(model.getTable().get(0).getPersonalEmail().equalsIgnoreCase("")){
            txt_email.setText("-");
        }
        else{
            txt_email.setText(model.getTable().get(0).getPersonalEmail());
        }

        if(model.getTable().get(0).getPerAddress().equalsIgnoreCase("")){
            txt_address.setText("-");
        }
        else{
            txt_address.setText(model.getTable().get(0).getPerAddress());
        }



        if(model.getTable().get(0).getMobileModel().equalsIgnoreCase("")){
            txt_mobile.setText("-");
        }
        else{
            txt_mobile.setText(model.getTable().get(0).getMobileModel());
        }
        if(model.getTable().get(0).getMaritalStatus().equalsIgnoreCase("")){
            txt_marital_status.setText("-");
        }
        else{
            txt_marital_status.setText(model.getTable().get(0).getMaritalStatus());
        }


        if(model.getTable().get(0).getHeight().equalsIgnoreCase("")){
            txt_height.setText("-");
        }
        else{
            txt_height.setText(model.getTable().get(0).getHeight());
        }


        if(model.getTable().get(0).getWeight().equalsIgnoreCase("")){
            txt_weight.setText("-");
        }
        else{
            txt_weight.setText(model.getTable().get(0).getWeight());
        }


        if(model.getTable().get(0).getBloodGroup().equalsIgnoreCase("")){
            txt_blood_group.setText("-");
        }
        else{
            txt_blood_group.setText(model.getTable().get(0).getBloodGroup());
        }


        if(model.getTable().get(0).getNationality().equalsIgnoreCase("")){
            txt_nationality.setText("-");
        }
        else{
            txt_nationality.setText(model.getTable().get(0).getNationality());
        }



        txt_employee_code.setText(model.getTable().get(0).getEmployeeCode());



    }


    private void setListner() {
    }


    @Override
    public void onApiSuccess(Object mObject) {
        try {

                //parse common detail data
                Log.e("", "onApiSuccess: my profile json >>  " + mObject.toString());
                Gson gson = new Gson();
                model = gson.fromJson(mObject.toString(), MyprofileModel.class);

            //set data

            setData();




        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onApiFailure(Throwable mThrowable) {

    }
}
