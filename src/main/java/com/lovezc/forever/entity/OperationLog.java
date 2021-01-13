package com.lovezc.forever.entity;

import lombok.Data;

import java.util.Date;

/**
 * 日志
 */
@Data
public class OperationLog {

    private String operId;

    private String operModul;

    private String operType;

    private String operDesc;

    private String operMethod;

    private String operRequParam;

    private String operRespParam;

    private String operUri;

    private Date operCreateTime;

    private String operVer;

    @Override
    public String toString() {
        return "OperationLog{" +
                "operId='" + operId + '\'' +
                ", operModul='" + operModul + '\'' +
                ", operType='" + operType + '\'' +
                ", operDesc='" + operDesc + '\'' +
                ", operMethod='" + operMethod + '\'' +
                ", operRequParam='" + operRequParam + '\'' +
                ", operRespParam='" + operRespParam + '\'' +
                ", operUri='" + operUri + '\'' +
                ", operCreateTime=" + operCreateTime +
                ", operVer='" + operVer + '\'' +
                '}';
    }
}
