package com.autokrew.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.autokrew.R;
import com.autokrew.interfaces.RecyclerViewDashBoardListener;
import com.autokrew.models.DashbordModel;
import com.autokrew.models.Employee;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;


public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {

    private List<Employee> empList;
    public EmployeeAdapter(List<Employee> employees) {
        this.empList = employees;
    }

    @Override
    public EmployeeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.rv_temp_item, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final int pos = position;
        Employee employee = empList.get(position);
        viewHolder.tvName.setText(employee.getEmpName());
        viewHolder.tvComp.setText(employee.getEmpComp());
        viewHolder.tvNum.setText(employee.getNumber());

        viewHolder.chkSelected.setChecked(employee.isSelected());
        viewHolder.chkSelected.setTag(empList.get(position));
        viewHolder.chkSelected.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                Employee emp = (Employee) cb.getTag();

                emp.setSelected(cb.isChecked());
                empList.get(pos).setSelected(cb.isChecked());

                Toast.makeText(
                        v.getContext(),
                        "Selected Employees: " + cb.getText() + " is "
                                + cb.isChecked(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return empList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName;
        public TextView tvComp;
        public TextView tvNum;
        public CheckBox chkSelected;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            tvName = (TextView) itemLayoutView.findViewById(R.id.empName);
            tvComp = (TextView) itemLayoutView.findViewById(R.id.empcomp);
            tvNum = (TextView) itemLayoutView.findViewById(R.id.empNum);
            chkSelected = (CheckBox) itemLayoutView.findViewById(R.id.checkBox);
        }
    }
    public List<Employee> getEmployeeList() {
        return empList;
    }
}
