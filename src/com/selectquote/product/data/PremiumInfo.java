package com.selectquote.product.data;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.logging.Logger;

import com.selectquote.product.config.Mapping;
import com.selectquote.product.config.Context;

/**
 * PremiumInfo: represents each row of the price comparison table, holds company name, premium and health category
 *
 * @author jonglee
 */
public class PremiumInfo implements Serializable {

    private static final Logger logger = Context.getLogger(PremiumInfo.class);
    public static final PremiumInfo NO_PREMIUM = new PremiumInfo(Mapping.NOT_APPLICABLE, Mapping.NOT_APPLICABLE, null);
    public final String companyName;
    public final String premium;
    public final String premiumClass;

    public PremiumInfo(String companyName, String premium, String premiumClass) {
        this.companyName = makeAlias(companyName);
        logger.fine(String.format("Converting company name '%s' to alias '%s' with premium = '%s'",
                companyName, this.companyName, premium));
        this.premium = premium;
        this.premiumClass = premiumClass;
    }

    public double getPremiumInDouble() {
        try {
            return NumberFormat.getInstance().parse(premium).doubleValue();
        } catch (ParseException e) {
            logger.info(String.format("Unable to parse premium '%s' for company '%s'", premium, companyName));
            return 1.0;
        }
    }

    @Override
    public String toString() {
        return new StringBuilder().append("companyName=").append(companyName)
                .append(", premium=").append(premium)
                .toString();
    }

    private static String makeAlias(String name) {
        int i = name.indexOf(" Mutual");
        if (i > 0)
            return trim(name, i);

        i = name.indexOf(" Life");
        if (i > 0)
            return trim(name, i);

        i = name.indexOf(" Insurance");
        if (i > 0)
            return trim(name, i);

        i = name.indexOf(" Assurance");
        if (i > 0)
            return trim(name, i);

        i = name.indexOf(" Company");
        if (i > 0)
            return trim(name, i);

        return name;
    }

    private static String trim(String s, int i) {
        return s.substring(0, i).trim();
    }
}
