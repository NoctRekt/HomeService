package com.example.XYZ.homeservice;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

private  ArrayList<ExampleItem> mExampleList;
Context context;

    public ExampleAdapter(ArrayList<ExampleItem>exampleList, Context context){
        mExampleList=exampleList;
        this.context=context;
    }


    public static class ExampleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public ImageView mImageView;
    public TextView mType;
    ArrayList<ExampleItem>mExampleList=new ArrayList<ExampleItem>();
    Context context;

        public ExampleViewHolder(View itemView, Context context, ArrayList<ExampleItem> mExampleList) {
            super(itemView);
            this.context= context;
            this.mExampleList=mExampleList;
            itemView.setOnClickListener(this);
            mImageView=itemView.findViewById(R.id.mimageView);
            mType=itemView.findViewById(R.id.mtvtype);
        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            ExampleItem exampleItems=this.mExampleList.get(position);

            if(position==0) {
                Intent intent = new Intent(context, selected.class);
                intent.putExtra("type", exampleItems.getMtype());
                this.context.startActivity(intent);
            }
            if(position==1){
                Intent intent = new Intent(context, tv.class);
                intent.putExtra("type", exampleItems.getMtype());
                this.context.startActivity(intent);
            }
            if(position==2){
                Intent intent = new Intent(context, fridge.class);
                intent.putExtra("type", exampleItems.getMtype());
                this.context.startActivity(intent);
            }
            if(position==3){
                Intent intent = new Intent(context, ac.class);
                intent.putExtra("type", exampleItems.getMtype());
                this.context.startActivity(intent);
            }

            if(position==4){
                Intent intent = new Intent(context, mo.class);
                intent.putExtra("type", exampleItems.getMtype());
                this.context.startActivity(intent);
            }



        }
    }




    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item,parent,false);
       ExampleViewHolder evh=new ExampleViewHolder(v,context,mExampleList);
       return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        ExampleItem currentItem = mExampleList.get(position);
        holder.mImageView.setImageResource(currentItem.getmImage());
        holder.mType.setText(currentItem.getMtype());

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }


}
