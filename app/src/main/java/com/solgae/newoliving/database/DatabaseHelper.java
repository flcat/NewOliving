package com.solgae.newoliving.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "ApprovalData";
    private static final String TABLE_APPROVAL_COMMON = "APP_COMMON";
    private static final String BILL_NO = "bill_no";
    private static final String MAC_CODE = "mac_code";
    private static final String POS_NUM = "pos_num";
    private static final String USERID = "userid";
    private static final String CUSTOM_ID = "custom_id";
    private static final String TOTAL_MONEY = "total_money";
    private static final String REAL_MONEY = "real_money";
    private static final String CASH_MONEY = "cash_money";
    private static final String CARD_MONEY = "card_money";
    private static final String POINT_MONEY = "point_money";
    private static final String DIS_MONEY = "dis_money";
    private static final String COUPON_MONEY = "coupon_money";
    private static final String GIFT_MONEY = "gift_money";
    private static final String GIFTCARD_MONEY = "giftcard_money";
    private static final String REFUND_MONEY = "refund_money";
    private static final String SALE_POINT_MONEY = "sale_point_money";
    private static final String SALE_CUT_MONEY = "sale_cut_money";
    private static final String SUPPLY_MONEY = "supply_money";
    private static final String CHARGE_MONEY = "charge_money";
    private static final String TAX_MONEY = "tax_money";
    private static final String DIS_TYPE = "dis_type";
    private static final String SALE_STATUS = "sale_status";
    private static final String WRITE_DAY = "write_day";
    private static final String CANCEL_REASON = "cancel_reason";
    private static final String OLD_BILL_NO = "old_bill_no";
    private static final String IS_CANCEL = "is_cancel";
    private static final String DATA_TYPE = "data_type";
    private static final String DATA_MONEY = "data_money";
    private static final String DATA_ACTION = "data_action";
    private static final String DATA_NUM = "data_num";
    private static final String DATA_DIV = "data_div";
    private static final String DATA_DAY = "data_day";
    private static final String DATA_CONFIRM_NUM = "data_confirm_num";
    private static final String DATA_RECEIPT_NUM = "data_receipt_num";
    private static final String DATA_VAN_INFO = "data_van_info";
    private static final String DATA_RECEIPT_TYPE = "data_receipt_type";
    private static final String DATA_RECEIPT_INPUT = "data_receipt_input";
    private static final String DATA_VAN = "data_van";
    private static final String DATA_COM = "data_com";
    private static final String DATA_ACQUIRE_COM = "data_acquire_com";
    private static final String DATA_COM_CODE = "data_com_code";
    private static final String DATA_ACQUIRE_COM_CODE = "data_acquire_com_code";

    private static final String TABLE_APPROVAL_DETAIL = "APP_DETAIL";
    private static final String SEQINX = "seqinx";
    private static final String MAIN_CODE = "main_code";
    private static final String SUB_CODE = "sub_code";
    private static final String STEP_CODE = "step_code";
    private static final String MENU_STYLE = "menu_style";
    private static final String PRO_TITLE = "pro_title";
    private static final String PRO_CODE = "pro_code";
    private static final String UNIT_MONEY = "unit_money";
    private static final String PRO_CNT = "pro_cnt";
    private static final String DETALI_DIS_MONEY = "dis_money";
    private static final String RESULT_MONEY = "result_money";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_APPROVAL_COMMON =
                "CREATE TABLE " + TABLE_APPROVAL_COMMON + "(" +
                    BILL_NO + " TEXT NOT NULL," +
                    MAC_CODE + " TEXT NOT NULL, " +
                    POS_NUM + " TEXT, " +
                    USERID + " TEXT, " +
                    CUSTOM_ID  + " TEXT, " +
                    TOTAL_MONEY + " TEXT, " +
                    REAL_MONEY + " TEXT, " +
                    CASH_MONEY + " TEXT, " +
                    CARD_MONEY + " TEXT, " +
                    POINT_MONEY + " TEXT, " +
                    DIS_MONEY + " TEXT, " +
                    COUPON_MONEY  + " TEXT, " +
                    GIFT_MONEY + " TEXT, " +
                    GIFTCARD_MONEY + " TEXT, " +
                    REFUND_MONEY + " TEXT, " +
                    SALE_POINT_MONEY  + " TEXT, " +
                    SALE_CUT_MONEY + " TEXT, " +
                    SUPPLY_MONEY + " TEXT, " +
                    CHARGE_MONEY + " TEXT, " +
                    TAX_MONEY + " TEXT, " +
                    DIS_TYPE  + " TEXT, " +
                    SALE_STATUS  + " TEXT, " +
                    WRITE_DAY + " TEXT, " +
                    CANCEL_REASON + " TEXT, " +
                    OLD_BILL_NO + " TEXT, " +
                    IS_CANCEL + " TEXT, " +
                    DATA_TYPE + " TEXT, " +
                    DATA_MONEY  + " TEXT, " +
                    DATA_ACTION + " TEXT, " +
                    DATA_NUM + " TEXT, " +
                    DATA_DIV + " TEXT, " +
                    DATA_DAY + " TEXT, " +
                    DATA_CONFIRM_NUM + " TEXT, " +
                    DATA_RECEIPT_NUM  + " TEXT, " +
                    DATA_VAN_INFO  + " TEXT, " +
                    DATA_RECEIPT_TYPE + " TEXT, " +
                    DATA_RECEIPT_INPUT  + " TEXT, " +
                    DATA_VAN  + " TEXT, " +
                    DATA_COM  + " TEXT, " +
                    DATA_ACQUIRE_COM + " TEXT, " +
                    DATA_COM_CODE + " TEXT, " +
                    DATA_ACQUIRE_COM_CODE  + " TEXT);";
        db.execSQL(CREATE_TABLE_APPROVAL_COMMON);

        String CREATE_TABLE_APPROVAL_DETAIL =
                "CREATE TABLE " + TABLE_APPROVAL_DETAIL + "(" +
                        BILL_NO + " TEXT NOT NULL," +
                        MAIN_CODE + " TEXT NOT NULL," +
                        SUB_CODE + " TEXT," +
                        STEP_CODE + " TEXT," +
                        MENU_STYLE + " TEXT NOT NULL," +
                        PRO_TITLE + " TEXT NOT NULL," +
                        PRO_CODE + " TEXT NOT NULL," +
                        UNIT_MONEY + " TEXT NOT NULL," +
                        PRO_CNT + " TEXT NOT NULL," +
                        DETALI_DIS_MONEY + " TEXT NOT NULL," +
                        RESULT_MONEY + " TEXT NOT NULL," +
                        "PRIMARY KEY (" + BILL_NO + ", " +  PRO_CODE + "));" ;
        db.execSQL(CREATE_TABLE_APPROVAL_DETAIL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE_APPROVAL_COMMON =
                "DROP TABLE IF EXISTS " + TABLE_APPROVAL_COMMON;
        db.execSQL(DROP_TABLE_APPROVAL_COMMON);

        String DROP_TABLE_APPROVAL_DETAIL =
                "DROP TABLE IF EXISTS " + TABLE_APPROVAL_DETAIL;
        db.execSQL(DROP_TABLE_APPROVAL_DETAIL);

        onCreate(db);
    }

    public void add_common(ApprovalCommon approvalCommon) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BILL_NO, approvalCommon.getBILL_NO());
        values.put(MAC_CODE, approvalCommon.getMAC_CODE());
        values.put(POS_NUM, approvalCommon.getPOS_NUM());
        values.put(USERID, approvalCommon.getUSERID());
        values.put(CUSTOM_ID, approvalCommon.getCUSTOM_ID());
        values.put(TOTAL_MONEY, approvalCommon.getTOTAL_MONEY());
        values.put(REAL_MONEY, approvalCommon.getREAL_MONEY());
        values.put(CASH_MONEY, approvalCommon.getCASH_MONEY());
        values.put(CARD_MONEY, approvalCommon.getCARD_MONEY());
        values.put(POINT_MONEY, approvalCommon.getPOINT_MONEY());

        values.put(DIS_MONEY, approvalCommon.getDIS_MONEY());
        values.put(COUPON_MONEY, approvalCommon.getCOUPON_MONEY());
        values.put(GIFT_MONEY, approvalCommon.getGIFT_MONEY());
        values.put(GIFTCARD_MONEY, approvalCommon.getGIFTCARD_MONEY());
        values.put(REFUND_MONEY, approvalCommon.getREFUND_MONEY());
        values.put(SALE_POINT_MONEY, approvalCommon.getSALE_POINT_MONEY());
        values.put(SALE_CUT_MONEY, approvalCommon.getSALE_CUT_MONEY());
        values.put(SUPPLY_MONEY, approvalCommon.getSUPPLY_MONEY());
        values.put(CHARGE_MONEY, approvalCommon.getCHARGE_MONEY());
        values.put(TAX_MONEY, approvalCommon.getTAX_MONEY());
        values.put(DIS_TYPE, approvalCommon.getDIS_TYPE());
        values.put(SALE_STATUS, approvalCommon.getSALE_STATUS());
        values.put(WRITE_DAY, approvalCommon.getWRITE_DAY());
        values.put(CANCEL_REASON, approvalCommon.getCANCEL_REASON());
        values.put(OLD_BILL_NO, approvalCommon.getOLD_BILL_NO());
        values.put(IS_CANCEL, approvalCommon.getIS_CANCEL());
        values.put(DATA_TYPE, approvalCommon.getDATA_TYPE());
        values.put(DATA_MONEY, approvalCommon.getDATA_MONEY());
        values.put(DATA_ACTION, approvalCommon.getDATA_ACTION());
        values.put(DATA_NUM, approvalCommon.getDATA_NUM());
        values.put(DATA_DIV, approvalCommon.getDATA_DIV());
        values.put(DATA_DAY, approvalCommon.getDATA_DAY());
        values.put(DATA_CONFIRM_NUM, approvalCommon.getDATA_CONFIRM_NUM());
        values.put(DATA_RECEIPT_NUM, approvalCommon.getDATA_RECEIPT_NUM());
        values.put(DATA_VAN_INFO, approvalCommon.getDATA_VAN_INFO());
        values.put(DATA_RECEIPT_TYPE, approvalCommon.getDATA_RECEIPT_TYPE());
        values.put(DATA_RECEIPT_INPUT, approvalCommon.getDATA_RECEIPT_INPUT());
        values.put(DATA_VAN, approvalCommon.getDATA_VAN());
        values.put(DATA_COM, approvalCommon.getDATA_COM());
        values.put(DATA_ACQUIRE_COM, approvalCommon.getDATA_ACQUIRE_COM());
        values.put(DATA_COM_CODE, approvalCommon.getDATA_COM_CODE());
        values.put(DATA_ACQUIRE_COM_CODE, approvalCommon.getDATA_ACQUIRE_COM_CODE());
        db.insert(TABLE_APPROVAL_COMMON, null, values);
        db.close();
    }

    public void add_detail(ApprovalDetail approvalDetail) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BILL_NO, approvalDetail.getBill_no());
        values.put(MAIN_CODE, approvalDetail.getMain_code());
        values.put(SUB_CODE, approvalDetail.getSub_code());
        values.put(STEP_CODE, approvalDetail.getStep_code());
        values.put(MENU_STYLE, approvalDetail.getMenu_style());
        values.put(PRO_TITLE, approvalDetail.getPro_title());
        values.put(PRO_CODE, approvalDetail.getPro_code());
        values.put(UNIT_MONEY, approvalDetail.getUnit_money());
        values.put(PRO_CNT, approvalDetail.getPro_cnt());
        values.put(DIS_MONEY, approvalDetail.getDis_money());
        values.put(RESULT_MONEY, approvalDetail.getResult_money());

        db.insert(TABLE_APPROVAL_DETAIL, null, values);
        db.close();
    }


    public List<ApprovalCommon> getAllCommon() {
        List<ApprovalCommon> drinkList = new ArrayList<ApprovalCommon>();

        String SELECT_ALL = "SELECT * FROM " + TABLE_APPROVAL_COMMON;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(SELECT_ALL, null);

        if (cursor.moveToFirst()) {
            do {
                ApprovalCommon approvalCommon = new ApprovalCommon();
                approvalCommon.setBILL_NO(cursor.getString(0));
                approvalCommon.setMAC_CODE(cursor.getString(1));
                approvalCommon.setPOS_NUM(cursor.getString(2));
                approvalCommon.setUSERID(cursor.getString(3));
                approvalCommon.setCUSTOM_ID(cursor.getString(4));
                approvalCommon.setTOTAL_MONEY(cursor.getString(5));
                approvalCommon.setREAL_MONEY(cursor.getString(6));
                approvalCommon.setCASH_MONEY(cursor.getString(7));
                approvalCommon.setCARD_MONEY(cursor.getString(8));
                approvalCommon.setPOINT_MONEY(cursor.getString(9));
                approvalCommon.setDIS_MONEY(cursor.getString(10));
                approvalCommon.setCOUPON_MONEY(cursor.getString(11));
                approvalCommon.setGIFT_MONEY(cursor.getString(12));
                approvalCommon.setGIFTCARD_MONEY(cursor.getString(13));
                approvalCommon.setREFUND_MONEY(cursor.getString(14));
                approvalCommon.setSALE_POINT_MONEY(cursor.getString(15));
                approvalCommon.setSALE_CUT_MONEY(cursor.getString(16));
                approvalCommon.setSUPPLY_MONEY(cursor.getString(17));
                approvalCommon.setCHARGE_MONEY(cursor.getString(18));
                approvalCommon.setTAX_MONEY(cursor.getString(19));
                approvalCommon.setDIS_TYPE(cursor.getString(20));
                approvalCommon.setSALE_STATUS(cursor.getString(21));
                approvalCommon.setWRITE_DAY(cursor.getString(22));
                approvalCommon.setCANCEL_REASON(cursor.getString(23));
                approvalCommon.setOLD_BILL_NO(cursor.getString(24));
                approvalCommon.setIS_CANCEL(cursor.getString(25));
                approvalCommon.setDATA_TYPE(cursor.getString(26));
                approvalCommon.setDATA_MONEY(cursor.getString(27));
                approvalCommon.setDATA_ACTION(cursor.getString(28));
                approvalCommon.setDATA_NUM(cursor.getString(29));
                approvalCommon.setDATA_DIV(cursor.getString(30));
                approvalCommon.setDATA_DAY(cursor.getString(31));
                approvalCommon.setDATA_CONFIRM_NUM(cursor.getString(32));
                approvalCommon.setDATA_RECEIPT_NUM(cursor.getString(33));
                approvalCommon.setDATA_VAN_INFO(cursor.getString(34));
                approvalCommon.setDATA_RECEIPT_TYPE(cursor.getString(35));
                approvalCommon.setDATA_RECEIPT_INPUT(cursor.getString(36));
                approvalCommon.setDATA_VAN(cursor.getString(37));
                approvalCommon.setDATA_COM(cursor.getString(38));
                approvalCommon.setDATA_ACQUIRE_COM(cursor.getString(39));
                approvalCommon.setDATA_COM_CODE(cursor.getString(40));
                approvalCommon.setDATA_ACQUIRE_COM_CODE(cursor.getString(41));
                drinkList.add(approvalCommon);
            } while (cursor.moveToNext());
        }

        return drinkList;
    }

    public List<ApprovalDetail> getAllDetail() {
        List<ApprovalDetail> drinkList = new ArrayList<ApprovalDetail>();

        String SELECT_ALL = "SELECT * FROM " + TABLE_APPROVAL_DETAIL;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(SELECT_ALL, null);

        if (cursor.moveToFirst()) {
            do {
                ApprovalDetail approvalDetail = new ApprovalDetail();
                approvalDetail.setBill_no(cursor.getString(0));
                approvalDetail.setMain_code(cursor.getString(1));
                approvalDetail.setSub_code(cursor.getString(2));
                approvalDetail.setStep_code(cursor.getString(3));
                approvalDetail.setMenu_style(cursor.getString(4));
                approvalDetail.setPro_title(cursor.getString(5));
                approvalDetail.setPro_code(cursor.getString(6));
                approvalDetail.setUnit_money(cursor.getString(7));
                approvalDetail.setPro_cnt(cursor.getString(8));
                approvalDetail.setDis_money(cursor.getString(9));
                approvalDetail.setResult_money(cursor.getString(10));

                drinkList.add(approvalDetail);
            } while (cursor.moveToNext());
        }

        return drinkList;
    }

    public List<ApprovalCommon> getSelect_Common(String data_confirm_num) {

        List<ApprovalCommon> drinkList = new ArrayList<ApprovalCommon>();
        String SELECTQRY = "SELECT * FROM " + TABLE_APPROVAL_COMMON + " Where DATA_CONFIRM_NUM = '" + data_confirm_num + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(SELECTQRY, null);

        if (cursor.moveToFirst()) {
            do {
                ApprovalCommon approvalCommon = new ApprovalCommon();
                approvalCommon.setBILL_NO(cursor.getString(0));
                approvalCommon.setMAC_CODE(cursor.getString(1));
                approvalCommon.setPOS_NUM(cursor.getString(2));
                approvalCommon.setUSERID(cursor.getString(3));
                approvalCommon.setCUSTOM_ID(cursor.getString(4));
                approvalCommon.setTOTAL_MONEY(cursor.getString(5));
                approvalCommon.setREAL_MONEY(cursor.getString(6));
                approvalCommon.setCASH_MONEY(cursor.getString(7));
                approvalCommon.setCARD_MONEY(cursor.getString(8));
                approvalCommon.setPOINT_MONEY(cursor.getString(9));
                approvalCommon.setDIS_MONEY(cursor.getString(10));
                approvalCommon.setCOUPON_MONEY(cursor.getString(11));
                approvalCommon.setGIFT_MONEY(cursor.getString(12));
                approvalCommon.setGIFTCARD_MONEY(cursor.getString(13));
                approvalCommon.setREFUND_MONEY(cursor.getString(14));
                approvalCommon.setSALE_POINT_MONEY(cursor.getString(15));
                approvalCommon.setSALE_CUT_MONEY(cursor.getString(16));
                approvalCommon.setSUPPLY_MONEY(cursor.getString(17));
                approvalCommon.setCHARGE_MONEY(cursor.getString(18));
                approvalCommon.setTAX_MONEY(cursor.getString(19));
                approvalCommon.setDIS_TYPE(cursor.getString(20));
                approvalCommon.setSALE_STATUS(cursor.getString(21));
                approvalCommon.setWRITE_DAY(cursor.getString(22));
                approvalCommon.setCANCEL_REASON(cursor.getString(23));
                approvalCommon.setOLD_BILL_NO(cursor.getString(24));
                approvalCommon.setIS_CANCEL(cursor.getString(25));
                approvalCommon.setDATA_TYPE(cursor.getString(26));
                approvalCommon.setDATA_MONEY(cursor.getString(27));
                approvalCommon.setDATA_ACTION(cursor.getString(28));
                approvalCommon.setDATA_NUM(cursor.getString(29));
                approvalCommon.setDATA_DIV(cursor.getString(30));
                approvalCommon.setDATA_DAY(cursor.getString(31));
                approvalCommon.setDATA_CONFIRM_NUM(cursor.getString(32));
                approvalCommon.setDATA_RECEIPT_NUM(cursor.getString(33));
                approvalCommon.setDATA_VAN_INFO(cursor.getString(34));
                approvalCommon.setDATA_RECEIPT_TYPE(cursor.getString(35));
                approvalCommon.setDATA_RECEIPT_INPUT(cursor.getString(36));
                approvalCommon.setDATA_VAN(cursor.getString(37));
                approvalCommon.setDATA_COM(cursor.getString(38));
                approvalCommon.setDATA_ACQUIRE_COM(cursor.getString(39));
                approvalCommon.setDATA_COM_CODE(cursor.getString(40));
                approvalCommon.setDATA_ACQUIRE_COM_CODE(cursor.getString(41));
                drinkList.add(approvalCommon);
            } while (cursor.moveToNext());
        }
        return drinkList;
    }

    public List<ApprovalDetail> getSelect_Detail(String bill_no) {

        List<ApprovalDetail> drinkList = new ArrayList<ApprovalDetail>();
        String SELECTQRY = "SELECT * FROM " + TABLE_APPROVAL_DETAIL + " Where bill_no = '" + bill_no + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(SELECTQRY, null);

        if (cursor.moveToFirst()) {
            do {
                ApprovalDetail approvalDetail = new ApprovalDetail();
                approvalDetail.setBill_no(cursor.getString(0));
                approvalDetail.setMain_code(cursor.getString(1));
                approvalDetail.setSub_code(cursor.getString(2));
                approvalDetail.setStep_code(cursor.getString(3));
                approvalDetail.setMenu_style(cursor.getString(4));
                approvalDetail.setPro_title(cursor.getString(5));
                approvalDetail.setPro_code(cursor.getString(6));
                approvalDetail.setUnit_money(cursor.getString(7));
                approvalDetail.setPro_cnt(cursor.getString(8));
                approvalDetail.setDis_money(cursor.getString(9));
                approvalDetail.setResult_money(cursor.getString(10));
                drinkList.add(approvalDetail);
            } while (cursor.moveToNext());
        }
        return drinkList;
    }

    public void getAlldelete_Common() {
        String DELETE_ALL = "DELETE FROM " + TABLE_APPROVAL_COMMON;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(DELETE_ALL, null);
    }

    public void getAlldelete_Detail() {
        String DELETE_ALL = "DELETE FROM " + TABLE_APPROVAL_DETAIL;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(DELETE_ALL, null);
    }
}
