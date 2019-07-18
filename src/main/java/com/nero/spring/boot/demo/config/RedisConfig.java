package com.nero.spring.boot.demo.config;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nero
 * @since 2019-07-18 13:45
 */
@Configuration
public class RedisConfig {
    @Value("${redis.host}")
    private String host;
    @Value("${redisson.database}")
    private Integer database;
    @Value("${redisson.password}")
    private String password;

    @Bean
    public RedissonClient redissonClient(){

        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + host)
                .setPassword(password)
                .setDatabase(database);
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }
}
