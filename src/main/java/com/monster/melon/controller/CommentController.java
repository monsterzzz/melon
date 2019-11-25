package com.monster.melon.controller;

import com.monster.melon.pojo.Comment;
import com.monster.melon.pojo.User;
import com.monster.melon.serializer.Response;
import com.monster.melon.service.CommentService;
import com.monster.melon.util.UserUtil;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;


    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("")
    public Response insertComment(Comment comment,HttpServletRequest request){
        Response response = new Response();

        User user = UserUtil.getCurrentUserByToken(request);

        comment.setUserId(user.getId());
        commentService.insertComment(comment);

        response.setCode(60000);
        response.setMsg("success");
        return response;

    }


    @GetMapping("/{newsId}/{page}")
    public Response getNewsPageComments(@PathVariable(value = "newsId") Integer newsId,@PathVariable(value = "page") Integer page){

        List<Comment> comments = commentService.getNewsPageComments(newsId,page);
        Response response = new Response();
        response.setCode(60005);
        response.setMsg("success");
        response.setData(comments);
        return response;
    }


    @GetMapping("")
    public Response getUserComments(HttpServletRequest request){
        Response response = new Response();
        User user = UserUtil.getCurrentUserByToken(request);
        response.setCode(60001);
        response.setMsg("success");
        response.setData(commentService.getUserComments(user.getId()));
        return response;
    }


    @DeleteMapping("/{id}")
    public Response deleteComment(@PathVariable(value = "id") Integer id,HttpServletRequest request){
        User user = UserUtil.getCurrentUserByToken(request);
        Comment comment = commentService.getCommentById(id);
        Response response = new Response();
        if (comment.getUserId().equals(user.getId()) || comment.getUserId().equals(9999)){
            commentService.deleteComment(id);
            response.setCode(60002);
            response.setMsg("success");
        }else{
            response.setCode(60003);
            response.setMsg("Error");
        }
        return response;

    }



}
