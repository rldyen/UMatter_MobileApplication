package com.group9_3ITF.umatter;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Register extends AppCompatActivity {

    EditText regUser;
    EditText regPass;
    EditText regConPass;
    EditText regFname;
    EditText regMname;
    EditText regLname ;
    EditText regPhone;
    EditText regEmail;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("UMatter");

        regUser = findViewById(R.id.etvRegUsername);
        regPass =  findViewById(R.id.etvRegPass);
        regConPass = findViewById(R.id.etvRegConPass);
        regFname = findViewById(R.id.etvRegFirstName);
        regMname = findViewById(R.id.etvRegMiddleName);
        regLname = findViewById(R.id.etvRegLastName);
        regPhone = findViewById(R.id.etvRegMobile);
        regEmail = findViewById(R.id.etvRegEmail);
        Button btnRegisterSave = findViewById(R.id.btnRegisterSave);
        Button btnRegisterCancel = findViewById(R.id.btnRegisterCancel);


        btnRegisterSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDataEntered();
            }

        });

        btnRegisterCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Register.this, Login.class);
                startActivity(i);
            }
        });

    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    void checkDataEntered(){
        String Password = regPass.getText().toString();
        String ConfirmPass = regConPass.getText().toString();
        if (isEmpty(regUser)) {
            regUser.setError("Username is required");
        }
        if (isEmpty(regPass)) {
            regPass.setError("Password is required");
        }
        else if (isEmpty(regConPass)) {
            regConPass.setError("Confirm Password is required");
        }

        else if (!Password.equals(ConfirmPass)){
            regConPass.setError("Passwords do not match");
        }

        if (isEmpty(regFname)) {
            regFname.setError("First Name is required");
        }
        if (isEmpty(regMname)) {
            regMname.setError("Middle Name is required");
        }
        if (isEmpty(regLname)) {
            regLname.setError("Last Name is required");
        }
        if (isEmpty(regPhone)) {
            regPhone.setError("Phone Number is required");
        }

        if (isEmpty(regEmail)) {
            regEmail.setError("Email is required");
        }
        else if (isEmail(regEmail) == false) {
            regEmail.setError("Enter a valid email!");
        }

        if((!isEmpty(regUser)) &&
                (!isEmpty(regFname)) &&
                (!isEmpty(regMname)) &&
                (!isEmpty(regLname)) &&
                (!isEmpty(regPhone)) &&
                (!isEmpty(regEmail)) &&
                (!isEmpty(regConPass)) &&
                (!isEmpty(regPass)) &&
                (isEmail(regEmail) == true) &&
                (Password.equals(ConfirmPass))){
            toDb();
            Toast.makeText(getApplicationContext(), "Account Successfully Registered!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            finish();
        }
    }

    void toDb(){


        FirebaseDatabase database = FirebaseDatabase.getInstance("https://umatterfirebase-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("Accounts");

        String username = regUser.getText().toString();
        String password = regPass.getText().toString();
        String first_name = regFname.getText().toString();
        String middle_name = regMname.getText().toString();
        String last_name = regLname.getText().toString();
        String phone = regPhone.getText().toString();
        String email = regEmail.getText().toString();
        String lastMoodValue = "none";

        Accounts accounts = new Accounts(username,password,first_name,middle_name,last_name,phone,email,lastMoodValue);
        String uniqueID = UUID.randomUUID().toString();
        myRef.child(username).setValue(accounts);

        regUser.setText(null);
        regPass.setText(null);
        regConPass.setText(null);
        regFname.setText(null);
        regMname.setText(null);
        regLname.setText(null);
        regPhone.setText(null);
        regEmail.setText(null);
    }
}