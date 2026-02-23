/**
 * Fee component that sums add-on costs.
 * Each add-on's price comes from config — adding a new add-on means
 * adding an entry to AddOn enum + FeeConfig, not editing this class.
 */
public class AddOnPricingComponent implements FeeComponent {
    private final FeeConfig config;

    public AddOnPricingComponent(FeeConfig config) {
        this.config = config;
    }

    @Override
    public Money monthlyFee(BookingRequest request) {
        double total = 0.0;
        for (AddOn a : request.addOns) {
            total += config.getAddOnPrice(a);
        }
        return new Money(total);
    }
}
