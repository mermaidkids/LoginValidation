package com.example.loginvalidation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class SplashScreenActivity extends AppCompatActivity {

    public static final String FILENAME = "login";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                if(isLogin()){
                    startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                }else {
                    startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                }
                finish();
            }
        }, 3000);
    }

    boolean isLogin(){
        File sdcard = getFilesDir();
        File file = new File(sdcard, FILENAME);
        if(file.exists()){
            return true;
        }else {
            return false;
        }
    }


}
