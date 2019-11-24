package com.monster.melon.controller;

import com.monster.melon.pojo.User;
import com.monster.melon.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public String signIn(User user){
        System.out.println(user);
        userService.insertUser(user);
        return "success";
    }

    @GetMapping("")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @PutMapping("/avatar")
    public String updateUserAvatar(){
        return "success";
    }

    @PutMapping("/nickname")
    public String updateUserNickName(){
        return "success";
    }

    @PutMapping("/description")
    public String updateUserDescription(){
        return "success";
    }

    @PutMapping("/password")
    public String updateUserPassword(){
        return "success";
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable(value = "id") Integer id){
        return "success";
    }


}
