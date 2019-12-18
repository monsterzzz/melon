package com.monster.melon.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedisUtil {


    public static StringRedisTemplate redisTemplate;

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate redisTemplate) {
        RedisUtil.redisTemplate = redisTemplate;

    }

   public static <T> List<T> getPageCache(String key, Integer page, Class<T> clazz){
        page = Math.max(page-1,0);
        int start = page * 7;
        Set<String> ids = redisTemplate.opsForZSet().range(key,start,start+6);
        ids = ids == null || ids.size() == 0 ? new HashSet<>() : ids ;
        String itemKey = key.split(":")[0];
        if(!itemKey.equals("news")){
            itemKey = itemKey.substring(0,key.length() - 2);
        }
        log.info(itemKey);
        List<T> list = new ArrayList<>();
        for(String id : ids){
            Map<Object,Object> map = redisTemplate.opsForHash().entries(String.format("%s:%s",itemKey,id));
           list.add(Map2Object(map,clazz));
        }
        return list;
   }

   public static <T> T Map2Object(Map<Object,Object> map,Class<T> clazz){
        return JSON.parseObject(JSON.toJSONString(map),clazz);
   }

   public static JSONObject object2Map(Object o){
        return JSON.parseObject(JSON.toJSONString(o));
   }



}
