package com.monster.melon.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Avatar {

    private Integer id;
    private String md5;
    private String path;
    private Timestamp createTime;




}
