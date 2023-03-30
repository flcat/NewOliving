package com.solgae.newoliving.vo;

public class store_info {

    String com_code_store_id;
    String com_code_store_name;
    String store_thumbnail;

    String saupja_no;
    String manager_name;
    String tel;
    String hphone;
    String post;
    String address;
    String van_tid;
    String gps_lat;
    String gps_long;
    String distance;

//    public store_info(){}

    public store_info(String com_code_store_id, String com_code_store_name, String store_thumbnail,
                      String saupja_no, String manager_name, String tel, String hphone, String post,
                      String address, String van_tid, String gps_lat, String gps_long, String distance){
        this.com_code_store_id = com_code_store_id;
        this.com_code_store_name = com_code_store_name;
        this.store_thumbnail = store_thumbnail;
        this.saupja_no = saupja_no;
        this.tel = tel;
        this.hphone = hphone;
        this.post = post;
        this.address = address;
        this.van_tid = van_tid;
        this.gps_lat = gps_lat;
        this.gps_long = gps_long;
        this.distance = distance;
    }

    public String getCom_code_store_id() {
        return com_code_store_id;
    }

    public void setCom_code_store_id(String com_code_store_id) {
        this.com_code_store_id = com_code_store_id;
    }

    public String getCom_code_store_name() {
        return com_code_store_name;
    }

    public void setCom_code_store_name(String com_code_store_name) {
        this.com_code_store_name = com_code_store_name;
    }

    public String getStore_thumbnail() {
        return store_thumbnail;
    }

    public void setStore_thumbnail(String store_thumbnail) {
        this.store_thumbnail = store_thumbnail;
    }

    public String getSaupja_no() {
        return saupja_no;
    }

    public void setSaupja_no(String saupja_no) {
        this.saupja_no = saupja_no;
    }

    public String getManager_name() {
        return manager_name;
    }

    public void setManager_name(String manager_name) {
        this.manager_name = manager_name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getHphone() {
        return hphone;
    }

    public void setHphone(String hphone) {
        this.hphone = hphone;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVan_tid() {
        return van_tid;
    }

    public void setVan_tid(String van_tid) {
        this.van_tid = van_tid;
    }

    public String getGps_lat() {
        return gps_lat;
    }

    public void setGps_lat(String gps_lat) {
        this.gps_lat = gps_lat;
    }

    public String getGps_long() {
        return gps_long;
    }

    public void setGps_long(String gps_long) {
        this.gps_long = gps_long;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
