package com.group9_3ITF.umatter;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Accounts {

    private String Username;
    private String Password;
    private String First_Name;
    private String Middle_Name;
    private String Last_Name;
    private String Phone;
    private String Email;


    private String lastMoodValue;

    public Accounts() {

    }

    public Accounts(String username, String password, String first_Name, String middle_Name, String last_Name, String phone, String email, String lastMoodValue) {
        this.Username = username;
        this.Password = password;
        this.First_Name = first_Name;
        this.Middle_Name = middle_Name;
        this.Last_Name = last_Name;
        this.Phone = phone;
        this.Email = email;
        this.lastMoodValue = lastMoodValue;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public void setFirst_Name(String first_Name) {
        this.First_Name = first_Name;
    }

    public String getMiddle_Name() {
        return Middle_Name;
    }

    public void setMiddle_Name(String middle_Name) {
        this.Middle_Name = middle_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String last_Name) {
        this.Last_Name = last_Name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        this.Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getLastMoodValue() {
        return lastMoodValue;
    }

    public void setLastMoodValue(String lastModeValue) {
        this.lastMoodValue = lastModeValue;
    }
}


