package com.group9_3ITF.umatter;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

public class ListMood extends AppCompatActivity {
    HashMap<String, String> account2;
    ListView listview;
    ListView listview2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mood);

        account2 = new HashMap<>();

        Button back = findViewById(R.id.btnBack4);
        FirebaseDatabase database= FirebaseDatabase.getInstance("https://umatterfirebase-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("Accounts");

        SessionManagement s = new SessionManagement(ListMood.this   );
        String username = s.getSession();


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListMood.this, MainScreen.class);
                startActivity(intent);
            }
        });

        listview = (ListView) findViewById(R.id.listView);
        listview2 = (ListView)findViewById(R.id.listView2);

        final ArrayList<String> list = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, list);

        final ArrayList<String> list2 = new ArrayList<>();
        final ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, list2);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                list2.clear();
                if(dataSnapshot.getValue() != null) {
                    account2 = (HashMap<String, String>) dataSnapshot.child(username).getValue();
                    DataSnapshot sp = dataSnapshot.child(username).child("Mood");
                    for (Map.Entry<String, String> user : account2.entrySet()) {
                        Log.v(TAG, "User:" + user.getKey());
                        if(user.getKey().equals("Mood"))
                        {
                            account2 = (HashMap<String, String>) dataSnapshot.child(username).child("Mood").getValue();
                            for(Map.Entry<String, String> mood : account2.entrySet()) {
                                list.add(mood.getKey());
                                list2.add(mood.getValue().toString());
                            }

                        }
                    }
                    listview.setAdapter(adapter);
                    listview2.setAdapter(adapter2);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}