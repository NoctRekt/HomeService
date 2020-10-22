package com.example.XYZ.homeservice;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button btnReg;
    //Button btnSign;
    Button btnLogin;

    TextInputLayout etLEmail,etLPass;
    FirebaseAuth firebaseAuth;
    ProgressBar loginP;

    ViewPager viewPager;
    LinearLayout sliderDots;
    private int dotscount;
    private ImageView[] dots;

    @Override
    protected void onStart() {
        super.onStart();
        if(firebaseAuth.getCurrentUser()!=null){
            startActivity(new Intent(MainActivity.this,home.class));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnReg=(Button)findViewById(R.id.btnReg);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        etLEmail=(TextInputLayout) findViewById(R.id.etLEmail);
        etLPass=(TextInputLayout) findViewById(R.id.etLPass);
        loginP=(ProgressBar)findViewById(R.id.i);
        firebaseAuth=FirebaseAuth.getInstance();
      //  btnSign=(Button)findViewById(R.id.btnSign);
        viewPager=(ViewPager)findViewById(R.id.viewPager);
        sliderDots=(LinearLayout)findViewById(R.id.sliderDots);


        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

        dotscount=viewPagerAdapter.getCount();
        dots=new ImageView[dotscount];

        for(int i=0;i<dotscount;i++){
            dots[i]=new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.default_dots));

            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8,0,8,0);
            sliderDots.addView(dots[i],params);
        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.active_dots));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
    for (int i=0;i<dotscount;i++)
    {
    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.default_dots));
    }
    dots[position].setImageDrawable(ContextCompat.getDrawable(getApplication(),R.drawable.active_dots));
    }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });



        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,up.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });

       /* btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,in.class);
                startActivity(i);
            }
        });*/

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
                    loginP.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkEmailVerification() {
        FirebaseUser firebaseUser=firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag=firebaseUser.isEmailVerified();

        if(emailflag){
            this.finish();
            Toast.makeText(getApplicationContext(),"Logined",Toast.LENGTH_SHORT).show();
            loginP.setVisibility(View.GONE);
            Intent intent=new Intent(MainActivity.this,home.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(),"Verify Your Email",Toast.LENGTH_SHORT).show();
            loginP.setVisibility(View.GONE);
            firebaseAuth.signOut();
        }
    }
}
