package com.autokrew.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.autokrew.R;
import com.autokrew.interfaces.RecyclerViewClickListener;
import com.autokrew.models.AttendanceModel;
import com.autokrew.models.GroupLeaveModel;
import com.autokrew.utils.Constant;
import com.autokrew.utils.Pref;

import java.util.ArrayList;
import java.util.List;


public class GroupLeaveAdapter extends RecyclerView.Adapter<GroupLeaveAdapter.ViewHolder> implements Filterable {

    public GroupLeaveModel feedItems;
 //   private List<GroupLeaveModel> mFilteredList;
    String TAG = "GroupLeaveAdapter ::";
    Context ctx;
    private static RecyclerViewClickListener itemListener;

    public GroupLeaveAdapter(Context ctx, GroupLeaveModel feedItems, RecyclerViewClickListener itemListener) {

        this.feedItems = feedItems;
        this.ctx=ctx;
        this.itemListener = itemListener;
        //mFilteredList = feedItems;

    }

    public Filter getFilter() {

        return  null;
       /* return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mFilteredList = feedItems;
                } else {

                    ArrayList<GroupLeaveModel> filteredList = new ArrayList<>();


                    for (GroupLeaveModel model : feedItems) {

                        String getName=model.getFirstname().toLowerCase();
                            if(getName.contains(charString.toLowerCase())){

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

                mFilteredList = (ArrayList<GroupLeaveModel>) filterResults.values;

                notifyDataSetChanged();
            }
        };*/
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_name ,txt_emp_code,txt_from_date,txt_to_date,
                txt_department,txt_designation,txt_leave_type
                ,txt_leave_day_type,txt_duration;
        ImageView iv_edit ,iv_revised,iv_cancel,iv_lock,iv_approved,iv_lock_revised ;

        public ViewHolder(View itemView) {
            super(itemView);
           this.txt_name = (TextView)itemView.findViewById(R.id.txt_name);
           this.iv_edit = (ImageView)itemView.findViewById(R.id.iv_edit);
            this.iv_revised = (ImageView)itemView.findViewById(R.id.iv_revised);
            this.iv_cancel = (ImageView)itemView.findViewById(R.id.iv_cancel);
            this.iv_lock = (ImageView)itemView.findViewById(R.id.iv_lock);
            this.iv_approved = (ImageView)itemView.findViewById(R.id.iv_approved);

            this.iv_lock_revised = (ImageView)itemView.findViewById(R.id.iv_lock_revised);


            this.txt_emp_code = (TextView)itemView.findViewById(R.id.txt_emp_code);
            this.txt_from_date = (TextView)itemView.findViewById(R.id.txt_from_date);
            this.txt_to_date = (TextView)itemView.findViewById(R.id.txt_to_date);
            this.txt_department = (TextView)itemView.findViewById(R.id.txt_department);
            this.txt_designation = (TextView)itemView.findViewById(R.id.txt_designation);
            this.txt_leave_type = (TextView)itemView.findViewById(R.id.txt_leave_type);
            this.txt_leave_day_type = (TextView)itemView.findViewById(R.id.txt_leave_day_type);
            this.txt_duration = (TextView)itemView.findViewById(R.id.txt_duration);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_group_leave_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {


        holder.txt_name.setText(feedItems.getTable().get(position).getName());
        holder.txt_emp_code.setText(feedItems.getTable().get(position).get_$EmployeeCode276());
        holder.txt_from_date.setText(feedItems.getTable().get(position).get_$LeaveFrom158());
        holder.txt_to_date.setText(feedItems.getTable().get(position).get_$LeaveTo322());
        holder.txt_department.setText(feedItems.getTable().get(position).getDepartment());
        holder.txt_designation.setText(feedItems.getTable().get(position).getDesignation());
        holder.txt_leave_type.setText(feedItems.getTable().get(position).get_$LeaveType236());
        holder.txt_leave_day_type.setText(feedItems.getTable().get(position).get_$LeaveDayType84());
        holder.txt_duration.setText(String.valueOf(feedItems.getTable().get(position).getDuration()));

//        iv_edit ,iv_revised,iv_cancel,iv_lock,iv_approved ;
        if(feedItems.getTable().get(position).get_$MobileManageStatus30().equalsIgnoreCase("Approved")){
            holder.iv_approved.setVisibility(View.VISIBLE);
        }
        if(feedItems.getTable().get(position).get_$MobileManageStatus30().equalsIgnoreCase("Rejected")){
            holder.iv_cancel.setVisibility(View.VISIBLE);

        }
        if(feedItems.getTable().get(position).get_$MobileManageStatus30().equalsIgnoreCase("Open")){
            holder.iv_edit.setVisibility(View.VISIBLE);

        }
        if(feedItems.getTable().get(position).get_$MobileManageStatus30().equalsIgnoreCase("Lock")){
            holder.iv_lock.setVisibility(View.VISIBLE);
        }



        if(feedItems.getTable().get(position).get_$MobileReviseLeave160().equalsIgnoreCase("Open Revise")){
            holder.iv_revised.setVisibility(View.VISIBLE);
        }
        if(feedItems.getTable().get(position).get_$MobileReviseLeave160().equalsIgnoreCase("Lock Revise")){
            holder.iv_lock_revised.setVisibility(View.VISIBLE);
        }





        holder.iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Pref.setValue(ctx, Constant.PREF_LEAVE_BUTTON,"iv_edit");
                itemListener.recyclerViewListClicked(v, position);
            }
        });

        holder.iv_revised.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pref.setValue(ctx, Constant.PREF_LEAVE_BUTTON,"iv_revised");
                itemListener.recyclerViewListClicked(v, position);
            }
        });



    }

    @Override
    public int getItemCount() {
        return feedItems.getTable().size();
    }

    @Override
    public long getItemId(int i) {

        return i;
    }

}
