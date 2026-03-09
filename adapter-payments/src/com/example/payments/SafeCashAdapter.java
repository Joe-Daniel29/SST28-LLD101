package com.example.payments;

import java.util.Objects;

/**
 * Object Adapter: wraps SafeCashClient and exposes it as a PaymentGateway.
 * Translates charge(customerId, amountCents) → createPayment(amount,
 * user).confirm().
 * Note: SafeCashClient takes (amount, user) — argument order is swapped vs the
 * gateway.
 */
public final class SafeCashAdapter implements PaymentGateway {

    private final SafeCashClient client;

    public SafeCashAdapter(SafeCashClient client) {
        this.client = Objects.requireNonNull(client, "SafeCashClient must not be null");
    }

    @Override
    public String charge(String customerId, int amountCents) {
        Objects.requireNonNull(customerId, "customerId");
        return client.createPayment(amountCents, customerId).confirm();
    }
}
