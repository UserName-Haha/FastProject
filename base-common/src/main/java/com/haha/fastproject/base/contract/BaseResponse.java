package com.haha.fastproject.base.contract;


public class BaseResponse {

    private String msg;
    private int stat;

    public boolean isOk() {
        return stat == 200;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }
}
