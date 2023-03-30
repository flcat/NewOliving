package com.solgae.newoliving.vo;

import java.io.Serializable;
import java.util.ArrayList;

public class ApprovalReport implements Serializable {

    private static final long serialVersionUID = 1L;

    String SaupjaName;
    String SaupjaNo;
    String DaepyoName;
    String PhoneNumber;
    String Address;
    String TableNo;
    String OrderNumber;
    ArrayList<OrderItem> orderitems;
    int OrderTotal;
    int SaleCost;
    int ChangeCost;
    int Cash;
    int Card;
    int Point;
    int taxation;  //과세
    int taxamount; //세액
    int taxfree;   //면세
    int Receipt_Amount; // 영수금액
// 신용승인내역
    String card_office;
    String card_number;
    String approval_number;
    String approval_cost;
    String affiliate; // 가맹번호
    String approval_date_time;
    String machin_number;




    public String getSaupjaName() {
        return SaupjaName;
    }

    public void setSaupjaName(String saupjaName) {
        SaupjaName = saupjaName;
    }

    public String getSaupjaNo() {
        return SaupjaNo;
    }

    public void setSaupjaNo(String saupjaNo) {
        SaupjaNo = saupjaNo;
    }

    public String getDaepyoName() {
        return DaepyoName;
    }

    public void setDaepyoName(String daepyoName) {
        DaepyoName = daepyoName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getTableNo() {
        return TableNo;
    }

    public void setTableNo(String tableNo) {
        TableNo = tableNo;
    }

    public String getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        OrderNumber = orderNumber;
    }

    public ArrayList<OrderItem> getOrderitems() {
        return orderitems;
    }

    public void setOrderitems(ArrayList<OrderItem> orderitems) {
        this.orderitems = orderitems;
    }

    public int getOrderTotal() {
        return OrderTotal;
    }

    public void setOrderTotal(int orderTotal) {
        OrderTotal = orderTotal;
    }

    public int getSaleCost() {
        return SaleCost;
    }

    public void setSaleCost(int saleCost) {
        SaleCost = saleCost;
    }

    public int getChangeCost() {
        return ChangeCost;
    }

    public void setChangeCost(int changeCost) {
        ChangeCost = changeCost;
    }

    public int getCash() {
        return Cash;
    }

    public void setCash(int cash) {
        Cash = cash;
    }

    public int getCard() {
        return Card;
    }

    public void setCard(int card) {
        Card = card;
    }

    public int getPoint() {
        return Point;
    }

    public void setPoint(int point) {
        Point = point;
    }

    public int getTaxation() {
        return taxation;
    }

    public void setTaxation(int taxation) {
        this.taxation = taxation;
    }

    public int getTaxamount() {
        return taxamount;
    }

    public void setTaxamount(int taxamount) {
        this.taxamount = taxamount;
    }

    public int getTaxfree() {
        return taxfree;
    }

    public void setTaxfree(int taxfree) {
        this.taxfree = taxfree;
    }

    public int getReceipt_Amount() {
        return Receipt_Amount;
    }

    public void setReceipt_Amount(int receipt_Amount) {
        Receipt_Amount = receipt_Amount;
    }

    public String getCard_office() {
        return card_office;
    }

    public void setCard_office(String card_office) {
        this.card_office = card_office;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getApproval_number() {
        return approval_number;
    }

    public void setApproval_number(String approval_number) {
        this.approval_number = approval_number;
    }

    public String getApproval_cost() {
        return approval_cost;
    }

    public void setApproval_cost(String approval_cost) {
        this.approval_cost = approval_cost;
    }

    public String getAffiliate() {
        return affiliate;
    }

    public void setAffiliate(String affiliate) {
        this.affiliate = affiliate;
    }

    public String getApproval_date_time() {
        return approval_date_time;
    }

    public void setApproval_date_time(String approval_date_time) {
        this.approval_date_time = approval_date_time;
    }

    public String getMachin_number() {
        return machin_number;
    }

    public void setMachin_number(String machin_number) {
        this.machin_number = machin_number;
    }
}
