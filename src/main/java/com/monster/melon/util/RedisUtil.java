package com.monster.melon.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {


    public static RedisTemplate stringRedisTemplate;

    @Autowired
    public void setStringRedisTemplate(RedisTemplate stringRedisTemplate) {
        RedisUtil.stringRedisTemplate = stringRedisTemplate;

    }

    public static ValueOperations String(){
        return stringRedisTemplate.opsForValue();
    }

    public static ListOperations List(){
        return stringRedisTemplate.opsForList();
    }

    public static SetOperations Set(){
        return stringRedisTemplate.opsForSet();
    }

    public static ZSetOperations ZSet(){
        return stringRedisTemplate.opsForZSet();
    }

    public static HashOperations Hash(){
        return stringRedisTemplate.opsForHash();
    }

    @SuppressWarnings("unchecked")
    public static void ExpireAt(String key, Duration t){
        stringRedisTemplate.expireAt(key,new Date(System.currentTimeMillis() + (t.getSeconds() * 1000)));
    }




}
