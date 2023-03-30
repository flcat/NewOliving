package com.solgae.newoliving.utils;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtils {

    // 전표번호를 구한다.
    public int getjunpyoNo() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyyMMdd");
        String formatDate = sdfNow.format(date);
        int number;
        String[] bunsuk;
// tranno =>> 장비코드_사업자번호_일자_순번
        String data = readFile(new File(Environment.getExternalStorageDirectory()+"/xperon_no/junpyo.dat"));
        if (!data.equalsIgnoreCase("")) {
            bunsuk = data.split("_");
            if (bunsuk[2].equalsIgnoreCase(formatDate)) {
                number = Integer.parseInt(bunsuk[3]) + 1;
            } else {
                number = 1;
            }
        } else {
            number = 1;
        }

        return number;

    }
/**
 * 디렉토리 여부 체크 하기
 * @param dir
 * @return
 */
    public boolean isDirectory(File dir){
        boolean result;
        if(dir!=null&&dir.isDirectory()){
            result=true;
        }else{
            result=false;
        }
        return result;
    }


/**
 * 파일 존재 여부 확인 하기
 * @param file
 * @return
 */
    public boolean isFileExist(File file){
        boolean result;
        if(file!=null&&file.exists()){
            result=true;
        }else{
            result=false;
        }
        return result;
    }

/**
 * 디렉토리 생성
 * @return dir
 */
    public File makeDirectory(String dir_path){
        File dir = new File(dir_path);
        if (!dir.exists())
        {
            dir.mkdirs();
        }
        return dir;
    }

/**
 * 파일 생성
 * @param dir
 * @return file
 */
    public File makeFile(File dir , String file_path){
        File file = null;
        boolean isSuccess = false;
        if(dir.isDirectory()){
            file = new File(file_path);
            if(file!=null&&!file.exists()){
                try {
                    isSuccess = file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

/**
 * (dir/file) 절대 경로 얻어오기
 * @param file
 * @return String
 */
    public String getAbsolutePath(File file){
        return ""+file.getAbsolutePath();
    }

/**
 * 파일에 내용 쓰기
 * @param file
 * @param file_content
 * @return
 */
    public boolean writeFile(File file , byte[] file_content){
        boolean result;
        FileOutputStream fos;
        if(file!=null&&file.exists()&&file_content!=null){
            try {
                fos = new FileOutputStream(file);
                try {
                    fos.write(file_content);
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            result = true;
        }else{
            result = false;
        }
        return result;
    }

/**
 * 파일 읽어 오기
 * @param file
 */
    public String readFile(File file){
        int readcount=0;
        String value = "";
        if(file!=null&&file.exists()){
            try {
                FileInputStream fis = new FileInputStream(file);
                readcount = (int)file.length();
                byte[] buffer = new byte[readcount];
                fis.read(buffer);
                for(int i=0 ; i<file.length();i++){
                    value += buffer[i];
                }
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return value;
    }
}
