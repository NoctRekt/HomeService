package com.example.XYZ.homeservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ac extends AppCompatActivity {
Toolbar ac;
    EditText etadd;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button btnmake;
    TextView tvinfo;
    String selected,tvname,tvphone,tvmail;
    DatabaseReference databaseCustomer,dataComplaint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac);
        ac=(Toolbar)findViewById(R.id.ac);
        setSupportActionBar(ac);
        getSupportActionBar().setTitle("Air Conditoner");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        radioButton=(RadioButton)findViewById(R.id.rbService);
        radioButton=(RadioButton)findViewById(R.id.rbRepair);
        btnmake=(Button)findViewById(R.id.btnmake);
        tvinfo=(TextView)findViewById(R.id.tvinfo);
        etadd=(EditText)findViewById(R.id.etaddress);
        databaseCustomer = FirebaseDatabase.getInstance().getReference("customers");
        dataComplaint=FirebaseDatabase.getInstance().getReference("complaint");

        btnmake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                selected=radioButton.getText().toString();
                addCustomer();
            }
        });
        FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User u = dataSnapshot.getValue(User.class);
                tvname=u.getName();
                tvphone=u.getPhone();
tvmail=u.getEmail();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }
    private void addCustomer() {

        String type="Air Conditioner";
        String address = etadd.getText().toString().trim();
        if (address.isEmpty()) {
            etadd.setError("Please Provide Address");
            etadd.requestFocus();
            return;
        }

        if (!TextUtils.isEmpty(tvname)) {
            String myuid= FirebaseAuth.getInstance().getCurrentUser().getUid();
            String post_id = databaseCustomer.push().getKey();
            Customer customer = new Customer(post_id,myuid,tvname,tvmail,tvphone,address,selected,type);
            databaseCustomer.child(post_id).setValue(customer);

            //String post_id=dataComplaint.push().getKey();
            /*Post post=new Post(myuid,post_id);
            dataComplaint.child(myuid).setValue(post);*/

            Toast.makeText(getApplicationContext(), "Complaint Place Successfully", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"Complaint Place Unsuccessfully",Toast.LENGTH_SHORT).show();
        }

    }
}
