import java.util.*;

public class BudgetTracker {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Budget Tracker ---");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View All Transactions");
            System.out.println("4. Show Summary");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue; // return to main loop
            }

            switch (choice) {
                case 1 -> addTransaction("Income");
                case 2 -> addTransaction("Expense");
                case 3 -> viewTransactions();
                case 4 -> showSummary();
                case 5 -> {
                    System.out.println("Exiting... Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void addTransaction(String type) {
        System.out.print("Enter amount: ");
        double amount;

        try {
            amount = Double.parseDouble(scanner.nextLine());
            if (amount <= 0) {
                System.out.println("Amount must be greater than 0.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a valid number.");
            return;
        }

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.println("Select a category:");
        for (Category c : Category.values()) {
            System.out.println("- " + c);
        }

        System.out.print("Enter category: ");
        String input = scanner.nextLine().toUpperCase();

        Category category;
        try {
            category = Category.valueOf(input);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid category. Please try again.");
            return;
        }

        Date date = new Date(); // current date

        Transaction t = type.equals("Income")
                ? new Income(amount, description, date, category)
                : new Expense(amount, description, date, category);

        transactions.add(t);
        System.out.println(type + " added successfully!");
    }

    private static void viewTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }

    private static void showSummary() {
        double totalIncome = 0;
        double totalExpense = 0;

        for (Transaction t : transactions) {
            if (t instanceof Income) {
                totalIncome += t.getAmount();
            } else if (t instanceof Expense) {
                totalExpense += t.getAmount();
            }
        }

        System.out.printf("Total Income: %.2f\n", totalIncome);
        System.out.printf("Total Expenses: %.2f\n", totalExpense);
        System.out.printf("Balance: %.2f\n", totalIncome - totalExpense);
    }
}
