package com.meowster.avgrgb;

import javax.swing.JPanel;
import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

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
    private int pupilSize = PUPIL_START;
    private BufferedImage image = null;
    private int centerX = 0;
    private int centerY = 0;

    private final Screen screen = new Screen();

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
        // draw for rectangles: top and bottom, fill in at the sides
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
        repaint();
    }

    private void moveCenter(int dx, int dy, boolean shifted) {
        int amount = shifted ? PAN_STEP_LARGE : PAN_STEP_SMALL;
        int newX = centerX + amount * dx;
        int newY = centerY + amount * dy;
        recenterImage(newX, newY);
    }


    // === API ===

    public void recenterImage(int x, int y) {
        this.image = screen.fetchArea(x, y);
        centerX = x;
        centerY = y;
        repaint();
    }

    public void resetPupil() {
        pupilSize = PUPIL_START;
        repaint();
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

}
