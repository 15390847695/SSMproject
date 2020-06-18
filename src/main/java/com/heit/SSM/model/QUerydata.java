package com.heit.SSM.model;

import java.util.List;

public class QUerydata {
    private int  code;
    private String msg;
    private List<tbadmin> data;

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

    public List<tbadmin> getData() {
        return data;
    }

    public void setData(List<tbadmin> data) {
        this.data = data;
    }
}
