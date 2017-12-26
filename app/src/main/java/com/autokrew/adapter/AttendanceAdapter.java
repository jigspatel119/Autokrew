package com.autokrew.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.autokrew.R;
import com.autokrew.interfaces.RecyclerViewClickListener;
import com.autokrew.models.AttendanceModel;
import com.autokrew.utils.CommonUtils;

import java.util.List;


public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.ViewHolder> {

    public AttendanceModel feedItems;

    String TAG = "AttendanceAdapter ::";
    Context ctx;
    private static RecyclerViewClickListener itemListener;



    public AttendanceAdapter(Context ctx, AttendanceModel feedItems, RecyclerViewClickListener itemListener) {

        this.feedItems = feedItems;
        this.ctx=ctx;
        this.itemListener = itemListener;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_intime ,txt_outtime,txt_workinghrs ,date_time ,
                txt_deviation,txt_emp_remarks,txt_rep_person_status,txt_rep_person_remarks;
        Button btn_book;
        ImageView iv_absent;

        ImageView iv_edit ;

        public ViewHolder(View itemView) {
            super(itemView);
            this.txt_intime = (TextView) itemView.findViewById(R.id.txt_intime);
            this.txt_outtime = (TextView) itemView.findViewById(R.id.txt_outtime);
            this.txt_workinghrs = (TextView) itemView.findViewById(R.id.txt_workinghrs);
            this.date_time = (TextView) itemView.findViewById(R.id.date_time);

            this.txt_deviation = (TextView) itemView.findViewById(R.id.txt_deviation);
            this.txt_emp_remarks = (TextView) itemView.findViewById(R.id.txt_emp_remarks);
            this.txt_rep_person_status = (TextView) itemView.findViewById(R.id.txt_rep_person_status);
            this.txt_rep_person_remarks = (TextView) itemView.findViewById(R.id.txt_rep_person_remarks);

            this.iv_absent = (ImageView)itemView.findViewById(R.id.iv_absent);

            this.iv_edit = (ImageView)itemView.findViewById(R.id.iv_edit);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_attendance_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {

        holder.txt_intime.setText(""+feedItems.getTable2().get(position).getFirstIn());
        holder.txt_outtime.setText(""+feedItems.getTable2().get(position).getLastOut());
        holder.txt_workinghrs.setText(""+feedItems.getTable2().get(position).getWorkingHours());
        holder.date_time.setText(""+feedItems.getTable2().get(position).getDate());


        holder.txt_deviation.setText(""+feedItems.getTable2().get(position).getDeviation());
        holder.txt_emp_remarks.setText(""+feedItems.getTable2().get(position).getEmployeeRemarks());
        holder.txt_rep_person_status.setText(""+feedItems.getTable2().get(position).getReportingPersonStatus());
        holder.txt_rep_person_remarks.setText(""+feedItems.getTable2().get(position).getReportingPersonRemarks());



        if(feedItems.getTable2().get(position).getLockAttendance().equalsIgnoreCase("Lock")){
            holder.iv_edit.setVisibility(View.INVISIBLE);
        }
        else if(feedItems.getTable2().get(position).getStatus().equalsIgnoreCase("L")){

            if(feedItems.getTable2().get(position).getLeaveDayType().equals(1)){
                holder.iv_edit.setVisibility(View.VISIBLE);
            }else{
                holder.iv_edit.setVisibility(View.INVISIBLE);
            }

        }

        else if((feedItems.getTable2().get(position).getStatus().equalsIgnoreCase("PL") ||
                feedItems.getTable2().get(position).getStatus().equalsIgnoreCase("PE") ||
                feedItems.getTable2().get(position).getStatus().equalsIgnoreCase("P2") ||
                feedItems.getTable2().get(position).getStatus().equalsIgnoreCase("A") ||
                feedItems.getTable2().get(position).getStatus().equalsIgnoreCase("WO") ||
                feedItems.getTable2().get(position).getStatus().equalsIgnoreCase("H"))&&(
                        feedItems.getTable2().get(position).getReportingPersonStatus().equalsIgnoreCase("-")||
                        feedItems.getTable2().get(position).getReportingPersonStatus().equalsIgnoreCase("Rejected"))
                ){
            holder.iv_edit.setVisibility(View.VISIBLE);

        }

        else{
            holder.iv_edit.setVisibility(View.INVISIBLE); //for "P"
        }

        holder.iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                itemListener.recyclerViewListClicked(v, position);
            }
        });

       /* Typeface copperplateGothicLight = Typeface.createFromAsset(ctx.getAssets(), "GillSans-SemiBold.ttf");
        holder.btn_book.setTypeface(copperplateGothicLight);*/

       //set icons...
        if(feedItems.getTable2().get(position).getStatus().equalsIgnoreCase("A")){
            holder.iv_absent.setImageResource(R.drawable.absent);
        }else if(feedItems.getTable2().get(position).getStatus().equalsIgnoreCase("H")){
            holder.iv_absent.setImageResource(R.drawable.holiday);
        }else if(feedItems.getTable2().get(position).getStatus().equalsIgnoreCase("WO")){
            holder.iv_absent.setImageResource(R.drawable.weekoff);
        }else if(feedItems.getTable2().get(position).getStatus().equalsIgnoreCase("PL")){
            holder.iv_absent.setImageResource(R.drawable.punchlate);
        }else if(feedItems.getTable2().get(position).getStatus().equalsIgnoreCase("P2")){
            holder.iv_absent.setImageResource(R.drawable.halfday);
        }else if(feedItems.getTable2().get(position).getStatus().equalsIgnoreCase("PE")){
            holder.iv_absent.setImageResource(R.drawable.punchearly);
        }else if(feedItems.getTable2().get(position).getStatus().equalsIgnoreCase("L")){
            holder.iv_absent.setImageResource(R.drawable.leave);
        }
        else if(feedItems.getTable2().get(position).getStatus().equalsIgnoreCase("P")){
            holder.iv_absent.setImageResource(R.drawable.present);
        }


    }

    @Override
    public int getItemCount() {

        return feedItems.getTable2().size();
        //return feedItems.size();
    }

    public int getCount() {

        return feedItems.getTable2().size();
    }

    /*public AttendanceModel getItem(int i) {

        return feedItems.get(i);
    }*/

    @Override
    public long getItemId(int i) {

        return i;
    }

}
