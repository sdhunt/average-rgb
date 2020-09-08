package com.meowster.avgrgb;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;

class MainPanel extends JPanel {

    private final Aperture aperture = new Aperture();
    private final Info info = new Info();

    public MainPanel() {
        setLayout(new BorderLayout());

        add(info, BorderLayout.WEST);
        add(aperture, BorderLayout.CENTER);
    }

    public void setAperture(BufferedImage area) {
        aperture.setImage(area);
    }
}
