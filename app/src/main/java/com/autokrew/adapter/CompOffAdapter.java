package com.autokrew.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.autokrew.R;
import com.autokrew.interfaces.RecyclerViewDashBoardListener;
import com.autokrew.models.CompoffLeaveModel;
import com.autokrew.models.Employee;
import com.autokrew.utils.Constant;

import java.util.ArrayList;
import java.util.List;


public class CompOffAdapter extends RecyclerView.Adapter<CompOffAdapter.ViewHolder> {



    public CompoffLeaveModel feedItems;

    String TAG = "BirthdayAdapter ::";
    Context ctx;
    private static RecyclerViewDashBoardListener itemListener;

    public ArrayList<CompoffLeaveModel.TableBean> mCheckList = new ArrayList<>();


    public CompOffAdapter(Context ctx, CompoffLeaveModel feedItems ,ArrayList<CompoffLeaveModel.TableBean> mCheckList) {

        this.feedItems = feedItems;
        this.ctx=ctx;
        this.mCheckList = mCheckList;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_compoff_item, parent, false);
        CompOffAdapter.ViewHolder viewHolder = new CompOffAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,int position) {

        holder.txt_date.setText(feedItems.getTable().get(position).getDate());
        holder.txt_balance.setText(String.valueOf(feedItems.getTable().get(position).getBalance()));

        //holder.chk_compoff.setChecked(Constant.mCheckList.get(position).getSelected());
       /* holder.chk_compoff.setTag(position);
        holder.chk_compoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer pos = (Integer) holder.chk_compoff.getTag();
                if (Constant.mCheckList.get(pos).getSelected()) {
                    Constant.mCheckList.get(pos).setSelected(false);
                } else {
                    Constant.mCheckList.get(pos).setSelected(true);
                }
            }
        });*/

        final int pos = position;
        CompoffLeaveModel.TableBean employee = mCheckList.get(position);
        holder.chk_compoff.setChecked(employee.isSelected());
        holder.chk_compoff.setTag(mCheckList.get(position));
        holder.chk_compoff.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                CompoffLeaveModel.TableBean emp = (CompoffLeaveModel.TableBean) cb.getTag();

                emp.setSelected(cb.isChecked());
                mCheckList.get(pos).setSelected(cb.isChecked());

                /*Toast.makeText(
                        v.getContext(),
                        "Selected Employees: " + cb.getText() + " is "
                                + cb.isChecked(), Toast.LENGTH_LONG).show();*/
            }
        });

    }

    @Override
    public int getItemCount() {

       // return 10;

       return mCheckList.size(); //today's birthday
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_date ,txt_balance;
        CheckBox chk_compoff;

        public ViewHolder(View itemView) {
            super(itemView);
            this.txt_date = (TextView)itemView.findViewById(R.id.txt_date);
            this.txt_balance = (TextView)itemView.findViewById(R.id.txt_balance);
            this.chk_compoff = (CheckBox)itemView.findViewById(R.id.chk_compoff);
        }
    }


    public List<CompoffLeaveModel.TableBean> getEmployeeList() {
        return mCheckList;
    }
}
