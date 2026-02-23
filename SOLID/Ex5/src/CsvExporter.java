import java.nio.charset.StandardCharsets;

/**
 * CSV exporter — sanitises fields for CSV compatibility.
 * Newlines and commas are replaced with spaces to ensure single-cell output.
 * This is an intentional format-appropriate encoding, not data corruption.
 */
public class CsvExporter extends Exporter {
    @Override
    protected ExportResult doExport(ExportRequest req) {
        String title = req.title == null ? "" : sanitise(req.title);
        String body = req.body == null ? "" : sanitise(req.body);
        String csv = "title,body\n" + title + "," + body + "\n";
        return new ExportResult("text/csv", csv.getBytes(StandardCharsets.UTF_8));
    }

    /** Replace characters that would break CSV cell boundaries. */
    private String sanitise(String field) {
        return field.replace("\n", " ").replace(",", " ");
    }
}
