package com.monster.melon.service;

import com.monster.melon.mapper.LikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    private final LikeMapper likeMapper;

    public LikeService(LikeMapper likeMapper) {
        this.likeMapper = likeMapper;
    }

    public void insertCommentLike(Integer userId, Integer commentId) {
        likeMapper.insertCommentLike(userId,commentId);
    }

    public void deleteCommentLike(Integer commentId,Integer userId) {
        likeMapper.deleteCommentLike(userId,commentId);
    }

    public void insertNewsLike(Integer userId, Integer newsId) {
        likeMapper.insertNewsLike(userId, newsId);
    }

    public void deleteNewsLike(Integer userId, Integer newsId) {
        likeMapper.deleteNewsLike(userId,newsId);
    }

    public Integer existNewsLike(Integer newsId, Integer userId) {
        return likeMapper.existNewsLike(newsId, userId);
    }

    public Integer existCommentLike(Integer commentId, Integer userId) {
        return likeMapper.existCommentLike(commentId, userId);
    }
}
