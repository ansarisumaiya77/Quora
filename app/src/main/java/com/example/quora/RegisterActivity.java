package com.example.quora;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText first_name, last_name, phone, email, password, confirm_password;
    Button register;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("users");

        InitUI();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname = first_name.getText().toString();
                String lastname = last_name.getText().toString();
                String contact = phone.getText().toString();
                String email_address = email.getText().toString();
                String pass = password.getText().toString();
                String c_pass = confirm_password.getText().toString();
                if(pass.toLowerCase() != c_pass.toLowerCase())
                {
                    SignUpMethod(firstname, lastname, contact, email_address, pass);
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, pass +" and "+ c_pass, Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void SignUpMethod(final String firstname, final String lastname, final String contact, String email_address, String pass) {
        auth.createUserWithEmailAndPassword(email_address, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    String key = task.getResult().getUser().getUid();
                    reference.child(key).child("First Name").setValue(firstname);
                    reference.child(key).child("Last Name").setValue(lastname);
                    reference.child(key).child("Contact").setValue(contact);
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void InitUI() {
        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);
        phone = findViewById(R.id.txt_phone);
        email = findViewById(R.id.txt_email);
        password = findViewById(R.id.txt_password);
        confirm_password = findViewById(R.id.confirm_password);
        register = findViewById(R.id.register_btn);
    }
}
