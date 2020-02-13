package com.company;

import java.awt.*;
import java.net.*;
import java.io.File;
import java.util.ArrayList;

public class ImageLoader extends Canvas {

    public static Toolkit tKit = Toolkit.getDefaultToolkit();

    public void paint(Graphics g) {
    }

    public Iterable<CardImage> loadAllImages(URI dirPath) {
        //get all files in directory
        File dir = new File(dirPath);
        File[] fileList = dir.listFiles();
        ArrayList<CardImage> cardImageList = new ArrayList<CardImage>();

        for (File file : fileList) {
            CardImage img = new CardImage(file.getName(), removeExtension(file.getPath()));
            img.init(tKit);
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

