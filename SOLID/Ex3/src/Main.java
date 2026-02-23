import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Placement Eligibility ===");

        // Externalised thresholds — change here, not inside any rule class
        RuleConfig cfg = new RuleConfig(8.0, 75, 20);

        // Ordered rule list — evaluation priority matches the original if/else chain
        List<EligibilityRule> rules = new ArrayList<>();
        rules.add(new DisciplinaryFlagRule());
        rules.add(new CgrRule(cfg.minCgr));
        rules.add(new AttendanceRule(cfg.minAttendance));
        rules.add(new CreditsRule(cfg.minCredits));

        EligibilityEngine engine = new EligibilityEngine(rules, new FakeEligibilityStore());

        StudentProfile s = new StudentProfile("23BCS1001", "Ayaan", 8.10, 72, 18, LegacyFlags.NONE);
        engine.runAndPrint(s);
    }
}
