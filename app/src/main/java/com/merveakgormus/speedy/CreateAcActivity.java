package com.merveakgormus.speedy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

public class CreateAcActivity extends AppCompatActivity {

    private Button  btn_register;
    private TextView tv_backlogin;
    private EditText edt_namesurname, edt_mail, edt_password, edt_phone;
    private String name, mail, phone, password;

    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    String macadresi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ac);

        btn_register    = (Button)findViewById(R.id.btn_register);
        tv_backlogin    = (TextView)findViewById(R.id.tv_gologin);
        edt_namesurname = (EditText)findViewById(R.id.edt_namesurname);
        edt_mail        = (EditText)findViewById(R.id.edt_mail);
        edt_password    =(EditText)findViewById(R.id.edt_password);
        edt_phone       = (EditText)findViewById(R.id.edt_phone);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        macadresi = getUserMacAddr().toLowerCase().toString();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name      = edt_namesurname.getText().toString();
                mail      = edt_mail.getText().toString();
                phone     = edt_phone.getText().toString();
                password  = edt_password.getText().toString();

                if(mail.isEmpty() || password.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please fill in the required fields!",Toast.LENGTH_SHORT).show();
                }else{
                    UserRegister();
                    AddUser(name, mail, phone, password,macadresi);
                }
            }
        });
        tv_backlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateAcActivity.this, MainActivity.class));
            }
        });

    }

    public void UserRegister(){
        firebaseAuth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener(CreateAcActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(CreateAcActivity.this, EmergencyContactActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public  void AddUser(String uname,String umail,String uphone,String upassword, String udeviceId){
        User user = new User(uname, umail, uphone, upassword, udeviceId);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Users").child(udeviceId).setValue(user);
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
