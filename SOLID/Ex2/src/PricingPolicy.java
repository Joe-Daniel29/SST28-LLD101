import java.util.*;

/**
 * Pricing abstraction.
 * Takes already-resolved invoice lines — computes subtotal, tax, discount
 * internally. Caller only provides identity and classification.
 */
public interface PricingPolicy {
    Invoice calculate(String invoiceId, CustomerType customerType,
            List<InvoiceLine> resolvedLines);
}
