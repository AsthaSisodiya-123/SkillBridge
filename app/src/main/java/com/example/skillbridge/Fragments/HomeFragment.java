package com.example.skillbridge.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.AnimationTypes;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.skillbridge.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {


    ImageSlider imageSlider;
    GridLayout gridLayout1,gridLayout2;

    String[] technology_Name={"Artificial Intelligence (AI)","Full Stack Java Development"};
    int[] image={R.drawable.ai,R.drawable.java};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        imageSlider=view.findViewById(R.id.isMyCartImageSlider);


        ArrayList<SlideModel>slideModelArrayList=new ArrayList<>();
        slideModelArrayList.add(new SlideModel(R.drawable.ai, ScaleTypes.CENTER_CROP));
        slideModelArrayList.add(new SlideModel(R.drawable.cybersecurity, ScaleTypes.CENTER_CROP));
        slideModelArrayList.add(new SlideModel(R.drawable.web_development, ScaleTypes.CENTER_CROP));
        slideModelArrayList.add(new SlideModel(R.drawable.app_development, ScaleTypes.CENTER_CROP));
        slideModelArrayList.add(new SlideModel(R.drawable.java, ScaleTypes.CENTER_CROP));
        slideModelArrayList.add(new SlideModel(R.drawable.python, ScaleTypes.CENTER_CROP));
        slideModelArrayList.add(new SlideModel(R.drawable.cloud, ScaleTypes.CENTER_CROP));
        slideModelArrayList.add(new SlideModel(R.drawable.data_science, ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(slideModelArrayList);
        imageSlider.setSlideAnimation(AnimationTypes.BACKGROUND_TO_FOREGROUND);

        gridLayout1=view.findViewById(R.id.glHomeFragment);
        LayoutInflater inflater1=LayoutInflater.from(getActivity());


        for (int i=0;i<technology_Name.length;i++)
        {
            View view1=inflater1.inflate(R.layout.home_fragment_purchase_grid_list,null);
            ImageView ivCustomImage=view1.findViewById(R.id.technologyImage);
            TextView tvCustomName=view1.findViewById(R.id.technologyName);

            ivCustomImage.setImageResource(image[i]);
            tvCustomName.setText(technology_Name[i]);


            gridLayout1.addView(view1);

        }
        gridLayout2=view.findViewById(R.id.glHomeFragment1);
        LayoutInflater inflater2=LayoutInflater.from(getActivity());


        for (int i=0;i<technology_Name.length;i++)
        {
            View view2=inflater1.inflate(R.layout.home_fragment_latest_tech_list,null);
            ImageView ivCustomImage=view2.findViewById(R.id.latestTechnologyImage);
            TextView tvCustomName=view2.findViewById(R.id.latestTechnologyName);

            ivCustomImage.setImageResource(image[i]);
            tvCustomName.setText(technology_Name[i]);


            gridLayout2.addView(view2);

        }


        Toast.makeText(getActivity(),"My Cart",Toast.LENGTH_SHORT).show();
        return view;
    }

}