package com.monster.melon.pojo;

import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@ToString
public class Comment {
    private Integer id;
    private Integer userId;
    private Integer newsId;
    private String content;
    private Timestamp createTime;
    private Integer replyId;
    private Integer likeNum;

}
