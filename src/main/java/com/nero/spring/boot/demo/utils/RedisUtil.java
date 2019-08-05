package com.nero.spring.boot.demo.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author Nero
 * @since 2019-08-05 10:43
 */
public class RedisUtil {
    private static JedisPool jedisPool = ContextUtil.getBean(JedisPool.class);

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }

    public static void releseJedis(Jedis jedis){
        if (jedis != null){
            jedis.close();
        }
    }

    public static String set(String key, String value){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.set(key, value);
        }finally {
            releseJedis(jedis);
        }
    }

    public static String get(String key){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.get(key);
        }finally {
            releseJedis(jedis);
        }
    }
}
