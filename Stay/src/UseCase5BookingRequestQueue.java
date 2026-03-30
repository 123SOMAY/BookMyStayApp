import java.util.LinkedList;
import java.util.Queue;

class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "Reservation [Guest: " + guestName + ", Room: " + roomType + "]";
    }
}

class BookingRequestQueue {
    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        this.requestQueue = new LinkedList<>();
    }

    public void submitRequest(Reservation reservation) {
        requestQueue.add(reservation);
        System.out.println("Request added to queue: " + reservation);
    }

    public void displayQueue() {
        System.out.println("\n--- Current    Booking Request Queue (FIFO) ---");
        if (requestQueue.isEmpty()) {
            System.out.println("Queue is already empty.");
        } else {
            for (Reservation res : requestQueue) {
                System.out.println(res);
            }
        }
    }

    public Queue<Reservation> getQueue() {
        return requestQueue;
    }
}

public class UseCase5BookingRequestQueue {
    public static void main(String[] args) {
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        bookingQueue.submitRequest(new Reservation("Alice", "Deluxe"));
        bookingQueue.submitRequest(new Reservation("Bob", "Suite"));
        bookingQueue.submitRequest(new Reservation("Charlie", "Single"));

        bookingQueue.displayQueue();

        System.out.println("\nSystem Status:     Requests stored in order. No inventory has been modified.");
    }
}
