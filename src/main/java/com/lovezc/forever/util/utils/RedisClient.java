package com.lovezc.forever.util.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.exceptions.JedisException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class RedisClient {


    @Autowired
    private JedisPool jedisPool;

     public Jedis getJedis() {
        return jedisPool.getResource();
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return Boolean
     */
    public String set(final String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.set(key, String.valueOf(value));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            jedis.close();
        }
    }

     /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Optional<String> get(final String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return Optional.ofNullable(jedis.get(key));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    //批量获取匹配的所有的key
    public Set<String> getScan(String key, Integer count) {
        Jedis jedis = null;
        Set<String> sets = new HashSet<>();
        ScanParams params = new ScanParams();
        try{
            jedis = getJedis();
            params.match(key);
            params.count(count);
            String cursor = "0";
            while (true) {
                ScanResult scanResult = jedis.scan(cursor, params);
                List<String> elements = scanResult.getResult();
                if (elements != null && elements.size() > 0) {
                    sets.addAll(elements);
                }
                cursor = scanResult.getCursor();
                if ("0".equals(cursor)) {
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();

        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return sets;
    }

    //删除 批量key
    public boolean delLargeHashKey(String key, Integer count) {
        Jedis jedis = getJedis();
        try {
            Set<String> sets = getScan(key, count);
            if (sets != null && sets.size() > 0) {
                for (String keyt : sets) {
                    //删除
                    jedis.del(keyt);
                }
                return true;
            }
            return false;
        } catch (JedisException e) {
            if (jedis != null) {
                jedis.close();
            }
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }



}