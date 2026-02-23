import java.util.*;

/**
 * Concrete pricing: computes subtotal internally, then applies tax and
 * discount policies.
 */
public class PricingService implements PricingPolicy {
    private final TaxPolicy taxPolicy;
    private final DiscountPolicy discountPolicy;

    public PricingService(TaxPolicy taxPolicy, DiscountPolicy discountPolicy) {
        this.taxPolicy = taxPolicy;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Invoice calculate(String invoiceId, CustomerType customerType,
            List<InvoiceLine> resolvedLines) {
        double subtotal = 0.0;
        for (InvoiceLine il : resolvedLines) {
            subtotal += il.lineTotal;
        }

        double taxPct = taxPolicy.taxPercent(customerType);
        double tax = subtotal * (taxPct / 100.0);
        double discount = discountPolicy.discountAmount(customerType, subtotal, resolvedLines.size());
        double total = subtotal + tax - discount;

        return new Invoice(invoiceId, resolvedLines, subtotal, taxPct, tax, discount, total);
    }
}
