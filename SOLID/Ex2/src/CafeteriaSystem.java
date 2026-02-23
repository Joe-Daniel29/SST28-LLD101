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

        // Resolve menu items here — PricingPolicy stays free of menu concerns
        List<InvoiceLine> resolvedLines = resolveOrderLines(lines);
        double subtotal = 0.0;
        for (InvoiceLine il : resolvedLines) {
            subtotal += il.lineTotal;
        }

        Invoice inv = pricingPolicy.calculate(invId, customerType, resolvedLines, subtotal);
        String printable = formatter.format(inv);

        store.save(invId, printable);

        // ALL formatting delegated to Formatter — orchestrator never builds strings
        int lineCount = printable.split("\n").length;
        return printable + formatter.formatSaveConfirmation(invId, lineCount);
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
