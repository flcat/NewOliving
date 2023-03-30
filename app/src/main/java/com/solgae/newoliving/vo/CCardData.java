package com.solgae.newoliving.vo;

public class CCardData {
    String BILL_NO;            // 디바이스|년월일|시분초
    String MAC_CODE;
    String POS_NUM;
    String USERID;
    String CUSTOM_ID ;         // 고객카드번호
    String TOTAL_MONEY;        // 판매금액
    String REAL_MONEY;         // total_money과 동일
    String CASH_MONEY;         // 현금결제
    String CARD_MONEY;         // 카드결제
    String POINT_MONEY;        // 포인트결제
    String DIS_MONEY;           // 할인금액
    String COUPON_MONEY ;
    String GIFT_MONEY;
    String GIFTCARD_MONEY;     // 충전카드용 결제
    String REFUND_MONEY;       // 거스름돈 항상 0
    String SALE_POINT_MONEY ;
    String SALE_CUT_MONEY;
    String SUPPLY_MONEY;        // 공급가
    String CHARGE_MONEY;
    String TAX_MONEY;            // 부가세
    String DIS_TYPE ;
    String SALE_STATUS ;
    String WRITE_DAY;
    String CANCEL_REASON;
    String OLD_BILL_NO;
    String IS_CANCEL;
    String DATA_TYPE;
    String DATA_MONEY ;
    String DATA_ACTION;
    String DATA_NUM;                // 카드번호
    String DATA_DIV;                // 할부개월
    String DATA_DAY;               // van에서 온 승인.취소일자
    String DATA_CONFIRM_NUM;      // 승인번호
    String DATA_RECEIPT_NUM ;
    String DATA_VAN_INFO ;
    String DATA_RECEIPT_TYPE;
    String DATA_RECEIPT_INPUT ;
    String DATA_VAN ;
    String DATA_COM ;               // 발급사명
    String DATA_ACQUIRE_COM;       // 매입사명
    String DATA_COM_CODE;          // 발급사코드
    String DATA_ACQUIRE_COM_CODE; // 매입사코드

    public CCardData() {

    }

    public CCardData(String BILL_NO, String MAC_CODE, String POS_NUM, String USERID,
                          String CUSTOM_ID, String TOTAL_MONEY, String REAL_MONEY, String CASH_MONEY,
                          String CARD_MONEY, String POINT_MONEY, String DIS_MONEY, String COUPON_MONEY,
                          String GIFT_MONEY, String GIFTCARD_MONEY, String REFUND_MONEY, String SALE_POINT_MONEY,
                          String SALE_CUT_MONEY, String SUPPLY_MONEY, String CHARGE_MONEY, String TAX_MONEY,
                          String DIS_TYPE, String SALE_STATUS, String WRITE_DAY, String CANCEL_REASON, String OLD_BILL_NO,
                          String IS_CANCEL, String DATA_TYPE, String DATA_MONEY, String DATA_ACTION, String DATA_NUM,
                          String DATA_DIV, String DATA_DAY, String DATA_CONFIRM_NUM, String DATA_RECEIPT_NUM, String DATA_VAN_INFO,
                          String DATA_RECEIPT_TYPE, String DATA_RECEIPT_INPUT, String DATA_VAN, String DATA_COM,
                          String DATA_ACQUIRE_COM, String DATA_COM_CODE, String DATA_ACQUIRE_COM_CODE){
        this.BILL_NO = BILL_NO;
        this.MAC_CODE = MAC_CODE;
        this.POS_NUM = POS_NUM;
        this.USERID = USERID;
        this.CUSTOM_ID = CUSTOM_ID;
        this.TOTAL_MONEY = TOTAL_MONEY;
        this.REAL_MONEY = REAL_MONEY;
        this.CASH_MONEY = CASH_MONEY;
        this.CARD_MONEY = CARD_MONEY;
        this.POINT_MONEY = POINT_MONEY;
        this.DIS_MONEY = DIS_MONEY;
        this.COUPON_MONEY = COUPON_MONEY;
        this.GIFT_MONEY = GIFT_MONEY;
        this.GIFTCARD_MONEY = GIFTCARD_MONEY;
        this.REFUND_MONEY = REFUND_MONEY;
        this.SALE_POINT_MONEY = SALE_POINT_MONEY;
        this.SALE_CUT_MONEY = SALE_CUT_MONEY;
        this.SUPPLY_MONEY = SUPPLY_MONEY;
        this.CHARGE_MONEY = CHARGE_MONEY;
        this.TAX_MONEY = TAX_MONEY;
        this.DIS_TYPE = DIS_TYPE;
        this.SALE_STATUS = SALE_STATUS;
        this.WRITE_DAY = WRITE_DAY;
        this.CANCEL_REASON = CANCEL_REASON;
        this.OLD_BILL_NO = OLD_BILL_NO;
        this.IS_CANCEL = IS_CANCEL;
        this.DATA_TYPE = DATA_TYPE;
        this.DATA_MONEY = DATA_MONEY;
        this.DATA_ACTION = DATA_ACTION;
        this.DATA_NUM = DATA_NUM;
        this.DATA_DIV = DATA_DIV;
        this.DATA_DAY = DATA_DAY;
        this.DATA_CONFIRM_NUM = DATA_CONFIRM_NUM;
        this.DATA_RECEIPT_NUM = DATA_RECEIPT_NUM;
        this.DATA_VAN_INFO = DATA_VAN_INFO;
        this.DATA_RECEIPT_TYPE = DATA_RECEIPT_TYPE;
        this.DATA_RECEIPT_INPUT = DATA_RECEIPT_INPUT;
        this.DATA_VAN = DATA_VAN;
        this.DATA_COM = DATA_COM;
        this.DATA_ACQUIRE_COM = DATA_ACQUIRE_COM;
        this.DATA_COM_CODE = DATA_COM_CODE;
        this.DATA_ACQUIRE_COM_CODE = DATA_ACQUIRE_COM_CODE;
    }

    public String getBILL_NO() {
        return BILL_NO;
    }

    public void setBILL_NO(String BILL_NO) {
        this.BILL_NO = BILL_NO;
    }

    public String getMAC_CODE() {
        return MAC_CODE;
    }

    public void setMAC_CODE(String MAC_CODE) {
        this.MAC_CODE = MAC_CODE;
    }

    public String getPOS_NUM() {
        return POS_NUM;
    }

    public void setPOS_NUM(String POS_NUM) {
        this.POS_NUM = POS_NUM;
    }

    public String getUSERID() {
        return USERID;
    }

    public void setUSERID(String USERID) {
        this.USERID = USERID;
    }

    public String getCUSTOM_ID() {
        return CUSTOM_ID;
    }

    public void setCUSTOM_ID(String CUSTOM_ID) {
        this.CUSTOM_ID = CUSTOM_ID;
    }

    public String getTOTAL_MONEY() {
        return TOTAL_MONEY;
    }

    public void setTOTAL_MONEY(String TOTAL_MONEY) {
        this.TOTAL_MONEY = TOTAL_MONEY;
    }

    public String getREAL_MONEY() {
        return REAL_MONEY;
    }

    public void setREAL_MONEY(String REAL_MONEY) {
        this.REAL_MONEY = REAL_MONEY;
    }

    public String getCASH_MONEY() {
        return CASH_MONEY;
    }

    public void setCASH_MONEY(String CASH_MONEY) {
        this.CASH_MONEY = CASH_MONEY;
    }

    public String getCARD_MONEY() {
        return CARD_MONEY;
    }

    public void setCARD_MONEY(String CARD_MONEY) {
        this.CARD_MONEY = CARD_MONEY;
    }

    public String getPOINT_MONEY() {
        return POINT_MONEY;
    }

    public void setPOINT_MONEY(String POINT_MONEY) {
        this.POINT_MONEY = POINT_MONEY;
    }

    public String getDIS_MONEY() {
        return DIS_MONEY;
    }

    public void setDIS_MONEY(String DIS_MONEY) {
        this.DIS_MONEY = DIS_MONEY;
    }

    public String getCOUPON_MONEY() {
        return COUPON_MONEY;
    }

    public void setCOUPON_MONEY(String COUPON_MONEY) {
        this.COUPON_MONEY = COUPON_MONEY;
    }

    public String getGIFT_MONEY() {
        return GIFT_MONEY;
    }

    public void setGIFT_MONEY(String GIFT_MONEY) {
        this.GIFT_MONEY = GIFT_MONEY;
    }

    public String getGIFTCARD_MONEY() {
        return GIFTCARD_MONEY;
    }

    public void setGIFTCARD_MONEY(String GIFTCARD_MONEY) {
        this.GIFTCARD_MONEY = GIFTCARD_MONEY;
    }

    public String getREFUND_MONEY() {
        return REFUND_MONEY;
    }

    public void setREFUND_MONEY(String REFUND_MONEY) {
        this.REFUND_MONEY = REFUND_MONEY;
    }

    public String getSALE_POINT_MONEY() {
        return SALE_POINT_MONEY;
    }

    public void setSALE_POINT_MONEY(String SALE_POINT_MONEY) {
        this.SALE_POINT_MONEY = SALE_POINT_MONEY;
    }

    public String getSALE_CUT_MONEY() {
        return SALE_CUT_MONEY;
    }

    public void setSALE_CUT_MONEY(String SALE_CUT_MONEY) {
        this.SALE_CUT_MONEY = SALE_CUT_MONEY;
    }

    public String getSUPPLY_MONEY() {
        return SUPPLY_MONEY;
    }

    public void setSUPPLY_MONEY(String SUPPLY_MONEY) {
        this.SUPPLY_MONEY = SUPPLY_MONEY;
    }

    public String getCHARGE_MONEY() {
        return CHARGE_MONEY;
    }

    public void setCHARGE_MONEY(String CHARGE_MONEY) {
        this.CHARGE_MONEY = CHARGE_MONEY;
    }

    public String getTAX_MONEY() {
        return TAX_MONEY;
    }

    public void setTAX_MONEY(String TAX_MONEY) {
        this.TAX_MONEY = TAX_MONEY;
    }

    public String getDIS_TYPE() {
        return DIS_TYPE;
    }

    public void setDIS_TYPE(String DIS_TYPE) {
        this.DIS_TYPE = DIS_TYPE;
    }

    public String getSALE_STATUS() {
        return SALE_STATUS;
    }

    public void setSALE_STATUS(String SALE_STATUS) {
        this.SALE_STATUS = SALE_STATUS;
    }

    public String getWRITE_DAY() {
        return WRITE_DAY;
    }

    public void setWRITE_DAY(String WRITE_DAY) {
        this.WRITE_DAY = WRITE_DAY;
    }

    public String getCANCEL_REASON() {
        return CANCEL_REASON;
    }

    public void setCANCEL_REASON(String CANCEL_REASON) {
        this.CANCEL_REASON = CANCEL_REASON;
    }

    public String getOLD_BILL_NO() {
        return OLD_BILL_NO;
    }

    public void setOLD_BILL_NO(String OLD_BILL_NO) {
        this.OLD_BILL_NO = OLD_BILL_NO;
    }

    public String getIS_CANCEL() {
        return IS_CANCEL;
    }

    public void setIS_CANCEL(String IS_CANCEL) {
        this.IS_CANCEL = IS_CANCEL;
    }

    public String getDATA_TYPE() {
        return DATA_TYPE;
    }

    public void setDATA_TYPE(String DATA_TYPE) {
        this.DATA_TYPE = DATA_TYPE;
    }

    public String getDATA_MONEY() {
        return DATA_MONEY;
    }

    public void setDATA_MONEY(String DATA_MONEY) {
        this.DATA_MONEY = DATA_MONEY;
    }

    public String getDATA_ACTION() {
        return DATA_ACTION;
    }

    public void setDATA_ACTION(String DATA_ACTION) {
        this.DATA_ACTION = DATA_ACTION;
    }

    public String getDATA_NUM() {
        return DATA_NUM;
    }

    public void setDATA_NUM(String DATA_NUM) {
        this.DATA_NUM = DATA_NUM;
    }

    public String getDATA_DIV() {
        return DATA_DIV;
    }

    public void setDATA_DIV(String DATA_DIV) {
        this.DATA_DIV = DATA_DIV;
    }

    public String getDATA_DAY() {
        return DATA_DAY;
    }

    public void setDATA_DAY(String DATA_DAY) {
        this.DATA_DAY = DATA_DAY;
    }

    public String getDATA_CONFIRM_NUM() {
        return DATA_CONFIRM_NUM;
    }

    public void setDATA_CONFIRM_NUM(String DATA_CONFIRM_NUM) {
        this.DATA_CONFIRM_NUM = DATA_CONFIRM_NUM;
    }

    public String getDATA_RECEIPT_NUM() {
        return DATA_RECEIPT_NUM;
    }

    public void setDATA_RECEIPT_NUM(String DATA_RECEIPT_NUM) {
        this.DATA_RECEIPT_NUM = DATA_RECEIPT_NUM;
    }

    public String getDATA_VAN_INFO() {
        return DATA_VAN_INFO;
    }

    public void setDATA_VAN_INFO(String DATA_VAN_INFO) {
        this.DATA_VAN_INFO = DATA_VAN_INFO;
    }

    public String getDATA_RECEIPT_TYPE() {
        return DATA_RECEIPT_TYPE;
    }

    public void setDATA_RECEIPT_TYPE(String DATA_RECEIPT_TYPE) {
        this.DATA_RECEIPT_TYPE = DATA_RECEIPT_TYPE;
    }

    public String getDATA_RECEIPT_INPUT() {
        return DATA_RECEIPT_INPUT;
    }

    public void setDATA_RECEIPT_INPUT(String DATA_RECEIPT_INPUT) {
        this.DATA_RECEIPT_INPUT = DATA_RECEIPT_INPUT;
    }

    public String getDATA_VAN() {
        return DATA_VAN;
    }

    public void setDATA_VAN(String DATA_VAN) {
        this.DATA_VAN = DATA_VAN;
    }

    public String getDATA_COM() {
        return DATA_COM;
    }

    public void setDATA_COM(String DATA_COM) {
        this.DATA_COM = DATA_COM;
    }

    public String getDATA_ACQUIRE_COM() {
        return DATA_ACQUIRE_COM;
    }

    public void setDATA_ACQUIRE_COM(String DATA_ACQUIRE_COM) {
        this.DATA_ACQUIRE_COM = DATA_ACQUIRE_COM;
    }

    public String getDATA_COM_CODE() {
        return DATA_COM_CODE;
    }

    public void setDATA_COM_CODE(String DATA_COM_CODE) {
        this.DATA_COM_CODE = DATA_COM_CODE;
    }

    public String getDATA_ACQUIRE_COM_CODE() {
        return DATA_ACQUIRE_COM_CODE;
    }

    public void setDATA_ACQUIRE_COM_CODE(String DATA_ACQUIRE_COM_CODE) {
        this.DATA_ACQUIRE_COM_CODE = DATA_ACQUIRE_COM_CODE;
    }
}
