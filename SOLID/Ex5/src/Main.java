public class Main {
    public static void main(String[] args) {
        System.out.println("=== Export Demo ===");

        ExportRequest req = new ExportRequest("Weekly Report", SampleData.longBody());
        Exporter pdf = new PdfExporter();
        Exporter csv = new CsvExporter();
        Exporter json = new JsonExporter();

        // No try/catch needed — exporters honor LSP, errors are results not exceptions
        System.out.println("PDF: " + describe(pdf.export(req)));
        System.out.println("CSV: " + describe(csv.export(req)));
        System.out.println("JSON: " + describe(json.export(req)));
    }

    private static String describe(ExportResult result) {
        if (result.error) {
            return "ERROR: " + result.errorMessage;
        }
        return "OK bytes=" + result.bytes.length;
    }
}
