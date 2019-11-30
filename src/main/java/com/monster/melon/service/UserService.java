package com.monster.melon.service;


import com.monster.melon.exception.NickNameExistsException;
import com.monster.melon.mapper.UserMapper;
import com.monster.melon.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UserService {


    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    public User getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    public Boolean verify(String username,String password){
        return userMapper.verify(username, password) > 0;
    }

    public void updateAvatar(Integer id, String avatar){
        userMapper.updateAvatar(id,avatar);
    };

    public void updateNickname(String nickname,Integer userId) throws NickNameExistsException {
        if(userMapper.nicknameExists(nickname) >= 1){
            throw new NickNameExistsException("nickname " + nickname +  "already exists!");
        }
        userMapper.updateNickname(nickname,userId);
    }

    public void updateDescription(String description, @Param("userId") Integer userId){
        userMapper.updateDescription(description,userId);
    }

    public void updatePassword(String password,Integer userId){
        userMapper.updatePassword(password,userId);
    }

}
