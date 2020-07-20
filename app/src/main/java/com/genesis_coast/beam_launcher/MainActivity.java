package com.genesis_coast.beam_launcher;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.WallpaperManager;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, NavigationView.OnNavigationItemSelectedListener {
    private ActionBarDrawerToggle mNavigationToggle;
    private DrawerLayout mNavigationDrawer;
    private TabLayout mCategoriesTab;
    private Toolbar mNavigationToolbar;
    private NavigationView mNavigationView;
    private ImageView mWallpaperView;
    private ViewPager mCategoriesPager;
    private ViewPagerAdapter mCategoriesPagerAdapter;
    private HomeNotificationListener mHomeNotificationListener;

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

        mNavigationView = findViewById(R.id.main_nv_navigation);
        mNavigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar()
                .setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeButtonEnabled(true);

        // Set the background.
        //checkPermissions();

        mWallpaperView.setImageDrawable(
                WallpaperManager
                        .getInstance(this)
                        .getDrawable()
        );

        setupCategories();
    }

    private void setupCategories() {
        // Setup the view pager.
        mCategoriesPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mCategoriesPagerAdapter.addFragment(new DirectionsFragment(), "Directions");
        mCategoriesPagerAdapter.addFragment(new PhoneFragment(), "Phone");
        mCategoriesPagerAdapter.addFragment(new HomeFragment(), "Home");
        mCategoriesPagerAdapter.addFragment(new MusicFragment(), "Music");
        mCategoriesPagerAdapter.addFragment(new FavouritesFragment(), "Favourites");

        // Finalize the view pager behaviour.
        mCategoriesPager.setAdapter(mCategoriesPagerAdapter);
        mCategoriesPager.addOnPageChangeListener(this);
        mCategoriesTab.setupWithViewPager(mCategoriesPager);

        // Set the tab icons.
        mCategoriesTab.getTabAt(0).setIcon(R.drawable.ic_directions_white);
        mCategoriesTab.getTabAt(1).setIcon(R.drawable.ic_phone_white);
        mCategoriesTab.getTabAt(2).setIcon(R.drawable.ic_home_white);
        mCategoriesTab.getTabAt(3).setIcon(R.drawable.ic_music_white);
        mCategoriesTab.getTabAt(4).setIcon(R.drawable.ic_favourites_white);
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            String[] permissions,
            int[] grantResults
    ) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(
                            MainActivity.this,
                            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                    {
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }

                return;
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.menu_main_navigation_settings:
                Fragment preferenceFragment = new SettingsFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.add(R.id.main_fl_settings, preferenceFragment);
                ft.commit();
            default:
                return true;
        }
    }
}