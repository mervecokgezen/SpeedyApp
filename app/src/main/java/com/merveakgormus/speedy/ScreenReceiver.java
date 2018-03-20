package com.merveakgormus.speedy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by vestel on 12.03.2018.
 */

public class ScreenReceiver extends BroadcastReceiver{

    public String st, dy;

    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    String macadresi;

    @Override
    public void onReceive(Context context, Intent intent) {

        databaseReference = FirebaseDatabase.getInstance().getReference("ScreenUnLock");
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        macadresi = getUserMacAddr().toLowerCase().toString();

        if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){
            Log.e("Lock", "OF");

        }else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)){
            Log.e("Lock","ON");

            SimpleDateFormat bicim2=new SimpleDateFormat("hh:mm:ss");
            Date tarihSaat=new Date();
            st =bicim2.format(tarihSaat);//02:17:02

            Log.e("Time : ", st);

            SimpleDateFormat day = new SimpleDateFormat("dd-M-yyyy");
            Date date = new Date();
            dy = day.format(date);

            Bundle pudsBundle = intent.getExtras();

            AddUnLockTime(st, macadresi, dy);

        }
    }

    public void AddUnLockTime(String screenontime, String cdeviceid, String cday){
        ScreenLockTime screenLockTime = new ScreenLockTime(screenontime);
        databaseReference.child(cdeviceid).child(cday).setValue(screenLockTime);
        Log.e("\n\nDevice id: "+cdeviceid, "  Screen On Time: "+screenontime);
    }

    public static String getUserMacAddr()
    {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(Integer.toHexString(b & 0xFF) + ":");
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
        }
        return "null-mac";
    }

}
