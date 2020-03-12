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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    TextInputLayout first_name, last_name, phone, email, password, confirm_password;
    String firstname, lastname, contact, email_address, pass;
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
                if( !validateFirstname() | !validateLastname() | !validatePhone() | !validateEmail() | !validatePassword() | !validateConfirmPassword())
                {
                    return;
                }
                else {
                    SignUpMethod(firstname, lastname, contact, email_address, pass);
                }

            }
        });

    }

    private void SignUpMethod(final String firstname, final String lastname, final String contact, String email_address, String pass) {
        auth.createUserWithEmailAndPassword(email_address, pass).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
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

    private boolean validateFirstname() {
        firstname = first_name.getEditText().getText().toString().trim();

        if (firstname.isEmpty()) {
            first_name.setError("Field can't be empty");
            return false;
        } else if (firstname.length() > 8) {
            first_name.setError("Username too long");
            return false;
        } else {
            first_name.setError(null);
            return true;
        }
    }
    private boolean validateLastname() {
        lastname = last_name.getEditText().getText().toString().trim();

        if (lastname.isEmpty()) {
            last_name.setError("Field can't be empty");
            return false;
        } else if (lastname.length() > 8) {
            last_name.setError("Username too long");
            return false;
        } else {
            last_name.setError(null);
            return true;
        }
    }
    private boolean validatePhone() {
        contact = phone.getEditText().getText().toString().trim();

        if (contact.isEmpty()) {
            phone.setError("Field can't be empty");
            return false;
        } else {
            phone.setError(null);
            return true;
        }
    }
    private boolean validateEmail() {
        email_address = email.getEditText().getText().toString().trim();

        if (email_address.isEmpty()) {
            email.setError("Field can't be empty");
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }
    private boolean validatePassword() {
        pass = password.getEditText().getText().toString().trim();

        if (pass.isEmpty()) {
            password.setError("Field can't be empty");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }
    private boolean validateConfirmPassword() {
        String passwordInput = password.getEditText().getText().toString().trim();
        String confirmPasswordInput = confirm_password.getEditText().getText().toString().trim();

        if (confirmPasswordInput.isEmpty()) {
            confirm_password.setError("Field can't be empty");
            return false;
        }
        else if(!passwordInput.equals(confirmPasswordInput)){
            confirm_password.setError("Password doesn't match");
            return false;
        }
        else {
            confirm_password.setError(null);
            return true;
        }
    }
}
