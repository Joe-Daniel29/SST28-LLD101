/** Rule: student must have earned a configurable minimum number of credits. */
public class CreditsRule implements EligibilityRule {
    private final int minCredits;

    public CreditsRule(int minCredits) {
        this.minCredits = minCredits;
    }

    @Override
    public String evaluate(StudentProfile student) {
        if (student.earnedCredits < minCredits) {
            return "credits below " + minCredits;
        }
        return null;
    }
}
