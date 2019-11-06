package com.petrochina.e7.monitor.commons.utils;

//import lombok.Data;

import java.util.Objects;

/**
 * @ProjectName com.petrochina.e7.monitor.commons.utils
 * @ClassName: Result
 * @Description: TODO 封装结果集
 * @Author: Administrator
 * @Date: 2019/08/27 0027$ 11:05$
 * @Version: 1.0
 */
//@Data
public class Result {
    /**
     * 响应状态码
     */
    private int code;
    /**
     * 响应提示信息
     */
    private String message;
    /**
     * 响应结果对象
     */
    private Object data;

    /**
     * 响应条数
     */
    private int count;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return code == result.code &&
                count == result.count &&
                Objects.equals(message, result.message) &&
                Objects.equals(data, result.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message, data, count);
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", count=" + count +
                '}';
    }
}






