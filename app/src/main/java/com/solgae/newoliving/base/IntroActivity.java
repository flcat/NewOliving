package com.solgae.newoliving.base;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.RemoteException;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.solgae.newoliving.BuildConfig;
import com.solgae.newoliving.MainActivity;
import com.solgae.newoliving.R;
import com.solgae.newoliving.busevent.BusProvider;
import com.solgae.newoliving.busevent.PushEvent;
import com.solgae.newoliving.localdb.DataBases;
import com.solgae.newoliving.localdb.DbOpenHelper;
import com.solgae.newoliving.service.Helper;
import com.solgae.newoliving.service.QvingService;
import com.solgae.newoliving.utils.DeviceUtil;
import com.solgae.newoliving.utils.GetMachineInitItem;
import com.solgae.newoliving.vo.MachineColummInfo;
import com.solgae.newoliving.vo.MachineMaster;
import com.solgae.newoliving.vo.ResponseHeader;
import com.solgae.newoliving.vo.food_info;
import com.solgae.newoliving.vo.store_info;
import com.solgae.qvingservice.IRemoteService;
import com.solgae.qvingservice.IRemoteServiceCallback;
import com.squareup.otto.Subscribe;
import com.squareup.phrase.Phrase;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class IntroActivity extends BaseActivity
        implements GetMachineInitItem.Machine_Data_Receive {


    private DbOpenHelper mDbOpenHelper;
    Intent remoteIntent;
    CharSequence greeting;
    DeviceUtil deviceUtil;
    String strUrl;
    private Thread get_machine_item_thread;
    // 서버의 전송과 응답에따른 데이타값
    static String server_respones_result_type = "";
    private ResponseHeader responseHeader;
    private ProgressDialog mProgressDialog;
    Context context;
    GetMachineInitItem getMachineInitItem;

    // File Download
    private List<String> images;
    private URL[] urls;
    InfintiApplication infintiApplication;
    /**
     * 백그라운드서비스(리모트방식)
     */

    @Override
    public void onPause(){
        super.onPause();
        Log.d("nonono", "onPause");
    }

    IRemoteServiceCallback mCallbcak = new IRemoteServiceCallback.Stub() {
        @Override
        public void valueChanged(String command) throws RemoteException {
            Log.i("BHC_TEST", "Activity Callback value : " + command);
        }

    };

    JSONObject jsonObject;
    Response response;
    IRemoteService mRemoteService;
    boolean update = false;
    ServiceConnection mRemoteCon = new ServiceConnection() {
        @Override

        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub
            if(mRemoteService != null){
                try {

                    mRemoteService.unregisterCallback(mCallbcak);
                } catch (RemoteException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // TODO Auto-generated method stub
            if(service != null){
                mRemoteService = IRemoteService.Stub.asInterface(service);
                try {
                    Log.d("BHC_TEST", "Iremote" );
                    mRemoteService.registerCallback(mCallbcak);
                    remote();
                    Log.d("end", "end program");
                    finishAffinity();
                    System.runFinalization();
                    System.exit(0);
                    ActivityManager mActivityManager = (ActivityManager)getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
                    mActivityManager.killBackgroundProcesses("com.solgae.newoliving");


                } catch (RemoteException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    };
    @Override
    protected void onDestroy() {
        BusProvider.getInstance().unregister(this);
//        mProgressDialog.dismiss();
        Log.d("catch", "activity end");
        if(update) {
            unbindService(mRemoteCon);
        }

        super.onDestroy();
    }

    /**
     * Called as part of the activity lifecycle when the user no longer actively interacts with the
     * activity, but it is still visible on screen. The counterpart to {@link #onResume}.
     *
     * <p>When activity B is launched in front of activity A, this callback will
     * be invoked on A.  B will not be created until A's {@link #onPause} returns,
     * so be sure to not do anything lengthy here.
     *
     * <p>This callback is mostly used for saving any persistent state the
     * activity is editing, to present a "edit in place" model to the user and
     * making sure nothing is lost if there are not enough resources to start
     * the new activity without first killing this one.  This is also a good
     * place to stop things that consume a noticeable amount of CPU in order to
     * make the switch to the next activity as fast as possible.
     *
     * <p>On platform versions prior to {@link Build.VERSION_CODES#Q} this is also a good
     * place to try to close exclusive-access devices or to release access to singleton resources.
     * Starting with {@link Build.VERSION_CODES#Q} there can be multiple resumed
     * activities in the system at the same time, so {@link #onTopResumedActivityChanged(boolean)}
     * should be used for that purpose instead.
     *
     * <p>If an activity is launched on top, after receiving this call you will usually receive a
     * following call to {@link #onStop} (after the next activity has been resumed and displayed
     * above). However in some cases there will be a direct call back to {@link #onResume} without
     * going through the stopped state. An activity can also rest in paused state in some cases when
     * in multi-window mode, still visible to user.
     *
     * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @see #onResume
     * @see #onSaveInstanceState
     * @see #onStop
     */





    //    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case READ_EXTERNAL_STORAGE: {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    // permission was granted, yay! Do the
//                    // contacts-related task you need to do.
//
//                } else {
//
//                    // permission denied, boo! Disable the
//                    // functionality that depends on this permission.
//                }
//                return;
//            }
//
//            // other 'case' lines to check for other
//            // permissions this app might request
//        }
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        if (mRemoteService==null) {
            remoteIntent= new Intent(getApplicationContext(), IRemoteService.class);
            startService(remoteIntent);
            Toast.makeText(getApplicationContext(), "외부서비스 스타트", Toast.LENGTH_LONG).show();
        } else {
            remoteIntent = QvingService.serviceIntent;//getInstance().getApplication();
            Toast.makeText(getApplicationContext(), "already", Toast.LENGTH_LONG).show();
        }

        BusProvider.getInstance().register(this);
        infintiApplication = new InfintiApplication();
        InfintiApplication.downloadMp4 = false;

        deviceUtil = new DeviceUtil(IntroActivity.this);
//       this.onStop();
// Local DB Create and Open
        mDbOpenHelper = new DbOpenHelper(IntroActivity.this);
        mDbOpenHelper.open();


        VersionCheck();
        context = getApplicationContext();
//        reboot();

        handler = new Handler();
        handler.postDelayed(RunRunnable, 100);     // 동작 중 확인
        Connhandler = new Handler();
        Connhandler.postDelayed(ConnRunnable, 60000); // 인터넷 확인
    }

    Handler handler, Connhandler;

    // 동작 중인 지 확인
    Runnable RunRunnable = new Runnable() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void run() {
                if (Helper.isForegroundRunning(IntroActivity.this, "com.solgae.newoliving")) {
                // App Foreground is running
                Log.d("RUN", "RUNNING");
            } else {
                // App Foreground is not running
                Log.d("RUN", "NOOOO");

//                PackageManager packageManager = getApplicationContext().getPackageManager();
//                Intent intent = packageManager.getLaunchIntentForPackage(getApplicationContext().getPackageName());
//                ComponentName componentName = intent.getComponent();
//                Intent mainIntent = Intent.makeRestartActivityTask(componentName);
////              getApplicationContext().startActivity(mainIntent);
//                startActivity(mainIntent);
//                Runtime.getRuntime().exit(0);
            BusProvider.getInstance().post(new PushEvent("ON_DESTROY"));
             Log.d("waittest","BusProvider ON_DESTROY post");

              finishAffinity();
              Intent mintent = new Intent(IntroActivity.this, IntroActivity.class);
              startActivity(mintent);
            };
            handler.postDelayed(RunRunnable, 300000);
        }
    };

    // 인터넷 연결 확인
    Runnable ConnRunnable = new Runnable() {
        @Override
        public void run() {
            if(isNetworkConnected()){
                Log.d("isNetworkConnected", "OKKKKK");
            }else{
                Log.d("isNetworkConnected", "NOOOOO");
                PackageManager packageManager = context.getPackageManager();
                Intent intent = packageManager.getLaunchIntentForPackage(context.getPackageName());
                ComponentName componentName = intent.getComponent();
                Intent mainIntent = Intent.makeRestartActivityTask(componentName);
                context.startActivity(mainIntent);
                Runtime.getRuntime().exit(0);
            }
            Connhandler.postDelayed(ConnRunnable, 3600000);
        }
    };

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public void VersionCheck(){

        //앱버전 체크 API
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    OkHttpClient client1 = new OkHttpClient().newBuilder()
                            .build();

                    // SocketTimeoutException
//                    OkHttpClient client1 = new OkHttpClient.Builder()
//                            .connectTimeout(100, TimeUnit.SECONDS)
//                            .readTimeout(100,TimeUnit.SECONDS)
//                            .writeTimeout(100,TimeUnit.SECONDS)
//                            .build();

                    MediaType mediaType1 = MediaType.parse("application/json");
                    Request request1 = new Request.Builder()
//                            .url("http://15.165.226.158:7150/server_address_test.txt")
                            .url("http://15.165.226.158:7150/server_address.txt")
                            .addHeader("Content-Type", "application/json")
                            .build();
                    Response response1 = client1.newCall(request1).execute();
                    InfintiApplication.SERVER_URL = response1.body().string();

                    // 영상주소
                    Request request2 = new Request.Builder()
                            .url("http://15.165.226.158:7150/video_address.txt")
                            .addHeader("Content-Type", "application/json")
                            .build();
                    Response response2 = client1.newCall(request2).execute();
                    InfintiApplication.VideoURI = response2.body().string();

                    OkHttpClient client = new OkHttpClient().newBuilder().build();
                    Request request = new Request.Builder()
                            .url("http://15.165.226.158:7150/ecoVersion.txt")
                            .method("GET", null)
                            .build();
                    Response response = client.newCall(request).execute();
                    String NewVersion = response.body().string();
                    NewVersion = NewVersion.replaceAll("[^0-9]", "");
                    String thisVersion = BuildConfig.APPLICATION_ID;
                    String[] names = thisVersion.split("\\.");
                    thisVersion = names[2];
                    thisVersion = thisVersion +  BuildConfig.VERSION_CODE;
                    thisVersion = thisVersion.replaceAll("[^0-9]", "");
                    Log.d("token", response+"");


                        if(Integer.parseInt(thisVersion) < Integer.parseInt((NewVersion))){
//                        if (true) {
                            Intent intent = new Intent(IRemoteService.class.getName());
                            intent.setPackage("com.solgae.qvingservice");
                            intent.setAction("INFINITY.SERVER.BANDINGSERVICE");
                            //서비스 바인드
                            bindService(intent, mRemoteCon, Service.BIND_AUTO_CREATE);
                        }
//                    }

                    //앱 권한 체크 API
                    authChecker();

                }catch (ConnectException e){
                    VersionCheck();
                }catch (SocketTimeoutException e){
                    VersionCheck();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void authChecker(){

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED ){
            Log.d("nonono", "ok");
            Log.d("nonono", "activity restart");
            //자판기 등록하기
            registerVanding();
        }else {
            Log.d("nonono", "not ok");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.d("nonono", "one more time");
                    authChecker();
                }
            }, 3000);
        }
    }


    public void registerVanding(){

        //sdcard 내부에 생성된 파일을 읽어온다
        File sdcard = Environment.getExternalStorageDirectory();
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String day = calendar.get(Calendar.DAY_OF_MONTH) + "";
        int randomNo = new Random().nextInt(100);
        day = day + randomNo;
        Log.d("random", day);

        //sccard 내부에 qvingquth.txt 파일생성
        File file = new File(sdcard,"qvingAuth.txt");
        FileOutputStream output = null;
        //당일 날짜와 생성된 난수를 맥어드레스 뒤에 붙인다
        String addKey = day;

        if(file.exists()){
            Log.d("nonono", "File read");

            try {
                StringBuilder text = new StringBuilder();
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    text.append(line);
                    text.append('\n');
                }
                br.close();
                addKey = text.toString();
                addKey = addKey.replaceAll("[^0-9]", "");
            }
            catch (IOException e) {
                //You'll need to add proper error handling here
            }
        }else{
            Log.d("nonono", "can't read FIle");
            try {
                output = new FileOutputStream(file);
                output.write(day.getBytes());
                output.close();
                addKey = "";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        InfintiApplication.serial_number = deviceUtil.getSerialNumber() + addKey;
        InfintiApplication.serial_number = "0HNXFR2C3X1579";

// 1. 장비설정정보
        InfintiApplication.machineMaster = new MachineMaster();

// 현재장비의 FCM 토큰값을 구한다.
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                boolean fcm_task_state = task.isSuccessful();
                if (!fcm_task_state) {
                    Log.w("IntroActivity", "getInstanceId failed", task.getException());
                    return;
                }
                InfintiApplication.this_token = task.getResult().getToken();
                Log.d("fcm_token",InfintiApplication.this_token);

// 1. 시리얼번호에 해당하는 장비의 토콘을 업데이트한다....
//    ex ::: http://dev.kepay.or.kr:8888/interface/api_machine_master.php?f_data={"mac_code":"RW8GIY5R55"}

                server_respones_result_type = "SERIAL_TOCKEN";
                strUrl = InfintiApplication.SERVER_URL + "/interface/" + "api_machine_master_update.php?f_data={";
                greeting = Phrase.from(IntroActivity.this, R.string.api_machine_master_update)
                        .put("mac_code", InfintiApplication.serial_number)
                        .put("token", InfintiApplication.this_token)
                        .format();
                strUrl += greeting;
                strUrl += ",\"sw_version\":\"" + BuildConfig.VERSION_CODE +"\"";
                strUrl += "}";
                Log.d("mfile", InfintiApplication.serial_number);

                getMachineInitItem = new GetMachineInitItem(IntroActivity.this, strUrl, server_respones_result_type);
                get_machine_item_thread = new Thread(getMachineInitItem);
                get_machine_item_thread.start();
            }
        });

    }



    private void remote(){
        try {
            mRemoteService.setServerInfo("http://15.165.226.158:7150/download.php?file=newoliving.apk");
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Override
    public void onMachine_Data_ReceiveRun(Object arrayList) {
        get_machine_item_thread.interrupt(); // 스레드를 종료한다.
        
        switch (server_respones_result_type) {
            case "SERIAL_TOCKEN":
                responseHeader = (ResponseHeader) arrayList;

                /**
                 * 서버에서 업데이트 ★★★★★
                 */
                InfintiApplication.xperon = false;
                InfintiApplication.delivery = false;
                // 업데이트변동이 없을경우
                if (InfintiApplication.xperon || InfintiApplication.delivery) {
                    Intent intent = new Intent(this.getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    IntroActivity.this.finish();
                    // 초기 혹은 업데이트가 있는경우
                } else {
                    strUrl = InfintiApplication.SERVER_URL+ "/interface/" + "api_machine_group_info.php?f_data={";
                    greeting = Phrase.from(getApplicationContext(), R.string.api_machine_master)
//                            .put("mac_code", InfintiApplication.serial_number)
                            .put("mac_code", InfintiApplication.serial_number)
                            .format();
                    strUrl += greeting;
                    strUrl += "}";
                    infintiApplication.Server_Data_Update(strUrl, IntroActivity.this, false);
                    break;
                }
// 서버의 버젼정보가 일치하지안는다면  InfintiApplication.xperon 과 InfintiApplication.delivery는 false
                //코딩
        }
    }
    @Subscribe
    public void ReveiveOttoData(PushEvent event) {
        switch (event.getCommand()) {
            case InfintiApplication.UPDATE_END:
                Log.d("yoon", "UPDATE_END");
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                IntroActivity.this.finish();
                break;
        }
    }
}
