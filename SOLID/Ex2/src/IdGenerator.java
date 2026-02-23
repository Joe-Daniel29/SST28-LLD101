/**
 * Abstraction for invoice ID generation.
 * Allows swapping strategies (sequential, UUID, etc.) without modifying
 * CafeteriaSystem.
 */
public interface IdGenerator {
    String next();
}
