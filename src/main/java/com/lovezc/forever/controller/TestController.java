package com.lovezc.forever.controller;

import com.lovezc.forever.config.OperLog;
import com.lovezc.forever.entity.TbUser;
import com.lovezc.forever.util.utils.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController {

    @RequestMapping("/testlog")
    @OperLog(operModul = "欢迎",operType = "01",operDesc = "测试功能")
    public String test(String name) {
        TbUser tbUser = null;
        try{
            String id = tbUser.getId();
        }catch (Exception e){
            e.printStackTrace();
        }
        return name;
    }


}
