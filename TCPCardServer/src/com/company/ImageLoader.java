package com.company;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class ImageLoader {

    public Toolkit tKit;

    public ImageLoader(){}
    public ImageLoader(Toolkit tKit){
        this.tKit = tKit;
    }

    public Iterable<CardImage> loadAllImages(String dirPath) {
        File dir = new File(dirPath);
        File[] fileList = dir.listFiles();
        ArrayList<CardImage> cardImageList = new ArrayList<>();

        for (File file : fileList) {
            CardImage img = new CardImage(file.getName(), file.getPath());
            this.loadImage(img, tKit);

            cardImageList.add(img);
        }
        return cardImageList;
    }

    public void loadImage(CardImage img, Toolkit tKit) {
        if (img.imgURL != null && !img.imgURL.equals("")) {
            Image tempImg = tKit.getImage(img.imgURL);
            img.setCardImage(tempImg);
        }
    }

    public String removeExtension(String filePath){
        return filePath.replaceFirst("[.][^.]+$", "");
    }
}

