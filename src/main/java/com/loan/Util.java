package com.loan;

/**
 * Set of helper methods for the application.
 */
public class Util {
    private static final int PERIOD = 36;
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

}
