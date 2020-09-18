/*
 * Copyright (c) 2020. Meowster.com
 * All Rights Reserved.
 */

package com.meowster.avgrgb;

import java.awt.Color;

class Config {
    static final Color APERTURE_BORDER_COLOR = new Color(0x585876);
    static final Color INFO_BG_COLOR = new Color(0xFFF6EE);
    static final Color IRIS_COLOR = Color.BLACK;
    static final float IRIS_ALPHA = 0.5f;

    static final int VERT = 99;

    // NOTE: Aperture and Pupil sizes are all assumed to be odd
    static final int APERTURE_SIZE = 95;
    static final int APERTURE_BORDER = (VERT - APERTURE_SIZE) / 2;
    static final int PUPIL_MAX = APERTURE_SIZE - 10;
    static final int PUPIL_MIN = 5;
    static final int PUPIL_START = APERTURE_SIZE / 2;
    static final int PUPIL_STEP_SMALL = 2;
    static final int PUPIL_STEP_LARGE = 10;
    static final int PAN_STEP_SMALL = 5;
    static final int PAN_STEP_LARGE = 20;

    static final int INFO_WIDTH = 200;
    static final int INFO_HEIGHT = VERT;
}
