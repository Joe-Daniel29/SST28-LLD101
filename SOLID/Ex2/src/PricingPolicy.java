import java.util.*;

public interface PricingPolicy {
    Invoice calculate(String invoiceId, String customerType,
            List<OrderLine> lines, Map<String, MenuItem> menu);
}
