/*
 * Copyright (c) 2020. Meowster.com
 * All Rights Reserved.
 */

package com.meowster.avgrgb;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Point;

class MainPanel extends JPanel {
    private final Aperture aperture = new Aperture();

    public MainPanel() {
        setLayout(new BorderLayout());

        Patch patch = new Patch();
        InfoPanel info = new InfoPanel();

        info.setPatch(patch);

        add(patch, BorderLayout.WEST);
        add(info, BorderLayout.CENTER);
        add(aperture, BorderLayout.EAST);

        aperture.setInfoPanel(info);
        aperture.updateState();
    }

    public void resetPupil() {
        aperture.resetPupil();
    }

    public void contractPupil(boolean shifted) {
        aperture.contractPupil(shifted);
    }

    public void dilatePupil(boolean shifted) {
        aperture.dilatePupil(shifted);
    }

    public void panUp(boolean shifted) {
        aperture.panUp(shifted);
    }

    public void panDown(boolean shifted) {
        aperture.panDown(shifted);
    }

    public void panLeft(boolean shifted) {
        aperture.panLeft(shifted);
    }

    public void panRight(boolean shifted) {
        aperture.panRight(shifted);
    }

    public void setApertureCenteredAt(Point p) {
        aperture.recenterImage(p.x, p.y);
    }

    public void copyToClipboard() {
        aperture.copyToClipboard();
    }
}
