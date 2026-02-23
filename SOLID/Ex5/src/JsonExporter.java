import java.nio.charset.StandardCharsets;

/**
 * JSON exporter — consistent with base contract (null handled by base class).
 */
public class JsonExporter extends Exporter {
    @Override
    protected ExportResult doExport(ExportRequest req) {
        // No null-request guard needed — base class enforces non-null precondition
        String json = "{\"title\":\"" + escape(req.title) + "\",\"body\":\"" + escape(req.body) + "\"}";
        return new ExportResult("application/json", json.getBytes(StandardCharsets.UTF_8));
    }

    private String escape(String s) {
        if (s == null)
            return "";
        return s.replace("\"", "\\\"");
    }
}
