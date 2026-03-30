import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class CancellationService {
    public Map<String, Integer> inventory = new HashMap<>();
    public Map<String, String> activeBookings = new HashMap<>();
    public Stack<String> releasedRooms = new Stack<>();

    public CancellationService() {
        inventory.put("Deluxe", 5);
        inventory.put("Suite", 2);
    }

    public void confirmBooking(String guestName, String roomType, String roomId) {
        activeBookings.put(guestName, roomType + ":" + roomId);
        inventory.put(roomType, inventory.get(roomType) - 1);
        System.out.println("BOOKED: " + guestName + " assigned to " + roomId);
    }

    public void cancelBooking(String guestName) {
        System.out.println("\nInitiating cancellation for: " + guestName);

        if (!activeBookings.containsKey(guestName)) {
            System.out.println("CANCELLATION ERROR: No active booking found for " + guestName);
            return;
        }

        String bookingData = activeBookings.remove(guestName);
        String[] details = bookingData.split(":");
        String type = details[0];
        String id = details[1];

        releasedRooms.push(id);
        inventory.put(type, inventory.get(type) + 1);

        System.out.println("SUCCESS: " + guestName + "'s reservation cancelled.");
        System.out.println("Room " + id + " added to rollback stack.");
        System.out.println("Inventory for " + type + " restored to " + inventory.get(type));
    }

    public void displayStatus() {
        System.out.println("\n--- Current System State ---");
        System.out.println("Active Bookings: " + activeBookings.keySet());
        System.out.println("Room Inventory: " + inventory);
        System.out.println("Released Room IDs (LIFO Stack): " + releasedRooms);
    }
}

public class UseCase10BookingCancellation {
    public static void main(String[] args) {
        CancellationService service = new CancellationService();

        service.confirmBooking("Alice", "Deluxe", "D101");
        service.confirmBooking("Bob", "Suite", "S201");
        service.confirmBooking("Charlie", "Deluxe", "D102");

        service.cancelBooking("Bob");
        service.cancelBooking("Alice");

        service.cancelBooking("Eve");

        service.displayStatus();
    }
}