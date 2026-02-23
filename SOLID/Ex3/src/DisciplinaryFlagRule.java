/** Rule: student must have no disciplinary flag. */
public class DisciplinaryFlagRule implements EligibilityRule {

    @Override
    public String evaluate(StudentProfile student) {
        if (student.disciplinaryFlag != LegacyFlags.NONE) {
            return "disciplinary flag present";
        }
        return null;
    }
}
