import java.util.*;

public class Invoice {
    public final String id;
    public final List<InvoiceLine> lines;
    public final double subtotal;
    public final double taxPercent;
    public final double tax;
    public final double discount;
    public final double total;

    public Invoice(String id, List<InvoiceLine> lines,
            double subtotal, double taxPercent, double tax,
            double discount, double total) {
        this.id = id;
        this.lines = Collections.unmodifiableList(new ArrayList<>(lines));
        this.subtotal = subtotal;
        this.taxPercent = taxPercent;
        this.tax = tax;
        this.discount = discount;
        this.total = total;
    }
}
