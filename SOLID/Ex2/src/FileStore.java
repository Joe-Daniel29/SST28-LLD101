import java.util.*;

public class FileStore implements InvoiceStore {
    private final Map<String, String> files = new HashMap<>();

    @Override
    public void save(String name, String content) {
        files.put(name, content);
    }
}
