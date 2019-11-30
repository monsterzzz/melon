package com.monster.melon.pojo;



import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@ToString
public class User {

    private Integer id;
    private String userName;
    private String nickName = UUID.randomUUID().toString().replaceAll("-","").substring(0,15);
    private String password;
    private String avatar;
    private String description = "现在还是个谜团哦";
    private Integer status;
    private Timestamp createTime;

}
