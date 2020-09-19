/*
 * Copyright (c) 2020. Meowster.com
 * All Rights Reserved.
 */

package com.meowster.avgrgb;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;

class ImageUtils {
    private static final String PATH_PREFIX = "/images/rgb-icon-";
    private static final String PNG = ".png";

    private static String imagePath(int size) {
        return PATH_PREFIX + size + PNG;
    }

    public static Image getIcon(int size) {
        String path = imagePath(size);
        InputStream is = ImageUtils.class.getResourceAsStream(path);
        if (is == null) {
            System.out.println("Warning: could not locate " + path);
        } else {
            try {
                ImageIcon ii = new ImageIcon(ImageIO.read(is));
                return ii.getImage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
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
