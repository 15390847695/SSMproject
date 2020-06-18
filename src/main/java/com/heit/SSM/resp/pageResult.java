package com.heit.SSM.resp;

import jdk.nashorn.internal.runtime.linker.LinkerCallSite;

import java.io.Serializable;
import java.util.List;

public class pageResult<T> implements Serializable {
    long count;
    String code;
    String msg;
    List<T> data;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
