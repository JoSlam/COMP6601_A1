package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Toolkit tKit = Toolkit.getDefaultToolkit();
        ImageLoader imgLoader = new ImageLoader(tKit);

        ArrayList<CardImage> cardImages = (ArrayList<CardImage>) imgLoader.loadAllImages("resources\\JPEG");
        DrawImages imageDrawer = new DrawImages(new Dimension(640, 480), new GridLayout(13, 4));

        CardImage toPrint = cardImages.get(0);
        System.out.println("the image: " + toPrint);
        /*imageDrawer.addImageToFrame(toPrint);
        imageDrawer.displayFrame(true);*/
    }
}
