package com.solgae.newoliving.localdb;

import android.provider.BaseColumns;

public final class DataBases {
    public static final class CreateDB implements BaseColumns {

        public static final String MAC_ADDRESS = "mac_address";                   // 시리얼번호
        public static final String DEV_TYPE = "dev_type";                          // 장비타입(QB, QK)
        public static final String INSTALL_SHOPCODE = "install_shopcode";        // 매장코드
        public static final String INSTALL_SHOPNAME = "install_shopname";        // 매장명
        public static final String XPERON_CNT = "xperon_cnt";                      // 컬럼수
        public static final String STYLER_CNT = "styler_cnt";                      // 스타일러수
        public static final String SHOES_CNT = "shoes_cnt";                        // 살균기수
        public static final String UPDATE_DATE = "update_date";                    // 변경일자
        public static final String MANAGER_PHONE = "manager_phone";               // 장비모델
        public static final String DEV_TOCKEN = "dev_tocken";                      // 밴사코드
        public static final String DEV_TID = "dev_tid";                             // TID
        public static final String COMMON_SAUPJANO = "common_saupjano";          // 사업자번호
        public static final String INSTALL_DATE = "install_date";                 // 설치일자
        public static final String GROUP_CODE = "group_code";
        public static final String MANAGER_NAME = "manager_name";                 // 관리자명
        public static final String SHOPPHONE = "shopPhone";                        // 가게전번
        public static final String _CTABLENAME = "BandingCommon";
        public static final String _COMMON_CREATE =
                "create table "+_CTABLENAME+"("
                        +MAC_ADDRESS+" primary key, "
                        +DEV_TYPE+" text not null , "
                        +INSTALL_SHOPCODE+" text not null , "
                        +INSTALL_SHOPNAME+" text not null , "
                        +XPERON_CNT+" text not null , "
                        +STYLER_CNT+" text not null , "
                        +SHOES_CNT+" text not null , "
                        +UPDATE_DATE+" text not null , "
                        +MANAGER_PHONE+" text not null , "
                        +DEV_TOCKEN+" text not null , "
                        +DEV_TID+" text not null , "
                        +COMMON_SAUPJANO+" text not null , "
                        +INSTALL_DATE+" text not null , "
                        +GROUP_CODE+" text not null , "
                        +MANAGER_NAME+" text not null , "
                        +SHOPPHONE+" text not null );";

        public static final String D_MAC_ADDRESS = "mac_address"; // 시리얼번호
        public static final String ITEMGROUP = "itemgroup"; //
        public static final String D_MAC_COLUMN = "mac_column"; // 컬럼번호
        public static final String ITEM_CODE = "item_code";
        public static final String ITEM_NAME = "item_name";
        public static final String ITEM_COST = "item_cost";
        public static final String ITEM_IMAGE = "item_image";
        public static final String ITEM_DETAIL_IMAGE = "item_detail_image";
        public static final String ITEM_MOV = "item_mov"; //
        public static final String ITEM_DATE = "item_date";
        public static final String ITEM_SAUPJA_NO = "item_saupja_no";
        public static final String TOKEN = "token";
        public static final String EMPTY = "empty";
        public static final String ITEM_TID = "item_tid";
        public static final String _DTABLENAME = "BandingDetail";
        public static final String _DETAIL_CREATE =
                "create table "+_DTABLENAME+"("
                        +D_MAC_ADDRESS+" text not null, "
                        +ITEMGROUP+" text not null , "
                        +D_MAC_COLUMN+" text not null , "
                        +ITEM_CODE+" text not null , "
                        +ITEM_NAME+" text not null , "
                        +ITEM_COST+" text not null , "
                        +ITEM_IMAGE+" text not null , "
                        +ITEM_DETAIL_IMAGE+" text not null , "
                        +ITEM_MOV+" text not null , "
                        +ITEM_DATE+" text not null , "
                        +ITEM_SAUPJA_NO+" text not null , "
                        +TOKEN+" text not null , "
                        +EMPTY+" text not null , "
                        +ITEM_TID+" text not null , "
                        +"PRIMARY KEY ("+ D_MAC_ADDRESS + ", " + D_MAC_COLUMN  + ", " + ITEMGROUP + ", " + ITEM_CODE + "));";

        // Food
        public static final String F_MAIN_CODE = "main_code"; //
        public static final String F_SEQ = "disSeq"; // 화면에 출력되는 순번
        public static final String F_MAINTHUMBNAIL = "main_code_thumbnail";
        public static final String F_COM_CODE_STORE_NAME = "com_code_store_name";
        public static final String F_COM_CODE_STORE_ID = "com_code_store_id";
        public static final String F_PRO_CODE = "pro_code";
        public static final String F_PRO_TITLE = "pro_title";
        public static final String F_THUMBNAIL = "thumbnail";
        public static final String F_GPS_LAT = "gps_lat";
        public static final String F_GPS_LONG = "gps_long";
        public static final String F_DISTANCE = "distance";
        public static final String F_SALE_COST = "sale_cost";
        public static final String F_ITEM_SAUPJA_NO = "item_saupja_no"; //
        public static final String F_MAC_ADDRESS = "mac_address"; // 시리얼번호
        public static final String F_EMPTY = "empty";

        public static final String _FOODDTABLENAME = "BandingFoodDetail";
        public static final String _FOOD_DETAIL_CREATE =
                "create table "+_FOODDTABLENAME+"("
                        +F_MAIN_CODE+" text not null , "
                        +F_SEQ+" text not null , "
                        +F_MAINTHUMBNAIL+" text not null , "
                        +F_COM_CODE_STORE_NAME+" text not null , "
                        +F_COM_CODE_STORE_ID+" text not null , "
                        +F_PRO_TITLE+" text not null , "
                        +F_PRO_CODE+" text not null , "
                        +F_THUMBNAIL+" text not null , "
                        +F_GPS_LAT+" text not null , "
                        +F_GPS_LONG+" text not null , "
                        +F_DISTANCE+" text not null , "
                        +F_SALE_COST+" text not null , "
                        +F_ITEM_SAUPJA_NO+" text not null , "
                        +F_MAC_ADDRESS+" text not null, "
                        +F_EMPTY+" text not null , "
                        +"PRIMARY KEY ("+ F_MAC_ADDRESS + ", " + F_COM_CODE_STORE_ID  + ", " + F_PRO_CODE + "));";

        // Store
        public static final String S_COM_CODE_STORE_ID = "com_code_store_id";
        public static final String S_COM_CODE_STORE_NAME = "com_code_store_name";
        public static final String S_THUMNAIL = "store_thumbnail";

        public static final String S_COM_CODE_STORE_SAUPJA_NO = "saupja_no";
        public static final String S_COM_CODE_STORE_MANAGER_NAME = "manager_name";
        public static final String S_COM_CODE_STORE_TEL = "tel";
        public static final String S_COM_CODE_STORE_HPHONE = "hphone";
        public static final String S_COM_CODE_STORE_POST = "post";
        public static final String S_COM_CODE_STORE_ADDRESS = "address";
        public static final String S_COM_CODE_STORE_VAN_TID = "van_tid";
        public static final String S_COM_CODE_STORE_GPS_LAT = "gps_lat";
        public static final String S_COM_CODE_STORE_GPS_LONG = "gps_long";
        public static final String S_COM_CODE_STORE_DISTANCE = "distance";

        public static final String _STOREDTABLENAME = "BandingFoodStore";
        public static final String _STORE_TABLE_CREATE =
                "create table "+_STOREDTABLENAME+"("
                        +S_COM_CODE_STORE_ID+" text not null, "
                        +S_COM_CODE_STORE_NAME+" text not null, "
                        +S_THUMNAIL+" text not null, "
                        +S_COM_CODE_STORE_SAUPJA_NO+" text not null, "
                        +S_COM_CODE_STORE_MANAGER_NAME+" text not null, "
                        +S_COM_CODE_STORE_TEL+" text not null, "
                        +S_COM_CODE_STORE_HPHONE+" text not null, "
                        +S_COM_CODE_STORE_POST+" text not null, "
                        +S_COM_CODE_STORE_ADDRESS+" text not null, "
                        +S_COM_CODE_STORE_VAN_TID+" text not null, "
                        +S_COM_CODE_STORE_GPS_LAT+" text not null, "
                        +S_COM_CODE_STORE_GPS_LONG+" text not null, "
                        +S_COM_CODE_STORE_DISTANCE+" text not null, "
                        +"PRIMARY KEY (" + S_COM_CODE_STORE_ID + "));";
    }
}
