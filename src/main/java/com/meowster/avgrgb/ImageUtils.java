/*
 * Copyright (c) 2020. Meowster.com
 * All Rights Reserved.
 */

package com.meowster.avgrgb;

import javax.swing.ImageIcon;
import java.awt.Image;

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
}
