package com.autokrew.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.autokrew.R;
import com.autokrew.adapter.EmployeeAdapter;
import com.autokrew.adapter.MyItemRecyclerViewAdapter;
import com.autokrew.models.Employee;
import com.autokrew.utils.DummyContent;

import java.util.ArrayList;
import java.util.List;


public class TempFragment extends Fragment {

    private RecyclerView reclist;
    private RecyclerView.Adapter mAdapter;
    private List<Employee> employeeList;
    private Button empBTN;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_temp, container, false);
        empBTN = (Button) view.findViewById(R.id.btnShow);

        employeeList = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {
            Employee st = new Employee("EmpName - "+i,"EmpComp - "+i,"EmpNum - "+i,false);

            employeeList.add(st);
        }

        //-------------
        reclist = (RecyclerView)view.findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        reclist.setHasFixedSize(true);

        // use a linear layout manager
        reclist.setLayoutManager(new LinearLayoutManager(getActivity()));

        // create an Object for Adapter
        mAdapter = new EmployeeAdapter(employeeList);

        // set the adapter object to the Recyclerview
        reclist.setAdapter(mAdapter);
        empBTN.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String secemp = "";
                List<Employee> stList = ((EmployeeAdapter) mAdapter).getEmployeeList();

                for (int i = 0; i < stList.size(); i++) {
                    Employee employee = stList.get(i);
                    if (employee.isSelected() == true) {

                        secemp = secemp + "\n" + employee.getEmpName().toString();

                    }

                }

                Toast.makeText(getActivity(),
                        "Selected Employees: \n" + secemp, Toast.LENGTH_LONG)
                        .show();
            }
        });



        return view;
    }


}
