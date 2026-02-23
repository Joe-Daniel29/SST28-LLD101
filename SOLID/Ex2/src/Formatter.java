/**
 * Formatting abstraction for invoices and related output.
 */
public interface Formatter {
    String format(Invoice inv);

    /**
     * Format the save-confirmation line — keeps all string building outside the
     * orchestrator.
     */
    String formatSaveConfirmation(String invoiceId, int lineCount);
}
