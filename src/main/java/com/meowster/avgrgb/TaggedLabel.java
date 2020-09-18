package com.meowster.avgrgb;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Component;

class TaggedLabel extends JLabel {

    private final String tag;

    TaggedLabel(String tag) {
        this.tag = tag;
        setText("--");
        setForeground(Color.BLACK);
        setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    @Override
    public void setText(String text) {
        String s = this.tag + ": " + text;
        super.setText(s);
    }
}
