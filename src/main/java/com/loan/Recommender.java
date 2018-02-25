package com.loan;

import com.loan.model.Lender;
import com.loan.model.Quote;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Processes loan requests and returns the most competitive quote from a
 * pool of lenders.
 */
public class Recommender {

    private Lender[] lenders;

    public Recommender(Lender[] lenders) {
        this.lenders = lenders;
    }

    /**
     * Obtains the best quote from the pool of lenders.
     *
     * @param amount the loan amount that was requested
     * @return a single quote, indicating if it was available or not
     */
    public Quote retrieveQuote(int amount) {
        Quote quote = new Quote(amount);
        if (!availableLenders(amount)) {
            quote.setAvailable(false);
            return quote;
        }

        quote.setAvailable(true);

        return quote;
    }

    /**
     * Helper method to check to see if there is at least one lender
     * that can provide a quotation
     *
     * @param amount the loan amount that was requested
     * @return true if there is at least one lender available, false otherwise
     */
    private boolean availableLenders(int amount) {
        Stream<Lender> lenders = Arrays.stream(this.lenders);
        return lenders.filter(l -> l.getAvailable() >= amount).count() > 0;
    }

}
