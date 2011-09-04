package com.selectquote.product.data;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.text.ParseException;

import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: MTan
 * Date: Feb 16, 2009
 * Time: 10:45:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class PremiumInfoParserTest {
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(PremiumInfoParserTest.class);
    }

    @Test
    public void testParsePreferPlus() throws IOException, ParseException {
        PremiumInfo expectPremium = new PremiumInfo("Genworth", "73.50", PremiumInfoFilter.PremiumClass.PPLUS.name);
        runScenario(Category.Type._500K_PREFERPLUS_NON_SMOKER_10_YEAR, 5, expectPremium);
    }

    @Test
    public void testParsePrefer() throws IOException, ParseException {
        PremiumInfo expectPremium = new PremiumInfo("Genworth", "79.50", PremiumInfoFilter.PremiumClass.PF.name);
        runScenario(Category.Type._250K_PREFER_SMOKER_30_YEAR, 6, expectPremium);
    }

    @Test
    public void testParseStandardPlus() throws IOException, ParseException {
        PremiumInfo expectPremium = new PremiumInfo("Genworth", "87.00", PremiumInfoFilter.PremiumClass.RPLUS.name);
        runScenario(Category.Type._100K_STANDARD_PLUS_NON_SMOKER_20_YEAR, 4, expectPremium);
    }

    @Test
    public void testParseStandard() throws IOException, ParseException {
        PremiumInfo expectPremium = new PremiumInfo("Western Reserve", "95.00", PremiumInfoFilter.PremiumClass.RG.name);
        runScenario(Category.Type._500K_STANDARD_NON_SMOKER_10_YEAR, 18, expectPremium);
    }

    private void runScenario(Category.Type type,
                             int expectSize,
                             PremiumInfo expectPremium)
            throws IOException, ParseException {
        PremiumInfo[] premiums = new PremiumInfoParser().parse(type,
                new FileInputStream("test/data/post-result-duplicate-rows.html"));
        Assert.assertEquals("Expect size of PremiumInfo[]", expectSize, premiums.length);
        Assert.assertEquals("Expect first company found ", expectPremium.companyName, premiums[0].companyName);
        Assert.assertEquals("Expect premium", expectPremium.premium, premiums[0].premium);
        Assert.assertEquals("Expect premium class", expectPremium.premiumClass, premiums[0].premiumClass);
    }

}
