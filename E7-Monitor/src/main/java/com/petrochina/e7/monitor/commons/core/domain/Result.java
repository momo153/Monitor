package com.petrochina.e7.monitor.commons.core.domain;

public class Result {
    private boolean flag;   //是否成功
    private Object data;    //返回数据

    public Result() {
    }

    public Result(boolean flag) {
        this.flag = flag;
    }

    public Result(boolean flag, Object data) {
        this.flag = flag;
        this.data = data;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
