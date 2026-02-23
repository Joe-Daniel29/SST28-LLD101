public class SendResult {
    private final boolean success;
    private final String message;

    private SendResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static SendResult success(String message) {
        return new SendResult(true, message);
    }

    public static SendResult failure(String reason) {
        return new SendResult(false, reason);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
