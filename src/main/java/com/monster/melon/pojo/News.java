package com.monster.melon.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.sql.Timestamp;
import java.util.Arrays;


public class News {
    private Integer id;
    private Integer userId;
    private Integer eventId;
    private String content;
    private Integer happenTime = 0;
    private Integer adminOpt = 0;
    private Integer LikeNum = 0;
    private Integer commentNum = 0;
    private Timestamp createTime;
    private Media[] Media;

    public Integer getLikeNum() {
        return LikeNum;
    }

    public void setLikeNum(Integer likeNum) {
        LikeNum = likeNum;
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

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getHappenTime() {
        return happenTime;
    }

    public void setHappenTime(Integer happenTime) {
        this.happenTime = happenTime;
    }

    public Integer getAdminOpt() {
        return adminOpt;
    }

    public void setAdminOpt(Integer adminOpt) {
        this.adminOpt = adminOpt;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public com.monster.melon.pojo.Media[] getMedia() {
        return Media;
    }

    public void setMedia(com.monster.melon.pojo.Media[] media) {
        Media = media;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", userId=" + userId +
                ", eventId=" + eventId +
                ", content='" + content + '\'' +
                ", happen_time=" + happenTime +
                ", adminOpt=" + adminOpt +
                ", createTime=" + createTime +
                ", Media=" + Arrays.toString(Media) +
                ", commentNum=" + commentNum +
                '}';
    }
}

