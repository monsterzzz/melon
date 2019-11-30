package com.monster.melon.mapper;


import com.monster.melon.pojo.Tag;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @author monster
 */
@Mapper
@Component
public interface TagMapper {

    /**
     * @param tag 标签；
     *
     */
    void insertTag(Tag tag);

    //@Select("select a.*,b.* from event_tag as a left join tag as b on a.tag_id = b.id where a.event_id = #{eventId};")
    List<Tag> getAllTag();

    Set<String> getEventTag(@Param("eventId") Integer eventId);


}
