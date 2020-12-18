package com.lovezc.forever.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author shihaojian
 * @date 2020年3月30日16:20:43
 * @description
 */
@Data
public class RequestLog implements Serializable {

    /**
     * 日志主键
     */
    private Long id;
    /**
     * 请求ip
     */
    private String ip;
    /**
     * 请求地址
     */
    private String url;
    /**
     * 请求方式：普通请求 ajax请求
     */
    private String type;
    /**
     * 请求方式 get post
     */
    private String way;
    /**
     * 请求执行的类路径
     */
    private String classPath;
    /**
     * 请求方法名
     */
    private String methodName;
    /**
     * 请求参数json
     */
    private String param;
    /**
     * 操作类型
     */
    private String operation;
    /**
     * 请求接口唯一session标识
     */
    private String sessionId;
    /**
     * 请求开始时间
     */
    private Date startTime;
    /**
     * 请求完成时间 毫秒
     */
    private Long finishTime;
    /**
     * 接口返回时间
     */
    private Date returnTime;
    /**
     * 接口返回值
     */
    private String returnData;

}
