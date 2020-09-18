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

class InfoPanel extends JPanel {
    private static final int FONT_SIZE = 12;
    private static final String FONT_NAME = "Monospaced";
    private static final Font LABEL_FONT =
            new Font(FONT_NAME, Font.PLAIN, FONT_SIZE);

    private static final Dimension DIM =
            new Dimension(Config.INFO_WIDTH, Config.INFO_HEIGHT);
    private static final int PAD = 8;

    private final JLabel rlab = mkLabel("R");
    private final JLabel glab = mkLabel("G");
    private final JLabel blab = mkLabel("B");
    private final JLabel rgblab = mkLabel("RGB");

    private Patch patch;


    public InfoPanel() {
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
        box.add(Box.createVerticalStrut(6));
        box.add(rgblab);
    }

    public void updateState(Color avColor) {
        rlab.setText(StringUtils.asHex(avColor.red(), 2));
        glab.setText(StringUtils.asHex(avColor.green(), 2));
        blab.setText(StringUtils.asHex(avColor.blue(), 2));
        rgblab.setText(avColor.hex());
        if (patch != null) {
            patch.setBackground(new java.awt.Color(avColor.toInt()));
        }
    }

    static JLabel mkLabel(String tag) {
        JLabel label = new TaggedLabel(tag);
        label.setFont(LABEL_FONT);
        return label;
    }

    public void setPatch(Patch patch) {
        this.patch = patch;
    }
}
