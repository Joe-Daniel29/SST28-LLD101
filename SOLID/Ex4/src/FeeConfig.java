import java.util.*;

/**
 * Externalised pricing configuration.
 * Change thresholds here without touching any FeeComponent class.
 */
public class FeeConfig {
    private final Map<Integer, Double> roomPrices;
    private final Map<AddOn, Double> addOnPrices;
    private final double deposit;

    public FeeConfig(Map<Integer, Double> roomPrices, Map<AddOn, Double> addOnPrices, double deposit) {
        this.roomPrices = roomPrices;
        this.addOnPrices = addOnPrices;
        this.deposit = deposit;
    }

    public double getRoomPrice(int roomType) {
        return roomPrices.getOrDefault(roomType, 16000.0);
    }

    public double getAddOnPrice(AddOn addOn) {
        return addOnPrices.getOrDefault(addOn, 0.0);
    }

    public Money getDeposit() {
        return new Money(deposit);
    }
}
