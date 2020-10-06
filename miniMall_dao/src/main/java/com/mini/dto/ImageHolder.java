package com.mini.dto;

import java.io.File;
import java.io.InputStream;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-10-04 18:00
 **/
public class ImageHolder {
    private String imageName;
    private File image;

    public ImageHolder(String imageName, File image) {
        this.imageName = imageName;
        this.image = image;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }
}
