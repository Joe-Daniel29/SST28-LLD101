import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

/**
 * Starter demo that shows why mutability is risky.
 *
 * After refactor:
 * - direct mutation should not compile (no setters)
 * - external modifications to tags should not affect the ticket
 * - service "updates" should return a NEW ticket instance
 */
public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        IncidentTicket t = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created: " + t);
        System.out.println("Ticket HashCode: " + System.identityHashCode(t));

        // Demonstrate post-creation mutation through service returning NEW objects
        IncidentTicket assignedTicket = service.assign(t, "agent@example.com");
        IncidentTicket finalTicket = service.escalateToCritical(assignedTicket);

        System.out.println("\nAfter service updates (new instance created):");
        System.out.println("Final Ticket: " + finalTicket);
        System.out.println("Final Ticket HashCode: " + System.identityHashCode(finalTicket));
        System.out.println("Original Ticket remains unchanged: " + t);

        // Demonstrate external mutation via leaked list reference failing
        System.out.println("\nAttempting to mutate tags externally...");
        try {
            List<String> tags = finalTicket.getTags();
            tags.add("HACKED_FROM_OUTSIDE");
        } catch (UnsupportedOperationException e) {
            System.out.println("Caught UnsupportedOperationException! The tags list is immutable.");
        }
        System.out.println("Tags list remains: " + finalTicket.getTags());

        // Demonstrate equals() and hashCode() functionality
        System.out.println("\nDemonstrating value equality...");
        IncidentTicket finalTicketCopy = finalTicket.toBuilder().build();
        System.out.println("finalTicket equals finalTicketCopy: " + finalTicket.equals(finalTicketCopy));
        System.out.println("finalTicket hashCode: " + finalTicket.hashCode());
        System.out.println("finalTicketCopy hashCode: " + finalTicketCopy.hashCode());
    }
}
