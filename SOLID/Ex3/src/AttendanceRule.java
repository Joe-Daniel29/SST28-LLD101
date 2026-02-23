/** Rule: student attendance percentage must meet a configurable minimum. */
public class AttendanceRule implements EligibilityRule {
    private final int minAttendance;

    public AttendanceRule(int minAttendance) {
        this.minAttendance = minAttendance;
    }

    @Override
    public String evaluate(StudentProfile student) {
        if (student.attendancePct < minAttendance) {
            return "attendance below " + minAttendance;
        }
        return null;
    }
}
