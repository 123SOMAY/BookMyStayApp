import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Service {
    String name;
    double price;

    public Service(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " ($" + price + ")";
    }
}

class AddOnServiceManager {
    private Map<String, List<Service>> reservationAddOns = new HashMap<>();

    public void addServiceToReservation(String reservationId, Service service) {
        reservationAddOns.putIfAbsent(reservationId, new ArrayList<>());
        reservationAddOns.get(reservationId).add(service);
        System.out.println("Service Added: " + service.name + " for Booking ID: " + reservationId);
    }

    public void calculateTotalAndDisplay(String reservationId) {
        System.out.println("\n--- Add-On Details for " + reservationId + " ---");
        List<Service> services = reservationAddOns.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No add-on services selected.");
            return;
        }

        double totalCost = 0;
        for (Service s : services) {
            System.out.println("- " + s);
            totalCost += s.price;
        }
        System.out.println("Total Additional Cost: $" + totalCost);
    }
}

public class UseCase7AddOnServiceSelection {
    public static void main(String[] args) {
        AddOnServiceManager manager = new AddOnServiceManager();

        String bookingId1 = "D101"; // Alice's Room
        String bookingId2 = "S102"; // Dave's Room

        manager.addServiceToReservation(bookingId1, new Service("Breakfast Buffet", 25.0));
        manager.addServiceToReservation(bookingId1, new Service("Late Check-out", 15.0));

        manager.addServiceToReservation(bookingId2, new Service("Airport Pickup", 50.0));

        manager.calculateTotalAndDisplay(bookingId1);
        manager.calculateTotalAndDisplay(bookingId2);

        System.out.println("\nNote: Core inventory and room status remain untouched by these additions.");
    }
}