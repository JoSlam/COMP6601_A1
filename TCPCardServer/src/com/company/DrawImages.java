package com.company;

import javax.swing.*;
import java.awt.*;

public class DrawImages {
    private JFrame drawingFrame;

    public DrawImages(Dimension dimensions, LayoutManager layout){
        JFrame newFrame = setupFrame(dimensions, layout);
        drawingFrame = newFrame;
    }


    public JFrame setupFrame(Dimension dimensions, LayoutManager layout){
        JFrame frame = new JFrame();
        frame.setSize(dimensions);
        frame.setLayout(layout);

        return frame;
    }

    public void addImageToFrame(JComponent imageComponent){
        if (drawingFrame != null) drawingFrame.add(imageComponent);
    }

    public void displayFrame(boolean display){
        if (drawingFrame != null) {
            drawingFrame.setVisible(display);
        }
    }

}
