/*
 * Copyright, 1999-2006, SALESFORCE.com
 * All Rights Reserved
 * Company Confidential
 */
package com.selectquote.product.config;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

/**
 * Mapping
 *
 * @author jonglee
 * @since 160
 */
public enum Mapping {

    CA("config/company-mappings-ca.txt", "ca", "", InputParameters.ZIP_CODE_CA),
    NY("config/company-mappings-ny.txt", "ny", "Of New York", InputParameters.ZIP_CODE_NY);

    public static final String NOT_APPLICABLE = "N/A";
    public static final String DEFAULT_BG_COLOR = "white";
    private HashMap<String, String> sqCompanyMap = new HashMap<String, String>();
    private String stateName;
    private String stateTitle;
    private String zipCode;


    Mapping(String mappingFile,
            String stateName,
            String stateTitle,
            String zipCode) {
        this.stateName = stateName;
        this.stateTitle = stateTitle;
        this.zipCode = zipCode;

        Properties p = new Properties();
        try {
            p.load(new FileReader(mappingFile));
        } catch (IOException e) {
            //this should never happen
            throw new AssertionError(e);
        }

        int i = Integer.parseInt(p.getProperty("sq.companies"));
        for (int j = 0; j < i; j++) {
            String name = p.getProperty("sq.company." + j);
            sqCompanyMap.put(name, p.getProperty("sq.company." + j + ".color"));
        }

    }

    public String getZipCode() {
        return zipCode;
    }

    public String getStateName() {
        return stateName;
    }

    public String getStateTitle() {
        return stateTitle;
    }

    public String getBackgroundColor(String companyName) {
        String bgColor = sqCompanyMap.get(companyName);
        return bgColor == null ? DEFAULT_BG_COLOR : bgColor;
    }

    public boolean isSQCompany(String companyName) {
        return sqCompanyMap.containsKey(companyName);
    }

}
