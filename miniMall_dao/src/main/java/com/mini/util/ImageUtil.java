package com.mini.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @program: miniMall
 * @description: handle thumbnail and return his relative path
 * @author: xin
 * @create: 2020-09-26 10:55
 **/
public class ImageUtil {
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static Random random = new Random();
    private static final Logger log = LoggerFactory.getLogger(ImageUtil.class);

    public static String generateThumbnail(File thumbNail, String targetAddress) {
        String realFileName = getRandomFileName();
        String extensionName = getExtensionName(thumbNail);
        makeDirPath(targetAddress);
        String relativeAddr = targetAddress + realFileName + extensionName;

        log.debug("current relativeAddr is : " + relativeAddr);

        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);

        log.debug("current complete addr is : " + PathUtil.getImgBasePath() + relativeAddr);
        try {
            Thumbnails.of(thumbNail).size(200, 200).
                    watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.2f)
            .outputQuality(0.7f).toFile(dest);
        } catch (IOException e) {
            log.error(e.toString());
            e.printStackTrace();
        }
        return relativeAddr;
    }
    /**
    * @Parame: [multipartFile]
    * @description: transfer  commonsMultipartFile to file
    * @return: java.io.File
    * @author: xin kaining
    * @Date 2020/9/26
    **/
    private static File transferCommonsMultipartFileToFile(CommonsMultipartFile multipartFile){
        File file = new File(multipartFile.getOriginalFilename());
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            log.error(e.toString());
            e.printStackTrace();
        }
        return file;
    }

    /**
     * @Parame: [targetAddress]
     * @description: create folders related to the target path if not exist
     * @return: void
     * @author: xin kaining
     * @Date 2020/9/26
     **/
    private static void makeDirPath(String targetAddress) {
        String realFileParentPath = PathUtil.getImgBasePath();
        File file = new File(realFileParentPath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * @Parame: [thumbNail]
     * @description: get file extension name
     * @return: java.lang.String
     * @author: xin kaining
     * @Date 2020/9/26
     **/
    private static String getExtensionName(File thumbNail) {
        String path = thumbNail.getName();
        return path.substring(path.lastIndexOf("."));
    }

    /**
     * @Parame: []
     * @description: get a random file name
     * @return: java.lang.String
     * @author: xin kaining
     * @Date 2020/9/26
     **/
    private static String getRandomFileName() {
        int rand = random.nextInt(89999) + 10000;
        String time = simpleDateFormat.format(new Date());
        return time + rand;
    }

}
