package com.lovezc.forever.util;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;  
  
  
public class WebSocketUtils {  
  
    // 队列大小  
    public static final int QUEUE_MAX_SIZE = 10000;  
  
    private static WebSocketUtils alarmWebSocketUtils = new WebSocketUtils();  
  
    // 阻塞队列  
    private BlockingQueue<Object> blockingQueue = new LinkedBlockingQueue<Object>(QUEUE_MAX_SIZE);  
  
    private WebSocketUtils(){  
  
    }  
  
    public static WebSocketUtils getInstance() {  
        return alarmWebSocketUtils;  
    }  
  
    /** 
     * 消息入队 - 队列满了就抛出异常，不阻塞 
     * 
     * @param message 
     * @return 
     */  
    public boolean push(Object message) {  
        return this.blockingQueue.add(message);  
    }  
  
    /** 
     * 消息出队 
     * 
     * @return 
     */  
    public Object poll() {  
        Object result = null;  
        try {  
            result = this.blockingQueue.take();  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        return result;  
    }  
}