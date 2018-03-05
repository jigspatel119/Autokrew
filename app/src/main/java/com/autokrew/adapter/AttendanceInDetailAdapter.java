package com.autokrew.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.autokrew.R;
import com.autokrew.interfaces.RecyclerViewClickListener;
import com.autokrew.models.AttendanceInDetailModel;
import com.autokrew.models.AttendanceModel;


public class AttendanceInDetailAdapter extends RecyclerView.Adapter<AttendanceInDetailAdapter.ViewHolder> {

    public AttendanceInDetailModel feedItems;

    String TAG = "AttendanceAdapter ::";
    Context ctx;
    private SparseBooleanArray itemStateArray= new SparseBooleanArray();


    public AttendanceInDetailAdapter(Context ctx, AttendanceInDetailModel feedItems) {

        this.feedItems = feedItems;
        this.ctx=ctx;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_intime ,txt_outtime,txt_workinghrs ,date_time ,
                txt_deviation,txt_emp_remarks,txt_rep_person_status,txt_rep_person_remarks;
        Button btn_book;
        TextView txt_status;

        RelativeLayout rv_status;

        LinearLayout ll_header_card ,ll_bottom_views;
        ImageView iv_edit ,img_hide_show;

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

            this.txt_status = (TextView) itemView.findViewById(R.id.txt_status);
            this.rv_status = (RelativeLayout)itemView.findViewById(R.id.rv_status);

            this.iv_edit = (ImageView)itemView.findViewById(R.id.iv_edit);
            this.img_hide_show = (ImageView)itemView.findViewById(R.id.img_hide_show);

            this.ll_header_card = (LinearLayout)itemView.findViewById(R.id.ll_header_card);
            this.ll_bottom_views = (LinearLayout)itemView.findViewById(R.id.ll_bottom_views);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_attendance_item2, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {

        holder.txt_intime.setText(""+feedItems.getTable1().get(position).get_$FirstIn301());
        holder.txt_outtime.setText(""+feedItems.getTable1().get(position).get_$LastOut183());
        holder.txt_workinghrs.setText(""+feedItems.getTable1().get(position).get_$WorkingHours1());
        String[] parts = feedItems.getTable1().get(position).getDate().split(", ");

        //holder.date_time.setText(""+feedItems.getTable2().get(position).getDate());
        holder.date_time.setText(""+parts[1]);


        holder.txt_deviation.setText(""+feedItems.getTable1().get(position).getDeviation());
        holder.txt_emp_remarks.setText(""+feedItems.getTable1().get(position).get_$EmployeeRemarks121());
        holder.txt_rep_person_status.setText(""+feedItems.getTable1().get(position).get_$ReportingPersonStatus233());
        holder.txt_rep_person_remarks.setText(""+feedItems.getTable1().get(position).get_$ReportingPersonRemarks188());

        holder.iv_edit.setVisibility(View.INVISIBLE);

       /* if(feedItems.getTable1().get(position).getLockAttendance().equalsIgnoreCase("Lock")){
            holder.iv_edit.setVisibility(View.INVISIBLE);
        }
        else if(feedItems.getTable1().get(position).getStatus().equalsIgnoreCase("L")){

            if(feedItems.getTable1().get(position).getLeaveDayType().equals(1)){
                holder.iv_edit.setVisibility(View.VISIBLE);
            }else{
                holder.iv_edit.setVisibility(View.INVISIBLE);
            }

        }*/

     /*   if((feedItems.getTable1().get(position).getStatus().equalsIgnoreCase("PL") ||
                feedItems.getTable1().get(position).getStatus().equalsIgnoreCase("PE") ||
                feedItems.getTable1().get(position).getStatus().equalsIgnoreCase("P2") ||
                feedItems.getTable1().get(position).getStatus().equalsIgnoreCase("A") ||
                feedItems.getTable1().get(position).getStatus().equalsIgnoreCase("WO") ||
                feedItems.getTable1().get(position).getStatus().equalsIgnoreCase("H"))&&(
                        feedItems.getTable1().get(position).get_$ReportingPersonStatus233().equalsIgnoreCase("-")||
                        feedItems.getTable1().get(position).get_$ReportingPersonStatus233().equalsIgnoreCase("Rejected"))
                ){
            holder.iv_edit.setVisibility(View.VISIBLE);

        }

        else{
            holder.iv_edit.setVisibility(View.INVISIBLE); //for "P"
        }*/

       /* holder.iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                itemListener.recyclerViewListClicked(v, position);
            }
        });*/

        holder.img_hide_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               //itemListener.recyclerViewListClicked(v, position);
               // holder.ll_bottom_views.setVisibility(View.VISIBLE);
                //int adapterPosition = getAdapterPosition();
                if (!itemStateArray.get(position, false)) {
                   // holder.ll_bottom_views.animate().translationY(holder.ll_bottom_views.getHeight());
                    //holder.ll_bottom_views.setVisibility(View.VISIBLE);
                    itemStateArray.put(position, true);
                    holder.img_hide_show.setImageResource(R.mipmap.ic_keyboard_arrow_up_white_24dp);
                    expand(holder.ll_bottom_views);

                }
                else  {
                    //holder.ll_bottom_views.animate().translationY(0);
                   // holder.ll_bottom_views.setVisibility(View.GONE);
                    itemStateArray.put(position, false);
                    holder.img_hide_show.setImageResource(R.mipmap.ic_keyboard_arrow_down_white_24dp);
                    collapse(holder.ll_bottom_views);

                }

            }
        });


       //set icons...
        if(feedItems.getTable1().get(position).getStatus().equalsIgnoreCase("A")){
            holder.txt_status.setText("A");
            holder.rv_status.setBackground(ctx.getResources().getDrawable(R.drawable.bg_circular3));
            holder.ll_header_card.setBackground(ctx.getResources().getDrawable(R.drawable.bg_circular3));

        }else if(feedItems.getTable1().get(position).getStatus().equalsIgnoreCase("H")){
            holder.txt_status.setText("H");
            holder.rv_status.setBackground(ctx.getResources().getDrawable(R.drawable.bg_circular5));
            holder.ll_header_card.setBackground(ctx.getResources().getDrawable(R.drawable.bg_circular5));


        }else if(feedItems.getTable1().get(position).getStatus().equalsIgnoreCase("WO")){
            holder.txt_status.setText("WO");
            holder.rv_status.setBackground(ctx.getResources().getDrawable(R.drawable.bg_circular6));
            holder.ll_header_card.setBackground(ctx.getResources().getDrawable(R.drawable.bg_circular6));


        }else if(feedItems.getTable1().get(position).getStatus().equalsIgnoreCase("PL")){
            holder.txt_status.setText("PL");
            holder.rv_status.setBackground(ctx.getResources().getDrawable(R.drawable.bg_circular7));
            holder.ll_header_card.setBackground(ctx.getResources().getDrawable(R.drawable.bg_circular7));


        }else if(feedItems.getTable1().get(position).getStatus().equalsIgnoreCase("P2")){
            holder.txt_status.setText("P2");
            holder.rv_status.setBackground(ctx.getResources().getDrawable(R.drawable.bg_circular2));
            holder.ll_header_card.setBackground(ctx.getResources().getDrawable(R.drawable.bg_circular2));


        }else if(feedItems.getTable1().get(position).getStatus().equalsIgnoreCase("PE")){
            holder.txt_status.setText("PE");
            holder.rv_status.setBackground(ctx.getResources().getDrawable(R.drawable.bg_circular7));
            holder.ll_header_card.setBackground(ctx.getResources().getDrawable(R.drawable.bg_circular7));


        }else if(feedItems.getTable1().get(position).getStatus().equalsIgnoreCase("L")){
            holder.txt_status.setText("L");
            holder.rv_status.setBackground(ctx.getResources().getDrawable(R.drawable.bg_circular4));
            holder.ll_header_card.setBackground(ctx.getResources().getDrawable(R.drawable.bg_circular4));


        }
        else if(feedItems.getTable1().get(position).getStatus().equalsIgnoreCase("P")){
            holder.txt_status.setText("P");
            holder.rv_status.setBackground(ctx.getResources().getDrawable(R.drawable.bg_circular1));
            holder.ll_header_card.setBackground(ctx.getResources().getDrawable(R.drawable.bg_circular1));

        }

    }

    @Override
    public int getItemCount() {

        return feedItems.getTable1().size();
        //return 5;
    }




    @Override
    public long getItemId(int i) {

        return i;
    }


    public static void expand(final View v) {
        v.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final int targtetHeight = v.getMeasuredHeight();

        v.getLayoutParams().height = 0;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? LinearLayout.LayoutParams.WRAP_CONTENT
                        : (int)(targtetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration((int)(targtetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1){
                    v.setVisibility(View.GONE);
                }else{
                    v.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration((int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }


}
