package com.happy.bwiesample.entry;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/16
 * @Time 11:01
 */

public class  VideoHttpResponse<T> {
    private int code;
    private String msg;
    T ret;

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

    public T getRet() {
        return ret;
    }

    public void setRet(T ret) {
        this.ret = ret;
    }
}
