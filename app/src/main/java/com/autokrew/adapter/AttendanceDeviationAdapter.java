package com.autokrew.adapter;

import android.app.Activity;
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
import com.autokrew.activity.AttendanceDeviationActivity;
import com.autokrew.interfaces.RecyclerViewClickListener;
import com.autokrew.models.AttendanceDeviationTGModel;
import com.autokrew.models.AttendanceModel;
import com.autokrew.models.AttendanceTeamGroupModel;

import java.util.ArrayList;
import java.util.List;


public class AttendanceDeviationAdapter extends RecyclerView.Adapter<AttendanceDeviationAdapter.ViewHolder> implements
        Filterable {

    public AttendanceDeviationTGModel feedItems;
   // private List<AttendanceDeviationTGModel> mFilteredList;

    String TAG = "AttendanceDeviationAdapter ::";
    Context ctx;
    private static RecyclerViewClickListener itemListener;

    public AttendanceDeviationAdapter(Context ctx, AttendanceDeviationTGModel feedItems, RecyclerViewClickListener itemListener) {

        this.feedItems = feedItems;
        this.ctx=ctx;
        this.itemListener = itemListener;
       // mFilteredList = feedItems;

    }

   public Filter getFilter() {
       return  null;

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
        TextView txt_date_time,txt_name,txt_inouttime,txt_workinghrs,txt_deviation,txt_approval_status ,txt_emp_remarks;

        ImageView iv_edit ;

        public ViewHolder(View itemView) {
            super(itemView);
           this.txt_name = (TextView)itemView.findViewById(R.id.txt_name);
           this.txt_date_time = (TextView)itemView.findViewById(R.id.txt_date_time);
            this.txt_inouttime = (TextView)itemView.findViewById(R.id.txt_inouttime);
            this.txt_workinghrs = (TextView)itemView.findViewById(R.id.txt_workinghrs);
            this.txt_deviation = (TextView)itemView.findViewById(R.id.txt_deviation);
            this.txt_approval_status = (TextView)itemView.findViewById(R.id.txt_approval_status);
            this.txt_emp_remarks = (TextView)itemView.findViewById(R.id.txt_emp_remarks);


           this.iv_edit = (ImageView)itemView.findViewById(R.id.iv_edit);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_attendance_deviation_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {
        holder.txt_name.setText(feedItems.getTable().get(position).getName());
        holder.txt_date_time.setText(feedItems.getTable().get(position).getDate());
        holder.txt_inouttime.setText(feedItems.getTable().get(position).getFirstIn()+" TO "+feedItems.getTable().get(position).getLastOut());
        holder.txt_workinghrs.setText(feedItems.getTable().get(position).getWorkingHours());
        holder.txt_deviation.setText(feedItems.getTable().get(position).getDeviation());
        holder.txt_approval_status.setText(feedItems.getTable().get(position).getApprovalStatus());
        holder.txt_emp_remarks.setText(feedItems.getTable().get(position).getEmployeeRemarks());



        holder.iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                itemListener.recyclerViewListClicked(v, position);
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
