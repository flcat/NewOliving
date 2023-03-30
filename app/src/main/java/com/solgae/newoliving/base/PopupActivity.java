package com.solgae.newoliving.base;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.gson.Gson;
import com.solgae.newoliving.R;
import com.solgae.newoliving.busevent.BusProvider;
import com.solgae.newoliving.busevent.PushEvent;
import com.solgae.newoliving.utils.FileUtils;
import com.solgae.newoliving.utils.SerialPortPreferences;
import com.solgae.newoliving.vo.ApplovalData;
import com.solgae.newoliving.vo.ApprovalData;
import com.solgae.newoliving.vo.ApprovalResult;
import com.solgae.newoliving.vo.MachineColummInfo;
import com.solgae.serialport.SerialPort;
import com.squareup.otto.Subscribe;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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

public class PopupActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout popupTitle, popupDetail, onlyOKLi, TwoBtnLi, LeftArrow, RightArrow, PopupTextLi , PopupImageLi, imgLayout;
    TextView titleTV, noticeTV, OKBtn, cancelBtn, TWO_Okbtn;
    String Title, IntentType, Logo, mainColor, CateType;
    EditText adminText;
    ImageView popupLogo,left_NT, right_NT, leftImg, rightImg;
    FrameLayout frameView;
    ArrayList<MachineColummInfo> SelectedItem = new ArrayList<>();
    int imgIndex = 0;
    ImageView centerIV;
    MachineColummInfo NewItem;
    public static PopupActivity mPopupActivity;
    String GUBUN = "W"; // 할인쿠폰 ( W : 금액할인 , P : 포인트할인 )
    MachineColummInfo mItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("PopupDestroy", "onCreate HERE");

        BusProvider.getInstance().register(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("CHANGE_ITEM");
//        registerReceiver(broadcastReceiver, intentFilter);
        RESET_TIME();
        setContentView(R.layout.activity_popup);
        frameView = (FrameLayout) findViewById(R.id.framePopup);
        WindowManager wm = getWindowManager();
        Display dp = wm.getDefaultDisplay();
        Intent intent = getIntent();
        Title = intent.getStringExtra("title");
        double this_w = intent.getDoubleExtra("width", 50);
        double this_h = intent.getDoubleExtra("height", 50);
        IntentType = intent.getStringExtra("Type");
        mItem = (MachineColummInfo) intent.getSerializableExtra("mItem");
        Logo = intent.getStringExtra("logo");
        int id = getResources().getIdentifier(Logo, "drawable", getPackageName());
        int id2 = getResources().getIdentifier("cover", "drawable", getPackageName());
        Log.d("width",(int) (dp.getWidth() * this_w)+ " < w -- h >"  + (int)(dp.getHeight() * this_h) );
        mainColor = intent.getStringExtra("color"); // 타이틀, OKbtn 색상
        popupTitle = (LinearLayout) findViewById(R.id.popupTitle); // <-- 이 레이아웃은 항상 높이 동일해야함.
        popupDetail = (LinearLayout) findViewById(R.id.popupDetail);
        titleTV = (TextView) findViewById(R.id.titleTV);
        noticeTV = (TextView) findViewById(R.id.noticeTV);
        OKBtn = (TextView) findViewById(R.id.OKBtn);
        OKBtn.setOnClickListener(this);
        onlyOKLi = (LinearLayout) findViewById(R.id.onlyOKLi); // 확인 버튼만 필요할 때 USE (default VISIBLE)
        TwoBtnLi = (LinearLayout) findViewById(R.id.TwoBtnLi); // 취소, 확인 버튼 필요할 때 USE (GONE)
        cancelBtn = (TextView) findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(this);
        TWO_Okbtn  = (TextView) findViewById(R.id.TWO_Okbtn);
        TWO_Okbtn.setOnClickListener(this);
        popupLogo = (ImageView) findViewById(R.id.popupLogo);
        left_NT = (ImageView)findViewById(R.id.left_NT);
        right_NT = (ImageView) findViewById(R.id.right_NT);
        TextView focustv = (TextView) findViewById(R.id.focus);
        focustv.setOnClickListener(this);
        LeftArrow = (LinearLayout) findViewById(R.id.LeftArrow);
        LeftArrow.setOnClickListener(this);
        RightArrow = (LinearLayout) findViewById(R.id.RightArrow);
        RightArrow.setOnClickListener(this);
        PopupTextLi = (LinearLayout) findViewById(R.id.PopupTextLi);
        PopupImageLi = (LinearLayout) findViewById(R.id.PopupImageLi);
        leftImg = (ImageView)findViewById(R.id.leftImg);
        rightImg = (ImageView) findViewById(R.id.rightImg);
        leftImg.setOnClickListener(this);
        rightImg.setOnClickListener(this);
        centerIV = (ImageView) findViewById(R.id.centerIV);
        mPopupActivity = PopupActivity.this;
        imgLayout =(LinearLayout) findViewById(R.id.imgLayout);


        // 로고 변경
        if(!Logo.equalsIgnoreCase("")) {
            Drawable logo_drawable = getResources().getDrawable(id);
            popupLogo.setBackground(logo_drawable);
//            popupTitle.setPadding(15,0,15,0);
        }

        // 팝업크기설정
        FrameLayout.LayoutParams params1 = new FrameLayout.LayoutParams((int) (dp.getWidth() * this_w), (int)(dp.getHeight() * this_h));
        frameView.setLayoutParams(params1);

        // 타이틀 색 변경
        final GradientDrawable drawable = (GradientDrawable) ContextCompat.getDrawable(this, R.drawable.noticepopup_round);
        drawable.setColor(Color.parseColor(mainColor));
        popupTitle.setBackgroundResource(R.drawable.noticepopup_round);

        // ok 버튼 색 변경
        final GradientDrawable OKdrawable = (GradientDrawable) ContextCompat.getDrawable(this, R.drawable.okbtn_round);
        OKdrawable.setColor(Color.parseColor(mainColor));
        OKBtn.setBackgroundResource(R.drawable.okbtn_round);
        OKdrawable.setColor(Color.parseColor("#16c7bd"));
        TWO_Okbtn.setBackgroundResource(R.drawable.okbtn_round);

        if(Title.contains("(")){
            int in = Title.indexOf("(");
            String[] titleText = Title.split("\\(");
            titleTV.setText(titleText[0] + "\n(" + titleText[1]);
        }else{
            titleTV.setText(Title);
        }

        switch (IntentType){

            case "NO_PRODUCT":
                noticeTV.setText("선택된 상품이 없습니다.");
                break;

            // 재고 부족
            case "STOCK":
                noticeTV.setText("현재 상품 재고 보다 많이 \n 주문하실 수 없습니다.");
                break;

            case "PAY_LIMIT":
                noticeTV.setText("50,000원 이상 \n 주문하실 수 없습니다. ");
                break;

            case "OUT_OF_RANGE_ITEM":
                noticeTV.setText("선택하신 번호는 \n 상품이 존재하지 않습니다.");
                break;

            case "NOT_ENTER":
                noticeTV.setText("화면 오른쪽하단의 키패드에 \n 원하시는 상품의 번호를 입력해주세요.");
                break;

            // 할인수단 선택
            case "Discount":
                break;

            // 결제수단 선택
            case "Payment":
                break;


        }
    }

    public void RESET_TIME(){
        Intent sendIntent = new Intent();
        sendIntent.setAction("stock_receiver");
        sendIntent.putExtra("actionType", "RESET_TIME");
        sendBroadcast(sendIntent);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d("PopupDestroy", "onPostResume HERE");

        switch (IntentType) {
            case "ItemNotice":
                Log.d("IMAGE",OKBtn.getWidth() +"");
            break;
        }

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        Intent sendIntent = new Intent();
        sendIntent.setAction("stock_receiver");
        sendIntent.putExtra("actionType", "RESET_TIME");
        sendBroadcast(sendIntent);
        Intent intent = new Intent();

        switch (v.getId()){
            case R.id.OKBtn: // 확인
                finish();
                break;

            case R.id.cancelBtn: // (취소) 확인
                intent.putExtra("NO_CHANGE", "NO_CHANGE");
                setResult(0,intent);
                finish();
                break;


        }

    }

    @Override
    protected void onDestroy() {
        finish();
        Log.d("PopupDestroy", "FINISH HERE");
        super.onDestroy();
    }

    FileUtils fileUtils;
    long now;
    int totalAmount;
    public void startPayement(){
        String supjano = "1108605631";
        String dev_tid = "2028229";
        Intent payIntent = getIntent();
        totalAmount = payIntent.getIntExtra("totalAmount",0);
        String tranno = InfintiApplication.serial_number;
// TRAN_TYPE  ==> 거래구분자(전문구분)  D1 : 승인, D4 : 당일취소/전일취소(반품환불), M8 : 포인트 조회, RB : 직전거래통신취소(망취소)-TRAN_NO로 거래 취소, LA : 직전거래 응답값 재전송 요청(응답값 못받아을 경우), PT : 단말기 프린트 명령어(단말기연결시에만 사용가능)
        String transType = "D1";
// TERMINAL_TYPE ==> 단말기구분 40 : 일반거래, CE: CJ 직원할인
        String terminal_type = "40";
// TOTAL_AMOUNT(9 BYTE) : 총결재금액 예> 5만원인경우 - "50000"
        String total_amount = "";
// TAX(9 BYTE): 부과세
        String tax = "";
// TAX_OPTION : 부가세 옵션 ==> M:입력값으로 처리(Default), A:총결제 금액에서 자동 계산(10%)
        String tax_option = "A";
// TIP        : 봉사료 금액(9 BYTE)
        String tip = "0";
// TIP_OPTION : 봉사료 옵션
        String tip_option = "N";
// INSTALLMENT:(2BYTE) 할부유무
        String installment = "";
// APPROVAL_NUM(12 BYTE) : 취소 거래, 상세 거래내역 에만 필요함 승인거래시 전달받은 승인번호
        String approval_num = "";
// APPROVAL_DATE(13 BYTE):취소 거래시, 상세 거래내역에만 필요함 승인거래시 전달받은 승인날짜
        String approval_date = "";
        String timeout = "30";
        String text_process = "결제 진행중입니다";
        String text_complete = "결제가 완료되었습니다";
//                    String text_complete = "포인트 할인이 완료되었습니다.";
        String text_decline = "";
// PRINT_DATA    : 프린트함수 사용시

        tranno += "_" + "4168085442";
        int totalcost = totalAmount;
        double mvat = (totalcost / 1.1) * 0.1;        // 부가세(VAT)
        double mprice = Math.round(totalcost - mvat); // 부가세제외(반올림)
        mvat = Math.round(mvat);                // 부가세(반올림)
        total_amount = "" + totalcost;
        tax = "" + (int)mvat;
        fileUtils = new FileUtils();

        SimpleDateFormat sdfNow;
        Date date = new Date(now);
        sdfNow = new SimpleDateFormat("yyyyMMddHHmmss");
        tranno += "_" + sdfNow.format(date);
        tranno += "_" + fileUtils.getjunpyoNo();
        String TranNO;
        ApprovalResult approvalReport;
        ApprovalData applovalData = new ApprovalData(tranno, transType, terminal_type, "", total_amount, tax, tax_option,
                tip, tip_option, "", "", "", "", "", "",
                "", timeout, text_process, text_complete, "", dev_tid, supjano,  "", null);
// 결재함수 (응답값은 onActivityResult 에서)
        InfintiApplication.set_Kicc_TranData(PopupActivity.this, applovalData, "KICC");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("result", "result");
        String result = data.getStringExtra("RESULT_CODE");

        if (resultCode == RESULT_OK) {

        }
    }

    private void AlertMsg(String title, double width, double height, String Type, @Nullable String logo, String mainColor, String text, String type){
        // type : 마지막버튼 - 0: 없음, 1: 확인, 2: 취소, 3: 영수
        Intent intent = new Intent(getApplicationContext(), PopupActivity.class);
        intent.putExtra("logo", logo);
        intent.putExtra("title", title);
        intent.putExtra("width", width);
        intent.putExtra("height", height);
        intent.putExtra("Type", Type);
        intent.putExtra("color", mainColor);
        intent.putExtra("text", text);
        intent.putExtra("type", type);
        // 예 아니오 필요한 경우
        if(Type.equalsIgnoreCase("CHANGE_ALLCATE")|Type.equalsIgnoreCase("CHANGE_SETCATE")){
            startActivityForResult(intent,2);
        }else if(Type.equalsIgnoreCase("ADMIN_MODE")){
            startActivityForResult(intent, 1);
        }else{
            startActivity(intent);
        }
        this.finish();
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
    @Subscribe
    public void ReveiveOttoData(PushEvent event) {
        switch (event.getCommand()) {
            case "REMOVE":
                Log.d("yoon log", "remove productPay");
                this.finish();
                break;

            case "GETDATA":
                noticeTV.setText("물품 정보를 변경중입니다.");
                break;

        }
    }

    // QR 통신
    InfintiApplication infintiApplication = new InfintiApplication();
    SerialPort mSerialPort;
    private InputStream mInputStream;
    private OutputStream mOutputStream;
    byte[] byteArray = new byte[1024];
    int byteLength = 0;
    private boolean readMode = false;
    String QR = "";



    private void DisplayError(int resourceId) {
        Bundle bun = new Bundle();
        bun.putString("notiMessage", String.valueOf(resourceId));
        Intent popupIntent = new Intent(getApplicationContext(), SerialPortPreferences.class);
        popupIntent.putExtras(bun);
        PendingIntent pie = PendingIntent.getActivity(getApplicationContext(), 0, popupIntent, PendingIntent.FLAG_ONE_SHOT);
        try {
            pie.send();
        } catch (PendingIntent.CanceledException e) {
//            LogUtil.degug(e.getMessage());
        }
    }
    List<String> images = new ArrayList<>();



}
