package com.solgae.newoliving.vo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import javax.crypto.Mac;

public class MachineColummInfo implements Serializable {
    private String macaddress;
    private String itemgroup; // (자판기나 주문키오스크에서 사용됩니다. 자판기에서도 그룹이 존재합니다. 일반상품/스타일러/신발살균기)
    private String columm;
    private String item_code;
    private String item_name;
    private String item_cost;
    private String item_image;
    private String item_detail_image;
    private String item_mov;
    private String item_date;
    private String item_saupja_no;
    private String item_tid;
    private String token; // 아이템이 장비일 경우(신발살균기, 스타일러)
    private boolean empty;
    private String remain_cnt; // 아이템 수량
    private String new_cnt;
    private String stock;
    private String now_page;
    private String country;
    private String shelf_life_day;
    private String memo;
    private Boolean NoticeClick; // 상품사진 앞화면(true), 유의사항 뒤화면(false)
    private String Machine_MacCode; // 카테고리 기기별 맥코드

    public String getMachine_MacCode() {
        return Machine_MacCode;
    }

    public void setMachine_MacCode(String machine_MacCode) {
        Machine_MacCode = machine_MacCode;
    }


    public Boolean getNoticeClick() {
        return NoticeClick;
    }

    public void setNoticeClick(Boolean noticeClick) {
        NoticeClick = noticeClick;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }



    public String getShelf_life_day() {
        return shelf_life_day;
    }

    public void setShelf_life_day(String shelf_life_day) {
        this.shelf_life_day = shelf_life_day;
    }



    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }


    public int getSelectedItemNum() {
        return SelectedItemNum;
    }

    public void setSelectedItemNum(int selectedItemNum) {
        SelectedItemNum = selectedItemNum;
    }

    private int SelectedItemNum;

    public String getNow_page() {
        return now_page;
    }

    public void setNow_page(String now_page) {
        this.now_page = now_page;
    }


    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    private boolean checked = false;

    public void setStock(String stock) {
        this.stock = stock;
    }
    public String getStock(){return stock; }

//    public MachineColummInfo() {}

    public MachineColummInfo(String macaddress, String itemgroup, String columm, String item_code, String item_name,
                             String item_cost, String item_image, String item_detail_image, String item_mov, String item_date,
                             String item_saupja_no, String token, boolean empty, String item_tid,  String country, String shelf_life_day,
                             String memo) {
        this.macaddress = macaddress;
        this.itemgroup = itemgroup;
        this.columm = columm;
        this.item_code = item_code;
        this.item_name = item_name;
        this.item_cost = item_cost;
        this.item_image = item_image;
        this.item_detail_image = item_detail_image;
        this.item_mov = item_mov;
        this.item_date = item_date;
        this.item_saupja_no = item_saupja_no;
        this.token = token;
        this.empty = empty;
        this.item_tid = item_tid;
        this.country = country;
        this.shelf_life_day = shelf_life_day;
        this.memo = memo;
    }

    public String getItemgroup() {
        return itemgroup;
    }

    public void setItemgroup(String itemgroup) {
        this.itemgroup = itemgroup;
    }

    public String getColumm() {
        return columm;
    }

    public void setColumm(String columm) {
        this.columm = columm;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_cost() {
        return item_cost;
    }

    public void setItem_cost(String item_cost) {
        this.item_cost = item_cost;
    }

    public String getItem_image() {
        return item_image;
    }

    public void setItem_image(String item_image) {
        this.item_image = item_image;
    }

    public String getItem_detail_image() {
        return item_detail_image;
    }

    public void setItem_detail_image(String item_detail_image) {
        this.item_detail_image = item_detail_image;
    }

    public String getItem_mov() {
        return item_mov;
    }

    public void setItem_mov(String item_mov) {
        this.item_mov = item_mov;
    }

    public String getItem_saupja_no() {
        return item_saupja_no;
    }

    public void setItem_saupja_no(String item_saupja_no) {
        this.item_saupja_no = item_saupja_no;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public String getMacaddress() {
        return macaddress;
    }

    public void setMacaddress(String macaddress) {
        this.macaddress = macaddress;
    }

    public String getItem_date() {
        return item_date;
    }

    public void setItem_date(String item_date) {
        this.item_date = item_date;
    }

    public String getItem_tid() {
        return item_tid;
    }

    public void setItem_tid(String item_tid) {
        this.item_tid = item_tid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRemain_cnt() {
        return remain_cnt;
    }

    public void setRemain_cnt(String remain_cnt) {
        this.remain_cnt = remain_cnt;
    }

    public void reset() {
//        MachineColummInfo ma = new MachineColummInfo();
    }
}
