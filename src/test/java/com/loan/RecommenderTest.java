package com.loan;

import com.loan.model.Lender;
import com.loan.model.Quote;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Unit tests for the Recommender.
 */
public class RecommenderTest {

    private Recommender rec0;
    private Lender[] len0 = {
            new Lender("l0", new BigDecimal("0.01"), 500),
            new Lender("l1", new BigDecimal("0.02"), 300),
            new Lender("l2", new BigDecimal("0.05"), 100)
    };

    @Before
    public void setup() {
        rec0 = new Recommender(len0);
    }

    @Test
    public void testAvailableLenders() {
        Quote q0 = rec0.retrieveQuote(300);
        assertTrue(q0.getAvailable());

        Quote q1 = rec0.retrieveQuote(600);
        assertFalse(q1.getAvailable());
    }
}
