import java.time.LocalDate;
import java.util.Set;

public class BookCopy {
    private static int idCounter = 1;

    private int id;
    private String status;
    private int shelfNumber;
    private LocalDate dateOfPurchase;

    private static final Set<String> ALLOWED_STATUSES = Set.of("available", "borrowed", "damaged");

    public BookCopy(String status, int shelfNumber, LocalDate dateOfPurchase) {
        this.id = idCounter++;
        setStatus(status);
        setShelfNumber(shelfNumber);
        setDateOfPurchase(dateOfPurchase);
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status must not be null or empty.");
        }

        String normalizedStatus = status.trim().toLowerCase();
        if (!ALLOWED_STATUSES.contains(normalizedStatus)) {
            throw new IllegalArgumentException("Invalid status. Allowed values are: available, borrowed, damaged.");
        }

        this.status = normalizedStatus;
    }

    public int getShelfNumber() {
        return shelfNumber;
    }

    public void setShelfNumber(int shelfNumber) {
        if (shelfNumber <= 0) {
            throw new IllegalArgumentException("Shelf number must be a positive integer.");
        }
        this.shelfNumber = shelfNumber;
    }

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(LocalDate dateOfPurchase) {
        if (dateOfPurchase == null) {
            throw new IllegalArgumentException("Date of purchase must not be null.");
        }
        if (dateOfPurchase.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date of purchase cannot be in the future.");
        }
        this.dateOfPurchase = dateOfPurchase;
    }
}
