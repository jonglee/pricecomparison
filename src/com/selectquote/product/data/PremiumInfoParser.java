/*
 * Copyright, 1999-2006, SALESFORCE.com
 * All Rights Reserved
 * Company Confidential
 */
package com.selectquote.product.data;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;

import com.selectquote.product.config.Context;

import au.id.jericho.lib.html.*;

/**
 * RateParser
 *
 * @author jonglee
 * @since 160
 */
public class PremiumInfoParser {

    private static final java.util.logging.Logger logger = Context.getLogger(PremiumInfoParser.class);

    private static final String RESULT_PAGE_TITLE = "Term4Sale.com Comparison Results";
    private static final int NUM_OF_COLS = 7;
    private static final int INDEX_OF_PREMIUM = 4;
    private static final int INDEX_OF_PREMIUM_CLASS = 5;

    public PremiumInfoParser() {
    }

    public PremiumInfo[] parse(Category.Type type, InputStream in) throws IOException, ParseException {
        PremiumInfoFilter filter = new PremiumInfoFilter();
        ArrayList<PremiumInfo> premiumInfos = new ArrayList<PremiumInfo>(100);

        Source source = new Source(in);

        String title = getTitle(source);
        logger.fine("Reading response page title as " + title);

        Object[] elements = source.findAllElements(Element.TD).toArray();
        if (elements != null) {
            logger.fine(String.format("Found %d elements with <TD> tag", elements.length));

            if (RESULT_PAGE_TITLE.equals(title)) {
                for (int i = NUM_OF_COLS; i < elements.length;) {
                    PremiumInfo premiumInfo = new PremiumInfo(getContent(elements[i]),
                            getContent(elements[i + INDEX_OF_PREMIUM]),
                            getContent(elements[i + INDEX_OF_PREMIUM_CLASS]));
                    if (filter.isValid(premiumInfo, type.health))
                        premiumInfos.add(premiumInfo);
                    i += NUM_OF_COLS;
                }
            } else {
                logger.severe(String.format("Mis-matched result page title, expected: '%s', actual: '%s'",
                        RESULT_PAGE_TITLE, title));
            }
        } else {
            //something wrong with the site?
            logger.severe("Found no elements with <TD>");
        }
        return premiumInfos.toArray(new PremiumInfo[premiumInfos.size()]);
    }

    private static String getContent(Object e) {
        return ((Element) e).getContent().getTextExtractor().toString();
    }

    private static String getTitle(Source source) {
        Element titleElement = source.findNextElement(0, HTMLElementName.TITLE);
        if (titleElement == null) return null;
        // TITLE element never contains other tags so just decode it collapsing whitespace:
        return CharacterReference.decodeCollapseWhiteSpace(titleElement.getContent());
    }

}
