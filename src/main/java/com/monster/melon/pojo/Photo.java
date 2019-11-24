package com.monster.melon.pojo;

import java.sql.Timestamp;

public class Photo {
    private Integer id;
    private Integer userId;
    private Integer commentId;
    private String path;
    private Timestamp createTime;

    public Timestamp getCreateDate() {
        return createTime;
    }

    public void setCreateDate(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


}
