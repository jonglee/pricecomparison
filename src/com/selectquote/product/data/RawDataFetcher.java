/*
 * Copyright, 1999-2006, SALESFORCE.com
 * All Rights Reserved
 * Company Confidential
 */
package com.selectquote.product.data;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;
import java.text.ParseException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;

import com.selectquote.product.config.Context;

/**
 * ReportFetcher
 *
 * @author jonglee
 * @since 160
 */
public class RawDataFetcher {
    //private static final Logger logger = Context.getLogger(RawDataFetcher.class);
    private static final int MAX_RETRIES = 3;

    private static final TimeZone tz = TimeZone.getTimeZone("America/Los_Angeles");

    private static final int MAX_CONNECTION = 1000;
    private static final int SO_TIME_OUT = 60 * 1000; /* 60 secs */

    private MultiThreadedHttpConnectionManager connMgr;

    //private static final RawDataFetcher THE_ONE = new RawDataFetcher();

    public RawDataFetcher() {
        connMgr = new MultiThreadedHttpConnectionManager();
        connMgr.getParams().setDefaultMaxConnectionsPerHost(MAX_CONNECTION);
        connMgr.getParams().setMaxTotalConnections(MAX_CONNECTION);
        connMgr.getParams().setConnectionTimeout(SO_TIME_OUT);
        //logger.fine("Creating RawDataFetcher...");
    }

   /* public static RawDataFetcher getInstance() {
        return THE_ONE;
    }*/

    public PremiumInfo[] fetch(Category.Type type, String zipCode, int age) throws IOException, ParseException {
        IOException savedEx = new IOException("Unable to fetch for " + type);
        for (int i = 0; i < MAX_RETRIES; i++) {
            try {
                PremiumInfo[] info  = fetchOnce(type, zipCode, age);
                if (info != null) return info;
            } catch (IOException ioe) {
                savedEx = ioe;
            }
        }
        throw savedEx;
    }

    private PremiumInfo[] fetchOnce(Category.Type type, String zipCode, int age) throws IOException, ParseException {
        HttpClient httpClient = new HttpClient(connMgr);
        httpClient.getParams().setSoTimeout(SO_TIME_OUT);
        PostMethod method = new PostMethod("http://www.term4sale.com/cgi-bin/cqsl.cgi");
        method.setRequestHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
        method.setRequestHeader("Referer", "http://www.term4sale.com");
        method.setRequestHeader("Accept-Encoding", "gzip, deflate");
        method.setRequestHeader("Cache-Control", "no-cache");
        try {
            method.addParameter("ErrOnMissingZipCode", "ON");
            method.addParameter("State", "0");
            method.addParameter("ZipCode", zipCode);
            method.addParameter("Sex", "M");

            //calculate birthday based on a month from today
            //e.g: for age 30, today is 02/11/2009, then we enter 01/11/1979
            GregorianCalendar day = new GregorianCalendar(tz);
            day.add(Calendar.YEAR, -age);
            //jlee: 08-Jan-2010
            //the website was updated on Jan 2010, where Jan is now set to 1 insteadof 0
            method.addParameter("BirthMonth", String.valueOf(day.get(Calendar.MONTH)+1));
            method.addParameter("Birthday", String.valueOf(day.get(Calendar.DAY_OF_MONTH)));
            method.addParameter("BirthYear", String.valueOf(day.get(Calendar.YEAR)));

            method.addParameter("Smoker", type.smoker ? "Y" : "N");
            method.addParameter("Health", type.health);
            method.addParameter("Category", type.term);
            method.addParameter("ModeUsed", "A");
            method.addParameter("FaceAmount", type.faceAmount);
            method.addParameter("CompRating", "4");
            httpClient.executeMethod(method);
            PremiumInfoParser parser = new PremiumInfoParser();
            return parser.parse(type, method.getResponseBodyAsStream());
        } finally {
            method.releaseConnection();
        }
    }

}
