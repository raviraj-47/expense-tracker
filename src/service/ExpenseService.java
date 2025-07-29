package service;

import dao.ExpenseDAO;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import model.Expense;

public class ExpenseService {
    private ExpenseDAO dao;

    public ExpenseService() {
        this.dao = new ExpenseDAO();
    }

    /**
     * Adds a new expense after validating the inputs.
     *
     * @param date        Date in "yyyy-MM-dd" format
     * @param amount      Expense amount (must be > 0)
     * @param category    Non-empty category string
     * @param description Optional description (max 100 chars)
     * @throws IllegalArgumentException on validation errors
     * @throws IOException on file I/O errors
     */
    public void addExpense(String date, double amount, String category, String description) throws Exception {
        // Validate date
        if (date == null || date.trim().isEmpty()) {
            throw new IllegalArgumentException("❌ Error: Date is required.");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException("❌ Error: Date must be in YYYY-MM-DD format.");
        }

        // Validate amount
        if (amount <= 0) {
            throw new IllegalArgumentException("❌ Error: Amount must be greater than 0.");
        }

        // Validate category
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("❌ Error: Category cannot be empty.");
        }

        // Validate description length (optional)
        if (description != null && description.length() > 100) {
            throw new IllegalArgumentException("❌ Error: Description must be less than 100 characters.");
        }

        // Normalize category casing (optional)
        category = category.trim().toLowerCase();

        // Create Expense object
        Expense expense = new Expense(date, amount, category, description);

        // Save expense using DAO
        dao.addExpense(expense);
    }

    /**
     * Retrieves all expenses from storage.
     *
     * @return List of Expense objects
     * @throws IOException if reading from file fails
     */
    public List<Expense> getExpenses() throws IOException {
        return dao.getAllExpenses();
    }
}
