package com.solgae.newoliving.vo;

import java.io.Serializable;

public class OrderItem implements Serializable {

    String itemcode;
    String itemname;
    int itemqty;
    int itemdanga;
    int itemcost;

    public OrderItem() {}

    public OrderItem(String itemcode, String itemname, int itemqty, int itemdanga, int itemcost) {
        this.itemcode = itemcode;
        this.itemname = itemname;
        this.itemqty = itemqty;
        this.itemdanga = itemdanga;
        this.itemcost = itemcost;
    }

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public int getItemqty() {
        return itemqty;
    }

    public void setItemqty(int itemqty) {
        this.itemqty = itemqty;
    }

    public int getItemdanga() {
        return itemdanga;
    }

    public void setItemdanga(int itemdanga) {
        this.itemdanga = itemdanga;
    }

    public int getItemcost() {
        return itemcost;
    }

    public void setItemcost(int itemcost) {
        this.itemcost = itemcost;
    }

}
