package com.monster.melon.cache;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

//    @Bean
//    @Primary//@Primary：自动装配时当出现多个Bean候选者时，被注解为@Primary的Bean将作为首选者，否则将抛出异常
//    public KeyGenerator keyGenerator() {
//        return (o, method, objects) -> {
//            StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append(o.getClass().getSimpleName());
//            stringBuilder.append(".");
//            stringBuilder.append(method.getName());
//            stringBuilder.append("[");
//            for (Object obj : objects) {
//                if(obj != null){
//                    stringBuilder.append(obj.toString());
//
//                }
//            }
//            stringBuilder.append("]");
//            log.info(stringBuilder.toString());
//            return stringBuilder.toString();
//        };
//    }



    /**
     * spring boot 缓存默认配置
     * @param factory
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        return RedisCacheManager.builder(factory)
                //默认缓存时间
                .cacheDefaults(getRedisCacheConfigurationWithTtl(60))
                .transactionAware()
                //自定义缓存时间
                .withInitialCacheConfigurations(getRedisCacheConfigurationMap())
                .build();
    }

    /**
     * 自定义缓存时间
     * @return
     */
    private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap() {
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
        redisCacheConfigurationMap.put("user", this.getRedisCacheConfigurationWithTtl(3000));
        return redisCacheConfigurationMap;
    }

    private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Integer seconds) {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(seconds))//定义默认的cache time-to-live.(缓存存储有效时间)
                .disableCachingNullValues()//静止缓存为空
                //此处定义了cache key的前缀, 避免公司不同项目之间的key名称冲突.
                .computePrefixWith(new CacheKeyPrefix() {
                    @Override
                    public String compute(String cacheName) {
                        log.info(cacheName);
                        return  "api".concat(":").concat(cacheName).concat(":");
                    }
                })
                //定义key和value的序列化协议, 同时的hash key和hash value也被定义.
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(createJackson2JsonRedisSerializer()));
    }

    /**
     * 创建redisTemplate工具
     * @param factory
     * @return
     */
    @Bean
    public StringRedisTemplate redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        template.setKeySerializer(new StringRedisSerializer());//设置key序列化类，否则key前面会多了一些乱码
        template.setValueSerializer(createJackson2JsonRedisSerializer());//设置value序列化
        template.setHashKeySerializer(createJackson2JsonRedisSerializer());//设置 hash key 序列化
        template.setHashValueSerializer(createJackson2JsonRedisSerializer());//设置 hash value 序列化
        template.setEnableTransactionSupport(true);//设置redis支持数据库的事务

        template.afterPropertiesSet();//初始化设置并且生效

        return template;
    }

    /**
     * 创建redis序列化
     * @return
     */
    private RedisSerializer<Object> createJackson2JsonRedisSerializer() {
        ObjectMapper objectMapper = new ObjectMapper();
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        return jackson2JsonRedisSerializer;
    }

}
