

public class Consumer implements Runnable {
    private final Ticketpool ticketPool;
    private final int customerRetrievalRate;

    public Consumer(Ticketpool ticketPool, int customerRetrievalRate) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    @Override
    public void run() {
        while (true) {
            Ticket ticket = ticketPool.removeTicket();
            if (ticket == null) {
                System.out.println(Thread.currentThread().getName() + " has no more tickets to consume.");
                break;
            }
            try {
                Thread.sleep(1000 / customerRetrievalRate); // Simulate delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
