public class DefaultTaxPolicy implements TaxPolicy {
    @Override
    public double taxPercent(CustomerType customerType) {
        return switch (customerType) {
            case STUDENT -> 5.0;
            case STAFF -> 2.0;
            default -> 8.0;
        };
    }
}
