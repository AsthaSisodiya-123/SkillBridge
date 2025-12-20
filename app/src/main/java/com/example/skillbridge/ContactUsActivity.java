package com.example.skillbridge;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ContactUsActivity extends AppCompatActivity {

    EditText etMessage,etSubject,etEmailMsg;
    Button btnSend,btnSendEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#5C6ED1")));


        etMessage = findViewById(R.id.etMessage);
        etSubject=findViewById(R.id.etSubject);
        etEmailMsg=findViewById(R.id.etEmailMsg);
        btnSend = findViewById(R.id.btnSend);
        btnSendEmail = findViewById(R.id.btnSendEmail);

        if(ContextCompat.checkSelfPermission(ContactUsActivity.this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},111);
        }

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String strSMSMobileNo="8329431239";
                    String strSMSMessage=etMessage.getText().toString();

                    SmsManager smsManager=SmsManager.getDefault();
                    smsManager.sendTextMessage(strSMSMobileNo,null,strSMSMessage,null,null);
                    Toast.makeText(ContactUsActivity.this,"Send SMS Successfully",Toast.LENGTH_SHORT).show();

                    etMessage.setText(" ");
                } catch (Exception e) {
                    Toast.makeText(ContactUsActivity.this,"SMS failed to Send...Try Again",Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strEmail="astha.sisodiya0112@gmail.com";
                String strEmailSubject=etSubject.getText().toString();
                String strEmailMsg=etEmailMsg.getText().toString();

                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");

                intent.putExtra(Intent.EXTRA_EMAIL,new String[]{strEmail});
                intent.putExtra(Intent.EXTRA_SUBJECT,strEmailSubject);
                intent.putExtra(Intent.EXTRA_TEXT,strEmailMsg);

                try {
                    startActivity(Intent.createChooser(intent,"Choose an Email App"));
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(ContactUsActivity.this,"No Email App Installed",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
