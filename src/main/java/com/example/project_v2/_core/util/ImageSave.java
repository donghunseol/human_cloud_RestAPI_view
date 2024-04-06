package com.example.project_v2._core.util;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
public class ImageSave {

    //TODO: 이미지 저장 메소드
    public String saveImageFile(MultipartFile imgFilename,String imageName) {
        // 이미지 파일의 저장 경로 설정
        String webImgPath = null;
        if (imgFilename != null && !imgFilename.isEmpty()) {
            String guestImgFilename = UUID.randomUUID() + "_" + imageName;
            Path imgPath = Paths.get("./image/" + guestImgFilename);
            try {
                Files.write(imgPath, imgFilename.getBytes());
                webImgPath = imgPath.toString().replace("\\", "/");
                webImgPath = webImgPath.substring(webImgPath.lastIndexOf("/") + 1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return webImgPath;
    }

    //TODO: 이미지 체크 로직
    public boolean ImageCheck(MultipartFile imgFilename) {
       return  imgFilename != null && !imgFilename.isEmpty();
    }
}
