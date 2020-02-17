package com.company;

import java.io.File;
import java.util.ArrayList;

public class ImageLoader {

    static String stripExtension(String str) {
        if (str == null) return null;
        int pos = str.lastIndexOf(".");
        if (pos == -1) return str;
        return str.substring(0, pos);
    }

    public Iterable<CardImage> getImagesFromDir(String dirPath) {
        File dir = new File(dirPath);
        File[] fileList = dir.listFiles();
        ArrayList<CardImage> cardImageList = new ArrayList<>();

        for (File file : fileList) {
            CardImage img = new CardImage(stripExtension(file.getName()), file.getPath(), file);
            cardImageList.add(img);
        }
        return cardImageList;
    }
}
