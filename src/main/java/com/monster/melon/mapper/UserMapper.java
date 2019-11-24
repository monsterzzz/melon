package com.monster.melon.mapper;

import com.monster.melon.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface UserMapper {

    @Select("select * from user")
    List<User> getAllUser();

    @Select("select * from user where id = #{id}")
    User getUserById(@Param(value = "id") Integer id);

    @Select("select * from user where name = #{name}")
    User getUserByName(@Param(value = "name") String name);

    @Insert("insert into `user` (id,username,password,nickname,description,avatar) values(#{id},#{userName},#{password},#{nickName},#{description},#{avatar})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insertUser(User user);

    @Select("select Count(*) from user where username = #{username} and password = #{password}")
    Integer verify(@Param(value = "username") String username,@Param(value = "password") String password);

}
