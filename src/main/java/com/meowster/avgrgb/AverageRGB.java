/*
 * Copyright (c) 2020. Meowster.com
 * All Rights Reserved.
 */

package com.meowster.avgrgb;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class AverageRGB {
    public static final String VERSION = "1.0b";

    public static void main(String[] args) {
        System.out.println(version());

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException
                    | InstantiationException
                    | IllegalAccessException
                    | UnsupportedLookAndFeelException ignored) {
            }
            MainFrame frame = new MainFrame();
            ImageUtils.centerOnScreen(frame);
            frame.setVisible(true);
        });
    }

    public static String version() {
        return "AverageRGB: version " + VERSION;
    }
}
