package com.group9_3ITF.umatter;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Mood extends AppCompatActivity {


    HashMap<String, String> account;
    HashMap<String, String> account2;


    ImageButton imgBtnJoyful;
    ImageButton imgBtnHappy;
    ImageButton imgBtnNeutral;
    ImageButton imgBtnSad;
    ImageButton imgBtnAwful;

    Button btnMoodBack;

    View myLayoutVertical;
    TextView tvGoodSummaryDate;

    final int mode = Activity.MODE_PRIVATE;
    final String MYPREFS = "MyPreferences_001";

    ImageView imgMoodExpression;

    SharedPreferences mySharedPreferences;
    SharedPreferences.Editor myEditor;

    int bgColorJoyful = Color.rgb(255,255,90);
    int bgColorHappy = Color.rgb(156,255,87);
    int bgColorNeutral = Color.rgb(0,191,165);
    int bgColorSad = Color.rgb(24,255,255);
    int bgColorAwful = Color.rgb(68,138,255);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);
        setTitle("UMatter");


        retreiveLastImage();

        account = new HashMap<>();
        account2 = new HashMap<>();

        tvGoodSummaryDate = (TextView) findViewById(R.id.tvGoodSummaryDate);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        tvGoodSummaryDate.setText(currentDate);

        myLayoutVertical = (View) findViewById(R.id.linLayoutVertical);

        mySharedPreferences = getSharedPreferences(MYPREFS, 0);
        myEditor = mySharedPreferences.edit();

        imgMoodExpression = findViewById(R.id.imgMoodExpression);

        if (mySharedPreferences != null && mySharedPreferences.contains("backColor")){
            applySavedPreferences();
        }



        imgBtnJoyful = findViewById(R.id.imgBtnJoyful);
        imgBtnJoyful.setOnClickListener(this::onClick);

        imgBtnHappy = findViewById(R.id.imgBtnHappy);
        imgBtnHappy.setOnClickListener(this::onClick);

        imgBtnNeutral = findViewById(R.id.imgBtnNeutral);
        imgBtnNeutral.setOnClickListener(this::onClick);

        imgBtnSad = findViewById(R.id.imgBtnSad);
        imgBtnSad.setOnClickListener(this::onClick);

        imgBtnAwful = findViewById(R.id.imgBtnAwful);
        imgBtnAwful.setOnClickListener(this::onClick);

        btnMoodBack = findViewById(R.id.btnMoodBack);
        btnMoodBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myEditor.putString("DateLastExecution", new Date().toLocaleString());
                myEditor.commit();
                Intent i = new Intent(Mood.this, MainScreen.class);
                startActivity(i);
                finish();
            }
        });

    }

    public void onClick(View v){

        myEditor.clear();
        if (v.getId() == imgBtnJoyful.getId()){
            myEditor.putInt("layoutColor", bgColorJoyful);
            myEditor.putInt("backColor", bgColorJoyful);
            imgMoodExpression.setImageResource(R.drawable.mood_joyful_expression);
            applyHaxCodes("Joyful");
            //Bitmap icon = BitmapFactory.decodeResource(getResources(),R.drawable.mood_joyful_expression);
            //setMoodPreference(icon);
        }

        else if (v.getId() == imgBtnHappy.getId()){
            myEditor.putInt("layoutColor", bgColorHappy);
            myEditor.putInt("backColor", bgColorHappy);
            imgMoodExpression.setImageResource(R.drawable.mood_happy_expression);
            applyHaxCodes("Happy");
           // Bitmap icon = BitmapFactory.decodeResource(getResources(),R.drawable.mood_happy_expression);
           // setMoodPreference(icon);
        }

        else if (v.getId() == imgBtnNeutral.getId()){
            myEditor.putInt("layoutColor", bgColorNeutral);
            myEditor.putInt("backColor", bgColorNeutral);
            imgMoodExpression.setImageResource(R.drawable.mood_neutral_expression);
            applyHaxCodes("Neutral");
            //Bitmap icon = BitmapFactory.decodeResource(getResources(),R.drawable.mood_neutral_expression);
           // setMoodPreference(icon);
        }

        else if (v.getId() == imgBtnSad.getId()){
            myEditor.putInt("layoutColor", bgColorSad);
            myEditor.putInt("backColor", bgColorSad);
            imgMoodExpression.setImageResource(R.drawable.mood_sad_expression);
            applyHaxCodes("Sad");
            //Bitmap icon = BitmapFactory.decodeResource(getResources(),R.drawable.mood_sad_expression);
            //setMoodPreference(icon);
        }

        else if (v.getId() == imgBtnAwful.getId()){
            myEditor.putInt("layoutColor", bgColorAwful);
            myEditor.putInt("backColor", bgColorAwful);
            imgMoodExpression.setImageResource(R.drawable.mood_awful_expression);
            applyHaxCodes("Awful");
            //Bitmap icon = BitmapFactory.decodeResource(getResources(),R.drawable.mood_awful_expression);
            //setMoodPreference(icon);
        }

        myEditor.commit();
        applySavedPreferences();

    }

    protected void onPause() {
        myEditor.putString("DateLastExecution", new Date().toLocaleString());
        myEditor.commit();
        super.onPause();
    }

    public void applyHaxCodes(String value) {

        FirebaseDatabase database= FirebaseDatabase.getInstance("https://umatterfirebase-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("Accounts");
        //DatabaseReference myRef2 = database.getReference("StaticImages");

        SessionManagement s = new SessionManagement(Mood.this);
        String username = s.getSession();


        //Set images on firebase once
        /*
        Bitmap icon1 = BitmapFactory.decodeResource(getResources(),R.drawable.mood_joyful_expression);
        Bitmap icon2 = BitmapFactory.decodeResource(getResources(),R.drawable.mood_happy_expression);
        Bitmap icon3 = BitmapFactory.decodeResource(getResources(),R.drawable.mood_neutral_expression);
        Bitmap icon4 = BitmapFactory.decodeResource(getResources(),R.drawable.mood_sad_expression);
        Bitmap icon5 = BitmapFactory.decodeResource(getResources(),R.drawable.mood_awful_expression);
        String image1 = encodeToBase64(icon1);
        String image2 = encodeToBase64(icon2);
        String image3 = encodeToBase64(icon3);
        String image4 = encodeToBase64(icon4);
        String image5 = encodeToBase64(icon5);



        myRef2.child("imageJoyful").setValue(image1);
        myRef2.child("imageHappy").setValue(image2);
        myRef2.child("imageNeutral").setValue(image3);
        myRef2.child("imageSad").setValue(image4);
        myRef2.child("imageAwful").setValue(image5);
        */

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Date s = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = dateFormat.format(s);

                if(dataSnapshot.getValue() != null) {
                    account2 = (HashMap<String, String>) dataSnapshot.child(username).getValue();
                    DataSnapshot sp = dataSnapshot.child(username).child("Mood");
                    for (Map.Entry<String, String> user : account2.entrySet()) {
                        Log.v(TAG, "User:" + user.getKey());
                        if(user.getKey().equals("Mood"))
                        {
                            account2 = (HashMap<String, String>) sp.getValue();
                            for (Map.Entry<String, String> date : account2.entrySet()) {
                                //Edit the branch
                                //Compare sa date today
                                if(formattedDate.toString().equals(date.getKey())) //MAY API VERSIONS LANG
                                {
                                    //If match sa date change only the value and not the date
                                    date.setValue(value);
                                    myRef.child(username).child("lastMoodValue").setValue(value);
                                    Toast.makeText(Mood.this, "Changed mood for today", Toast.LENGTH_SHORT).show();
                                    myRef.removeEventListener(this);
                                    return;
                                }
                                if(!LocalDate.now().toString().equals(date.getKey()))
                                {
                                    //If d match sa date make a new date and new mood
                                    myRef.child(username).child("Mood").child(formattedDate.toString()).setValue(value);
                                    myRef.child(username).child("lastMoodValue").setValue(value);
                                    Toast.makeText(Mood.this, "Made a new mood", Toast.LENGTH_SHORT).show();
                                    myRef.removeEventListener(this);
                                    return;
                                }
                            }
                        }
                        else
                        {
                            //Kapag walang branch na mood make a new one

                            myRef.child(username).child("Mood").child(formattedDate.toString()).setValue(value);
                            myRef.child(username).child("lastMoodValue").setValue(value);
                            Toast.makeText(Mood.this, "Made a new mood", Toast.LENGTH_SHORT).show();
                            myRef.removeEventListener(this);
                            return;
                        }

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public boolean strcmp(String one, String two) {
        if(one.equals(two)) {
            return true;
        }
        return false;
    }

    public String encodeToBase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
    }

    public static Bitmap decodeToBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }

    public void applySavedPreferences(){
        int layoutColor = mySharedPreferences.getInt("layoutColor", Color.WHITE);
        myLayoutVertical.setBackgroundColor(layoutColor);
        /*
        String imageS = mySharedPreferences.getString("MoodImage", "");
        Bitmap imageB;
        if(!imageS.equals("")) {
            imageB = decodeToBase64(imageS);
            imgMoodExpression.setImageBitmap(imageB);
        }
         */
    }

    public void setMoodPreference(Bitmap image) {
        myEditor.clear();
        myEditor.putString("MoodImage", encodeToBase64(image));
        myEditor.commit();
        applySavedPreferences();
    }

    public void retreiveLastImage() {

        SessionManagement s = new SessionManagement(Mood.this);
        String username = s.getSession();

        FirebaseDatabase database= FirebaseDatabase.getInstance("https://umatterfirebase-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("Accounts");
        DatabaseReference myRef2 = database.getReference("StaticImages");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() != null) {
                    String lastmood = dataSnapshot.child(username).child("lastMoodValue").getValue().toString();
                    if(lastmood.equals("Joyful")) {
                        myRef2.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String image = snapshot.child("imageJoyful").getValue().toString();
                                byte[] decodedByte = Base64.decode(image, 0);
                                Bitmap imageBitmap = BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
                                imgMoodExpression.setImageBitmap(imageBitmap);
                                myRef2.removeEventListener(this);
                                return;
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                    else if(lastmood.equals("Happy")){
                        myRef2.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String image = snapshot.child("imageHappy").getValue().toString();
                                Log.v(TAG, "test" + image);
                                byte[] decodedByte = Base64.decode(image, 0);
                                Bitmap imageBitmap = BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
                                imgMoodExpression.setImageBitmap(imageBitmap);
                                myRef2.removeEventListener(this);
                                return;
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                    else if(lastmood.equals("Neutral")) {
                        myRef2.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String image = snapshot.child("imageNeutral").getValue().toString();
                                byte[] decodedByte = Base64.decode(image, 0);
                                Bitmap imageBitmap = BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
                                imgMoodExpression.setImageBitmap(imageBitmap);
                                myRef2.removeEventListener(this);
                                return;
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                    else if(lastmood.equals("Sad")) {
                        myRef2.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String image = snapshot.child("imageSad").getValue().toString();
                                byte[] decodedByte = Base64.decode(image, 0);
                                Bitmap imageBitmap = BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
                                imgMoodExpression.setImageBitmap(imageBitmap);
                                myRef2.removeEventListener(this);
                                return;
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                    else if(lastmood.equals("Awful")) {
                        myRef2.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String image = snapshot.child("imageAwful").getValue().toString();
                                byte[] decodedByte = Base64.decode(image, 0);
                                Bitmap imageBitmap = BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
                                imgMoodExpression.setImageBitmap(imageBitmap);
                                myRef2.removeEventListener(this);
                                return;
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                    else if(lastmood.equals("none")) {
                        return;
                    }
                    else {
                        return;
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}