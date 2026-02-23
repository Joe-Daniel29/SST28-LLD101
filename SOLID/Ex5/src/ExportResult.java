/**
 * Export result with explicit success/error status.
 * Eliminates the need for try/catch workarounds in callers.
 */
public class ExportResult {
    public final String contentType;
    public final byte[] bytes;
    public final boolean error;
    public final String errorMessage;

    /** Success result. */
    public ExportResult(String contentType, byte[] bytes) {
        this.contentType = contentType;
        this.bytes = bytes;
        this.error = false;
        this.errorMessage = null;
    }

    /** Error result — LSP-safe alternative to throwing exceptions. */
    public ExportResult(String errorMessage) {
        this.contentType = null;
        this.bytes = new byte[0];
        this.error = true;
        this.errorMessage = errorMessage;
    }
}
