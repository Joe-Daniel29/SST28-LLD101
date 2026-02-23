import java.util.*;

/**
 * Concrete pricing: applies tax and discount policies to pre-resolved line
 * items.
 * No longer responsible for menu lookup.
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
            List<InvoiceLine> resolvedLines, double subtotal) {
        double taxPct = taxPolicy.taxPercent(customerType);
        double tax = subtotal * (taxPct / 100.0);
        double discount = discountPolicy.discountAmount(customerType, subtotal, resolvedLines.size());
        double total = subtotal + tax - discount;

        return new Invoice(invoiceId, resolvedLines, subtotal, taxPct, tax, discount, total);
    }
}
