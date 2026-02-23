public class InvoiceIdGenerator {
    private int seq = 1000;

    public String next() {
        return "INV-" + (++seq);
    }
}
