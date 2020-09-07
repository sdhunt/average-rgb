package com.meowster.avgrgb;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class MainPanel extends JPanel {
    private static final Color BG = new Color(0x112233);

    public MainPanel() {

        setLayout(new BorderLayout());
        setBackground(BG);
        setBorder(BorderFactory.createLineBorder(BG, 4));

        add(new Square());
    }


    private static class Square extends JPanel {
        private static final Dimension DIM = new Dimension(500, 400);

        @Override
        public Dimension getMaximumSize() {
            return DIM;
        }

        @Override
        public Dimension getMinimumSize() {
            return DIM;
        }

        @Override
        public Dimension getPreferredSize() {
            return DIM;
        }
    }

}
