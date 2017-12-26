package com.autokrew.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.autokrew.R;
import com.autokrew.interfaces.RecyclerViewClickListener;
import com.autokrew.interfaces.RecyclerViewDashBoardListener;
import com.autokrew.models.AnnouncementModel;
import com.autokrew.models.DashbordModel;

import java.util.List;


public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.ViewHolder> {


    public DashbordModel feedItems;
    String TAG = "AnnouncementAdapter ::";
    Context ctx;
    private static RecyclerViewDashBoardListener itemListener;


    public AnnouncementAdapter(Context ctx, DashbordModel feedItems, RecyclerViewDashBoardListener itemListener) {

        this.feedItems = feedItems;
        this.ctx=ctx;
        this.itemListener = itemListener;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_announcement_item, parent, false);
        AnnouncementAdapter.ViewHolder viewHolder = new AnnouncementAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tv_title.setText(feedItems.getTable12().get(position).getTitle());
        holder.tv_description.setText(feedItems.getTable12().get(position).getDetail());

        holder.tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return feedItems.getTable12().size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView tv_title ,tv_description;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            this.tv_description = (TextView) itemView.findViewById(R.id.tv_description);


        }
    }
}
