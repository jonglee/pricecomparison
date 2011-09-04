/*
 * Copyright, 1999-2006, SALESFORCE.com
 * All Rights Reserved
 * Company Confidential
 */
package com.selectquote.product.report;

import java.io.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Random;
import java.util.logging.Logger;

import org.apache.velocity.VelocityContext;

import com.selectquote.product.config.Context;
import com.selectquote.product.config.Mapping;
import com.selectquote.product.data.*;

/**
 * Generator
 *
 * @author jonglee
 * @since 160
 */
public class Generator {
    private static final Logger logger = Context.getLogger(Generator.class);

    private static final DecimalFormat RANKING_PERCENT_FORMAT = new DecimalFormat("#.#");
    private static final String DEFAULT_RANKING = "-";
    private final int MAX_ROWS_PER_TABLE = 10;

    private final TemplateLoader loader;

    //true if running test where we only generate one table randomly
    private static final Random RANDOM = new Random();

    private static final Category.Age[] ALL_AGES = Category.Age.values();
    private static final Category.Type[] ALL_TYPES = Category.Type.values();

    public Generator() throws Exception {
        loader = new TemplateLoader();
    }

    public void generate() throws Exception {
        logger.info("Running price comparison in " + (Context.isTestMode() ? "test" : "produciton") + " mode.");
        for (Mapping mapping : Mapping.values()) {
            generateReport(mapping);
        }
    }

    public void generateReport(Mapping mapping) throws Exception {
        long start = System.currentTimeMillis();
        File reportFile = Context.get().getReport(mapping);
        logger.info("Start generating " + reportFile);
        generatePageHeader(new BufferedOutputStream(new FileOutputStream(reportFile)), mapping);
        try {
            generateContent(reportFile, mapping);
        } finally {
            //in case of IO error while fetching the data, we always want to add the page footer
            generatePageFooter(reportFile);
            int processTime = (int) ((System.currentTimeMillis() - start) / (60 * 1000));
            logger.info(String.format("Done. It takes '%d' minutes to generate '%s'.",
                    processTime, reportFile.getName()));
        }
    }

    void generatePageHeader(OutputStream out, Mapping mapping) throws Exception {
        VelocityContext context = new VelocityContext();
        String state = mapping.getStateName();
        logger.fine("Putting " + state + " in the page header");
        context.put("state", state);
        String date = Context.REPORT_DATE_FORMAT.format(new Date());
        context.put("date", date);
        logger.fine("Putting " + date + " in the page header");
        loader.fillTemplate("page-header.vm", out, context);
    }

    void generatePageFooter(File reportFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("template/page-footer.vm"));
        try {
            FileWriter writer = new FileWriter(reportFile, true);
            try {
                String s;
                while ((s = reader.readLine()) != null) {
                    writer.append(s);
                }
            } finally {
                writer.close();
            }
        } finally {
            reader.close();
        }
    }

    void generateContent(File reportFile, Mapping mapping) throws Exception, ParseException {
        FileWriter writer = new FileWriter(reportFile, true);
        try {
            if (Context.isTestMode()) {
                Category.Type[] types = ALL_TYPES;
                int random = RANDOM.nextInt(types.length);
                Category.Type testType = types[random];
                writeOneCategory(testType, mapping, writer);
            } else {
                for (Category.Type type : ALL_TYPES) {
                    writeOneCategory(type, mapping, writer);
                }
            }
        } finally {
            writer.close();
        }
    }

    String generateTable(Mapping mapping, Category category) throws Exception {
        VelocityContext context = new VelocityContext();
        String tableTitle = category.getType().toString();
        logger.info("Generating table: " + tableTitle);
        context.put("TABLE_TITLE", tableTitle);
        for (int i = 0; i < MAX_ROWS_PER_TABLE; i++) {
            String row = generateRow(mapping, fillPremiumPerRow(category, i));
            context.put("COMPANY_ROW_" + i, row);
        }

        for (int i = 0; i < ALL_AGES.length; i++) {
            computeRanking(context, category.getPremiums(ALL_AGES[i]), mapping, i);
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        loader.fillTemplate("table.vm", out, context);
        return out.toString();
    }

    private void computeRanking(VelocityContext context, PremiumInfo[] allPremiums, Mapping mapping, int i) {
        String sqRanking = DEFAULT_RANKING;
        String percent = DEFAULT_RANKING;

        if (allPremiums != null && allPremiums.length > 0) {
            for (int j = 0; j < Math.min(MAX_ROWS_PER_TABLE, allPremiums.length); j++) {
                PremiumInfo premium = allPremiums[j];
                if (mapping.isSQCompany(premium.companyName)) {
                    sqRanking = String.valueOf(j + 1);
                    if (j != 0) {
                        double delta = (allPremiums[j].getPremiumInDouble() / allPremiums[0].getPremiumInDouble() - 1) * 100;
                        percent = RANKING_PERCENT_FORMAT.format(delta);
                    }
                    break;
                }
            }
        }
        context.put("RANK_" + i, sqRanking);
        context.put("PERCENT_" + i, percent);
    }

    Category fetchPremiumForOneTable(Category.Type type, String zipCode) throws IOException, ParseException {
        //RawDataFetcher dataFetcher = RawDataFetcher.getInstance();
        RawDataFetcher dataFetcher = new RawDataFetcher();
        Category c = new Category(type);
        for (Category.Age age : ALL_AGES) {
            PremiumInfo[] premiumInfos = dataFetcher.fetch(type, zipCode, age.age);
            if (premiumInfos != null)
                c.addPremiums(age, premiumInfos);
        }
        return c;
    }

    PremiumInfo[] fillPremiumPerRow(Category category, int i) throws Exception {
        PremiumInfo[] infoPerRow = new PremiumInfo[ALL_AGES.length];

        for (int j = 0; j < ALL_AGES.length; j++) {
            PremiumInfo[] premiumsForAge = category.getPremiums(ALL_AGES[j]);
            if (premiumsForAge == null ||
                    premiumsForAge.length == 0 ||
                    i >= premiumsForAge.length) {
                infoPerRow[j] = PremiumInfo.NO_PREMIUM;
            } else {
                infoPerRow[j] = premiumsForAge[i];
            }
        }
        return infoPerRow;
    }

    String generateRow(Mapping mapping, PremiumInfo[] info) throws Exception {
        VelocityContext context = new VelocityContext();
        for (int i = 0; i < info.length; i++) {
            context.put("BG_" + i, mapping.getBackgroundColor(info[i].companyName));
            context.put("COMPANY_" + i, info[i].companyName);
            context.put("AMOUNT_" + i, info[i].premium);
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        loader.fillTemplate("row.vm", out, context);
        return out.toString();
    }


    private void writeOneCategory(Category.Type type, Mapping mapping, FileWriter writer) throws Exception {
        Category c = fetchPremiumForOneTable(type, mapping.getZipCode());
        writer.append(generateTable(mapping, c));
        writer.flush();
    }
}
