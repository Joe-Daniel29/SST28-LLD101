/**
 * Persistence abstraction for invoices.
 * Stores the domain object, not a pre-formatted string.
 */
public interface InvoiceStore {
    void save(Invoice invoice);
}
