

import java.util.Scanner;

public class Main {
    private static Ticket ticket;
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Get configuration parameters from the user
        System.out.println("Enter total number of tickets to produce:");
        int totalTickets = scanner.nextInt();

        System.out.println("Enter ticket release rate (tickets per second):");
        int ticketReleaseRate = scanner.nextInt();

        System.out.println("Enter customer retrieval rate (tickets per second):");
        int customerRetrievalRate = scanner.nextInt();

        System.out.println("Enter maximum ticket pool capacity:");
        int maxTicketCapacity = scanner.nextInt();

        System.out.println("Enter the number of producers:");
        int numProducers = scanner.nextInt();

        System.out.println("Enter the number of consumers:");
        int numConsumers = scanner.nextInt();

        Configuration configuration = new Configuration(maxTicketCapacity,totalTickets,ticketReleaseRate,customerRetrievalRate);

        // Create the ticket pool
        Ticketpool ticketPool = new Ticketpool(configuration);

        // Create and start producer threads
        for (int i = 1; i <= numProducers; i++) {
            Thread producer = new Thread(new Producer(ticketPool, ticketReleaseRate), "Producer-" + i);
            producer.start();

        }

        // Create and start consumer threads
        for (int i = 1; i <= numConsumers; i++) {
            Thread consumer = new Thread(new Consumer(ticketPool, customerRetrievalRate), "Consumer-" + i);
            consumer.start();
        }

        scanner.close();


    }
}
