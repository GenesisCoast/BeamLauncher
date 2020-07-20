package com.genesis_coast.beam_launcher;

import android.content.Intent;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

class HomeNotificationListener extends NotificationListenerService {
    private ArrayList<StatusBarNotification> mNotificationsArrayList = new ArrayList<>();
    private ArrayAdapter<StatusBarNotification> mNotificationArrayAdapter;

    public void configure(ArrayAdapter adapter) {
        mNotificationArrayAdapter = adapter;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification notification){
        mNotificationsArrayList.add(notification);
        mNotificationArrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification notification){
        mNotificationsArrayList.remove(notification);
        mNotificationArrayAdapter.notifyDataSetChanged();
    }
}
