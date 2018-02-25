package com.loan;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Set of helper methods for the application.
 */
public class Util {
    public static final int MONTHS = 36;

    private static final int INCREMENT = 100;
    private static final int MIN_REQUEST = 1000;
    private static final int MAX_REQUEST = 15000;

    /**
     * Utility method to check if a loan request is valid.
     *
     * @return true if the request is valid, false otherwise
     */
    public static boolean validateLoanRequest(int amount) {
        return (amount % INCREMENT == 0 && amount >= MIN_REQUEST && amount <= MAX_REQUEST);
    }

    /**
     * Calculates the monthly repayment based on EMI, for reference see
     * https://en.wikipedia.org/wiki/Equated_monthly_installment
     *
     * @param rate   the annual rate
     * @param amount the requested loan amount
     * @return the monthly repayment to 2 decimal places
     */
    public static BigDecimal calculateMonthlyRepayment(BigDecimal rate, int amount) {
        BigDecimal twelve = new BigDecimal(12);
        BigDecimal months = new BigDecimal(MONTHS);
        BigDecimal requested = new BigDecimal(amount);

        // monthly rate
        BigDecimal mr = rate.divide(twelve, MathContext.DECIMAL128);

        BigDecimal oneAddMr = BigDecimal.ONE.add(mr);

        BigDecimal a = mr.multiply(oneAddMr.pow(MONTHS), MathContext.DECIMAL128);
        BigDecimal b = (oneAddMr.pow(MONTHS)).subtract(BigDecimal.ONE);

        BigDecimal aDivB = a.divide(b, MathContext.DECIMAL128);

        return requested.multiply(aDivB, MathContext.DECIMAL128)
                .setScale(2, BigDecimal.ROUND_HALF_DOWN);
    }

}
