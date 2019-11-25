package com.monster.melon.mapper;

import com.monster.melon.pojo.Event;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EventMapper {

    @Select("select * from event")
    List<Event> getAllEvents();

    @Select("select * from event limit #{page},#{page}+5")
    List<Event> getOnePageEvent(@Param(value = "page") Integer page);

//    @Insert("insert into event")
//    void insertEvent(Event event);



}
