/**
 * Abstraction for receipt output.
 * Allows swapping print implementations without editing the calculator.
 */
public interface ReceiptFormatter {
    void print(BookingRequest req, Money monthly, Money deposit);
}
