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
import com.autokrew.interfaces.RecyclerViewDashBoardListener;
import com.autokrew.models.BirthdayModel;
import com.autokrew.models.DashbordModel;
import com.autokrew.models.LeaveModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;


public class BirthdayAdapter extends RecyclerView.Adapter<BirthdayAdapter.ViewHolder> {



    public DashbordModel feedItems;

    String TAG = "BirthdayAdapter ::";
    Context ctx;
    private static RecyclerViewDashBoardListener itemListener;


    public BirthdayAdapter(Context ctx, DashbordModel feedItems, RecyclerViewDashBoardListener itemListener) {

        this.feedItems = feedItems;
        this.ctx=ctx;
        this.itemListener = itemListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_birthday_item, parent, false);
        BirthdayAdapter.ViewHolder viewHolder = new BirthdayAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

       // holder.iv_user.setImageResource(R.drawable.img_user1);

        holder.tv_name.setText(feedItems.getTable4().get(position).getName());

        holder.tv_date.setText(feedItems.getTable4().get(position).getBDate());
        holder.iv_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                itemListener.recyclerViewListClicked(v,position);

            }
        });

        Glide.with(ctx)
                .load(feedItems.getTable4().get(position).getImageUrl()).dontAnimate()
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        //progressLoadImage.setVisibility(View.GONE);
                        return false;
                    }
                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        // progressLoadImage.setVisibility(View.GONE);
                        return false;
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.drawable.profile_image)
                .error(R.drawable.profile_image)
                .into(holder.iv_user);

    }

    @Override
    public int getItemCount() {

       return feedItems.getTable4().size(); //today's birthday
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        ImageView iv_user ;
        TextView tv_date ,tv_name;

        public ViewHolder(View itemView) {
            super(itemView);
            this.iv_user = (ImageView)itemView.findViewById(R.id.iv_user);
            this.tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            this.tv_name = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
}
