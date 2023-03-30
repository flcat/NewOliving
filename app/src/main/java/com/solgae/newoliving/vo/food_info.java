package com.solgae.newoliving.vo;

import android.os.Parcel;
import android.os.Parcelable;

public class food_info  implements Parcelable, Comparable<food_info> {

    String main_code;
    String seq;
    String main_code_thumbnail;
    String com_code_store_name;
    String com_code_store_id;
    String store_thumbnail;
    String pro_code;
    String pro_title;
    String thumbnail;
    double gps_lat;
    double gps_long;
    double distance;
    double sale_cost;
    String saupja_no;
    String device_mac;
    String empty;
    int order_count;

    public food_info(){}

    public food_info(String main_code, String seq, String main_code_thumbnail, String com_code_store_name, String com_code_store_id, String pro_code,
            String pro_title, String thumbnail, double gps_lat, double gps_long, double distance, double sale_cost, String saupja_no,
            String device_mac, String empty, int order_count, String store_thumbnail){
        this.main_code = main_code;
        this.seq = seq;
        this.main_code_thumbnail = main_code_thumbnail;
        this.com_code_store_name = com_code_store_name;
        this.com_code_store_id = com_code_store_id;
        this.pro_code = pro_code;
        this.pro_title = pro_title;
        this.thumbnail = thumbnail;
        this.gps_lat = gps_lat;
        this.gps_long = gps_long;
        this.distance = distance;
        this.sale_cost = sale_cost;
        this.saupja_no = saupja_no;
        this.device_mac = device_mac;
        this.empty = empty;
        this.order_count = order_count;
        this.store_thumbnail = store_thumbnail;
    }

    public String getMain_code() {
        return main_code;
    }

    public void setMain_code(String main_code) {
        this.main_code = main_code;
    }

    public String getMain_code_thumbnail() {
        return main_code_thumbnail;
    }

    public void setMain_code_thumbnail(String main_code_thumbnail) {
        this.main_code_thumbnail = main_code_thumbnail;
    }

    public String getCom_code_store_name() {
        return com_code_store_name;
    }

    public void setCom_code_store_name(String com_code_store_name) {
        this.com_code_store_name = com_code_store_name;
    }

    public String getCom_code_store_id() {
        return com_code_store_id;
    }

    public void setCom_code_store_id(String com_code_store_id) {
        this.com_code_store_id = com_code_store_id;
    }

    public String getPro_title() {
        return pro_title;
    }

    public void setPro_title(String pro_title) {
        this.pro_title = pro_title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getSaupja_no() {
        return saupja_no;
    }

    public void setSaupja_no(String saupja_no) {
        this.saupja_no = saupja_no;
    }

    public String getDevice_mac() {
        return device_mac;
    }

    public void setDevice_mac(String device_mac) {
        this.device_mac = device_mac;
    }

    public double getGps_lat() {
        return gps_lat;
    }

    public void setGps_lat(double gps_lat) {
        this.gps_lat = gps_lat;
    }

    public double getGps_long() {
        return gps_long;
    }

    public void setGps_long(double gps_long) {
        this.gps_long = gps_long;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getSale_cost() {
        return sale_cost;
    }

    public void setSale_cost(double sale_cost) {
        this.sale_cost = sale_cost;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getPro_code() {
        return pro_code;
    }

    public void setPro_code(String pro_code) {
        this.pro_code = pro_code;
    }

    public String getEmpty() {
        return empty;
    }

    public void setEmpty(String empty) {
        this.empty = empty;
    }

    public  int getOrder_count() {
        return order_count;
    }

    public  void setOrder_count(int order_count) {
        this.order_count = order_count;
    }

    public String getStore_thumbnail() {
        return store_thumbnail;
    }

    public void setStore_thumbnail(String store_thumbnail) {
        this.store_thumbnail = store_thumbnail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.main_code);
        dest.writeString(this.seq);
        dest.writeString(this.main_code_thumbnail);
        dest.writeString(this.com_code_store_name);
        dest.writeString(this.com_code_store_id);
        dest.writeString(this.pro_code);
        dest.writeString(this.pro_title);
        dest.writeString(this.thumbnail);
        dest.writeDouble(this.gps_lat);
        dest.writeDouble(this.gps_long);
        dest.writeDouble(this.distance);
        dest.writeDouble(this.sale_cost);
        dest.writeString(this.saupja_no);
        dest.writeString(this.device_mac);
        dest.writeString(this.empty);
        dest.writeInt(this.order_count);
        dest.writeString(this.store_thumbnail);
    }

    public food_info(Parcel in) {
        this.main_code = in.readString();
        this.seq = in.readString();
        this.main_code_thumbnail = in.readString();
        this.com_code_store_name = in.readString();
        this.com_code_store_id = in.readString();
        this.pro_code = in.readString();
        this.pro_title = in.readString();
        this.thumbnail = in.readString();
        this.gps_lat = in.readDouble();
        this.gps_long = in.readDouble();
        this.distance = in.readDouble();
        this.sale_cost = in.readDouble();
        this.saupja_no = in.readString();
        this.device_mac = in.readString();
        this.empty = in.readString();
        this.order_count = in.readInt();
        this.store_thumbnail = in.readString();
    }

    @SuppressWarnings("rawtypes")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        @Override
        public food_info createFromParcel(Parcel in) {
            return new food_info(in);
        }

        @Override
        public food_info[] newArray(int size) {
            // TODO Auto-generated method stub
            return new food_info[size];
        }

    };

    @Override
    public int compareTo(food_info foodInfo) {
        return this.com_code_store_id.compareTo(foodInfo.com_code_store_id);
    }

}
