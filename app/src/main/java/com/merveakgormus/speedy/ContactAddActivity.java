package com.merveakgormus.speedy;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ContactAddActivity extends AppCompatActivity {

    Button btn_add;
    EditText edt_contactname, edt_contactphone, edt_contactemail;
    String contactname, contactphone, contactemail, device_id;

    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    String deviceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_add);

        deviceId  = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);

        databaseReference = FirebaseDatabase.getInstance().getReference("Contacts");

        btn_add          = (Button)findViewById(R.id.btn_contactadd);
        edt_contactname  = (EditText)findViewById(R.id.edt_contactname);
        edt_contactphone = (EditText)findViewById(R.id.edt_contactphone);
        edt_contactemail = (EditText)findViewById(R.id.edt_contactemail);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactname  = edt_contactname.getText().toString();
                contactphone = edt_contactphone.getText().toString();
                contactemail = edt_contactemail.getText().toString();
                device_id    = deviceId;

                AddNewUser(contactname, contactphone, contactemail, device_id);

                Toast.makeText(ContactAddActivity.this,"Add Succes!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(ContactAddActivity.this, EmergencyContactActivity.class));

            }
        });
    }

    private void  AddNewUser(String ccontactname, String ccontactphone, String ccontactemail, String cdeviceid){

        Contact contact = new Contact(ccontactname, ccontactphone, ccontactemail);

        String ContactsIDFromServer = databaseReference.push().getKey();
        databaseReference.child(cdeviceid).child(ContactsIDFromServer).setValue(contact);
    }
}
