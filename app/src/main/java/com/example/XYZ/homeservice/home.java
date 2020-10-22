package com.example.XYZ.homeservice;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class home extends AppCompatActivity {

    TextView mycomp,makecomp,profile,home;
    ViewPager mainPager;
    PagerViewAdapter pagerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mycomp=(TextView)findViewById(R.id.mycomp);
        makecomp=(TextView)findViewById(R.id.makecomp);
        profile=(TextView)findViewById(R.id.profile);
        home=(TextView)findViewById(R.id.home) ;
        mainPager=(ViewPager) findViewById(R.id.mainPager);

        pagerViewAdapter = new PagerViewAdapter(getSupportFragmentManager());
        mainPager.setAdapter(pagerViewAdapter);

        mycomp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPager.setCurrentItem(3);
            }
        });
        makecomp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPager.setCurrentItem(1);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPager.setCurrentItem(2);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPager.setCurrentItem(0);
            }
        });


        mainPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onPageSelected(int position) {
                changeTabs(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void changeTabs(int position) {

        if(position==3){
            mycomp.setTextColor(getColor(R.color.texttabBright));
            mycomp.setTextSize(15);

            makecomp.setTextColor(getColor(R.color.texttablight));
            makecomp.setTextSize(12);

            profile.setTextColor(getColor(R.color.texttablight));
            profile.setTextSize(12);

            home.setTextColor(getColor(R.color.texttablight));
            home.setTextSize(12);
        }

        if(position==1){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mycomp.setTextColor(getColor(R.color.texttablight));
            }
            mycomp.setTextSize(12);

            makecomp.setTextColor(getColor(R.color.texttabBright));
            makecomp.setTextSize(15);

            profile.setTextColor(getColor(R.color.texttablight));
            profile.setTextSize(12);

            home.setTextColor(getColor(R.color.texttablight));
            home.setTextSize(12);
        }

        if(position==2){
            mycomp.setTextColor(getColor(R.color.texttablight));
            mycomp.setTextSize(12);

            makecomp.setTextColor(getColor(R.color.texttablight));
            makecomp.setTextSize(12);

            profile.setTextColor(getColor(R.color.texttabBright));
            profile.setTextSize(15);

            home.setTextColor(getColor(R.color.texttablight));
            home.setTextSize(12);
        }

        if(position==0){
            mycomp.setTextColor(getColor(R.color.texttablight));
            mycomp.setTextSize(12);

            makecomp.setTextColor(getColor(R.color.texttablight));
            makecomp.setTextSize(12);

            profile.setTextColor(getColor(R.color.texttablight));
            profile.setTextSize(12);

            home.setTextColor(getColor(R.color.texttabBright));
            home.setTextSize(15);
        }



    }
}
