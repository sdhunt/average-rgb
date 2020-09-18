package com.meowster.avgrgb;

import javax.swing.JPanel;
import java.awt.Dimension;

class Info extends JPanel {

    private static final Dimension DIM =
            new Dimension(Config.INFO_WIDTH, Config.INFO_HEIGHT);

    public Info() {
        setBackground(Config.INFO_BG_COLOR);

        setPreferredSize(DIM);
        setMinimumSize(DIM);
        setMaximumSize(DIM);
    }

    // TODO: add informational JLabels

}
