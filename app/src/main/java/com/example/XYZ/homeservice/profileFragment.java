package com.example.XYZ.homeservice;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profileFragment extends Fragment {

    TextView tvname,tvphone;
    FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    Button btnSignOut;
    public profileFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        tvname=(TextView)view.findViewById(R.id.tvname);
        tvphone=(TextView)view.findViewById(R.id.tvphone);
        btnSignOut=(Button)view.findViewById(R.id.btnSignOut);
        FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User u=dataSnapshot.getValue(User.class);
                String name=u.getName().toString();
                String phone=u.getPhone().toString();
                String email=u.getEmail();

                tvname.setText(name);
                tvphone.setText(email+" | "+phone);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firebaseAuth.getInstance().signOut();
                Intent intent=new Intent(getContext(),MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
             //   startActivity(new Intent(getContext(),MainActivity.class));
            }
        });
    }
}
