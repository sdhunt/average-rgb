/*
 * Copyright (c) 2020. Meowster.com
 * All Rights Reserved.
 */

package com.meowster.avgrgb;

import javax.swing.JFrame;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static java.awt.event.InputEvent.SHIFT_DOWN_MASK;

class MainFrame extends JFrame {
    private final MainPanel mainPanel = new MainPanel();

    public MainFrame() {
        setTitle("Average RGB");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addKeyListener(new MyKbd());
        setIconImage(ImageUtils.getIcon(16));

        add(mainPanel);
        pack();
        setResizable(false);
    }

    private class MyKbd extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();
            boolean shifted = (e.getModifiersEx() & SHIFT_DOWN_MASK) > 0;

            if (code == KeyEvent.VK_SPACE) {
                Point p = MouseInfo.getPointerInfo().getLocation();
                mainPanel.setApertureCenteredAt(p);

            } else if (code == KeyEvent.VK_EQUALS) {
                mainPanel.resetPupil();

            } else if (code == KeyEvent.VK_COMMA) {
                mainPanel.contractPupil(shifted);

            } else if (code == KeyEvent.VK_PERIOD) {
                mainPanel.dilatePupil(shifted);

            } else if (code == KeyEvent.VK_UP) {
                mainPanel.panUp(shifted);

            } else if (code == KeyEvent.VK_DOWN) {
                mainPanel.panDown(shifted);

            } else if (code == KeyEvent.VK_LEFT) {
                mainPanel.panLeft(shifted);

            } else if (code == KeyEvent.VK_RIGHT) {
                mainPanel.panRight(shifted);
            }
        }
    }
}
