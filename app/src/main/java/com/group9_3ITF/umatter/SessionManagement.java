package com.group9_3ITF.umatter;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "session";
    String SESSION_KEY = "Current_LoggedIn";

    public SessionManagement(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSession(String username){
        editor.putString(SESSION_KEY, username).commit();

    }

    public String getSession(){
        return sharedPreferences.getString(SESSION_KEY, "null");
    }

    public void removeSession(){
        editor.putString(SESSION_KEY, "null").commit();
    }
}
