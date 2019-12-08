package com.monster.melon.mapper;

import com.monster.melon.pojo.Event;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author monster
 */
@Mapper
@Component
public interface EventMapper {

    /**
     * 获得所有事件
     * @return 事件列表
     */
    @Select("select * from event")
    List<Event> getAllEvents();


    /**
     * 获得一页事件
     * @param page 页数
     * @param num 每页数量
     * @return 事件列表
     */
    List<Event> getOnePageEvent(@Param(value = "page") Integer page,@Param(value = "num") Integer num);

    /**
     * 添加事件
     * @param event 事件对象
     */
    @Insert("insert into event (id,name,description,user_id) values(#{id},#{name},#{description},#{userId})")
    @Options(useGeneratedKeys = true,keyColumn = "id")
    void insertEvent(Event event, @Param("userId") Integer userId);

    @Delete("delete from event where id = #{id}")
    void deleteEvent(Integer id);

    List<Event> getEventsByTag(@Param("tagId") Integer tagId, @Param("page") Integer page, @Param("num") Integer num);


    @Update("update event set view_num = #{viewNum} where id = #{eventId}")
    void updateViewNum(@Param("viewNum") Integer viewNum, @Param("eventId") Integer eventId);


}
