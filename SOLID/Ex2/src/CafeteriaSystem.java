import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final PricingPolicy pricingPolicy;
    private final Formatter formatter;
    private final InvoiceStore store;
    private final InvoiceIdGenerator idGenerator;

    public CafeteriaSystem(PricingPolicy pricingPolicy,
            Formatter formatter,
            InvoiceStore store,
            InvoiceIdGenerator idGenerator) {
        this.pricingPolicy = pricingPolicy;
        this.formatter = formatter;
        this.store = store;
        this.idGenerator = idGenerator;
    }

    public void addToMenu(MenuItem i) {
        menu.put(i.id, i);
    }

    public String checkout(String customerType, List<OrderLine> lines) {
        String invId = idGenerator.next();

        Invoice inv = pricingPolicy.calculate(invId, customerType, lines, menu);
        String printable = formatter.format(inv);

        store.save(invId, printable);

        return printable + "Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")\n";
    }
}
