/*
 * Copyright, 1999-2006, SALESFORCE.com
 * All Rights Reserved
 * Company Confidential
 */
package com.selectquote.product.report;

import java.io.IOException;
import java.text.ParseException;

import org.junit.*;

import com.selectquote.product.config.Context;

import junit.framework.JUnit4TestAdapter;

/**
 * EmailSenderTest
 *
 * @author jonglee
 * @since 160
 */
public class EmailSenderTest {
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(EmailSenderTest.class);
    }

    @BeforeClass
    public static void setUp() throws IOException, ParseException {
        System.setProperty(Context.TEST_MODE, "true");
    }

    @AfterClass
    public static void tearDown() {
        System.setProperty(Context.TEST_MODE, "false");
    }

    @Test
    public void testSendSuccessEmail() {
        EmailSender email = new EmailSender();
        email.reportSuccess();
    }

    @Test
    public void testSendFailureEmail() {
        EmailSender email = new EmailSender();
        email.reportProblems();
    }
}
