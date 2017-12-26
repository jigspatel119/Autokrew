package com.autokrew.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.AppBarLayoutSpringBehavior;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabScrimHelper;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import com.autokrew.R;
import com.autokrew.adapter.TabFragmentPagerAdapter;
import com.autokrew.utils.CircleTransform;
import com.autokrew.utils.CommonUtils;
import com.autokrew.utils.Constant;
import com.autokrew.utils.Pref;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.github.mmin18.widget.RealtimeBlurView;

import java.io.File;
import java.util.ArrayList;


public class SpringAppBarLayoutWithTabActivity extends AppCompatActivity {

    ArrayList<String> mProfileTabs = new ArrayList<>();

    ImageView iv_cover_image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spring_app_bar_tab_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle("My Profile"); //logged in  user name

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CommonUtils.setupCustomToolbar(toolbar);

        getData();
        findView();
        setData();
        setListner();



        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        final RealtimeBlurView realtimeBlurView = (RealtimeBlurView) findViewById(R.id.real_time_blur_view);

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        AppBarLayoutSpringBehavior springBehavior = (AppBarLayoutSpringBehavior)
                ((CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams()).getBehavior();
        springBehavior.setSpringOffsetCallback(new AppBarLayoutSpringBehavior.SpringOffsetCallback() {
            @Override
            public void springCallback(int offset) {
                int radius = 20 * (240 - offset > 0 ? 240 - offset : 0) / 240;
              //  realtimeBlurView.setBlurRadius(radius);
            }
        });

        ViewPager viewPager = (ViewPager) findViewById(R.id.tabs_viewpager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(new TabFragmentPagerAdapter(getSupportFragmentManager(),mProfileTabs));
        TabScrimHelper tabScrimHelper = new TabScrimHelper(tabLayout, collapsingToolbarLayout);
        appBarLayout.addOnOffsetChangedListener(tabScrimHelper);
    }

    private void setData() {
        fiillArrayList();

        String mProfilePath = Pref.getValue(this,"profile_pic_server","");


        if(mProfilePath!=null){
            loadImageIntoProfilePicture(mProfilePath);
           /* File myFile = new File(Constant.FILE_DIRECTORY_MEDIA+"/" + mProfilePath);
            if(myFile.exists()){
                loadImageIntoProfilePicture(Constant.FILE_DIRECTORY_MEDIA +"/"+ mProfilePath);
            }*/
        }

    }

    private void getData() {
    }

    public void setImage(String path){
       // loadImageIntoProfilePicture(Pref.getValue(this, Constant.PREF_MOBILE_URL ,"")+"Upload/EmployeeDocument/"+path);
    }


    private void findView() {
        iv_cover_image = (ImageView)this.findViewById(R.id.iv_cover_image);
    }

    private void setListner() {
    }

    private void fiillArrayList() {

        mProfileTabs.clear();
        mProfileTabs.add("Basic");
       // mProfileTabs.add("Experience");
       /* mProfileTabs.add("Reference/Family");
        mProfileTabs.add("Joining Documents");
        mProfileTabs.add("Salary");
        mProfileTabs.add("Accounts");
        mProfileTabs.add("Assets");
        mProfileTabs.add("Miscellaneous");*/

       //change xml property if want scrollable tabs.. app:tabMode="fixed"

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {

            CommonUtils.getInstance().startActivityWithoutStack(this, MainActivity.class);
            this.overridePendingTransition(R.anim.activity_open_scale, R.anim.activity_close_translate);
        }
        return super.onOptionsItemSelected(menuItem);
    }


    private void loadImageIntoProfilePicture(String fileName) {

        // progressLoadImage.setVisibility(View.VISIBLE);

        Glide.with(this)
                .load(fileName).dontAnimate()
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
                .into(iv_cover_image);

    }


}
