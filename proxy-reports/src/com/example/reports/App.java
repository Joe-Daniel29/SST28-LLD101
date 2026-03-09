package com.example.reports;

/**
 * CampusVault CLI entry point.
 *
 * AFTER REFACTOR:
 * - Clients use ReportProxy (not ReportFile directly)
 * - Unauthorized access is blocked by the proxy
 * - First access triggers real disk load; subsequent accesses reuse the cache
 */
public class App {

    public static void main(String[] args) {
        User student = new User("Jasleen", "STUDENT");
        User faculty = new User("Prof. Noor", "FACULTY");
        User admin = new User("Kshitij", "ADMIN");

        // Clients now hold proxies, not raw ReportFile objects
        Report publicReport = new ReportProxy("R-101", "Orientation Plan", "PUBLIC");
        Report facultyReport = new ReportProxy("R-202", "Midterm Review", "FACULTY");
        Report adminReport = new ReportProxy("R-303", "Budget Audit", "ADMIN");

        ReportViewer viewer = new ReportViewer();

        System.out.println("=== CampusVault Demo ===\n");

        // PUBLIC report — every role can read it
        System.out.println("-- Student opens PUBLIC report --");
        viewer.open(publicReport, student);
        System.out.println();

        // FACULTY report — student should be denied
        System.out.println("-- Student tries FACULTY report (should be denied) --");
        viewer.open(facultyReport, student);
        System.out.println();

        // FACULTY report — faculty can read it (triggers first disk load)
        System.out.println("-- Faculty opens FACULTY report (first access → disk load) --");
        viewer.open(facultyReport, faculty);
        System.out.println();

        // ADMIN report — admin reads it once (disk load)
        System.out.println("-- Admin opens ADMIN report (first access → disk load) --");
        viewer.open(adminReport, admin);
        System.out.println();

        // ADMIN report second time — should show cache hit, no reload
        System.out.println("-- Admin opens ADMIN report again (should be cache hit) --");
        viewer.open(adminReport, admin);
    }
}
