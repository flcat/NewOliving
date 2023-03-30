package com.solgae.newoliving.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;
import com.solgae.newoliving.App;
import com.solgae.newoliving.vo.contents;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;

public class VideoUtils {
    private static final String TAG = "FileUtils";
    Context context;
    App app;

    public VideoUtils(Context context) {
        this.context = context;
        app = new App();
    }

    public void isDirectory(String mPath) {
        File directory = new File(mPath);
        if (!directory.isDirectory()) {
            directory.mkdir();
        }
    }

    public boolean isFile(String mPath) {
        boolean isfile = true;
        File file = new File(mPath);
        if (!file.exists()) {
            isfile = false;
        }
        return isfile;
    }

    public boolean file_write(String filepath, String data) {

        boolean rtn_value = false;

        File file = new File(filepath) ;
        FileWriter fileWriter = null ;
        BufferedWriter bufferedWriter = null ;

        try {
            // open file.
            fileWriter = new FileWriter(file) ;
            bufferedWriter = new BufferedWriter(fileWriter) ;

            // write data to the file.
            bufferedWriter.write(data) ;

            rtn_value = true;

        } catch (Exception e) {
            e.printStackTrace() ;
        }

        // close file.
        try {
            if (bufferedWriter != null)
                bufferedWriter.close() ;

            if (fileWriter != null)
                fileWriter.close() ;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rtn_value;
    }

    public boolean copyAssetAll(String srcPath) {

        boolean rtn_value = false;

        AssetManager assetMgr = context.getAssets();
        String assets[] = null;
        try {
            assets = assetMgr.list("");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        if (assets != null) {
            rtn_value = true;
            for (String filename : assets) {
                InputStream in = null;
                OutputStream out = null;
                try {
                    in = assetMgr.open(filename);
                    File outFile = new File(srcPath, filename);
                    out = new FileOutputStream(outFile);
                    copyFile(in, out);
                } catch (IOException e) {
                    Log.e("tag", "Failed to copy asset file: " + filename, e);
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            // NOOP
                        }
                    }
                    if (out != null) {
                        try {
                            out.close();
                        } catch (IOException e) {
                            // NOOP
                        }
                    }
                }
            }
        }
        return rtn_value;
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }

    public boolean write_file_json(String mPath, String type) {
        JSONObject jsonObject_0 = new JSONObject();
        JSONObject jsonObject_1 = new JSONObject();
        try {
            jsonObject_1.put("schedule_version", app.schedule_Info.getSchedule_version());
            jsonObject_0.put("schedule_info", jsonObject_1);

            JSONArray jsonArray = new JSONArray();
            for(contents contents_ : app.current_ary_contents) {
                JSONObject jsonObject_3 = new JSONObject();
                jsonObject_3.put("seq", contents_.getSeq());
                jsonObject_3.put("contents", contents_.getContents());
                jsonObject_3.put("title", contents_.getTitle());
                jsonObject_3.put("totaltime", contents_.getTotaltime());
                jsonObject_3.put("playtime", contents_.getPlaytime());
                jsonObject_3.put("local_folder", contents_.getLocal_folder());
                jsonObject_3.put("fileName", contents_.getFileName());
                jsonObject_3.put("convert_url", contents_.getConvert_url());
                jsonObject_3.put("convert_flag", contents_.isConvert_flag());
                jsonArray.put(jsonObject_3);
            }

            jsonObject_0.put("contents",jsonArray);

            String local_folder_path = app.ROOT_FOLDER_PATH + File.separator + app.SCHEDULE_LOCAL_FOLDER_NAME;
            String local_file_path   = local_folder_path + File.separator + app.SCHEDULE_FILE_NAME;

            try (FileWriter file = new FileWriter(local_file_path)) {
                file.write(jsonObject_0.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.print(jsonObject_0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean read_file_json(String mPath, String type) {
        boolean return_value = false;
        ArrayList<contents> contents = new ArrayList<contents>();

        File json_file = new File(mPath);
// 스케쥴 파일이 존재하면 읽어온다...
        if (json_file.exists()) {
            try {
                JSONObject jsonObject = new JSONObject(getStringFromFile(json_file.toString()));
                Gson gson = new Gson();
// 스케줄 데이터를 가져온다........
                JSONArray jsonArray = jsonObject.getJSONArray("contents");
                for (int i = 0; i < jsonArray.length(); i++) { //jsonObject에 담긴 두 개의 jsonObject를 jsonArray를 통해 하나씩 호출한다.
                    jsonObject = jsonArray.getJSONObject(i);
                    contents.add(gson.fromJson(jsonArray.get(i).toString(), contents.class));
                }
                switch (type) {
                    case "default":
                        app.contents_default = contents;
                        break;
                    case "infinity":
                        app.contents_local = contents;
                        break;
                }
                return_value = true;
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return return_value;
    }

    public static String getStringFromFile(String filePath) throws Exception {
        File fl = new File(filePath);
        FileInputStream fin = new FileInputStream(fl);
        String ret = convertStreamToString(fin);
        //Make sure you close all streams.
        fin.close();
        return ret;
    }

    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        return sb.toString();
    }

    public static String[] getStorageDirectories() {

        String[] dirs = null;
        BufferedReader bufReader = null;
        try {
            bufReader = new BufferedReader(new FileReader("/proc/mounts"));
            ArrayList<String> list = new ArrayList<String>();
            String line;
            while ((line = bufReader.readLine()) != null) {
                if (line.contains("vfat") || line.contains("/mnt")) {
                    StringTokenizer tokens = new StringTokenizer(line, " ");
                    String s = tokens.nextToken();
                    s = tokens.nextToken(); // Take the second token, i.e. mount point
                    if (s.equals(Environment.getExternalStorageDirectory().getPath())) {
                        list.add(s);
                    } else if (line.contains("/dev/block/vold")) {
                        if (!line.contains("/mnt/secure") && !line.contains("/mnt/asec") && !line.contains("/mnt/obb") && !line.contains("/dev/mapper") && !line.contains("tmpfs")) {
                            list.add(s);
                        }
                    }
                }
            }
            dirs = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                dirs[i] = list.get(i);
            }
        }
        catch (FileNotFoundException e) {}
        catch (IOException e) {}
        finally {
            if (bufReader != null) {
                try {
                    bufReader.close();
                }
                catch (IOException e) {}
            }
        }
        return dirs;
    }

    public static List<StorageInfo> getStorageList() {

        List<StorageInfo> list = new ArrayList<StorageInfo>();
        String def_path = Environment.getExternalStorageDirectory().getPath();
        boolean def_path_removable = Environment.isExternalStorageRemovable();
        String def_path_state = Environment.getExternalStorageState();
        boolean def_path_available = def_path_state.equals(Environment.MEDIA_MOUNTED)
                || def_path_state.equals(Environment.MEDIA_MOUNTED_READ_ONLY);
        boolean def_path_readonly = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED_READ_ONLY);

        HashSet<String> paths = new HashSet<String>();
        int cur_removable_number = 1;

        if (def_path_available) {
            paths.add(def_path);
            list.add(0, new StorageInfo(def_path, def_path_readonly, def_path_removable, def_path_removable ? cur_removable_number++ : -1));
        }

        BufferedReader buf_reader = null;
        try {
            buf_reader = new BufferedReader(new FileReader("/proc/mounts"));
            String line;
            Log.d(TAG, "/proc/mounts");
            while ((line = buf_reader.readLine()) != null) {
                Log.d(TAG, line);
                if (line.contains("vfat") || line.contains("/mnt")) {
                    StringTokenizer tokens = new StringTokenizer(line, " ");
                    String unused = tokens.nextToken(); //device
                    String mount_point = tokens.nextToken(); //mount point
                    if (paths.contains(mount_point)) {
                        continue;
                    }
                    unused = tokens.nextToken(); //file system
                    List<String> flags = Arrays.asList(tokens.nextToken().split(",")); //flags
                    boolean readonly = flags.contains("ro");

                    if (line.contains("/dev/block/vold")) {
                        if (!line.contains("/mnt/secure")
                                && !line.contains("/mnt/asec")
                                && !line.contains("/mnt/obb")
                                && !line.contains("/dev/mapper")
                                && !line.contains("tmpfs")) {
                            paths.add(mount_point);
                            list.add(new StorageInfo(mount_point, readonly, true, cur_removable_number++));
                        }
                    }
                }
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (buf_reader != null) {
                try {
                    buf_reader.close();
                } catch (IOException ex) {}
            }
        }
        return list;
    }

    private void isScheduleFile(String path) {
        String mURI = path + File.separator + "device_serial_schedule.php";

    }

    // 해당 디렉토리 통째로 비우기
    public boolean setDirEmpty(String dirName)
    {
        File dir = new File(dirName);
        File[] childFileList = dir.listFiles();
        if (dir.exists()) {
            for (File childFile : childFileList)
            {
                if (childFile.isDirectory())
                {
                    setDirEmpty(childFile.getAbsolutePath()); //하위 디렉토리
                } else {
                    childFile.delete(); //하위 파일
                }
            }
        }
        return true;
    }

}
