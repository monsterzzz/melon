package com.monster.melon.mapper;

import com.monster.melon.pojo.News;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface NewsMapper {

    @Select("select * from news where event_id = #{eventId}")
    List<News> getEventAllNews(@Param(value = "eventId") Integer eventId);

    @Select("select * from news where user_id = #{userId}")
    List<News> getUserNews(@Param(value = "userId") Integer userId);

    @Select("select a.*,count(b.id) as comment_num,like_num from news as a\n" +
            "left join comment as b on (b.news_id = a.id)\n" +
            "left join\n" +
            "    (select news_id,count(*) as like_num from news_like group by news_like.news_id)\n" +
            "        as c on a.id = c.news_id\n" +
            "where admin_opt = 0 and event_id = #{eventId}\n" +
            "group by a.id\n" +
            "limit #{start},#{end};")
    List<News> getOnePageNews(@Param(value = "eventId") Integer newsId,@Param(value = "start") Integer start,@Param(value = "end") Integer end);

    @Insert("insert into news (id,user_id,event_id,admin_opt,happen_time,content) values(#{id},#{userId},#{eventId},#{adminOpt},#{happenTime},#{content})")
    @Options(useGeneratedKeys = true,keyColumn = "id")
    News insertNews(News news);

    @Delete("delete from news where id = #{id}")
    void deleteNews(@Param(value = "id") Integer id);

    @Select("select a.*,count(b.content) as comment_num from news as a left join comment as b on a.id = b.news_id where a.id = #{id};")
    News getNewsById(@Param(value = "id") Integer id);

    @Select("select count(*) from news where event_id = #{eventId}")
    Integer getNewsCountByEventId(@Param("eventId") Integer eventId);

    List<News> getAllNews();

    Integer getLikeNum(@Param("newsId") Integer newsId);

}
