package com.example.XYZ.homeservice;

/**
 * Created by Milind on 9/2/2018.
 */

public class Post {
    String id;
    String uid;

    public Post(String id, String uid) {
        this.id = id;
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Post() {
    }
}
