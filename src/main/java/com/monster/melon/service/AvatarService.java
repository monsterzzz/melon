package com.monster.melon.service;

import com.monster.melon.mapper.AvatarMapper;
import com.monster.melon.pojo.Avatar;
import com.monster.melon.util.OssService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AvatarService{

    private final AvatarMapper avatarMapper;

    public AvatarService(AvatarMapper avatarMapper) {
        this.avatarMapper = avatarMapper;
    }

    public String getAvatarPath(Integer userId) {
        return avatarMapper.getAvatarPath(userId);
    }

    public void insertAvatar(Avatar avatar){
        avatarMapper.insertAvatar(avatar);
    }

    public Boolean avatarExists(String md5) {
        return avatarMapper.avatarExists(md5) > 0;
    }

    @Transactional
    public void updateAvatar(Integer avatarId,Integer userId){
//        if(avatarMapper.avatarExists(avatar.getMd5()) - 1 <= 0 && avatar.getId() != 1){
//            OssService.deleteFile("avatar/" + avatar.getMd5() + ".jpg");
//        }
        avatarMapper.updateAvatar(avatarId,userId);
    }

    public Avatar getAvatarPathByMd5(String md5){
        return avatarMapper.getAvatarPathByMd5(md5);
    }

}
