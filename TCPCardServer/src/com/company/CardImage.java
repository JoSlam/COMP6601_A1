package com.company;

import java.awt.*;

public class CardImage {
    public String cardName;
    public Image cardImage;
    public String imgURL;

    public CardImage() {}

    public CardImage(String cardName, String imgPath) {
        this.cardName = cardName;
        this.imgURL = imgPath;
    }

    public void init(Toolkit tKit) {
        ImageLoader imageLoader = new ImageLoader();
        imageLoader.loadImage(this, tKit);
    }

    public void setCardImage(Image cardImage) {
        this.cardImage = cardImage;
    }

    public Image getCardImage(){
        return this.cardImage;
    }
}


