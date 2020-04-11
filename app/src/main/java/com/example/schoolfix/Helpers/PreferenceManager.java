package com.example.schoolfix.Helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.schoolfix.R;

public class PreferenceManager {

    private final static String PREF_FILE="SESSION_FILE";
    private Context context;

//    private static final String PREF_NAME = "PREF_MANAGER";
//    private static final String KEY_VALUE = "AUTH_TOKEN";

//    private static PreferenceManager sInstance;
//    private final SharedPreferences mPref;

//    public PreferenceManager(Context context) {
//        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
//    }
//
//    public static synchronized void initializeInstance(Context context) {
//        if (sInstance == null) {
//            sInstance = new PreferenceManager(context);
//        }
//    }
//
//    public static synchronized PreferenceManager getInstance() {
//        if (sInstance == null) {
//            throw new IllegalStateException(PreferenceManager.class.getSimpleName() +
//                    " is not initialized, call initializeInstance(..) method first.");
//        }
//        return sInstance;
//    }
//
//    public void setValue(String value) {
//        mPref.edit()
//                .putString(KEY_VALUE, value)
//                .commit();
//    }
//
//    public String getValue() {
//        return mPref.getString(KEY_VALUE, null);
//    }
//
//    public void remove(String key) {
//        mPref.edit()
//                .remove(key)
//                .commit();
//    }

//    public boolean clear() {
//        return mPref.edit()
//                .clear()
//                .commit();
//    }

    public PreferenceManager(Context cont){
        this.context=cont;
    }

    public void saveAuthToken(String key, String value){
        final SharedPreferences prefs=context.getSharedPreferences(PREF_FILE,context.MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        editor.putString(key,value);
        editor.apply();
    }

    public  String fetchAuthToken(String key){
        SharedPreferences prefs=context.getSharedPreferences(PREF_FILE,context.MODE_PRIVATE);
        return prefs.getString(key,null);
    }

}
