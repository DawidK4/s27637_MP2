import java.time.LocalDate;

public class Borrow {
    private LocalDate dateOfBorrow;
    private LocalDate returnDeadline;
    private LocalDate returnDate; // Nullable
    private float penalty;

    public Borrow(LocalDate dateOfBorrow, LocalDate returnDeadline, LocalDate returnDate, float penalty) {
        this.setDateOfBorrow(dateOfBorrow);
        this.setReturnDeadline(returnDeadline);
        this.setReturnDate(returnDate);
        this.setPenalty(penalty);
    }

    public Borrow(LocalDate dateOfBorrow, LocalDate returnDeadline) {
        this(dateOfBorrow, returnDeadline, null, 0.0f);
    }

    public LocalDate getDateOfBorrow() {
        return dateOfBorrow;
    }

    public void setDateOfBorrow(LocalDate dateOfBorrow) {
        if (dateOfBorrow == null) {
            throw new IllegalArgumentException("Date of borrow cannot be null!");
        }
        if (dateOfBorrow.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date of borrow cannot be in the future!");
        }
        this.dateOfBorrow = dateOfBorrow;
    }

    public LocalDate getReturnDeadline() {
        return returnDeadline;
    }

    public void setReturnDeadline(LocalDate returnDeadline) {
        if (returnDeadline == null) {
            throw new IllegalArgumentException("Return deadline cannot be null!");
        }
        if (this.dateOfBorrow != null && returnDeadline.isBefore(this.dateOfBorrow)) {
            throw new IllegalArgumentException("Return deadline cannot be before the date of borrow!");
        }
        this.returnDeadline = returnDeadline;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        if (returnDate != null && this.dateOfBorrow != null && returnDate.isBefore(this.dateOfBorrow)) {
            throw new IllegalArgumentException("Return date cannot be before the date of borrow!");
        }
        this.returnDate = returnDate;
    }

    public float getPenalty() {
        return penalty;
    }

    public void setPenalty(float penalty) {
        if (penalty < 0) {
            throw new IllegalArgumentException("Penalty cannot be negative!");
        }
        this.penalty = penalty;
    }
}