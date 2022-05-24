package com.group9_3ITF.umatter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class DailyQuotes extends AppCompatActivity {

    public static TextView tvQuote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_quotes);

        Button btnQuote = (Button) findViewById(R.id.btnQuote);
        Button btnQuoteBack = (Button) findViewById(R.id.btnQuoteBack);
        tvQuote = (TextView) findViewById(R.id.tvQuote);
        ImageView imgDailyQuote = (ImageView) findViewById(R.id.imgQuoteLogo);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        TextView tvQuoteTodayDate = (TextView) findViewById(R.id.tvQuoteTodayDate);

        tvQuoteTodayDate.setText(currentDate);

        imgDailyQuote.setImageResource(R.drawable.daily_quote_logo);

        btnQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FetchData process = new FetchData();
                process.execute();
                tvQuote.animate().alpha(0.0f).setDuration(400).start();
            }
        });

        btnQuoteBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DailyQuotes.this, MainScreen.class);
                startActivity(i);
                finish();
            }
        });
    }
}