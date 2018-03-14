package com.merveakgormus.speedy;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by vestel on 12.03.2018.
 */

public class LockService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();
    }

    @Override
    public  int onStartCommand(Intent intent, int flags, int startId){
        final IntentFilter filter= new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        final BroadcastReceiver broadcastReceiver = new  ScreenReceiver();
        registerReceiver(broadcastReceiver,filter);
        MainActivity main = new MainActivity();
        return START_STICKY; //Uygulama kapatılsa bile servisin arka planda çalışmaya devam etmesini sağlıyor!
    }
    public class LocalBinder extends Binder {
        LockService getService(){
            return LockService.this;
        }
    }
}
