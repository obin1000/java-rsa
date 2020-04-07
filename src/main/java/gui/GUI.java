package main.java.gui;

import javax.swing.*;

public class GUI {
    private int width = 800;
    private int height = 600;
    private JFrame frame;
    private JSplitPane splitpane;
    private JPanel encode;
    private JPanel decode;

    public GUI(){
        // create a new frame
        frame = new JFrame("frame");
        encode = new Encode();
        decode = new Decode();

        // create a splitpane
        splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, encode, decode);
        splitpane.setDividerLocation(width/2);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // add panel
        frame.add(splitpane);

        // set the size of frame
        frame.setSize(width, height);

        frame.setVisible(true);
    }
}
