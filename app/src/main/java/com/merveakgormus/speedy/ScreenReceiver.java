package com.merveakgormus.speedy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by vestel on 12.03.2018.
 */

public class ScreenReceiver extends BroadcastReceiver{

    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    public void onReceive(Context context, Intent intent) {
        databaseReference = FirebaseDatabase.getInstance().getReference("ScreenLockTime");

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){
            Log.e("Lock", "OF");

        }else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)){
            Log.e("Lock","ON");




            SimpleDateFormat bicim2=new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
            Date tarihSaat=new Date();
            String st =bicim2.format(tarihSaat);// 24-8-2014 02:17:02
            Log.e("Time : ", st);
            Bundle pudsBundle = intent.getExtras();
            Toast.makeText(context, st + "Screen On Canıms  ",
                    Toast.LENGTH_LONG).show();
            setTime(st);
        }
    }

    private void setTime(String screenontime){
        ScreenLockTime screenLockTime = new ScreenLockTime(screenontime);

        String ContactsIDFromServer = databaseReference.push().getKey();
        databaseReference.child(ContactsIDFromServer).setValue(screenLockTime);

    }
}
