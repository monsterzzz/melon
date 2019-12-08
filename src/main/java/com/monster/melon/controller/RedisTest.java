package com.monster.melon.controller;


import com.monster.melon.pojo.Tag;
import com.monster.melon.pojo.User;
import com.monster.melon.util.RedisKey;
import com.monster.melon.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import sun.dc.pr.PRError;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/redis")
@Slf4j
public class RedisTest {

    @Resource
    private StringRedisTemplate redisTemplate;


    @PostMapping("/{key}")
    public String test(@PathVariable("key")String key){
        Map<String, User> map = new HashMap<>();
        map.put("1",new User());
        redisTemplate.opsForHash().putAll(key,map);
        return "success";
    }

    @GetMapping("/{key}")
    public Map<Object,Object> tes(@PathVariable("key")String key){
        return redisTemplate.opsForHash().entries(key);
    }


}
