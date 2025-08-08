
import java.util.Date;

public abstract class Transaction {
    protected double amount;
    protected String description;
    protected Date date;
    protected Category category;

    public Transaction(double amount, String description, Date date, Category category) {
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public Category getCategory() {
        return category;
    }

    public abstract String getType();

    public String toString() {
        return String.format("%s | %.2f | %s | %s | %s", getType(), amount, description, date.toString(), category);
    }
}
