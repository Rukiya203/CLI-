import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ticketpool {
    private final List<Ticket> ticketList;
//    private final int maxTicketCapacity;
//    private final int totalTickets;
    private final  Configuration configuration;
    private int ticketsProduced = 0; // Total tickets produced
    private int ticketsConsumed = 0; // Total tickets consumed

    public Ticketpool(Configuration configuration) {
        this.ticketList = Collections.synchronizedList(new ArrayList<>());
        this.configuration=configuration;

    }

    // Producer adds tickets to the pool
    public boolean addTicket(Ticket ticket) {
        synchronized (ticketList) {
            while (ticketList.size() >= configuration.getMaxTicketCapacity() || ticketsProduced >= configuration.getTotalTickets()) {
                if (ticketsProduced >= configuration.getTotalTickets()) {
                    return false; // All tickets have been produced
                }
                try {
                    System.out.println(Thread.currentThread().getName() + " is waiting to add tickets...");
                    ticketList.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return false;
                }
            }
            ticketList.add(ticket);
            ticketsProduced++;
            System.out.println(Thread.currentThread().getName() + " added ticket: " + ticket +
                    " | Tickets Produced: " + ticketsProduced +
                    " | Current Pool Size: " + ticketList.size());
            ticketList.notifyAll();
            return true;
        }
    }

    // Consumer removes a ticket from the pool
    public Ticket removeTicket() {
        synchronized (ticketList) {
            while (ticketList.isEmpty()) {
                try {
                    System.out.println(Thread.currentThread().getName() + " is waiting for tickets...");
                    ticketList.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return null;
                }
            }
            Ticket ticket = ticketList.remove(0);
            ticketsConsumed++;
            System.out.println(Thread.currentThread().getName() + " removed ticket: " + ticket +
                    " | Tickets Consumed: " + ticketsConsumed +
                    " | Current Pool Size: " + ticketList.size());
            ticketList.notifyAll();
            return ticket;
        }
    }

    // Get total tickets left to be consumed
    public synchronized int getTotalTicketsLeft() {
        return ticketsProduced - ticketsConsumed;
    }
}
