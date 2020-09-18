/*
 * Copyright (c) 2020. Meowster.com
 * All Rights Reserved.
 */

package com.meowster.avgrgb;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.Color;

import static com.meowster.avgrgb.Config.PATCH_BORDER;
import static com.meowster.avgrgb.Config.PATCH_BORDER_COLOR;
import static com.meowster.avgrgb.Config.PATCH_DIM;

class Patch extends JPanel {

    Patch() {
        setPreferredSize(PATCH_DIM);
        setMinimumSize(PATCH_DIM);
        setMaximumSize(PATCH_DIM);
        setBorder(BorderFactory.createLineBorder(PATCH_BORDER_COLOR, PATCH_BORDER));
        setBackground(Color.BLACK);
        setOpaque(true);
    }
}
