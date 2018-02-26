package com.loan.model;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Represents a quote that is returned by the recommender for the requested
 * amount if possible.
 */
public class Quote {
    private int requestedAmount;
    private boolean available;
    private BigDecimal rate;
    private BigDecimal monthlyRepayment;
    private BigDecimal totalRepayment;

    public Quote(int requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getMonthlyRepayment() {
        return monthlyRepayment;
    }

    public void setMonthlyRepayment(BigDecimal monthlyRepayment) {
        this.monthlyRepayment = monthlyRepayment;
    }

    public BigDecimal getTotalRepayment() {
        return totalRepayment;
    }

    public void setTotalRepayment(BigDecimal totalRepayment) {
        this.totalRepayment = totalRepayment;
    }

    @Override
    public String toString() {
        String ra = String.format("Requested amount: £%s\n", requestedAmount);
        String r = String.format("Rate: %.1f", rate.doubleValue() * 100)
                .concat("%\n");
        
        String mp = String.format("Monthly repayment: £%s\n", monthlyRepayment);
        String tp = String.format("Total repayment: £%s\n", totalRepayment);

        return String.format("%s%s%s%s", ra, r, mp, tp);
    }

}
