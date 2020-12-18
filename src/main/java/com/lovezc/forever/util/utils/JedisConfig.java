package com.lovezc.forever.util.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

@Configuration
@Data
public class JedisConfig {


    @Bean(name = "jedis.pool")
    @Autowired
    public JedisPool jedisPool(@Qualifier("jedis.pool.config") JedisPoolConfig config,
                               @Value("${spring.redis.host}") String host,
                               @Value("${spring.redis.port}") int port,
                               @Value("${spring.redis.timeout}") int timeout,
                               @Value("${spring.redis.password}") String password) {
        return new JedisPool(config, host, port, Protocol.DEFAULT_TIMEOUT, password);
    }

    @Bean(name = "jedis.pool.config")
    public JedisPoolConfig jedisPoolConfig(@Value("${spring.redis.jedis.pool.maxTotal}") int maxTotal,
                                           @Value("${spring.redis.jedis.pool.maxIdle}") int maxIdle,
                                           @Value("${spring.redis.jedis.pool.maxWaitMillis}") int maxWaitMillis,
                                           @Value("${spring.redis.jedis.pool.testOnBorrow}") boolean testOnBorrow,
                                           @Value("${spring.redis.jedis.pool.testOnReturn}") boolean testOnReturn) {

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnBorrow);
        return config;
    }

}