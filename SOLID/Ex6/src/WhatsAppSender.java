public class WhatsAppSender implements NotificationSender {
    private final AuditLog audit;

    public WhatsAppSender(AuditLog audit) {
        this.audit = audit;
    }

    @Override
    public SendResult send(Notification n) {
        // Validation failure is returned, not thrown — honours base contract
        if (n.phone == null || !n.phone.startsWith("+")) {
            audit.add("WA failed");
            return SendResult.failure("phone must start with + and country code");
        }
        System.out.println("WA -> to=" + n.phone + " body=" + n.body);
        audit.add("wa sent");
        return SendResult.success("wa sent");
    }

    @Override
    public boolean supportsSubject() {
        return false;
    }
}
