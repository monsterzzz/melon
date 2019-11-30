package com.monster.melon.controller;

import com.monster.melon.pojo.Avatar;
import com.monster.melon.pojo.User;
import com.monster.melon.serializer.Response;
import com.monster.melon.service.AvatarService;
import com.monster.melon.service.UserService;
import com.monster.melon.util.FileUtil;
import com.monster.melon.util.MD5Util;
import com.monster.melon.util.OssService;
import com.monster.melon.util.UserUtil;
import lombok.extern.slf4j.XSlf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.UUID;


@RestController
@RequestMapping("/avatar")
public class AvatarController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private final UserService userService;
    private final AvatarService avatarService;

    public AvatarController(UserService userService,AvatarService avatarService) {
        this.userService = userService;
        this.avatarService = avatarService;
    }

    @PostMapping("")
    public Response upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        Response response = new Response();
        try {
            User user = UserUtil.getCurrentUserByToken(request);
            Response resp = FileUtil.avatarFileCheck(file);
            if(resp.getCode() != 10000){
                return resp;
            }

            Avatar avatar = new Avatar();
            String path;

            if(!avatarService.avatarExists(resp.getMsg())){
                path = OssService.putFile((String) resp.getData(),file.getInputStream());
                avatar.setMd5(resp.getMsg());
                avatar.setPath(path);
                avatarService.insertAvatar(avatar);
            }else{
                avatar = avatarService.getAvatarPathByMd5(resp.getMsg());
                log.info("no upload~");
            }
            avatarService.updateAvatar(avatar.getId(),user.getId());

            response.setCode(10000);
            response.setMsg("上传成功");
            response.setData(avatar.getPath());
            return response;
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        response.setCode(10002);
        response.setMsg("上传失败");
        return response;
    }



}
