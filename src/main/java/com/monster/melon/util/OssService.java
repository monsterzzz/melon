package com.monster.melon.util;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.*;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@Slf4j
@Component
public class OssService {

    private static String endpoint;
    private static String accessKeyId;
    private static String accessKeySecret;
    private static String bucketName;

    @Value("${endpoint}")
    public void setEndpoint(String endpoint) {
        log.info(endpoint);
        OssService.endpoint = endpoint;
    }

    @Value("${accessKeyId}")
    public void setAccessKeyId(String accessKeyId) {
        OssService.accessKeyId = accessKeyId;
    }

    @Value("${accessKeySecret}")
    public void setAccessKeySecret(String accessKeySecret) {
        OssService.accessKeySecret = accessKeySecret;
    }

    @Value("${bucketName}")
    public void setBucketName(String bucketName) {
        OssService.bucketName = bucketName;
    }

    public static OSS getOssClient(){
        return new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);
    }

    public static String putFile(String fileName, File file){
        OSS ossClient = getOssClient();
        PutObjectResult result = ossClient.putObject(bucketName,fileName,file);
        if(result == null){
            return null;
        }
        return result.getETag();
    }

    public static String putFile(String fileName, InputStream inputStream){
        OSS ossClient = getOssClient();
        Date date = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 );
        PutObjectRequest request = new PutObjectRequest(bucketName,fileName,inputStream);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("/image/jpeg");
        request.setMetadata(metadata);
        PutObjectResult result = ossClient.putObject(request);
        if(result == null){
            return null;
        }
        ossClient.shutdown();
        String[] urls = endpoint.split("//");
        return String.format("%s//%s.%s/%s",urls[0],bucketName,urls[1],fileName);
    }

    public static void deleteFile(String filePath){
        OSS ossClient = getOssClient();
        ossClient.deleteObject(bucketName,filePath);
        ossClient.shutdown();
    }


}
