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
            //for window
            basePath = "D:/mini";
        } else {
            //for mac and linux
            basePath = "/Users/kainingxin/test";
        }
        basePath = basePath.replace("/", separator);
        return basePath;
    }

    //floder path for storing images
    public static String getShopImgPath(long shopId) {
        String imgPath = "/upload/item/shop/" + shopId + "/";
        return imgPath.replace("/", separator);
    }

    public static String getProductImgPath(long productId){
        String imgPath = "/upload/item/product/" + productId+ "/";
        return imgPath.replace("/", separator);
    }
}
