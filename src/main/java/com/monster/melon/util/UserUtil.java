package com.monster.melon.util;

import com.monster.melon.pojo.User;
import com.monster.melon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;

@Component
public class UserUtil {


    private static UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        UserUtil.userService = userService;
    }


    public static User getCurrentUserByToken(HttpServletRequest request){
        String userName = JWTUtil.getUserName(request.getHeader("token"));
        return userService.getUserByName(userName);
    }


}
