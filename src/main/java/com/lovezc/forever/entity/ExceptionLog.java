package com.lovezc.forever.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ExceptionLog {

    private String excId;

    private String excRequParam;

    private String operMethod;

    private String excName;

    private String excMessage;

    private String operUri;

    private Date operCreateTime;

    @Override
    public String toString() {
        return "ExceptionLog{" +
                "excId='" + excId + '\'' +
                ", excRequParam='" + excRequParam + '\'' +
                ", operMethod='" + operMethod + '\'' +
                ", excName='" + excName + '\'' +
                ", excMessage='" + excMessage + '\'' +
                ", operUri='" + operUri + '\'' +
                ", operCreateTime=" + operCreateTime +
                '}';
    }
}
