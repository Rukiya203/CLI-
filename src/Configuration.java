public class Configuration {

    private final int maxTicketCapacity;
    private final int totalTickets;
    private final int ticketReleaseRate;
    private final int customerRetrievalRate;

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public Configuration(int maxTicketCapacity, int totalTickets, int ticketReleaseRate, int customerRetrievalRate) {
        if (maxTicketCapacity <= 0 || totalTickets <= 0 || ticketReleaseRate <= 0 || customerRetrievalRate <= 0) {
            throw new IllegalArgumentException("All configuration parameters must be positive integers.");
        }
        this.maxTicketCapacity = maxTicketCapacity;
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "maxTicketCapacity=" + maxTicketCapacity +
                ", totalTickets=" + totalTickets +
                ", ticketReleaseRate=" + ticketReleaseRate +
                ", customerRetrievalRate=" + customerRetrievalRate +
                '}';
    }
}
