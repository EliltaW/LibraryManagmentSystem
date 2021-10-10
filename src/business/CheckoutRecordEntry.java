package business;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutRecordEntry implements Serializable {
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private BookCopy bookCopy;

    public CheckoutRecordEntry (LocalDate checkoutDate, LocalDate dueDate, BookCopy bookCopy) {
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.bookCopy = bookCopy;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }
}
