package com.solgae.newoliving.busevent;

import com.solgae.newoliving.vo.ColumSettingItem;

public class PushEvent {

    String command; // 대분류 ^ 소분류
    String data;
    ColumSettingItem columSettingItem;
    Object obj;

    public PushEvent(String command, ColumSettingItem columSettingItem) {
        this.command = command;
        this.columSettingItem = columSettingItem;
    }

    public PushEvent(String command, String data) {
        this.command = command;
        this.data = data;
    }

    public PushEvent(String command, Object obj) {
        this.command = command;
        this.obj = obj;
    }

    public PushEvent(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public ColumSettingItem getIns_product() {
        return columSettingItem;
    }

    public void setIns_product(ColumSettingItem columSettingItem) {
        this.columSettingItem = columSettingItem;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
