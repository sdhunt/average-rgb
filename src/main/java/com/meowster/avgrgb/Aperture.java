package com.meowster.avgrgb;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import static com.meowster.avgrgb.Config.APERTURE_BORDER;
import static com.meowster.avgrgb.Config.APERTURE_SIZE;
import static javax.swing.BorderFactory.createLineBorder;

class Aperture extends JPanel {
    private BufferedImage image = null;

    public Aperture() {
        setBorder(createLineBorder(APERTURE_BORDER, 2));
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

    public void setImage(BufferedImage image) {
        this.image = image;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (image != null) {
            int xOrig = getInsets().left;
            int yOrig = getInsets().top;
            g.drawImage(image, xOrig, yOrig, null);
        }
    }
}
