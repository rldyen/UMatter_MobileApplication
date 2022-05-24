package com.group9_3ITF.umatter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        setTitle("UMatter");

        ArrayList<View> viewsToFadeIn = new ArrayList<View>();

        Button btnMood = findViewById(R.id.btnMood);
        Button btnQuote = findViewById(R.id.btnQuote);
        Button btnProfile = findViewById(R.id.btnProfile);
        Button btnCalendar = findViewById(R.id.btnCalendar);
        Button btnDevs = findViewById(R.id.btnDevs);
        Button btnLogout = findViewById(R.id.btnLogout);
        Button btnHotlines = findViewById(R.id.btnHotlines);
        viewsToFadeIn.add(findViewById(R.id.btnMood));
        viewsToFadeIn.add(findViewById(R.id.btnQuote));
        viewsToFadeIn.add(findViewById(R.id.btnProfile));
        viewsToFadeIn.add(findViewById(R.id.btnCalendar));
        viewsToFadeIn.add(findViewById(R.id.btnDevs));
        viewsToFadeIn.add(findViewById(R.id.btnLogout));
        viewsToFadeIn.add(findViewById(R.id.btnHotlines));
        int k = 0;
        for (View v : viewsToFadeIn)
        {
            v.setAlpha(0.0f); // make invisible to start
            v.setEnabled(false);
        }

        Handler handler1 = new Handler();
        for (int a = 0; a < viewsToFadeIn.size(); a++) {
            int j = a;
            handler1.postDelayed(new Runnable() {

                @Override
                public void run() {

                    viewsToFadeIn.get(j).animate().alpha(1.0f).setDuration(300).start();
                    viewsToFadeIn.get(j).setEnabled(true);

                }
            }, 300 * a);

        }

        btnMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchActivity = new Intent(MainScreen.this, Mood.class);
                startActivity(launchActivity);
            }
        });

        btnQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchActivity = new Intent(MainScreen.this, DailyQuotes.class);
                startActivity(launchActivity);
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchActivity = new Intent(MainScreen.this, UserProfile.class);
                startActivity(launchActivity);
            }
        });

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchActivity = new Intent(MainScreen.this, ListMood.class);
                startActivity(launchActivity);
            }
        });

        btnDevs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchActivity = new Intent(MainScreen.this, DevelopersProfile.class);
                startActivity(launchActivity);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionManagement sessionManagement = new SessionManagement(MainScreen.this);
                sessionManagement.removeSession();
                clearSharedPrefs();
                Intent launchActivity = new Intent(MainScreen.this, Login.class);
                startActivity(launchActivity);
                finish();
            }
        });

        btnHotlines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchActivity = new Intent(MainScreen.this, Hotlines.class);
                startActivity(launchActivity);
            }
        });
    }

    public void clearSharedPrefs() {
        SharedPreferences mySharedPreferences;
        SharedPreferences.Editor myEditor;
        final int mode = Activity.MODE_PRIVATE;
        final String MYPREFS = "MyPreferences_001";

        mySharedPreferences = getSharedPreferences(MYPREFS, 0);
        myEditor = mySharedPreferences.edit();

        if (mySharedPreferences != null){
            myEditor.clear();
            myEditor.commit();
        }
    }
}