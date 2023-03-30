package com.solgae.newoliving.base;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.solgae.newoliving.R;
import com.solgae.newoliving.busevent.BusProvider;
import com.solgae.newoliving.busevent.PushEvent;
import com.solgae.newoliving.utils.GetMachineInitItem;
import com.solgae.newoliving.vo.ApprovalData;
import com.solgae.newoliving.vo.ApprovalReport;
import com.solgae.newoliving.vo.ApprovalResult;
import com.solgae.newoliving.vo.MachineColummInfo;
import com.solgae.newoliving.vo.ResponseHeader;
import com.squareup.otto.Subscribe;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

public class PayMethodActivity extends AppCompatActivity implements View.OnClickListener, GetMachineInitItem.Machine_Data_Receive {

    LinearLayout smartpay, smartpay2,UsePoint, UseMembership, UseMembership2, PaymentMethod, totalText;
    TextView OrderCount2, PayAmt2, DisAmt2, TotalAmt2, TotalTV;
    ImageView Pay_Unclick, Pay_Cancel, payunclick2, payunclick3, paybtn;
    ImageView CardPay, SamsungPay, KakaoPay, TossPay, SelectPayIcon;
    String Totalnum, Totalcost;
    Date date;
    long now;
    DecimalFormat decimalFormat;
    ArrayList<MachineColummInfo> selected;
    ArrayList<Integer> stock = new ArrayList<>();

    ApprovalResult approvalResult;
    int mDiscount;

    int cnt9977 = 0;
    Thread init_data_thread;
    InfintiApplication infintiApplication;
    MachineColummInfo machineColummInfo;
    ApprovalReport approvalReport;



    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("yoon", "pay activity end");
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.paymethod);
        overridePendingTransition(R.anim.fadeins,0);
        BusProvider.getInstance().register(this); // 버스 프로바이더 등록

        smartpay = (LinearLayout) findViewById(R.id.smartpay);
        smartpay.setOnClickListener(this);
        smartpay2 = (LinearLayout) findViewById(R.id.smartpay2);
        UsePoint = (LinearLayout) findViewById(R.id.UsePoint);
        UsePoint.setOnClickListener(this);
        UseMembership = (LinearLayout) findViewById(R.id.UseMembership);
        UseMembership.setOnClickListener(this);
        UseMembership2 = (LinearLayout) findViewById(R.id.UseMembership2);
        OrderCount2 = (TextView) findViewById(R.id.OrderCount2);
        PayAmt2 = (TextView) findViewById(R.id.PayAmt2);
        DisAmt2 = (TextView) findViewById(R.id.DisAmt2);
        TotalAmt2 = (TextView) findViewById(R.id.TotalAmt2);
        Pay_Unclick = (ImageView) findViewById(R.id.Pay_Unclick);
        Pay_Cancel = (ImageView) findViewById(R.id.Pay_Cancel);
        Pay_Cancel.setOnClickListener(this);
        payunclick2 = (ImageView) findViewById(R.id.payunclick2);
        payunclick3 = (ImageView) findViewById(R.id.payunclick3);
        CardPay = (ImageView) findViewById(R.id.CardPay);
        CardPay.setOnClickListener(this);
        SamsungPay = (ImageView) findViewById(R.id.SamsungPay);
        SamsungPay.setOnClickListener(this);
        KakaoPay = (ImageView) findViewById(R.id.KakaoPay);
        KakaoPay.setOnClickListener(this);
        TossPay = (ImageView) findViewById(R.id.TossPay);
        TossPay.setOnClickListener(this);
        SelectPayIcon = (ImageView) findViewById(R.id.SelectPayIcon);
        PaymentMethod =  (LinearLayout) findViewById(R.id.PaymentMethod);
        totalText =  (LinearLayout) findViewById(R.id.totalText);
        TotalTV = (TextView) findViewById(R.id.TotalTV);
        BusProvider.getInstance().post(new PushEvent("Pay_Touch_Now"));
        Log.d("EndHandler", "3 --pay Start ");

        InfintiApplication.startProcess = false; //사용자가 결제 한거라고 인지
        decimalFormat  = new DecimalFormat("###,###");

        infintiApplication = new InfintiApplication();
        approvalReport = new ApprovalReport();

        Intent payintent = getIntent();
        Totalnum = payintent.getStringExtra("Totalnum");
        Totalcost = payintent.getStringExtra("Totalcost");
        selected =  (ArrayList<MachineColummInfo>) payintent.getSerializableExtra("selected");
        stock = (ArrayList<Integer>)payintent.getIntegerArrayListExtra("stock");

        Totalcost = Totalcost.replaceAll("[^0-9]","");
        Totalcost = decimalFormat.format(Integer.parseInt(Totalcost));
        paybtn = (ImageView) findViewById(R.id.payBtn);
        paybtn.setOnClickListener(this);
        OrderCount2.setText(Totalnum + "개");
        PayAmt2.setText(Totalcost + "원");
        TotalAmt2.setText(Totalcost + "원");

        DisAmt2.setTextColor(Color.RED);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        BusProvider.getInstance().post(new PushEvent("Pay_Touch_Now"));

//        if(v.getId()==R.id.smartpay || v.getId()==R.id.UsePoint || v.getId() == R.id.UseMembership){
        if(v.getId()==R.id.smartpay){
            Pay_Unclick.setBackgroundResource(R.drawable.unclick);
            smartpay2.setVisibility(View.GONE);
            totalText.setVisibility(View.GONE);
            paybtn.setBackgroundResource(R.drawable.cardpay);
            PaymentMethod.setWeightSum(4);
            LinearLayout.LayoutParams parmas = (LinearLayout.LayoutParams) PaymentMethod.getLayoutParams();
            parmas.weight = (float) 5.3;
            PaymentMethod.setLayoutParams(parmas);
        }

        if(v.getId() == R.id.CardPay || v.getId() == R.id.TossPay || v.getId() == R.id.KakaoPay || v.getId() == R.id.SamsungPay){
            smartpay2.setVisibility(View.GONE);
            smartpay.setBackgroundResource(R.drawable.payenter);
            Pay_Unclick.setBackgroundResource(R.drawable.unclick);
            paybtn.setBackgroundResource(R.drawable.select_cardpay);

            totalText.setVisibility(View.VISIBLE);
            PaymentMethod.setWeightSum(3);
            LinearLayout.LayoutParams parmas = (LinearLayout.LayoutParams) PaymentMethod.getLayoutParams();
            parmas.weight = (float) 4.3;
            PaymentMethod.setLayoutParams(parmas);

        }

        switch (v.getId()){
            case R.id.payBtn:

//                    if(paybtn.getBackground().getCurrent().equals(R.drawable.cardpay)){
//                        return;
//                    }

                if (v.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.cardpay).getConstantState())) {
                    MakeToast();
//                    Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
//                    smartpay.startAnimation(shake); // starts animation
//                    Toast.makeText(getApplicationContext(), "결제수단을 선택해주세요" , Toast.LENGTH_SHORT).show();
                    return; // Do something here
                }

//                String supjano = "4108685442";
//                String dev_tid = InfintiApplication.machineMaster.getDev_tid();
                String supjano = "1168119948";
                String dev_tid = "0788888"; // KICC 단말기 번호 - 테스트
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
// PASSWORD : 비밀번호 사용 가맹점만 대입
// SHOP_TID(8BYTE): 가맹점 TID (멀티사업자 사용시)
// SHOP_BIZ_NUM(10BYTE): 가맹점 사업자번호(멀티사업자 사용시)
// ADD_FIELD(100BYTE) : 추가필드(영업 담당자와 협의 후 사용가능) OPTIONAL
// TRAN_SERIALNO(12BYTE) : 거래일련번호 (영업 담당자와 협의 후 사용가능)OPTIONAL
// SIGN_FLAG(1BYTE) : 서명사용여부(거래일련번호취소 시만) 예)"Y", "N"  OPTIONAL  값을 넘기지 않는 경우 Y로 진행
                String sign_flag = "Y";
// TIMEOUT : 타임 아웃	카드 대기 타임아웃(Default 30초) OPTIONAL
                    String timeout = "30";
// IMG_BG_PATH : 배경이미지 경로  예) "/sdcard/kicc/background.png"  사이즈 600 * 470 권장 OPTIONAL
// IMG_CARD_PATH : 카드이미지 경로	"예) ""/sdcard/kicc/card.png" 사이즈 370 * 150 권장 OPTIONAL
// IMG_CLOSE_PATH : 닫기버튼 이미지 경로 "예) "/sdcard/kicc/close.png" 사이즈 45 * 45 권장" OPTIONAL
// TEXT_MAIN_SIZE : 결재진행 텍스트 사이즈	"int 예) 18  OPTIONAL
// TEXT_MAIN_COLOR : 결재진행 텍스트 컬러	예) "#303030" OPTIONAL
// TEXT_SUB1_SIZE : 무결성검사 텍스트 사이즈	"int 예) 12"  OPTIONAL
// TEXT_SUB1_COLOR: 무결성검사 텍스트 컬러	예) "#ff752a"  OPTIONAL
// TEXT_SUB2_SIZE : 식별번호 텍스트 사이즈	"int 예) 10"  OPTIONAL
// TEXT_SUB2_COLOR: 식별번호 텍스트 컬러	예) "#ff752a"  OPTIONAL
// TEXT_SUB3_SIZE : 타임아웃 텍스트 사이즈	"int 예) 16"  OPTIONAL
// TEXT_SUB3_COLOR : 타임아웃 텍스트 컬러	예) "#909090"  OPTIONAL
// TEXT_PROCESS : 결제 진행중 메시지 Default : "IC승인이 진행중입니다";
                    String text_process = "결제 진행중입니다";
// TEXT_COMPLETE : 결제 완료 성공 메시지 Default : "승인이 정상적으로 완료되었습니다";
                    String text_complete = "결제가 완료되었습니다";
// TEXT_DECLINE  : 결제 거절 시 메시지
                    String text_decline = "";
// PRINT_DATA    : 프린트함수 사용시

                    tranno += "_" + supjano;
                    Totalcost = Totalcost.replace(",","");
                    int totalcost = Integer.parseInt(Totalcost);
                    double mvat = (totalcost / 1.1) * 0.1;        // 부가세(VAT)
                    double mprice = Math.round(totalcost - mvat); // 부가세제외(반올림)
                    mvat = Math.round(mvat);                // 부가세(반올림)
                    total_amount = "" + totalcost;
                    tax = "" + (int)mvat;


                    date = new Date(now);
                    String TranNO;

                    ApprovalData approvalData = new ApprovalData(tranno, transType, terminal_type, "", total_amount, tax, tax_option,
                            tip, tip_option, "", "", "", "", "", "",
                            "",sign_flag ,timeout, text_process, text_complete, "", dev_tid, supjano, "");
// 결재함수 (응답값은 onActivityResult 에서)
                    InfintiApplication.set_Kicc_TranData(this, approvalData, "KICC");
                break;

            case R.id.smartpay:
                Pay_Unclick.setBackgroundResource(R.drawable.click);
                smartpay2.setVisibility(View.VISIBLE);
                smartpay.setBackgroundResource(R.drawable.smartpay);

                break;

            case R.id.UsePoint:

                break;

            case R.id.UseMembership:
//                payunclick3.setBackgroundResource(R.drawable.click);
//                UseMembership2.setVisibility(View.VISIBLE);
                break;

            case R.id.Pay_Cancel:
                this.finish();
                BusProvider.getInstance().post(new PushEvent("Detail_Touch_Now"));
                overridePendingTransition(0,R.anim.fadeout);
                break;

            case R.id.CardPay:
                Log.d("payicon", "cardPay");

                SelectPayIcon.setBackgroundResource(R.drawable.cardicon);
                TotalTV.setText(Totalcost +"원을 " + "<카드결제>" + "로 결제합니다. ");
                LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(70,70);
                cardParams.gravity = Gravity.CENTER;
                SelectPayIcon.setLayoutParams(cardParams);

                break;

            case R.id.SamsungPay:
                Log.d("payicon", "SamsungPay");
                LinearLayout.LayoutParams iconParams = new LinearLayout.LayoutParams(70,40);
                iconParams.gravity = Gravity.CENTER;
                SelectPayIcon.setLayoutParams(iconParams);
                SelectPayIcon.setBackgroundResource(R.drawable.sspayicon);
                TotalTV.setText(Totalcost +"원을 " + "<삼성페이>" + "로 결제합니다. ");

                break;

            case R.id.KakaoPay:
                Log.d("payicon", "KakaoPay");
                LinearLayout.LayoutParams kakaoParams = new LinearLayout.LayoutParams(100,40);
                kakaoParams.gravity = Gravity.CENTER;
                SelectPayIcon.setLayoutParams(kakaoParams);
                SelectPayIcon.setBackgroundResource(R.drawable.kaopayicon);
                TotalTV.setText(Totalcost +"원을 " + "<카카오페이>" + "로 결제합니다. ");

                break;

            case R.id.TossPay:
                Log.d("payicon", "TossPay");
                LinearLayout.LayoutParams tossParams = new LinearLayout.LayoutParams(130,40);
                tossParams.gravity = Gravity.CENTER;
                SelectPayIcon.setLayoutParams(tossParams);
                SelectPayIcon.setBackgroundResource(R.drawable.tosicon);
                TotalTV.setText(Totalcost +"원을 " + "<토스>" + "로 결제합니다. ");

                break;

        }
    }

    /**
     * 승인결과
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Intent cancelInfo = data;
// 승인 처리 시간이 종료되었습니다.(9977)
                if ("9977".equals(data.getStringExtra("RESULT_CODE"))) cnt9977++;
                approvalResult = new ApprovalResult();
                approvalResult.setTRAN_NO(data.getStringExtra("TRAN_NO"));
                InfintiApplication.Approve_NO = data.getStringExtra("TRAN_NO");
                approvalResult.setTRAN_TYPE(data.getStringExtra("TRAN_TYPE"));
                approvalResult.setCARD_NUM(data.getStringExtra("CARD_NUM"));
                approvalResult.setCARD_NAME(data.getStringExtra("CARD_NAME"));
                approvalResult.setISSUER_CODE(data.getStringExtra("ISSUER_CODE"));
                approvalResult.setTOTAL_AMOUNT(data.getStringExtra("TOTAL_AMOUNT"));
                approvalResult.setTAX(data.getStringExtra("TAX"));
                approvalResult.setTIP(data.getStringExtra("TIP"));
                approvalResult.setINSTALLMENT(data.getStringExtra("INSTALLMENT"));
                approvalResult.setRESULT_CODE(data.getStringExtra("RESULT_CODE"));
                approvalResult.setRESULT_MSG(data.getStringExtra("RESULT_MSG"));
                approvalResult.setAPPROVAL_NUM(data.getStringExtra("APPROVAL_NUM"));
                approvalResult.setTOTAL_AMOUNT(data.getStringExtra("TOTAL_AMOUNT"));
                approvalResult.setAPPROVAL_DATE(data.getStringExtra("APPROVAL_DATE"));
                approvalResult.setACQUIRER_CODE(data.getStringExtra("ACQUIRER_CODE"));
                approvalResult.setAD1(data.getStringExtra("AD1"));
                approvalResult.setAD2(data.getStringExtra("AD2"));
                approvalResult.setMERCHANT_NUM(data.getStringExtra("MERCHANT_NUM"));
                approvalResult.setSHOP_TID(data.getStringExtra("SHOP_TID"));
                approvalResult.setSHOP_BIZ_NUM(data.getStringExtra("SHOP_BIZ_NUM"));
                approvalResult.setADD_FIELD(data.getStringExtra("ADD_FIELD"));

                approvalResult.setNOTICE(data.getStringExtra("NOTICE"));
                approvalResult.setCASHAMOUNT(data.getStringExtra("CASHAMOUNT"));
                approvalResult.setTPK(data.getStringExtra("TPK"));
                approvalResult.setTRAN_SERIALNO(data.getStringExtra("TRAN_SERIALNO"));
                approvalResult.setSHOP_NAME(data.getStringExtra("SHOP_NAME"));
                approvalResult.setSHOP_TEL(data.getStringExtra("SHOP_TEL"));
                approvalResult.setSHOP_ADDRESS(data.getStringExtra("SHOP_ADDRESS"));
                approvalResult.setSHOP_OWNER(data.getStringExtra("SHOP_ADDRESS"));
            }

            String result_code = approvalResult.getRESULT_CODE();
            String tran_type = approvalResult.getTRAN_TYPE();

// 정상적인 결재 승인정보가 온다면 해당털럼을 오픈한다...정상완료:0000
            if (result_code.equalsIgnoreCase("0000")) {
// D1: 카드승인, D4 : 카드취소
                if (tran_type.equalsIgnoreCase("D1") || tran_type.equalsIgnoreCase("D4")) {
// 서버에 저장한다.
                    String text_process = "취소가 진행중입니다";
                    String text_complete = "취소가 완료되었습니다";
                    String signflag = "Y";
                    String timeout = "30";
//                   ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★ 출시용 apk 파일에는 실제 사업자 번호 사용
//                    String supjano = "4108685442";
                    String supjano = "1168119948"; // KICC 사업자번호 - 테스트
                    String TranNO;
                    TranNO = approvalResult.getTRAN_NO();
                    ApprovalData cancelData = new ApprovalData(TranNO, "D4", "40", "", approvalReport.getApproval_cost(), approvalResult.getTAX(), "A",
                            "0", "N", approvalResult.getAPPROVAL_NUM(), approvalResult.getAPPROVAL_DATE(), approvalResult.getTRAN_SERIALNO(), "", "", "",
                            "",signflag,timeout, text_process, text_complete, "", InfintiApplication.machineMaster.getDev_tid(), InfintiApplication.machineMaster.getCommon_saupjano(),  mDiscount + ""
                    );
                    // 결재함수 (응답값은 onActivityResult 에서)
                    InfintiApplication.cancelData = cancelData;

                    ArrayList<MachineColummInfo> machineColummInfos = new ArrayList<>();

                    String strQry = infintiApplication.fun_approval_data_save(this, approvalResult, selected, stock);
//                    BusProvider.getInstance().post(new PushEvent("XPERON_ORDER_APPLOVAL_OK", selected.getColumm()));
                    GetMachineInitItem getMachineInitItem = new GetMachineInitItem(this, strQry, "CARD_APPROVAL");
                    init_data_thread = new Thread(getMachineInitItem);
                    init_data_thread.start();
                }
            }
        }
    }

    @Override
    public void onMachine_Data_ReceiveRun(Object arrayList) {

        ResponseHeader responseHeader;
        responseHeader = (ResponseHeader)arrayList;

        if (responseHeader.getRequest().equalsIgnoreCase("api_cash_master_upload")) {
            if (responseHeader.getStatus().equalsIgnoreCase("OK")) {
                switch (InfintiApplication.Tab_name) {
                    // 결제 후 물품출고 화면
                    case "XPERON":
                        this.finish();
                        BusProvider.getInstance().post(new PushEvent("product_out", selected));
                        break;
                }
            }
        }

    }

    public void MakeToast(){
        // Custom 레이아웃 연결 위해 LayoutInflater 객체 생성
        LayoutInflater inflater = getLayoutInflater();
// Custom 레이아웃 Imflatation '인플레이션', 레이아웃 메모리에 객체화
        View layout = inflater.inflate(R.layout.toast_custom, (ViewGroup) findViewById(R.id.custom_toast_layout));
// 보여줄 메시지 설정 위해 TextView 객체 연결, 인플레이션해서 생성된 View를 통해 findViewById 실행
        TextView message = layout.findViewById(R.id.custom_toast_message);
        message.setText("결제 수단을 선택해주세요");
// 보여줄 이미지 설정 위해 ImageView 연결
        ImageView image = layout.findViewById(R.id.custom_toast_image);
//        image.setBackgroundResource(R.drawable.ic_message);

// Toast 객체 생성
        Toast toast = new Toast(this);
// 위치설정, Gravity - 기준지정(상단,왼쪽 기준 0,0) / xOffset, yOffset - Gravity기준으로 위치 설정
        toast.setGravity(Gravity.BOTTOM| Gravity.CENTER, 0, 550);
// Toast 보여줄 시간 'Toast.LENGTH_SHORT 짧게'
        toast.setDuration(Toast.LENGTH_SHORT);
// CustomLayout 객체 연결
        toast.setView(layout);
// Toast 보여주기
//        toast.setMargin(500,0);
        toast.show();
    }

    @Subscribe
    public void ReveiveOttoData(PushEvent event) {
        switch (event.getCommand()) {
            case "PAY_HANDLER_END":
                this.finish();
                Log.d("EndHandler", "5 --ReveiveOttoData PAY_HANDLER_END ");
                BusProvider.getInstance().post(new PushEvent("Detail_Touch_Now"));
                break;
        }
    }
}



