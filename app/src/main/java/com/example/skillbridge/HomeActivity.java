package com.example.skillbridge;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.skillbridge.Fragments.HomeFragment;
import com.example.skillbridge.Fragments.ProfileFragment;
import com.example.skillbridge.Fragments.SettingFragment;
import com.example.skillbridge.Fragments.StoreFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    public boolean doubleTap = false;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        boolean isFirstTime= preferences.getBoolean("isFirstTime",true);

        if(isFirstTime)
        {
            welcome();
        }


        bottomNavigationView=findViewById(R.id.homeBottomNavIconView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.homeBottomNavHome);

    }

    private void welcome() {

        AlertDialog.Builder ad=new AlertDialog.Builder(this);
        ad.setTitle("SkillBridge");
        ad.setMessage("Welcome to SkillBridge");
        ad.setPositiveButton("Thank you", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).create().show();
        editor.putBoolean("isFirstTime",false).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.homeMenuContactUs) {
            Toast.makeText(HomeActivity.this, "Contact Us", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(HomeActivity.this, ContactUsActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.homeMenuAboutUs) {
            Toast.makeText(HomeActivity.this, "My Profile", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(HomeActivity.this, AboutUsActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.homeMenuHelpUs) {
            Toast.makeText(HomeActivity.this, "My Profile", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(HomeActivity.this, HelpUsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        if (doubleTap)
        {
            finishAffinity();
        }
        else
        {
            Toast.makeText(this,"Press again to exit",Toast.LENGTH_SHORT).show();

            doubleTap=true;
            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleTap=false;
                }
            },2000);
        }

    }

    HomeFragment homeFragment=new HomeFragment();
    StoreFragment storeFragment=new StoreFragment();
    ProfileFragment profileFragment=new ProfileFragment();
    SettingFragment settingFragment=new SettingFragment();


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
        if (menuitem.getItemId()==R.id.homeBottomNavHome)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFrameLayout, homeFragment).commit();

        }
        else if (menuitem.getItemId()==R.id.homeBottomNavStore)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFrameLayout,storeFragment).commit();

        }
        else if (menuitem.getItemId()==R.id.homeBottomNavProfile)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFrameLayout,profileFragment).commit();

        }
        else if (menuitem.getItemId()==R.id.homeBottomNavSettings) {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFrameLayout, settingFragment).commit();

        }
        return false;
    }

}