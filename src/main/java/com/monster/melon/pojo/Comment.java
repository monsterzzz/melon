package com.monster.melon.pojo;

import java.sql.Timestamp;


public class Comment {
    private Integer id;
    private Integer userId;
    private Integer newsId;
    private String content;
    private Timestamp createTime;
    private Integer replyId;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", userId=" + userId +
                ", newsId=" + newsId +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", replyId=" + replyId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getDate() {
        return createTime;
    }

    public void setDate(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }
}
