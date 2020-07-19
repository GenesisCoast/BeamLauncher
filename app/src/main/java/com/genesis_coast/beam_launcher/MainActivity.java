package com.genesis_coast.beam_launcher;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.WallpaperManager;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ActionBarDrawerToggle mNavigationToggle;
    private DrawerLayout mNavigationDrawer;
    private TabLayout mCategoriesTab;
    private Toolbar mNavigationToolbar;
    private ImageView mWallpaperView;
    private ViewPager mCategoriesPager;

    private void checkPermissions() {

        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Explain to the user why we need to read the contacts
            }

            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);

            // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
            // app-defined int constant that should be quite unique

            return;
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Remove the status bar.
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)){
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }else{
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        // Inflate the activity layout.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Localize the controls as field.
        mWallpaperView = findViewById(R.id.main_img_wallpaper);
        mCategoriesTab = findViewById(R.id.main_tl_categories);
        mCategoriesPager = findViewById(R.id.main_vp_categories);

        mNavigationToolbar = findViewById(R.id.main_tb_navigation);
        mNavigationToolbar.setNavigationIcon(R.drawable.ic_menu_white);
        setSupportActionBar(mNavigationToolbar);

        mNavigationToolbar.setTitleTextColor(Color.WHITE);

        mNavigationDrawer = findViewById(R.id.main_dl_navigation);

        mNavigationToggle = new ActionBarDrawerToggle(
                this,
                mNavigationDrawer,
                mNavigationToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );

        mNavigationDrawer.addDrawerListener(mNavigationToggle);

        getSupportActionBar()
                .setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeButtonEnabled(true);

        // Set the background.
        checkPermissions();

        mWallpaperView.setImageDrawable(
                WallpaperManager
                        .getInstance(this)
                        .getDrawable()
        );

        // Setup the view pager.
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new DirectionsFragment(), "Directions");
        adapter.addFragment(new PhoneFragment(), "Phone");
        adapter.addFragment(new HomeFragment(), "Home");
        adapter.addFragment(new MusicFragment(), "Music");
        adapter.addFragment(new FavouritesFragment(), "Favourites");

        // Finalize the view pager behaviour.
        mCategoriesPager.setAdapter(adapter);
        mCategoriesTab.setupWithViewPager(mCategoriesPager);

        // Set the tab icons.
        mCategoriesTab.getTabAt(0).setIcon(R.drawable.ic_directions_white);
        mCategoriesTab.getTabAt(1).setIcon(R.drawable.ic_phone_white);
        mCategoriesTab.getTabAt(2).setIcon(R.drawable.ic_home_white);
        mCategoriesTab.getTabAt(3).setIcon(R.drawable.ic_music_white);
        mCategoriesTab.getTabAt(4).setIcon(R.drawable.ic_favourites_white);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults){
        switch (requestCode){
            case 1: {
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (ContextCompat.checkSelfPermission(MainActivity.this,
                            Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        getSupportActionBar().hide();
//
//
//        getPermissions();
//        getData();
//
//        final LinearLayout mTopDrawerLayout = findViewById(R.id.topDrawerLayout);
//        mTopDrawerLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                DRAWER_PEEK_HEIGHT = mTopDrawerLayout.getHeight();
//                initializeHome();
//                initializeDrawer();
//            }
//        });
//
//        ImageButton mSettings = findViewById(R.id.settings);
//        mSettings.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) { startActivity(new Intent(getApplicationContext(), SettingsActivity.class)); }
//        });
//    }
//
//    ViewPagerAdapter mViewPagerAdapter;
//    private void initializeHome() {
//        ArrayList<PagerObject> pagerAppList = new ArrayList<>();
//
//        ArrayList<AppObject> appList1 = new ArrayList<>();
//        ArrayList<AppObject> appList2 = new ArrayList<>();
//        ArrayList<AppObject> appList3 = new ArrayList<>();
//        for (int i = 0; i < numColumn*numRow ;i++)
//            appList1.add(new AppObject("", "", getResources().getDrawable(R.drawable.ic_launcher_foreground), false));
//        for (int i = 0; i < numColumn*numRow ;i++)
//            appList2.add(new AppObject("", "", getResources().getDrawable(R.drawable.ic_launcher_foreground), false));
//        for (int i = 0; i < numColumn*numRow ;i++)
//            appList3.add(new AppObject("", "", getResources().getDrawable(R.drawable.ic_launcher_foreground), false));
//
//        pagerAppList.add(new PagerObject(appList1));
//        pagerAppList.add(new PagerObject(appList2));
//        pagerAppList.add(new PagerObject(appList3));
//
//        cellHeight = (getDisplayContentHeight() - DRAWER_PEEK_HEIGHT) / numRow ;
//
//        mViewPager = findViewById(R.id.viewPager);
//        mViewPagerAdapter = new ViewPagerAdapter(this, pagerAppList, cellHeight, numColumn);
//        mViewPager.setAdapter(mViewPagerAdapter);
//    }
//
//    List<AppObject> installedAppList = new ArrayList<>();
//    GridView mDrawerGridView;
//    BottomSheetBehavior mBottomSheetBehavior;
//    private void initializeDrawer() {
//        View mBottomSheet =findViewById(R.id.bottomSheet);
//        mDrawerGridView = findViewById(R.id.drawerGrid);
//        mBottomSheetBehavior = BottomSheetBehavior.from(mBottomSheet);
//        mBottomSheetBehavior.setHideable(false);
//        mBottomSheetBehavior.setPeekHeight(DRAWER_PEEK_HEIGHT);
//
//        installedAppList = getInstalledAppList();
//
//        mDrawerGridView.setAdapter(new AppAdapter(this, installedAppList, cellHeight));
//
//        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
//            @Override
//            public void onStateChanged(@NonNull View bottomSheet, int newState) {
//                if(mAppDrag != null)
//                    return;
//
//                if(newState == BottomSheetBehavior.STATE_COLLAPSED && mDrawerGridView.getChildAt(0).getY() != 0)
//                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//                if(newState == BottomSheetBehavior.STATE_DRAGGING && mDrawerGridView.getChildAt(0).getY() != 0)
//                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//            }
//
//            @Override
//            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
//            }
//        });
//
//    }
//
//    public AppObject mAppDrag = null;
//    public void itemPress(AppObject app){
//        if(mAppDrag != null && !app.getName().equals("")){
//            Toast.makeText(this,"Cell Already Occupied", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if(mAppDrag != null && !app.getIsAppInDrawer()){
//
//            app.setPackageName(mAppDrag.getPackageName());
//            app.setName(mAppDrag.getName());
//            app.setImage(mAppDrag.getImage());
//            app.setIsAppInDrawer(false);
//
//            if(!mAppDrag.getIsAppInDrawer()){
//                mAppDrag.setPackageName("");
//                mAppDrag.setName("");
//                mAppDrag.setImage(getResources().getDrawable(R.drawable.ic_launcher_foreground));
//                mAppDrag.setIsAppInDrawer(false);
//            }
//            mAppDrag = null;
//            mViewPagerAdapter.notifyGridChanged();
//            return;
//        }else{
//            Intent launchAppIntent = getApplicationContext().getPackageManager().getLaunchIntentForPackage(app.getPackageName());
//            if (launchAppIntent != null)
//                getApplicationContext().startActivity(launchAppIntent);
//        }
//    }
//
//    public void itemLongPress(AppObject app){
//        collapseDrawer();
//        mAppDrag = app;
//    }
//
//    private void collapseDrawer() {
//        mDrawerGridView.setY(DRAWER_PEEK_HEIGHT);
//        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//    }
//
//    private List<AppObject> getInstalledAppList() {
//        List<AppObject> list = new ArrayList<>();
//
//        Intent intent = new Intent(Intent.ACTION_MAIN, null);
//        intent.addCategory(Intent.CATEGORY_LAUNCHER);
//        List<ResolveInfo> untreatedAppList = getApplicationContext().getPackageManager().queryIntentActivities(intent, 0);
//
//        for(ResolveInfo untreatedApp : untreatedAppList){
//            String appName = untreatedApp.activityInfo.loadLabel(getPackageManager()).toString();
//            String appPackageName = untreatedApp.activityInfo.packageName;
//            Drawable appImage = untreatedApp.activityInfo.loadIcon(getPackageManager());
//
//            AppObject app = new AppObject(appPackageName, appName, appImage, true);
//            if (!list.contains(app))
//                list.add(app);
//        }
//        return list;
//    }
//
//    private int getDisplayContentHeight() {
//        final WindowManager windowManager = getWindowManager();
//        final Point size = new Point();
//        int screenHeight = 0, actionBarHeight = 0, statusBarHeight = 0;
//        if(getActionBar()!=null){
//            actionBarHeight = getActionBar().getHeight();
//        }
//
//        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
//        if(resourceId > 0){
//            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
//        }
//        int contentTop = (findViewById(android.R.id.content)).getTop();
//        windowManager.getDefaultDisplay().getSize(size);
//        screenHeight = size.y;
//        return screenHeight - contentTop - actionBarHeight - statusBarHeight;
//    }
//
//    private void getData(){
//        ImageView mHomeScreenImage = findViewById(R.id.homeScreenImage);
//        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
//        String imageUri = sharedPreferences.getString("imageUri", null);
//        int numRow = sharedPreferences.getInt("numRow", 7);
//        int numColumn = sharedPreferences.getInt("numColumn", 5);
//
//        if(this.numRow != numRow || this.numColumn != numColumn){
//            this.numRow = numRow;
//            this.numColumn = numColumn;
//            initializeHome();
//        }
//
//        if(imageUri != null)
//            mHomeScreenImage.setImageURI(Uri.parse(imageUri));
//
//    }
//    private void getPermissions() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
//            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
//        }
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        getData();
//    }