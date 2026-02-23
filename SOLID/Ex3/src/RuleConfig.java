/**
 * Externalised thresholds for eligibility rules.
 * Change these values without touching any rule class.
 */
public class RuleConfig {
    public final double minCgr;
    public final int minAttendance;
    public final int minCredits;

    public RuleConfig(double minCgr, int minAttendance, int minCredits) {
        this.minCgr = minCgr;
        this.minAttendance = minAttendance;
        this.minCredits = minCredits;
    }
}
