package com.loan;

import com.loan.model.Lender;
import com.loan.model.Quote;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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
        Lender[] qualifying = getAvailableLenders(amount);
        if (qualifying.length == 0) {
            quote.setAvailable(false);
            return quote;
        }

        quote.setAvailable(true);

        // calculate average interest rate
        List<Lender> lendersList = Arrays.asList(qualifying);
        BigDecimal totalRate = lendersList.stream().map(l -> l.getRate())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal avgRate = totalRate.divide(new BigDecimal(lendersList.size()),
                MathContext.DECIMAL128);

        quote.setRate(avgRate);
        return quote;
    }

    /**
     * Returns a number of lenders that can satisfy the loan request, priority
     * given to those with lower interest rates
     *
     * @param amount the loan amount that was requested
     * @return array of lenders that can satisfy the loan, empty otherwise
     */
    public Lender[] getAvailableLenders(int amount) {
        List<Lender> lenders = Arrays.asList(this.lenders);
        lenders.sort(Comparator.comparing(Lender::getRate).thenComparing
                (Lender::getAvailable));

        int cumulative = 0;
        List<Lender> qualifying = new ArrayList<>();
        for (Lender provider : lenders) {
            qualifying.add(provider);
            cumulative += provider.getAvailable();
            if (cumulative >= amount) {
                return qualifying.toArray(new Lender[qualifying.size()]);
            }
        }

        return new Lender[0];
    }

}
