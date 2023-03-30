package com.solgae.newoliving.service;

import android.app.DownloadManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.solgae.newoliving.App;
import com.solgae.newoliving.base.InfintiApplication;
import com.solgae.newoliving.receivers.ScheduleDownload;
import com.solgae.newoliving.receivers.UsbAttachReceiver;
import com.solgae.newoliving.receivers.UsbDetachReceiver;
import com.solgae.newoliving.receivers.UsbReceiver;
import com.solgae.newoliving.utils.AndroidSuUtils;
import com.solgae.newoliving.utils.VideoUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

//import com.solgae.signage.utils.StorageInfo;

public class BackService extends Service {
    private static final String ACTION_USB_PERMISSION = "com.dynamsoft.USB_PERMISSION";
    Messenger mFromActivity = null;   // Activity 에서 가져온 Messenger
    public static final int MSG_REGISTER_CLIENT = 1;
    public static final int MSG_SEND_TO_SERVICE = 3;
    public static final int MSG_SEND_TO_ACTIVITY = 4;
    Integer currentIndex = 0;

    DownloadManager downloadManager;
    private UsbManager mUsbManager;
    App app;
    private VideoUtils videoUtils;
    private String usb_stoeage;
    private UsbDetachReceiver mUsbDetachReceiver;
    private UsbAttachReceiver mUsbAttachReceiver;
    private UsbReceiver usbReceiver;
    private PendingIntent mPermissionIntent;
    AndroidSuUtils androidSuUtils;
    private ScheduleDownload scheduleDownload;
    int endIndex = 0;

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d("inside", "backservice destroyed");
    }
    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);

        Log.d("inside", "backservice rebind");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        app = new App();
        Log.d("inside", "service Created");
        videoUtils = new VideoUtils(this);
// DownLoad
        app.download_id_list = new ArrayList<Long>();
        downloadManager = (DownloadManager) getSystemService(this.DOWNLOAD_SERVICE);
// USB 인식
        mUsbManager      = (UsbManager) getSystemService(Context.USB_SERVICE);
        broadcast_receiver();
    }

    private void broadcast_receiver() {
        scheduleDownload  = new ScheduleDownload(this);
        mUsbDetachReceiver = new UsbDetachReceiver();
        mUsbAttachReceiver = new UsbAttachReceiver();
        usbReceiver = new UsbReceiver(mUsbManager);



        IntentFilter filter = new IntentFilter(UsbManager.ACTION_USB_DEVICE_ATTACHED);
//        registerReceiver(mUsbAttachReceiver , filter);

        filter = new IntentFilter(UsbManager.ACTION_USB_DEVICE_DETACHED);
//        registerReceiver(mUsbDetachReceiver , filter);

        mPermissionIntent = PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), 0);
        filter = new IntentFilter(ACTION_USB_PERMISSION);
//        registerReceiver(usbReceiver, filter);

        showDevices();
    }


    /**
     * USB가 연결되었을때
     */
    private void showDevices() {
        HashMap<String, UsbDevice> deviceList = mUsbManager.getDeviceList();
        String[] drive_dir = videoUtils.getStorageDirectories();
//        List<StorageInfo> fi = videoUtils.getStorageList();
        if (drive_dir.length==0) return;
        String[] usb_dev = drive_dir[0].split("/");
        usb_stoeage = "/storage/"+usb_dev[usb_dev.length-1];
        File directory = new File(usb_stoeage);
        File[] files = directory.listFiles();
        ArrayList<String> filenames = new ArrayList<String>();
        for (int i = 0; i < files.length; i++)
        {
            String file_name = files[i].getName();
            // you can store name to arraylist and use it later
            filenames.add(file_name);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

    /**
     * activity로부터 binding 된 Messenger
     */
    Handler thisHandler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(@NonNull Message message) {
            switch (message.what) {
                case MSG_REGISTER_CLIENT:
                    mFromActivity = message.replyTo;  // activity로부터 가져온
                    sendMsgToActivity("SERVICE_CONNECTED");
                    break;
                case MSG_SEND_TO_SERVICE:
                    switch (message.obj.toString()) {
                        case "START_TIME_CLOCK":
                            Log.d("inside service", "start time clock order");
                            start_time_clock();
                            break;
                        case "STOP_TIME_CLOCK":
                            stop_time_clock();
                            break;
                        case "DOWNLOAD FILE":
                            Log.d("inside", "unregister receiver");
                            sendMsgToActivity("Start_Download");
                            break;
                        case "REMOVE_Messages":

                            thisHandler.removeMessages(1);
                            thisHandler.removeMessages(3);
                            break;
                    }
                    break;

            }
            return false;
        }
    });
    /**
     * activity로부터 binding 된 Messenger
     */
    private Messenger mMessenger = new Messenger(thisHandler);


    private void sendMsgToActivity(String sendValue) {
        try {
            Bundle bundle = new Bundle();
            bundle.putString("FROM_SERVICE", sendValue);
            Log.d("inside", sendValue);
            Message msg = Message.obtain(null, MSG_SEND_TO_ACTIVITY);
            msg.setData(bundle);
            Log.d("inside", msg.toString());
            mFromActivity.send(msg);      // msg 보내기
        } catch (RemoteException e) {
            Log.d("inside", e.toString());
        }
    }


    Thread thread;
    NewRunnable newRunnable;
    private static Handler mHandler ;

    private void start_time_clock() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                Log.d("inside", simpleDateFormat.format(calendar.getTime()));
                if (null!=mFromActivity)
                    sendMsgToActivity("TIME_" + simpleDateFormat.format(calendar.getTime()));
            }
        };
        newRunnable = new NewRunnable() ;
        thread = new Thread(newRunnable) ;
        thread.start();

    }

    private void stop_time_clock() {
        newRunnable.stop();
    }

    class NewRunnable implements Runnable
    {
        private boolean stopped = false;

        @Override
        public void run() {
            while (!stopped) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace() ;
                }
                mHandler.sendEmptyMessage(0) ;
            }
        }
        public void stop() {
            stopped = true;
        }
    }


    /**
     *  파일 다운로드,
     */
    private long refid;
    private void contents_file_download() {
        boolean file_down_load = false;
        File directory = new File(App.BaseFolder);
        app = InfintiApplication.myapp;
        Log.d("inside", "contents_file_download");
//        getApplicationContext().registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        if (!directory.isDirectory()) {
            directory.mkdir();
        }else{

        }
        if (!app.current_ary_contents.isEmpty()) {
            app.download_id_list.clear();
            int i = 0;
            Log.d("inside", "predownload");



//            for (contents contents_ : app.current_ary_contents) {
//                Log.d("inside", "ready to download");
//                File file = new File(app.BaseFolder + File.separator + contents_.getFileName());
//                if(file.exists()){
//                    Log.d("inside", file.getPath());
//                    i++;
//                    if(app.current_ary_contents.size() == i){
//                        Log.d("inside", "download end");
//                        if(!file_down_load){ //동영상 파일을 다운받을 필요가 없다면
//                            sendMsgToActivity("DOWNLOAD_END");
//                        }
//
//                    }
//                }else{
//                    Log.d("inside", "number " + i + " is downloading");
////                    Uri Download_Uri = Uri.parse(contents_.getConvert_url().equalsIgnoreCase("")?contents_.getConvert_url():InfinitiApplication.VideoURI + contents_.getTitle());
//                    Uri Download_Uri = Uri.parse(InfintiApplication.VideoURI + contents_.getTitle());
//                    DownloadManager.Request request = new DownloadManager.Request(Download_Uri);
//                    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
//                    request.setAllowedOverRoaming(false);
//                    request.setTitle("GadgetSaint Downloading " + contents_.getConvert_url());
//                    request.setDescription("Downloading " + contents_.getConvert_url());
//                    request.setVisibleInDownloadsUi(true);
//                    request.setDestinationInExternalPublicDir(app.SCHEDULE_LOCAL_FOLDER_NAME, contents_.getFileName());
//                    refid = downloadManager.enqueue(request);
//                    app.download_id_list.add(refid);
//                    file_down_load = true;
//
//
//                    i++;
////                    if(app.current_ary_contents.size() == i){
////                        Log.d("inside", "download end");
////                        sendMsgToActivity("DOWNLOAD_END");
////                    }
//                }


//            }

        }
    }


}
