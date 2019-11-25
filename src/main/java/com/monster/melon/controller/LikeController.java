package com.monster.melon.controller;

import com.monster.melon.pojo.User;
import com.monster.melon.serializer.Response;
import com.monster.melon.service.LikeService;
import com.monster.melon.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/like")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/news/{newsId}")
    public Response newsLike(@PathVariable(value = "newsId") Integer newsId, HttpServletRequest request){
        User user = UserUtil.getCurrentUserByToken(request);
        Response response = new Response();

        if(likeService.existNewsLike(newsId,user.getId()) >= 1){
            response.setCode(90010);
            response.setMsg("error");
            return response;
        }
        likeService.insertNewsLike(user.getId(),newsId);
        response.setCode(90000);
        response.setMsg("success");
        return response;
    }

    @PostMapping("/comment/{commentId}")
    public Response commentLike(@PathVariable(value = "commentId") Integer commentId,HttpServletRequest request){
        Response response = new Response();
        User user = UserUtil.getCurrentUserByToken(request);
        if(likeService.existCommentLike(commentId,user.getId()) >= 1){
            response.setCode(90020);
            response.setMsg("error");
            return response;
        }

        likeService.insertCommentLike(user.getId(),commentId);
        response.setCode(90001);
        response.setMsg("success");
        return response;
    }

    @DeleteMapping("/news/{newsId}")
    public Response deleteNewsLike(@PathVariable(value = "newsId") Integer newsId,HttpServletRequest request){
        Response response = new Response();
        User user = UserUtil.getCurrentUserByToken(request);
        likeService.deleteNewsLike(newsId,user.getId());
        response.setCode(90030);
        response.setMsg("success");
        return response;
    }

    @DeleteMapping("/comment/{commentId}")
    public Response deleteCommentLike(@PathVariable(value = "commentId") Integer commentIdId,HttpServletRequest request){
        Response response = new Response();
        User user = UserUtil.getCurrentUserByToken(request);
        likeService.deleteCommentLike(commentIdId,user.getId());
        response.setCode(90040);
        response.setMsg("success");
        return response;
    }


}
