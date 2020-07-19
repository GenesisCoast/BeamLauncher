package com.genesis_coast.beam_launcher;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FavouritesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v= inflater.inflate(R.layout.fragment_favourites, container, false);
        TextView textView=v.findViewById(R.id.text);
        textView.setText("Favourites Fragment");
        return v;
    }
}