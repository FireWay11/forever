package com.lovezc.forever.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lovezc.forever.dao.TbUserDao;
import com.lovezc.forever.entity.TbUser;
import com.lovezc.forever.service.IndexService;
import com.lovezc.forever.service.TbUserService;
import com.mongodb.DBObject;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 页面服务实现类
 *
 * @author makejava
 * @since 2020-04-16 13:15:08
 */
@Service("indexService")
public class IndexServiceImpl implements IndexService {

    @Resource
    private MongoTemplate mongoTemplate;


    /**
     * 采集数据存储到mongodb中
     * @param data
     */
    @Override
    public void saveDataToMongo(String data) {
        JSONObject jsonObject = JSON.parseObject(data);
        mongoTemplate.insert(jsonObject,"city");
    }
}