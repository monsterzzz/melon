package com.monster.melon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.monster.melon.pojo.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;


@Component
//@ConfigurationProperties("jwt")
public class JWTUtil {

    private static long EXPIRE_TIME;
    private static String secret;

    @Value("${expire_time}")
    public void setExpireTime(Integer minute) {
        EXPIRE_TIME = minute * 60 * 1000;
    }

    @Value("${secret}")
    public void setSecret(String secret) {
        System.out.println(secret + "                   !!!!!!!!!!");
        JWTUtil.secret = secret;
    }

    // 加密
    public static String sign(User user){
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create().withClaim("userName",user.getId()).withExpiresAt(date).sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getUserName(String token){
        try{
            return JWT.decode(token).getClaim("userName").asString();
        }catch (JWTDecodeException e){
            return null;
        }
    }

    //解密
    public static boolean verify(String token,String userName){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier jwtVerifier = JWT.require(algorithm).withClaim("userName",userName).build();
            DecodedJWT jwt = jwtVerifier.verify(token);
            return true;
        } catch (UnsupportedEncodingException | JWTDecodeException e) {
            e.printStackTrace();
            return false;
        }

    }




}
