package com.merveakgormus.speedy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText edt_mail, edtpassword;
    private Button btn_login;
    private TextView tv_createac;
    private String email, pwd;

    FirebaseUser firebaseUser;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // progress = new ProgressDialog(this);
        startService(new Intent(getApplicationContext(), LockService.class));
        edt_mail = (EditText)findViewById(R.id.edt_mail);
        edtpassword = (EditText)findViewById(R.id.edt_password);
        btn_login = (Button)findViewById(R.id.btn_login);
        tv_createac = (TextView)findViewById(R.id.tv_createac);

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();

        tv_createac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreateAcActivity.class));
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = edt_mail.getText().toString();
                pwd = edtpassword.getText().toString();

                if(email.isEmpty() || pwd.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Gerekli alanları doldur.", Toast.LENGTH_LONG).show();
                }else{
                    LoginFunc();
                }

            }
        });

    }

    public void LoginFunc(){
        mAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(MainActivity.this, EmergencyContactActivity.class));
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
