/*
 * Copyright, 1999-2006, SALESFORCE.com
 * All Rights Reserved
 * Company Confidential
 */
package com.selectquote.product.ui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import thinlet.*;
import thinlet.layout.*;

/**
 * Progresses
 *
 * @author jonglee
 * @since 160
 */
public class Progresses extends Pane implements ActionListener {

    private Timer timer;
    private ProgressBar progressBar;
    private IndeterminateProgress indeterminateProgress;

    public Progresses() {
        setLayout(new InlineLayout(8, 8, 8, 8, 4));

        add(new Text("Generating Price Comaprison Reports"));
        add(new Separator());
        progressBar = new ProgressBar();
        add(progressBar);

        indeterminateProgress = new IndeterminateProgress();
        add(indeterminateProgress);

        // indeterminate

        // ProgressMonitor

        timer = new Timer(100, this);
        timer.setRepeats(true);
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        progressBar.setValue((progressBar.getValue() + 0.1f) % 1);
        indeterminateProgress.nextStep();
        repaint();
    }

    @Override
    protected void setParent(Widget parent) {
        if (parent == null) {
            timer.stop();
            timer = null;
        }
        super.setParent(parent);
    }

    private static class IndeterminateProgress extends Widget {

        private int angle;

        private void nextStep() {
            angle++;
            repaint();
        }

        @Override
        public Metrics getPreferredSize(int preferredWidth) {
            return new Metrics(24, 24);
        }

        @Override
        protected void paint(Graphics2D g) {
            Stroke stroke = g.getStroke();
            g.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
            int cx = (getWidth() - 24) / 2, cy = (getHeight() - 24) / 2;
            for (int i = 0; i < 12; i++) {
                g.setColor(new Color((255 * ((i + angle) % 12) / 12) << 24, true));
                double a = 2 * Math.PI / 12, x = Math.sin(i * a), y = Math.cos(i * a);
                g.drawLine(cx + 24 / 2 + (int) (5 * x), cy + 24 / 2 + (int) (5 * y),
                        cx + 24 / 2 + (int) (24 / 2 * x), cy + 24 / 2 + (int) (24 / 2 * y));
            }
            g.setStroke(stroke);
        }
    }
}
