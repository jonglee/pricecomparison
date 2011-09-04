package com.selectquote.product.data;

import org.junit.Test;
import org.junit.Assert;

import com.selectquote.product.config.InputParameters;

import junit.framework.JUnit4TestAdapter;/*
 * Copyright, 1999-2006, SALESFORCE.com
 * All Rights Reserved
 * Company Confidential
 */

/**
 * ReportFetcherTest
 *
 * @author jonglee
 * @since 160
 */
public class ReportFetcherTest {
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(ReportFetcherTest.class);
    }


    @Test
    public void testFetchData() throws Exception {
        PremiumInfo[] premiumInfos = new RawDataFetcher().fetch(
                Category.Type._1Mil_PREFERPLUS_NON_SMOKER_20_YEAR,
                InputParameters.ZIP_CODE_CA,
                30);

        Assert.assertNotNull("Expect not null premium info", premiumInfos);
        Assert.assertTrue("Expect premium info having data", premiumInfos.length > 0);
    }


}
