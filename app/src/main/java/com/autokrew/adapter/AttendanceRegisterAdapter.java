package com.autokrew.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.autokrew.R;
import com.autokrew.models.AttendanceRegisterModel;
import com.autokrew.models.AttendanceStatusModel;


public class AttendanceRegisterAdapter extends RecyclerView.Adapter<AttendanceRegisterAdapter.ViewHolder> implements
        Filterable {

    public AttendanceRegisterModel feedItems;
    Context ctx;

    public AttendanceRegisterAdapter(Context ctx, AttendanceRegisterModel feedItems) {

        this.feedItems = feedItems;
        this.ctx=ctx;
    }

   public Filter getFilter() {
       return  null;

      /*  return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();


                if (charString.isEmpty()) {

                    mFilteredList = feedItems;
                } else {

                    ArrayList<AttendanceModel> filteredList = new ArrayList<>();


                    for (AttendanceModel model : feedItems) {


                            if(model.getFirstname().toLowerCase().contains(charString.toLowerCase())){

                            filteredList.add(model);
                        }
                    }


                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                mFilteredList = (ArrayList<AttendanceModel>) filterResults.values;

                // Set on UI Thread
                ((Activity) ctx).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Notify the List that the DataSet has changed...
                        notifyDataSetChanged();
                    }
                });

            }
        };*/
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_name,txt_emp_code
        ,txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9,txt10,
        txt11,txt12,txt13,txt14,txt15,txt16,txt17,txt18,txt19,txt20,
        txt21,txt22,txt23,txt24,txt25,txt26,txt27,txt28,txt29,txt30,txt31
        ;

        public ViewHolder(View itemView) {
            super(itemView);
            this.txt_name = (TextView)itemView.findViewById(R.id.txt_name);
            this.txt_emp_code = (TextView)itemView.findViewById(R.id.txt_emp_code);

            this.txt1 = (TextView)itemView.findViewById(R.id.txt1);
            this.txt2 = (TextView)itemView.findViewById(R.id.txt2);
            this.txt3 = (TextView)itemView.findViewById(R.id.txt3);
            this.txt4 = (TextView)itemView.findViewById(R.id.txt4);
            this.txt5 = (TextView)itemView.findViewById(R.id.txt5);
            this.txt6 = (TextView)itemView.findViewById(R.id.txt6);
            this.txt7 = (TextView)itemView.findViewById(R.id.txt7);
            this.txt8 = (TextView)itemView.findViewById(R.id.txt8);
            this.txt9 = (TextView)itemView.findViewById(R.id.txt9);
            this.txt10 = (TextView)itemView.findViewById(R.id.txt10);

            this.txt11 = (TextView)itemView.findViewById(R.id.txt11);
            this.txt12 = (TextView)itemView.findViewById(R.id.txt12);
            this.txt13 = (TextView)itemView.findViewById(R.id.txt13);
            this.txt14 = (TextView)itemView.findViewById(R.id.txt14);
            this.txt15 = (TextView)itemView.findViewById(R.id.txt15);
            this.txt16 = (TextView)itemView.findViewById(R.id.txt16);
            this.txt17 = (TextView)itemView.findViewById(R.id.txt17);
            this.txt18 = (TextView)itemView.findViewById(R.id.txt18);
            this.txt19 = (TextView)itemView.findViewById(R.id.txt19);
            this.txt20 = (TextView)itemView.findViewById(R.id.txt20);

            //txt21,txt22,txt23,txt24,txt25,txt26,txt27,txt28,txt29,txt30,txt31
            this.txt21 = (TextView)itemView.findViewById(R.id.txt21);
            this.txt22 = (TextView)itemView.findViewById(R.id.txt22);
            this.txt23 = (TextView)itemView.findViewById(R.id.txt23);
            this.txt24 = (TextView)itemView.findViewById(R.id.txt24);
            this.txt25 = (TextView)itemView.findViewById(R.id.txt25);
            this.txt26 = (TextView)itemView.findViewById(R.id.txt26);
            this.txt27 = (TextView)itemView.findViewById(R.id.txt27);
            this.txt28 = (TextView)itemView.findViewById(R.id.txt28);
            this.txt29 = (TextView)itemView.findViewById(R.id.txt29);
            this.txt30 = (TextView)itemView.findViewById(R.id.txt30);

            this.txt31 = (TextView)itemView.findViewById(R.id.txt31);


        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_attendance_register_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {
        holder.txt_name.setText(feedItems.getTable().get(position).getName());
        holder.txt_emp_code.setText(feedItems.getTable().get(position).get_$EmployeeCode265());

        // ,txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9,txt10,
        holder.txt1.setText(feedItems.getTable().get(position).get_$159());
        if(feedItems.getTable().get(position).get_$159().equalsIgnoreCase("P")){
            holder.txt1.setBackgroundResource(R.drawable.bg_circular1);
            holder.txt1.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$159().equalsIgnoreCase("P2")){
            holder.txt1.setBackgroundResource(R.drawable.bg_circular2);
            holder.txt1.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$159().equalsIgnoreCase("A")){
            holder.txt1.setBackgroundResource(R.drawable.bg_circular3);
            holder.txt1.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$159().equalsIgnoreCase("L")){
            holder.txt1.setBackgroundResource(R.drawable.bg_circular4);
            holder.txt1.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$159().equalsIgnoreCase("H")){
            holder.txt1.setBackgroundResource(R.drawable.bg_circular5);
            holder.txt1.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$159().equalsIgnoreCase("WO")){
            holder.txt1.setBackgroundResource(R.drawable.bg_circular6);
            holder.txt1.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$159().equalsIgnoreCase("PL")){
            holder.txt1.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt1.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$159().equalsIgnoreCase("PE")){
            holder.txt1.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt1.setTextColor(Color.parseColor("#ffffff"));
        }
       
        holder.txt2.setText(feedItems.getTable().get(position).get_$2158());
        if(feedItems.getTable().get(position).get_$2158().equalsIgnoreCase("P")){
            holder.txt2.setBackgroundResource(R.drawable.bg_circular1);
            holder.txt2.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$2158().equalsIgnoreCase("P2")){
            holder.txt2.setBackgroundResource(R.drawable.bg_circular2);
            holder.txt2.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$2158().equalsIgnoreCase("A")){
            holder.txt2.setBackgroundResource(R.drawable.bg_circular3);
            holder.txt2.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$2158().equalsIgnoreCase("L")){
            holder.txt2.setBackgroundResource(R.drawable.bg_circular4);
            holder.txt2.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$2158().equalsIgnoreCase("H")){
            holder.txt2.setBackgroundResource(R.drawable.bg_circular5);
            holder.txt2.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$2158().equalsIgnoreCase("WO")){
            holder.txt2.setBackgroundResource(R.drawable.bg_circular6);
            holder.txt2.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$2158().equalsIgnoreCase("PL")){
            holder.txt2.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt2.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$2158().equalsIgnoreCase("PE")){
            holder.txt2.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt2.setTextColor(Color.parseColor("#ffffff"));
        }
        holder.txt3.setText(feedItems.getTable().get(position).get_$374());
        if(feedItems.getTable().get(position).get_$374().equalsIgnoreCase("P")){
            holder.txt3.setBackgroundResource(R.drawable.bg_circular1);
            holder.txt3.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$374().equalsIgnoreCase("P2")){
            holder.txt3.setBackgroundResource(R.drawable.bg_circular2);
            holder.txt3.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$374().equalsIgnoreCase("A")){
            holder.txt3.setBackgroundResource(R.drawable.bg_circular3);
            holder.txt3.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$374().equalsIgnoreCase("L")){
            holder.txt3.setBackgroundResource(R.drawable.bg_circular4);
            holder.txt3.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$374().equalsIgnoreCase("H")){
            holder.txt3.setBackgroundResource(R.drawable.bg_circular5);
            holder.txt3.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$374().equalsIgnoreCase("WO")){
            holder.txt3.setBackgroundResource(R.drawable.bg_circular6);
            holder.txt3.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$374().equalsIgnoreCase("PL")){
            holder.txt3.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt3.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$374().equalsIgnoreCase("PE")){
            holder.txt3.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt3.setTextColor(Color.parseColor("#ffffff"));
        }

        holder.txt4.setText(feedItems.getTable().get(position).get_$4250());
        if(feedItems.getTable().get(position).get_$4250().equalsIgnoreCase("P")){
            holder.txt4.setBackgroundResource(R.drawable.bg_circular1);
            holder.txt4.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$4250().equalsIgnoreCase("P2")){
            holder.txt4.setBackgroundResource(R.drawable.bg_circular2);
            holder.txt4.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$4250().equalsIgnoreCase("A")){
            holder.txt4.setBackgroundResource(R.drawable.bg_circular3);
            holder.txt4.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$4250().equalsIgnoreCase("L")){
            holder.txt4.setBackgroundResource(R.drawable.bg_circular4);
            holder.txt4.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$4250().equalsIgnoreCase("H")){
            holder.txt4.setBackgroundResource(R.drawable.bg_circular5);
            holder.txt4.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$4250().equalsIgnoreCase("WO")){
            holder.txt4.setBackgroundResource(R.drawable.bg_circular6);
            holder.txt4.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$4250().equalsIgnoreCase("PL")){
            holder.txt4.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt4.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$4250().equalsIgnoreCase("PE")){
            holder.txt4.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt4.setTextColor(Color.parseColor("#ffffff"));
        }
        holder.txt5.setText(feedItems.getTable().get(position).get_$5247());
        if(feedItems.getTable().get(position).get_$5247().equalsIgnoreCase("P")){
            holder.txt5.setBackgroundResource(R.drawable.bg_circular1);
            holder.txt5.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$5247().equalsIgnoreCase("P2")){
            holder.txt5.setBackgroundResource(R.drawable.bg_circular2);
            holder.txt5.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$5247().equalsIgnoreCase("A")){
            holder.txt5.setBackgroundResource(R.drawable.bg_circular3);
            holder.txt5.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$5247().equalsIgnoreCase("L")){
            holder.txt5.setBackgroundResource(R.drawable.bg_circular4);
            holder.txt5.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$5247().equalsIgnoreCase("H")){
            holder.txt5.setBackgroundResource(R.drawable.bg_circular5);
            holder.txt5.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$5247().equalsIgnoreCase("WO")){
            holder.txt5.setBackgroundResource(R.drawable.bg_circular6);
            holder.txt5.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$5247().equalsIgnoreCase("PL")){
            holder.txt5.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt5.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$5247().equalsIgnoreCase("PE")){
            holder.txt5.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt5.setTextColor(Color.parseColor("#ffffff"));
        }


        holder.txt6.setText(feedItems.getTable().get(position).get_$6175());

        if(feedItems.getTable().get(position).get_$6175().equalsIgnoreCase("P")){
            holder.txt6.setBackgroundResource(R.drawable.bg_circular1);
            holder.txt6.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$6175().equalsIgnoreCase("P2")){
            holder.txt6.setBackgroundResource(R.drawable.bg_circular2);
            holder.txt6.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$6175().equalsIgnoreCase("A")){
            holder.txt6.setBackgroundResource(R.drawable.bg_circular3);
            holder.txt6.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$6175().equalsIgnoreCase("L")){
            holder.txt6.setBackgroundResource(R.drawable.bg_circular4);
            holder.txt6.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$6175().equalsIgnoreCase("H")){
            holder.txt6.setBackgroundResource(R.drawable.bg_circular5);
            holder.txt6.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$6175().equalsIgnoreCase("WO")){
            holder.txt6.setBackgroundResource(R.drawable.bg_circular6);
            holder.txt6.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$6175().equalsIgnoreCase("PL")){
            holder.txt6.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt6.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$6175().equalsIgnoreCase("PE")){
            holder.txt6.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt6.setTextColor(Color.parseColor("#ffffff"));
        }
        holder.txt7.setText(feedItems.getTable().get(position).get_$7295());
        if(feedItems.getTable().get(position).get_$7295().equalsIgnoreCase("P")){
            holder.txt7.setBackgroundResource(R.drawable.bg_circular1);
            holder.txt7.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$7295().equalsIgnoreCase("P2")){
            holder.txt7.setBackgroundResource(R.drawable.bg_circular2);
            holder.txt7.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$7295().equalsIgnoreCase("A")){
            holder.txt7.setBackgroundResource(R.drawable.bg_circular3);
            holder.txt7.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$7295().equalsIgnoreCase("L")){
            holder.txt7.setBackgroundResource(R.drawable.bg_circular4);
            holder.txt7.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$7295().equalsIgnoreCase("H")){
            holder.txt7.setBackgroundResource(R.drawable.bg_circular5);
            holder.txt7.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$7295().equalsIgnoreCase("WO")){
            holder.txt7.setBackgroundResource(R.drawable.bg_circular6);
            holder.txt7.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$7295().equalsIgnoreCase("PL")){
            holder.txt7.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt7.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$7295().equalsIgnoreCase("PE")){
            holder.txt7.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt7.setTextColor(Color.parseColor("#ffffff"));
        }


        holder.txt8.setText(feedItems.getTable().get(position).get_$8305());
        if(feedItems.getTable().get(position).get_$8305().equalsIgnoreCase("P")){
            holder.txt8.setBackgroundResource(R.drawable.bg_circular1);
            holder.txt8.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$8305().equalsIgnoreCase("P2")){
            holder.txt8.setBackgroundResource(R.drawable.bg_circular2);
            holder.txt8.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$8305().equalsIgnoreCase("A")){
            holder.txt8.setBackgroundResource(R.drawable.bg_circular3);
            holder.txt8.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$8305().equalsIgnoreCase("L")){
            holder.txt8.setBackgroundResource(R.drawable.bg_circular4);
            holder.txt8.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$8305().equalsIgnoreCase("H")){
            holder.txt8.setBackgroundResource(R.drawable.bg_circular5);
            holder.txt8.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$8305().equalsIgnoreCase("WO")){
            holder.txt8.setBackgroundResource(R.drawable.bg_circular6);
            holder.txt8.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$8305().equalsIgnoreCase("PL")){
            holder.txt8.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt8.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$8305().equalsIgnoreCase("PE")){
            holder.txt8.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt8.setTextColor(Color.parseColor("#ffffff"));
        }
        holder.txt9.setText(feedItems.getTable().get(position).get_$9104());
        if(feedItems.getTable().get(position).get_$9104().equalsIgnoreCase("P")){
            holder.txt9.setBackgroundResource(R.drawable.bg_circular1);
            holder.txt9.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$9104().equalsIgnoreCase("P2")){
            holder.txt9.setBackgroundResource(R.drawable.bg_circular2);
            holder.txt9.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$9104().equalsIgnoreCase("A")){
            holder.txt9.setBackgroundResource(R.drawable.bg_circular3);
            holder.txt9.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$9104().equalsIgnoreCase("L")){
            holder.txt9.setBackgroundResource(R.drawable.bg_circular4);
            holder.txt9.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$9104().equalsIgnoreCase("H")){
            holder.txt9.setBackgroundResource(R.drawable.bg_circular5);
            holder.txt9.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$9104().equalsIgnoreCase("WO")){
            holder.txt9.setBackgroundResource(R.drawable.bg_circular6);
            holder.txt9.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$9104().equalsIgnoreCase("PL")){
            holder.txt9.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt9.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$9104().equalsIgnoreCase("PE")){
            holder.txt9.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt9.setTextColor(Color.parseColor("#ffffff"));
        }

        holder.txt10.setText(feedItems.getTable().get(position).get_$1071());
        if(feedItems.getTable().get(position).get_$1071().equalsIgnoreCase("P")){
            holder.txt10.setBackgroundResource(R.drawable.bg_circular1);
            holder.txt10.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$1071().equalsIgnoreCase("P2")){
            holder.txt10.setBackgroundResource(R.drawable.bg_circular2);
            holder.txt10.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$1071().equalsIgnoreCase("A")){
            holder.txt10.setBackgroundResource(R.drawable.bg_circular3);
            holder.txt10.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$1071().equalsIgnoreCase("L")){
            holder.txt10.setBackgroundResource(R.drawable.bg_circular4);
            holder.txt10.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$1071().equalsIgnoreCase("H")){
            holder.txt10.setBackgroundResource(R.drawable.bg_circular5);
            holder.txt10.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$1071().equalsIgnoreCase("WO")){
            holder.txt10.setBackgroundResource(R.drawable.bg_circular6);
            holder.txt10.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$1071().equalsIgnoreCase("PL")){
            holder.txt10.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt10.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$1071().equalsIgnoreCase("PE")){
            holder.txt10.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt10.setTextColor(Color.parseColor("#ffffff"));
        }

//------------------------------



        holder.txt11.setText(feedItems.getTable().get(position).get_$11299());
        if(feedItems.getTable().get(position).get_$11299().equalsIgnoreCase("P")){
            holder.txt11.setBackgroundResource(R.drawable.bg_circular1);
            holder.txt11.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$11299().equalsIgnoreCase("P2")){
            holder.txt11.setBackgroundResource(R.drawable.bg_circular2);
            holder.txt11.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$11299().equalsIgnoreCase("A")){
            holder.txt11.setBackgroundResource(R.drawable.bg_circular3);
            holder.txt11.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$11299().equalsIgnoreCase("L")){
            holder.txt11.setBackgroundResource(R.drawable.bg_circular4);
            holder.txt11.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$11299().equalsIgnoreCase("H")){
            holder.txt11.setBackgroundResource(R.drawable.bg_circular5);
            holder.txt11.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$11299().equalsIgnoreCase("WO")){
            holder.txt11.setBackgroundResource(R.drawable.bg_circular6);
            holder.txt11.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$11299().equalsIgnoreCase("PL")){
            holder.txt11.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt11.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$11299().equalsIgnoreCase("PE")){
            holder.txt11.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt11.setTextColor(Color.parseColor("#ffffff"));
        }



        holder.txt12.setText(feedItems.getTable().get(position).get_$12309());
        if(feedItems.getTable().get(position).get_$12309().equalsIgnoreCase("P")){
            holder.txt12.setBackgroundResource(R.drawable.bg_circular1);
            holder.txt12.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$12309().equalsIgnoreCase("P2")){
            holder.txt12.setBackgroundResource(R.drawable.bg_circular2);
            holder.txt12.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$12309().equalsIgnoreCase("A")){
            holder.txt12.setBackgroundResource(R.drawable.bg_circular3);
            holder.txt12.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$12309().equalsIgnoreCase("L")){
            holder.txt12.setBackgroundResource(R.drawable.bg_circular4);
            holder.txt12.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$12309().equalsIgnoreCase("H")){
            holder.txt12.setBackgroundResource(R.drawable.bg_circular5);
            holder.txt12.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$12309().equalsIgnoreCase("WO")){
            holder.txt12.setBackgroundResource(R.drawable.bg_circular6);
            holder.txt12.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$12309().equalsIgnoreCase("PL")){
            holder.txt12.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt12.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$12309().equalsIgnoreCase("PE")){
            holder.txt12.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt12.setTextColor(Color.parseColor("#ffffff"));
        }
        holder.txt13.setText(feedItems.getTable().get(position).get_$13182());
        if(feedItems.getTable().get(position).get_$13182().equalsIgnoreCase("P")){
            holder.txt13.setBackgroundResource(R.drawable.bg_circular1);
            holder.txt13.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$13182().equalsIgnoreCase("P2")){
            holder.txt13.setBackgroundResource(R.drawable.bg_circular2);
            holder.txt13.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$13182().equalsIgnoreCase("A")){
            holder.txt13.setBackgroundResource(R.drawable.bg_circular3);
            holder.txt13.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$13182().equalsIgnoreCase("L")){
            holder.txt13.setBackgroundResource(R.drawable.bg_circular4);
            holder.txt13.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$13182().equalsIgnoreCase("H")){
            holder.txt13.setBackgroundResource(R.drawable.bg_circular5);
            holder.txt13.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$13182().equalsIgnoreCase("WO")){
            holder.txt13.setBackgroundResource(R.drawable.bg_circular6);
            holder.txt13.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$13182().equalsIgnoreCase("PL")){
            holder.txt13.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt13.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$13182().equalsIgnoreCase("PE")){
            holder.txt13.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt13.setTextColor(Color.parseColor("#ffffff"));
        }
        holder.txt14.setText(feedItems.getTable().get(position).get_$14219());
        if(feedItems.getTable().get(position).get_$14219().equalsIgnoreCase("P")){
            holder.txt14.setBackgroundResource(R.drawable.bg_circular1);
            holder.txt14.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$14219().equalsIgnoreCase("P2")){
            holder.txt14.setBackgroundResource(R.drawable.bg_circular2);
            holder.txt14.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$14219().equalsIgnoreCase("A")){
            holder.txt14.setBackgroundResource(R.drawable.bg_circular3);
            holder.txt14.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$14219().equalsIgnoreCase("L")){
            holder.txt14.setBackgroundResource(R.drawable.bg_circular4);
            holder.txt14.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$14219().equalsIgnoreCase("H")){
            holder.txt14.setBackgroundResource(R.drawable.bg_circular5);
            holder.txt14.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$14219().equalsIgnoreCase("WO")){
            holder.txt14.setBackgroundResource(R.drawable.bg_circular6);
            holder.txt14.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$14219().equalsIgnoreCase("PL")){
            holder.txt14.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt14.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$14219().equalsIgnoreCase("PE")){
            holder.txt14.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt14.setTextColor(Color.parseColor("#ffffff"));
        }
        holder.txt15.setText(feedItems.getTable().get(position).get_$15121());
        if(feedItems.getTable().get(position).get_$15121().equalsIgnoreCase("P")){
            holder.txt15.setBackgroundResource(R.drawable.bg_circular1);
            holder.txt15.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$15121().equalsIgnoreCase("P2")){
            holder.txt15.setBackgroundResource(R.drawable.bg_circular2);
            holder.txt15.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$15121().equalsIgnoreCase("A")){
            holder.txt15.setBackgroundResource(R.drawable.bg_circular3);
            holder.txt15.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$15121().equalsIgnoreCase("L")){
            holder.txt15.setBackgroundResource(R.drawable.bg_circular4);
            holder.txt15.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$15121().equalsIgnoreCase("H")){
            holder.txt15.setBackgroundResource(R.drawable.bg_circular5);
            holder.txt15.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$15121().equalsIgnoreCase("WO")){
            holder.txt15.setBackgroundResource(R.drawable.bg_circular6);
            holder.txt15.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$15121().equalsIgnoreCase("PL")){
            holder.txt15.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt15.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$15121().equalsIgnoreCase("PE")){
            holder.txt15.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt15.setTextColor(Color.parseColor("#ffffff"));
        }

        holder.txt16.setText(feedItems.getTable().get(position).get_$16159());
        if(feedItems.getTable().get(position).get_$16159().equalsIgnoreCase("P")){
            holder.txt16.setBackgroundResource(R.drawable.bg_circular1);
            holder.txt16.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$16159().equalsIgnoreCase("P2")){
            holder.txt16.setBackgroundResource(R.drawable.bg_circular2);
            holder.txt16.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$16159().equalsIgnoreCase("A")){
            holder.txt16.setBackgroundResource(R.drawable.bg_circular3);
            holder.txt16.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$16159().equalsIgnoreCase("L")){
            holder.txt16.setBackgroundResource(R.drawable.bg_circular4);
            holder.txt16.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$16159().equalsIgnoreCase("H")){
            holder.txt16.setBackgroundResource(R.drawable.bg_circular5);
            holder.txt16.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$16159().equalsIgnoreCase("WO")){
            holder.txt16.setBackgroundResource(R.drawable.bg_circular6);
            holder.txt16.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$16159().equalsIgnoreCase("PL")){
            holder.txt16.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt16.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$16159().equalsIgnoreCase("PE")){
            holder.txt16.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt16.setTextColor(Color.parseColor("#ffffff"));
        }

        holder.txt17.setText(feedItems.getTable().get(position).get_$17153());
        if(feedItems.getTable().get(position).get_$17153().equalsIgnoreCase("P")){
            holder.txt17.setBackgroundResource(R.drawable.bg_circular1);
            holder.txt17.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$17153().equalsIgnoreCase("P2")){
            holder.txt17.setBackgroundResource(R.drawable.bg_circular2);
            holder.txt17.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$17153().equalsIgnoreCase("A")){
            holder.txt17.setBackgroundResource(R.drawable.bg_circular3);
            holder.txt17.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$17153().equalsIgnoreCase("L")){
            holder.txt17.setBackgroundResource(R.drawable.bg_circular4);
            holder.txt17.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$17153().equalsIgnoreCase("H")){
            holder.txt17.setBackgroundResource(R.drawable.bg_circular5);
            holder.txt17.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$17153().equalsIgnoreCase("WO")){
            holder.txt17.setBackgroundResource(R.drawable.bg_circular6);
            holder.txt17.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$17153().equalsIgnoreCase("PL")){
            holder.txt17.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt17.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$17153().equalsIgnoreCase("PE")){
            holder.txt17.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt17.setTextColor(Color.parseColor("#ffffff"));
        }


        holder.txt18.setText(feedItems.getTable().get(position).get_$1839());
        if(feedItems.getTable().get(position).get_$1839().equalsIgnoreCase("P")){
            holder.txt18.setBackgroundResource(R.drawable.bg_circular1);
            holder.txt18.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$1839().equalsIgnoreCase("P2")){
            holder.txt18.setBackgroundResource(R.drawable.bg_circular2);
            holder.txt18.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$1839().equalsIgnoreCase("A")){
            holder.txt18.setBackgroundResource(R.drawable.bg_circular3);
            holder.txt18.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$1839().equalsIgnoreCase("L")){
            holder.txt18.setBackgroundResource(R.drawable.bg_circular4);
            holder.txt18.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$1839().equalsIgnoreCase("H")){
            holder.txt18.setBackgroundResource(R.drawable.bg_circular5);
            holder.txt18.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$1839().equalsIgnoreCase("WO")){
            holder.txt18.setBackgroundResource(R.drawable.bg_circular6);
            holder.txt18.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$1839().equalsIgnoreCase("PL")){
            holder.txt18.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt18.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$1839().equalsIgnoreCase("PE")){
            holder.txt18.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt18.setTextColor(Color.parseColor("#ffffff"));
        }


        holder.txt19.setText(feedItems.getTable().get(position).get_$19155());
        if(feedItems.getTable().get(position).get_$19155().equalsIgnoreCase("P")){
            holder.txt19.setBackgroundResource(R.drawable.bg_circular1);
            holder.txt19.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$19155().equalsIgnoreCase("P2")){
            holder.txt19.setBackgroundResource(R.drawable.bg_circular2);
            holder.txt19.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$19155().equalsIgnoreCase("A")){
            holder.txt19.setBackgroundResource(R.drawable.bg_circular3);
            holder.txt19.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$19155().equalsIgnoreCase("L")){
            holder.txt19.setBackgroundResource(R.drawable.bg_circular4);
            holder.txt19.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$19155().equalsIgnoreCase("H")){
            holder.txt19.setBackgroundResource(R.drawable.bg_circular5);
            holder.txt19.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$19155().equalsIgnoreCase("WO")){
            holder.txt19.setBackgroundResource(R.drawable.bg_circular6);
            holder.txt19.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$19155().equalsIgnoreCase("PL")){
            holder.txt19.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt19.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$19155().equalsIgnoreCase("PE")){
            holder.txt19.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt19.setTextColor(Color.parseColor("#ffffff"));
        }
        holder.txt20.setText(feedItems.getTable().get(position).get_$2058());
        if(feedItems.getTable().get(position).get_$2058().equalsIgnoreCase("P")){
            holder.txt20.setBackgroundResource(R.drawable.bg_circular1);
            holder.txt20.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$2058().equalsIgnoreCase("P2")){
            holder.txt20.setBackgroundResource(R.drawable.bg_circular2);
            holder.txt20.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$2058().equalsIgnoreCase("A")){
            holder.txt20.setBackgroundResource(R.drawable.bg_circular3);
            holder.txt20.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$2058().equalsIgnoreCase("L")){
            holder.txt20.setBackgroundResource(R.drawable.bg_circular4);
            holder.txt20.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$2058().equalsIgnoreCase("H")){
            holder.txt20.setBackgroundResource(R.drawable.bg_circular5);
            holder.txt20.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$2058().equalsIgnoreCase("WO")){
            holder.txt20.setBackgroundResource(R.drawable.bg_circular6);
            holder.txt20.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$2058().equalsIgnoreCase("PL")){
            holder.txt20.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt20.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$2058().equalsIgnoreCase("PE")){
            holder.txt20.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt20.setTextColor(Color.parseColor("#ffffff"));
        }

        holder.txt21.setText(feedItems.getTable().get(position).get_$21165());
        if(feedItems.getTable().get(position).get_$21165().equalsIgnoreCase("P")){
            holder.txt21.setBackgroundResource(R.drawable.bg_circular1);
            holder.txt21.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$21165().equalsIgnoreCase("P2")){
            holder.txt21.setBackgroundResource(R.drawable.bg_circular2);
            holder.txt21.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$21165().equalsIgnoreCase("A")){
            holder.txt21.setBackgroundResource(R.drawable.bg_circular3);
            holder.txt21.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$21165().equalsIgnoreCase("L")){
            holder.txt21.setBackgroundResource(R.drawable.bg_circular4);
            holder.txt21.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$21165().equalsIgnoreCase("H")){
            holder.txt21.setBackgroundResource(R.drawable.bg_circular5);
            holder.txt21.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$21165().equalsIgnoreCase("WO")){
            holder.txt21.setBackgroundResource(R.drawable.bg_circular6);
            holder.txt21.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$21165().equalsIgnoreCase("PL")){
            holder.txt21.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt21.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$21165().equalsIgnoreCase("PE")){
            holder.txt21.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt21.setTextColor(Color.parseColor("#ffffff"));
        }
        holder.txt22.setText(feedItems.getTable().get(position).get_$22205());
        if(feedItems.getTable().get(position).get_$22205().equalsIgnoreCase("P")){
            holder.txt22.setBackgroundResource(R.drawable.bg_circular1);
            holder.txt22.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$22205().equalsIgnoreCase("P2")){
            holder.txt22.setBackgroundResource(R.drawable.bg_circular2);
            holder.txt22.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$22205().equalsIgnoreCase("A")){
            holder.txt22.setBackgroundResource(R.drawable.bg_circular3);
            holder.txt22.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$22205().equalsIgnoreCase("L")){
            holder.txt22.setBackgroundResource(R.drawable.bg_circular4);
            holder.txt22.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$22205().equalsIgnoreCase("H")){
            holder.txt22.setBackgroundResource(R.drawable.bg_circular5);
            holder.txt22.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$22205().equalsIgnoreCase("WO")){
            holder.txt22.setBackgroundResource(R.drawable.bg_circular6);
            holder.txt22.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$22205().equalsIgnoreCase("PL")){
            holder.txt22.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt22.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$22205().equalsIgnoreCase("PE")){
            holder.txt22.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt22.setTextColor(Color.parseColor("#ffffff"));
        }
        holder.txt23.setText(feedItems.getTable().get(position).get_$23215());
        if(feedItems.getTable().get(position).get_$23215().equalsIgnoreCase("P")){
            holder.txt23.setBackgroundResource(R.drawable.bg_circular1);
            holder.txt23.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$23215().equalsIgnoreCase("P2")){
            holder.txt23.setBackgroundResource(R.drawable.bg_circular2);
            holder.txt23.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$23215().equalsIgnoreCase("A")){
            holder.txt23.setBackgroundResource(R.drawable.bg_circular3);
            holder.txt23.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$23215().equalsIgnoreCase("L")){
            holder.txt23.setBackgroundResource(R.drawable.bg_circular4);
            holder.txt23.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$23215().equalsIgnoreCase("H")){
            holder.txt23.setBackgroundResource(R.drawable.bg_circular5);
            holder.txt23.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$23215().equalsIgnoreCase("WO")){
            holder.txt23.setBackgroundResource(R.drawable.bg_circular6);
            holder.txt23.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$23215().equalsIgnoreCase("PL")){
            holder.txt23.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt23.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$23215().equalsIgnoreCase("PE")){
            holder.txt23.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt23.setTextColor(Color.parseColor("#ffffff"));
        }
        holder.txt24.setText(feedItems.getTable().get(position).get_$24326());
        if(feedItems.getTable().get(position).get_$24326().equalsIgnoreCase("P")){
            holder.txt24.setBackgroundResource(R.drawable.bg_circular1);
            holder.txt24.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$24326().equalsIgnoreCase("P2")){
            holder.txt24.setBackgroundResource(R.drawable.bg_circular2);
            holder.txt24.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$24326().equalsIgnoreCase("A")){
            holder.txt24.setBackgroundResource(R.drawable.bg_circular3);
            holder.txt24.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$24326().equalsIgnoreCase("L")){
            holder.txt24.setBackgroundResource(R.drawable.bg_circular4);
            holder.txt24.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$24326().equalsIgnoreCase("H")){
            holder.txt24.setBackgroundResource(R.drawable.bg_circular5);
            holder.txt24.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$24326().equalsIgnoreCase("WO")){
            holder.txt24.setBackgroundResource(R.drawable.bg_circular6);
            holder.txt24.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$24326().equalsIgnoreCase("PL")){
            holder.txt24.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt24.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$24326().equalsIgnoreCase("PE")){
            holder.txt24.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt24.setTextColor(Color.parseColor("#ffffff"));
        }

        holder.txt25.setText(feedItems.getTable().get(position).get_$25241());
        if(feedItems.getTable().get(position).get_$25241().equalsIgnoreCase("P")){
            holder.txt25.setBackgroundResource(R.drawable.bg_circular1);
            holder.txt25.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$25241().equalsIgnoreCase("P2")){
            holder.txt25.setBackgroundResource(R.drawable.bg_circular2);
            holder.txt25.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$25241().equalsIgnoreCase("A")){
            holder.txt25.setBackgroundResource(R.drawable.bg_circular3);
            holder.txt25.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$25241().equalsIgnoreCase("L")){
            holder.txt25.setBackgroundResource(R.drawable.bg_circular4);
            holder.txt25.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$25241().equalsIgnoreCase("H")){
            holder.txt25.setBackgroundResource(R.drawable.bg_circular5);
            holder.txt25.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$25241().equalsIgnoreCase("WO")){
            holder.txt25.setBackgroundResource(R.drawable.bg_circular6);
            holder.txt25.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$25241().equalsIgnoreCase("PL")){
            holder.txt25.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt25.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$25241().equalsIgnoreCase("PE")){
            holder.txt25.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt25.setTextColor(Color.parseColor("#ffffff"));
        }

        holder.txt26.setText(feedItems.getTable().get(position).get_$26281());
        if(feedItems.getTable().get(position).get_$26281().equalsIgnoreCase("P")){
            holder.txt26.setBackgroundResource(R.drawable.bg_circular1);
            holder.txt26.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$26281().equalsIgnoreCase("P2")){
            holder.txt26.setBackgroundResource(R.drawable.bg_circular2);
            holder.txt26.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$26281().equalsIgnoreCase("A")){
            holder.txt26.setBackgroundResource(R.drawable.bg_circular3);
            holder.txt26.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$26281().equalsIgnoreCase("L")){
            holder.txt26.setBackgroundResource(R.drawable.bg_circular4);
            holder.txt26.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$26281().equalsIgnoreCase("H")){
            holder.txt26.setBackgroundResource(R.drawable.bg_circular5);
            holder.txt26.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$26281().equalsIgnoreCase("WO")){
            holder.txt26.setBackgroundResource(R.drawable.bg_circular6);
            holder.txt26.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$26281().equalsIgnoreCase("PL")){
            holder.txt26.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt26.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$26281().equalsIgnoreCase("PE")){
            holder.txt26.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt26.setTextColor(Color.parseColor("#ffffff"));
        }


        holder.txt27.setText(feedItems.getTable().get(position).get_$27275());
        if(feedItems.getTable().get(position).get_$27275().equalsIgnoreCase("P")){
            holder.txt27.setBackgroundResource(R.drawable.bg_circular1);
            holder.txt27.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$27275().equalsIgnoreCase("P2")){
            holder.txt27.setBackgroundResource(R.drawable.bg_circular2);
            holder.txt27.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$27275().equalsIgnoreCase("A")){
            holder.txt27.setBackgroundResource(R.drawable.bg_circular3);
            holder.txt27.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$27275().equalsIgnoreCase("L")){
            holder.txt27.setBackgroundResource(R.drawable.bg_circular4);
            holder.txt27.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$27275().equalsIgnoreCase("H")){
            holder.txt27.setBackgroundResource(R.drawable.bg_circular5);
            holder.txt27.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$27275().equalsIgnoreCase("WO")){
            holder.txt27.setBackgroundResource(R.drawable.bg_circular6);
            holder.txt27.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$27275().equalsIgnoreCase("PL")){
            holder.txt27.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt27.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$27275().equalsIgnoreCase("PE")){
            holder.txt27.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt27.setTextColor(Color.parseColor("#ffffff"));
        }



        holder.txt28.setText(feedItems.getTable().get(position).get_$2838());
        if(feedItems.getTable().get(position).get_$2838().equalsIgnoreCase("P")){
            holder.txt28.setBackgroundResource(R.drawable.bg_circular1);
            holder.txt28.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$2838().equalsIgnoreCase("P2")){
            holder.txt28.setBackgroundResource(R.drawable.bg_circular2);
            holder.txt28.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$2838().equalsIgnoreCase("A")){
            holder.txt28.setBackgroundResource(R.drawable.bg_circular3);
            holder.txt28.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$2838().equalsIgnoreCase("L")){
            holder.txt28.setBackgroundResource(R.drawable.bg_circular4);
            holder.txt28.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$2838().equalsIgnoreCase("H")){
            holder.txt28.setBackgroundResource(R.drawable.bg_circular5);
            holder.txt28.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$2838().equalsIgnoreCase("WO")){
            holder.txt28.setBackgroundResource(R.drawable.bg_circular6);
            holder.txt28.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$2838().equalsIgnoreCase("PL")){
            holder.txt28.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt28.setTextColor(Color.parseColor("#ffffff"));
        }else if(feedItems.getTable().get(position).get_$2838().equalsIgnoreCase("PE")){
            holder.txt28.setBackgroundResource(R.drawable.bg_circular7);
            holder.txt28.setTextColor(Color.parseColor("#ffffff"));
        }

        //check for 29th ...
        if(feedItems.getTable().get(position).get_$29112()!=null){
            holder.txt29.setText(feedItems.getTable().get(position).get_$29112());
            if(feedItems.getTable().get(position).get_$29112().equalsIgnoreCase("P")){
                holder.txt29.setBackgroundResource(R.drawable.bg_circular1);
                holder.txt29.setTextColor(Color.parseColor("#ffffff"));
            }else if(feedItems.getTable().get(position).get_$29112().equalsIgnoreCase("P2")){
                holder.txt29.setBackgroundResource(R.drawable.bg_circular2);
                holder.txt29.setTextColor(Color.parseColor("#ffffff"));
            }else if(feedItems.getTable().get(position).get_$29112().equalsIgnoreCase("A")){
                holder.txt29.setBackgroundResource(R.drawable.bg_circular3);
                holder.txt29.setTextColor(Color.parseColor("#ffffff"));
            }else if(feedItems.getTable().get(position).get_$29112().equalsIgnoreCase("L")){
                holder.txt29.setBackgroundResource(R.drawable.bg_circular4);
                holder.txt29.setTextColor(Color.parseColor("#ffffff"));
            }else if(feedItems.getTable().get(position).get_$29112().equalsIgnoreCase("H")){
                holder.txt29.setBackgroundResource(R.drawable.bg_circular5);
                holder.txt29.setTextColor(Color.parseColor("#ffffff"));
            }else if(feedItems.getTable().get(position).get_$29112().equalsIgnoreCase("WO")){
                holder.txt29.setBackgroundResource(R.drawable.bg_circular6);
                holder.txt29.setTextColor(Color.parseColor("#ffffff"));
            }else if(feedItems.getTable().get(position).get_$29112().equalsIgnoreCase("PL")){
                holder.txt29.setBackgroundResource(R.drawable.bg_circular7);
                holder.txt29.setTextColor(Color.parseColor("#ffffff"));
            }else if(feedItems.getTable().get(position).get_$29112().equalsIgnoreCase("PE")){
                holder.txt29.setBackgroundResource(R.drawable.bg_circular7);
                holder.txt29.setTextColor(Color.parseColor("#ffffff"));
            }
        }



        //check for 30th
        if(feedItems.getTable().get(position).get_$30193()!=null){
            holder.txt30.setText(feedItems.getTable().get(position).get_$30193());
            if(feedItems.getTable().get(position).get_$30193().equalsIgnoreCase("P")){
                holder.txt30.setBackgroundResource(R.drawable.bg_circular1);
                holder.txt30.setTextColor(Color.parseColor("#ffffff"));
            }else if(feedItems.getTable().get(position).get_$30193().equalsIgnoreCase("P2")){
                holder.txt30.setBackgroundResource(R.drawable.bg_circular2);
                holder.txt30.setTextColor(Color.parseColor("#ffffff"));
            }else if(feedItems.getTable().get(position).get_$30193().equalsIgnoreCase("A")){
                holder.txt30.setBackgroundResource(R.drawable.bg_circular3);
                holder.txt30.setTextColor(Color.parseColor("#ffffff"));
            }else if(feedItems.getTable().get(position).get_$30193().equalsIgnoreCase("L")){
                holder.txt30.setBackgroundResource(R.drawable.bg_circular4);
                holder.txt30.setTextColor(Color.parseColor("#ffffff"));
            }else if(feedItems.getTable().get(position).get_$30193().equalsIgnoreCase("H")){
                holder.txt30.setBackgroundResource(R.drawable.bg_circular5);
                holder.txt30.setTextColor(Color.parseColor("#ffffff"));
            }else if(feedItems.getTable().get(position).get_$30193().equalsIgnoreCase("WO")){
                holder.txt30.setBackgroundResource(R.drawable.bg_circular6);
                holder.txt30.setTextColor(Color.parseColor("#ffffff"));
            }else if(feedItems.getTable().get(position).get_$30193().equalsIgnoreCase("PL")){
                holder.txt30.setBackgroundResource(R.drawable.bg_circular7);
                holder.txt30.setTextColor(Color.parseColor("#ffffff"));
            }else if(feedItems.getTable().get(position).get_$30193().equalsIgnoreCase("PE")){
                holder.txt30.setBackgroundResource(R.drawable.bg_circular7);
                holder.txt30.setTextColor(Color.parseColor("#ffffff"));
            }

        }


        //check for 31st
        if(feedItems.getTable().get(position).get_$31194()!=null){

            holder.txt31.setText(feedItems.getTable().get(position).get_$31194());
            if(feedItems.getTable().get(position).get_$31194().equalsIgnoreCase("P")){
                holder.txt31.setBackgroundResource(R.drawable.bg_circular1);
                holder.txt31.setTextColor(Color.parseColor("#ffffff"));
            }else if(feedItems.getTable().get(position).get_$31194().equalsIgnoreCase("P2")){
                holder.txt31.setBackgroundResource(R.drawable.bg_circular2);
                holder.txt31.setTextColor(Color.parseColor("#ffffff"));
            }else if(feedItems.getTable().get(position).get_$31194().equalsIgnoreCase("A")){
                holder.txt31.setBackgroundResource(R.drawable.bg_circular3);
                holder.txt31.setTextColor(Color.parseColor("#ffffff"));
            }else if(feedItems.getTable().get(position).get_$31194().equalsIgnoreCase("L")){
                holder.txt31.setBackgroundResource(R.drawable.bg_circular4);
                holder.txt31.setTextColor(Color.parseColor("#ffffff"));
            }else if(feedItems.getTable().get(position).get_$31194().equalsIgnoreCase("H")){
                holder.txt31.setBackgroundResource(R.drawable.bg_circular5);
                holder.txt31.setTextColor(Color.parseColor("#ffffff"));
            }else if(feedItems.getTable().get(position).get_$31194().equalsIgnoreCase("WO")){
                holder.txt31.setBackgroundResource(R.drawable.bg_circular6);
                holder.txt31.setTextColor(Color.parseColor("#ffffff"));
            }else if(feedItems.getTable().get(position).get_$31194().equalsIgnoreCase("PL")){
                holder.txt31.setBackgroundResource(R.drawable.bg_circular7);
                holder.txt31.setTextColor(Color.parseColor("#ffffff"));
            }else if(feedItems.getTable().get(position).get_$31194().equalsIgnoreCase("PE")){
                holder.txt31.setBackgroundResource(R.drawable.bg_circular7);
                holder.txt31.setTextColor(Color.parseColor("#ffffff"));
            }

        }


    }

    @Override
    public int getItemCount() {

        return feedItems.getTable().size();
       // return 10;
    }



   /* public void filter(ArrayList<AttendanceModel> newList)
    {

        nameArrayList.clear();
        nameArrayList.addAll(newList);
        notifyDataSetChanged();
    }
*/
}
