package com.solgae.newoliving.utils;

import android.os.Build;
import android.os.Handler;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class AndroidSuUtils {

    public void system_reboot() {
        ProcessBuilder pb = new ProcessBuilder(new String[] { "su", "-c", "/system/bin/reboot" });
        Process process = null;
        try {
            process = pb.start();
            process.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean screen_capture() {
        boolean return_value = false;

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                screenshots("/storage/emulated/0/", "p123.png");
//                screenshots(MultiApplication.FILE_DOWNLOAD_PATH, "p123.png");
            }
        }, 1000);
        return return_value;
    }
    public void screenshots(String path, String name) {
        executer("screencap -p " + path + name);
    }

    private String executer(String command) {
        StringBuffer output = new StringBuffer();
        Process process = null;
        DataOutputStream os = null;
        try {
            process = Runtime.getRuntime().exec("sh");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(command + "\n");
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
            BufferedReader response = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";

            while ((line = response.readLine()) != null) {
                output.append(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }

                if (process != null) {
                    process.destroy();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        String response1 = output.toString().trim();
        return response1;
    }
}
