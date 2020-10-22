package com.example.XYZ.homeservice;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;


public class homeFragment extends Fragment {

    ViewFlipper vf;

    public homeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_home, container, false);

        vf=(ViewFlipper)view.findViewById(R.id.vf);
        int images[]={R.drawable.ac,R.drawable.wm,R.drawable.fridge};

        for(int image:images)
        {
            flipperImages(image);
        }


        return view;

    }

    private void flipperImages(int image) {
        ImageView imageView=new ImageView(getContext());
        imageView.setBackgroundResource(image);

        vf.addView(imageView);
        vf.setFlipInterval(3000);
        vf.setAutoStart(true);

        vf.setInAnimation(getContext(),android.R.anim.slide_in_left);
        vf.setInAnimation(getContext(),android.R.anim.slide_out_right);
    }

}
