package com.solgae.newoliving.utils;

import android.os.AsyncTask;
import android.util.Log;

import com.solgae.newoliving.base.InfintiApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SignageSchedule extends AsyncTask<String, Void, String> {
    private String str, receiveMsg;
    @Override
    protected String doInBackground(String... params) {
        URL url = null;
        try {
//            InfintiApplication.serial_number = "RW8GIY5R552126" ;
            String mac_code = "?f_data={\"mac_code\": \"" + InfintiApplication.serial_number +"\"}";
            url = new URL(params[0] + mac_code);


            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }
                receiveMsg = buffer.toString();
                Log.i("receiveMsg : ", receiveMsg);
                reader.close();

            } else {
                Log.i("통신 결과", conn.getResponseCode() + "에러");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return receiveMsg;
    }
}
