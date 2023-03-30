package com.solgae.newoliving.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {

    private SharedPreferences pref;
    private Context mContext;

    public SharedPreferencesUtil(Context mContext) {
        this.mContext = mContext;
    }


    public String getSharedString(String XML_FILE_NAME, String key) {
        pref = mContext.getSharedPreferences(XML_FILE_NAME, Activity.MODE_PRIVATE);
        String json = pref.getString(key, "");
        return json;
    }

    public boolean getSharedString(String XML_FILE_NAME, String key, boolean base_value) {
        pref = mContext.getSharedPreferences(XML_FILE_NAME, Activity.MODE_PRIVATE);
        boolean json = pref.getBoolean(key, base_value);
        return json;
    }

    public void setSharedString(String XML_FILE_NAME, String key, String json) {
        pref = mContext.getSharedPreferences(XML_FILE_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, json);
        editor.commit();
    }

    public void setSharedString(String XML_FILE_NAME, String key, boolean json) {
        pref = mContext.getSharedPreferences(XML_FILE_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, json);
        editor.commit();
    }

    public void delShared(String XML_FILE_NAME, String key) {
        pref = mContext.getSharedPreferences(XML_FILE_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(key).commit();
    }
}
