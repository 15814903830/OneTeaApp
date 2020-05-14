package com.example.oneteaapp.base;

public class SetOrderBase {

    /**
     * code : 0
     * msg : 余额不足！
     * data :
     * url : javascript:history.back(-1);
     * wait : 3
     */

    private int code;
    private String msg;
    private String data;
    private String url;
    private int wait;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWait() {
        return wait;
    }

    public void setWait(int wait) {
        this.wait = wait;
    }
}
