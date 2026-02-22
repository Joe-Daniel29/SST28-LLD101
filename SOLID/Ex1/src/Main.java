public class Main {
    public static void main(String[] args) {
        System.out.println("=== Student Onboarding ===");

        StudentRepository db = new FakeDb();
        OnboardingPrinter printer = new OnboardingPrinter();

        OnboardingService svc = new OnboardingService(
                new InputParser(),
                new StudentValidator(),
                db,
                printer,
                new IdUtil());

        String raw = "name=Riya;email=riya@sst.edu;phone=9876543210;program=CSE";
        svc.registerFromRawInput(raw);

        printer.printDbDump(db);

        System.out.println();
        String bad = "name=;email=bad;phone=abc;program=UNKNOWN";
        svc.registerFromRawInput(bad);
    }
}
