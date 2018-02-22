package com.loan;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for Util.
 */
public class UtilTest {

    @Test
    public void testValidateLoanRequest() {
        assertTrue(Util.validateLoanRequest(1000));
        assertTrue(Util.validateLoanRequest(15000));
        assertTrue(Util.validateLoanRequest(1500));
        assertFalse(Util.validateLoanRequest(9999));
        assertFalse(Util.validateLoanRequest(900));
        assertFalse(Util.validateLoanRequest(15100));
    }
}
