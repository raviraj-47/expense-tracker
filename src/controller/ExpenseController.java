package controller;

import service.ExpenseService;
import model.Expense;

import java.util.List;

public class ExpenseController {
    private ExpenseService service;

    // Constructor - initializes the service
    public ExpenseController() {
        this.service = new ExpenseService();
    }

    /**
     * Handles adding a new expense.
     *
     * @param date        Date string in YYYY-MM-DD format
     * @param amount      Amount spent
     * @param category    Expense category
     * @param description Optional description
     * @return true if expense was added successfully, false otherwise
     */
    public boolean handleAddExpense(String date, double amount, String category, String description) {
        try {
            service.addExpense(date, amount, category, description);
            return true;
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error adding expense: " + e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves all expenses.
     *
     * @return List of Expense objects, empty list if none or on errors
     */
    public List<Expense> handleGetExpenses() {
        try {
            return service.getExpenses();
        } catch (Exception e) {
            System.out.println("Error retrieving expenses: " + e.getMessage());
            return List.of();
        }
    }

    // Additional controller methods for other features can be added here,
    // e.g., handleTotalSpent(), handleSpentByCategory(), etc.
}
