
import java.util.Date;

public class Income extends Transaction {
    public Income(double amount, String description, Date date, Category category) {
        super(amount, description, date, category);
    }

    @Override
    public String getType() {
        return "Income";
    }
}
