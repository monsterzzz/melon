package com.monster.melon.controller;


import com.monster.melon.pojo.User;
import com.monster.melon.serializer.Response;
import com.monster.melon.service.UserService;
import com.monster.melon.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.plugin2.message.Serializer;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class TokenController  {

    private final UserService userService;

    public TokenController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public Response login(User user){
        if(userService.verify(user.getUserName(),user.getPassword())){
            Response resp = new Response();
            resp.setCode(50000);
            resp.setData(JWTUtil.sign(user));
            resp.setMsg("success");
            return resp;
        }
        Response resp = new Response();
        resp.setCode(50001);
        resp.setMsg("error account or password");
        return resp;
    }

    @DeleteMapping("")
    public Response logout(){
        Response response = new Response();
        response.setCode(50002);
        response.setMsg("success");
        return response;
    }

}
