package dao;

import java.io.*;
import java.util.ArrayList;  // import the FileUtil class
import java.util.List;
import model.Expense;
import util.FileUtil;

public class ExpenseDAO {
    private static final String FILE_PATH = "logs/expenses-log.txt";

    /**
     * Adds an expense by appending it to the log file.
     * Ensures the log file exists before writing.
     */
    public void addExpense(Expense expense) throws IOException {
        // Ensure the file and directories exist before writing
        FileUtil.ensureLogFileExists(FILE_PATH);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            String line = String.format("%s,%.2f,%s,%s",
                    expense.getDate(),
                    expense.getAmount(),
                    expense.getCategory().replace(",", " "),  // avoid CSV issues
                    expense.getDescription() != null ? expense.getDescription().replace(",", " ") : "");
            writer.write(line);
            writer.newLine();
        }
    }

    /**
     * Reads all expenses from the log file.
     * Ensures the log file exists before reading.
     */
    public List<Expense> getAllExpenses() throws IOException {
        // Ensure the file and directories exist before reading
        FileUtil.ensureLogFileExists(FILE_PATH);

        List<Expense> expenses = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",", 4);
                if (parts.length < 3) {
                    System.err.println("Skipping malformed line: " + line);
                    continue;
                }
                try {
                    String date = parts[0].trim();
                    double amount = Double.parseDouble(parts[1].trim());
                    String category = parts[2].trim();
                    String description = parts.length == 4 ? parts[3].trim() : "";

                    expenses.add(new Expense(date, amount, category, description));
                } catch (NumberFormatException e) {
                    System.err.println("Skipping line with invalid amount: " + line);
                }
            }
        }
        return expenses;
    }
}
