package com.meowster.avgrgb;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

class Screen {

    private Bot bot = null;

    public Screen() {
        try {
            bot = new Bot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage fetchArea(Point p) {
        int size = Config.APERTURE_SIZE;
        int half = size / 2;
        int x = p.x - half;
        int y = p.y - half;
        Rectangle area = new Rectangle(x, y, size, size);
        return bot.createScreenCapture(area);
    }

    // our internal Robot instance
    private static class Bot extends Robot {
        public Bot() throws AWTException {
        }
    }
}
