import java.util.*;

public class InMemoryStore implements InvoiceStore {
    private final Map<String, Invoice> invoices = new HashMap<>();

    @Override
    public void save(Invoice invoice) {
        invoices.put(invoice.id, invoice);
    }
}
