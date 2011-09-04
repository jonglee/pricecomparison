/*
 * Copyright, 1999-2006, SALESFORCE.com
 * All Rights Reserved
 * Company Confidential
 */
package com.selectquote.product.report;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;

import org.apache.velocity.VelocityContext;

import com.selectquote.product.config.Context;
import com.selectquote.product.config.Mapping;

/**
 * StatusEmailer
 *
 * @author jonglee
 * @since 160
 */
public class EmailSender {
    private static Logger logger = Context.getLogger(EmailSender.class);
    private final Context.EmailConfig config;
    private final TemplateLoader loader;

    public EmailSender() {
        loader = new TemplateLoader();
        config = Context.get().getEmailConfig();
        logger.fine("Using smtp properties: " + config.emailProperties);
        logger.fine("email sender is " + config.sender);
        logger.fine("email recipeints: " + config.recipientString);
    }

    public void reportSuccess() {
        sendEmail(config.sender, config.recipients, "Your PriceComparison reports are ready",
                loadEmailTemplate(true), false);
    }

    public void reportProblems() {
        sendEmail(config.sender, config.recipients, "We are unable to generate PriceComparison reports",
                loadEmailTemplate(false), true);
    }

    private void sendEmail(String sender, String[] recipients, String title, String content, boolean attachLogFile) {
        logger.info(String.format("Sending email to '%s' with title='%s'", config.recipientString, title));
        try {
            Session mailSession = Session.getDefaultInstance(config.emailProperties, null);
            //mailSession.setDebug(true);
            Transport transport = mailSession.getTransport();
            MimeMessage message = new MimeMessage(mailSession);

            message.setFrom(new InternetAddress(sender));
            if (Context.isDevMode()) {
                message.addRecipient(Message.RecipientType.TO,
                        new InternetAddress(sender));
            } else {
                for (String recipient : recipients) {
                    message.addRecipient(Message.RecipientType.TO,
                            new InternetAddress(recipient));
                }
            }

            message.setSubject(title);

            MimeMultipart multipart = new MimeMultipart("related");
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(content, "text/html");
            multipart.addBodyPart(messageBodyPart);

            if (attachLogFile) {
                messageBodyPart = new MimeBodyPart();
                File logFile = Context.get().getLogFile();
                DataSource source = new FileDataSource(logFile);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(logFile.getName());
                multipart.addBodyPart(messageBodyPart);
            }
            message.setContent(multipart);
            transport.connect();
            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            transport.close();
        } catch (MessagingException e) {
            logger.log(Level.SEVERE, "Fail to send status email", e);
        }
    }

    private String loadEmailTemplate(boolean success) {
        VelocityContext context = new VelocityContext();
        String date = Context.REPORT_DATE_FORMAT.format(new Date());
        logger.fine("date in context: " + date);
        context.put("date", date);
        String template;
        if (success) {
            String reportFile = Context.get().getReport(Mapping.CA).getAbsolutePath();
            context.put("CA_REPORT", reportFile);
            logger.fine("CA_REPORT in context: " + reportFile);
            reportFile = Context.get().getReport(Mapping.NY).getAbsolutePath();
            context.put("NY_REPORT", reportFile);
            logger.fine("NY_REPORT in context: " + reportFile);
            template = "success-email.vm";
        } else {
            template = "failure-email.vm";
        }
        logger.fine("Email template: " + template);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            loader.fillTemplate(template, out, context);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unable to generate email tempalte", e);
        }
        return out.toString();
    }
}
