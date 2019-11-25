package com.monster.melon.mapper;

import com.monster.melon.pojo.Event;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface EventMapper {

    @Select("select * from event")
    List<Event> getAllEvents();

    @Select("select * from event limit #{page},#{num}")
    List<Event> getOnePageEvent(@Param(value = "page") Integer page,@Param(value = "num") Integer num);

    @Insert("insert into event (id,name,description,user_id) values(#{id},#{name},#{description},#{userId})")
    @Options(useGeneratedKeys = true,keyColumn = "id")
    void insertEvent(Event event);

    @Delete("delete from event where id = #{id}")
    void deleteEvent(Integer id);



}
