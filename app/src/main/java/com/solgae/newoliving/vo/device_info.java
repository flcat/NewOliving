package com.solgae.newoliving.vo;

public class device_info {

    String serial_no;
    String token;
    String shop_name;
    String groupcode;
    String device_type;       // xp(빌링머신), st(스타일러), sh(신발살균기), od(주문)
    String columm;             // 47, 51

    public device_info() {}

    public device_info(String serial_no, String token, String shop_name, String groupcode, String device_type, String columm) {
        this.serial_no = serial_no;
        this.token = token;
        this.shop_name = shop_name;
        this.groupcode = groupcode;
        this.device_type = device_type;
        this.columm = columm;
    }

    public String getSerial_no() {
        return serial_no;
    }

    public void setSerial_no(String serial_no) {
        this.serial_no = serial_no;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getGroupcode() {
        return groupcode;
    }

    public void setGroupcode(String groupcode) {
        this.groupcode = groupcode;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getColumm() {
        return columm;
    }

    public void setColumm(String columm) {
        this.columm = columm;
    }
}
