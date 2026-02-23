public class MenuItem {
    public final String id;
    public final String name;
    public final double price;

    public MenuItem(String id, String name, double price) {
        if (price < 0)
            throw new IllegalArgumentException("price cannot be negative: " + price);
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
