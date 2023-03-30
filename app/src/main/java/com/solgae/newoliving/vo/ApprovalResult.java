package com.solgae.newoliving.vo;

public class ApprovalResult {
    private String TRAN_NO;
/**
 * 거래구분자
 * D1 : 승인
 * D4 : 당일취소/전일취소(반품환불)
 * M8 : 포인트조회
 */
    private String TRAN_TYPE;
// 결제한 카드번호/식별번호 카드결제인경우 - "499912" (Card BIN 만 전송)
    private String CARD_NUM;
// 결제한 카드사
    private String CARD_NAME;
// 발급사 코드
    private String ISSUER_CODE;
// 총 결제 금액
    private String TOTAL_AMOUNT;
// 부가세 금액
    private String TAX;
// 봉사료 금액
    private String TIP;
/**
 * 할부유무/현금영수증 취소사유
 * (신용승인/취소)
 * "0" - 일시불
 * "N" - 할부 N개월, N은 할부 개월수
 */
    private String INSTALLMENT;
/**
 * 응답코드
 * 정상응답 - "0000"
 * 거절응답 - KICC에서 제공하는 응답코드 테이블 참조
 */
    private String RESULT_CODE;
// 거절응답시 사유
    private String RESULT_MSG;
// 거래 승인번호
    private String APPROVAL_NUM;
/**
 * 거래 승인날짜
 * "YYMMDDHHMMSSW"
 * W 는 요일 (일요일:0, 월요일:1, 화요일:2 …토요일:6)
 * 예) 2013년 2월 1일 12시 15분 17초 금요일 인경우
 * "1302011215175"
 */
    private String APPROVAL_DATE;
// 매입사 코드
    private String ACQUIRER_CODE;
// 매입사명
    private String ACQUIRER_NAME;
// 알림1
    private String AD1;
// 알림2
    private String AD2;
// 가맹점번호
    private String MERCHANT_NUM;
// 결제된 가맹점 TID
    private String SHOP_TID;
// 결제된 가맹점 사업자번호
    private String SHOP_BIZ_NUM;
// 추가필드(영업 담당자와 협의 후 사용가능
    private String ADD_FIELD;
    private String NOTICE;
// 현금지급금액
    private String CASHAMOUNT;
    private String TPK;
// 거래일련번호(영업 담당자와 협의 후 사용가능)
    private String TRAN_SERIALNO;
// 가맹점 명
    private String SHOP_NAME;
// 가맹점 전화번호
    private String SHOP_TEL;
// 가맹점 주소
    private String SHOP_ADDRESS;
// 가맹점주명
    private String SHOP_OWNER;

    public boolean isPoint() {
        return point;
    }

    public void setPoint(boolean point) {
        this.point = point;
    }

    private boolean point = false;

    public ApprovalResult() {}

    public ApprovalResult(String TRAN_NO, String TRAN_TYPE, String CARD_NUM, String CARD_NAME, String ISSUER_CODE,
                           String TOTAL_AMOUNT, String TAX, String TIP, String INSTALLMENT, String RESULT_CODE,
                           String RESULT_MSG, String APPROVAL_NUM, String APPROVAL_DATE, String ACQUIRER_CODE,
                           String ACQUIRER_NAME, String AD1, String AD2, String MERCHANT_NUM, String SHOP_TID,
                           String SHOP_BIZ_NUM, String ADD_FIELD, String NOTICE, String CASHAMOUNT, String TPK,
                           String TRAN_SERIALNO, String SHOP_NAME, String SHOP_TEL, String SHOP_ADDRESS, String SHOP_OWNER) {
        this.TRAN_NO = TRAN_NO;                  // 받은 요청 순번 그대로 전송
        this.TRAN_TYPE = TRAN_TYPE;
        this.CARD_NUM = CARD_NUM;
        this.CARD_NAME = CARD_NAME;
        this.ISSUER_CODE = ISSUER_CODE;
        this.TOTAL_AMOUNT = TOTAL_AMOUNT;
        this.TAX = TAX;
        this.TIP = TIP;
        this.INSTALLMENT = INSTALLMENT;
        this.RESULT_CODE = RESULT_CODE;
        this.RESULT_MSG = RESULT_MSG;
        this.APPROVAL_NUM = APPROVAL_NUM;
        this.APPROVAL_DATE = APPROVAL_DATE;
        this.ACQUIRER_CODE = ACQUIRER_CODE;
        this.ACQUIRER_NAME = ACQUIRER_NAME;
        this.AD1 = AD1;
        this.AD2 = AD2;
        this.MERCHANT_NUM = MERCHANT_NUM;
        this.SHOP_TID = SHOP_TID;
        this.SHOP_BIZ_NUM = SHOP_BIZ_NUM;
        this.ADD_FIELD = ADD_FIELD;
        this.NOTICE = NOTICE;
        this.CASHAMOUNT = CASHAMOUNT;
        this.TPK = TPK;
        this.TRAN_SERIALNO = TRAN_SERIALNO;
        this.SHOP_NAME = SHOP_NAME;
        this.SHOP_TEL = SHOP_TEL;
        this.SHOP_ADDRESS = SHOP_ADDRESS;
        this.SHOP_OWNER = SHOP_OWNER;
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

    public String getCARD_NUM() {
        return CARD_NUM;
    }

    public void setCARD_NUM(String CARD_NUM) {
        this.CARD_NUM = CARD_NUM;
    }

    public String getCARD_NAME() {
        return CARD_NAME;
    }

    public void setCARD_NAME(String CARD_NAME) {
        this.CARD_NAME = CARD_NAME;
    }

    public String getISSUER_CODE() {
        return ISSUER_CODE;
    }

    public void setISSUER_CODE(String ISSUER_CODE) {
        this.ISSUER_CODE = ISSUER_CODE;
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

    public String getTIP() {
        return TIP;
    }

    public void setTIP(String TIP) {
        this.TIP = TIP;
    }

    public String getINSTALLMENT() {
        return INSTALLMENT;
    }

    public void setINSTALLMENT(String INSTALLMENT) {
        this.INSTALLMENT = INSTALLMENT;
    }

    public String getRESULT_CODE() {
        return RESULT_CODE;
    }

    public void setRESULT_CODE(String RESULT_CODE) {
        this.RESULT_CODE = RESULT_CODE;
    }

    public String getRESULT_MSG() {
        return RESULT_MSG;
    }

    public void setRESULT_MSG(String RESULT_MSG) {
        this.RESULT_MSG = RESULT_MSG;
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

    public String getACQUIRER_CODE() {
        return ACQUIRER_CODE;
    }

    public void setACQUIRER_CODE(String ACQUIRER_CODE) {
        this.ACQUIRER_CODE = ACQUIRER_CODE;
    }

    public String getACQUIRER_NAME() {
        return ACQUIRER_NAME;
    }

    public void setACQUIRER_NAME(String ACQUIRER_NAME) {
        this.ACQUIRER_NAME = ACQUIRER_NAME;
    }

    public String getAD1() {
        return AD1;
    }

    public void setAD1(String AD1) {
        this.AD1 = AD1;
    }

    public String getAD2() {
        return AD2;
    }

    public void setAD2(String AD2) {
        this.AD2 = AD2;
    }

    public String getMERCHANT_NUM() {
        return MERCHANT_NUM;
    }

    public void setMERCHANT_NUM(String MERCHANT_NUM) {
        this.MERCHANT_NUM = MERCHANT_NUM;
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

    public String getADD_FIELD() {
        return ADD_FIELD;
    }

    public void setADD_FIELD(String ADD_FIELD) {
        this.ADD_FIELD = ADD_FIELD;
    }

    public String getNOTICE() {
        return NOTICE;
    }

    public void setNOTICE(String NOTICE) {
        this.NOTICE = NOTICE;
    }

    public String getCASHAMOUNT() {
        return CASHAMOUNT;
    }

    public void setCASHAMOUNT(String CASHAMOUNT) {
        this.CASHAMOUNT = CASHAMOUNT;
    }

    public String getTPK() {
        return TPK;
    }

    public void setTPK(String TPK) {
        this.TPK = TPK;
    }

    public String getTRAN_SERIALNO() {
        return TRAN_SERIALNO;
    }

    public void setTRAN_SERIALNO(String TRAN_SERIALNO) {
        this.TRAN_SERIALNO = TRAN_SERIALNO;
    }

    public String getSHOP_NAME() {
        return SHOP_NAME;
    }

    public void setSHOP_NAME(String SHOP_NAME) {
        this.SHOP_NAME = SHOP_NAME;
    }

    public String getSHOP_TEL() {
        return SHOP_TEL;
    }

    public void setSHOP_TEL(String SHOP_TEL) {
        this.SHOP_TEL = SHOP_TEL;
    }

    public String getSHOP_ADDRESS() {
        return SHOP_ADDRESS;
    }

    public void setSHOP_ADDRESS(String SHOP_ADDRESS) {
        this.SHOP_ADDRESS = SHOP_ADDRESS;
    }

    public String getSHOP_OWNER() {
        return SHOP_OWNER;
    }

    public void setSHOP_OWNER(String SHOP_OWNER) {
        this.SHOP_OWNER = SHOP_OWNER;
    }
}
