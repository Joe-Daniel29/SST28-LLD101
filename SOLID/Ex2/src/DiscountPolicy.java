public interface DiscountPolicy {
    double discountAmount(CustomerType customerType, double subtotal, int distinctLines);
}
