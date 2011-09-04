/*
 * Copyright, 1999-2006, SALESFORCE.com
 * All Rights Reserved
 * Company Confidential
 */
package com.selectquote.product.config;

/**
 * Constants
 *
 * @author jonglee
 * @since 160
 */
public final class InputParameters {
    /**
     * Health Selection
     * "R": Regular
     * "RP" Regular Plus
     * "P"  Preferred
     * "PP" Preferred Plus
     */
    public static final String STANDARD = "R";
    public static final String PREFERRED = "P";
    public static final String PREFERRED_PLUS = "PP";
    public static final String STANDARD_PLUS = "RP";

    /**
     * Category Selection
     * <OPTION value="A:20">&nbsp;&nbsp;5 Year Guaranteed&nbsp;</OPTION>&nbsp;
     * <OPTION value="B:20">10 Year Guaranteed&nbsp;</OPTION>&nbsp;
     * <OPTION value="C:25">15 Year Guaranteed&nbsp;</OPTION>&nbsp;
     * <OPTION selected value="D:30">20 Year Guaranteed&nbsp;</OPTION>&nbsp;
     * <OPTION value="E:35">25 Year - Guaranteed&nbsp;</OPTION>&nbsp;
     * <OPTION value="F:40">30 Year - Guaranteed&nbsp;</OPTION>&nbsp;
     * <OPTION value="T:40">to age 65 Guaranteed&nbsp;</OPTION>&nbsp;
     * <OPTION value="U:40">to age 70 Guaranteed&nbsp;</OPTION>&nbsp;
     * <OPTION value="V:40">to age 75 Guaranteed&nbsp;</OPTION>&nbsp;
     * <OPTION value="Z:BD:30">10 versus 20 Year Term&nbsp;</OPTION>&nbsp;
     * <OPTION value="Z:DF:40">20 versus 30 Year Term&nbsp;</OPTION>&nbsp;
     * <OPTION value="Z:BCD:30">10 versus 15 versus 20&nbsp;</OPTION>&nbsp;
     * <OPTION value="Z:BDF:40">10 versus 20 versus 30&nbsp;</OPTION>&nbsp;
     * <OPTION value="Z:ABCDEF:40">5 versus 10 v 15 v 20 v 25 v 30&nbsp;</OPTION>
     */
    public static final String TEN_YEAR = "B:20";
    public static final String FIFTEEN_YEAR = "C:25";
    public static final String TWENTY_YEAR = "D:30";
    public static final String THIRTY_YEAR = "F:40";

    /**
     * FaceAmount Selection
     */
    public static final String FIFTY_K = "50000";
    public static final String HUNDRED_K = "100000";
    public static final String TWO_FIFTY_K = "250000";
    public static final String FIVE_HUNDRED_K = "500000";
    public static final String ONE_MILLION = "1000000";

    /**
     * ZipCode Selection
     */
    public static final String ZIP_CODE_CA = "94134";
    public static final String ZIP_CODE_NY = "10001";
}
