public class SmsSender implements NotificationSender {
    private final AuditLog audit;

    public SmsSender(AuditLog audit) {
        this.audit = audit;
    }

    @Override
    public SendResult send(Notification n) {
        // Subject is not used — declared via supportsSubject(), not a violation
        System.out.println("SMS -> to=" + n.phone + " body=" + n.body);
        audit.add("sms sent");
        return SendResult.success("sms sent");
    }

    @Override
    public boolean supportsSubject() {
        return false;
    }
}
