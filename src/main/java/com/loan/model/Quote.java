package com.loan.model;

import java.math.BigDecimal;

/**
 * Represents a quote that is returned by the recommender for the requested
 * amount if possible.
 */
public class Quote {
    private int requestedAmount;

    private boolean available;

    public Quote(int requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

}
