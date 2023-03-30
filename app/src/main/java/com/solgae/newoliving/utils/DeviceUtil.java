package com.solgae.newoliving.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;


import com.solgae.newoliving.vo.device_info;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DeviceUtil {

    Context context;
    SharedPreferencesUtil sharedPreferencesUtil;

    public DeviceUtil(Context context) {
        this.context = context;

        // 환경설정파일
        sharedPreferencesUtil = new SharedPreferencesUtil(context);

    }

    public void SharedPreferences_Device_Infomation(device_info deviceInfo) {
        SharedPreferences pref = context.getSharedPreferences("device_info", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("shop_name", deviceInfo.getShop_name());
        editor.putString("serial_no", deviceInfo.getSerial_no());
        editor.putString("token", deviceInfo.getToken());
        editor.putString("groupcode", deviceInfo.getGroupcode());
        editor.putString("device_type", deviceInfo.getDevice_type());
        editor.putString("columm", deviceInfo.getColumm());
        editor.commit();
    }

    public device_info SharedPreferences_Device_Infomation() {
        SharedPreferences pref = context.getSharedPreferences("device_info", Activity.MODE_PRIVATE);

        device_info deviceInfo
                = new device_info(pref.getString("shop_name",""),
                pref.getString("serial_no",""),
                pref.getString("token",""),
                pref.getString("groupcode",""),
                pref.getString("device_type",""),
                pref.getString("columm",""));
        return  deviceInfo;
    }

    public String getSerialNumber() {
        String serialNumber;

        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class);

            serialNumber = (String) get.invoke(c, "gsm.sn1");
            if (serialNumber.equals(""))
                serialNumber = (String) get.invoke(c, "ril.serialnumber");
            if (serialNumber.equals(""))
                serialNumber = (String) get.invoke(c, "ro.serialno");
            if (serialNumber.equals(""))
                serialNumber = (String) get.invoke(c, "sys.serialnumber");
            if (serialNumber.equals(""))
                serialNumber = Build.SERIAL;

            // If none of the methods above worked
            if (serialNumber.equals("") || serialNumber.equalsIgnoreCase("unknown"))
                serialNumber = null;
        } catch (Exception e) {
            e.printStackTrace();
            serialNumber = null;
        }

        if (null == serialNumber)
            serialNumber = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);

        return serialNumber;
    }

// 화면정보를 구한다.
    public int[] display_metrics() {

        int[] wid0hig1 = {0,0};

        Display display = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        wid0hig1[0] = metrics.widthPixels;
        wid0hig1[1] = metrics.heightPixels;

        return wid0hig1;
    }


}
