package com.monster.melon.controller;

import com.monster.melon.error.NickNameExistsException;
import com.monster.melon.pojo.User;
import com.monster.melon.serializer.Response;
import com.monster.melon.service.UserService;
import com.monster.melon.util.Common;
import com.monster.melon.util.MailUtil;
import com.monster.melon.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public Response allUser(){
        return new Response().setData(userService.getAllUser());
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
        boolean flag = false;
        try{
            flag = userService.getUserByName(user.getUserName()) != null;
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        if(flag){
            response.setCode(20002);
            response.setMsg("用户已存在");
            return response;
        }
        userService.insertUser(user);
        response.setCode(20000);
        response.setMsg("success");
        session.setAttribute("checkCode","");
        return response;
    }

    @GetMapping("")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Integer id){
        return userService.getUserById(id);
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

    @GetMapping("/active")
    public Response getActive(HttpServletRequest request){
        Response response = new Response();
        HttpSession session = request.getSession();
        User user = UserUtil.getCurrentUserBySession(request);
        if(user.getStatus() != 0){
            response.setCode(60001);
            response.setMsg("already active");
            return response;
        }
        ArrayList<String> activeCode = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            activeCode.add(Integer.toString(random.nextInt()));
        }
        String ac = activeCode.toString();
        session.setAttribute("activeCode",ac);
        MailUtil.sendTxtMail("瓜站激活",String.format("验证码: %s",ac),user.getEmail());
        response.setCode(60000);
        response.setMsg("success");
        return response;
    }

    @PostMapping("/active")
    public Response userActive(HttpServletRequest request,@RequestParam("ac") String ac){
        User user = UserUtil.getCurrentUserBySession(request);
        String activeCode = (String) request.getSession().getAttribute("activeCode");
        Response response = new Response();
        if(!ac.equals(activeCode)){
            response.setCode(60002);
            response.setMsg("激活码错误");
            return response;
        }
        userService.updateStatus(user.getId());
        response.setCode(60010);
        response.setMsg("激活成功");
        return response;

    }


}
