/*
 * Copyright (c) 2020. Meowster.com
 * All Rights Reserved.
 */

package com.meowster.avgrgb;

import static com.meowster.avgrgb.StringUtils.asHex;

/**
 * Our notion of color, encapsulating the ubiquitous alpha-red-green-blue (ARGB)
 * color model, with 8 bits per component.
 *
 * @author Simon Hunt
 */
class Color {
    private static final int FF = 0xff;
    private static final int BYTE_LEN = 8;
    private static final int MAX_BYTE = 255;
    private static final int RGB_MASK = 0xffffff;

    /**
     * Totally transparent.
     */
    public static final Color TRANSPARENT = new Color(0x0);

    /**
     * The color white.
     */
    public static final Color WHITE = new Color(0xffffffff);

    private final int raw;
    private final int alpha;
    private final int red;
    private final int green;
    private final int blue;

    /**
     * Creates a color instance from the given int value, where the color
     * components are encoded as: {@code 0xAARRGGBB}.
     *
     * @param val the encoded value
     */
    public Color(int val) {
        int tmp = val;
        raw = tmp;
        blue = tmp & FF;
        tmp >>= BYTE_LEN;
        green = tmp & FF;
        tmp >>= BYTE_LEN;
        red = tmp & FF;
        tmp >>= BYTE_LEN;
        alpha = tmp & FF;
    }

    /**
     * Creates a color instance from the given component values. Note that the
     * values are "clamped" to the range 0 .. 255; that is to say, values less
     * than 0 will be treated as 0, and values more than 255 will be treated as
     * 255.
     *
     * @param alpha the alpha (opacity) component
     * @param red   the red component
     * @param green the green component
     * @param blue  the blue component
     */
    public Color(int alpha, int red, int green, int blue) {
        this.alpha = clampByte(alpha);
        this.red = clampByte(red);
        this.green = clampByte(green);
        this.blue = clampByte(blue);
        raw = computeRaw();
    }

    private int clampByte(int value) {
        return value < 0 ? 0 : (Math.min(value, MAX_BYTE));
    }

    private int computeRaw() {
        return (alpha << 24) | (red << 16) | (green << 8) | blue;
    }

    @Override
    public String toString() {
        return "Color{" + hex() + '}';
    }

    /**
     * Returns the alpha component. Can be thought of as the "opacity", that is,
     * the amount of opaqueness (from 0 .. 255).
     *
     * @return the alpha component
     */
    public int alpha() {
        return alpha;
    }

    /**
     * Returns the transparency. Can be thought of as the opposite of the
     * "opacity" or {@link #alpha()}. The amount of transparency is
     * from 0 .. 255.
     *
     * @return the transparency
     */
    public int transparency() {
        return FF - alpha;
    }

    /**
     * Returns the red component (from 0 .. 255).
     *
     * @return the red component
     */
    public int red() {
        return red;
    }

    /**
     * Returns the green component (from 0 .. 255).
     *
     * @return the green component
     */
    public int green() {
        return green;
    }

    /**
     * Returns the blue component (from 0 .. 255).
     *
     * @return the blue component
     */
    public int blue() {
        return blue;
    }

    /**
     * Returns the color as a hex string of the form {@code "0xRRGGBB"}.
     * Alpha component is ignored.
     *
     * @return the color as a hex string
     */
    public String hex() {
        return asHex(raw & RGB_MASK, 6);
    }

    /**
     * Returns the color as a hex string of the form {@code "0xAARRGGBB"}.
     *
     * @return the color as a hex string
     */
    public String hexa() {
        return asHex(raw, 8);
    }

    /**
     * Returns the color encoded in an int value: {@code 0xAARRGGBB}.
     *
     * @return the color as an int
     */
    public int toInt() {
        return raw;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Color color = (Color) o;
        return raw == color.raw;
    }

    @Override
    public int hashCode() {
        return raw;
    }


    /**
     * Treats the input integers as ARGB color values; computes and returns the
     * average color.
     *
     * @param argb the color values to average
     * @return the computed average color
     */
    public static Color averageColor(int... argb) {
        if (argb == null || argb.length == 0) {
            return TRANSPARENT;
        }
        if (argb.length == 1) {
            return new Color(argb[0]);
        }

        int a = 0;
        int r = 0;
        int g = 0;
        int b = 0;

        for (int c : argb) {
            Color color = new Color(c);
            a += color.alpha;
            r += color.red;
            g += color.green;
            b += color.blue;
        }
        final int n = argb.length;
        return new Color(a / n, r / n, g / n, b / n);
    }
}
