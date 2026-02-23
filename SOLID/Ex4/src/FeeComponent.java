/**
 * A pricing component that contributes to the monthly hostel fee.
 * Both room pricing and add-on pricing implement this interface.
 * Adding a new fee component (e.g., late fee) = new class, zero edits to
 * calculator.
 */
public interface FeeComponent {
    /** Return the monthly fee contribution for this component. */
    Money monthlyFee(BookingRequest request);
}
