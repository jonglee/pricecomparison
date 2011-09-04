package com.selectquote.product.data;

import com.selectquote.product.config.InputParameters;

import java.util.HashMap;

/**
 * Price comparison results page contain different health category and possibly the same company with different
 * product name, so we need to filter the results for unique company name with the category we are asking for
 *
 * @author MTan
 *         Date: Feb 16, 2009
 */
public class PremiumInfoFilter {

    /**
     * Ugh?  They used different category string on the results than the input page
     * we need to make sure they are matched
     */
    enum PremiumClass {
        PPLUS("P+"), PF("Pf"), RPLUS("R+"), RG("Rg");

        public final String name;

        private PremiumClass(String name) {
            this.name = name;
        }
    }

    private static final HashMap<String, PremiumClass> inputParamToPremiumClassMapping;
    private final HashMap<String, String> knownCompanyNames = new HashMap<String, String>();

    /**
     * Build a map to match result page's health category to the input page
     */
    static {
        inputParamToPremiumClassMapping = new HashMap<String, PremiumClass>();
        inputParamToPremiumClassMapping.put(InputParameters.PREFERRED_PLUS, PremiumClass.PPLUS);
        inputParamToPremiumClassMapping.put(InputParameters.PREFERRED, PremiumClass.PF);
        inputParamToPremiumClassMapping.put(InputParameters.STANDARD_PLUS, PremiumClass.RPLUS);
        inputParamToPremiumClassMapping.put(InputParameters.STANDARD, PremiumClass.RG);
    }

    private static boolean isPremiumClassMatched(PremiumInfo info, String health) {
        return inputParamToPremiumClassMapping.get(health).name.equals(info.premiumClass);
    }

    private boolean companyExisits(String companyName) {
        if (!knownCompanyNames.containsKey(companyName)) {
            knownCompanyNames.put(companyName, companyName);
            return false;
        }
        return true;
    }

    public boolean isValid(PremiumInfo info, String health) {
        return isPremiumClassMatched(info, health) && !companyExisits(info.companyName);
    }
}
