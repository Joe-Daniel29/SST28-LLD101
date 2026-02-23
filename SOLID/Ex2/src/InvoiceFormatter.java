public class InvoiceFormatter implements Formatter {

    @Override
    public String format(Invoice inv) {
        StringBuilder out = new StringBuilder();
        out.append("Invoice# ").append(inv.id).append("\n");

        for (InvoiceLine line : inv.lines) {
            out.append(String.format("- %s x%d = %.2f\n", line.itemName, line.qty, line.lineTotal));
        }

        out.append(String.format("Subtotal: %.2f\n", inv.subtotal));
        out.append(String.format("Tax(%.0f%%): %.2f\n", inv.taxPercent, inv.tax));

        if (inv.discount > 0) {
            out.append(String.format("Discount: -%.2f\n", inv.discount));
        }

        out.append(String.format("TOTAL: %.2f\n", inv.total));

        return out.toString();
    }

    @Override
    public String formatSaveConfirmation(String invoiceId, int lineCount) {
        return "Saved invoice: " + invoiceId + " (lines=" + lineCount + ")\n";
    }
}
