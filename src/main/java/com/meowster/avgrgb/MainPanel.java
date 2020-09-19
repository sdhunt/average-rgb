/*
 * Copyright (c) 2020. Meowster.com
 * All Rights Reserved.
 */

package com.meowster.avgrgb;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Point;

class MainPanel extends JPanel {

    private final Patch patch = new Patch();
    private final InfoPanel info = new InfoPanel();
    private final Aperture aperture = new Aperture();

    public MainPanel() {
        setLayout(new BorderLayout());

        add(patch, BorderLayout.WEST);
        add(info, BorderLayout.CENTER);
        add(aperture, BorderLayout.EAST);

        aperture.setInfoPanel(info);
        aperture.updateState();

        info.setPatch(patch);
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
