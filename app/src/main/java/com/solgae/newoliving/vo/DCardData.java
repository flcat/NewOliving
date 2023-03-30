package com.solgae.newoliving.vo;

public class DCardData {

    String bill_no;
    String main_code;
    String sub_code;
    String step_code;
    String menu_style;
    String pro_title;
    String pro_code;
    String unit_money;
    String pro_cnt;
    String dis_money;
    String result_money;

    public DCardData() {

    }

    public DCardData(String bill_no, String main_code, String sub_code, String step_code, String menu_style,
                     String pro_title, String pro_code, String unit_money, String pro_cnt,
                     String dis_money, String result_money) {
        this.bill_no = bill_no;
        this.main_code = main_code;
        this.sub_code = sub_code;
        this.step_code = step_code;
        this.menu_style = menu_style;
        this.pro_title = pro_title;
        this.pro_code = pro_code;
        this.unit_money = unit_money;
        this.pro_cnt = pro_cnt;
        this.dis_money = dis_money;
        this.result_money = result_money;
    }

    public String getBill_no() {
        return bill_no;
    }

    public void setBill_no(String bill_no) {
        this.bill_no = bill_no;
    }

    public String getMain_code() {
        return main_code;
    }

    public void setMain_code(String main_code) {
        this.main_code = main_code;
    }

    public String getSub_code() {
        return sub_code;
    }

    public void setSub_code(String sub_code) {
        this.sub_code = sub_code;
    }

    public String getStep_code() {
        return step_code;
    }

    public void setStep_code(String step_code) {
        this.step_code = step_code;
    }

    public String getMenu_style() {
        return menu_style;
    }

    public void setMenu_style(String menu_style) {
        this.menu_style = menu_style;
    }

    public String getPro_title() {
        return pro_title;
    }

    public void setPro_title(String pro_title) {
        this.pro_title = pro_title;
    }

    public String getPro_code() {
        return pro_code;
    }

    public void setPro_code(String pro_code) {
        this.pro_code = pro_code;
    }

    public String getUnit_money() {
        return unit_money;
    }

    public void setUnit_money(String unit_money) {
        this.unit_money = unit_money;
    }

    public String getPro_cnt() {
        return pro_cnt;
    }

    public void setPro_cnt(String pro_cnt) {
        this.pro_cnt = pro_cnt;
    }

    public String getDis_money() {
        return dis_money;
    }

    public void setDis_money(String dis_money) {
        this.dis_money = dis_money;
    }

    public String getResult_money() {
        return result_money;
    }

    public void setResult_money(String result_money) {
        this.result_money = result_money;
    }
}
