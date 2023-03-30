package com.solgae.newoliving.vo;

public class ResponseHeader {
    String status;
    String message;
    String result_code;
    String grp_code;
    String request;

    public ResponseHeader() {

    }

    public ResponseHeader(String status, String message, String result_code, String grp_code, String request) {
        this.status = status;
        this.message = message;
        this.result_code = result_code;
        this.grp_code = grp_code;
        this.request = request;
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

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getGrp_code() {
        return grp_code;
    }

    public void setGrp_code(String grp_code) {
        this.grp_code = grp_code;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
