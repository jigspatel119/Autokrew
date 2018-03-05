package com.autokrew.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.autokrew.R;
import com.autokrew.activity.AttendanceInDetailActivity;
import com.autokrew.models.AttendanceStatusModel;
import com.autokrew.utils.Pref;


public class AttendanceStatusAdapter extends RecyclerView.Adapter<AttendanceStatusAdapter.ViewHolder> implements
        Filterable {

    public AttendanceStatusModel feedItems;
    Context ctx;
    Intent i;

    public AttendanceStatusAdapter(Context ctx, AttendanceStatusModel feedItems) {

        this.feedItems = feedItems;
        this.ctx = ctx;
    }

    public Filter getFilter() {
        return null;

      /*  return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();


                if (charString.isEmpty()) {

                    mFilteredList = feedItems;
                } else {

                    ArrayList<AttendanceModel> filteredList = new ArrayList<>();


                    for (AttendanceModel model : feedItems) {


                            if(model.getFirstname().toLowerCase().contains(charString.toLowerCase())){

                            filteredList.add(model);
                        }
                    }


                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                mFilteredList = (ArrayList<AttendanceModel>) filterResults.values;

                // Set on UI Thread
                ((Activity) ctx).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Notify the List that the DataSet has changed...
                        notifyDataSetChanged();
                    }
                });

            }
        };*/
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_present, txt_halfday, txt_absent, txt_leave, txt_holiday, txt_weekoff, txt_punch_late, txt_punch_early, txt_name, txt_emp_code;

        RelativeLayout rv_pe, rv_pl, rv_wo, rv_h, rv_l, rv_a, rv_p2, rv_p;

        public ViewHolder(View itemView) {
            super(itemView);
            this.txt_present = (TextView) itemView.findViewById(R.id.txt_present);
            this.txt_halfday = (TextView) itemView.findViewById(R.id.txt_halfday);
            this.txt_absent = (TextView) itemView.findViewById(R.id.txt_absent);
            this.txt_leave = (TextView) itemView.findViewById(R.id.txt_leave);
            this.txt_holiday = (TextView) itemView.findViewById(R.id.txt_holiday);
            this.txt_weekoff = (TextView) itemView.findViewById(R.id.txt_weekoff);
            this.txt_punch_late = (TextView) itemView.findViewById(R.id.txt_punch_late);
            this.txt_punch_early = (TextView) itemView.findViewById(R.id.txt_punch_early);
            this.txt_name = (TextView) itemView.findViewById(R.id.txt_name);
            this.txt_emp_code = (TextView) itemView.findViewById(R.id.txt_emp_code);

            this.rv_pe = (RelativeLayout) itemView.findViewById(R.id.rv_pe);
            this.rv_pl = (RelativeLayout) itemView.findViewById(R.id.rv_pl);
            this.rv_wo = (RelativeLayout) itemView.findViewById(R.id.rv_wo);
            this.rv_h = (RelativeLayout) itemView.findViewById(R.id.rv_h);
            this.rv_l = (RelativeLayout) itemView.findViewById(R.id.rv_l);
            this.rv_a = (RelativeLayout) itemView.findViewById(R.id.rv_a);
            this.rv_p2 = (RelativeLayout) itemView.findViewById(R.id.rv_p2);
            this.rv_p = (RelativeLayout) itemView.findViewById(R.id.rv_p);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_attendance_status_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.txt_name.setText(feedItems.getTable().get(position).getName());
        holder.txt_emp_code.setText(feedItems.getTable().get(position).get_$EmployeeCode299());

        holder.txt_present.setText(String.valueOf(feedItems.getTable().get(position).getP()));
        holder.txt_halfday.setText(String.valueOf(feedItems.getTable().get(position).getP2()));
        holder.txt_absent.setText(String.valueOf(feedItems.getTable().get(position).getA()));
        holder.txt_leave.setText(String.valueOf(feedItems.getTable().get(position).getL()));
        holder.txt_holiday.setText(String.valueOf(feedItems.getTable().get(position).getH()));
        holder.txt_weekoff.setText(String.valueOf(feedItems.getTable().get(position).getWO()));
        holder.txt_punch_late.setText(String.valueOf(feedItems.getTable().get(position).getPL()));
        holder.txt_punch_early.setText(String.valueOf(feedItems.getTable().get(position).getPE()));

        holder.rv_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.txt_present.getText().toString().equalsIgnoreCase("0")){

                }
                else{
                    i = new Intent(ctx, AttendanceInDetailActivity.class);
                    i.putExtra("approve_status", "P");
                    i.putExtra("employee_fk",holder.txt_emp_code.getText().toString()); //emp code
                    ctx.startActivity(i);

                }

            }
        });

        holder.rv_pe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.txt_punch_early.getText().toString().equalsIgnoreCase("0")){

                }
                else {
                    i = new Intent(ctx, AttendanceInDetailActivity.class);
                    i.putExtra("approve_status", "PE");
                    i.putExtra("employee_fk", holder.txt_emp_code.getText().toString()); //emp code
                    ctx.startActivity(i);
                }
            }
        });
        holder.rv_pl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.txt_punch_late.getText().toString().equalsIgnoreCase("0")){

                }
                else {
                    i = new Intent(ctx, AttendanceInDetailActivity.class);
                    i.putExtra("approve_status", "PL");
                    i.putExtra("employee_fk", holder.txt_emp_code.getText().toString()); //emp code
                    ctx.startActivity(i);
                }
            }
        });
        holder.rv_wo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.txt_weekoff.getText().toString().equalsIgnoreCase("0")){

                }
                else {
                    i = new Intent(ctx, AttendanceInDetailActivity.class);
                    i.putExtra("approve_status", "WO");
                    i.putExtra("employee_fk", holder.txt_emp_code.getText().toString()); //emp code
                    ctx.startActivity(i);
                }
            }
        });
        holder.rv_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.txt_holiday.getText().toString().equalsIgnoreCase("0")){

                }
                else {
                    i = new Intent(ctx, AttendanceInDetailActivity.class);
                    i.putExtra("approve_status", "H");
                    i.putExtra("employee_fk", holder.txt_emp_code.getText().toString()); //emp code
                    ctx.startActivity(i);
                }
            }
        });
        holder.rv_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.txt_leave.getText().toString().equalsIgnoreCase("0")){

                }
                else {
                    i = new Intent(ctx, AttendanceInDetailActivity.class);
                    i.putExtra("approve_status", "L");
                    i.putExtra("employee_fk", holder.txt_emp_code.getText().toString()); //emp code
                    ctx.startActivity(i);
                }
            }
        });
        holder.rv_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.txt_absent.getText().toString().equalsIgnoreCase("0")){

                }
                else {
                    i = new Intent(ctx, AttendanceInDetailActivity.class);
                    i.putExtra("approve_status", "A");
                    i.putExtra("employee_fk", holder.txt_emp_code.getText().toString()); //emp code
                    ctx.startActivity(i);
                }
            }
        });
        holder.rv_p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.txt_halfday.getText().toString().equalsIgnoreCase("0")){

                }
                else {
                    i = new Intent(ctx, AttendanceInDetailActivity.class);
                    i.putExtra("approve_status", "P2");
                    i.putExtra("employee_fk", holder.txt_emp_code.getText().toString()); //emp code
                    ctx.startActivity(i);
                }
            }
        });

    }

    @Override
    public int getItemCount() {

        return feedItems.getTable().size();
    }



   /* public void filter(ArrayList<AttendanceModel> newList)
    {

        nameArrayList.clear();
        nameArrayList.addAll(newList);
        notifyDataSetChanged();
    }
*/
}
