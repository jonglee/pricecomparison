/*
 * Copyright, 1999-2006, SALESFORCE.com
 * All Rights Reserved
 * Company Confidential
 */
package com.selectquote.product.report;

import java.io.*;
import java.util.logging.Logger;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.VelocityContext;

import com.selectquote.product.config.Context;

/**
 * TemplateLoader
 *
 * @author jonglee
 * @since 160
 */
class TemplateLoader {
    private static final Logger logger = Context.getLogger(TemplateLoader.class);
    private final VelocityEngine velocityEngine;

    TemplateLoader() {
        velocityEngine = Context.get().getVelocityEngine();
    }

    public void fillTemplate(String template, OutputStream out, VelocityContext context) throws Exception {
        logger.fine("Merging template " + template);
        Writer writer = new OutputStreamWriter(out, Context.ENCODING);
        velocityEngine.mergeTemplate(template, Context.ENCODING, context, writer);
        writer.close();
    }


}
