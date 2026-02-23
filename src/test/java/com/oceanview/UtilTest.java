package com.oceanview;

import com.oceanview.util.DateUtil;
import com.oceanview.util.NumberUtil;
import com.oceanview.util.StringUtil;
import org.junit.Test;
import static org.junit.Assert.*;

// Test class for utility functions
public class UtilTest {

    @Test
    public void testStringUtilIsEmpty() {
        assertTrue("Empty string should return true", StringUtil.isEmpty(""));
        assertTrue("Null string should return true", StringUtil.isEmpty(null));
        assertFalse("Non-empty string should return false", StringUtil.isEmpty("test"));
    }

    @Test
    public void testNumberUtilAdd() {
        double result = NumberUtil.add(5.0, 3.0);
        assertEquals("5 + 3 should equal 8", 8.0, result, 0.01);
    }

    @Test
    public void testNumberUtilMultiply() {
        double result = NumberUtil.multiply(4.0, 5.0);
        assertEquals("4 * 5 should equal 20", 20.0, result, 0.01);
    }

    @Test
    public void testDateUtilGetCurrentDate() {
        assertNotNull("Current date should not be null", DateUtil.getCurrentDate());
    }
}
