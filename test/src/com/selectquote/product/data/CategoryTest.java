/*
 * Copyright, 1999-2006, SALESFORCE.com
 * All Rights Reserved
 * Company Confidential
 */
package com.selectquote.product.data;

import org.junit.Test;
import org.junit.Assert;

import junit.framework.JUnit4TestAdapter;

/**
 * CategoryTest
 *
 * @author jonglee
 * @since 160
 */
public class CategoryTest {
    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(CategoryTest.class);
    }

    @Test
    public void testTypeEnum() {
        Category.Type[] types = Category.Type.values();
        Assert.assertEquals("Expect num of Types", 140, types.length);
    }

    @Test
    public void testAgeEnum() {
        Category.Age[] ages = Category.Age.values();
        Assert.assertEquals("Expect num of Ages", 4, ages.length);
    }
}
