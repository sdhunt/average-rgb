/*
 * Copyright (c) 2020. Meowster.com
 * All Rights Reserved.
 */

package com.meowster.avgrgb;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

class Info extends JPanel {
    private static final int FONT_SIZE = 12;
    private static final String FONT_NAME = "Monospaced";
    private static final Font LABEL_FONT =
            new Font(FONT_NAME, Font.PLAIN, FONT_SIZE);

    private static final Dimension DIM =
            new Dimension(Config.INFO_WIDTH, Config.INFO_HEIGHT);
    private static final int PAD = 20;

    private final JLabel rlab = mkLabel("R");
    private final JLabel glab = mkLabel("G");
    private final JLabel blab = mkLabel("B");


    public Info() {
        setBackground(Config.INFO_BG_COLOR);
        setBorder(BorderFactory.createEmptyBorder(PAD, PAD, PAD, PAD));
        setPreferredSize(DIM);
        setMinimumSize(DIM);
        setMaximumSize(DIM);

        setLayout(new GridLayout(1, 1));
        Box box = Box.createVerticalBox();
        add(box);
        box.add(rlab);
        box.add(glab);
        box.add(blab);
    }

    public void update(int centerX, int centerY, int pupilSize) {

    }

    static JLabel mkLabel(String tag) {
        JLabel label = new TaggedLabel(tag);
        label.setFont(LABEL_FONT);
        return label;
    }

}
