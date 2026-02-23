/** Rule: student CGR must meet a configurable minimum threshold. */
public class CgrRule implements EligibilityRule {
    private final double minCgr;

    public CgrRule(double minCgr) {
        this.minCgr = minCgr;
    }

    @Override
    public String evaluate(StudentProfile student) {
        if (student.cgr < minCgr) {
            return "CGR below " + minCgr;
        }
        return null;
    }
}
