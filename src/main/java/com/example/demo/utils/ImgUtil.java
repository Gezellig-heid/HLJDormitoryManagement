package com.example.demo.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 图片工具类
 */

@Component
public class ImgUtil {
    public final static String IMG_PATH_PREFIX = "avatars";

    public final static String REPAIR_IMG_PATH_PREFIX = "repair";

    //默认头像路径
    public final static String DEFAULT_IMG_PATH = "assets/images/avatars/profile-pic.jpg";

    public static File getImgDirFile(){
        //构建头像上传的路径
        String fileDirPath = new String("src/main/resources/static/" + IMG_PATH_PREFIX);
        File fileDir = new File(fileDirPath);
        return fileDir;
    }

    public static File getRepairImgDirFile(){
        String fileDirPath = new String("src/main/resources/static/" + REPAIR_IMG_PATH_PREFIX);
        File fileDir = new File(fileDirPath);
        return fileDir;
    }




    public static String saveImg(MultipartFile img, String fileName){
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        UUID uuid = UUID.randomUUID();
        fileName = uuid + suffixName;        //生成图片名称
        File filePath = ImgUtil.getImgDirFile();
        File dest = new File(filePath.getAbsolutePath() + File.separator + fileName);
        try {
            img.transferTo(dest);
            return IMG_PATH_PREFIX+"/"+fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String saveRepairImg(MultipartFile img, String fileName){
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        UUID uuid = UUID.randomUUID();
        fileName = uuid + suffixName;        //生成图片名称
        File filePath = ImgUtil.getRepairImgDirFile();
        File dest = new File(filePath.getAbsolutePath() + File.separator + fileName);
        try {
            img.transferTo(dest);
            return REPAIR_IMG_PATH_PREFIX+"/"+fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
