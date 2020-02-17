package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawImages {
    private JFrame drawingFrame;

    public DrawImages(Dimension dimensions, LayoutManager layout) {
        JFrame newFrame = setupFrame(dimensions, layout);
        drawingFrame = newFrame;
    }


    private JFrame setupFrame(Dimension dimensions, LayoutManager layout) {
        JFrame frame = new JFrame();
        frame.setSize(dimensions);
        frame.setLayout(layout);
        frame.setLocationRelativeTo(null);

        return frame;
    }

    private JPanel addImageToPanel(CardInfo card, JPanel panel) {
        JLabel label = new JLabel(card.getMessage());
        label.setIcon(new ImageIcon(getScaledImage(card.getCardImage().cardImage, 120, 120)));
        panel.add(label);

        return panel;
    }

    private JPanel addImagesToPanel(Iterable<CardInfo> cardList) {
        JPanel panel = new JPanel();
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        for (CardInfo card : cardList) {
            addImageToPanel(card, panel);
        }

        panel.validate();
        return panel;
    }

/*    private JPanel addImagesToPanel(Iterable<CardInfo> cardList) {
        JPanel panel = new JPanel();
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        for (CardInfo card : cardList) {
            //change text here
            JLabel label = new JLabel(card.getMessage());
            label.setIcon(new ImageIcon(getScaledImage(card.getCardImage().cardImage, 120, 120)));

            panel.add(label);
        }
        panel.validate();
        return panel;
    }*/

    public void addImagesToFrame(Iterable<CardInfo> cardList) {
        //clear content from frame before adding new
        this.drawingFrame.getContentPane().removeAll();
        this.drawingFrame.repaint();

        if (this.drawingFrame != null) {
            JPanel panel = addImagesToPanel(cardList);
            this.drawingFrame.add(panel);
        }
    }

    public void addImageToFrame(CardInfo card){
        //clear content from frame before adding new
        this.drawingFrame.getContentPane().removeAll();
        this.drawingFrame.repaint();

        if (this.drawingFrame != null) {
            JPanel panel = new JPanel();
            addImageToPanel(card, panel);
            this.drawingFrame.add(panel);
        }
    }


    public void displayFrame(boolean display) {
        if (drawingFrame != null) {
//            drawingFrame.pack();
            drawingFrame.setVisible(display);
        }
    }

    public JFrame getFrame() {
        return drawingFrame;
    }

    private Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }

}
