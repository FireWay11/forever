package com.lovezc.forever.model;

/**
 * @author shihaojian(a305566516 @ sina.com)
 * @2020/4/2 9:48
 * @description 异常实体类
 */
public class ExceptionLog {

    /**
     * 主键
     */
    private Long id;

    /**
     * 异常对象json形式
     */
    private String exceptionJson;

    /**
     * 异常对象简单信息，等同于e.getMessage();
     */
    private String exceptionMessage;
    /**
     * 异常产生时间
     */
    private String happenTime;

}
