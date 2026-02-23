/**
 * Fee component for room base pricing.
 * Looks up the room type price from config — no switch-case needed.
 */
public class RoomPricingComponent implements FeeComponent {
    private final FeeConfig config;

    public RoomPricingComponent(FeeConfig config) {
        this.config = config;
    }

    @Override
    public Money monthlyFee(BookingRequest request) {
        return new Money(config.getRoomPrice(request.roomType));
    }
}
