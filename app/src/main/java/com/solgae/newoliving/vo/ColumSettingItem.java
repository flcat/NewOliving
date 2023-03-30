package com.solgae.newoliving.vo;

/**
 * 컬럼에 설정한 제품정보
 */
public class ColumSettingItem implements Comparable {

    private String itemgroup; // (자판기나 주문키오스크에서 사용됩니다. 자판기에서도 그룹이 존재합니다. 일반상품/스타일러/신발살균기)
    private String columm;
    private String item_code;
    private String item_name;
    private String item_cost;
    private String item_image;
    private String item_detail_image;
    private String item_mov;
    private String item_saupja_no;
    private String tocken; // 아이템이 장비일 경우(신발살균기, 스타일러)
    private boolean empty;

    public ColumSettingItem(String itemgroup, String columm, String item_code, String item_name,
                            String item_cost, String item_image, String item_detail_image, String item_mov,
                            String item_saupja_no, String tocken, boolean empty) {
        this.itemgroup = itemgroup;
        this.columm = columm;
        this.item_code = item_code;
        this.item_name = item_name;
        this.item_cost = item_cost;
        this.item_image = item_image;
        this.item_detail_image = item_detail_image;
        this.item_mov = item_mov;
        this.item_saupja_no = item_saupja_no;
        this.tocken = tocken;
        this.empty = empty;
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

    public String getTocken() {
        return tocken;
    }

    public void setTocken(String tocken) {
        this.tocken = tocken;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    /** * Comparable 인터페이스에 정의된 compareTo 메소드를 오버라이드 합니다. */
    @Override
    public int compareTo(Object settingItem) {
        ColumSettingItem instPro = (ColumSettingItem)settingItem;
        return this.columm.compareTo(instPro.columm);
    }
}
