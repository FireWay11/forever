package com.lovezc.forever.controller;

import com.lovezc.forever.entity.MessageResponse;
import com.lovezc.forever.model.RequestLog;
import com.lovezc.forever.service.IndexService;
import com.lovezc.forever.util.WebSocketUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 史浩健(a305566516@sina.com)
 * @Description IndexController
 * @CreateAt 2020/3/8 18:52
 */
@RestController
@RequestMapping("/data")
public class IndexController {

    /**
     * 首页服务对象
     */
    @Resource
    private IndexService indexService;

    /**
     * 全局时间
     */
    private long time = 0;

    /**
     * 整体项目测试接口
     * @return
     */
    @ApiOperation(value = "测试", notes="项目第一个测试方法")
    @GetMapping("/getData")
    public RequestLog getData(){
        RequestLog requestLog = new RequestLog();
        requestLog.setId(null);
        requestLog.setIp("localhost");
        requestLog.setMethodName("getData");
        requestLog.setParam("wu");
        requestLog.setOperation("测试数据");
        System.out.println(requestLog);
        return requestLog;
    }

    /**
     * 采集数据初始化
     */
    @GetMapping("/init")
    public void init(){
        time = System.currentTimeMillis();
        System.out.println("开始时间为："+System.currentTimeMillis());
    }


    /**
     * 测试采集数据保存到mongodb
     * @param data
     * @return
     */
    @PostMapping("/receiveData")
    public String receiveData(@RequestBody String data){
        String result = "123123123";
        indexService.saveDataToMongo(data);
        return result;
    }

    /**
     * 采集数据结束方法
     */
    @GetMapping("/destory")
    public void destory(){
        long duration = System.currentTimeMillis() - time;
        System.out.println("持续的时间为：" + duration);
    }

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    /**
     * 放置消息
     *
     */
    @RequestMapping(value = "/pushMessage")
    @ResponseBody
    public void pushMessage(){
        for(int i = 0;i < 10;i++){
            MessageResponse messageResponse = new MessageResponse();
            messageResponse.setResponseMessage("消息" + i);
            messageResponse.setStatus("1");
            WebSocketUtils.getInstance().push(messageResponse);
        }
    }

    /**
     * 将消息往页面发送
     *
     */
    @PostConstruct
    public void addMessage(){
        ExecutorService executorService= Executors.newFixedThreadPool(2);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        MessageResponse messageResponse =
                                (MessageResponse)WebSocketUtils.getInstance().poll();
                        if(messageResponse != null){
                            if(messagingTemplate != null) {
                                messagingTemplate.convertAndSend("/topic/pullMessage"
                                        ,messageResponse);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        executorService.submit(runnable);
        executorService.submit(runnable);
    }
}
