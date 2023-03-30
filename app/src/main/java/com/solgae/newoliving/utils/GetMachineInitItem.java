package com.solgae.newoliving.utils;

import android.os.Handler;
import android.os.Looper;
import android.telephony.mbms.MbmsErrors;
import android.util.Log;

import com.google.gson.Gson;
import com.solgae.newoliving.base.InfintiApplication;
import com.solgae.newoliving.jsonresult.api_food_store;
import com.solgae.newoliving.vo.BandingMachineItem;
import com.solgae.newoliving.vo.MachineMaster;
import com.solgae.newoliving.vo.ResponseHeader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * 장비에 설정된 아이템을 서버에서 가져온다
 */
public class GetMachineInitItem extends Thread {

    public static Handler mHandler = new Handler(Looper.getMainLooper());
    Machine_Data_Receive machine_data_receive;
    static BandingMachineItem bandingMachineItem; // 자판기의 설정된 아이템 및 제어장비들에 대한 정보
    static ResponseHeader responseHeader;         // 요청에 의한 응답데이터의 Headr
    static MachineMaster machineMaster;
    api_food_store apiFoodStore;

    String strUrl;
    String jsonFile;
    String result_type;  // 응답형태
    Object return_obj;

    public interface Machine_Data_Receive{
        public void onMachine_Data_ReceiveRun(Object arrayList);
    }

    public GetMachineInitItem(Machine_Data_Receive machine_data_receive, String url, String result_type) {
        this.machine_data_receive = machine_data_receive;
        this.strUrl = url;
        this.result_type = result_type;
    }

    @Override
    public void run() {
        super.run();
        try {
//            URL url = new URL(InfinityApplication.server_url+InfinityApplication.file_name);
            URL url = new URL(strUrl);
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

                switch (result_type) {
                    case "MACHINE_MASTER_INFO":
                        machineMaster = new MachineMaster();
                        String changData = jsonObject.toString().replaceAll("\"product_info\":", "\"machineColummInfo\":");
                        changData = changData.replaceAll("\"food_info\":", "\"food_infos\":");
                        machineMaster = gson.fromJson(changData, MachineMaster.class);
                        InfintiApplication.TID = machineMaster.getDev_tid();
                        Log.d("TID", machineMaster.getDev_tid());
                        InfintiApplication.BID = machineMaster.getCommon_saupjano();
                        return_obj = machineMaster;
                        break;
                    case "MACHINE_MASTER_INFO_STORES":
                        apiFoodStore = new api_food_store();
                        apiFoodStore = gson.fromJson(jsonObject.toString(), api_food_store.class);
                        return_obj = apiFoodStore;
                        break;
                    // 장비의 제품및 설정정보
                    case "MACHINE_INFO":
                        bandingMachineItem = new BandingMachineItem();
                        bandingMachineItem = gson.fromJson(jsonObject.toString(), BandingMachineItem.class);
                        return_obj = bandingMachineItem;
                        break;
                    // 장비 시리얼번호와 토큰값을 확인
                    case "SERIAL_TOCKEN":
                        responseHeader = new ResponseHeader();
                        responseHeader = gson.fromJson(jsonObject.toString(), ResponseHeader.class);
                        return_obj = responseHeader;
                        break;
                    case "CHANGE_COLUM_STATE":
                        responseHeader = new ResponseHeader();
                        responseHeader = gson.fromJson(jsonObject.toString(), ResponseHeader.class);
                        return_obj = responseHeader;
                        break;

                    case "CARD_APPROVAL":
                        responseHeader = new ResponseHeader();
                        responseHeader = gson.fromJson(jsonObject.toString(), ResponseHeader.class);
                        return_obj = responseHeader;
                        break;
                }

                reader.close();

            } else {
                Log.i("통신 결과", conn.getResponseCode() + "에러");
            }

            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    machine_data_receive.onMachine_Data_ReceiveRun(return_obj);   //참조된 ThreadRecevie객체의 onReceiveRun함수를 호출
                }
            });

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
