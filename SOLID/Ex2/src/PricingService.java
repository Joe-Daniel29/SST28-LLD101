import java.util.*;

public class PricingService implements PricingPolicy {
    private final TaxPolicy taxPolicy;
    private final DiscountPolicy discountPolicy;

    public PricingService(TaxPolicy taxPolicy, DiscountPolicy discountPolicy) {
        this.taxPolicy = taxPolicy;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Invoice calculate(String invoiceId, String customerType,
            List<OrderLine> lines, Map<String, MenuItem> menu) {
        List<InvoiceLine> invoiceLines = new ArrayList<>();
        double subtotal = 0.0;

        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            if (item == null) {
                throw new IllegalArgumentException("Unknown menu item: " + l.itemId);
            }
            double lineTotal = item.price * l.qty;
            subtotal += lineTotal;
            invoiceLines.add(new InvoiceLine(item.name, l.qty, lineTotal));
        }

        double taxPct = taxPolicy.taxPercent(customerType);
        double tax = subtotal * (taxPct / 100.0);
        double discount = discountPolicy.discountAmount(customerType, subtotal, lines.size());
        double total = subtotal + tax - discount;

        return new Invoice(invoiceId, invoiceLines, subtotal, taxPct, tax, discount, total);
    }
}
