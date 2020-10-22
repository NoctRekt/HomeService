package com.example.XYZ.homeservice;

import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;



public class paging extends AppCompatActivity {
private ViewPager mPager;

//private int[] layouts={R.layout.activity_main,R.layout.activity_admin_login};
private MpagerAdapter mpagerAdapter;
/*private LinearLayout Dots_Layout;
private ImageView[] dots;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>19){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        setContentView(R.layout.activity_paging);
        mPager=(ViewPager)findViewById(R.id.viewPager);
        //mpagerAdapter=new MpagerAdapter(layouts,this);
        mPager.setAdapter(mpagerAdapter);
     //   Dots_Layout=(LinearLayout)findViewById(R.id.dotsLayout);
      //  createdots(0);
    }
/*
    private void createdots(int current_position){
        if(Dots_Layout!=null){
            Dots_Layout.removeAllViews();
            dots=new ImageView[layouts.length];

            for(int i=0;i<layouts.length;i++){
                dots[i]=new ImageView(this);
                if(i==current_position){
                dots[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.active_dots));
                }
                else {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.default_dots));
                }

                LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(4,0,4,0);

                Dots_Layout.addView(dots[i],params);
            }
        }
    }*/
}
