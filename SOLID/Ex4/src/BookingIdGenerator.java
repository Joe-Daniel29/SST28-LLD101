import java.util.Random;

/**
 * Generates deterministic booking IDs (matches original behaviour).
 */
public class BookingIdGenerator {
    private final Random rng;

    public BookingIdGenerator(long seed) {
        this.rng = new Random(seed);
    }

    public String next() {
        return "H-" + (7000 + rng.nextInt(1000));
    }
}
