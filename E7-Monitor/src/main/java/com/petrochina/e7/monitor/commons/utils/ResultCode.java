package com.petrochina.e7.monitor.commons.utils;

/**
 * @ProjectName com.petrochina.e7.monitor.commons.utils
 * @ClassName: ResultCode
 * @Description: TODO
 * @Author: Administrator
 * @Date: 2019/08/27 0027$ 11:07$
 * @Version: 1.0
 */


    public enum  ResultCode {
    /**
     * 成功
     */
    SUCCESS(200),
    /**
     * 失败
     */
    FAIL(400),

    /**
     * 未认证（签名错误）
     */
    UNAUTHORIZED(401),

    /**
     * 接口不存在
     */
    NOT_FOUND(404),

    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR (500);

    public int code;

    ResultCode(int code) {
        this.code = code;
    }
}
