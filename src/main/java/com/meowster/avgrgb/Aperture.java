/*
 * Copyright (c) 2020. Meowster.com
 * All Rights Reserved.
 */

package com.meowster.avgrgb;

import javax.swing.JPanel;
import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static com.meowster.avgrgb.Config.APERTURE_BORDER;
import static com.meowster.avgrgb.Config.APERTURE_BORDER_COLOR;
import static com.meowster.avgrgb.Config.APERTURE_SIZE;
import static com.meowster.avgrgb.Config.IRIS_ALPHA;
import static com.meowster.avgrgb.Config.IRIS_COLOR;
import static com.meowster.avgrgb.Config.PAN_STEP_LARGE;
import static com.meowster.avgrgb.Config.PAN_STEP_SMALL;
import static com.meowster.avgrgb.Config.PUPIL_MAX;
import static com.meowster.avgrgb.Config.PUPIL_MIN;
import static com.meowster.avgrgb.Config.PUPIL_START;
import static com.meowster.avgrgb.Config.PUPIL_STEP_LARGE;
import static com.meowster.avgrgb.Config.PUPIL_STEP_SMALL;
import static java.awt.AlphaComposite.SRC_OVER;
import static javax.swing.BorderFactory.createLineBorder;

class Aperture extends JPanel {
    private final Clipboard clipboard =
            Toolkit.getDefaultToolkit().getSystemClipboard();

    private BufferedImage image = null;
    private int centerX = APERTURE_SIZE;
    private int centerY = APERTURE_SIZE;
    private int pupilSize = PUPIL_START;
    private Color currentColor = Color.WHITE;

    private final Screen screen = new Screen();
    private InfoPanel info;

    public Aperture() {
        setBorder(createLineBorder(APERTURE_BORDER_COLOR, APERTURE_BORDER));
        configureSize();
    }

    private void configureSize() {
        int pad = getInsets().top;
        int size = pad + APERTURE_SIZE + pad;
        Dimension dim = new Dimension(size, size);

        setMaximumSize(dim);
        setMinimumSize(dim);
        setPreferredSize(dim);
    }

    @Override
    public String toString() {
        return "Aperture{" +
                "pupilSize=" + pupilSize +
                ", centerX=" + centerX +
                ", centerY=" + centerY +
                '}';
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // top and left insets are assumed to be the same
        int inset = getInsets().top;
        if (image != null) {
            g.drawImage(image, inset, inset, null);
        }
        paintIris(g, inset);
    }

    private int irisWidth() {
        return (APERTURE_SIZE - pupilSize) / 2;
    }

    private void paintIris(Graphics g, int inset) {
        Graphics2D g2 = (Graphics2D) g;
        int iw = irisWidth();

        Composite c = AlphaComposite.getInstance(SRC_OVER, IRIS_ALPHA);
        g2.setComposite(c);
        g2.setPaint(IRIS_COLOR);
        // draw four rectangles: top and bottom, fill in at the sides
        g2.fillRect(inset, inset, APERTURE_SIZE, iw);
        g2.fillRect(inset, inset + iw + pupilSize, APERTURE_SIZE, iw);
        g2.fillRect(inset, inset + iw, iw, pupilSize);
        g2.fillRect(inset + iw + pupilSize, inset + iw, iw, pupilSize);

    }

    private void adjustPupil(int mult, boolean shifted) {
        int amount = shifted ? PUPIL_STEP_LARGE : PUPIL_STEP_SMALL;
        int proposed = pupilSize + amount * mult;
        if (proposed >= PUPIL_MIN && proposed <= PUPIL_MAX) {
            pupilSize = proposed;
        }
        updateState();
    }

    private void moveCenter(int dx, int dy, boolean shifted) {
        int amount = shifted ? PAN_STEP_LARGE : PAN_STEP_SMALL;
        int newX = centerX + amount * dx;
        int newY = centerY + amount * dy;
        recenterImage(newX, newY);
    }

    private Color computedAverageColor() {
        if (image != null) {
            BufferedImage area = image.getSubimage(irisWidth(), irisWidth(), pupilSize, pupilSize);
            return Color.averageColor(convertToIntArray(area));
        }
        return Color.WHITE;
    }

    private int[] convertToIntArray(BufferedImage im) {
        int w = im.getWidth();
        int h = im.getHeight();
        List<Integer> pixels = new ArrayList<>(w * h);

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                pixels.add(im.getRGB(x, y));
            }
        }
        return pixels.stream().mapToInt(i -> i).toArray();
    }

    // === API ===

    public void setInfoPanel(InfoPanel info) {
        this.info = info;
    }

    public void updateState() {
        repaint();
        currentColor = computedAverageColor();
        if (info != null) {
            info.updateState(currentColor);
        }
    }

    public void recenterImage(int x, int y) {
        this.image = screen.fetchArea(x, y);
        centerX = x;
        centerY = y;
        updateState();
    }

    public void resetPupil() {
        pupilSize = PUPIL_START;
        updateState();
    }

    public void contractPupil(boolean shifted) {
        adjustPupil(-1, shifted);
    }

    public void dilatePupil(boolean shifted) {
        adjustPupil(1, shifted);
    }

    public void panUp(boolean shifted) {
        moveCenter(0, -1, shifted);
    }

    public void panDown(boolean shifted) {
        moveCenter(0, 1, shifted);
    }

    public void panLeft(boolean shifted) {
        moveCenter(-1, 0, shifted);
    }

    public void panRight(boolean shifted) {
        moveCenter(1, 0, shifted);
    }

    public void copyToClipboard() {
        System.out.println("Copy to Clipboard: " + currentColor);
        StringSelection str = new StringSelection(currentColor.hex());
        clipboard.setContents(str, str);
    }
}
