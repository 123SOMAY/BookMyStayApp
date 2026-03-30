import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

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