package com.loan;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Set of helper methods for the application.
 */
public class Util {
    private static final int MONTHS = 36;
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
     * @param rate the annual rate
     * @param amount the requested loan amount
     *
     * @return the monthly repayment
     */
    public static BigDecimal calculateMonthlyRepayment(BigDecimal rate, int amount) {
        BigDecimal twelve = new BigDecimal(12);
        BigDecimal months = new BigDecimal(MONTHS);
        BigDecimal requested = new BigDecimal(amount);

        // monthly rate
        BigDecimal mr = rate.divide(twelve, 8, BigDecimal.ROUND_DOWN);

        BigDecimal oneAddMr = BigDecimal.ONE.add(mr);

        BigDecimal a = mr.multiply(oneAddMr.pow(MONTHS));
        BigDecimal b = (oneAddMr.pow(MONTHS)).subtract(BigDecimal.ONE);

        BigDecimal aDivB = a.divide(b, 8, BigDecimal.ROUND_DOWN);

        return requested.multiply(aDivB).setScale(2, RoundingMode.DOWN);
    }

}
