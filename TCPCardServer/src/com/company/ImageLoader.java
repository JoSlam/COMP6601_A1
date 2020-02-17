package com.company;

import java.io.File;
import java.util.ArrayList;

public class ImageLoader {

    public Iterable<CardImage> getImagesFromDir(String dirPath) {
        File dir = new File(dirPath);
        File[] fileList = dir.listFiles();
        ArrayList<CardImage> cardImageList = new ArrayList<>();

        for (File file : fileList) {
            CardImage img = new CardImage(file.getName(), file.getPath(), file);
            cardImageList.add(img);
        }
        return cardImageList;
    }
}
