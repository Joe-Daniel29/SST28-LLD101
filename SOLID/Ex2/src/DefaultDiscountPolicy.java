public class DefaultDiscountPolicy implements DiscountPolicy {
    private static final double STUDENT_MIN_SUBTOTAL = 180.0;
    private static final double STUDENT_DISCOUNT = 10.0;
    private static final int STAFF_MIN_ITEMS = 3;
    private static final double STAFF_DISCOUNT_HIGH = 15.0;
    private static final double STAFF_DISCOUNT_LOW = 5.0;

    @Override
    public double discountAmount(CustomerType customerType, double subtotal, int distinctLines) {
        return switch (customerType) {
            case STUDENT -> subtotal >= STUDENT_MIN_SUBTOTAL ? STUDENT_DISCOUNT : 0.0;
            case STAFF -> distinctLines >= STAFF_MIN_ITEMS ? STAFF_DISCOUNT_HIGH : STAFF_DISCOUNT_LOW;
            case OTHER -> 0.0;
        };
    }
}
