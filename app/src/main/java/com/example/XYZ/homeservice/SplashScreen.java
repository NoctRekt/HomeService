package com.example.XYZ.homeservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {
    ImageView imageView;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        imageView=(ImageView)findViewById(R.id.imageView);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getSupportActionBar().hide();
        logoLauncher logoLauncher=new logoLauncher();
        logoLauncher.start();

    }

    private class logoLauncher extends Thread{
        public void run(){
            try{
                sleep(2000);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            Intent intent=new Intent(SplashScreen.this,MainActivity.class);
            startActivity(intent);
          SplashScreen.this.finish();

        }
    }
}

