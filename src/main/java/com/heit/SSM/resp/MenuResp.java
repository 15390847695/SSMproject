package com.heit.SSM.resp;

import java.util.List;

public class MenuResp {

    private int code;

    private String msg;

    private List<MenusData> data;

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

    public List<MenusData> getData() {
        return data;
    }

    public void setData(List<MenusData> data) {
        this.data = data;
    }
}
