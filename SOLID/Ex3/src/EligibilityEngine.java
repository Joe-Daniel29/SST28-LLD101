import java.util.*;

/**
 * Rules engine that evaluates placement eligibility.
 * <p>
 * OCP-compliant: adding a new rule requires creating a new EligibilityRule
 * class
 * and wiring it into the rule list — this class never needs editing.
 */
public class EligibilityEngine {
    private final List<EligibilityRule> rules;
    private final FakeEligibilityStore store;

    public EligibilityEngine(List<EligibilityRule> rules, FakeEligibilityStore store) {
        this.rules = rules;
        this.store = store;
    }

    public void runAndPrint(StudentProfile s) {
        ReportPrinter p = new ReportPrinter();
        EligibilityEngineResult r = evaluate(s);
        p.print(s, r);
        store.save(s.rollNo, r.status);
    }

    public EligibilityEngineResult evaluate(StudentProfile s) {
        List<String> reasons = new ArrayList<>();
        for (EligibilityRule rule : rules) {
            String reason = rule.evaluate(s);
            if (reason != null) {
                reasons.add(reason);
                break; // preserve original short-circuit: report only the first failing rule
            }
        }
        String status = reasons.isEmpty() ? "ELIGIBLE" : "NOT_ELIGIBLE";
        return new EligibilityEngineResult(status, reasons);
    }
}
