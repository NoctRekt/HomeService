package com.example.XYZ.homeservice;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class makegragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mlayoutManager;

    public makegragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view= inflater.inflate(R.layout.fragment_makegragment, container, false);
        ArrayList<ExampleItem> exampleList=new ArrayList<>();
        exampleList.add(new ExampleItem(R.drawable.newwm,"Washing Machine"));
        exampleList.add(new ExampleItem(R.drawable.newtv,"Television"));
        exampleList.add(new ExampleItem(R.drawable.newfd,"Refrigerator"));
        exampleList.add(new ExampleItem(R.drawable.newac,"Air Conditioner"));
        exampleList.add(new ExampleItem(R.drawable.newmo,"MicroOven"));


        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        mlayoutManager=new LinearLayoutManager(getContext());
        mAdapter=new ExampleAdapter(exampleList,getContext());

        recyclerView.setLayoutManager(mlayoutManager);
        recyclerView.setAdapter(mAdapter);
       return view;
    }

}
