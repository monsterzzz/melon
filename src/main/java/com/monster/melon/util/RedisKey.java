package com.monster.melon.util;

public class RedisKey {

    public static String key(String... args) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : args) {
            stringBuilder.append(s);
            stringBuilder.append(":");
        }
        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        return stringBuilder.toString();
    }



}
