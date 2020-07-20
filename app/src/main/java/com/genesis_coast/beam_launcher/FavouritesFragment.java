package com.genesis_coast.beam_launcher;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FavouritesFragment extends Fragment {


    List<AppObject> installedAppList = new ArrayList<>();
    GridView mDrawerGridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v= inflater.inflate(R.layout.fragment_favourites, container, false);

        initializeDrawer(v);

        return v;
    }

    private List<AppObject> getInstalledAppList() {
        List<AppObject> list = new ArrayList<>();

        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> untreatedAppList = getActivity().getApplicationContext().getPackageManager().queryIntentActivities(intent, 0);

        for(ResolveInfo untreatedApp : untreatedAppList){
            String appName = untreatedApp.activityInfo.loadLabel(getActivity().getApplicationContext().getPackageManager()).toString();
            String appPackageName = untreatedApp.activityInfo.packageName;
            Drawable appImage = untreatedApp.activityInfo.loadIcon(getActivity().getApplicationContext().getPackageManager());

            AppObject app = new AppObject(appPackageName, appName, appImage, true);
            if (!list.contains(app))
                list.add(app);
        }
        return list;
    }



    private void initializeDrawer(View v) {
        mDrawerGridView = v.findViewById(R.id.favourites_gv_apps);

        installedAppList = getInstalledAppList();

        mDrawerGridView.setAdapter(new AppAdapter(getActivity().getApplicationContext(), installedAppList, 50));

    }
}