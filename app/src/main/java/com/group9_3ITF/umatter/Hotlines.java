package com.group9_3ITF.umatter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Hotlines extends AppCompatActivity {

    Button btnHotline1, btnHotline2, btnHotline3, btnHotline4, btnHotline5, btnHotlineBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotlines);

        btnHotline1 = findViewById(R.id.btnHotline1);
        btnHotline2 = findViewById(R.id.btnHotline2);
        btnHotline3 = findViewById(R.id.btnHotline3);
        btnHotline4 = findViewById(R.id.btnHotline4);
        btnHotline5 = findViewById(R.id.btnHotline5);
        btnHotlineBack = findViewById(R.id.btnHotlineBack);

        btnHotline1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0917-889-8727"));
                startActivity(intent);
            }
        });

        btnHotline2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0966-351-4518"));
                startActivity(intent);
            }
        });

        btnHotline3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0917-899-8727"));
                startActivity(intent);
            }
        });

        btnHotline4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0908-639-2672"));
                startActivity(intent);
            }
        });

        btnHotline5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:911"));
                startActivity(intent);
            }
        });

        btnHotlineBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Hotlines.this, MainScreen.class);
                startActivity(i);
                finish();
            }
        });



    }
}