/**
 * Base exporter with enforced contract via Template Method.
 *
 * <h3>Contract:</h3>
 * <ul>
 * <li><b>Precondition:</b> request must not be null</li>
 * <li><b>Postcondition:</b> always returns a non-null ExportResult (success or
 * error)</li>
 * <li><b>Invariant:</b> subclasses must not throw for valid input; use error
 * results instead</li>
 * </ul>
 *
 * Subclasses implement {@link #doExport(ExportRequest)} only.
 */
public abstract class Exporter {

    /**
     * Template method — enforces preconditions, delegates to subclass, validates
     * postconditions.
     * Marked final so subclasses cannot bypass the contract.
     */
    public final ExportResult export(ExportRequest req) {
        if (req == null) {
            throw new NullPointerException("ExportRequest must not be null");
        }
        ExportResult result = doExport(req);
        if (result == null) {
            throw new IllegalStateException("Exporter must not return null");
        }
        return result;
    }

    /**
     * Subclass hook — format-specific encoding logic.
     * Must never throw for valid input; return an error ExportResult instead.
     */
    protected abstract ExportResult doExport(ExportRequest req);
}
