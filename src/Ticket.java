

public class Ticket {
    private String ticketId;

    public Ticket(String ticketId) {
        this.ticketId = ticketId;
    }

    @Override
    public String toString() {
        return "Ticket{id='" + ticketId + "'}";
    }
}
