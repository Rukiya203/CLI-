import java.util.UUID;

public class Producer implements Runnable {
    private final Ticketpool ticketPool;
    private final int ticketReleaseRate;

    public Producer(Ticketpool ticketPool, int ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    @Override
    public void run() {
        while (true) {
            String ticketId = UUID.randomUUID().toString(); // Generate a unique UUID as ticket ID
            Ticket ticket = new Ticket(ticketId);
            boolean added = ticketPool.addTicket(ticket);

            if (!added) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + " was interrupted. Stopping production.");
                } else {
                    System.out.println(Thread.currentThread().getName() + " has produced all tickets.");
                }
                break; // Stop producing when all tickets are produced or interrupted
            }

            try {
                Thread.sleep((long) (1000.0 / ticketReleaseRate)); // Simulate delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break; // Exit if interrupted
            }
        }
    }
}
