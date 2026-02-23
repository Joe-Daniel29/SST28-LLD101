import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Hostel Fee Calculator ===");

        // Externalised pricing config
        Map<Integer, Double> roomPrices = new LinkedHashMap<>();
        roomPrices.put(LegacyRoomTypes.SINGLE, 14000.0);
        roomPrices.put(LegacyRoomTypes.DOUBLE, 15000.0);
        roomPrices.put(LegacyRoomTypes.TRIPLE, 12000.0);

        Map<AddOn, Double> addOnPrices = new LinkedHashMap<>();
        addOnPrices.put(AddOn.MESS, 1000.0);
        addOnPrices.put(AddOn.LAUNDRY, 500.0);
        addOnPrices.put(AddOn.GYM, 300.0);

        FeeConfig config = new FeeConfig(roomPrices, addOnPrices, 5000.0);

        // Pricing components — add new ones here without touching the calculator
        List<FeeComponent> components = new ArrayList<>();
        components.add(new RoomPricingComponent(config));
        components.add(new AddOnPricingComponent(config));

        ReceiptFormatter printer = new ReceiptPrinter();
        BookingStore store = new FakeBookingRepo();
        BookingIdGenerator idGen = new BookingIdGenerator(1);

        HostelFeeCalculator calc = new HostelFeeCalculator(components, config, printer, store, idGen);

        BookingRequest req = new BookingRequest(LegacyRoomTypes.DOUBLE, List.of(AddOn.LAUNDRY, AddOn.MESS));
        calc.process(req);
    }
}
