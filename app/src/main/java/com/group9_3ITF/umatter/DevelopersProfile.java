package com.group9_3ITF.umatter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class DevelopersProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developers_profile);
        ImageButton imgbtnDequito = (ImageButton) findViewById(R.id.imgbtnDequito);
        ImageButton imgbtnPaloma = (ImageButton) findViewById(R.id.imgbtnPaloma);
        ImageButton imgbtnSantiago = (ImageButton) findViewById(R.id.imgbtnSantiago);
        ImageButton imgbtnSoriano = (ImageButton) findViewById(R.id.imgbtnSoriano);
        ImageView imgDeveloperProfilePicture = (ImageView) findViewById(R.id.imgDeveloperProfilePicture);
        Button btnBack = (Button) findViewById(R.id.btnDeveloperBack);
        TextView tvDeveloperName = (TextView) findViewById(R.id.tvDeveloperName);
        TextView tvDeveloperEmail = (TextView) findViewById(R.id.tvDeveloperEmail);
        TextView tvDeveloperPhone = (TextView) findViewById(R.id.tvDeveloperPhone);
        TextView tvReferences = (TextView) findViewById(R.id.tvReferences);

        imgbtnDequito.setImageResource(R.drawable.developer_dequito);
        imgbtnPaloma.setImageResource(R.drawable.developer_paloma);
        imgbtnSantiago.setImageResource(R.drawable.developer_santiago);
        imgbtnSoriano.setImageResource(R.drawable.developer_soriano);

        imgDeveloperProfilePicture.setAlpha(0.0f);
        tvDeveloperName.setAlpha(0.0f);
        tvDeveloperEmail.setAlpha(0.0f);
        tvDeveloperPhone.setAlpha(0.0f);

        tvReferences.setText("Zen Quotes API: https://zenquotes.io/ \n" +
                "Android Studio Vector Assets: https://developer.android.com/studio/write/vector-asset-studio \n" +
                "Logo and other Images made by: UMatter Team");

        imgbtnDequito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgDeveloperProfilePicture.animate().alpha(0.0f).setDuration(500).start();
                tvDeveloperName.animate().alpha(0.0f).setDuration(500).start();
                tvDeveloperEmail.animate().alpha(0.0f).setDuration(500).start();
                tvDeveloperPhone.animate().alpha(0.0f).setDuration(500).start();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgDeveloperProfilePicture.setImageResource(R.drawable.developer_dequito);
                        tvDeveloperName.setText(("John Ezequiel Dequito"));
                        tvDeveloperEmail.setText("Email: johnezequiel.dequito.cics@ust.edu.ph");
                        tvDeveloperPhone.setText("+639123456789");
                        imgDeveloperProfilePicture.animate().alpha(1.0f).setDuration(500).start();
                        tvDeveloperName.animate().alpha(1.0f).setDuration(500).start();
                        tvDeveloperEmail.animate().alpha(1.0f).setDuration(500).start();
                        tvDeveloperPhone.animate().alpha(1.0f).setDuration(500).start();
                    }
                },600);


            }
        });

        imgbtnPaloma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgDeveloperProfilePicture.animate().alpha(0.0f).setDuration(500).start();
                tvDeveloperName.animate().alpha(0.0f).setDuration(500).start();
                tvDeveloperEmail.animate().alpha(0.0f).setDuration(500).start();
                tvDeveloperPhone.animate().alpha(0.0f).setDuration(500).start();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgDeveloperProfilePicture.setImageResource(R.drawable.developer_paloma);
                        tvDeveloperName.setText(("Dale Miguel Paloma"));
                        tvDeveloperEmail.setText("Email: dalemiguel.paloma.cics@ust.edu.ph");
                        tvDeveloperPhone.setText("+639053176417");
                        imgDeveloperProfilePicture.animate().alpha(1.0f).setDuration(500).start();
                        tvDeveloperName.animate().alpha(1.0f).setDuration(500).start();
                        tvDeveloperEmail.animate().alpha(1.0f).setDuration(500).start();
                        tvDeveloperPhone.animate().alpha(1.0f).setDuration(500).start();
                    }
                },600);

            }
        });

        imgbtnSantiago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgDeveloperProfilePicture.animate().alpha(0.0f).setDuration(500).start();
                tvDeveloperName.animate().alpha(0.0f).setDuration(500).start();
                tvDeveloperEmail.animate().alpha(0.0f).setDuration(500).start();
                tvDeveloperPhone.animate().alpha(0.0f).setDuration(500).start();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgDeveloperProfilePicture.setImageResource(R.drawable.developer_santiago);
                        tvDeveloperName.setText(("Gabriel Luis Santiago"));
                        tvDeveloperEmail.setText("Email: gabrielluis.santiago.cics@ust.edu.ph");
                        tvDeveloperPhone.setText("+639216654632");
                        imgDeveloperProfilePicture.animate().alpha(1.0f).setDuration(500).start();
                        tvDeveloperName.animate().alpha(1.0f).setDuration(500).start();
                        tvDeveloperEmail.animate().alpha(1.0f).setDuration(500).start();
                        tvDeveloperPhone.animate().alpha(1.0f).setDuration(500).start();
                    }
                },600);

            }
        });

        imgbtnSoriano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgDeveloperProfilePicture.animate().alpha(0.0f).setDuration(500).start();
                tvDeveloperName.animate().alpha(0.0f).setDuration(500).start();
                tvDeveloperEmail.animate().alpha(0.0f).setDuration(500).start();
                tvDeveloperPhone.animate().alpha(0.0f).setDuration(500).start();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgDeveloperProfilePicture.setImageResource(R.drawable.developer_soriano);
                        tvDeveloperName.setText(("Roald Yen Soriano"));
                        tvDeveloperEmail.setText("Email: roaldyen.soriano.cics@ust.edu.ph");
                        tvDeveloperPhone.setText("+639997067149");
                        imgDeveloperProfilePicture.animate().alpha(1.0f).setDuration(500).start();
                        tvDeveloperName.animate().alpha(1.0f).setDuration(500).start();
                        tvDeveloperEmail.animate().alpha(1.0f).setDuration(500).start();
                        tvDeveloperPhone.animate().alpha(1.0f).setDuration(500).start();
                    }
                },600);

            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DevelopersProfile.this, MainScreen.class);
                startActivity(i);
                finish();
            }
        });
    }

}