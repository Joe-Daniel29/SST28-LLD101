/**
 * Formatting abstraction for invoices.
 * Single responsibility: render an Invoice as a displayable string.
 */
public interface Formatter {
    String format(Invoice inv);
}
