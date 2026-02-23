public class DefaultDiscountPolicy implements DiscountPolicy {
    @Override
    public double discountAmount(CustomerType customerType, double subtotal, int distinctLines) {
        return switch (customerType) {
            case STUDENT -> subtotal >= 180.0 ? 10.0 : 0.0;
            case STAFF -> distinctLines >= 3 ? 15.0 : 5.0;
            default -> 0.0;
        };
    }
}
