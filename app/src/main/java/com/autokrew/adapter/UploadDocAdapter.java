package com.autokrew.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.autokrew.R;
import com.autokrew.interfaces.RecyclerViewDashBoardListener;
import com.autokrew.models.DashbordModel;
import com.autokrew.utils.CommonUtils;
import com.autokrew.utils.Constant;


public class UploadDocAdapter extends RecyclerView.Adapter<UploadDocAdapter.ViewHolder> {


    public DashbordModel feedItems;
    String TAG = "AnnouncementAdapter ::";
    Context ctx;
    private static RecyclerViewDashBoardListener itemListener;

    public UploadDocAdapter(Context ctx, DashbordModel feedItems, RecyclerViewDashBoardListener itemListener) {

        this.feedItems = feedItems;
        this.ctx=ctx;
        this.itemListener = itemListener;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_upload_doc_item, parent, false);
        UploadDocAdapter.ViewHolder viewHolder = new UploadDocAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {

        holder.tv_title.setText(Constant.mList.get(position).getDocument());
        if(Constant.mList.get(position).getName()==null){
            holder.tv_description.setText("- -");
        }
        else{
            holder.tv_description.setText(Constant.mList.get(position).getName());
        }

       /* if(Constant.mList.size()>0){
            for (int i = 0; i <Constant.mList.size() ; i++) {
                if(position == i){
                    Log.e(TAG, "onBindViewHolder >>>> "+Constant.mList.get(0).getName() );
                    holder.tv_description.setText(Constant.mList.get(0).getName());
                }
            }
        }*/


        holder.img_attach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //attach...
                itemListener.recyclerViewListClicked(v, position);


            }
        });
        holder.img_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //upload service calls
                if(holder.tv_description.getText().toString().equalsIgnoreCase("- -")){

                    CommonUtils.getInstance().displayToast(ctx,"No attachment!");
                }
                else{
                    itemListener.recyclerViewModuleClicked(v, position);


                }

            }
        });


    }

    @Override
    public int getItemCount() {
        //return feedItems.getTable12().size();

        return Constant.mList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title ,tv_description;
        ImageView img_attach,img_upload;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            this.tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            this.img_attach = (ImageView) itemView.findViewById(R.id.img_attach);
            this.img_upload = (ImageView) itemView.findViewById(R.id.img_upload);

        }
    }

/*    public  void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("MyAdapter", "onActivityResult");

        switch(requestCode){
            case PICKFILE_RESULT_CODE:
                if(resultCode==RESULT_OK){
                    String FilePath = data.getData().getPath();
                    Log.e("FilePath", "FilePath >> "+FilePath.substring(FilePath.lastIndexOf("/")+1));
                   // textFile.setText(FilePath);
                }
                break;

        }
    }*/
}
