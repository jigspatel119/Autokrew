package com.autokrew.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.autokrew.R;
import com.autokrew.activity.MainActivity;
import com.autokrew.models.Model_country;

import java.util.ArrayList;


/**
 * Created by deepshikha on 12/7/17.
 */

public class CountryAdapter extends BaseExpandableListAdapter {
    Context context;
    ArrayList<Model_country> al_country;
    ImageView img_parent_left;


    public CountryAdapter(Context context, ArrayList<Model_country> al_country) {
        this.context = context;
        this.al_country = al_country;
    }

    @Override
    public int getGroupCount() {

        return al_country.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return al_country.get(i).getAl_state().size();
    }

    @Override
    public Object getGroup(int i) {

        return al_country.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {

        return al_country.get(i).getAl_state().get(i1);
    }

    @Override
    public long getGroupId(int i)
    {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {

        return i1;
    }

    @Override
    public boolean hasStableIds() {

        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if (view==null){

            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.adapter_header, null);
        }
        TextView tv_state = (TextView)view.findViewById(R.id.tv_name);

        img_parent_left = (ImageView)view.findViewById(R.id.img_parent_left);
        tv_state.setText(al_country.get(i).getStr_country());

        //set left icon as per the parent category

        if(al_country.get(i).getStr_country().equalsIgnoreCase("Dashboard")){
            img_parent_left.setImageResource(R.drawable.ic_dashboard);
        }

        if(al_country.get(i).getStr_country().equalsIgnoreCase("Attendance")){
            img_parent_left.setImageResource(R.drawable.ic_attendence);
        }

        else if(al_country.get(i).getStr_country().equalsIgnoreCase("Leave")){
            img_parent_left.setImageResource(R.drawable.ic_leave);
        }

        else if(al_country.get(i).getStr_country().equalsIgnoreCase("Sign Out")){
            img_parent_left.setImageResource(R.drawable.ic_signout);
        }

        return view;
    }

    @Override
    public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
        if (view==null){

            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.adapter_childview, null);
        }

        TextView tv_state = (TextView)view.findViewById(R.id.tv_name);

        tv_state.setText(al_country.get(i).getAl_state().get(i1).getStr_name());
        tv_state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)context).fn_selectedPosition(i,i1);
            }
        });

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }


}
