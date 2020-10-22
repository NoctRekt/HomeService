package com.example.XYZ.homeservice;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class in extends AppCompatActivity {

    Button btnLogin;
    TextInputLayout etLEmail,etLPass;
    FirebaseAuth firebaseAuth;
    ProgressBar loginP;

    @Override
    protected void onStart() {
        super.onStart();
        if(firebaseAuth.getCurrentUser()!=null){
            startActivity(new Intent(in.this,home.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in);

        btnLogin=(Button)findViewById(R.id.btnLogin);
        etLEmail=(TextInputLayout) findViewById(R.id.etLEmail);
        etLPass=(TextInputLayout) findViewById(R.id.etLPass);
        loginP=(ProgressBar)findViewById(R.id.i);
        firebaseAuth=FirebaseAuth.getInstance();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });
    }

    private void userLogin() {

        String email=etLEmail.getEditText().getText().toString();
        String pass=etLPass.getEditText().getText().toString();
        if(email.length()==0)
        {
            etLEmail.setError("Email is Required");
            etLEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            etLEmail.setError("Enter Valid Email");
            etLEmail.requestFocus();
            return;
        }
        if(pass.length()==0)
        {
            etLPass.setError("Password is Required");
            etLPass.requestFocus();
            return;
        }
        if(pass.length()<6)
        {
            etLPass.setError("Minimum 6 charater are Required");
            etLPass.requestFocus();
            return;
        }
        loginP.setVisibility(View.VISIBLE);
        String userEmail=etLEmail.getEditText().getText().toString().trim();
        String userPass=etLPass.getEditText().getText().toString().trim();



        firebaseAuth.signInWithEmailAndPassword(userEmail,userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    checkEmailVerification();

                }
                else{
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

 private void checkEmailVerification(){
        FirebaseUser firebaseUser=firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag=firebaseUser.isEmailVerified();

        if(emailflag){
            finish();
            Toast.makeText(getApplicationContext(),"Logined",Toast.LENGTH_SHORT).show();
            loginP.setVisibility(View.GONE);
            startActivity(new Intent(in.this,home.class));
        }
        else{
            Toast.makeText(getApplicationContext(),"Verify Your Email",Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }
}
