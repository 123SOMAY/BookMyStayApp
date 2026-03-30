import java.util.ArrayList;
import java.util.List;

class ConfirmedBooking {
    private String guestName;
    private String roomType;
    private String roomId;

    public ConfirmedBooking(String guestName, String roomType, String roomId) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    @Override
    public String toString() {
        return String.format("ID: %-5s | Guest: %-10s | Room Type: %-10s", roomId, guestName, roomType);
    }
}

class BookingHistory {
    private List<ConfirmedBooking> history = new ArrayList<>();

    public void recordBooking(ConfirmedBooking booking) {
        history.add(booking);
    }

    public List<ConfirmedBooking> getHistory() {
        return history;
    }
}

class BookingReportService {
    public void generateSummary(List<ConfirmedBooking> history) {
        System.out.println("\n--- Administrative Booking Report ---");
        if (history.isEmpty()) {
            System.out.println("No records found.");
            return;
        }

        int deluxeCount = 0;
        int suiteCount = 0;

        for (ConfirmedBooking b : history) {
            System.out.println(b);
            if (b.getRoomType().equalsIgnoreCase("Deluxe")) deluxeCount++;
            else if (b.getRoomType().equalsIgnoreCase("Suite")) suiteCount++;
        }

        System.out.println("-------------------------------------");
        System.out.println("Total Confirmed Bookings: " + history.size());
        System.out.println("Deluxe Rooms Booked: " + deluxeCount);
        System.out.println("Suites Booked: " + suiteCount);
        System.out.println("-------------------------------------");
    }
}

public class UseCase8BookingHistoryReport {
    public static void main(String[] args) {
        BookingHistory historyStore = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        historyStore.recordBooking(new ConfirmedBooking("Alice", "Deluxe", "D101"));
        historyStore.recordBooking(new ConfirmedBooking("Bob", "Deluxe", "D102"));
        historyStore.recordBooking(new ConfirmedBooking("Dave", "Suite", "S103"));

        reportService.generateSummary(historyStore.getHistory());

        System.out.println("\nAudit Status: Persistence mindset established. Ready for database integration.");
    }
}
