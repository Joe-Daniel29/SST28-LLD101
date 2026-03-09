package com.example.reports;

/**
 * Client-facing viewer.
 * Depends only on the Report interface, not on any concrete implementation.
 * Works transparently whether passed a ReportProxy or a RealReport.
 */
public class ReportViewer {

    public void open(Report report, User user) {
        report.display(user);
    }
}
