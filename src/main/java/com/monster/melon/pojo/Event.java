package com.monster.melon.pojo;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Data
public class Event {
    private Integer id;
    private User user;
    private String name;
    private String description;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Set<String> tags;
    private Integer viewNum = 0;
    private Integer newsNum = 0;

}
