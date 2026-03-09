package com.example.payments;

import java.util.Objects;

/**
 * Object Adapter: wraps FastPayClient and exposes it as a PaymentGateway.
 * Translates charge(customerId, amountCents) → payNow(custId, amountCents).
 */
public final class FastPayAdapter implements PaymentGateway {

    private final FastPayClient client;

    public FastPayAdapter(FastPayClient client) {
        this.client = Objects.requireNonNull(client, "FastPayClient must not be null");
    }

    @Override
    public String charge(String customerId, int amountCents) {
        Objects.requireNonNull(customerId, "customerId");
        return client.payNow(customerId, amountCents);
    }
}
