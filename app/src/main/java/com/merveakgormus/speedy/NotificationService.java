package com.merveakgormus.speedy;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by vestel on 14.03.2018.
 */

public class NotificationService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate(){
        super.onCreate();
        notifiy();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startID){
        try {

        }catch (Exception e){
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startID);
    }
    public void notifiy(){
        IntentFilter filter = new IntentFilter();
        filter.addAction("RSS Pull Services");

        Intent myintent = new Intent(Intent.ACTION_VIEW, Uri.parse(""));
        PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 0, myintent, Intent.FLAG_ACTIVITY_NEW_TASK);
        Context context = getApplicationContext();

        Notification.Builder builder;

        builder=new Notification.Builder(context)
                .setContentTitle("Hey!").setContentText("İyi misin ? Seni merak ettim :)")
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setAutoCancel(true)    ;

        Notification notification = builder.build();

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);

    }
}
