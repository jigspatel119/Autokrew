package com.autokrew.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.autokrew.R;

import java.util.ArrayList;
import java.util.List;

public class MonthYearSpinnerAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> dataBeans = new ArrayList<>();
    LayoutInflater inflater;

    public MonthYearSpinnerAdapter(Context activitySpinner, List objects) {
        super(activitySpinner, R.layout.row_spinner_item, objects);

        context = activitySpinner;
        dataBeans = objects;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    // This funtion called for each row ( Called dataBeans.size() times )
    public View getCustomView(int position, View convertView, ViewGroup parent) {

        View row = inflater.inflate(R.layout.row_spinner_item, parent, false);

       // JobTitleModel.DataBean tempValues = dataBeans.get(position);

        TextView tvItem = (TextView) row.findViewById(R.id.tvItem);

        // Set values for spinner each row
        tvItem.setText(dataBeans.get(position));

        return row;
    }
}