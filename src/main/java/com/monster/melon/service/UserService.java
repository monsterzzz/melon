package com.monster.melon.service;

import com.monster.melon.mapper.UserMapper;
import com.monster.melon.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
