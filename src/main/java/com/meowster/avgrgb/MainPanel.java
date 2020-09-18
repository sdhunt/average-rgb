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
    private final Info info = new Info();

    public MainPanel() {
        setLayout(new BorderLayout());

        add(info, BorderLayout.WEST);
        add(aperture, BorderLayout.CENTER);
        aperture.setInfo(info);
    }

    public String debugString() {
        return aperture.toString();
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

}
