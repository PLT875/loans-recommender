package com.loan;

import com.loan.model.Lender;
import com.loan.model.Quote;

import java.io.IOException;

/**
 * Main class of the application.
 */
public class Main {

    public static final String NO_QUOTE_AVAILABLE = "It is not possible to " +
            "provide a quote at this time!\n";

    public static final String INVALID_LOAN_QUEST = "Invalid request, must " +
            "be a multiple of £100 between £1000 and £15000 inclusive!\n";

    public static void main(String[] args) {
        String marketFile = args[0];
        int loanAmount = new Integer(args[1]).intValue();
        if (!Util.validateLoanRequest(loanAmount)) {
            System.err.print(INVALID_LOAN_QUEST);
        } else {
            Recommender recommender;
            CsvReader reader = new CsvReader();
            Lender[] lenders;
            try {
                lenders = reader.readCsv(marketFile);
                recommender = new Recommender(lenders);
                Quote q0 = recommender.retrieveQuote(loanAmount);
                if (!q0.getAvailable()) {
                    System.err.print(NO_QUOTE_AVAILABLE);
                } else {
                    System.out.print(q0);
                }
            } catch (IOException e) {
                System.err.print(e.getMessage() + "\n");
            }
        }
    }
}
