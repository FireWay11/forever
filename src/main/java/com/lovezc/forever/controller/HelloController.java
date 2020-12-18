package com.lovezc.forever.controller;

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
    public String hello(HttpServletRequest request, @RequestParam(value = "name", defaultValue = "笨蛋小面包") String name) {
        request.setAttribute("name", name);
        return "hello";
    }

    @RequestMapping("/test")
    @ResponseBody
    public boolean test(String str) {
//        Set<String> test = redisClient.getScan(str, 500);
//        return test;
        boolean b = redisClient.delLargeHashKey(str, 500);
        return b;
    }


}
