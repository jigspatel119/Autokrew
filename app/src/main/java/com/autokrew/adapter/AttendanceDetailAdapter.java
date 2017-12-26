package com.autokrew.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.autokrew.R;
import com.autokrew.interfaces.RecyclerViewDashBoardListener;
import com.autokrew.models.AttendanceDetailModel;
import com.autokrew.models.DashbordModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;


public class AttendanceDetailAdapter extends RecyclerView.Adapter<AttendanceDetailAdapter.ViewHolder> {



    public AttendanceDetailModel feedItems;
    Context ctx;


    public AttendanceDetailAdapter(Context ctx, AttendanceDetailModel feedItems) {

        this.feedItems = feedItems;
        this.ctx=ctx;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_attendance_detail_item,parent,false);

        AttendanceDetailAdapter.ViewHolder viewHolder = new AttendanceDetailAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.tv_pending_attendance.setText(""+feedItems.getTable().get(position).get_$PendingAttendance320());
        holder.tv_pending_month.setText(feedItems.getTable().get(position).get_$PendingForMonth150());


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
