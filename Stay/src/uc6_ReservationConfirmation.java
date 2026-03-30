import java.util.LinkedList;
import java.util.Queue;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Reservation {
    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return guestName + " (" + roomType + ")";
    }
}

class RoomAllocationService {
    private Queue<Reservation> requestQueue = new LinkedList<>();
    private Map<String, Integer> inventory = new HashMap<>();
    private Map<String, Set<String>> allocatedRooms = new HashMap<>();
    private int roomCounter = 100;

    public RoomAllocationService() {
        inventory.put("Deluxe", 2);
        inventory.put("Suite", 1);
        allocatedRooms.put("Deluxe", new HashSet<>());
        allocatedRooms.put("Suite", new HashSet<>());
    }

    public void addRequest(Reservation res) {
        requestQueue.add(res);
    }

    public void processAllocations() {
        System.out.println("--- Starting Room Allocation Process ---");

        while (!requestQueue.isEmpty()) {
            Reservation request = requestQueue.poll();
            String type = request.roomType;

            if (inventory.containsKey(type) && inventory.get(type) > 0) {
                String roomId = type.substring(0, 1) + (++roomCounter);

                allocatedRooms.get(type).add(roomId);
                inventory.put(type, inventory.get(type) - 1);

                System.out.println("CONFIRMED: " + request.guestName + " assigned to " + roomId + " [" + type + "]");
            } else {
                System.out.println("REJECTED: No availability for " + request.guestName + " [" + type + "]");
            }
        }
    }

    public void displayStatus() {
        System.out.println("\n--- Final Inventory Status ---");
        inventory.forEach((type, count) -> System.out.println(type + " left: " + count));
        System.out.println("Allocated IDs: " + allocatedRooms);
    }
}

public class UseCase6RoomAllocationService {
    public static void main(String[] args) {
        RoomAllocationService service = new RoomAllocationService();

        service.addRequest(new Reservation("Alice", "Deluxe"));
        service.addRequest(new Reservation("Bob", "Deluxe"));
        service.addRequest(new Reservation("Charlie", "Deluxe"));
        service.addRequest(new Reservation("Dave", "Suite"));

        service.processAllocations();
        service.displayStatus();
    }
}
