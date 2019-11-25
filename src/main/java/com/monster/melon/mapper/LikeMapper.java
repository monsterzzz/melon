package com.monster.melon.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface LikeMapper {

    @Insert("insert into comment_like (user_id,comment_id) values (#{userId},#{commentId})")
    void insertCommentLike(@Param("userId") Integer userId,@Param("commentId") Integer commentId );

    @Delete("delete from comment_like where user_id = #{userId} and comment_id = #{commentId}")
    void deleteCommentLike(@Param("userId") Integer userId,@Param("commentId") Integer commentId);

    @Insert("insert into news_like (user_id,news_id) values (#{userId},#{newsId})")
    void insertNewsLike(@Param("userId") Integer userId,@Param("newsId") Integer newsId );

    @Delete("delete from news_like where user_id = #{userId} and news_id = #{newsId}")
    void deleteNewsLike(@Param("userId") Integer userId,@Param("commentId") Integer newsId);

    @Select("select count(*) from news_like where news_id = #{newsId} and user_id = #{userId}")
    Integer existNewsLike(@Param("newsId") Integer newsId,@Param("userId") Integer userId);

    @Select("select count(*) from comment_like where comment_id = #{commentId} and user_id = #{userId}")
    Integer existCommentLike(@Param("commentId") Integer commentId,@Param("userId") Integer userId);

}
