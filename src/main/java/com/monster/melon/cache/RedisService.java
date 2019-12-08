package com.monster.melon.cache;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class RedisService {
    @Resource
    private StringRedisTemplate redisTemplate;

    public List<String> lRange(String key,Integer start,Integer end){
        return redisTemplate.opsForList().range(key,start,end);
    }

}
