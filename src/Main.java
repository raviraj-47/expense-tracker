import controller.ExpenseController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import model.Expense;

public class Main {
    public static void main(String[] args) {
        ExpenseController controller = new ExpenseController();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Expense Tracker (Console Demo) ===");

        while (running) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. View Total Spent");
            System.out.println("4. View Spent by Category");
            System.out.println("5. Exit");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    // Gather inputs for adding expense
                    System.out.print("Date (YYYY-MM-DD): ");
                    String date = scanner.nextLine().trim();
                    if (date.isEmpty()) {
                        System.out.println("❌ Error: Date is required.");
                        break;
                    }

                    // Validate date format early for user-friendly error
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    sdf.setLenient(false);
                    try {
                        sdf.parse(date);
                    } catch (ParseException e) {
                        System.out.println("❌ Error: Date must be in YYYY-MM-DD format.");
                        break;
                    }

                    System.out.print("Amount: ");
                    String amountStr = scanner.nextLine().trim();
                    double amount;
                    try {
                        amount = Double.parseDouble(amountStr);
                    } catch (NumberFormatException ex) {
                        System.out.println("❌ Error: Amount must be a valid number.");
                        break;
                    }
                    if (amount <= 0) {
                        System.out.println("❌ Error: Amount must be greater than 0.");
                        break;
                    }

                    System.out.print("Category: ");
                    String category = scanner.nextLine().trim();
                    if (category.isEmpty()) {
                        System.out.println("❌ Error: Category is required.");
                        break;
                    }
                    category = category.toLowerCase();

                    System.out.print("Description: ");
                    String description = scanner.nextLine().trim();

                    try {
                        boolean added = controller.handleAddExpense(date, amount, category, description);
                        if (added) {
                            System.out.println("✔ Expense added successfully!");
                        } else {
                            System.out.println("❌ Failed to add expense.");
                        }
                    } catch (Exception e) {
                        System.out.println("❌ Failed to add expense. Please try again.");
                    }
                    break;

                case "2":
                    List<Expense> expenses = controller.handleGetExpenses();
                    if (expenses.isEmpty()) {
                        System.out.println("⚠️ No expenses recorded.");
                    } else {
                        System.out.println("\n--- All Expenses ---");
                        for (Expense exp : expenses) {
                            System.out.printf("%s | Rs.%.2f | %s | %s\n",
                                    exp.getDate(),
                                    exp.getAmount(),
                                    exp.getCategory(),
                                    exp.getDescription());
                        }
                    }
                    break;

                case "3":
                    try {
                        double total = controller.handleGetExpenses()
                                .stream()
                                .mapToDouble(Expense::getAmount)
                                .sum();
                        System.out.printf("Total Spent: Rs.%.2f\n", total);
                    } catch (Exception e) {
                        System.out.println("Error calculating total.");
                    }
                    break;

                case "4":
                    try {
                        List<Expense> categoryMap = controller.handleGetExpenses();
                        java.util.Map<String, Double> summary = new java.util.HashMap<>();

                        for (Expense exp : categoryMap) {
                            summary.put(exp.getCategory(),
                                    summary.getOrDefault(exp.getCategory(), 0.0) + exp.getAmount());
                        }

                        System.out.println("\n--- Spent by Category ---");
                        for (var entry : summary.entrySet()) {
                            System.out.printf("%s: Rs.%.2f\n", entry.getKey(), entry.getValue());
                        }

                    } catch (Exception e) {
                        System.out.println("Error fetching summary.");
                    }
                    break;

                case "5":
                    running = false;
                    System.out.println("Exiting. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please enter a number from 1 to 5.");
                    break;
            }
        }
        scanner.close();
    }
}
