package com.solgae.newoliving.vo;

import java.io.Serializable;

public class contents implements Serializable {
    private int seq;
    private String contents;
    private String title;
    private String totaltime;
    private String playtime;
    private String local_folder;
    private String fileName;
    private String convert_url;
    private boolean convert_flag = false;

    public contents(int seq, String contents, String title, String totaltime, String playtime,
                    String local_folder, String fileName) {
        this.seq = seq;
        this.contents = contents;
        this.title = title;
        this.totaltime = totaltime;
        this.playtime = playtime;
        this.local_folder = local_folder;
        this.fileName = fileName;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTotaltime() {
        return totaltime;
    }

    public void setTotaltime(String totaltime) {
        this.totaltime = totaltime;
    }

    public String getPlaytime() {
        return playtime;
    }

    public void setPlaytime(String playtime) {
        this.playtime = playtime;
    }

    public String getLocal_folder() {
        return local_folder;
    }

    public void setLocal_folder(String local_folder) {
        this.local_folder = local_folder;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getConvert_url() {
        return convert_url;
    }

    public void setConvert_url(String convert_url) {
        this.convert_url = convert_url;
    }

    public boolean isConvert_flag() {
        return convert_flag;
    }

    public void setConvert_flag(boolean convert_flag) {
        this.convert_flag = convert_flag;
    }
}