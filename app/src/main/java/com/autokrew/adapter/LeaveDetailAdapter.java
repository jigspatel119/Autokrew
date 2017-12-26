package com.autokrew.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.autokrew.R;
import com.autokrew.models.AttendanceDetailModel;
import com.autokrew.models.LeaveDetailModel;


public class LeaveDetailAdapter extends RecyclerView.Adapter<LeaveDetailAdapter.ViewHolder> {



    public LeaveDetailModel feedItems;
    Context ctx;


    public LeaveDetailAdapter(Context ctx, LeaveDetailModel feedItems) {

        this.feedItems = feedItems;
        this.ctx=ctx;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_attendance_detail_item,parent,false);

        LeaveDetailAdapter.ViewHolder viewHolder = new LeaveDetailAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.tv_pending_attendance.setText(""+feedItems.getTable().get(position).get_$PendingLeave251());
        holder.tv_pending_month.setText(feedItems.getTable().get(position).get_$PendingForMonth8());

    }

    @Override
    public int getItemCount() {

       return feedItems.getTable().size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_pending_month ,tv_pending_attendance; ;
        public ViewHolder(View itemView) {
            super(itemView);
            this. tv_pending_month = (TextView)itemView.findViewById(R.id.tv_pending_month);
            this.tv_pending_attendance = (TextView)itemView.findViewById(R.id.tv_pending_attendance);
        }
    }
}
