package com.example.skillbridge;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.skillbridge.Database.DBHelper;

public class LoginActivity extends AppCompatActivity {

    LinearLayout lllogin;
    EditText login_username,login_passward;
    AppCompatButton btn_login, btn_google;
    TextView login_newuser;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    DBHelper dbHelper; // ✅ Add DBHelper


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferences= PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        if (preferences.getBoolean("isLogin",false))
        {
            Intent intent=new Intent(this,HomeActivity.class);
            startActivity(intent);

        }
        lllogin=findViewById(R.id.llLogin);
        login_username=findViewById(R.id.login_username);
        login_passward=findViewById(R.id.login_passward);
        btn_login=findViewById(R.id.btn_login);
        btn_google=findViewById(R.id.btn_google);
        login_newuser=findViewById(R.id.login_newuser);

        dbHelper = new DBHelper(this);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strusername = login_username.getText().toString();
                String strpassword = login_passward.getText().toString();
                if (login_username.getText().toString().isEmpty()) {
                    login_username.setError("Please Enter Username");
                } else if (login_username.getText().toString().length() < 8) {
                    login_username.setError("Username must contain at least 8 letters");
                } else if (login_username.getText().toString().isEmpty()) {
                    login_passward.setError("Please Enter Password");
                } else if (login_passward.getText().toString().length() < 8) {
                    login_passward.setError("Password must contain at least 8 digits");
                } else if (!login_passward.getText().toString().matches(".*[A-Z].*")) {
                    login_passward.setError("Password contain at least 1 letter in uppercase");
                } else if (!login_passward.getText().toString().matches(".*[a-z].*")) {
                    login_passward.setError("Password contain at least 1 letter in lowecase");
                } else if (!login_passward.getText().toString().matches(".*[0-9].*")) {
                    login_passward.setError("Password contain at least 1 digit");
                } else if (!login_passward.getText().toString().matches(".*[@#$%^&+=!].*")) {
                    login_passward.setError("Password contain at least 1 special symbol");
                } else {
                    validateUser(strusername,strpassword);

                }

            }

        });

        login_newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }


private void validateUser(String strusername, String strpassword){
        if (dbHelper.loginUser(strusername,strpassword))
        {
            Toast.makeText(this,"Login Successfully Done",Toast.LENGTH_SHORT).show();
            Intent i=new Intent(LoginActivity.this,HomeActivity.class);
            editor.putBoolean("isLogin",true).commit();
            editor.putString("username",login_username.getText().toString()).commit();
            startActivity(i);
            finish();
        }
        else {
            Toast.makeText(this,"User Does Not Exists",Toast.LENGTH_SHORT).show();

        }
    }

}