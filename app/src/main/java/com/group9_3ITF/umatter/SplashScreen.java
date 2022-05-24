package com.group9_3ITF.umatter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    private static final float ROTATE_FROM = 0.0f;
    private static final float ROTATE_TO = 360.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        setTitle("UMatter");

        TextView tvWelcome1 = (TextView) findViewById(R.id.tvWelcome1);
        TextView tvWelcome2 = (TextView) findViewById(R.id.tvWelcome2);
        tvWelcome1.setAlpha(0.0f);
        tvWelcome2.setAlpha(0.0f);

        ImageView appLogo = (ImageView) findViewById(R.id.imgLogoCrop);

        appLogo.setImageResource(R.drawable.umatter_logo);

        RotateAnimation r; // = new RotateAnimation(ROTATE_FROM, ROTATE_TO);
        r = new RotateAnimation(ROTATE_FROM, ROTATE_TO, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        r.setDuration((long) 1000);
        r.setRepeatCount(0);
        appLogo.startAnimation(r);

        Handler handler1 = new Handler();

        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                appLogo.animate().alpha(0.0f).setDuration(300).start();
            }
        }, 2000);

        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                tvWelcome1.animate().alpha(1.0f).setDuration(1000).start();
            }
        }, 2500);


        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                tvWelcome2.animate().alpha(1.0f).setDuration(1000).start();
            }
        }, 4000);

        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                tvWelcome1.animate().alpha(0.0f).setDuration(300).start();
                tvWelcome2.animate().alpha(0.0f).setDuration(300).start();
            }
        }, 6000);


        Handler handler = new Handler();
        handler.postDelayed(launchActivity, 6500);

    }

    private Runnable launchActivity = new Runnable(){
        public void run(){
            checkSession();
        }
    };


    private void checkSession() {
        //check if user is logged in
        //if user is logged in --> move to mainActivity

        SessionManagement sessionManagement = new SessionManagement(this);
        String username = sessionManagement.getSession();

        if(!username.equals("null")){
            //user id logged in and so move to mainActivity
            Intent i = new Intent(getApplicationContext(), MainScreen.class);
            startActivity(i);
            finish();
        }
        else
        {
            Intent i = new Intent(getApplicationContext(), Login.class);
            startActivity(i);
            finish();
        }
    }

}