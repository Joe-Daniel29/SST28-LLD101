/**
 * Abstraction for a single placement-eligibility rule.
 * Each rule inspects a StudentProfile and returns a failure reason
 * (or null if the student passes the rule).
 */
public interface EligibilityRule {
    /**
     * @return a human-readable reason string if the student fails this rule, or
     *         null if they pass.
     */
    String evaluate(StudentProfile student);
}
