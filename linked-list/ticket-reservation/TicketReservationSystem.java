public class TicketReservationSystem {
    class Node {
        int ticketID;
        String customerName, movieName, seatNumber, bookingTime;
        Node next;

        Node(int ticketID, String customerName, String movieName, String seatNumber, String bookingTime) {
            this.ticketID = ticketID;
            this.customerName = customerName;
            this.movieName = movieName;
            this.seatNumber = seatNumber;
            this.bookingTime = bookingTime;
            this.next = null;
        }
    }

    private Node last;
    private int ticketCount;

    public TicketReservationSystem() {
        this.last = null;
        this.ticketCount = 0;
    }

    public void addTicket(int ticketID, String customerName, String movieName, String seatNumber, String bookingTime) {
        Node newNode = new Node(ticketID, customerName, movieName, seatNumber, bookingTime);
        if (last == null) {
            last = newNode;
            last.next = last;
        } else {
            newNode.next = last.next;
            last.next = newNode;
            last = newNode;
        }
        ticketCount++;
    }

    public void removeTicket(int ticketID) {
        if (last == null) return;
        Node temp = last, prev = null;
        do {
            if (temp.ticketID == ticketID) {
                if (temp == last && temp.next == last) {
                    last = null;
                } else {
                    if (temp == last) {
                        prev.next = last.next;
                        last = prev;
                    } else {
                        prev.next = temp.next;
                    }
                }
                ticketCount--;
                return;
            }
            prev = temp;
            temp = temp.next;
        } while (temp != last.next);
    }

    public void displayTickets() {
        if (last == null) {
            System.out.println("No tickets booked.");
            return;
        }
        Node temp = last.next;
        do {
            System.out.println("Ticket ID: " + temp.ticketID + ", Customer: " + temp.customerName + ", Movie: " + temp.movieName + ", Seat: " + temp.seatNumber + ", Time: " + temp.bookingTime);
            temp = temp.next;
        } while (temp != last.next);
    }

    public Node searchByCustomerName(String customerName) {
        if (last == null) return null;
        Node temp = last.next;
        do {
            if (temp.customerName.equalsIgnoreCase(customerName)) return temp;
            temp = temp.next;
        } while (temp != last.next);
        return null;
    }

    public Node searchByMovieName(String movieName) {
        if (last == null) return null;
        Node temp = last.next;
        do {
            if (temp.movieName.equalsIgnoreCase(movieName)) return temp;
            temp = temp.next;
        } while (temp != last.next);
        return null;
    }

    public int getTotalBookedTickets() {
        return ticketCount;
    }

    public static void main(String[] args) {
        TicketReservationSystem system = new TicketReservationSystem();
        system.addTicket(1, "John Doe", "Inception", "A1", "10:00 AM");
        system.addTicket(2, "Jane Smith", "Avatar", "B2", "12:00 PM");
        system.displayTickets();
        System.out.println("Total Tickets: " + system.getTotalBookedTickets());
        system.removeTicket(1);
        system.displayTickets();
    }
}
