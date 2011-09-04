/*
 * Copyright, 1999-2006, SALESFORCE.com
 * All Rights Reserved
 * Company Confidential
 */
package com.selectquote.product.config;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.*;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;

/**
 * Global
 *
 * @author jonglee
 * @since 160
 */
public final class Context {
    public static final String REPORT_DIR = "reports";
    public static final SimpleDateFormat REPORT_DATE_FORMAT =
            new SimpleDateFormat("MM/dd/yy");
    public static final SimpleDateFormat FILENAME_DATE_FORMAT =
            new SimpleDateFormat("MM-dd-yy");

    public static final String ENCODING = "UTF-8";

    //used by tests, don't generate the whole report
    public static final String TEST_MODE = "com.selectquote.product.pricecomparison.test_mode";

    //used by dev mode, send email to sender only(config.properties)
    public static final String DEV_MODE = "com.selectquote.product.pricecomparison.dev_mode";

    private static Logger logger = getLogger(Context.class);

    private static final Context CONTEXT = new Context();

    private VelocityEngine velocityEngine;

    private File reportsDir;
    private HashMap<Mapping, File> reportFiles = new HashMap<Mapping, File>();

    private EmailConfig emailConfig;

    private File logFile;

    private Context() {
        initLogging();
        initProperties();
        initReport();
        initVelocity();
    }

    private void initLogging() {
        try {
            LogManager.getLogManager().readConfiguration(getConfigFile("logger.properties"));
        } catch (IOException e) {
            quit("Unable to load logger.properties", e);
        }
    }

    private void initReport() {
        reportsDir = new File(Context.REPORT_DIR);
        if (!reportsDir.exists()) {
            if (!reportsDir.mkdirs()) {
                quit("Unable to create report dir " + reportsDir, null);
            }
        }

        for (Mapping mapping : Mapping.values()) {
            String reportName = Context.get().getReportName(mapping);
            File reportFile = new File(reportsDir, reportName);
            reportFiles.put(mapping, reportFile);
        }

    }

    private void initVelocity() {
        //init velocity engine
        velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.ENCODING_DEFAULT, ENCODING);
        velocityEngine.setProperty(RuntimeConstants.OUTPUT_ENCODING, ENCODING);
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "file");
        Vector<String> path = new Vector<String>();
        String templatePath = new File("template").getAbsolutePath();
        path.add(templatePath);
        logger.fine("Loading velocity template from " + templatePath);
        velocityEngine.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, path);
        velocityEngine.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_CACHE, true);
        try {
            velocityEngine.init();
        } catch (Exception e) {
            quit("unable to initialize Velocity", e);
        }

    }

    private void initProperties() {
        Properties config = new Properties();
        try {
            config.load(getConfigFile("config.properties"));
            Properties emailProps = new Properties();
            emailProps.setProperty("mail.transport.protocol", "smtp");
            emailProps.setProperty("mail.smtps.auth", "false");
            emailProps.setProperty("mail.smtp.host", config.getProperty("mail.smtp.host"));
            emailProps.setProperty("mail.smtp.port", config.getProperty("mail.smtp.port"));
            emailConfig = new EmailConfig(emailProps, config.getProperty("mail.sender"),
                    config.getProperty("mail.recipients"));

            File logDir = new File(config.getProperty("log.dir"));
            if (!logDir.exists()) logDir.mkdirs();

            logFile = new File(logDir, config.getProperty("log.name"));
            backupLogFile(logFile);
        } catch (IOException e) {
            quit("unable to load config properties", e);
        }

    }

    private static FileInputStream getConfigFile(String fileName) throws FileNotFoundException {
        return new FileInputStream(new File("config", fileName));
    }

    private static void backupLogFile(File source) {
        try {
            if (!source.exists()) return;
            File dest = new File(source.getParent(), source.getName() + ".backup");
            BufferedReader fin = new BufferedReader(new FileReader(source));
            BufferedWriter fout = new BufferedWriter(new FileWriter(dest));
            String s;
            while ((s = fin.readLine()) != null) {
                fout.append(s).append("\n");
            }
            fin.close();
            fout.close();
            source.delete();
        } catch (IOException e) {
            logger.log(Level.WARNING, "Unable to back up debug log " + source);
        }
    }

    private static void quit(String msg, Exception e) {
        logger.log(Level.SEVERE, msg, e);
        System.exit(-1);
    }

    private static String getReportName(Mapping mapping) {
        StringBuilder sb = new StringBuilder(mapping.getStateName())
                .append("-comparison-")
                .append(FILENAME_DATE_FORMAT.format(new Date()))
                .append(".doc");
        return sb.toString();
    }

    public static Context get() {
        return CONTEXT;
    }

    public static boolean isTestMode() {
        return Boolean.getBoolean(TEST_MODE);
    }

    public static boolean isDevMode() {
        return Boolean.getBoolean(DEV_MODE);
    }
    
    public static Logger getLogger(Class c) {
        return Logger.getLogger(c.getName());
    }

    public File getReport(Mapping mapping) {
        return reportFiles.get(mapping);
    }

    public VelocityEngine getVelocityEngine() {
        return velocityEngine;
    }

    public File getLogFile() {
        return logFile;
    }

    public EmailConfig getEmailConfig() {
        return emailConfig;
    }

    public static class EmailConfig {
        public final Properties emailProperties;
        public final String sender;
        public final String recipientString;
        public final String[] recipients;

        private EmailConfig(Properties emailProperties, String sender, String recipients) {
            this.emailProperties = emailProperties;
            this.sender = sender;
            this.recipientString = recipients;
            this.recipients = recipients.split(",");
        }
    }
}
