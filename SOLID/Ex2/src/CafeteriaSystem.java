import java.util.*;

/**
 * Orchestrator — delegates to collaborators, never formats strings or encodes
 * business rules.
 */
public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final PricingPolicy pricingPolicy;
    private final Formatter formatter;
    private final InvoiceStore store;
    private final IdGenerator idGenerator;

    public CafeteriaSystem(PricingPolicy pricingPolicy,
            Formatter formatter,
            InvoiceStore store,
            IdGenerator idGenerator) {
        this.pricingPolicy = pricingPolicy;
        this.formatter = formatter;
        this.store = store;
        this.idGenerator = idGenerator;
    }

    public void addToMenu(MenuItem i) {
        menu.put(i.id, i);
    }

    public String checkout(CustomerType customerType, List<OrderLine> lines) {
        String invId = idGenerator.next();

        List<InvoiceLine> resolvedLines = resolveOrderLines(lines);

        // Subtotal computation fully delegated to PricingPolicy
        Invoice inv = pricingPolicy.calculate(invId, customerType, resolvedLines);
        String printable = formatter.format(inv);

        store.save(inv);

        // Save confirmation — simple orchestration output, not invoice formatting
        int lineCount = printable.split("\n").length;
        return printable + "Saved invoice: " + invId + " (lines=" + lineCount + ")\n";
    }

    private List<InvoiceLine> resolveOrderLines(List<OrderLine> orders) {
        List<InvoiceLine> resolved = new ArrayList<>();
        for (OrderLine o : orders) {
            MenuItem item = menu.get(o.itemId);
            if (item == null) {
                throw new IllegalArgumentException("Unknown menu item: " + o.itemId);
            }
            resolved.add(new InvoiceLine(item.name, o.qty, item.price * o.qty));
        }
        return resolved;
    }
}
