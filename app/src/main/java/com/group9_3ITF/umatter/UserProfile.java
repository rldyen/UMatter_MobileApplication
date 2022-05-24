package com.group9_3ITF.umatter;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserProfile extends AppCompatActivity {


    HashMap<String, String> account;
    HashMap<String, String> account2;
    String Firstname;
    String MiddleName;
    String FullName;
    String Mobile;
    String Email;
    String NewPassword;
    String NewPasswordConfirm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        EditText etvFirstname = (EditText) findViewById(R.id.etvFirstName);
        EditText etvMiddleName = (EditText) findViewById(R.id.etvMiddleName);
        EditText etvFullName = (EditText) findViewById(R.id.etvLastName);
        EditText etvMobile = (EditText) findViewById(R.id.etvMobile);
        EditText etvEmail = (EditText) findViewById(R.id.etvEmail);
        EditText etvNewPassword = (EditText) findViewById(R.id.etvNewPassword);
        EditText etvNewPasswordConfirm = (EditText) findViewById(R.id.etvNewPasswordConfirm);


        Button btnCancel = (Button) findViewById(R.id.btnCancel);
        Button btnSave = (Button) findViewById(R.id.btnRegisterSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Firstname = etvFirstname.getText().toString();
                MiddleName = etvMiddleName.getText().toString();
                FullName = etvFullName.getText().toString();
                Mobile = etvMobile.getText().toString() ;
                Email = etvEmail.getText().toString();
                NewPassword = etvNewPassword.getText().toString();
                NewPasswordConfirm = etvNewPasswordConfirm.getText().toString();


                SessionManagement s = new SessionManagement(UserProfile.this);
                String username = s.getSession();
                if(strcmp(NewPassword, NewPasswordConfirm))
                {
                    updateUserProfile(username, Firstname,MiddleName,FullName,Mobile,Email,NewPassword);
                }
                else
                {
                    Toast.makeText(UserProfile.this,"New password does not match, please reconfirm", Toast.LENGTH_LONG).show();
                }
            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserProfile.this, MainScreen.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void updateUserProfile(String username, String Firstname, String MiddleName, String FullName, String Mobile, String Email, String NewPassword) {
        FirebaseDatabase database= FirebaseDatabase.getInstance("https://umatterfirebase-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("Accounts");
        SessionManagement s = new SessionManagement(UserProfile.this);

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Intent i = new Intent(UserProfile.this, MainScreen.class);
                if(dataSnapshot.getValue() != null) {
                    account = (HashMap<String, String>) dataSnapshot.getValue();

                    for (Map.Entry<String, String> user : account.entrySet()) {
                        if (strcmp(user.getKey(), username))
                        {
                            myRef.child(username).child("email").setValue(Email);
                            myRef.child(username).child("first_Name").setValue(Firstname);
                            myRef.child(username).child("last_Name").setValue(FullName);
                            myRef.child(username).child("middle_Name").setValue(MiddleName);
                            myRef.child(username).child("password").setValue(NewPassword);
                            myRef.child(username).child("phone").setValue(Mobile);
                            Toast.makeText(UserProfile.this, "Success updating user prof!", Toast.LENGTH_LONG).show();
                            startActivity(i);
                            finish();
                            return;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        myRef.addListenerForSingleValueEvent(valueEventListener);




    }

    boolean strcmp(String passone, String passtwo){
        if(passone.equals(passtwo)) { return true; }
        return false;
    }
}