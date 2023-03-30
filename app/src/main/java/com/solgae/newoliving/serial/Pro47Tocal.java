package com.solgae.newoliving.serial;

public class Pro47Tocal {

    public static int[] DOOR_MICRO_STATE;

    /**
     * 47컬럼 통신 명령어F
     */
    public static byte[] CMD47_DOOR_OPEN = {0x10, 0x02, 0x44, 0x00, 0x00, 0x00, 0x00, 0x00, 0x10, 0x03};
    public static byte[] CMD47_DOOR_RECE = {0x10, 0x02, 0x64, 0x00, 0x00, 0x10, 0x03};
    //  마이크로 상태값 -> 문열림 상태 요청
    public static byte[] CMD47_MICRO_REQ = {0x10, 0x02, 0x53, 0x00, 0x00, 0x10, 0x03};
    public static byte[] CMD47_MICRO_REC = {0x10, 0x02, 0x73,
            0x31, 0x31, 0x31, 0x31, 0x31,0x31, 0x31, 0x31, 0x31, 0x31,
            0x31, 0x31, 0x31, 0x31, 0x31,0x31, 0x31, 0x31, 0x31, 0x31,
            0x31, 0x31, 0x31, 0x31, 0x31,0x31, 0x31, 0x31, 0x31, 0x31,
            0x31, 0x31, 0x31, 0x31, 0x31,0x31, 0x31, 0x31, 0x31, 0x31,
            0x31, 0x31, 0x31, 0x31, 0x31,0x31, 0x31,
            0x00, 0x10, 0x03};
    public static byte[] CMD47_MICRO_ECHO = {(byte)0x10, (byte)0x02, (byte)0x69, (byte)0x00, (byte)0x85, (byte)0x10, (byte)0x03};

// micro echo
    public static byte[] MICRO_STATE_ECHO = {(byte)0x10, (byte)0x02, (byte)0x69, (byte)0x00, (byte)0x85, (byte)0x10, (byte)0x03};

    public boolean DOOR_OPEN_REV(byte[] rev_data) {
        boolean rtn_value = false;
        if (rev_data[0] == (byte)0x10
                && rev_data[1] == (byte)0x02
                && rev_data[2] == (byte)0x64) {
            rtn_value = true;
        }
        return rtn_value;
    }


// Door_OPEN
    public static byte[] DOOR_OPEN(char[] rev_data) {
        CMD47_DOOR_OPEN[3] = (byte) rev_data[0];
        CMD47_DOOR_OPEN[4] = (byte) rev_data[1];
        CMD47_DOOR_OPEN[5] = (byte) 0x31;
        CMD47_DOOR_OPEN[6] = (byte) (CMD47_DOOR_OPEN[3] ^ CMD47_DOOR_OPEN[4] ^ CMD47_DOOR_OPEN[5]);
        CMD47_DOOR_OPEN[7] = (byte) 0x85;
        return CMD47_DOOR_OPEN;
    }

    public boolean MICRO_STATE_RECEIVE(byte[] rev_data) {
        boolean rtn_value = false;
        if (rev_data[0] == (byte)0x10
                && rev_data[1] == (byte)0x02
                && rev_data[2] == (byte)0x73
                && rev_data[52] == (byte)0x10
                && rev_data[53] == (byte)0x03) {
            rtn_value = true;
        }
        return rtn_value;
    }

    public boolean AUTO_MICRO_STATE_RECEIVE(byte[] rev_data) {
        boolean rtn_value = false;
        if (rev_data[0] == (byte)0x10
                && rev_data[1] == (byte)0x02
                && rev_data[2] == (byte)0x49
                && rev_data[52] == (byte)0x10
                && rev_data[53] == (byte)0x03) {
            rtn_value = true;
        }
        return rtn_value;
    }

    // Micro
    public static byte[] MICRO_INFOMATION() {
        CMD47_MICRO_REQ[3] = (byte) 0x00;
        CMD47_MICRO_REQ[4] = (byte) 0x85;
        return CMD47_MICRO_REQ;
    }

// 응답메시지 분석
    public boolean DOOR_OPEN_RECEIVE(byte[] rev_data) {
        boolean rtn_value = false;
        if ((rev_data[0] == (byte)0x10 && rev_data[1] == (byte)0x02 && rev_data[2] == (byte)0x64
                && rev_data[5] == (byte)0x10 && rev_data[6] == (byte)0x03) || rev_data[2] == (byte)0x64) {
            rtn_value = true;
        }
        return rtn_value;
    }

}
