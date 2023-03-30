package com.solgae.newoliving;

import android.hardware.usb.UsbDevice;
import android.os.Environment;

import com.solgae.newoliving.base.InfintiApplication;
import com.solgae.newoliving.vo.UsbDataBinder;
import com.solgae.newoliving.vo.contents;
import com.solgae.newoliving.vo.scheduleInfo;

import java.util.ArrayList;
import java.util.HashMap;

public class App {

//    public static final String ServerName = "http://ultra.iptime.org:7150/schedule.json";
//    public static final String ServerName = "http://15.165.226.158:7150/america/device_serial_schedule.php";

    public static final String ServerName = InfintiApplication.SERVER_URL + "/interface/api_schdule.php";
    public static final String BaseFolder = Environment.getExternalStorageDirectory().toString() + "/infinity";
    public static final String SCHEDULE_LOCAL_FOLDER_NAME = "infinity";
    public static HashMap<UsbDevice, UsbDataBinder> hashMap = new HashMap<UsbDevice, UsbDataBinder>();
    public static ArrayList<contents> contents_default;
    public static String ROOT_FOLDER_PATH = "/storage/emulated/0/";
    public static String FILE_DOWNLOAD_PATH = ROOT_FOLDER_PATH + "Download/";
    public static ArrayList<contents> contents_local;
    public ArrayList<contents> server_ary_contents;
    public scheduleInfo server_schedule_Info;
    public static final String SCHEDULE_FILE_NAME = "device_serial_schedule.php";
    public ArrayList<contents> ary_contents;
    public scheduleInfo schedule_Info;
    public static ArrayList<Long> download_id_list;
    public ArrayList<contents> current_ary_contents;
    public static int play_time = 0;
    public static int player_current_Index = 0;
    public boolean revision_exec = false;

}
