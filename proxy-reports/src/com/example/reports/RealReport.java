package com.example.reports;

/**
 * Real Subject in the Proxy pattern.
 * Contains the expensive file-loading logic.
 * Clients should never instantiate this directly — go through ReportProxy.
 */
public class RealReport implements Report {

    private final String reportId;
    private final String title;
    private final String classification;
    private String content; // loaded lazily

    public RealReport(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
        this.content = loadFromDisk();
    }

    private String loadFromDisk() {
        System.out.println("[disk] loading report " + reportId + " from disk...");
        try {
            Thread.sleep(120);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Internal report body for " + title;
    }

    @Override
    public void display(User user) {
        System.out.println("REPORT -> id=" + reportId
                + " title=" + title
                + " classification=" + classification
                + " openedBy=" + user.getName());
        System.out.println("CONTENT: " + content);
    }

    public String getClassification() {
        return classification;
    }
}
