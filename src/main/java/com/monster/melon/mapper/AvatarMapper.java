package com.monster.melon.mapper;

import com.monster.melon.pojo.Avatar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface AvatarMapper {

    String getAvatarPath(@Param("userId") Integer userId);

    void insertAvatar(Avatar avatar);

    Integer avatarExists(String md5);

    void deleteAvatar(@Param("userId") Integer userId);

    void updateAvatar(@Param("avatarId") Integer avatarId, @Param("userId") Integer userId );

    Avatar getAvatarPathByMd5(@Param("md5") String md5);
}
