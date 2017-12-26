package com.autokrew.adapter;

import android.content.Context;
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
import com.autokrew.models.LeaveCardModel;
import com.autokrew.models.LeaveModel;

import java.util.List;


public class LeaveAdapter extends RecyclerView.Adapter<LeaveAdapter.ViewHolder> {

    public LeaveCardModel feedItems;

    String TAG = "LeaveAdapter ::";
    Context ctx;
    private static RecyclerViewClickListener itemListener;



    public LeaveAdapter(Context ctx, LeaveCardModel feedItems, RecyclerViewClickListener itemListener) {

        this.feedItems = feedItems;
        this.ctx=ctx;
        this.itemListener = itemListener;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_leave_type ,txt_leave_day,txt_duration,txt_leave_status
                ,txt_leave_to ,txt_leave_from;


        ImageView iv_edit ;

        public ViewHolder(View itemView) {
            super(itemView);
            this.txt_leave_type = (TextView) itemView.findViewById(R.id.txt_leave_type);
            this.txt_leave_day = (TextView) itemView.findViewById(R.id.txt_leave_day);
            this.txt_duration = (TextView) itemView.findViewById(R.id.txt_duration);
            this.txt_leave_status =(TextView) itemView.findViewById(R.id.txt_leave_status);
            this.txt_leave_from =(TextView)itemView.findViewById(R.id.txt_leave_from);
            this.txt_leave_to =(TextView)itemView.findViewById(R.id.txt_leave_to);


            this.iv_edit = (ImageView)itemView.findViewById(R.id.iv_edit);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_leave_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {

        holder.txt_leave_type.setText(feedItems.getTable1().get(position).get_$LeaveType97());
        holder.txt_leave_day.setText(feedItems.getTable1().get(position).get_$LeaveDayType232());
        holder.txt_duration.setText(""+feedItems.getTable1().get(position).getDuration());
        holder.txt_leave_status.setText(feedItems.getTable1().get(position).get_$LeaveStatus209());
        holder.txt_leave_from.setText(feedItems.getTable1().get(position).get_$LeaveFrom161());
        holder.txt_leave_to.setText(feedItems.getTable1().get(position).get_$LeaveTo87());

        if(feedItems.getTable1().get(position).get_$LeaveStatus209().equalsIgnoreCase("Approved") ||feedItems.getTable1().get(position).get_$LeaveStatus209().equalsIgnoreCase("Rejected") ){
            //hide
            //locak icon
            holder.iv_edit.setImageResource(R.drawable.ic_lock);
        }else{
            //show

            holder.iv_edit.setImageResource(R.drawable.ic_cancel_leave);

        }

        holder.iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(feedItems.getTable1().get(position).get_$LeaveStatus209().equalsIgnoreCase("Approved") ||feedItems.getTable1().get(position).get_$LeaveStatus209().equalsIgnoreCase("Rejected") ){
                    //hide
                    //locak icon

                }else{
                    //show dialog
                    itemListener.recyclerViewListClicked(v, position);
                }

            }
        });

       /* Typeface copperplateGothicLight = Typeface.createFromAsset(ctx.getAssets(), "GillSans-SemiBold.ttf");
        holder.btn_book.setTypeface(copperplateGothicLight);*/

    }

    @Override
    public int getItemCount() {

        return feedItems.getTable1().size();
        //return feedItems.size();
    }

    @Override
    public long getItemId(int i) {

        return i;
    }

}
