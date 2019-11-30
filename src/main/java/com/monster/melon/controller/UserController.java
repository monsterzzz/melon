package com.monster.melon.controller;

import com.monster.melon.exception.NickNameExistsException;
import com.monster.melon.pojo.User;
import com.monster.melon.serializer.Response;
import com.monster.melon.service.UserService;
import com.monster.melon.util.Common;
import com.monster.melon.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public Response signIn(User user,HttpServletRequest request,@RequestParam("checkCode") String checkCode){
        HttpSession session = request.getSession();
        Response response = new Response();
        log.info(checkCode);
        if(session.getAttribute("checkCode") == null){
            response.setCode(20003);
            response.setMsg("请获取验证码后重试");
            return response;
        }

        String recvCode = session.getAttribute("checkCode").toString();
        if(!recvCode.toLowerCase().equals(checkCode)){
            response.setCode(20001);
            response.setMsg("验证码错误");
            return response;
        }
        if(userService.getUserByName(user.getUserName()) != null){
            response.setCode(20002);
            response.setMsg("用户已存在");
            return response;
        }
        userService.insertUser(user);
        response.setCode(20000);
        response.setMsg("success");
        return response;
    }

    @GetMapping("")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @PutMapping("/nickname")
    public Response updateUserNickName(@RequestParam("nickname") String nickname,HttpServletRequest request){
        Response response = new Response();
        User user = UserUtil.getCurrentUserBySession(request);
        try{
            if(Common.checkLength(nickname,2,8)){
                userService.updateNickname(nickname,user.getId());
                response.setCode(20010);
                response.setMsg("success");
                response.setData(nickname);
                return response;
            }
            response.setCode(20012);
            response.setMsg("nickname size error!");
            return response;

        } catch (NickNameExistsException e) {
            response.setCode(20011);
            response.setMsg("error");
            e.printStackTrace();
            return response;
        }

    }

    @PutMapping("/description")
    public Response updateUserDescription(@RequestParam("description") String description,HttpServletRequest request){
        Response response = new Response();
        User user = UserUtil.getCurrentUserBySession(request);
        if (Common.checkLength(description,2,254)){
            userService.updateDescription(description,user.getId());
            response.setCode(20020);
            response.setMsg("success");
            response.setData(description);
        }else {
            response.setCode(20021);
            response.setMsg("error size");
        }
        return response;
    }

    @PutMapping("/password")
    public Response updateUserPassword(HttpServletRequest request,@RequestParam("password") String password,@RequestParam("re_password") String re_password){
        Response response = new Response();
        User user = UserUtil.getCurrentUserBySession(request);
        if(!password.equals(re_password)){
            response.setCode(20031);
            response.setMsg("两次输入密码不一致");
            return response;
        }

        if (Common.checkLength(password,6,16)){
            userService.updatePassword(password,user.getId());
            response.setCode(20030);
            response.setMsg("success");
        }else {
            response.setCode(20032);
            response.setMsg("error size");
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable(value = "id") Integer id){
        return "success";
    }


}
