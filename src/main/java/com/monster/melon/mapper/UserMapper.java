package com.monster.melon.mapper;

import com.monster.melon.pojo.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface UserMapper {

    @Select("select * from user")
    List<User> getAllUser();

    //@Select("select * from user where id = #{id}")
    User getUserById(@Param(value = "id") Integer id);

    @Select("select * from user where username = #{name}")
    User getUserByName(@Param(value = "name") String name);

    @Insert("insert into `user` (id,username,password,nickname,description) values(#{id},#{userName},#{password},#{nickName},#{description})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insertUser(User user);

    @Select("select Count(*) from user where username = #{username} and password = #{password}")
    Integer verify(@Param(value = "username") String username,@Param(value = "password") String password);

    @Update("update user set avatar = #{avatar} where id = #{id}")
    void updateAvatar(@Param("id") Integer id, @Param("avatar") String avatar);

    @Update("update user set nickname = #{nickname} where id = #{userId}")
    void updateNickname(@Param("nickname") String nickname, @Param("userId") Integer userId);

    @Select("select count(*) from user where nickname = #{nickname}")
    Integer nicknameExists(@Param("nickname") String nickname);

    @Update("update user set description = #{description} where id = #{userId}")
    void updateDescription(@Param("description") String description, @Param("userId") Integer userId);

    @Update("update user set password = #{password} where id = #{userId}")
    void updatePassword(@Param("password") String password, @Param("userId") Integer userId);

    @Update("update user set status = 1 where id = #{userId}")
    void updateStatus(@Param("userId") Integer userId);


}
