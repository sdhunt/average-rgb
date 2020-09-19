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
    private static final int ICON_SIZE = 32;
    private static final String[] HELP = {
            "------------------------------------------------------",
            "Enter      - capture image; centered on mouse position",
            "Arrows     - pan image; up, down, left, right",
            "              (hold Shift for faster pan)",
            "< and >    - contract/dilate pupil",
            "              (hold Shift for bigger steps)",
            "=          - reset pupil to default size",
            "Spacebar   - copy current color to clipboard",
            "/          - print this help text",
            "a          - about this program",
            "q          - quit",
            "------------------------------------------------------",
    };
    private static final String[] ABOUT = {
            "------------------------------------------------------",
            AverageRGB.version(),
            "",
            "Computes the average color of a small patch of pixels",
            "",
            "https://github.com/sdhunt/average-rgb",
            "(c) 2020 Meowster.com",
            "------------------------------------------------------",
    };

    private final MainPanel mainPanel = new MainPanel();

    public MainFrame() {
        setTitle("Average RGB");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addKeyListener(new MyKbd());
        setIconImage(ImageUtils.getIcon(ICON_SIZE));

        add(mainPanel);
        pack();
        setResizable(false);
        printHint();
    }

    private void printHint() {
        System.out.println("Press '/' key for help");
    }

    private void printHelp() {
        for (String s : HELP) {
            System.out.println(s);
        }
    }

    private void printAbout() {
        for (String s : ABOUT) {
            System.out.println(s);
        }
    }


    private class MyKbd extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();
            boolean shifted = (e.getModifiersEx() & SHIFT_DOWN_MASK) > 0;

            if (code == KeyEvent.VK_ENTER) {
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

            } else if (code == KeyEvent.VK_SPACE) {
                mainPanel.copyToClipboard();

            } else if (code == KeyEvent.VK_SLASH) {
                printHelp();

            } else if (code == KeyEvent.VK_A) {
                printAbout();

            } else if (code == KeyEvent.VK_Q) {
                System.exit(0);

            }
        }
    }
}
