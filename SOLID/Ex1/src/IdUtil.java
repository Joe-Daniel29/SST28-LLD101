import java.time.Year;

public class IdUtil implements IdGenerator {
    public String next(int currentCount) {
        int next = currentCount + 1;
        String num = String.format("%04d", next);
        return "SST-" + Year.now().getValue() + "-" + num;
    }
}
