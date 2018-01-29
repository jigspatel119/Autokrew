package com.autokrew.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.autokrew.ActionSheetDialog.ActionSheetDialog;
import com.autokrew.ActionSheetDialog.OnOpenItemClickL;
import com.autokrew.R;
import com.autokrew.adapter.CountryAdapter;
import com.autokrew.fragments.DashboardFragment;
import com.autokrew.fragments.GroupAttendanceFragment;
import com.autokrew.fragments.GroupLeaveFragment;
import com.autokrew.fragments.MyAttendanceFragment;
import com.autokrew.fragments.MyLeaveFragment;
import com.autokrew.fragments.ProfileFragment;
import com.autokrew.models.Model_Dish;
import com.autokrew.models.Model_country;
import com.autokrew.models.ProfileImageParams;
import com.autokrew.models.UserProfileModel;
import com.autokrew.network.ApiListener;
import com.autokrew.network.WebServices;
import com.autokrew.utils.CircleTransform;
import com.autokrew.utils.CommonUtils;
import com.autokrew.utils.Constant;
import com.autokrew.utils.Pref;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener ,ApiListener {

    ArrayList<Model_country> al_main = new ArrayList<>();
    ExpandableListView ev_list;
    CountryAdapter obj_adapter;
    String TAG = "MainActivity";
    private DrawerLayout mDrawerLayout;
    Fragment fragment;
    String TAG_FRAGMENT ;

    TextView tv_name ,txt_user_name ,txt_dashboard ,txt_employee_code;
    LinearLayout ll_user_profile;
    TextView txt_view_profile;
    RelativeLayout rl_menu;
    private int lastExpandedPosition = -1;
    private String filePath;
    Uri currentImageUri;
    UserProfileModel userProfileModel;

    private static final int MY_PERMISSION_REQUEST_CAMERA = 2;
    private static final int REQUEST_CAMERA = 1;
    private static final int MY_PERMISSION_REQUEST_READ_STORAGE = 1;
    private static final int MY_PERMISSION_REQUEST_READ_STORAGE_PROFILE_IMAGE = 3;
    private int RESULT_LOAD_PROFILE_IMAGE = 111;

    private String strUserImagePath = "", strCoverImagePath = "";



    ImageView img_profile ;
    private Boolean isForProfilePicture = false;
    String mToken;
    Fragment f;
    FragmentManager mManager = getSupportFragmentManager();
    //ProgressBar progressBar ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Pref.setValue(this, "auto_login","true");

        getData();
        init();
        setData();
        setListner();

    }

    private void setData() {


    }

    private void setListner() {

        img_profile.setOnClickListener(this);
        //txt_user_name.setOnClickListener(this);
       // ll_user_profile.setOnClickListener(this);
        txt_view_profile.setOnClickListener(this);
       // txt_dashboard.setOnClickListener(this);
    }

    private void init() {

        //getSupportActionBar().hide();

       // txt_dashboard = (TextView) findViewById(R.id.txt_dashboard);
        ev_list = (ExpandableListView) findViewById(R.id.ev_menu);
        tv_name = (TextView) findViewById(R.id.tv_name);
        rl_menu = (RelativeLayout) findViewById(R.id.rl_menu);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        obj_adapter = new CountryAdapter(MainActivity.this, al_main);

       final View v = findViewById(R.id.left_drawer);

        v.post(new Runnable() {
            @Override
            public void run() {
               /* Resources resources = getResources();
                float width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, resources.getDisplayMetrics());
                DrawerLayout.LayoutParams params = (DrawerLayout.LayoutParams) v.getLayoutParams();
                params.width = (int) (width);
                v.setLayoutParams(params);*/

                DisplayMetrics displaymetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
                int height = displaymetrics.heightPixels;
                int width = displaymetrics.widthPixels;

                DrawerLayout.LayoutParams params = (DrawerLayout.LayoutParams) v.getLayoutParams();
                params.width = (int) ((int) (width) * (0.8));
                v.setLayoutParams(params);

             //   v.setLayoutParams(new DrawerLayout.LayoutParams(DrawerLayout.LayoutParams.FILL_PARENT,width/2));

            }
        });
        img_profile = (ImageView)v.findViewById(R.id.img_profile);

        //progressBar = (ProgressBar) findViewById(R.id.progressBar);
        txt_user_name = (TextView)v.findViewById(R.id.txt_user_name);
        txt_user_name.setText(userProfileModel.getTable().get(0).getEmpName());

       // ll_user_profile = (LinearLayout)v.findViewById(R.id.ll_user_profile);
        txt_view_profile = (TextView)v.findViewById(R.id.txt_view_profile);

        txt_employee_code =(TextView)v.findViewById(R.id.txt_employee_code);
        txt_employee_code.setText(userProfileModel.getTable().get(0).getEmployeeCode());

//MobileURL/Upload/EmployeDocument/8816/a9a46b04-76fd-4434-9f05-2cf78fff06b0_images.png


  /*          String file_path = Pref.getValue(this,Constant.PREF_MOBILE_URL,"")
                    +"Upload/EmployeDocument/"+userProfileModel.getTable().get(0).getImageUrl();
            //save data in to pref
            Pref.setValue(this,"profile_pic_server" ,file_path);
            displayProfilePic(file_path);*/




        ev_list.setAdapter(obj_adapter);
        ev_list.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                setListViewHeight(parent, groupPosition);

                if(groupPosition == 0){
                    fragment = new DashboardFragment();
                    tv_name.setText("Dashboard");
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment, "Dashboard")
                            .addToBackStack("null").commit();

                    mDrawerLayout.closeDrawer(Gravity.LEFT);

                }
                else if(groupPosition == 3){
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                    showAlert();
                }


                return false;
            }
        });


        /**
         * for expand and collapseGroup on click event of group item
         * */

        ev_list.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {

                if (lastExpandedPosition != -1
                        && groupPosition != lastExpandedPosition) {
                    ev_list.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });

        //@kns.p
        setExpandableListViewHeightBasedOnChildren(ev_list);



        //default fragment load..

        fragment = new DashboardFragment();
        tv_name.setText(al_main.get(0).getStr_country()); // "name": "Dashboard" form json file
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment, "Dashboard")
                .addToBackStack("null").commit();


        rl_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });
    }

    private void displayProfilePic(String file_path) {

        Log.e(TAG, "file_path >> "+file_path );

        if(file_path!=null){
            // loadImageIntoProfilePicture(file_path);
            Glide.with(this)
                    .load(file_path).dontAnimate()
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            //progressLoadImage.setVisibility(View.GONE);
                           // progressBar.setVisibility(View.GONE);
                            Log.e(TAG, "onException: TRUE");
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            // progressLoadImage.setVisibility(View.GONE);
                            //progressBar.setVisibility(View.GONE);
                            Log.e(TAG, "onResourceReady: TRUE");
                            return false;
                        }
                    })

                    .bitmapTransform(new CircleTransform(this))
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .placeholder(R.drawable.profile_image)
                    .error(R.drawable.profile_image)
                    .into(img_profile);
        }
    }


    private void setListViewHeight(ExpandableListView listView, int group) {
        ExpandableListAdapter listAdapter = (ExpandableListAdapter) listView.getExpandableListAdapter();
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(),
                View.MeasureSpec.EXACTLY);
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            View groupItem = listAdapter.getGroupView(i, false, null, listView);
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

            totalHeight += groupItem.getMeasuredHeight();

            if (((listView.isGroupExpanded(i)) && (i != group))
                    || ((!listView.isGroupExpanded(i)) && (i == group))) {
                for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
                    View listItem = listAdapter.getChildView(i, j, false, null,
                            listView);
                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                    totalHeight += listItem.getMeasuredHeight();

                }
            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
       /* if (height < 10)
            height = 200;*/
        params.height = height;
        listView.setLayoutParams(params);
        listView.requestLayout();

    }

    private void getData() {

        mToken = Pref.getValue(this,Constant.PREF_TOKEN,"");
        //get user profile model and retrieve use's detail
        String data=  Pref.getValue(this,Constant.PREF_USER_DATA,"");
            Gson gson = new Gson();
        userProfileModel = gson.fromJson(data, UserProfileModel.class);

        String str_data = loadJSONFromAsset();

        try {
            JSONObject jsonObject_country = new JSONObject(str_data);
            JSONArray jsonArray_country = jsonObject_country.getJSONArray("country");

            al_main = new ArrayList<>();

           int roleFK= Pref.getValue(this,Constant.PREF_ROLE_FK,0);


            if(roleFK==2 || roleFK ==4){
                for (int i = 0; i < 4 ; i++) {

                    Model_country obj_country = new Model_country();
                    JSONObject jsonObject = jsonArray_country.getJSONObject(i);
                    JSONArray jsonArray_dishes = jsonObject.getJSONArray("dishes");
                    ArrayList<Model_Dish> al_dishes = new ArrayList<>();

                    for (int j = 0; j < jsonArray_dishes.length(); j++) {

                        JSONObject jsonObject_dishes = jsonArray_dishes.getJSONObject(j);
                        Model_Dish obj_dish = new Model_Dish();
                        obj_dish.setStr_name(jsonObject_dishes.getString("dishname"));
                        obj_dish.setStr_description(jsonObject_dishes.getString("description"));
                        obj_dish.setStr_image(jsonObject_dishes.getString("image"));
                        al_dishes.add(obj_dish);
                    }

                    obj_country.setAl_state(al_dishes);
                    obj_country.setStr_country(jsonObject.getString("name"));

                    al_main.add(obj_country);
                }
            }

            else{

                for (int i = 0; i < 4 ; i++) {

                    Model_country obj_country = new Model_country();
                    JSONObject jsonObject = jsonArray_country.getJSONObject(i);
                    JSONArray jsonArray_dishes = jsonObject.getJSONArray("dishes");
                    ArrayList<Model_Dish> al_dishes = new ArrayList<>();

                    for (int j = 0; j < jsonArray_dishes.length()-1; j++) {
                        JSONObject jsonObject_dishes = jsonArray_dishes.getJSONObject(j);
                        Model_Dish obj_dish = new Model_Dish();
                        obj_dish.setStr_name(jsonObject_dishes.getString("dishname"));
                        obj_dish.setStr_description(jsonObject_dishes.getString("description"));
                        obj_dish.setStr_image(jsonObject_dishes.getString("image"));
                        al_dishes.add(obj_dish);
                    }

                    obj_country.setAl_state(al_dishes);
                    obj_country.setStr_country(jsonObject.getString("name"));

                    al_main.add(obj_country);
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static void setExpandableListViewHeightBasedOnChildren(ExpandableListView expandableListView) {
        CountryAdapter adapter = (CountryAdapter) expandableListView.getExpandableListAdapter();
        if (adapter == null) {
            return;
        }
        int totalHeight = expandableListView.getPaddingTop() + expandableListView.getPaddingBottom();
        for (int i = 0; i < adapter.getGroupCount(); i++) {
            View groupItem = adapter.getGroupView(i, false, null, expandableListView);
            groupItem.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            totalHeight += groupItem.getMeasuredHeight();

            if (expandableListView.isGroupExpanded(i)) {
                for (int j = 0; j < adapter.getChildrenCount(i); j++) {
                    View listItem = adapter.getChildView(i, j, false, null, expandableListView);
                    listItem.setLayoutParams(new ViewGroup.LayoutParams(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED));
                    listItem.measure(View.MeasureSpec.makeMeasureSpec(0,
                            View.MeasureSpec.UNSPECIFIED), View.MeasureSpec
                            .makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                    totalHeight += listItem.getMeasuredHeight();

                }
            }
        }

        ViewGroup.LayoutParams params = expandableListView.getLayoutParams();
        int height = totalHeight + expandableListView.getDividerHeight() * (adapter.getGroupCount() - 1);

        if (height < 10)
            height = 100;
        params.height = height;
        expandableListView.setLayoutParams(params);
        expandableListView.requestLayout();
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {

            InputStream is = getAssets().open("dishes.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        Log.e(TAG, "Json response " + json);
        return json;

    }

    public void fn_selectedPosition(int group, int child) {

        String child_item = al_main.get(group).getAl_state().get(child).getStr_name();
        Bundle bundle = new Bundle();

        switch (child_item) {

            case "My Attendance":

                fragment = new MyAttendanceFragment();
                bundle.putString("name", al_main.get(group).getStr_country());
                //bundle.putString("des", al_main.get(group).getAl_state().get(child).getStr_description());
                bundle.putString("dish", al_main.get(group).getAl_state().get(child).getStr_name());
               // bundle.putString("image", al_main.get(group).getAl_state().get(child).getStr_image());
                fragment.setArguments(bundle);
                TAG_FRAGMENT=  "MyAttendanceFragment";

            break;

            case "My Team Attendance":

                //opening same #group attendance fragment for team attendance...
                // xml are same //just change the title
                fragment = new GroupAttendanceFragment();
                bundle.putString("name", al_main.get(group).getStr_country());
                //bundle.putString("des", al_main.get(group).getAl_state().get(child).getStr_description());
                bundle.putString("dish", al_main.get(group).getAl_state().get(child).getStr_name());
                // bundle.putString("image", al_main.get(group).getAl_state().get(child).getStr_image());
                fragment.setArguments(bundle);
                TAG_FRAGMENT = "GroupAttendanceFragment";

            break;

            case "Group Attendance":
                fragment = new GroupAttendanceFragment();
                bundle.putString("name", al_main.get(group).getStr_country());
                //bundle.putString("des", al_main.get(group).getAl_state().get(child).getStr_description());
                bundle.putString("dish", al_main.get(group).getAl_state().get(child).getStr_name());
                // bundle.putString("image", al_main.get(group).getAl_state().get(child).getStr_image());
                fragment.setArguments(bundle);
                TAG_FRAGMENT = "GroupAttendanceFragment";

            break;


            case "My Leave":
                fragment = new MyLeaveFragment();
                bundle.putString("name", al_main.get(group).getStr_country());
                //bundle.putString("des", al_main.get(group).getAl_state().get(child).getStr_description());
                bundle.putString("dish", al_main.get(group).getAl_state().get(child).getStr_name());
                // bundle.putString("image", al_main.get(group).getAl_state().get(child).getStr_image());
                fragment.setArguments(bundle);
                TAG_FRAGMENT=  "MyLeaveFragment";
                break;


            case "Group Leave":

                fragment = new GroupLeaveFragment();
                bundle.putString("name", al_main.get(group).getStr_country());
                //bundle.putString("des", al_main.get(group).getAl_state().get(child).getStr_description());
                bundle.putString("dish", al_main.get(group).getAl_state().get(child).getStr_name());
                // bundle.putString("image", al_main.get(group).getAl_state().get(child).getStr_image());
                fragment.setArguments(bundle);
                TAG_FRAGMENT = "GroupLeave";

                break;



            case "Team Leave":
                //opening same #Group Leave fragment for Team Leave...
                // xml are same //just change the title
                fragment = new GroupLeaveFragment();
                bundle.putString("name", al_main.get(group).getStr_country());
                //bundle.putString("des", al_main.get(group).getAl_state().get(child).getStr_description());
                bundle.putString("dish", al_main.get(group).getAl_state().get(child).getStr_name());
                // bundle.putString("image", al_main.get(group).getAl_state().get(child).getStr_image());
                fragment.setArguments(bundle);
                TAG_FRAGMENT = "GroupLeave";

                break;

        }

        getSupportFragmentManager().beginTransaction().
                replace(R.id.content_frame, fragment, TAG_FRAGMENT).
                addToBackStack("null").
                commit();
        mDrawerLayout.closeDrawer(Gravity.LEFT);
        tv_name.setText(al_main.get(group).getStr_country());

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {

            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                Log.e(TAG, "onBackPressed: if >> "+getSupportFragmentManager().getBackStackEntryCount());
                f = mManager.findFragmentById(R.id.content_frame);

                if (f != null && f instanceof DashboardFragment) {
                   // showAlert();
                    exitAlert();
                }

                else{
                    fragment = new DashboardFragment();
                    tv_name.setText("Dashboard");
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment, "Dashboard").addToBackStack("null").commit();
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                }

            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_profile:

                             changeProfilePicture();
                break;

            case R.id.txt_view_profile:


                mDrawerLayout.closeDrawer(Gravity.LEFT);

                fragment = new ProfileFragment();
                tv_name.setText("My Profile");
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment, "Profile").addToBackStack("null").commit();


                break;


           /* case R.id.txt_dashboard:

                TAG_FRAGMENT = "Dashboard";
                fragment = new DashboardFragment();
                Bundle bundle = new Bundle();
                bundle.putString("name", al_main.get(0).getStr_country());
                bundle.putString("des", al_main.get(0).getAl_state().get(0).getStr_description());
                bundle.putString("dish", al_main.get(0).getAl_state().get(0).getStr_name());
                bundle.putString("image", al_main.get(0).getAl_state().get(0).getStr_image());
                tv_name.setText(al_main.get(0).getStr_country());

                fragment.setArguments(bundle);


                getSupportFragmentManager().beginTransaction().
                        replace(R.id.content_frame, fragment, TAG_FRAGMENT).
                        addToBackStack("null").
                        commit();
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                tv_name.setText("Dashboard");

                break;*/
        }





}


    private void changeProfilePicture() {
        final String[] items = new String[]{"Camera", "Gallery"};

        final ActionSheetDialog dialog = new ActionSheetDialog(this, items, null);
        dialog.isTitleShow(true).show();
        dialog.title("Set your profile picture");
        dialog.setCancelable(true);

        dialog.setOnOperItemClickL(new OnOpenItemClickL() {
            @Override
            public void onOpenItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (items[position]) {

                    case "Camera":

                      isForProfilePicture = true;
                      requestPermissionForCameraPermission();

                        dialog.dismiss();
                        break;

                    case "Gallery":

                        dialog.dismiss();
                        requestPermissionForReadStorageForProfileImage();

                        break;

                    default:

                        dialog.dismiss();
                        break;
                }
            }
        });
    }


    private void requestPermissionForCameraPermission() {

        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED) {

                cameraIntent();

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSION_REQUEST_CAMERA);
            }
        } else {
            cameraIntent();
        }

    }

    private void cameraIntent() {
        currentImageUri = getImageFileUri();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, currentImageUri); // set the image file name
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    private Uri getImageFileUri() {
        // Create an image file name
        //String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        //File image = new File(imagePath,"MyProject_"+ timeStamp + ".jpg");
        File destination = new File(Constant.FILE_DIRECTORY_MEDIA, "profile_pic_"+System.currentTimeMillis() + ".jpeg");
        Pref.setValue(this,"profile_pic_path" ,"profile_pic_"+System.currentTimeMillis() + ".jpeg");

        filePath = destination.getAbsolutePath();
        Log.e(TAG, "onCaptureImageResult: filePath >> " + filePath);

        // Create an File Uri
       // return Uri.fromFile(destination);
        return FileProvider.getUriForFile(MainActivity.this, this.getApplicationContext().getPackageName() + ".provider",destination);
    }


    private void requestPermissionForReadStorageForProfileImage() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {

                openGalleryForProfilePicture();

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSION_REQUEST_READ_STORAGE_PROFILE_IMAGE);
            }
        } else {
            openGalleryForProfilePicture();
        }
    }

    void openGalleryForProfilePicture() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), RESULT_LOAD_PROFILE_IMAGE);

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
         if (requestCode == RESULT_LOAD_PROFILE_IMAGE && resultCode == RESULT_OK && null != data) {

            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            strUserImagePath = picturePath;

             File f = new File(Constant.FILE_DIRECTORY_MEDIA, "profile_pic_"+System.currentTimeMillis() + ".jpeg");

            // Pref.setValue(this,"profile_pic_path" ,"profile_pic_"+System.currentTimeMillis() + ".jpeg");


           /*  if (!f.exists())
             {*/
                 try {
                     f.createNewFile();
                     copyFile(new File(picturePath), f);
                 } catch (IOException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                 }
           //  }


            loadImageIntoProfilePicture(picturePath);


        }


         else if (requestCode == REQUEST_CAMERA) {
             if (resultCode == RESULT_OK)
                 onCaptureImageResult2(data);
         }

        //requestCode = 1 for coming back from verification screen

        else if (requestCode == 1) {

            if (resultCode == RESULT_CANCELED) {

                getWindow().setSoftInputMode(
                        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
                );

            }
        }

    }



    private void copyFile(File sourceFile, File destFile) throws IOException {
        if (!sourceFile.exists()) {
            return;
        }

        FileChannel source = null;
        FileChannel destination = null;
        source = new FileInputStream(sourceFile).getChannel();
        destination = new FileOutputStream(destFile).getChannel();
        if (destination != null && source != null) {
            destination.transferFrom(source, 0, source.size());
        }
        if (source != null) {
            source.close();
        }
        if (destination != null) {
            destination.close();
        }


    }


    private void onCaptureImageResult2(Intent data) {
       // Bitmap reducedSizeBitmap = getBitmap(currentImageUri.getPath());
        if (isForProfilePicture) {
            loadImageIntoProfilePicture(filePath);
            isForProfilePicture = false;
            strUserImagePath = filePath;
        }
    }


    private void loadImageIntoProfilePicture(String fileName) {

        try{
            Bitmap bm = BitmapFactory.decodeFile(fileName);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 80, baos); //bm is the bitmap object
            byte[] b = baos.toByteArray();
            String ImageAsString = Base64.encodeToString(b, Base64.DEFAULT);

            //api calls for image upload
            ProfileImageParams params = new ProfileImageParams();
            params.setEmployeeFK(Pref.getValue(this,Constant.PREF_SESSION_EMPLOYEE_FK,0));
            params.setImageAsString(ImageAsString); //create file type object

            params.setFileName("profile_pic_"+System.currentTimeMillis() + ".jpeg");


            new WebServices(this/* ActivityContext */, this /* ApiListener */,
                    true /* show progress dialog */,true).
                    callProfileImageAPI(mToken,params);
        }
        catch (Exception e){
            Log.e(TAG, "loadImageIntoProfilePicture: " +e);
        }



    }

    private void showAlert() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_signout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        //TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
        //text.setText(msg);

        Button btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
        Button btn_ok = (Button) dialog.findViewById(R.id.btn_ok);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // dialog.dismiss();
                //clear pref
                Pref.setValue(MainActivity.this, "auto_login","false");

                //login credential
                Pref.setValue(MainActivity.this,"user_id","");
                Pref.setValue(MainActivity.this,"user_password","");

                Pref.setValue(MainActivity.this,"profile_pic_server" ,""); //clear profile pic


                Pref.setValue(MainActivity.this,"mApprovalStatus","");
                Pref.setValue(MainActivity.this,"mYearPK","");
                Pref.setValue(MainActivity.this,"mMonthPK","");
                Pref.setValue(MainActivity.this,"mEmployeePK","");

               CommonUtils.getInstance().startActivityWithoutStack(getApplicationContext(), SigninActivity.class);

                /*Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(startMain);
                finish();*/


            }
        });

        dialog.show();

    }


    private void exitAlert() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_exit);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        //TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
        //text.setText(msg);

        Button btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
        Button btn_ok = (Button) dialog.findViewById(R.id.btn_ok);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish();


            }
        });

        dialog.show();

    }

    @Override
    public void onApiSuccess(Object mObject) {
        //set adapter here
        if (mObject instanceof String) {
            //LoginModel model = (LoginModel) mObject;
            // Log.e("", "onApiSuccess: 123 >>  "+mObject.toString() );
            try {
                if(mObject.toString().contains(".jpeg")){

                    String file_path = Pref.getValue(this,Constant.PREF_MOBILE_URL,"")+ mObject.toString();
                    Pref.setValue(this,"profile_pic_server" ,file_path);

                    Toast.makeText(this,"Profile picture changed!",Toast.LENGTH_LONG).show();

                    displayProfilePic(file_path);
                }else{
                    CommonUtils.getInstance().displayToast(this,mObject.toString());
                }
                Log.e("", "onApiSuccess: profile image upload >>  " + mObject.toString());



            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onApiFailure(Throwable mThrowable) {
        Log.e("", "onApiFailure: profile image upload >>  " );


    }


    @Override
    protected void onResume() {
        super.onResume();
        //set profile pic again.
        String mProfilePath = Pref.getValue(this,"profile_pic_server","");
        if(mProfilePath!=null){
           displayProfilePic(mProfilePath);
        }
        /*else{
            String file_path = Pref.getValue(this,Constant.PREF_MOBILE_URL,"")+"Upload/EmployeeDocument/"+userProfileModel.getTable().get(0).getImageUrl();
            displayProfilePic(file_path);

        }*/
    }
}
