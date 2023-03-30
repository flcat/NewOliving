package com.solgae.newoliving.jsonresult;

import com.solgae.newoliving.vo.store_info;

import java.util.ArrayList;

public class api_food_store {

    String status;
    String message;
    String mac_code;
    String request;
    String count;
    ArrayList<store_info> food_info;

    public api_food_store(){}

    public api_food_store(String status, String message, String mac_code, String request,
                           String count, ArrayList<store_info> food_info)
    {
        this.status = status;
        this.message = message;
        this.mac_code = mac_code;
        this.request = request;
        this.count = count;
        this.food_info = food_info;
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

    public String getMac_code() {
        return mac_code;
    }

    public void setMac_code(String mac_code) {
        this.mac_code = mac_code;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public ArrayList<store_info> getFood_info() {
        return food_info;
    }

    public void setFood_info(ArrayList<store_info> food_info) {
        this.food_info = food_info;
    }
}
