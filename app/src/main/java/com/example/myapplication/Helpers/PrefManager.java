package com.example.myapplication.Helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.myapplication.app.MyApplication;

public class PrefManager {
        private static com.example.myapplication.Helpers.PrefManager instance;
        // Shared Preferences
        SharedPreferences pref;

        // Editor for Shared preferences
        SharedPreferences.Editor editor;

        // Context
        Context _context;

        // Shared pref mode
        int PRIVATE_MODE = 0;

        // Shared preferences file name
        private static final String PREF_NAME = "SampleApp";

        private static final String IS_LOGGEDIN_STATUS = "isLoggedin";
    private static final String KEY_MOBILE_NUMBER = "mobile";
    private static final String KEY_EMAIL = "email";


        public static synchronized com.example.myapplication.Helpers.PrefManager getInstance() {
            if (instance == null) {
                instance = new com.example.myapplication.Helpers.PrefManager(MyApplication.getContext());
            }
            return instance;
        }

        private PrefManager(Context context) {
            this._context = context;
            pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
            editor = pref.edit();
        }

        // login status //


        public boolean getIsLoggedinStatus() {
            return pref.getBoolean(IS_LOGGEDIN_STATUS,false);
        }

        public void setIsLoggedinStatus(boolean value){
            editor.putBoolean(IS_LOGGEDIN_STATUS, value);
            editor.commit();
        }



    // userName

    public void setemail(String name) {
        editor.putString(KEY_EMAIL, name);
        editor.commit();
    }

    public String getemail() {
        return pref.getString(KEY_EMAIL, null);
    }


    //    mobile
    public void setMobileNumber(String mobile) {
        editor.putString(KEY_MOBILE_NUMBER, mobile);
        editor.commit();
    }
    public String getMobileNumber() {
        return pref.getString(KEY_MOBILE_NUMBER, null);
    }

}