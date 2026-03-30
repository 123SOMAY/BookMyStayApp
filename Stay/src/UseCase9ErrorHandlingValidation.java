import java.util.HashMap;
import java.util.Map;

class BookingException extends Exception {
    public BookingException(String message) {
        super(message);
    }
}

class ValidationService {
    private Map<String, Integer> inventory = new HashMap<>();

    public ValidationService() {
        inventory.put("Deluxe", 1);
        inventory.put("Suite", 5);
    }

    public void validateRequest(String guestName, String roomType) throws BookingException {
        if (guestName == null || guestName.trim().isEmpty()) {
            throw new BookingException("INVALID INPUT: Guest name cannot be empty.");
        }

        if (!inventory.containsKey(roomType)) {
            throw new BookingException("INVALID ROOM TYPE: '" + roomType + "' does not exist.");
        }

        if (inventory.get(roomType) <= 0) {
            throw new BookingException("OUT OF STOCK: No available rooms for type '" + roomType + "'.");
        }
    }

    public void processBooking(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
        System.out.println("SUCCESS: Room allocated successfully.");
    }
}

public class UseCase9ErrorHandlingValidation {
    public static void main(String[] args) {
        ValidationService service = new ValidationService();

        String[][] testCases = {
                {"Alice", "Deluxe"},   // Valid
                {"", "Deluxe"},        // Invalid Name
                {"Bob", "Penthouse"},  // Invalid Room Type
                {"Charlie", "Deluxe"}  // Out of Stock (Deluxe was only 1)
        };

        for (String[] test : testCases) {
            String name = test[0];
            String type = test[1];

            try {
                System.out.println("\nProcessing: " + name + " for " + type);
                service.validateRequest(name, type);
                service.processBooking(type);
            } catch (BookingException e) {
                System.err.println("ERROR: " + e.getMessage());
            }
        }

        System.out.println("\nSystem Status: Validation complete. System remains stable.");
    }
}