package com.example.quora;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    TextView create;
    TextInputLayout txt_email, txt_password;
    String email_address, pass;
    Button login;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        InitUI();

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateEmail() | !validatePassword()) {

//                    return;
                } else {

                    Signin(email_address, pass);
                }
//                String email = txt_email.getText().toString();
//                String password = txt_password.getText().toString();
//                if(!email.isEmpty() && !password.isEmpty()) {
//                    Signin(email, password);
//                }
//                else
//                {
//                    Toast.makeText(LoginActivity.this, "Please fill all the fields properly", Toast.LENGTH_LONG).show();
//                }
            }
        });

    }
    private void Signin(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Invalid Username or Password", Toast.LENGTH_LONG).show();
                }
            }
        });
//        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful())
//                {
//                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                }
//                else
//                {
//                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
    }

    private void InitUI()
    {
        create = findViewById(R.id.create_one);
        txt_email = findViewById(R.id.txt_email);
        txt_password = findViewById(R.id.txt_password);
        login = findViewById(R.id.login_btn);
    }

    private boolean validateEmail() {
        email_address = txt_email.getEditText().getText().toString().trim();

        if (email_address.isEmpty()) {
            txt_email.setError("Field can't be empty");
            return false;
        } else {
            txt_email.setError(null);
            return true;
        }
    }
    private boolean validatePassword() {
        pass = txt_password.getEditText().getText().toString().trim();

        if (pass.isEmpty()) {
            txt_password.setError("Field can't be empty");
            return false;
        } else {
            txt_password.setError(null);
            return true;
        }
    }

}
