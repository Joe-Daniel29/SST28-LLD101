/**
 * Persistence abstraction for invoices.
 * Pure storage concern — no presentation queries like line counting.
 */
public interface InvoiceStore {
    void save(String id, String content);
}
