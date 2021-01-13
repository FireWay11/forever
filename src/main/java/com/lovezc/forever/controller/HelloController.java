package com.lovezc.forever.controller;

import com.lovezc.forever.config.OperLog;
import com.lovezc.forever.util.utils.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.Set;

@Controller
public class HelloController {

    @Autowired
    private RedisClient redisClient;


    @RequestMapping("/")
    @OperLog(operModul = "欢迎",operType = "01",operDesc = "测试功能")
    public String hello(HttpServletRequest request, @RequestParam(value = "name", defaultValue = "world") String name) {
        request.setAttribute("name", name);
        return "hello";
    }

    /**
     * 测试redis删除key
     * @param str
     * @return
     */
    @RequestMapping("/test")
    @ResponseBody
    public boolean test(String str) {
        return redisClient.delLargeHashKey(str, 500);
    }


}
