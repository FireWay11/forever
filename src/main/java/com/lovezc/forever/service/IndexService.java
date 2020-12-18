package com.lovezc.forever.service;

import com.lovezc.forever.entity.TbUser;

import java.util.List;

/**
 * 首页服务接口
 *
 * @author 史浩健 a305566516@sina.com
 * @since 2020-04-16 13:15:08
 */
public interface IndexService {

    /**
     * 采集数据存储到mongodb中
     * @param data
     */
    void saveDataToMongo(String data);
}