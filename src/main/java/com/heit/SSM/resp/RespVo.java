package com.heit.SSM.resp;

public class RespVo {
    private int code=0;
    private String msg="请求成功";


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

    /**
     * 成功返回
     */

    public static RespVo getSuccess() {
        return new RespVo();
    }
    /**
     * 失败返回
     */

    public static RespVo getFail(String msg){
        RespVo respVo=new RespVo();
        respVo.setCode(-1);
        respVo.setMsg(msg);

        return respVo;
    }
}
