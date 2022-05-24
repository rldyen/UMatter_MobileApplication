package com.group9_3ITF.umatter;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Login extends AppCompatActivity {
    HashMap<String, String> account;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    final int mode = Activity.MODE_PRIVATE;
    final String MYPREFS = "User_Session";

    String username = new String("");
    String password = new String("");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("UMatter");
        account = new HashMap<>();
        ImageView imgLogoCrop = (ImageView) findViewById(R.id.imgLogoCrop);
        LinearLayout main = (LinearLayout) findViewById(R.id.main_layout);
        TextView tvLogin = (TextView) findViewById(R.id.tvLogin);
        TextView tvNewAcc = (TextView) findViewById(R.id.tvNewAcc);
        TextView tvSignup = (TextView) findViewById(R.id.tvSignUp);
        EditText etvUsername = (EditText) findViewById(R.id.etvUsername);
        EditText etvPass = (EditText) findViewById(R.id.etvPass);
        Button btnLogin = (Button)findViewById(R.id.btnLogin) ;

        main.setAlpha(0.0f);
        tvLogin.setAlpha(0.0f);
        tvNewAcc.setAlpha(0.0f);
        tvSignup.setAlpha(0.0f);
        etvUsername.setAlpha(0.0f);
        etvPass.setAlpha(0.0f);
        btnLogin.setAlpha(0.0f);

        etvUsername.setEnabled(false);
        etvPass.setEnabled(false);
        tvSignup.setEnabled(false);
        btnLogin.setEnabled(false);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                main.animate().alpha(0.95f).setDuration(500).start();
                tvLogin.animate().alpha(1.0f).setDuration(500).start();
                tvNewAcc.animate().alpha(1.0f).setDuration(500).start();
                tvSignup.animate().alpha(1.0f).setDuration(500).start();
                etvUsername.animate().alpha(1.0f).setDuration(500).start();
                etvPass.animate().alpha(1.0f).setDuration(500).start();
                btnLogin.animate().alpha(1.0f).setDuration(500).start();

            }
        }, 1500);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                etvUsername.setEnabled(true);
                etvPass.setEnabled(true);
                tvSignup.setEnabled(true);
                btnLogin.setEnabled(true);
            }
        }, 2000);



        Animation animation = new TranslateAnimation(-1000, -10,0, 0);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        imgLogoCrop.startAnimation(animation);

        tvSignup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v1) {
                Intent launchActivity = new Intent(Login.this, Register.class);
                startActivity(launchActivity);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username2 = etvUsername.getText().toString();
                String password2 = etvPass.getText().toString();
                checkUser(username2, password2);

                sharedPreferences = getSharedPreferences(MYPREFS, 0);
                editor = sharedPreferences.edit();
                SessionManagement sessionManagement = new SessionManagement(Login.this);
                sessionManagement.saveSession(username2);

            }
        });
    }

    void checkUser(String usernameTest, String passwordTest) {
        FirebaseDatabase database= FirebaseDatabase.getInstance("https://umatterfirebase-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("Accounts");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() != null) {
                    account = (HashMap<String, String>) dataSnapshot.getValue();
                    Iterator valueIterator = account.keySet().iterator();
                    for (Map.Entry<String, String> user : account.entrySet()) {
                        Log.v(TAG, "value" + valueIterator.next());
                        if (strcmp(user.getKey(), usernameTest))
                        {
                            String password = (String) dataSnapshot.child(user.getKey()).child("password").getValue();
                            if(strcmp(passwordTest, password))
                            {
                                Intent launchActivity = new Intent(Login.this, MainScreen.class);
                                startActivity(launchActivity);
                                myRef.removeEventListener(this);
                                return;
                            }
                            else {
                                Toast.makeText(Login.this, "Wrong password!", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else if(!valueIterator.hasNext())
                        {
                            Toast.makeText(Login.this, "User doesn't exist! Please Sign Up", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        //myRef.addListenerForSingleValueEvent(valueEventListener);
    }

    boolean strcmp(String one, String two) {
        if(one.equals(two)) {
            return true;
        }
        return false;
    }
}