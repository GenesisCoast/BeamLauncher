package com.genesis_coast.beam_launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class HomeFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(
                R.layout.fragment_home,
                container,
                false
        );

        ListView listView = v.findViewById(R.id.home_lv_notifications);

        HomeNotificationAdapter adapter = new HomeNotificationAdapter(
                getActivity().getApplicationContext(),
                R.layout.item_home_navigation
        );

        HomeNotificationListener listener = new HomeNotificationListener();

        listener.configure(adapter);
        listView.setAdapter(adapter);

        return v;
    }
}
