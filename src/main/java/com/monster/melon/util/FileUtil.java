package com.monster.melon.util;


import com.monster.melon.pojo.User;
import com.monster.melon.serializer.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Slf4j
@Component
public class FileUtil {

    private static Set<String> fileType = new HashSet<>();
    static {
        fileType.add("jpg");
        fileType.add("jpeg");
        fileType.add("png");
    }

    public static Response avatarFileCheck(MultipartFile file) throws IOException {
        Response response = new Response();
        if (file.isEmpty()) {
            response.setCode(10001);
            response.setMsg("文件为空");
            return response;
        }
        String fileName = file.getOriginalFilename();
        String currentFileType = fileName.substring(fileName.lastIndexOf(".")+1);
        log.info(currentFileType);
        if( !fileType.contains(currentFileType)){
            response.setCode(10002);
            response.setMsg("文件类型错误");
            return response;
        }
        String md5 = MD5Util.encrypt(file.getInputStream());

        response.setCode(10000);
        response.setMsg(md5);
        response.setData("avatar/" + md5  + "." + currentFileType );

        return response;
    }

}
