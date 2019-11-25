package com.monster.melon.service;

import com.monster.melon.mapper.CommentMapper;
import com.monster.melon.pojo.Comment;
import com.monster.melon.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentMapper commentMapper;

    public CommentService(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Value("${comment_page}")
    private Integer pageNum;

    public List<Comment> getNewsAllComments(Integer newsId) {
        return commentMapper.getNewsAllComments(newsId);
    }


    public List<Comment> getUserComments(Integer userId) {
        return commentMapper.getUserComments(userId);
    }


    public List<Comment> getNewsPageComments(Integer newsId, Integer page) {
        page = Math.max(page - 1, 0);
        return commentMapper.getNewsPageComments(newsId,page * pageNum,pageNum);
    }


    public void insertComment(Comment comment) {
        commentMapper.insertComment(comment);
    }

    public void deleteComment(Integer id) {
        commentMapper.deleteComment(id);
    }


    public Comment getCommentById(Integer id) {
        return commentMapper.getCommentById(id);
    }
}
