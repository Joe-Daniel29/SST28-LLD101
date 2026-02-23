import java.util.*;

/**
 * Pure orchestrator — sums FeeComponent contributions, delegates printing and
 * persistence.
 * Never branches on room type or add-on. OCP-compliant: adding a new fee
 * component
 * requires zero edits here.
 */
public class HostelFeeCalculator {
    private final List<FeeComponent> components;
    private final FeeConfig config;
    private final ReceiptFormatter printer;
    private final BookingStore store;
    private final BookingIdGenerator idGenerator;

    public HostelFeeCalculator(List<FeeComponent> components, FeeConfig config,
            ReceiptFormatter printer, BookingStore store, BookingIdGenerator idGenerator) {
        this.components = components;
        this.config = config;
        this.printer = printer;
        this.store = store;
        this.idGenerator = idGenerator;
    }

    public void process(BookingRequest req) {
        Money monthly = calculateMonthly(req);
        Money deposit = config.getDeposit();

        printer.print(req, monthly, deposit);

        String bookingId = idGenerator.next();
        store.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(BookingRequest req) {
        Money total = new Money(0);
        for (FeeComponent component : components) {
            total = total.plus(component.monthlyFee(req));
        }
        return total;
    }
}
