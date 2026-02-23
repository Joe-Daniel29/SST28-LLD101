public class EmailSender implements NotificationSender {
    private final AuditLog audit;

    public EmailSender(AuditLog audit) {
        this.audit = audit;
    }

    @Override
    public SendResult send(Notification n) {
        // No truncation — honours the base contract fully
        System.out.println("EMAIL -> to=" + n.email + " subject=" + n.subject + " body=" + n.body);
        audit.add("email sent");
        return SendResult.success("email sent");
    }
}
