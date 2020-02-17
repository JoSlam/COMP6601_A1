package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CardImage {
    public String cardName;
    public Image cardImage;
    public String imgURL;
    public File imageFile;

    public CardImage() {
    }

    public CardImage(String cardName, String imgPath, File imageFile) {
        this.cardName = cardName;
        this.imgURL = imgPath;
        this.imageFile = imageFile;
        this.loadImage();
    }

    public void loadImage() {

        if (this.imageFile != null) {
            BufferedImage tempImage = null;
            try {
                tempImage = ImageIO.read(this.imageFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.setCardImage(tempImage);
        }
    }

    public Image reloadImage() {
        BufferedImage tempImage = null;
        File file = (this.imageFile != null) ? this.imageFile : new File(this.imgURL);

        try {
            tempImage = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setCardImage(tempImage);
        return tempImage;
    }

    public Image getCardImage() {
        return this.cardImage;
    }

    public void setCardImage(Image cardImage) {
        this.cardImage = cardImage;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
}


