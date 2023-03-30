package com.solgae.newoliving.base;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.solgae.newoliving.App;
import com.solgae.newoliving.R;
import com.solgae.newoliving.busevent.BusProvider;
import com.solgae.newoliving.busevent.PushEvent;
import com.solgae.newoliving.localdb.DataBases;
import com.solgae.newoliving.localdb.DbOpenHelper;
import com.solgae.newoliving.utils.CardOffice;
import com.solgae.newoliving.utils.DownLoadFile;
import com.solgae.newoliving.utils.GetMachineInitItem;
import com.solgae.newoliving.vo.ApplovalData;
import com.solgae.newoliving.vo.ApprovalData;
import com.solgae.newoliving.vo.ApprovalReport;
import com.solgae.newoliving.vo.ApprovalResult;
import com.solgae.newoliving.vo.MachineColummInfo;
import com.solgae.newoliving.vo.MachineMaster;
import com.solgae.newoliving.vo.store_info;
import com.solgae.serialport.SerialPort;
import com.solgae.serialport.SerialPortFinder;
import com.squareup.phrase.Phrase;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class InfintiApplication extends Application
    implements GetMachineInitItem.Machine_Data_Receive {

    //사이니지추가
//    public static String ServerRoot = "http://dev.kepay.or.kr:8888";
    public static App myapp;
    public static boolean startMain = false;
    public static boolean downloadMp4;
    public static String[] downloads = new String[100];
    public static String VideoURI = "http://15.165.226.158:7150/upload/XPERON/";

    public static ArrayList<FrameLayout> totalItems = new ArrayList<>(); // 메인 아이템 어레이 리스트

    public static boolean startProcess = false;
    public static ApprovalData cancelData;


    public static String Van_Office = "KICC";
    public static String Kicc_Tid = "";

    public static final String UPDATE_END = "UPDATE_END";
// 아이템그룹
    public static boolean xperon   = false;
    public static boolean delivery = false;
    public static boolean shoes    = false;
    public static boolean styler   = false;

// 한페이지에 뿌려지는 아이템 수
    public static final int PAGE_OF_ITEM_COUNT = 12;
    public static final int COLUMM_OF_LINE = 4;
    public static String Approve_NO;
// 현재의 TAB에 따라 수행플세스가 다르다...
    public static String Tab_name = "XPERON";
    public static final String BROADCAST_XPERON = "com.solgae.xperon.service";
    public static final String BROADCAST_MAIN  = "com.solgae.xperonorderbox";

// fcm 서버키
    public static final String FCM_MESSAGE_URL = "https://fcm.googleapis.com/fcm/send";
    public static final String SERVER_KEY = "AAAAP11qsDI:APA91bFftkwjKLKqPgP2D1VRZWerXBTTos9ULRx1WS5Yx86w9GwFjLGH6deoEwXeBA2TYG6IbmxCiAgFqmEFnlYA2cOsL9_BBMTJBOlQjZ_5ryJnrH0JKW0fGOzFeFekzZxzMTfiWniD";

// Servr API URL
//public static final String SERVER_URL = "http://dev.kepay.or.kr:8888";

//    public static final String SERVER_URL = "http://dev.kepay.or.kr:8888/interface/";
    public static  String SERVER_URL = "" ;
    public static final String LOCAL_IMAGE_URL = Environment.getExternalStorageDirectory()+"/xperon";
    public static final String IPADDRESS = "http://15.165.226.158:7150/download.php?file=app-xperonbox.apk";

    public static String serial_number ;
    public static String this_token ; // FCM token
    public static MachineMaster machineMaster;

// 1. 배달메뉴에서 음식점을 클릭했을때
    public static boolean foole_menu = false;
    public static store_info storeInfo;
// Serial
    public SerialPortFinder mSerialPortFinder = new SerialPortFinder();
    private SerialPort mSerialPort = null;

    private DbOpenHelper mDbOpenHelper;
    CharSequence greeting;
    String server_respones_result_type = "", strUrl = "";
    GetMachineInitItem getMachineInitItem;
    Thread get_machine_item_thread;
    // File Download
    private List<String> images;
    private URL[] urls;
    private static Context context;
    ArrayList<store_info> store_infos;
    public static String TID;
    public static String BID;

    private List<String> SDimages = new ArrayList<>();
    private boolean reboot = false;

    @Override
    public void onCreate() {
        super.onCreate();

        // Local DB Create and Open
        mDbOpenHelper = new DbOpenHelper(this);
        mDbOpenHelper.open();
        machineMaster = new MachineMaster();

    }

    public void Server_Data_Update(String strUrl, Context context, boolean reboot) {

        this.context = context;
        this.reboot = reboot;

        mDbOpenHelper.mDB.execSQL("delete from " + DataBases.CreateDB._CTABLENAME +";");
        mDbOpenHelper.mDB.execSQL("delete from " + DataBases.CreateDB._DTABLENAME +";");
        mDbOpenHelper.mDB.execSQL("delete from " + DataBases.CreateDB._FOODDTABLENAME +";");
        mDbOpenHelper.mDB.execSQL("delete from " + DataBases.CreateDB._STOREDTABLENAME +";");

        server_respones_result_type = "MACHINE_MASTER_INFO";

        getMachineInitItem = new GetMachineInitItem(this, strUrl, "MACHINE_MASTER_INFO");
        get_machine_item_thread = new Thread(getMachineInitItem);
        get_machine_item_thread.start();
    }

    @Override
    public void onMachine_Data_ReceiveRun(Object arrayList) {
        get_machine_item_thread.interrupt(); // 스레드를 종료한다.
        switch (server_respones_result_type) {
            case "MACHINE_MASTER_INFO":  // 장비에 설정한 제품정보
                if (null != arrayList) {

                    SendToast(); // admin 어플로 FCM 메세지 전송 중

                    images = new ArrayList<>();
                    InfintiApplication.machineMaster = (MachineMaster) arrayList;
// 공통을 저장

                        //새로받은 이미지랑기존에가지고 있는 이미지에서 뭘지우고 뭘 지우지 않아야하는지
                    for (MachineColummInfo machineColummInfo : InfintiApplication.machineMaster.getMachineColummInfo()) {

                        // 임시조건문
//                            if (machineColummInfo.getItemgroup().equalsIgnoreCase(prvItemgroup) &&
//                                    machineColummInfo.getColumm().equalsIgnoreCase(prvMac_column)) {
//                                System.out.println();
//                                continue;
//                            }
//                            prvMac_column = machineColummInfo.getColumm();
//                            prvItemgroup = machineColummInfo.getItemgroup();

                        // 임시조건문 종료

                        images.add(machineColummInfo.getItem_image());
                        images.add(machineColummInfo.getItem_detail_image());
                        }
                    if(setDirDel("/xperon")){

                        images.removeAll(SDimages);
                        urls = new URL[images.size()];

                        int i = 0;
                        for (String url : images) {
                            try {
                                urls[i] = new URL(SERVER_URL + "/upload/product/" + url);
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            }
                            i++;
                        }

                        DownLoadFile downLoadFile = new DownLoadFile(context, images);
                        downLoadFile.execute(urls);
                    }

//                    BusProvider.getInstance().post(new PushEvent(InfintiApplication.UPDATE_END, ""));


                }

                if (reboot) reboot();
                break;
        }
    }

    public void reboot() {

//            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
//            pm.reboot(null);


    }

    // 해당 디렉토리 통째로 비우기
    public boolean setDirEmpty(String dirName) {
        String path = Environment.getExternalStorageDirectory().toString() + dirName;
        File dir = new File(path);
        File[] childFileList = dir.listFiles();
        if (dir.exists()) {
            for (File childFile : childFileList) {
                Log.d("yoon", childFile.getParent());
                Log.d("yoon", childFile.getName());
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

    public boolean setDirDel(String dirName){

        String path = Environment.getExternalStorageDirectory().toString() + dirName;
        File dir = new File(path);
        File[] childFileList = dir.listFiles();
        if (dir.exists()) {
            for (File childFile : childFileList) {
                if (childFile.getName().endsWith(".jpg") || childFile.getName().endsWith(".png") ) {
                    Log.d("yoon", childFile.getName());
                    SDimages.add(childFile.getName());
                }
                if (childFile.isDirectory()) {
                    setDirDel(childFile.getAbsolutePath());
                    //하위 디렉토리
                } else {
                    // 기기내부 필요없는 이미지 delete
                    List<String> SDimages2 = new ArrayList<>();
                    SDimages2.addAll(SDimages);
                    SDimages2.removeAll(images);

                    for(int i=0; i<SDimages2.size(); i++) {
                        File file = new File(dir, SDimages2.get(i));
                        file.delete();
                    }
                }
            }
        }
        return true;
    }

    /**
     * KICC 결재루틴
     */
    public static void set_Kicc_TranData(Context context, ApprovalData approvalData, String type){

        BusProvider.getInstance().post(new PushEvent("END_Activity")); // 핸들러 제거
        ComponentName compName = new ComponentName("kr.co.kicc.easycarda","kr.co.kicc.easycarda.CallPopup");

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.putExtra("TRAN_NO", approvalData.getTRAN_NO());

/**
 *  거래구분자(전문구분)
 *  D1 : 승인
 *  D4 : 당일취소/전일취소(반품환불)
 *  M8 : 포인트 조회
 *  RB : 직전거래통신취소(망취소)-TRAN_NO로 거래 취소
 *  LA : 직전거래 응답값 재전송 요청(응답값 못받아을 경우)
 *  PT : 단말기 프린트 명령어(단말기연결시에만 사용가능)
 */
        intent.putExtra("TRAN_TYPE", approvalData.getTRAN_TYPE());
        intent.putExtra("TERMINAL_TYPE", "40");
// 총금액
        intent.putExtra("TOTAL_AMOUNT",approvalData.getTOTAL_AMOUNT());
// 부가세
        intent.putExtra("TAX",approvalData.getTAX());
        intent.putExtra("TAX_OPTION","M");
// 팁
        intent.putExtra("TIP",approvalData.getTIP());
        intent.putExtra("TIP_OPTION",approvalData.getTIP_OPTION());

//  D4 : 당일취소/전일취소(반품환불),
        if("D4".equals(approvalData.getTRAN_TYPE())||"B2".equals(approvalData.getTRAN_TYPE()) || "B4".equals(approvalData.getTRAN_TYPE())) {
            intent.putExtra("APPROVAL_NUM", approvalData.getAPPROVAL_NUM());
            intent.putExtra("APPROVAL_DATE", approvalData.getAPPROVAL_DATE());
            intent.putExtra("TRAN_SERIALNO", approvalData.getTRAN_SERIALNO());
        }
        if("B1".equals(approvalData.getTRAN_TYPE()) || "B2".equals(approvalData.getTRAN_TYPE()) || "B3".equals(approvalData.getTRAN_TYPE()) || "B4".equals(approvalData.getTRAN_TYPE())) {
            intent.putExtra("CASHAMOUNT", "00"); // APP card 에서는 캐쉬 마운트가 00리턴값이 정상으로 들어온다
        }else {
            intent.putExtra("INSTALLMENT", approvalData.getINSTALLMENT());
        }

        if("M3".equals(approvalData.getTRAN_TYPE()) || "M4".equals(approvalData.getTRAN_TYPE()) || "M8".equals(approvalData.getTRAN_TYPE())) {
            intent.putExtra("TERMINAL_TYPE", "40");
            intent.putExtra("TEXT_DECLINE", "포인트 조회가 거절되었습니다");
        }
        else
            intent.putExtra("ADD_FIELD",approvalData.getADD_FIELD());
        intent.putExtra("TIMEOUT",approvalData.getTIMEOUT());

        intent.putExtra("TEXT_PROCESS",approvalData.getTEXT_PROCESS());
        intent.putExtra("TEXT_COMPLETE", approvalData.getTEXT_COMPLETE());

        intent.putExtra("SHOP_TID",approvalData.getSHOP_TID());
        intent.putExtra("SHOP_BIZ_NUM", approvalData.getSHOP_BIZ_NUM());


        intent.setComponent(compName);
        if(approvalData.getTRAN_TYPE().equalsIgnoreCase("D1")){
            ((PayMethodActivity)context).startActivityForResult(intent, 1);
        }
    }
/**
 * 승인데이타를 서버에 저장한다.
 */
    long now;
    Date date;

    public String fun_approval_data_save(Context context, ApprovalResult approvalResult, ArrayList<MachineColummInfo> SelectedItem, ArrayList<Integer> stock) {
        String old_bill_no = "";
        now = System.currentTimeMillis();
        date = new Date(now);
        ArrayList<MachineColummInfo> selectedItem = SelectedItem;

        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyyMMddHHmmss");
        String bill_no = InfintiApplication.serial_number + sdfNow.format(date);

//        String strUrl = InfintiApplication.SERVER_URL + "/interface/" + "api_cash_master_upload.php?f_data={";
        String strUrl = InfintiApplication.SERVER_URL + "api_cash_master_upload.php?f_data={";

        CharSequence greeting = Phrase.from(context, R.string.api_cash_master_upload_data_header)
                .put("mac_code", InfintiApplication.serial_number)
                .put("pos_num", "1")                                     // 항상 1
                .put("bill_no", bill_no)                                  // 디바이스|년월일|시분초
                .put("userid", "")                                        // 로그인 사용자 없는경우 빈값
                .put("custom_id", approvalResult.getCARD_NUM())           // 고객카드번호
                .put("total_money",  approvalResult.getTOTAL_AMOUNT())    // 판매금액
                .put("real_money", approvalResult.getTOTAL_AMOUNT())      // total_money과 동일
                .put("cash_money", "00000000")                          // 현금결제 - 앱카드에서는 값이 "    "로 오기때문에 강제로 0값을 지정 송윤주
                .put("card_money", approvalResult.getTOTAL_AMOUNT())      // 카드결제
                .put("point_money", "0")                                 // 포인트결제
                .put("dis_money", "0")                                    // 할인금액
                .put("coupon_money", "0")
                .put("gift_money", "0")
                .put("giftcard_money", "0")                             // 충전카드용 결제
                .put("refund_money", "0")                                // 거스름돈 항상 0
                .put("sale_point_money", "0")                           // 현재 판매의 적립된 포인트
                .put("sale_cut_money", "0")                              // 절사금액
                .put("supply_money", (Integer.parseInt(approvalResult.getTOTAL_AMOUNT()) - Integer.parseInt(approvalResult.getTAX())))// 공급가
                .put("charge_money", "0")                                   // 봉사료
                .put("tax_money", approvalResult.getTAX())                   // 부가세
                .put("dis_type", "")                                        // 항상빈값
                .put("sale_status", (approvalResult.getTRAN_TYPE().equalsIgnoreCase("D1")?"1":"0")) // 1 - 판매, -2 - 취소
                .put("write_day",  sdfNow.format(date))  // 판매일자
                .put("cancel_reason", "") // 취소 결제건의 경우 취소사유
                .put("old_bill_no", old_bill_no) // 취소 시 원결제의 bill 번호
                .put("is_cancel", "0")// 항상 0  서버에서 처리함.
                .format();
        strUrl += greeting;

        for(int i=0; i<selectedItem.size(); i++){
            int amount = (int)Double.parseDouble(selectedItem.get(i).getItem_cost());
            int eachstock = stock.get(i);
            int total =amount * eachstock;
            String Total = String.valueOf(total);
            Log.d("Total", Total + "");

            strUrl +=  "{";
            // 카테고리별 머신 maccode 입력필드 필요
            greeting = Phrase.from(context, R.string.api_cash_master_upload_data_cash_process)
                    .put("main_code", "")                                                         // 대분류, 빈값으로 줘도 무방
                    .put("sub_code", "")                                                          // 중분류
                    .put("step_code", "")                                                         // 소분류
                    .put("menu_style", "")                                                        // 빈값, 향후 사용, 공짜, 포장 등에 사용
                    .put("pro_title", selectedItem.get(i).getItem_name())                                         // 제품명
                    .put("pro_code", selectedItem.get(i).getItem_code())                                          // 제품코드
                    .put("unit_money", selectedItem.get(i).getItem_cost())                                        // 단가
                    .put("pro_cnt", stock.get(i) +"")                                                          // 수량
                    .put("dis_money", "0")                                                        // 할인금액
                    .put("result_money", Total)                                      // 단가 * 수량 - 할인
                    .format();
            strUrl += greeting;
            strUrl += "},";
        }

        strUrl = strUrl.substring(0, strUrl.length()-1);
        strUrl += "],";
//        결재정보(승인정보) cash_result
        strUrl += "\"cash_result\": [";                             // 결제정보
        strUrl += "{";
        greeting = Phrase.from(context, R.string.api_cash_master_upload_data_cash_result)
                .put("data_type", "card")                                                        // 결제유형, card, cash, point, giftcard
                .put("data_money", approvalResult.getTOTAL_AMOUNT())                              // 결제금액
                .put("data_action", (approvalResult.getTRAN_TYPE().equalsIgnoreCase("D1")?"confirm":"cancel")) // 결제 comfirm, 취소 cancel
                .put("data_num", approvalResult.getCARD_NUM())                                    // 카드번호
                .put("data_div", "0")                                                             // 할부개월
                .put("data_day", approvalResult.getAPPROVAL_DATE())                               // van에서 온 승인.취소일자
                .put("data_confirm_num", approvalResult.getAPPROVAL_NUM())                       // 승인번호
                .put("data_receipt_num", approvalResult.getTRAN_NO())                            // van에서 온 영수증번호, 없는경우 승인번호 //
                .put("data_van_info", "0")                                                       // 예비용
                .put("data_receipt_type", "")                                                   // 현금영수증결제 시 경우 결제유형, hp, card, com-사업자번호, card_com-법인카드,self-자진발급
                .put("data_receipt_input", "")                                                  // 현금영수증결제 시 입력된 값
                .put("data_van", "KICC")                                                         // van사코드
                .put("write_day", sdfNow.format(date))                                          // db생성일자
                .put("data_com", CardOffice.ISSUER(approvalResult.getISSUER_CODE()))              // 발급사명
                .put("data_acquire_com", CardOffice.ISSUER(approvalResult.getACQUIRER_CODE()))   // 매입사명
                .put("data_com_code", approvalResult.getISSUER_CODE() )                          // 발급사코드
                .put("data_acquire_com_code", approvalResult.getACQUIRER_CODE())                // 매입사코드
                .put("supply_money", approvalResult.getTOTAL_AMOUNT())                           // van사에 전송한 공급가
                .put("charge_money",  approvalResult.getTIP())                                   // van사에 전송한 봉사료
                .put("tax_money", approvalResult.getTAX())                                        // van사에 전송한 부가세
                .put("cancel_reason", "")                                                        // 취소건의 경우 취소사유
                .put("is_cancel", "0")                                                            // 서버에서 처리 항상 0
                .format();
        strUrl += greeting;
        strUrl += "}";
        strUrl += "]";
// 마지막 테그
        strUrl += "}";
        strUrl = strUrl.replaceAll("\n", "");
        return strUrl;
    }


    /**
 * 시리얼설정 포토열기
 */
    public SerialPort getSerialPort(SharedPreferences sharedPreferences)
            throws SecurityException, IOException, InvalidParameterException {
        /* Read serial port parameters */
//        String packageName = mPackageName;
//        String packageName = getApplication().getPackageName();
//        SharedPreferences sp = getSharedPreferences(packageName + "_preferences", MODE_PRIVATE);
        SharedPreferences sp = sharedPreferences;
        if (mSerialPort == null) {
            String path = sp.getString("DEVICE", "/dev/ttyS3");
            int baudrate = Integer.decode(sp.getString("BAUDRATE", "9600"));
            /* Check parameters */
            if ((path.length() == 0) || (baudrate == -1)) {
                throw new InvalidParameterException();
            }
            /* Open the serial port */
            mSerialPort = new SerialPort(new File(path), baudrate, 0);
        }

        return mSerialPort;
    }
/**
 * 시리얼설정 포트닫기
 */
    public void closeSerialPort() {
        if (mSerialPort!=null) mSerialPort.close();
        mSerialPort = null;
    }

    /**
     * Firebase FCM
     */
    public static void send_to_fcm_data(final String tocken, final String command, final String message) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // FMC 메시지 생성 start
                    JSONObject root = new JSONObject();
                    JSONObject notification = new JSONObject();
                    notification.put("body", "VERSION 0.2");
                    notification.put("title", "UPDATE_SCHRDULE");
                    root.put("notification", notification);
                    root.put("to", tocken);
                    JSONObject data = new JSONObject();
                    data.put("command", command);
                    data.put("message", message);
                    data.put("title", "FG Title");
                    root.put("data", data);
                    // FMC 메시지 생성 end

                    URL Url = new URL(FCM_MESSAGE_URL);
                    HttpURLConnection conn = (HttpURLConnection) Url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.addRequestProperty("Authorization", "key=" + SERVER_KEY);
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setRequestProperty("Content-type", "application/json");
                    OutputStream os = conn.getOutputStream();
                    os.write(root.toString().getBytes("utf-8"));
                    os.flush();
                    conn.getResponseCode();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void SendToast(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    OkHttpClient client = new OkHttpClient().newBuilder().build();

                    String registration_id = "APA91bGBeyqJjfAGv3cUOIGK5qqYSXRvmgJaM6KTnJ0QDxnaT03H-w2yd2kTVjhjmwyfiOFW7H37lxFSSb3y3PKCyYgp2dEqr_92wqXLe713G03fXoi6St4";
                    String notiBody = "{" + "\"store\":" + "\"" + InfintiApplication.machineMaster.getInstall_shopname() +"\"," + "\"type\":\"open\"" + "}";

                    String postBody = "{" + "\"to\":" + "\"" + registration_id + "\"," +
                            "\"data\":" + notiBody + "}";


                    RequestBody requestBody = RequestBody.create(
                            MediaType.parse("application/json; charset=utf-8"), postBody);

                    Request request1 = new Request.Builder()
                            .url("https://fcm.googleapis.com/fcm/send")
                            .method("POST", requestBody)
                            .addHeader("Content-Type", "application/json")
                            .addHeader("Authorization", "key=AAAAP11qsDI:APA91bFftkwjKLKqPgP2D1VRZWerXBTTos9ULRx1WS5Yx86w9GwFjLGH6deoEwXeBA2TYG6IbmxCiAgFqmEFnlYA2cOsL9_BBMTJBOlQjZ_5ryJnrH0JKW0fGOzFeFekzZxzMTfiWniD")
                            .build();
                    Response response = client.newCall(request1).execute();
                    String res = response.body().string();
                    Log.d("Body", res +"");

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    public String point_approval(Context context, ApprovalResult approvalResult, Object detail_data, @Nullable ApprovalReport pointResult, boolean withPoint) {
        String old_bill_no = "";
        now = System.currentTimeMillis();
        date = new Date(now);

        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyyMMddHHmmss");
        String bill_no = InfintiApplication.serial_number + sdfNow.format(date);

        String strUrl = InfintiApplication.SERVER_URL + "/interface/" + "api_cash_master_upload.php?f_data={";
        CharSequence greeting = Phrase.from(context, R.string.api_cash_master_upload_data_header)
                .put("mac_code", InfintiApplication.serial_number)
                .put("pos_num", "1")                                     // 항상 1
                .put("bill_no", bill_no)                                  // 디바이스|년월일|시분초
                .put("userid", "")                                        // 로그인 사용자 없는경우 빈값
                .put("custom_id", approvalResult.getCARD_NUM())           // 고객카드번호
                .put("total_money",  (Integer.parseInt(approvalResult.getTOTAL_AMOUNT()) + (withPoint?Integer.parseInt(pointResult.getApproval_cost()):0 )) +"")    // 판매금액
                .put("real_money", (Integer.parseInt(approvalResult.getTOTAL_AMOUNT()) + (withPoint?Integer.parseInt(pointResult.getApproval_cost()):0 )) +"")      // total_money과 동일
                .put("cash_money", "00000000")                          // 현금결제 - 앱카드에서는 값이 "    "로 오기때문에 강제로 0값을 지정 송윤주
                .put("card_money", (Integer.parseInt(approvalResult.getTOTAL_AMOUNT()) + (withPoint?Integer.parseInt(pointResult.getApproval_cost()):0 )) +"")      // 카드결제
                .put("point_money", "0")                                 // 포인트결제
                .put("dis_money", "0")                                    // 할인금액
                .put("coupon_money", "0")
                .put("gift_money", "0")
                .put("giftcard_money", "0")                             // 충전카드용 결제
                .put("refund_money", "0")                                // 거스름돈 항상 0
                .put("sale_point_money", "0")                           // 현재 판매의 적립된 포인트
                .put("sale_cut_money", "0")                              // 절사금액
                .put("supply_money", (Integer.parseInt(approvalResult.getTOTAL_AMOUNT()) - Integer.parseInt(approvalResult.getTAX())))// 공급가
                .put("charge_money", "0")                                   // 봉사료
                .put("tax_money", approvalResult.getTAX())                   // 부가세
                .put("dis_type", "")                                        // 항상빈값
                .put("sale_status", (approvalResult.getTRAN_TYPE().equalsIgnoreCase("D1")?"1":"0")) // 1 - 판매, -2 - 취소
                .put("write_day",  sdfNow.format(date))  // 판매일자
                .put("cancel_reason", "") // 취소 결제건의 경우 취소사유
                .put("old_bill_no", old_bill_no) // 취소 시 원결제의 bill 번호
                .put("is_cancel", "0")// 항상 0  서버에서 처리함.
                .format();
        strUrl += greeting;


            for (MachineColummInfo mci : (ArrayList<MachineColummInfo>)detail_data) {
                strUrl +=  "{";
                greeting = Phrase.from(context, R.string.api_cash_master_upload_data_cash_process)
                        .put("main_code", "")                                                         // 대분류, 빈값으로 줘도 무방
                        .put("sub_code", "")                                                          // 중분류
                        .put("step_code", "")                                                         // 소분류
                        .put("menu_style", "")                                                        // 빈값, 향후 사용, 공짜, 포장 등에 사용
                        .put("pro_title", mci.getItem_name())                                         // 제품명
                        .put("pro_code", mci.getItem_code())                                          // 제품코드
                        .put("unit_money", mci.getItem_cost())                                        // 단가
                        .put("pro_cnt", "1")                                                          // 수량
                        .put("dis_money", "0")                                                        // 할인금액
                        .put("result_money", mci.getItem_cost())                                      // 단가 * 수량 - 할인
                        .format();
                strUrl += greeting;
                strUrl += "},";
            }
        strUrl = strUrl.substring(0, strUrl.length()-1);
        strUrl += "],";
//        결재정보(승인정보) cash_result
        strUrl += "\"cash_result\": [";                             // 결제정보
        strUrl += "{";
        greeting = Phrase.from(context, R.string.api_cash_master_upload_data_cash_result)
                .put("data_type", "card")                                                        // 결제유형, card, cash, point, giftcard
                .put("data_money", approvalResult.getTOTAL_AMOUNT())                              // 결제금액
                .put("data_action", (approvalResult.getTRAN_TYPE().equalsIgnoreCase("D1")?"confirm":"cancel")) // 결제 comfirm, 취소 cancel
                .put("data_num", approvalResult.getCARD_NUM())                                    // 카드번호
                .put("data_div", "0")                                                             // 할부개월
                .put("data_day", approvalResult.getAPPROVAL_DATE())                               // van에서 온 승인.취소일자
                .put("data_confirm_num", approvalResult.getAPPROVAL_NUM())                       // 승인번호
                .put("data_receipt_num", approvalResult.getTRAN_NO())                            // van에서 온 영수증번호, 없는경우 승인번호 //
                .put("data_van_info", "0")                                                       // 예비용
                .put("data_receipt_type", "")                                                   // 현금영수증결제 시 경우 결제유형, hp, card, com-사업자번호, card_com-법인카드,self-자진발급
                .put("data_receipt_input", "")                                                  // 현금영수증결제 시 입력된 값
                .put("data_van", "KICC")                                                         // van사코드
                .put("write_day", sdfNow.format(date))                                          // db생성일자
                .put("data_com", "기아레드멤버스")              // 발급사명
                .put("data_acquire_com", "기아레드멤버스")   // 매입사명
                .put("data_com_code", "기아레드멤버스" )                          // 발급사코드
                .put("data_acquire_com_code", "기아레드멤버스" )                // 매입사코드
                .put("supply_money", approvalResult.getTOTAL_AMOUNT())                           // van사에 전송한 공급가
                .put("charge_money",  approvalResult.getTIP())                                   // van사에 전송한 봉사료
                .put("tax_money", approvalResult.getTAX())                                        // van사에 전송한 부가세
                .put("cancel_reason", "")                                                        // 취소건의 경우 취소사유
                .put("is_cancel", "0")                                                            // 서버에서 처리 항상 0
                .format();
        strUrl += greeting;
        strUrl += "}";
        strUrl += "]";
        strUrl += "}";
        strUrl = strUrl.replaceAll("\n", "");
        return strUrl;
    }
}

