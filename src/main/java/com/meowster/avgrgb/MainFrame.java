package com.meowster.avgrgb;

import javax.swing.JFrame;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class MainFrame extends JFrame {

    private final MainPanel mainPanel = new MainPanel();
    private final Screen screen = new Screen();

    public MainFrame() {
        setTitle("Average RGB");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addKeyListener(new MyKbd());

        add(mainPanel);
        pack();
        setResizable(false);
    }

    private class MyKbd extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();

            if (code == KeyEvent.VK_SPACE) {
                Point p = MouseInfo.getPointerInfo().getLocation();
                mainPanel.setAperture(screen.fetchArea(p));

                System.out.println("Set Image centered on ("
                        + p.x + "," + p.y + ")");

            } else if (code == KeyEvent.VK_LEFT) {
                System.out.println("Decrease Lasso");
                // TODO: decrease lasso size

            } else if (code == KeyEvent.VK_RIGHT) {
                System.out.println("Increase Lasso");
                // TODO: increase lasso size
            }
        }
    }
}
