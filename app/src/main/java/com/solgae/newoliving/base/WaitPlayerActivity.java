  package com.solgae.newoliving.base;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.ileaf.fullscreenmediacontrollerlibrary.FullScreenMediaController;
import com.ileaf.fullscreenmediacontrollerlibrary.FullScreenVideoCallBack;
import com.potyvideo.library.AndExoPlayerView;

//import com.solgae.signage.MainActivity;
import com.solgae.newoliving.App;
import com.solgae.newoliving.MainActivity;
import com.solgae.newoliving.R;
import com.solgae.newoliving.base.InfintiApplication;
import com.solgae.newoliving.busevent.BusProvider;
import com.solgae.newoliving.busevent.PushEvent;
import com.solgae.newoliving.service.BackService;
import com.solgae.newoliving.utils.DownloadVideo;
import com.solgae.newoliving.utils.LocalSchedule;
import com.solgae.newoliving.utils.SignageSchedule;
import com.solgae.newoliving.vo.MachineColummInfo;
import com.solgae.newoliving.vo.contents;
import com.solgae.newoliving.vo.scheduleInfo;
import com.solgae.newoliving.base.BaseActivity;
import com.squareup.otto.Subscribe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class WaitPlayerActivity extends BaseActivity implements FullScreenVideoCallBack, LocalSchedule.LocalSchedule_ThreadRecevie, View.OnClickListener {
    String video_url = "";
    int video_position;

    private FullScreenMediaController mediaControls;
    LocalSchedule localSchedule;
    Thread get_data_thread;
    private AndExoPlayerView andExoPlayerView;
    private int req_code = 129;
    App app;
    private Messenger mServiceMessenger = null;
    private boolean mIsBound;
    ImageView introWaiting;
    MachineColummInfo machineColummInfo;
    ArrayList<MachineColummInfo> Item = new ArrayList<>();
    int count = 0;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d("test","onServiceConnected");
            mIsBound = true;
            mServiceMessenger = new Messenger(iBinder);
            try {
                Message msg = Message.obtain(null, BackService.MSG_REGISTER_CLIENT);
                msg.replyTo = mMessenger;
                mServiceMessenger.send(msg);
            }
            catch (RemoteException e) {
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
        }
    };


    /** Service 로 부터 message를 받음 */
    private final Messenger mMessenger = new Messenger(new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(@NonNull Message message) {
            switch (message.what) {
                case BackService.MSG_SEND_TO_ACTIVITY:

                    String command = message.getData().getString("FROM_SERVICE");
                    Log.d("insdie", command);
                    Log.d("play time",app.play_time +"");
                    if (command.contains("TIME_")) {
                        if (!app.current_ary_contents.get(app.player_current_Index).getPlaytime().equalsIgnoreCase("00:00")) {
                            if (app.play_time < 1) {
                                sendMessageToService("STOP_TIME_CLOCK");
                                schedule_play_time(command);            // 다음컨텐츠
//                                Log.d("here","Next play");
                                Log.d("waittest","Next play");


                            } else {
                                app.play_time--;
                            }
                        }
                    } else if (command.contains("SERVICE_CONNECTED")) {

                        sendMessageToService("DOWNLOAD FILE");

                    }
                    else if( command.contains("DOWNLOAD_END")){
                        Log.d("inside", "Start Time Clock");
                        sendMessageToService("START_TIME_CLOCK");

                    }
                    else if( command.contains("Start_Download")){
                        DownloadVideo downloadVideo = new DownloadVideo(WaitPlayerActivity.this , mMessenger);
                        downloadVideo.execute();
                    }
                    break;

            }
            return false;
        }
    }));

    /** Service 로 메시지를 보냄 */
    private void sendMessageToService(String str) {
        if (mIsBound) {
            if (mServiceMessenger != null) {
                try {
                    Message msg = Message.obtain(null, BackService.MSG_SEND_TO_SERVICE, str);
                    msg.replyTo = mMessenger;
                    mServiceMessenger.send(msg);
                } catch (RemoteException e) {
                }
            }
        }
    }

    private void schedule_play_time(String data) {
        String[] play_time;
        String[] mtime = data.split(":");
        int image_index = 0;
        if (app.current_ary_contents.size()-1 > app.player_current_Index) {
            app.player_current_Index++;
        } else {
            app.player_current_Index = 0;
        }
        play_time = app.current_ary_contents.get(app.player_current_Index).getPlaytime().split(":");
        app.play_time = (Integer.parseInt(play_time[0]) * 60) +  Integer.parseInt(play_time[1]);

        try{
            if(InfintiApplication.startMain == true){
                if(count > 9){
                    Schedule_Alive();
                    count = 0;
                }
                count++;
            }

            andExoPlayerView.setSource(app.current_ary_contents.get(app.player_current_Index).getLocal_folder()+
                    app.current_ary_contents.get(app.player_current_Index).getFileName());
        }catch (RuntimeException e){
            Log.d("inside", e.toString());
            e.printStackTrace();
        }

        ///storage/emulated/0/sk_contents/ + signage.mp4
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_player);
        andExoPlayerView = findViewById(R.id.video_view);
        Log.d("waittest", "waitplayer created");
        introWaiting = findViewById(R.id.introWaiting);
        BusProvider.getInstance().register(this);
        Schedule_Alive();
        Log.d("inside", "myApp?");
        if(InfintiApplication.myapp !=  null){
            Log.d("inside", "we have app");
            app = InfintiApplication.myapp;
        }
        count = 0;
        Log.d("inside", "we don't have app");
        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();

        int w_width = Integer.parseInt(String.valueOf(Math.round(width * 0.88)));
        int w_height = Integer.parseInt(String.valueOf(Math.round(height * 0.13)));
        LinearLayout.LayoutParams introLisize = new LinearLayout.LayoutParams(w_width, w_height);
        int topMg = Integer.parseInt(String.valueOf(Math.round(height * 0.88)));
        introLisize.gravity = Gravity.CENTER;
        introLisize.setMargins(0,topMg,0,0);
//        introWaiting.setLayoutParams(introLisize);

        video_position = 0;
        Item = (ArrayList<MachineColummInfo>) InfintiApplication.machineMaster.getMachineColummInfo();
//        Item.get(0).getRemain_cnt();

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        //videoView.setLayoutParams(new FrameLayout.LayoutParams(metrics.widthPixels, metrics.heightPixels));
        InfintiApplication.startMain = true;

        try {
            //set the media controller in the VideoView
            if (mediaControls == null) {
                mediaControls = new FullScreenMediaController(this,true, this);
            }
//            videoView.setMediaController(mediaControls);
            //set the uri of the video to be played
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        andExoPlayerView.requestFocus();

        andExoPlayerView.setOnClickListener(this);
        andExoPlayerView.setOnMessageListener(new AndExoPlayerView.MessageEventListener() {
            @Override
            public void onMessageEventListener(String msg) {

                switch (msg) {
                    case "STATE_IDLE":
                        Log.d("waittest", "STATE_IDLE");
                        System.out.println();
                        break;
                    case "STATE_BUFFERING":
                        Log.d("waittest", "STATE_BUFFERING");
                        System.out.println();
                        break;
                    case "STATE_READY":
                        Log.d("waittest", "STATE_READY");
                        sendMessageToService("START_TIME_CLOCK");
                        break;
                    case "STATE_ENDED":
                        Log.d("waittest", "STATE_ENDED");
                        sendMessageToService("START_TIME_CLOCK");
                        break;
                    case "UNKNOWN_STATE":
                        Log.d("waittest", "UNKNOWN_STATE");
                        break;
                }
                System.out.println(">>>>>>> From VideoPlayer >>>>>>>>>>>>> " + msg);
            }
        });

        if(InfintiApplication.downloadMp4 == false){
            Log.d("waittest", "downloadMp4 == false");
            app = new App();
            startService(new Intent(WaitPlayerActivity.this, BackService.class));
            bindService(new Intent(this.getApplicationContext(), BackService.class), mConnection, Context.BIND_AUTO_CREATE);
            app.play_time = 0;
/**
 * 스캐쥴을 읽어들이는곳
 */
//로컬 스케쥴의 존재 여부 확인 -> 로컬 스케쥴이 없다면 onLocalScheduleScheduleReceiveRun
            localSchedule = new LocalSchedule(this);
            get_data_thread = new Thread(localSchedule);
            get_data_thread.start();
        }else{
            Log.d("waittest", "downloadMp4 == true");
            app.play_time = 0;
            Intent intent =  new Intent(this.getApplicationContext(), BackService.class);
            intent.putExtra("msg", "rebind");
            bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
            bindService(new Intent(this.getApplicationContext(), BackService.class), mConnection, Context.BIND_AUTO_CREATE);
            sendMessageToService("START_TIME_CLOCK");
        }
    }


    @Override
    public void onClick(View v) {
        andExoPlayerView.stopPlayer();
        BusProvider.getInstance().post(new PushEvent("HIDE_VIDEO"));
        WaitPlayerActivity.this.finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("waittest", "Destory Waitplayer");
//        BusProvider.getInstance().post(new PushEvent("ON_DESTROY", "WAITPLAYER"));
        if (mIsBound) {
            sendMessageToService("STOP_TIME_CLOCK");
            sendMessageToService("REMOVE_Messages");
            Log.d("inside", "unbind my service");
            unbindService(mConnection);
            mIsBound = false;
        }
    }


    private void loadMP4Locale(String filePath) {
        andExoPlayerView.setSource(filePath);
    }

    // 스케쥴 읽어오는 부분
    @Override
    public void onLocalScheduleScheduleReceiveRun(Object object) {
        get_data_thread.interrupt();
        app.current_ary_contents = new ArrayList<contents>();
        float local_version = 0;
        float server_version = 0;
        File sdcard = Environment.getExternalStorageDirectory();
        File file = new File(sdcard, "local_version.txt");
        File infinity = new File(sdcard, "infinity");
        FileOutputStream output = null;
        try {
            if (JsonToData(new SignageSchedule().execute(app.ServerName).get(), "SERVER").equalsIgnoreCase("SERVER")) {
                if (JsonToData((String)object, "LOCAL").equalsIgnoreCase("LOCAL")) {
//                    if (null!=app.schedule_Info)

                    if(file.exists()){
                        try {
                            StringBuilder text = new StringBuilder();
                            BufferedReader br = new BufferedReader(new FileReader(file));
                            String line;
                            while ((line = br.readLine()) != null) {
                                text.append(line);
                                text.append('\n');
                            }
                            br.close();
                            local_version=Float.parseFloat(text.toString());
                        } catch (IOException e) {
                            //You'll need to add proper error handling here
                        }
                    }else{
                        local_version=Float.parseFloat("0");
                    }
//                        local_version = Float.parseFloat(app.schedule_Info.getSchedule_version().equalsIgnoreCase("")?"0":app.schedule_Info.getSchedule_version());
                    if (null!=app.server_schedule_Info)
                        server_version = Float.parseFloat(app.server_schedule_Info.getSchedule_version().equalsIgnoreCase("")?"0":app.server_schedule_Info.getSchedule_version());

                    if(local_version < server_version){
                        try {
                            if(infinity.exists()){
                                setDirEmpty("/infinity");
                            }
                            output = new FileOutputStream(file);
                            String version = server_version + "";
                            output.write(version.getBytes());
                            output.close();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    app.current_ary_contents = (server_version<local_version?app.ary_contents:app.server_ary_contents);

                }
            }

            if (null == app.ary_contents && null == app.server_ary_contents) {
                app.current_ary_contents = new ArrayList<contents>();
                contents _contents = new contents(1,"","","","01:00",Environment.getExternalStorageDirectory()+"/Download/","성전암.mp4");
                app.current_ary_contents.add(_contents);
                String[] play_time = app.current_ary_contents.get(app.player_current_Index).getPlaytime().split(":");
                app.play_time = (Integer.parseInt(play_time[0]) * 60) +  Integer.parseInt(play_time[1]);

                andExoPlayerView.setSource(app.current_ary_contents.get(0).getLocal_folder()+app.current_ary_contents.get(0).getFileName());
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String JsonToData(String JsonData, String type) {
        ArrayList<contents> ary_contents = new ArrayList<contents>();
        scheduleInfo schedule_Info = new scheduleInfo();
        if (null!=JsonData) {
            try {
                JSONObject jsonObject = new JSONObject(JsonData);
                Gson gson = new Gson();
                JSONObject scheduleInfo = jsonObject.getJSONObject("scedule_ver");
                schedule_Info = gson.fromJson(scheduleInfo.toString(), scheduleInfo.class);

                if (null != schedule_Info) {
                        JSONArray jsonArray = jsonObject.getJSONArray("contents");
                    for (int i = 0; i < jsonArray.length(); i++) { //jsonObject에 담긴 두 개의 jsonObject를 jsonArray를 통해 하나씩 호출한다.
                        ary_contents.add(gson.fromJson(jsonArray.get(i).toString(), contents.class));
                    }

                    if (type.equalsIgnoreCase("LOCAL")){ // Local
                        app.schedule_Info = schedule_Info;
                        app.ary_contents  = ary_contents;
                    } else {
                        // Server
                        app.server_schedule_Info = schedule_Info;
                        app.server_ary_contents  = ary_contents;
                        InfintiApplication.myapp = app;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return type;
    }

    public boolean setDirEmpty(String dirName) {
        String path = Environment.getExternalStorageDirectory().toString() + dirName;
        File dir = new File(path);
        File[] childFileList = dir.listFiles();
        if (dir.exists()) {
            for (File childFile : childFileList) {
                if (childFile.isDirectory()) {
                    setDirEmpty(childFile.getAbsolutePath());
                    //하위 디렉토리
                } else {
                    childFile.delete();
                    //하위 파일
                }
            }
            dir.delete();
        }

        return true;
    }

    @Override
    public void navigateToFullScreenVideoScreen() {
        finish();
    }

    public void Schedule_Alive(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try  {

//                    http://dev.kepay.or.kr:8888/interface/api_schdule_alive.php?f_data={"mac_code":"RW8GIY5R55"}
                    String strUrl = InfintiApplication.SERVER_URL + "/interface/" + "api_schdule_alive.php?f_data={\"mac_code\":\"";
                    strUrl += InfintiApplication.serial_number + "\"}";;
                    URL url = new URL(strUrl);
                    String jsonFile;
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
                    if (conn.getResponseCode() == conn.HTTP_OK) {
                        InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                        BufferedReader reader = new BufferedReader(tmp);
                        StringBuffer buffer = new StringBuffer();
                        while ((jsonFile = reader.readLine()) != null) {
                            buffer.append(jsonFile);
                        }

                        Gson gson = new Gson();

                        JSONObject jsonObject = new JSONObject(buffer.toString());
                    }
                }catch(MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    Handler handler = new Handler();
    Runnable r = new Runnable() {
        @Override
        public void run() {
            Intent mintent = new Intent(WaitPlayerActivity.this, IntroActivity.class);
            startActivity(mintent);
        }
    };
    @Subscribe
    public void ReveiveOttoData(PushEvent event) {
        switch (event.getCommand()) {
            case "open":
                Context context = getApplicationContext();
                int colNum = Integer.parseInt(event.getData());
                Log.d("receiveNum", colNum + "");
                if (colNum == 0) {
                    //reboot
                    sendMessageToService("STOP_TIME_CLOCK");
                    finishAffinity();
                    handler.postDelayed(r, 10000);
                }
                break;

        }
    }
}
