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
            new Lender("l0", new BigDecimal("0.08"), 800),
            new Lender("l1", new BigDecimal("0.07"), 600),
            new Lender("l2", new BigDecimal("0.03"), 200),
            new Lender("l3", new BigDecimal("0.01"), 100),
            new Lender("l4", new BigDecimal("0.01"), 500),
            new Lender("l5", new BigDecimal("0.03"), 300)
    };

    private Recommender rec1;
    private Lender[] len1 = {
            new Lender("Bob", new BigDecimal("0.075"), 640),
            new Lender("Jane", new BigDecimal("0.069"), 480),
            new Lender("Fred", new BigDecimal("0.071"), 520),
            new Lender("Mary", new BigDecimal("0.104"), 170),
            new Lender("John", new BigDecimal("0.081"), 320),
            new Lender("Dave", new BigDecimal("0.074"), 140),
            new Lender("Angela", new BigDecimal("0.071"), 60)
    };

    @Before
    public void setup() {
        rec0 = new Recommender(len0);
        rec1 = new Recommender(len1);
    }

    @Test
    public void testGetAvailableLenders() {
        Lender[] l0 = rec0.getAvailableLenders(1000);
        assertEquals(4, l0.length);
        assertTrue(l0[0].getLender().equals("l3"));
        assertTrue(l0[1].getLender().equals("l4"));
        assertTrue(l0[2].getLender().equals("l2"));
        assertTrue(l0[3].getLender().equals("l5"));

        Lender[] l1 = rec0.getAvailableLenders(1700);
        assertEquals(5, l1.length);
        assertTrue(l1[0].getLender().equals("l3"));
        assertTrue(l1[1].getLender().equals("l4"));
        assertTrue(l1[2].getLender().equals("l2"));
        assertTrue(l1[3].getLender().equals("l5"));
        assertTrue(l1[4].getLender().equals("l1"));

        Lender[] l2 = rec0.getAvailableLenders(3000);
        assertEquals(0, l2.length);
    }

    @Test
    public void testRetrieveQuote() {
        Quote q0 = rec1.retrieveQuote(1000);

        BigDecimal expRate = new BigDecimal("0.07");
        BigDecimal actRate = q0.getRate().setScale(2, BigDecimal
                .ROUND_HALF_DOWN);

        assertTrue(String.format("expected: %s, actual: %s", expRate, actRate),
                actRate.equals(expRate));

        BigDecimal expMr = new BigDecimal("30.89");
        BigDecimal actMr = q0.getMonthlyRepayment();

        assertTrue(String.format("expected: %s, actual: %s", expMr, actMr),
                actMr.equals(expMr));

        BigDecimal expTr = new BigDecimal("1112.04");
        BigDecimal actTr = q0.getTotalRepayment();

        assertTrue(String.format("expected: %s, actual: %s", expTr, actTr),
                actTr.equals(expTr));
    }
}
