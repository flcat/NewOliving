package com.solgae.newoliving.vo;

import java.util.List;

public class MachineMaster {

    String status;
    String message;
    String request;
    String xperon_cnt;
    String styler_cnt;
    String shoes_cnt;
    String update_date;
    String manager_phone;
    String mac_address;
    String dev_tocken;
    String dev_tid;
    String common_saupjano;
    String dev_type;
    String install_shopcode;
    String install_shopname;
    String manager_name;
    String shopPhone;
    String install_date;
    String group_code;
    List<MachineColummInfo> machineColummInfo;
    List<food_info> food_infos;
    List<store_info> store_infos;


    public MachineMaster() {}

    public MachineMaster(String status, String message, String request, String xperon_cnt, String styler_cnt,
                          String shoes_cnt, String update_date, String manager_phone, String mac_address,
                          String dev_tocken, String dev_tid, String common_saupjano, String dev_type, String install_shopcode,
                          String install_shopname, String manager_name, String shopPhone, String install_date, String group_code,
                          List<MachineColummInfo> machineColummInfo, List<food_info> food_infos, List<store_info> store_infos, String item_saupja_no) {
        this.status = status;
        this.message = message;
        this.request = request;
        this.xperon_cnt = xperon_cnt;
        this.styler_cnt = styler_cnt;
        this.shoes_cnt = shoes_cnt;
        this.update_date = update_date;
        this.manager_phone = manager_phone;
        this.mac_address = mac_address;
        this.dev_tocken = dev_tocken;
        this.dev_tid = dev_tid;
        this.common_saupjano = common_saupjano;
        this.dev_type = dev_type;
        this.install_shopcode = install_shopcode;
        this.install_shopname = install_shopname;
        this.manager_name = manager_name;
        this.shopPhone = shopPhone;
        this.install_date = install_date;
        this.group_code = group_code;
        this.machineColummInfo = machineColummInfo;
        this.food_infos = food_infos;
        this.store_infos = store_infos;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getXperon_cnt() {
        return xperon_cnt;
    }

    public void setXperon_cnt(String xperon_cnt) {
        this.xperon_cnt = xperon_cnt;
    }

    public String getStyler_cnt() {
        return styler_cnt;
    }

    public void setStyler_cnt(String styler_cnt) {
        this.styler_cnt = styler_cnt;
    }

    public String getShoes_cnt() {
        return shoes_cnt;
    }

    public void setShoes_cnt(String shoes_cnt) {
        this.shoes_cnt = shoes_cnt;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public String getManager_phone() {
        return manager_phone;
    }

    public void setManager_phone(String manager_phone) {
        this.manager_phone = manager_phone;
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

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }

    public List<MachineColummInfo> getMachineColummInfo() {
        return machineColummInfo;
    }

    public void setMachineColummInfo(List<MachineColummInfo> machineColummInfo) {
        this.machineColummInfo = machineColummInfo;
    }

    public List<food_info> getFood_infos() {
        return food_infos;
    }

    public void setFood_infos(List<food_info> food_infos) {
        this.food_infos = food_infos;
    }

    public List<store_info> getStore_infos() {
        return store_infos;
    }

    public void setStore_infos(List<store_info> store_infos) {
        this.store_infos = store_infos;
    }
}
