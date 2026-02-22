import java.util.*;

public class OnboardingService {
    private final InputParser parser;
    private final StudentValidator validator;
    private final StudentRepository repository;
    private final OnboardingPrinter printer;
    private final IdGenerator idGenerator;

    public OnboardingService(InputParser parser, StudentValidator validator,
            StudentRepository repository, OnboardingPrinter printer, IdGenerator idGenerator) {
        this.parser = parser;
        this.validator = validator;
        this.repository = repository;
        this.printer = printer;
        this.idGenerator = idGenerator;
    }

    public void registerFromRawInput(String raw) {
        printer.printInput(raw);

        Map<String, String> fields = parser.parse(raw);

        List<String> errors = validator.validate(fields);
        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }

        String id = idGenerator.next(repository.count());
        StudentRecord rec = new StudentRecord(
                id,
                fields.get("name"),
                fields.get("email"),
                fields.get("phone"),
                fields.get("program"));

        repository.save(rec);

        printer.printSuccess(id, repository.count(), rec);
    }
}
