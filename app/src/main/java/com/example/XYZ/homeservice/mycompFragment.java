package com.example.XYZ.homeservice;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class mycompFragment extends Fragment {
    ListView listView;
    FirebaseDatabase firebaseDatabase;
   // DatabaseReference databaseCustomer;
    Query databaseCustomer;
    String postid,type;
    Customer customer;

    ArrayList<String> customerList;
    ArrayAdapter<String> adapter;

    public mycompFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_mycomp, container, false);
        listView=(ListView)view.findViewById(R.id.listView);
        customerList = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
       // databaseCustomer = firebaseDatabase.getReference("customers");
        customer = new Customer();
        postid = customer.getPost();

        String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseCustomer = firebaseDatabase.getReference().child("customers").orderByChild("customerId").equalTo(currentUser);

        customerList=new ArrayList<>();
        adapter= new ArrayAdapter<String>(getContext(),R.layout.list_layout_my_post,R.id.tvInfo,customerList);
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        databaseCustomer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                customerList.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    customer = ds.getValue(Customer.class);
                    customerList.add(customer.getType()+" : "+customer.getDesc());
                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String cust=customerList.get(position);
                showDeleteDialog(cust);
                return false;
            }

        });
    }
    public void showDeleteDialog(String id){
        AlertDialog.Builder dialogBuilder=new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog,null);
        dialogBuilder.setView(dialogView);
        final Button btn=(Button) dialogView.findViewById(R.id.btnDelete);
        dialogBuilder.setTitle("Deleting complaint");
        AlertDialog alertDialog=dialogBuilder.create();
        alertDialog.show();



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                Query applesQuery = ref.child("customers").orderByChild("post").equalTo(postid);
                applesQuery.getRef().removeValue();
                Toast.makeText(getContext(), "deleted", Toast.LENGTH_SHORT).show();*/
        /*        Firebase firebase=new Firebase(Constant.FIREBASE_APP+id);
                firebase.orderByChild(id).equalTo(id).addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                dataSnapshot.getRef().removeValue();

                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });*/
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("customers");
                ref.orderByChild("post").equalTo(postid).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().removeValue();
                        Toast.makeText(getContext(), "deleted", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getContext(), "Un-deleted", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
