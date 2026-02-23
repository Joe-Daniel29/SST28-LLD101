import java.util.*;

/**
 * Pricing abstraction.
 * Takes already-resolved invoice lines (not raw menu map) — the caller
 * handles menu lookup so this interface stays free of menu concerns.
 */
public interface PricingPolicy {
    Invoice calculate(String invoiceId, CustomerType customerType,
            List<InvoiceLine> resolvedLines, double subtotal);
}
