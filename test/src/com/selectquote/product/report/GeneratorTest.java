/*
 * Copyright, 1999-2006, SALESFORCE.com
 * All Rights Reserved
 * Company Confidential
 */
package com.selectquote.product.report;

import java.io.*;
import java.text.ParseException;

import org.junit.*;

import com.selectquote.product.data.*;
import com.selectquote.product.config.Mapping;
import com.selectquote.product.config.Context;

import junit.framework.JUnit4TestAdapter;

/**
 * EngineTest
 *
 * @author jonglee
 * @since 160
 */
public class GeneratorTest {
    private static Category category = new Category(Category.Type._50K_PREFERPLUS_NON_SMOKER_10_YEAR);

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(GeneratorTest.class);
    }

    @BeforeClass
    public static void setUp() throws IOException, ParseException {
        System.setProperty(Context.TEST_MODE, "true");
        PremiumInfo[] premiumInfos =
                new PremiumInfoParser().parse(category.getType(), new FileInputStream("test/data/post-result.html"));
        category.addPremiums(Category.Age.THIRTY, premiumInfos);
    }

    @AfterClass
    public static void tearDown() {
        System.setProperty(Context.TEST_MODE, "false");
    }

    @Test
    public void testGenRow() throws Exception {
        Generator generator = new Generator();
        PremiumInfo[] premiums = category.getPremiums(Category.Age.THIRTY);
        String out = generator.generateRow(Mapping.CA, premiums);
        assertTemplateContent(out);
    }

    @Test
    public void testGenTable() throws Exception {
        Generator generator = new Generator();
        String out = generator.generateTable(Mapping.CA, category);
        assertTemplateContent(out);
    }

    @Test
    public void testGenReportCA() throws Exception {
        Generator generator = new Generator();
        generator.generateReport(Mapping.CA);
    }

    @Test
    public void testGenReportNY() throws Exception {
        Generator generator = new Generator();
        generator.generateReport(Mapping.NY);
    }

    private void assertTemplateContent(String out) {
        Assert.assertFalse("Expect no tempalte variables", out.contains("!{"));
    }
}
