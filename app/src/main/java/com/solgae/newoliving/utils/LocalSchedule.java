package com.solgae.newoliving.utils;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LocalSchedule extends Thread {

    public static Handler mHandler = new Handler(Looper.getMainLooper());
    public String responce;
    LocalSchedule_ThreadRecevie mLocalSchedule_ThreadRecevie;

    public LocalSchedule(LocalSchedule_ThreadRecevie localSchedule_threadRecevie) {
        mLocalSchedule_ThreadRecevie = localSchedule_threadRecevie;
    }

    public interface LocalSchedule_ThreadRecevie{
        public void onLocalScheduleScheduleReceiveRun(Object object);
    }

    @Override
    public void run() {
        super.run();
        try {
            File file = new File(Environment.getExternalStorageDirectory()+"/Download/","Schedule_Data.json");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null){
                stringBuilder.append(line).append("\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
// This responce will have Json Format String
            String responce = stringBuilder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mLocalSchedule_ThreadRecevie.onLocalScheduleScheduleReceiveRun(responce);   //참조된 ThreadRecevie객체의 onReceiveRun함수를 호출
            }
        });
    }
}
