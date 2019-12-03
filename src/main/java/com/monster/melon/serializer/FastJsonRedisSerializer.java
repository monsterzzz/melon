package com.monster.melon.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import sun.plugin2.message.Serializer;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FastJsonRedisSerializer<T> implements RedisSerializer<T> {

    public static final Charset DefaultCharset = StandardCharsets.UTF_8;
    private Class<T> tClass;

    public FastJsonRedisSerializer(Class<T> tClass ){
        super();
        this.tClass = tClass;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if(t == null){
            return new byte[0];
        }
        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DefaultCharset);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if(bytes == null || bytes.length <= 0){
            return null;
        }
        String s = new String(bytes,DefaultCharset);
        return JSON.parseObject(s,tClass);
    }
}
