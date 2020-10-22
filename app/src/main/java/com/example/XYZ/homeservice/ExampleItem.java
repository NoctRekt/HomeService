package com.example.XYZ.homeservice;

/**
 * Created by Milind on 9/22/2018.
 */

public class ExampleItem {
    private int mImage;
    private String mtype;

    public ExampleItem(int mImage, String mtype) {
        this.mImage = mImage;
        this.mtype = mtype;
    }

    public int getmImage() {
        return mImage;
    }

    public void setmImage(int mImage) {
        this.mImage = mImage;
    }

    public String getMtype() {
        return mtype;
    }

    public void setMtype(String mtype) {
        this.mtype = mtype;
    }
}
