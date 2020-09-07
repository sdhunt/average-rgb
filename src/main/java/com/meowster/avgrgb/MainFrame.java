package com.meowster.avgrgb;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

    private final MainPanel mainPanel = new MainPanel();

    public MainFrame() {
        setTitle("Average RGB");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(mainPanel);
        pack();
        // TODO: kick off any other loading we need to do
    }
}
