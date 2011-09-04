/*
 * Copyright, 1999-2006, SALESFORCE.com
 * All Rights Reserved
 * Company Confidential
 */
package com.selectquote.product.ui;

import java.util.concurrent.Executors;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.Date;
import java.io.*;
import java.awt.*;

import javax.swing.*;

import com.selectquote.product.config.Context;
import com.selectquote.product.report.EmailSender;
import com.selectquote.product.report.Generator;

import thinlet.RootPane;

/**
 * MainPane
 *
 * @author jonglee
 * @since 160
 */
public class Main {

    private static final Logger logger = Context.getLogger(Main.class);
    private static final String TITLE = "Price Comparison 2.0";
    private static final File processFile = new File("process.lock");
    private JFrame frame;

    private Main() {

        if (isProcessRunning()) {
            JOptionPane.showMessageDialog(
                    new JFrame(), "Reports are currently running.", TITLE, JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
        registerShutdownHook();
        showProgressFrame();
    }

    private void showProgressFrame() {
        logger.info("Display progress frame");
        frame = new RootPane(new Progresses()).showFrame(300, 100);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setIconImage(frame.getToolkit().getImage("images/icon.png"));
        frame.setTitle(TITLE);
    }

    private boolean isProcessRunning() {
        return processFile.exists();
    }

    private void registerShutdownHook() {
        //touch process lock file
        try {
            FileWriter writer = new FileWriter(processFile);
            writer.write(new Date().toString());
            writer.close();
            logger.info("Process locked");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Unable to create process lock file", e);
            System.exit(-1);
        }

        //register shutdown hook
        Runtime.getRuntime().addShutdownHook(new ProcessShutdownHook());
    }

    public void generate() {
        Executors.newFixedThreadPool(1).submit(new Runnable() {
            public void run() {
                EmailSender email = new EmailSender();
                try {
                    new Generator().generate();
                    email.reportSuccess();
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "failed to generate report and send email alert", e);
                    email.reportProblems();
                } finally {
                    frame.setVisible(false);
                    cleanUp();
                    System.exit(0);
                }

            }
        });
    }

    private static void cleanUp() {
        if (processFile.exists())
            logger.info("Deleting process lock file: " + processFile.delete());
        else
            logger.info("process has already been unlocked.");
    }

    private static class ProcessShutdownHook extends Thread {
        public void run() {
            logger.info("Running ProcessShutdownHook");
            cleanUp();
        }
    }

    public static void main(String[] args) {
        new Main().generate();
    }
}
