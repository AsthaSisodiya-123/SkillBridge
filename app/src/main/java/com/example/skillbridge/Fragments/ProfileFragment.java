package com.example.skillbridge.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.skillbridge.EditProfile;
import com.example.skillbridge.R;
import com.google.android.material.imageview.ShapeableImageView;


public class ProfileFragment extends Fragment {
    TextView name,edit,myProfileName,myProfileMobileno,myProfileEmail,myProfileUsername;
    ShapeableImageView myProfileImage;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_profile, container, false);

        preferences= PreferenceManager.getDefaultSharedPreferences(getContext());
        editor=preferences.edit();
        name=view.findViewById(R.id.name);
        edit=view.findViewById(R.id.edit_profile);
        myProfileName=view.findViewById(R.id.tvMyProfileName);
        myProfileMobileno=view.findViewById(R.id.tvMyProfileMobileNo);
        myProfileEmail=view.findViewById(R.id.tvMyProfileEmail);
        myProfileUsername=view.findViewById(R.id.tvMyProfileUsername);
        myProfileImage=view.findViewById(R.id.ivMyProfileImage);

        name.setText(preferences.getString("name","Name not available" ));
        myProfileName.setText(preferences.getString("name","Name not available"));
        myProfileMobileno.setText(preferences.getString("mobileno","Mobile No not available"));
        myProfileEmail.setText(preferences.getString("emailid","Email not available"));
        myProfileUsername.setText(preferences.getString("username","Username not available"));

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), EditProfile.class);
                startActivity(intent);
            }
        });

        return view;
    }
}