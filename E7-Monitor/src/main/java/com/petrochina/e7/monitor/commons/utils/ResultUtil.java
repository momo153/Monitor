package com.petrochina.e7.monitor.commons.utils;

/**
 * @ProjectName com.petrochina.e7.monitor.commons.utils
 * @ClassName: ResultUtil
 * @Description: TODO
 * @Author: Administrator
 * @Date: 2019/09/09 0009$ 10:39$
 * @Version: 1.0
 */

public class ResultUtil {
    /**
     * 请求成功返回
     * @param object
     * @return
     */
    public static Result success(Object object,int count){
        Result Result=new Result();
        Result.setCode(0);
        Result.setMessage("成功");
        Result.setData(object);
        Result.setCount(count);
        return Result;
    }
    public static Result success(Object object){
        Result Result=new Result();
        Result.setCode(0);
        Result.setMessage("成功");
        Result.setData(object);
//        Result.setCount(count);
        return Result;
    }

    public static Result error(Integer code,String resultResult){
        Result Result=new Result();
        Result.setCode(code);
        Result.setMessage(resultResult);
        return Result;
    }
}
