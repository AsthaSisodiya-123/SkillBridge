package com.example.skillbridge;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegistrationActivity extends AppCompatActivity {

    LinearLayout llRegistration;
    EditText signin_name,signin_mobileno,signin_emailid,signin_username,signin_passward;
    AppCompatButton btn_confirm;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

            llRegistration=findViewById(R.id.llRegistration);
            signin_name=findViewById(R.id.signin_name);
            signin_mobileno=findViewById(R.id.signin_mobileno);
            signin_emailid=findViewById(R.id.signin_emailid);
            signin_username=findViewById(R.id.signin_username);
            signin_passward=findViewById(R.id.signin_passward);
            btn_confirm=findViewById(R.id.btn_confirm);

            btn_confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (signin_name.getText().toString().isEmpty()) {
                        signin_name.setError("Enter Your Name");
                    } else if (signin_name.getText().toString().length() < 8) {
                        signin_name.setError("Name must contain at least 8 letters");
                    } else if (signin_mobileno.getText().toString().isEmpty()) {
                        signin_mobileno.setError("Enter Your Mobile No");
                    } else if (signin_mobileno.getText().toString().length() != 10) {
                        signin_mobileno.setError("Mobile No must contain 10 digits");
                    } else if (signin_emailid.getText().toString().isEmpty()) {
                        signin_emailid.setError("Enter Your Email Address");
                    } else if (!signin_emailid.getText().toString().contains("@gmail.com")) {
                        signin_emailid.setError("Invalid Email Address");
                    }else if (signin_passward.getText().toString().isEmpty()) {
                        signin_passward.setError("Enter your Password");
                    } else if (signin_passward.getText().toString().length() < 8) {
                        signin_passward.setError("Password must contain at least 8 digits");
                    } else if (!signin_passward.getText().toString().matches(".*[A-Z].*")) {
                        signin_passward.setError("Password contain at least 1 letter in uppercase");
                    } else if (!signin_passward.getText().toString().matches(".*[a-z].*")) {
                        signin_passward.setError("Password contain at least 1 letter in lowecase");
                    } else if (!signin_passward.getText().toString().matches(".*[0-9].*")) {
                        signin_passward.setError("Password contain at least 1 digit");
                    } else if (!signin_passward.getText().toString().matches(".*[@#$%^&+=!].*")) {
                        signin_passward.setError("Password contain at least 1 special symbol");
                    } else {
                        Intent intent=new Intent(RegistrationActivity.this,HomeActivity.class);
                        startActivity(intent);
                        finish();

                    }
                }
            });

        }

        @Override
        public void onBackPressed() {
            super.onBackPressed();
            Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
        }

    }
