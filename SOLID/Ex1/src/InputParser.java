import java.util.*;

public class InputParser {

    public Map<String, String> parse(String raw) {
        Map<String, String> kv = new LinkedHashMap<>();
        String[] parts = raw.split(";");
        for (String p : parts) {
            if (p.trim().isEmpty())
                continue;
            String[] t = p.split("=", 2);
            if (t.length == 2) {
                kv.put(t[0].trim(), t[1].trim());
            } else {
                throw new IllegalArgumentException("Malformed token: " + p.trim());
            }
        }
        return kv;
    }
}
