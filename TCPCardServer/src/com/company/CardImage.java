package com.company;

import javax.swing.*;
import java.awt.*;

public class CardImage extends JComponent {
    public String cardName;
    public Image cardImage;
    public String imgURL;

    public CardImage() {}
    public CardImage(String cardName, String imgPath) {
        this.cardName = cardName;
        this.imgURL = imgPath;
    }

    public void paint(Graphics g) {
        super.paint(g);

        if (this.cardImage != null){
            g.drawImage(this.cardImage, 0,0, 120, 120, null);
        }
    }

    public void setCardImage(Image cardImage) {
        this.cardImage = cardImage;
    }
    public Image getCardImage(){
        return this.cardImage;
    }
}


