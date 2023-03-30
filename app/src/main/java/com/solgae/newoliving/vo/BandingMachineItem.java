package com.solgae.newoliving.vo;

import java.util.ArrayList;

public class BandingMachineItem {

    private String group_code;
    private String mac_address;
    private String dev_tocken;
    private String dev_tid;
    private String common_saupjano;  // 대표사업자번호
    private String dev_type;          // 장비구분(밴딩머신47/51, 주문키오스크)
    private String install_shopcode; // 설치매장코드
    private String install_shopname; // 설치매장명
    private String manager_name;      // 대표자
    private String shopPhone;         // 전화번호
    private String install_date;     // 설치일자
    private ArrayList<ColumSettingItem> product_info; // 장비의 컬럼과 장비가 제어하는 아이템 정보(xp, sh, st)
    private ArrayList<DeliveryItem> food_info;

    public BandingMachineItem() {}

    public BandingMachineItem(String group_code, String mac_address, String dev_tocken, String dev_tid,
                             String common_saupjano, String dev_type, String install_shopcode, String install_shopname,
                             String manager_name, String shopPhone, String install_date, ArrayList<ColumSettingItem> product_info, ArrayList<DeliveryItem> food_info) {
        this.group_code = group_code;
        this.mac_address = mac_address;
        this.dev_tocken = dev_tocken;
        this.dev_tid = dev_tid;
        this.common_saupjano = common_saupjano;  // 대표사업자번호
        this.dev_type = dev_type;          // 장비구분(밴딩머신47/51, 주문키오스크)
        this.install_shopcode = install_shopcode; // 설치매장코드
        this.install_shopname = install_shopname; // 설치매장명
        this.manager_name = manager_name;      // 대표자
        this.shopPhone = shopPhone;         // 전화번호
        this.install_date = install_date;     // 설치일자
        this.product_info = product_info;
        this.food_info = food_info;
    }

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }

    public String getMac_address() {
        return mac_address;
    }

    public void setMac_address(String mac_address) {
        this.mac_address = mac_address;
    }

    public String getDev_tocken() {
        return dev_tocken;
    }

    public void setDev_tocken(String dev_tocken) {
        this.dev_tocken = dev_tocken;
    }

    public String getDev_tid() {
        return dev_tid;
    }

    public void setDev_tid(String dev_tid) {
        this.dev_tid = dev_tid;
    }

    public String getCommon_saupjano() {
        return common_saupjano;
    }

    public void setCommon_saupjano(String common_saupjano) {
        this.common_saupjano = common_saupjano;
    }

    public String getDev_type() {
        return dev_type;
    }

    public void setDev_type(String dev_type) {
        this.dev_type = dev_type;
    }

    public String getInstall_shopcode() {
        return install_shopcode;
    }

    public void setInstall_shopcode(String install_shopcode) {
        this.install_shopcode = install_shopcode;
    }

    public String getInstall_shopname() {
        return install_shopname;
    }

    public void setInstall_shopname(String install_shopname) {
        this.install_shopname = install_shopname;
    }

    public String getManager_name() {
        return manager_name;
    }

    public void setManager_name(String manager_name) {
        this.manager_name = manager_name;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }

    public String getInstall_date() {
        return install_date;
    }

    public void setInstall_date(String install_date) {
        this.install_date = install_date;
    }

    public ArrayList<ColumSettingItem> getProduct_info() {
        return product_info;
    }

    public void setProduct_info(ArrayList<ColumSettingItem> product_info) {
        this.product_info = product_info;
    }

    public ArrayList<DeliveryItem> getFood_info() {
        return food_info;
    }

    public void setFood_info(ArrayList<DeliveryItem> food_info) {
        this.food_info = food_info;
    }
}
