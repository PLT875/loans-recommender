package com.loan.model;

import java.math.BigDecimal;

/**
 * Represents a lender.
 */
public class Lender {
    private String lender;
    private BigDecimal rate;
    private int available;

    public Lender(String lender, BigDecimal rate, int available) {
        this.lender = lender;
        this.rate = rate;
        this.available = available;
    }

    public String getLender() {
        return lender;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public int getAvailable() {
        return available;
    }

}
