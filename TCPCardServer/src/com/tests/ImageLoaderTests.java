package com.tests;

import com.company.*;

import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;


public class ImageLoaderTests {
    public static ImageLoader imageLoader = new ImageLoader();
    public String dirPath;
    DrawImages imageDrawer;
    ArrayList<CardImage> cardList;

    public ImageLoaderTests() {
    }

    public ImageLoaderTests(String dirPath) {
        this.dirPath = dirPath;
        this.imageDrawer = new DrawImages(new Dimension(720, 640), new BorderLayout());
        this.cardList = (ArrayList<CardImage>) this.imageLoader.getImagesFromDir("resources\\PNG");
    }

/*    public void tryDisplayImages(){
        ArrayList<CardInfo> cardInfoList = (ArrayList<CardInfo>)buildCardInfoList(this.cardList);

        this.imageDrawer.addImagesToFrame(cardInfoList);
        this.imageDrawer.displayFrame(true);
    }

    public Iterable<CardInfo> buildCardInfoList(Iterable<CardImage> list){
        ArrayList<CardInfo> cardInfo = new ArrayList<>();
        list.forEach((card) -> {
            cardInfo.add(new CardInfo(card.cardName, card));
        });
        return cardInfo;
    }*/

}
