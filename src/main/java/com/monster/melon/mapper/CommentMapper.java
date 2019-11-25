package com.monster.melon.mapper;

import com.monster.melon.pojo.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CommentMapper {

    @Select("select * from comment where news_id = #{newsId}")
    List<Comment> getNewsAllComments(@Param(value = "newsId") Integer newsId);

    @Select("select * from comment where user_id = #{userId}")
    List<Comment> getUserComments(@Param(value = "userId") Integer userId);

    @Select("select * from comment where news_id = #{newsId} limit #{start},#{end}")
    List<Comment> getNewsPageComments(@Param(value = "newsId") Integer newsId,@Param(value = "start") Integer start,@Param(value = "end") Integer end);

    @Insert("insert into comment (id,user_id,news_id,reply_id,content) values(#{id},#{userId},#{newsId},#{replyId},#{content})")
    @Options(useGeneratedKeys = true,keyColumn = "id")
    void insertComment(Comment comment);

    @Delete("delete from comment where id = #{id}")
    void deleteComment(@Param(value = "id") Integer id);

    @Select("select * from comment where id = #{id}")
    Comment getCommentById(@Param(value = "id") Integer id);


}
