/*
 * Copyright (c) 2020. Meowster.com
 * All Rights Reserved.
 */

package com.meowster.avgrgb;

/**
 * A set of utility methods dealing with Strings.
 *
 * @author Simon Hunt
 */
public class StringUtils {
    private static final String OX = "0x";

    /**
     * Returns the long value represented by the given hex string. Leading
     * {@code "0x"} is stripped if present.
     * <pre>
     *     long a1 = Utils.hexAsLong("0x0a");
     *     long a2 = Utils.hexAsLong("0a");
     * </pre>
     *
     * @param s the hex string to evaluate
     * @return the long value
     * @throws NumberFormatException if input is not valid
     */
    public static long hexAsLong(String s) {
        String h = s.startsWith("0x") ? s.substring(2) : s;
        return Long.parseLong(h, 16);
    }

    /**
     * Returns the integer value represented by the given hex string. Leading
     * {@code "0x"} is stripped if present.
     * <pre>
     *     long a1 = Utils.hex("0x0a");
     *     long a2 = Utils.hex("0a");
     * </pre>
     *
     * @param s the hex string to evaluate
     * @return the integer value
     * @throws NumberFormatException if input is not valid
     */
    public static int hex(String s) {
        String h = s.startsWith("0x") ? s.substring(2) : s;
        return Integer.parseInt(h, 16);
    }

    /**
     * Returns a hex string representation of the given long value.
     *
     * @param val the value
     * @return a string representing the value in hex
     */
    public static String asHex(long val) {
        String h = Long.toHexString(val);
        return OX + h;
    }

    /**
     * Returns a hex string representation of the given int value.
     *
     * @param val the value
     * @return a string representing the value in hex
     */
    public static String asHex(int val) {
        return asHex(val, 0);
    }

    /**
     * Returns a hex string representation of the given int value.
     *
     * @param val the value
     * @return a string representing the value in hex
     */
    public static String asHex(int val, int zeroPad) {
        String h = Integer.toHexString(val);
        int numDigits = h.length();
        if (zeroPad > 0 && numDigits < zeroPad) {
            int numZeros = zeroPad - numDigits;
            h = "0".repeat(numZeros) + h;
        }
        return OX + h;
    }
}
