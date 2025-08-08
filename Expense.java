
import java.util.Date;

public class Expense extends Transaction {
    public Expense(double amount, String description, Date date, Category category) {
        super(amount, description, date, category);
    }

    @Override
    public String getType() {
        return "Expense";
    }
}
