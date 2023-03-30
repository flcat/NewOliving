package com.solgae.newoliving.vo;

import androidx.annotation.Nullable;

public class ApplovalData {
    private String TRAN_NO;             // 장비코드_사업자번호_일자_순번
// 멀티사업자용
    private String SHOP_TID;
    private String SHOP_BIZ_NUM;
/**
 * TRAN_TYPE
 * D1 : 승인
 * D4 : 당일취소/전일취소(반품환불)
 * M8 : 포인트 조회
 * RB : 직전거래통신취소(망취소)-TRAN_NO로 거래 취소
 * LA : 직전거래 응답값 재전송 요청(응답값 못받아을 경우)
 * PT : 단말기 프린트 명령어(단말기연결시에만 사용가능)
 */
    private String TRAN_TYPE;
// 40 : 일반거래  CE: CJ 직원할인
    private String TERMINAL_TYPE;
// 결제 거절 시 메시지(서버에서 보내온다)
    private String TEXT_DECLINE;
// 거래금액
    private String TOTAL_AMOUNT;
// 부과세
    private String TAX;
// M:입력값으로 처리(Default) A : 총 결제 금액에서 자동 계산(10%)
    private String TAX_OPTION;
// 봉사료 금액
    private String TIP;
/**
 * 봉사료 옵션
 * N : 총금액에 미포함(Default)
 *  - TOTAL_AMOUNT=1000,TIP=100 일 경우
 *    실 결제금액은 1100, 부가세 1000원에 대해서 계산
 * A : 총금액에 포함
 *  - TOTAL_AMOUNT=1000,TIP=100 일 경우
 *    실 결제금액은 1000, 부가세 900원에 대해서 계산
 */
    private String TIP_OPTION;
// 취소 거래, 상세 거래내역 에만 필요함 승인거래시 전달받은 승인번호 (취소거래시)
    private String APPROVAL_NUM;
// 취소 거래, 취소 거래시, 상세 거래내역 에만 필요함 승인거래시 전달받은 승인날짜
    private String APPROVAL_DATE;
// 거래일련번호 (영업 담당자와 협의 후 사용가능)
    private String TRAN_SERIALNO;
// 현금지급금액
    private String CASHAMOUNT;
// 할부유무 (신용승인/취소) "0" - 일시불 "N" - 할부 N개월, N은 할부 개월수
    private String INSTALLMENT;
    private String PRINT_DATA;
// 추가필드  (영업 담당자와 협의 후 사용가능)
    private String ADD_FIELD;
// 카드 대기 타임아웃(Default 30초)
    private String TIMEOUT;
// 결제 진행중 메시지  -->  Default : "IC승인이 진행중입니다";
    private String TEXT_PROCESS;
// 결제 완료 성공 메시지 ==> Default : "승인이 정상적으로 완료되었습니다";
    private String TEXT_COMPLETE;
    private String COLUMM_NO;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

    private String discount;

    public ApplovalData(String TRAN_NO, String TRAN_TYPE, String TERMINAL_TYPE, String TEXT_DECLINE, String TOTAL_AMOUNT, String TAX,
                        String TAX_OPTION, String TIP, String TIP_OPTION, String APPROVAL_NUM, String APPROVAL_DATE, String TRAN_SERIALNO,
                        String CASHAMOUNT, String INSTALLMENT, String PRINT_DATA, String ADD_FIELD, String TIMEOUT, String TEXT_PROCESS,
                        String TEXT_COMPLETE, String COLUMM_NO, String SHOP_TID, String SHOP_BIZ_NUM, String discount, @Nullable String type) {
        this.TRAN_NO = TRAN_NO;
        this.TRAN_TYPE = TRAN_TYPE;
        this.TERMINAL_TYPE = TERMINAL_TYPE;
        this.TEXT_DECLINE = TEXT_DECLINE;
        this.TOTAL_AMOUNT = TOTAL_AMOUNT;
        this.TAX = TAX;
        this.TAX_OPTION = TAX_OPTION;
        this.TIP = TIP;
        this.TIP_OPTION = TIP_OPTION;
        this.APPROVAL_NUM = APPROVAL_NUM;
        this.APPROVAL_DATE = APPROVAL_DATE;
        this.TRAN_SERIALNO = TRAN_SERIALNO;
        this.CASHAMOUNT = CASHAMOUNT;
        this.INSTALLMENT = INSTALLMENT;
        this.PRINT_DATA = PRINT_DATA;
        this.ADD_FIELD = ADD_FIELD;
        this.TIMEOUT = TIMEOUT;
        this.TEXT_PROCESS = TEXT_PROCESS;
        this.TEXT_COMPLETE = TEXT_COMPLETE;
        this.COLUMM_NO = COLUMM_NO;
        this.SHOP_TID = SHOP_TID;
        this.SHOP_BIZ_NUM = SHOP_BIZ_NUM;
        this.discount = discount;
        this.type = type;
    }
    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getTRAN_NO() {
        return TRAN_NO;
    }

    public void setTRAN_NO(String TRAN_NO) {
        this.TRAN_NO = TRAN_NO;
    }

    public String getTRAN_TYPE() {
        return TRAN_TYPE;
    }

    public void setTRAN_TYPE(String TRAN_TYPE) {
        this.TRAN_TYPE = TRAN_TYPE;
    }

    public String getTERMINAL_TYPE() {
        return TERMINAL_TYPE;
    }

    public void setTERMINAL_TYPE(String TERMINAL_TYPE) {
        this.TERMINAL_TYPE = TERMINAL_TYPE;
    }

    public String getTEXT_DECLINE() {
        return TEXT_DECLINE;
    }

    public void setTEXT_DECLINE(String TEXT_DECLINE) {
        this.TEXT_DECLINE = TEXT_DECLINE;
    }

    public String getTOTAL_AMOUNT() {
        return TOTAL_AMOUNT;
    }

    public void setTOTAL_AMOUNT(String TOTAL_AMOUNT) {
        this.TOTAL_AMOUNT = TOTAL_AMOUNT;
    }

    public String getTAX() {
        return TAX;
    }

    public void setTAX(String TAX) {
        this.TAX = TAX;
    }

    public String getTAX_OPTION() {
        return TAX_OPTION;
    }

    public void setTAX_OPTION(String TAX_OPTION) {
        this.TAX_OPTION = TAX_OPTION;
    }

    public String getTIP() {
        return TIP;
    }

    public void setTIP(String TIP) {
        this.TIP = TIP;
    }

    public String getTIP_OPTION() {
        return TIP_OPTION;
    }

    public void setTIP_OPTION(String TIP_OPTION) {
        this.TIP_OPTION = TIP_OPTION;
    }

    public String getAPPROVAL_NUM() {
        return APPROVAL_NUM;
    }

    public void setAPPROVAL_NUM(String APPROVAL_NUM) {
        this.APPROVAL_NUM = APPROVAL_NUM;
    }

    public String getAPPROVAL_DATE() {
        return APPROVAL_DATE;
    }

    public void setAPPROVAL_DATE(String APPROVAL_DATE) {
        this.APPROVAL_DATE = APPROVAL_DATE;
    }

    public String getTRAN_SERIALNO() {
        return TRAN_SERIALNO;
    }

    public void setTRAN_SERIALNO(String TRAN_SERIALNO) {
        this.TRAN_SERIALNO = TRAN_SERIALNO;
    }

    public String getCASHAMOUNT() {
        return CASHAMOUNT;
    }

    public void setCASHAMOUNT(String CASHAMOUNT) {
        this.CASHAMOUNT = CASHAMOUNT;
    }

    public String getINSTALLMENT() {
        return INSTALLMENT;
    }

    public void setINSTALLMENT(String INSTALLMENT) {
        this.INSTALLMENT = INSTALLMENT;
    }

    public String getPRINT_DATA() {
        return PRINT_DATA;
    }

    public void setPRINT_DATA(String PRINT_DATA) {
        this.PRINT_DATA = PRINT_DATA;
    }

    public String getADD_FIELD() {
        return ADD_FIELD;
    }

    public void setADD_FIELD(String ADD_FIELD) {
        this.ADD_FIELD = ADD_FIELD;
    }

    public String getTIMEOUT() {
        return TIMEOUT;
    }

    public void setTIMEOUT(String TIMEOUT) {
        this.TIMEOUT = TIMEOUT;
    }

    public String getTEXT_PROCESS() {
        return TEXT_PROCESS;
    }

    public void setTEXT_PROCESS(String TEXT_PROCESS) {
        this.TEXT_PROCESS = TEXT_PROCESS;
    }

    public String getTEXT_COMPLETE() {
        return TEXT_COMPLETE;
    }

    public void setTEXT_COMPLETE(String TEXT_COMPLETE) {
        this.TEXT_COMPLETE = TEXT_COMPLETE;
    }

    public String getCOLUMM_NO() {
        return COLUMM_NO;
    }

    public void setCOLUMM_NO(String COLUMM_NO) {
        this.COLUMM_NO = COLUMM_NO;
    }

    public String getSHOP_TID() {
        return SHOP_TID;
    }

    public void setSHOP_TID(String SHOP_TID) {
        this.SHOP_TID = SHOP_TID;
    }

    public String getSHOP_BIZ_NUM() {
        return SHOP_BIZ_NUM;
    }

    public void setSHOP_BIZ_NUM(String SHOP_BIZ_NUM) {
        this.SHOP_BIZ_NUM = SHOP_BIZ_NUM;
    }
}
