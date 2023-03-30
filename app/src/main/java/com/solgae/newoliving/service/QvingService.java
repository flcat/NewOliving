package com.solgae.newoliving.service;

import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.solgae.newoliving.MainActivity;
import com.solgae.newoliving.R;
import com.solgae.newoliving.base.InfintiApplication;
import com.solgae.newoliving.busevent.BusProvider;
import com.solgae.newoliving.serial.Pro47Tocal;
import com.solgae.newoliving.utils.SerialPortPreferences;
import com.solgae.serialport.SerialPort;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class QvingService extends Service {
    InfintiApplication ifApplication;

    // 통신
    static byte[] rev_data = new byte[1024];
    private Pro47Tocal pro47Tocal;
    int Open_Columm_Index = 1;
    static int empty_columm_index = 1;
    static String Current_Columm_Index = "";

    static int bit_count;
    static  String COMMON_TYPE;
    String FROM_DATA_TYPE;

    private IBinder qving_binder = new BindServiceBinder();
    private ICallback mCallback;

    private Context context;
    protected SerialPort mSerialPort;
    protected OutputStream mOutputStream;
    private InputStream mInputStream;
    private Serial_ReadThread serial_readthread;

    private Pro47Tocal proTocol;
    List<String> openData = new ArrayList<>();
    private boolean lastCall;

// 로컬서비스
    public static Intent serviceIntent = null;

    // 서비스에서 가장 먼저 호출됨(최초에 한번만)
    @Override
    public void onCreate() {
        super.onCreate();


        this.context = this.getApplicationContext();
        ifApplication = new InfintiApplication();

// Register ourselves so that we can provide the initial value.
        BusProvider.getInstance().register(this);
/**
 * Brodcast 등록
 */
        IntentFilter filter = new IntentFilter();
        filter.addAction(InfintiApplication.BROADCAST_XPERON);
        registerReceiver(xperonbroadcastreceiver, filter);

        pro47Tocal = new Pro47Tocal();
/****************************************************************************************************
 * Serial Port
 ****************************************************************************************************/
        Serial_Port_Open();


    }




    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(xperonbroadcastreceiver);
    }

    /**
     * 상세이미지통신
     */
    private void Serial_Port_Open() {
        try {

            String packageName = getApplication().getPackageName();
            SharedPreferences sharedPreferences = getSharedPreferences(packageName + "_preferences", MODE_PRIVATE);
            mSerialPort = ifApplication.getSerialPort(sharedPreferences);
            mOutputStream = mSerialPort.getOutputStream();
            mInputStream = mSerialPort.getInputStream();
            /* Create a receiving thread */
            serial_readthread = new Serial_ReadThread();
            serial_readthread.start();
        } catch (SecurityException e) {
            DisplayError(R.string.error_security);
        } catch (IOException e) {
            DisplayError(R.string.error_unknown);
        } catch (InvalidParameterException e) {
            DisplayError(R.string.error_configuration);
        }
    }

    private void DisplayError(int resourceId) {
        Bundle bun = new Bundle();
        bun.putString("notiMessage", String.valueOf(resourceId));
        Intent popupIntent = new Intent(getApplicationContext(), SerialPortPreferences.class);
        popupIntent.putExtras(bun);
        PendingIntent pie= PendingIntent.getActivity(getApplicationContext(), 0, popupIntent, PendingIntent.FLAG_ONE_SHOT);
        try {
            pie.send();
        } catch (PendingIntent.CanceledException e) {
//            LogUtil.degug(e.getMessage());
        }
    }

    private boolean fcmCheck = false;
    private final BroadcastReceiver xperonbroadcastreceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            rev_data = new byte[1024];
            bit_count=0;
            COMMON_TYPE = intent.getStringExtra("actionType");
            switch (COMMON_TYPE.toUpperCase()) {
                case "APPROVAL_COLUMM_OPEN":
                    String col_inx = Integer.toString(intent.getIntExtra("colummIndex", 0));
                    Current_Columm_Index = col_inx.length()==1 ? "0" + col_inx : col_inx;
                    Bundle extras = intent.getExtras();
                    if(extras.containsKey("fcm")) {
                        fcmCheck = true;
                    }else{
                        fcmCheck = false;
                    }
                    Log.d("fcm", fcmCheck + "임다");
                    control_board_send_data(pro47Tocal.DOOR_OPEN(Current_Columm_Index.toCharArray())); // 해당칸열기

                    break;
                case "MANAGER_ALL_COLUMM_OPEN":
                    Open_Columm_Index = 1;
                    String number = Open_Columm_Index>9? Integer.toString(Open_Columm_Index):"0"+Open_Columm_Index;
                    control_board_send_data(pro47Tocal.DOOR_OPEN(number.toCharArray()));
                    break;
                case "MANAGER_ALL_EMPTY_COLUMM_OPEN":
                    empty_columm_index = 1;
                    control_board_send_data(pro47Tocal.DOOR_OPEN((empty_columm_index<10 ? "0" + empty_columm_index : ""+empty_columm_index).toCharArray()));
                    break;
                case "GET_MICRO":
                    control_board_send_data(pro47Tocal.MICRO_INFOMATION());
                    break;
            }
        }
    };

    // Declare inner class
    public class BindServiceBinder extends Binder {
        public QvingService getService(){
            return QvingService.this; // return current service
        }
    }

    // declare callback function
    public interface ICallback {
        public void remoteCall(String mData);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return qving_binder;
    }

    // for registration in activity
    public void registerCallback(ICallback cb){
        mCallback = cb;
    }

    /**
     * Activity와 data를 주고받는곳 service contents
     */
    public void myServiceFunc(String command, String data){
        COMMON_TYPE = "COL_OPEN_REV";
        switch (command) {
            case "COL_OPEN":
                Current_Columm_Index = data.length()==1 ? "0" + data : data;
                control_board_send_data(pro47Tocal.DOOR_OPEN(Current_Columm_Index.toCharArray())); // 해당칸열기
                break;
        }

    }

    private void openColumn(String col_NO){
//        int collumn = Integer.valueOf(col_NO);
//        Log.d("collum", "start collum " + collumn);
//        int end_collumn = proTocol.collmns[collumn - 1];
//
//        Current_Columm_Index = end_collumn<10 ? "0" + end_collumn : "" + end_collumn;
//
//        int column = Integer.valueOf(Current_Columm_Index);
//        byte column_number = (byte) column;
//        proTocol.columm_open[2] = column_number;
//        proTocol.columm_open[3]= proTocol.MakeLRC(proTocol.columm_open);
//        control_board_send_data(proTocol.columm_open); //해당칸 열기
    }

/**
  * @param send_data
  * 232C에 데이타를 보낸다
 */
    private void control_board_send_data(byte[] send_data) {
        try {
            if (null!=mOutputStream) mOutputStream.write(send_data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/**
 * Serial port Thread Start
 */
    private class Serial_ReadThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (!isInterrupted()) {
                int size;
                try {
                    byte[] buffer = new byte[64];
                    if (mInputStream == null) return;
                    size = mInputStream.read(buffer);
                    if (size > 0) {
                        onDataReceived(buffer, size);
                        Log.d("revdata0", rev_data + "");
                        Log.d("revdata0", "buffer: "+buffer );

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }

//private class Serial_ReadThread extends Thread {
//    @Override
//    public void run() {
//        super.run();
//        while (!isInterrupted()) {
//            int size;
//            try {
//                byte[] buffer = new byte[64];
//                if (mInputStream == null) return;
//                size = mInputStream.read(buffer);
//                if (size > 0) {
//                    if(openData.size() == 0 && lastCall == true){
//                        lastCall = false;
//                        Log.d("yoon", "start Video");
//                        Intent sendIntent = new Intent("stock_receiver");
//                        sendIntent.putExtra("actionType", "START_VIDEO");
//                        sendBroadcast(sendIntent);
//                    }
//                    if(openData.size() > 0){
//
//                        lastCall = true;
//                        openColumn(openData.get(0));
//                        openData.remove(0);
//                    }
//
//
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//                return;
//            }
//        }
//    }
//}


/**
 * 포트에서 데이터를 읽어온다
 */
    boolean st_10 = false;
    boolean ed_10 = false;
    boolean st_02 = false;
    boolean ed_03 = false;
    boolean complate = false;
    boolean m_first = true;
    protected void onDataReceived(final byte[] buffer, final int size) {
        Log.d("YoonLog", buffer.length + "");
        Log.d("revdata1", rev_data.toString() + "");

        int rev_data_index = 0;
// 응답 데이타가오면 임시버퍼(rev_data)에서 빈곳의 위치를 찿아온다
        for (byte bb : rev_data) {
            if (bb==(byte)0x00) {
                break;
            }
            rev_data_index++;
        }
        Log.d("revdata2", rev_data.toString() + "");

        int empty_check = 0;  // 프로토콭애서 0x00가 두번연속이면 다음 데이터를 받는다..

        for (byte bb : buffer) {
// 버퍼의 값이 빈값지점에 도착하면 200msec 후에 빠져나간다.
            if (bb==(byte)0x00 && empty_check>0) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            Log.d("revdata3", rev_data.toString() + "");

            if (bb==(byte)0x00) empty_check++;
// 리얼버퍼에서온 응답데이터를 임시버퍼로 올린다
            if (rev_data_index>1024) {
                System.out.println();
            }
            rev_data[rev_data_index++] = bb;   // ★★★★★★★★★★★★★★★★★★★★★★★★★
        }
        Log.d("revdata4", rev_data.toString() + "");

// stx etx를 검사한다.
        for (byte dd : rev_data) {
            if (dd==(byte)0x10) st_10 = true;
            if (dd==(byte)0x02) st_02 = true;
            if (dd==(byte)0x10) ed_10 = true;
            if (dd==(byte)0x03) ed_03 = true;
            if (st_10 && st_02 && ed_10 && ed_03) complate = true;
        }
        Log.d("revdata5", rev_data.toString() + "");

// STX ETX
        if (complate) {
// 처음 연결시에는 메크로 데이타가 항시 올라오므로 매크로를 받은후 에코값을 처리한다.
            if (pro47Tocal.AUTO_MICRO_STATE_RECEIVE(rev_data)) {
                String[] col_state = new String[47];
                for (int i = 3; i < 50; i++) {
                    col_state[i - 3] = (rev_data[i]==0x30)?"1":"0";
                }
// 에코를 보내고처리
                control_board_send_data(pro47Tocal.MICRO_STATE_ECHO);
                rev_data = new byte[1024];
                Log.d("revdata6", rev_data.toString() + "");
                // 장비의 칸정보를 보낸다..(
                Intent sendIntent = new Intent(InfintiApplication.BROADCAST_MAIN);
                sendIntent.putExtra("actionType", "is_sold_out");
                sendIntent.putExtra("stack", col_state);
                sendBroadcast(sendIntent);

                if (null==COMMON_TYPE) COMMON_TYPE = "GET_MICRO";
            }
// 승인 후 도어 오픈명령에 따른 응답값을 검증한다.
            switch (COMMON_TYPE.toUpperCase()) {
                case "COL_OPEN_REV":
                    if (pro47Tocal.DOOR_OPEN_REV(rev_data)) {
                        COMMON_TYPE = "GET_MICRO";
                        rev_data = new byte[1024];
                    }
                    break;
                case "APPROVAL_COLUMM_OPEN" :

                    if (pro47Tocal.DOOR_OPEN_RECEIVE(rev_data)) {
                        rev_data = new byte[1024];

                        if(!fcmCheck){
                            Intent popupIntent = new Intent(getApplicationContext(), MainActivity.class);
                            popupIntent.putExtra("colummIndex", Integer.parseInt(Current_Columm_Index));
                            PendingIntent pie = PendingIntent.getActivity(getApplicationContext(), 0, popupIntent, PendingIntent.FLAG_ONE_SHOT);
                            try {
                                pie.send();
                            } catch (PendingIntent.CanceledException e) {
                                Log.e("QvingService 그림으로 표현", e.toString());
                            }
                        }


    // 화면에 컬럼배열을 그림으로 표현하는 창을 뛰운다...
//

                    }
                    break;
                case "GET_MICRO" :
                    if (pro47Tocal.MICRO_STATE_RECEIVE(rev_data)) {

                        pro47Tocal.DOOR_MICRO_STATE = new int[47];

                        control_board_send_data(pro47Tocal.MICRO_STATE_ECHO); // 에코를 보내고처리
                        String[] col_state = new String[47];
                        for (int i = 3; i < 50; i++) {
                            pro47Tocal.DOOR_MICRO_STATE[i - 3] = rev_data[i];
                            col_state[i - 3] = (rev_data[i] == 0x30) ? "1" : "0";
                        }
                        rev_data = new byte[1024];
                        // 장비의 칸정보를 보낸다..
                        Log.d("revdata7", rev_data.toString() + "");
                        Intent sendIntent = new Intent(InfintiApplication.BROADCAST_MAIN);
                        sendIntent.putExtra("actionType", "is_sold_out");
                        sendIntent.putExtra("stack", col_state);
                        sendBroadcast(sendIntent);
                    }
                    break;
                case "MANAGER_COLUMM_OPEN" :
                    if (pro47Tocal.DOOR_OPEN_RECEIVE(rev_data)) {
                        rev_data = new byte[1024];
                        if (mCallback != null) mCallback.remoteCall(Current_Columm_Index + "번 컬럼이 열렸습니다...!");
                    }
                    break;
// 전체컬럼열기
                case "MANAGER_ALL_COLUMM_OPEN" :
                    if (pro47Tocal.DOOR_OPEN_RECEIVE(rev_data)) {
// 응답이오면 일정시간대기 후
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        rev_data = new byte[1024];
                        Open_Columm_Index++;
                        if (Open_Columm_Index<48) {
                            String number = Open_Columm_Index>9? Integer.toString(Open_Columm_Index):"0"+Open_Columm_Index;
                            control_board_send_data(pro47Tocal.DOOR_OPEN(number.toCharArray()));
// 문이 다열렸다면 열린신호를 보낸다..
                        } else {

                        }
                    }
                    break;
// 비어있는 컬럼 전체열기
                case "MANAGER_ALL_EMPTY_COLUMM_OPEN" :

                    if (pro47Tocal.DOOR_OPEN_RECEIVE(rev_data)) {

// 응답이오면 일정시간대기 후
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        empty_columm_index = empty_room_index(empty_columm_index) + 1;
                        control_board_send_data(pro47Tocal.DOOR_OPEN((empty_columm_index<10 ? "0" + empty_columm_index : ""+empty_columm_index).toCharArray()));
                    }
                    break;
            }

        }
    }


    private int empty_room_index(int inx) {

        int rtn_value = 0;

        for (int i = (empty_columm_index-1); i<pro47Tocal.DOOR_MICRO_STATE.length; i++) {
            if (pro47Tocal.DOOR_MICRO_STATE[i] == 0x30) {
                rtn_value = i;
                break;
            }
        }
        return rtn_value;
    }

}
