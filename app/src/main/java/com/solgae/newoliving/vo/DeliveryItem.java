package com.solgae.newoliving.vo;

public class DeliveryItem implements Comparable {

    String main_code;
    String main_code_thumbnail;
    String com_code_store_name;
    String com_code_store_id;
    String pro_title;
    String thumbnail;
    float gps_lat;
    float distance;
    float sale_cost;

    public DeliveryItem() {}

    public DeliveryItem(String main_code, String main_code_thumbnail, String com_code_store_name,
                String com_code_store_id, String pro_title, String thumbnail, float gps_lat,
                float distance, float sale_cost) {
        this.main_code = main_code;
        this.main_code_thumbnail = main_code_thumbnail;
        this.com_code_store_name = com_code_store_name;
        this.com_code_store_id = com_code_store_id;
        this.pro_title = pro_title;
        this.thumbnail = thumbnail;
        this.gps_lat = gps_lat;
        this.distance = distance;
        this.sale_cost = sale_cost;
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

    public float getGps_lat() {
        return gps_lat;
    }

    public void setGps_lat(float gps_lat) {
        this.gps_lat = gps_lat;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getSale_cost() {
        return sale_cost;
    }

    public void setSale_cost(float sale_cost) {
        this.sale_cost = sale_cost;
    }

    /** * Comparable 인터페이스에 정의된 compareTo 메소드를 오버라이드 합니다. */
    @Override
    public int compareTo(Object deliveryItem) {
        DeliveryItem instPro = (DeliveryItem)deliveryItem;
        return this.com_code_store_id.compareTo(instPro.com_code_store_id);
    }
}
