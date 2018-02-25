package com.loan;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

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

    @Test
    public void testCalculateRepayment() {
        BigDecimal act = Util.calculateMonthlyRepayment(new BigDecimal(0.069), 1000);
        act.setScale(2, RoundingMode.DOWN);

        BigDecimal exp = new BigDecimal("30.83");
        assertTrue(String.format("expected: %s, actual: %s", exp, act), act.equals(exp));
    }
}
