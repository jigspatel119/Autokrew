package com.autokrew.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.autokrew.R;
import com.autokrew.fragments.AttendanceDeviationFragment;
import com.autokrew.fragments.ThreeFragment;
import com.autokrew.fragments.TwoFragment;

import java.util.ArrayList;
import java.util.List;

public class AttendanceActivity extends AppCompatActivity{

    Toolbar toolbar;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        findView();
    }


    private void findView() {
        // btn_signin = (MyTextView) this.findViewById(R.id.btn_signin);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Attendance");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        //CommonUtils.setupCustomToolbar(toolbar);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new AttendanceDeviationFragment(), "Attendance Deviation");
        adapter.addFrag(new TwoFragment(), "Employee Attendance Status");
        adapter.addFrag(new ThreeFragment(), "Employee Attendance Register");
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(0);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        this.overridePendingTransition(R.anim.activity_open_scale, R.anim.activity_close_translate);


    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {

            finish();
            this.overridePendingTransition(R.anim.activity_open_scale, R.anim.activity_close_translate);

        }
        return super.onOptionsItemSelected(menuItem);
    }

}

