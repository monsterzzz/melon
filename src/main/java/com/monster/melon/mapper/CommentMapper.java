package com.monster.melon.mapper;

import com.monster.melon.pojo.Comment;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CommentMapper {

    @Select("select * from comment where news_id = #{newsId}")
    List<Comment> getNewsAllComments(@Param(value = "newsId") Integer newsId);

    @Select("select \n" +
            "       a.*,count(b.comment_id) as like_num \n" +
            "from comment as a \n" +
            "left join comment_like as b \n" +
            "    on a.id = b.comment_id \n" +
            "where a.user_id = #{userId} \n" +
            "group by a.id;")
    List<Comment> getUserComments(@Param(value = "userId") Integer userId);

    @Select("select \n" +
            "       a.*,count(b.comment_id) as like_num \n" +
            "from comment as a \n" +
            "left join comment_like as b \n" +
            "    on a.id = b.comment_id \n" +
            "where a.news_id = #{newsId} \n" +
            "group by a.id limit #{start},#{end};")
    List<Comment> getNewsPageComments(@Param(value = "newsId") Integer newsId,@Param(value = "start") Integer start,@Param(value = "end") Integer end);

    @Insert("insert into comment (id,user_id,news_id,reply_id,content) values(#{id},#{userId},#{newsId},#{replyId},#{content})")
    @Options(useGeneratedKeys = true,keyColumn = "id")
    Comment insertComment(Comment comment);

    @Delete("delete from comment where id = #{id}")
    void deleteComment(@Param(value = "id") Integer id);

    @Select("select * from comment where id = #{id}")
    Comment getCommentById(@Param(value = "id") Integer id);

    @Select("select count(*) from comment where eventId = #{eventId}")
    Integer getCommonCountByEventId(@Param("eventId") Integer eventId);

    List<Comment> getAllComment();

    Integer getLikeNum(@Param("commentId") Integer commentId);

}
