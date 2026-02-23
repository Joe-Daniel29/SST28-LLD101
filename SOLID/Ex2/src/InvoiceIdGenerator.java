public class InvoiceIdGenerator implements IdGenerator {
    private int seq = 1000;

    @Override
    public String next() {
        return "INV-" + (++seq);
    }
}
