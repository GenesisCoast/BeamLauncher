package com.genesis_coast.beam_launcher;

import android.content.Context;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class HomeNotificationAdapter extends ArrayAdapter<StatusBarNotification> {
    private int mLayoutResourceID;

    private static final String TAG = "HomeNotificationAdapter";

    public HomeNotificationAdapter(Context context, int layoutResourceID) {
        super(context, layoutResourceID);
        mLayoutResourceID = layoutResourceID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            StatusBarNotification item = getItem(position);

            View view = null;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                view = inflater.inflate(mLayoutResourceID, null);

            } else {
                view = convertView;
            }

            TextView header = (TextView)view.findViewById(R.id.firstLine);

            header.setText(item.getPackageName());

            return view;
        }
        catch (Exception ex) {
            Log.e(TAG, "error", ex);
            return null;
        }
    }
}
