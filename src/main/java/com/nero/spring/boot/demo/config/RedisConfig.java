package com.nero.spring.boot.demo.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author Nero
 * @since 2019-07-18 13:45
 */
@Configuration
public class RedisConfig {
    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private int port;
    @Value("${redis.password}")
    private String password;
    @Value("${redis.database}")
    private Integer redisDatabase;

    @Value("${redisson.database}")
    private Integer redissonDatabase;

    @Bean
    public RedissonClient redissonClient(){

        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + host + ":" + port)
                .setPassword(password)
                .setDatabase(redissonDatabase);
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }

    @Bean
    public JedisPool getJedisPool(){

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMinIdle(5);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMaxWaitMillis(10000);
        jedisPoolConfig.setMaxTotal(50);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, 5000, password, redisDatabase);

        return jedisPool;
    }
}
