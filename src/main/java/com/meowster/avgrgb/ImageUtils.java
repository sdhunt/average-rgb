/*
 * Copyright (c) 2020. Meowster.com
 * All Rights Reserved.
 */

package com.meowster.avgrgb;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

class ImageUtils {
    private static final String PATH_PREFIX = "images/rgb-icon-";
    private static final String PNG = ".png";

    public static String imagePath(int size) {
        return PATH_PREFIX + size + PNG;
    }

    public static Image getIcon(int size) {
        String path = imagePath(size);
        System.out.println("Icon path: " + path);
        ImageIcon ii = new ImageIcon(path);
        System.out.println("ImageIcon: " + ii);
        Image im = ii.getImage();
        System.out.println("Image: " + im);
        return im;
    }

    public static void centerOnScreen(JFrame frame) {
        int fw = frame.getWidth();
        int fh = frame.getHeight();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerLeft = (screenSize.width - fw) / 2;
        int centerTop = (screenSize.height - fh) / 2;
        frame.setLocation(centerLeft, centerTop);
    }
}
