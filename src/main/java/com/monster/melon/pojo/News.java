package com.monster.melon.pojo;

import java.sql.Timestamp;
import java.util.Arrays;

public class News {
    private Integer id;
    private Integer userId;
    private Integer eventId;
    private String content;
    private Timestamp createTime;
    private Media[] Media;
    private Comment[] comments;

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", userId=" + userId +
                ", eventId=" + eventId +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", Media=" + Arrays.toString(Media) +
                ", comments=" + Arrays.toString(comments) +
                '}';
    }

    public com.monster.melon.pojo.Media[] getMedia() {
        return Media;
    }

    public void setMedia(com.monster.melon.pojo.Media[] media) {
        Media = media;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getAddDate() {
        return createTime;
    }

    public void setAddDate(Timestamp createTime) {
        this.createTime = createTime;
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

    public Comment[] getComments() {
        return comments;
    }

    public void setComments(Comment[] comments) {
        this.comments = comments;
    }
}

