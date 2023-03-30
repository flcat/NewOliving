package com.solgae.newoliving.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.solgae.newoliving.App;
import com.solgae.newoliving.base.InfintiApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.solgae.newoliving.service.BackService.MSG_SEND_TO_ACTIVITY;

public class DownloadVideo  extends AsyncTask<String, Integer, String> {
    App app;
    Messenger mFromActivity = null;
    Context context;
    private ProgressDialog mProgressDialog;

    Handler DownHandler = new Handler();
    Runnable downRunnable;


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
            Log.d("insdie", e.toString());
        }
    }

    public DownloadVideo(Context context, Messenger messenger) {
        this.context = context;
        mFromActivity = messenger;
        mProgressDialog = new ProgressDialog(this.context);
        mProgressDialog.setIndeterminate(false);
        // Progress dialog horizontal style
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // Progress dialog title
        mProgressDialog.setTitle("Signage MP4");
        // Progress dialog message
        mProgressDialog.setMessage("사이니지 영상을 다운받는 중입니다...");
        mProgressDialog.setCancelable(true);


        // Set a progress dialog dismiss listener

    }

    // Before the tasks execution
    protected void onPreExecute() {
// Display the progress dialog on async task start
        mProgressDialog.show();
        mProgressDialog.setProgress(0);
    }

    protected void onProgressUpdate(Integer... progress) {
        // Update the progress bar
        mProgressDialog.setProgress(progress[0]);
    }


    @Override
    protected String doInBackground(String... params) {
        int count;
        File directory = new File(App.BaseFolder);
        app = InfintiApplication.myapp;
        Log.d("inside", "contents_file_download");

        if (!directory.isDirectory()) {
            directory.mkdir();
        } else {

        }

        Log.d("inside", "predownload");
        if(app != null){

        }
        if(InfintiApplication.downloadMp4 == false){
            if (!app.current_ary_contents.isEmpty()) {
                app.download_id_list.clear();

                for (int i = 0; i < app.current_ary_contents.size(); i++) {
                    try {

                        mp4load(InfintiApplication.VideoURI + app.current_ary_contents.get(i).getTitle(), i);
                        publishProgress((int) (((i + 1) / (float) app.current_ary_contents.size()) * 100));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        }



        }
        return null;
    }

    public void mp4load(String urling, final int i) {
//
//        reboot();

        downRunnable = new Runnable() {
            @Override
            public void run() {
                Log.d("Hi", "Hihi" + i);
                if(InfintiApplication.downloads[i].equalsIgnoreCase("true")){
                    File file = new File(app.BaseFolder + File.separator + app.current_ary_contents.get(i).getFileName());
                    if(file.exists()){
                        file.delete();
                    }
                    reboot();
                }else{
                    DownHandler.removeCallbacks(downRunnable);
                }

            }
        };
        DownHandler.postDelayed(downRunnable, 300000);
        // 300000


        try {
            File file = new File(app.BaseFolder + File.separator + app.current_ary_contents.get(i).getFileName());
            if (!file.exists()) {
                InfintiApplication.downloads[i] = "true";
                URL url = new URL(urling);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                //c.setDoOutput(true);
                con.connect();

                String downloadsPath = Environment.getExternalStoragePublicDirectory(app.SCHEDULE_LOCAL_FOLDER_NAME).getAbsolutePath();

                String fileName = app.current_ary_contents.get(i).getFileName();

                File outputFile = new File(downloadsPath, fileName);

                if (!outputFile.exists()) {
                    outputFile.createNewFile();
                }

                FileOutputStream fos = new FileOutputStream(outputFile);

                int status = con.getResponseCode();

                InputStream is = con.getInputStream();

                byte[] buffer = new byte[1024];
                int len1 = 0;
                while ((len1 = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len1);
                }
                fos.close();
                is.close();
                InfintiApplication.downloads[i] = "false";

                Log.d("inside", "number " + i + " download is done");
            }else{
                InfintiApplication.downloads[i] = "false";
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public void reboot() {
        ProcessBuilder pb = new ProcessBuilder(new String[] {"/system/bin/" });
        Process process = null;
        try {
            Runtime.getRuntime().exec("reboot");
            process = pb.start();
            process.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("BHC_TEST", e + "reboot");
        }
        catch (InterruptedException e) {

            Log.d("BHC_TEST", e + "reboot");
            e.printStackTrace();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mProgressDialog.dismiss();
        InfintiApplication.downloadMp4 = true;
        sendMsgToActivity("DOWNLOAD_END");
    }
}
