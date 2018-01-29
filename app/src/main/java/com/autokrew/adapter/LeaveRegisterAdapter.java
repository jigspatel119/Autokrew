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
import com.autokrew.models.LeaveRegisterTeamGroupModel;


public class LeaveRegisterAdapter extends RecyclerView.Adapter<LeaveRegisterAdapter.ViewHolder> implements
        Filterable {

    public LeaveRegisterTeamGroupModel feedItems;
    Context ctx;

    public LeaveRegisterAdapter(Context ctx, LeaveRegisterTeamGroupModel feedItems) {

        this.feedItems = feedItems;
        this.ctx=ctx;
    }

   public Filter getFilter() {
       return  null;

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
        holder.txt_emp_code.setText(feedItems.getTable().get(position).get_$EmployeeCode66());


        if(feedItems.getTable().get(position).get_$135().contains("#")){
            holder.txt1.setTextColor(Color.parseColor("#ffffff"));
            String[] s = feedItems.getTable().get(position).get_$135().split("#");
            holder.txt1.setText(s[0]);
            if(s[1].equalsIgnoreCase("1")){
                holder.txt1.setBackgroundResource(R.drawable.bg_circular4);
            }
            if(s[1].equalsIgnoreCase("2")){
                holder.txt1.setBackgroundResource(R.drawable.bg_circular1);
            }
            if(s[1].equalsIgnoreCase("3")){
                holder.txt1.setBackgroundResource(R.drawable.bg_circular3);
            }
        }


        if(feedItems.getTable().get(position).get_$2233().contains("#")){
            holder.txt2.setTextColor(Color.parseColor("#ffffff"));
            String[] s = feedItems.getTable().get(position).get_$2233().split("#");
            holder.txt2.setText(s[0]);
            if(s[1].equalsIgnoreCase("1")){
                holder.txt2.setBackgroundResource(R.drawable.bg_circular4);
            }
            if(s[1].equalsIgnoreCase("2")){
                holder.txt2.setBackgroundResource(R.drawable.bg_circular1);
            }
            if(s[1].equalsIgnoreCase("3")){
                holder.txt2.setBackgroundResource(R.drawable.bg_circular3);
            }
        }

        if(feedItems.getTable().get(position).get_$328().contains("#")){
            holder.txt3.setTextColor(Color.parseColor("#ffffff"));
            String[] s = feedItems.getTable().get(position).get_$328().split("#");
            holder.txt3.setText(s[0]);
            if(s[1].equalsIgnoreCase("1")){
                holder.txt3.setBackgroundResource(R.drawable.bg_circular4);
            }
            if(s[1].equalsIgnoreCase("2")){
                holder.txt3.setBackgroundResource(R.drawable.bg_circular1);
            }
            if(s[1].equalsIgnoreCase("3")){
                holder.txt3.setBackgroundResource(R.drawable.bg_circular3);
            }
        }

        if(feedItems.getTable().get(position).get_$4190().contains("#")){
            holder.txt4.setTextColor(Color.parseColor("#ffffff"));
            String[] s = feedItems.getTable().get(position).get_$4190().split("#");
            holder.txt4.setText(s[0]);
            if(s[1].equalsIgnoreCase("1")){
                holder.txt4.setBackgroundResource(R.drawable.bg_circular4);
            }
            if(s[1].equalsIgnoreCase("2")){
                holder.txt4.setBackgroundResource(R.drawable.bg_circular1);
            }
            if(s[1].equalsIgnoreCase("3")){
                holder.txt4.setBackgroundResource(R.drawable.bg_circular3);
            }
        }

        if(feedItems.getTable().get(position).get_$5106().contains("#")){
            holder.txt5.setTextColor(Color.parseColor("#ffffff"));
            String[] s = feedItems.getTable().get(position).get_$5106().split("#");
            holder.txt5.setText(s[0]);
            if(s[1].equalsIgnoreCase("1")){
                holder.txt5.setBackgroundResource(R.drawable.bg_circular4);
            }
            if(s[1].equalsIgnoreCase("2")){
                holder.txt5.setBackgroundResource(R.drawable.bg_circular1);
            }
            if(s[1].equalsIgnoreCase("3")){
                holder.txt5.setBackgroundResource(R.drawable.bg_circular3);
            }
        }

        if(feedItems.getTable().get(position).get_$6143().contains("#")){
            holder.txt6.setTextColor(Color.parseColor("#ffffff"));
            String[] s = feedItems.getTable().get(position).get_$6143().split("#");
            holder.txt6.setText(s[0]);
            if(s[1].equalsIgnoreCase("1")){
                holder.txt6.setBackgroundResource(R.drawable.bg_circular4);
            }
            if(s[1].equalsIgnoreCase("2")){
                holder.txt6.setBackgroundResource(R.drawable.bg_circular1);
            }
            if(s[1].equalsIgnoreCase("3")){
                holder.txt6.setBackgroundResource(R.drawable.bg_circular3);
            }
        }

        if(feedItems.getTable().get(position).get_$7317().contains("#")){
            holder.txt7.setTextColor(Color.parseColor("#ffffff"));
            String[] s = feedItems.getTable().get(position).get_$7317().split("#");
            holder.txt7.setText(s[0]);
            if(s[1].equalsIgnoreCase("1")){
                holder.txt7.setBackgroundResource(R.drawable.bg_circular4);
            }
            if(s[1].equalsIgnoreCase("2")){
                holder.txt7.setBackgroundResource(R.drawable.bg_circular1);
            }
            if(s[1].equalsIgnoreCase("3")){
                holder.txt7.setBackgroundResource(R.drawable.bg_circular3);
            }
        }

        if(feedItems.getTable().get(position).get_$8314().contains("#")){
            holder.txt8.setTextColor(Color.parseColor("#ffffff"));
            String[] s = feedItems.getTable().get(position).get_$8314().split("#");
            holder.txt8.setText(s[0]);
            if(s[1].equalsIgnoreCase("1")){
                holder.txt8.setBackgroundResource(R.drawable.bg_circular4);
            }
            if(s[1].equalsIgnoreCase("2")){
                holder.txt8.setBackgroundResource(R.drawable.bg_circular1);
            }
            if(s[1].equalsIgnoreCase("3")){
                holder.txt8.setBackgroundResource(R.drawable.bg_circular3);
            }
        }

        if(feedItems.getTable().get(position).get_$9150().contains("#")){
            holder.txt9.setTextColor(Color.parseColor("#ffffff"));
            String[] s = feedItems.getTable().get(position).get_$9150().split("#");
            holder.txt9.setText(s[0]);
            if(s[1].equalsIgnoreCase("1")){
                holder.txt9.setBackgroundResource(R.drawable.bg_circular4);
            }
            if(s[1].equalsIgnoreCase("2")){
                holder.txt9.setBackgroundResource(R.drawable.bg_circular1);
            }
            if(s[1].equalsIgnoreCase("3")){
                holder.txt9.setBackgroundResource(R.drawable.bg_circular3);
            }
        }

        if(feedItems.getTable().get(position).get_$10329().contains("#")){
            holder.txt10.setTextColor(Color.parseColor("#ffffff"));
            String[] s = feedItems.getTable().get(position).get_$10329().split("#");
            holder.txt10.setText(s[0]);
            if(s[1].equalsIgnoreCase("1")){
                holder.txt10.setBackgroundResource(R.drawable.bg_circular4);
            }
            if(s[1].equalsIgnoreCase("2")){
                holder.txt10.setBackgroundResource(R.drawable.bg_circular1);
            }
            if(s[1].equalsIgnoreCase("3")){
                holder.txt10.setBackgroundResource(R.drawable.bg_circular3);
            }
        }

        if(feedItems.getTable().get(position).get_$11156().contains("#")){
            holder.txt11.setTextColor(Color.parseColor("#ffffff"));
            String[] s = feedItems.getTable().get(position).get_$11156().split("#");
            holder.txt11.setText(s[0]);
            if(s[1].equalsIgnoreCase("1")){
                holder.txt11.setBackgroundResource(R.drawable.bg_circular4);
            }
            if(s[1].equalsIgnoreCase("2")){
                holder.txt11.setBackgroundResource(R.drawable.bg_circular1);
            }
            if(s[1].equalsIgnoreCase("3")){
                holder.txt11.setBackgroundResource(R.drawable.bg_circular3);
            }
        }

        if(feedItems.getTable().get(position).get_$12146().contains("#")){
            holder.txt12.setTextColor(Color.parseColor("#ffffff"));
            String[] s = feedItems.getTable().get(position).get_$12146().split("#");
            holder.txt12.setText(s[0]);
            if(s[1].equalsIgnoreCase("1")){
                holder.txt12.setBackgroundResource(R.drawable.bg_circular4);
            }
            if(s[1].equalsIgnoreCase("2")){
                holder.txt12.setBackgroundResource(R.drawable.bg_circular1);
            }
            if(s[1].equalsIgnoreCase("3")){
                holder.txt12.setBackgroundResource(R.drawable.bg_circular3);
            }
        }

        if(feedItems.getTable().get(position).get_$1394().contains("#")){
            holder.txt13.setTextColor(Color.parseColor("#ffffff"));
            String[] s = feedItems.getTable().get(position).get_$1394().split("#");
            holder.txt13.setText(s[0]);
            if(s[1].equalsIgnoreCase("1")){
                holder.txt13.setBackgroundResource(R.drawable.bg_circular4);
            }
            if(s[1].equalsIgnoreCase("2")){
                holder.txt13.setBackgroundResource(R.drawable.bg_circular1);
            }
            if(s[1].equalsIgnoreCase("3")){
                holder.txt13.setBackgroundResource(R.drawable.bg_circular3);
            }
        }

        if(feedItems.getTable().get(position).get_$1471().contains("#")){
            holder.txt14.setTextColor(Color.parseColor("#ffffff"));
            String[] s = feedItems.getTable().get(position).get_$1471().split("#");
            holder.txt14.setText(s[0]);
            if(s[1].equalsIgnoreCase("1")){
                holder.txt14.setBackgroundResource(R.drawable.bg_circular4);
            }
            if(s[1].equalsIgnoreCase("2")){
                holder.txt14.setBackgroundResource(R.drawable.bg_circular1);
            }
            if(s[1].equalsIgnoreCase("3")){
                holder.txt14.setBackgroundResource(R.drawable.bg_circular3);
            }
        }

        if(feedItems.getTable().get(position).get_$15311().contains("#")){
            holder.txt15.setTextColor(Color.parseColor("#ffffff"));
            String[] s = feedItems.getTable().get(position).get_$15311().split("#");
            holder.txt15.setText(s[0]);
            if(s[1].equalsIgnoreCase("1")){
                holder.txt15.setBackgroundResource(R.drawable.bg_circular4);
            }
            if(s[1].equalsIgnoreCase("2")){
                holder.txt15.setBackgroundResource(R.drawable.bg_circular1);
            }
            if(s[1].equalsIgnoreCase("3")){
                holder.txt15.setBackgroundResource(R.drawable.bg_circular3);
            }
        }

        if(feedItems.getTable().get(position).get_$16282().contains("#")){
            holder.txt16.setTextColor(Color.parseColor("#ffffff"));
            String[] s = feedItems.getTable().get(position).get_$16282().split("#");
            holder.txt16.setText(s[0]);
            if(s[1].equalsIgnoreCase("1")){
                holder.txt16.setBackgroundResource(R.drawable.bg_circular4);
            }
            if(s[1].equalsIgnoreCase("2")){
                holder.txt16.setBackgroundResource(R.drawable.bg_circular1);
            }
            if(s[1].equalsIgnoreCase("3")){
                holder.txt16.setBackgroundResource(R.drawable.bg_circular3);
            }
        }




        //@kns.p

        if(feedItems.getTable().get(position).get_$17204().contains("#")){
            holder.txt17.setTextColor(Color.parseColor("#ffffff"));
            String[] s = feedItems.getTable().get(position).get_$17204().split("#");
            holder.txt17.setText(s[0]);
            if(s[1].equalsIgnoreCase("1")){
                holder.txt17.setBackgroundResource(R.drawable.bg_circular4);
            }
            if(s[1].equalsIgnoreCase("2")){
                holder.txt17.setBackgroundResource(R.drawable.bg_circular1);
            }
            if(s[1].equalsIgnoreCase("3")){
                holder.txt17.setBackgroundResource(R.drawable.bg_circular3);
            }
        }

        if(feedItems.getTable().get(position).get_$18202().contains("#")){
            holder.txt18.setTextColor(Color.parseColor("#ffffff"));
            String[] s = feedItems.getTable().get(position).get_$18202().split("#");
            holder.txt18.setText(s[0]);
            if(s[1].equalsIgnoreCase("1")){
                holder.txt18.setBackgroundResource(R.drawable.bg_circular4);
            }
            if(s[1].equalsIgnoreCase("2")){
                holder.txt18.setBackgroundResource(R.drawable.bg_circular1);
            }
            if(s[1].equalsIgnoreCase("3")){
                holder.txt18.setBackgroundResource(R.drawable.bg_circular3);
            }
        }


        if(feedItems.getTable().get(position).get_$19252().contains("#")){
            holder.txt19.setTextColor(Color.parseColor("#ffffff"));
            String[] s = feedItems.getTable().get(position).get_$19252().split("#");
            holder.txt19.setText(s[0]);
            if(s[1].equalsIgnoreCase("1")){
                holder.txt19.setBackgroundResource(R.drawable.bg_circular4);
            }
            if(s[1].equalsIgnoreCase("2")){
                holder.txt19.setBackgroundResource(R.drawable.bg_circular1);
            }
            if(s[1].equalsIgnoreCase("3")){
                holder.txt19.setBackgroundResource(R.drawable.bg_circular3);
            }
        }

        if(feedItems.getTable().get(position).get_$20117().contains("#")){
            holder.txt20.setTextColor(Color.parseColor("#ffffff"));
            String[] s = feedItems.getTable().get(position).get_$20117().split("#");
            holder.txt20.setText(s[0]);
            if(s[1].equalsIgnoreCase("1")){
                holder.txt20.setBackgroundResource(R.drawable.bg_circular4);
            }
            if(s[1].equalsIgnoreCase("2")){
                holder.txt20.setBackgroundResource(R.drawable.bg_circular1);
            }
            if(s[1].equalsIgnoreCase("3")){
                holder.txt20.setBackgroundResource(R.drawable.bg_circular3);
            }
        }


        if(feedItems.getTable().get(position).get_$21253().contains("#")){
            holder.txt21.setTextColor(Color.parseColor("#ffffff"));
            String[] s = feedItems.getTable().get(position).get_$21253().split("#");
            holder.txt21.setText(s[0]);
            if(s[1].equalsIgnoreCase("1")){
                holder.txt21.setBackgroundResource(R.drawable.bg_circular4);
            }
            if(s[1].equalsIgnoreCase("2")){
                holder.txt21.setBackgroundResource(R.drawable.bg_circular1);
            }
            if(s[1].equalsIgnoreCase("3")){
                holder.txt21.setBackgroundResource(R.drawable.bg_circular3);
            }
        }


        if(feedItems.getTable().get(position).get_$22169().contains("#")){
            holder.txt22.setTextColor(Color.parseColor("#ffffff"));
            String[] s = feedItems.getTable().get(position).get_$22169().split("#");
            holder.txt22.setText(s[0]);
            if(s[1].equalsIgnoreCase("1")){
                holder.txt22.setBackgroundResource(R.drawable.bg_circular4);
            }
            if(s[1].equalsIgnoreCase("2")){
                holder.txt22.setBackgroundResource(R.drawable.bg_circular1);
            }
            if(s[1].equalsIgnoreCase("3")){
                holder.txt22.setBackgroundResource(R.drawable.bg_circular3);
            }
        }

        if(feedItems.getTable().get(position).get_$2318().contains("#")){
            holder.txt23.setTextColor(Color.parseColor("#ffffff"));
            String[] s = feedItems.getTable().get(position).get_$2318().split("#");
            holder.txt23.setText(s[0]);
            if(s[1].equalsIgnoreCase("1")){
                holder.txt23.setBackgroundResource(R.drawable.bg_circular4);
            }
            if(s[1].equalsIgnoreCase("2")){
                holder.txt23.setBackgroundResource(R.drawable.bg_circular1);
            }
            if(s[1].equalsIgnoreCase("3")){
                holder.txt23.setBackgroundResource(R.drawable.bg_circular3);
            }
        }

        if(feedItems.getTable().get(position).get_$2490().contains("#")){
            holder.txt24.setTextColor(Color.parseColor("#ffffff"));
            String[] s = feedItems.getTable().get(position).get_$2490().split("#");
            holder.txt24.setText(s[0]);
            if(s[1].equalsIgnoreCase("1")){
                holder.txt24.setBackgroundResource(R.drawable.bg_circular4);
            }
            if(s[1].equalsIgnoreCase("2")){
                holder.txt24.setBackgroundResource(R.drawable.bg_circular1);
            }
            if(s[1].equalsIgnoreCase("3")){
                holder.txt24.setBackgroundResource(R.drawable.bg_circular3);
            }
        }

        if(feedItems.getTable().get(position).get_$2528().contains("#")){
            holder.txt25.setTextColor(Color.parseColor("#ffffff"));
            String[] s = feedItems.getTable().get(position).get_$2528().split("#");
            holder.txt25.setText(s[0]);
            if(s[1].equalsIgnoreCase("1")){
                holder.txt25.setBackgroundResource(R.drawable.bg_circular4);
            }
            if(s[1].equalsIgnoreCase("2")){
                holder.txt25.setBackgroundResource(R.drawable.bg_circular1);
            }
            if(s[1].equalsIgnoreCase("3")){
                holder.txt25.setBackgroundResource(R.drawable.bg_circular3);
            }
        }

        if(feedItems.getTable().get(position).get_$2668().contains("#")){
            holder.txt26.setTextColor(Color.parseColor("#ffffff"));
            String[] s = feedItems.getTable().get(position).get_$2668().split("#");
            holder.txt26.setText(s[0]);
            if(s[1].equalsIgnoreCase("1")){
                holder.txt26.setBackgroundResource(R.drawable.bg_circular4);
            }
            if(s[1].equalsIgnoreCase("2")){
                holder.txt26.setBackgroundResource(R.drawable.bg_circular1);
            }
            if(s[1].equalsIgnoreCase("3")){
                holder.txt26.setBackgroundResource(R.drawable.bg_circular3);
            }
        }

        if(feedItems.getTable().get(position).get_$27146().contains("#")){
            holder.txt27.setTextColor(Color.parseColor("#ffffff"));
            String[] s = feedItems.getTable().get(position).get_$27146().split("#");
            holder.txt27.setText(s[0]);
            if(s[1].equalsIgnoreCase("1")){
                holder.txt27.setBackgroundResource(R.drawable.bg_circular4);
            }
            if(s[1].equalsIgnoreCase("2")){
                holder.txt27.setBackgroundResource(R.drawable.bg_circular1);
            }
            if(s[1].equalsIgnoreCase("3")){
                holder.txt27.setBackgroundResource(R.drawable.bg_circular3);
            }
        }

        if(feedItems.getTable().get(position).get_$28197().contains("#")){
            holder.txt28.setTextColor(Color.parseColor("#ffffff"));
            String[] s = feedItems.getTable().get(position).get_$28197().split("#");
            holder.txt28.setText(s[0]);
            if(s[1].equalsIgnoreCase("1")){
                holder.txt28.setBackgroundResource(R.drawable.bg_circular4);
            }
            if(s[1].equalsIgnoreCase("2")){
                holder.txt28.setBackgroundResource(R.drawable.bg_circular1);
            }
            if(s[1].equalsIgnoreCase("3")){
                holder.txt28.setBackgroundResource(R.drawable.bg_circular3);
            }
        }

        //check for 29th

        if(feedItems.getTable().get(position).get_$29113()!= null){

            if(feedItems.getTable().get(position).get_$29113().contains("#")){
                holder.txt29.setTextColor(Color.parseColor("#ffffff"));
                String[] s = feedItems.getTable().get(position).get_$29113().split("#");
                holder.txt29.setText(s[0]);
                if(s[1].equalsIgnoreCase("1")){
                    holder.txt29.setBackgroundResource(R.drawable.bg_circular4);
                }
                if(s[1].equalsIgnoreCase("2")){
                    holder.txt29.setBackgroundResource(R.drawable.bg_circular1);
                }
                if(s[1].equalsIgnoreCase("3")){
                    holder.txt29.setBackgroundResource(R.drawable.bg_circular3);
                }
            }

        }

        //check for 30th
        if(feedItems.getTable().get(position).get_$30173()!=null){
            if(feedItems.getTable().get(position).get_$30173().contains("#")){
                holder.txt30.setTextColor(Color.parseColor("#ffffff"));
                String[] s = feedItems.getTable().get(position).get_$30173().split("#");
                holder.txt30.setText(s[0]);
                if(s[1].equalsIgnoreCase("1")){
                    holder.txt30.setBackgroundResource(R.drawable.bg_circular4);
                }
                if(s[1].equalsIgnoreCase("2")){
                    holder.txt30.setBackgroundResource(R.drawable.bg_circular1);
                }
                if(s[1].equalsIgnoreCase("3")){
                    holder.txt30.setBackgroundResource(R.drawable.bg_circular3);
                }
            }
        }


        //check for 31st
        if(feedItems.getTable().get(position).get_$31136()!=null){
            if(feedItems.getTable().get(position).get_$31136().contains("#")){
                holder.txt31.setTextColor(Color.parseColor("#ffffff"));
                String[] s = feedItems.getTable().get(position).get_$31136().split("#");
                holder.txt31.setText(s[0]);
                if(s[1].equalsIgnoreCase("1")){
                    holder.txt31.setBackgroundResource(R.drawable.bg_circular4);
                }
                if(s[1].equalsIgnoreCase("2")){
                    holder.txt31.setBackgroundResource(R.drawable.bg_circular1);
                }
                if(s[1].equalsIgnoreCase("3")){
                    holder.txt31.setBackgroundResource(R.drawable.bg_circular3);
                }
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
