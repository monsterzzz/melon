package com.monster.melon.util;


import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.springframework.stereotype.Component;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class MD5Util {

    private static MessageDigest md5;

    static {
        try {
            md5 = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String s){
        byte[] digest = md5.digest(s.getBytes());
        return ByteUtils.toHexString(digest);
    }

    public static String encrypt(File file){
        try {
            InputStream inputStream = new FileInputStream(file);
            byte[] buff = new byte[1024];
            while (true){
                if(inputStream.read(buff) != -1){
                    md5.update(buff);
                }else {
                    inputStream.close();
                    return ByteUtils.toHexString(md5.digest());
                }
            }
        } catch ( IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String encrypt(InputStream inputStream){
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] buff = new byte[1024];
            while (true){
                if(inputStream.read(buff) != -1){
                    md5.update(buff);
                }else {
                    inputStream.close();
                    return ByteUtils.toHexString(md5.digest());
                }
            }
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
        return "";
    }


}
