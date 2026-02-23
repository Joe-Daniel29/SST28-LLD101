public class OrderLine {
    public final String itemId;
    public final int qty;

    public OrderLine(String itemId, int qty) {
        if (qty <= 0)
            throw new IllegalArgumentException("qty must be positive: " + qty);
        this.itemId = itemId;
        this.qty = qty;
    }
}
