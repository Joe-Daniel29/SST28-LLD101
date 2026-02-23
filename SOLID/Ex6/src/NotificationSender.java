/**
 * Base contract for all notification senders.
 *
 * Preconditions : {@code n} is non-null.
 * Postconditions : returns a {@link SendResult} — never throws on invalid
 * input.
 *
 * Senders MAY ignore {@code subject} if {@link #supportsSubject()} returns
 * false.
 * Senders MUST NOT silently truncate or alter the body.
 */
public interface NotificationSender {

    SendResult send(Notification n);

    /** Whether this channel uses the subject field. Default is true. */
    default boolean supportsSubject() {
        return true;
    }
}
