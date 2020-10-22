package com.example.XYZ.homeservice;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class PagerViewAdapter extends FragmentPagerAdapter {

    public PagerViewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        switch (position){
            case 0:
                homeFragment homeFragment=new homeFragment();
                return homeFragment;
            case 1:
                makegragment makefragment=new makegragment();
                return makefragment;
            case 2:
                profileFragment profilefragment=new profileFragment();
                return profilefragment;
            case 3:
                mycompFragment mycompFragment=new mycompFragment();
                return mycompFragment;


                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
