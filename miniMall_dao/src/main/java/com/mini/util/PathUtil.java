package com.mini.util;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-09-26 11:01
 **/
public class PathUtil {
    private static String separator = System.getProperty("file.separator");

    public static String getImgBasePath() {
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
            basePath = "D:/mini/img";
        } else {
            basePath = "/home/mini/img";
        }
        basePath = basePath.replace("/", separator);
        return basePath;
    }


    public static String getShopImgPath(long shopId) {
        String imgPath = "/upload/item/shop/" + shopId + "/";
        return imgPath.replace("/", separator);
    }
}
