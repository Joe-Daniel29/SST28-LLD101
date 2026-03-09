package com.example.reports;

/**
 * Proxy in the Proxy pattern.
 *
 * Responsibilities:
 * 1) Protection — checks access control before delegating to the real subject.
 * 2) Virtual — lazy-initialises RealReport only when access is granted.
 * 3) Caching — reuses the same RealReport instance on repeated calls
 * (avoids expensive disk reload through the same proxy).
 */
public class ReportProxy implements Report {

    private final String reportId;
    private final String title;
    private final String classification;
    private final AccessControl accessControl = new AccessControl();

    // null until first authorised access
    private RealReport realReport;

    public ReportProxy(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
    }

    @Override
    public void display(User user) {
        // 1) Protection: deny unauthorised users immediately
        if (!accessControl.canAccess(user, classification)) {
            System.out.println("[proxy] ACCESS DENIED — " + user.getName()
                    + " (" + user.getRole() + ") cannot view " + classification + " report \"" + title + "\"");
            return;
        }

        // 2) Virtual + Caching: load the real report only once per proxy instance
        if (realReport == null) {
            System.out.println("[proxy] First access — initialising RealReport for \"" + title + "\"");
            realReport = new RealReport(reportId, title, classification);
        } else {
            System.out.println("[proxy] Cache hit — reusing loaded report for \"" + title + "\"");
        }

        // 3) Delegate to real subject
        realReport.display(user);
    }
}
