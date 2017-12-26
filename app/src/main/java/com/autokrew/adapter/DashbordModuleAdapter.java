package com.autokrew.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.autokrew.R;
import com.autokrew.interfaces.RecyclerViewClickListener;
import com.autokrew.interfaces.RecyclerViewDashBoardListener;
import com.autokrew.models.BirthdayModel;
import com.autokrew.models.DashboardModuleModel;

import java.util.List;


public class DashbordModuleAdapter extends RecyclerView.Adapter<DashbordModuleAdapter.ViewHolder> {


    public List<DashboardModuleModel> feedItems;

    String TAG = "DashbordModuleAdapter ::";
    Context ctx;
    private static RecyclerViewDashBoardListener itemListener;


    public DashbordModuleAdapter(Context ctx, List<DashboardModuleModel> feedItems, RecyclerViewDashBoardListener itemListener) {

        this.feedItems = feedItems;
        this.ctx=ctx;
        this.itemListener = itemListener;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_dashboard__top_item, parent, false);
        DashbordModuleAdapter.ViewHolder viewHolder = new DashbordModuleAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.tv_title.setText(feedItems.get(position).getTitle());
        holder.tv_count.setText(String.valueOf(feedItems.get(position).getCount()));

        holder.rl_viewdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemListener.recyclerViewModuleClicked(v,position);
            }
        });

        if (position % 2 == 1) {
            holder.rl_top_module.setBackgroundColor(ctx.getResources().getColor(R.color.bg_card_color2));
        } else {
            holder.rl_top_module.setBackgroundColor(ctx.getResources().getColor(R.color.bg_card_color3));
        }


    }

    @Override
    public int getItemCount() {

        return feedItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


      //  ImageView iv_user ;
        TextView tv_title ,tv_count;
        RelativeLayout rl_viewdetail;
        RelativeLayout rl_top_module;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tv_title = (TextView)itemView.findViewById(R.id.tv_title);
            this.tv_count = (TextView) itemView.findViewById(R.id.tv_count);
            this.rl_viewdetail = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
            this.rl_top_module = (RelativeLayout)itemView.findViewById(R.id.rl_top_module);
        }
    }
}
