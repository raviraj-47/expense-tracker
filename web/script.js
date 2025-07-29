// ---- Handling Form Submission ---- //
document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("expenseForm");
    const message = document.getElementById("formMessage");

    if (form) {
        form.addEventListener("submit", function (e) {
            e.preventDefault();

            // Input values
            const date = document.getElementById("date").value;
            const amount = parseFloat(document.getElementById("amount").value);
            const category = document.getElementById("category").value;
            const description = document.getElementById("description").value.trim();

            // Validating inputs
            if (!date || isNaN(amount) || amount <= 0 || category === "") {
                message.textContent = "Please fill all required fields with valid values.";
                return;
            }

            // Creating expense object
            const expense = {
                date,
                amount: amount.toFixed(2),
                category,
                description: description || "-"
            };

            // Save to localStorage
            let expenses = JSON.parse(localStorage.getItem("expenses")) || [];
            expenses.push(expense);
            localStorage.setItem("expenses", JSON.stringify(expenses));

            message.style.color = "green";
            message.textContent = "Expense added successfully!";

            // Clear form fields
            form.reset();
        });
    }

    // ---- Display Expenses in view.html ---- //
    const expensesTable = document.getElementById("expensesTable");
    const summaryBox = document.getElementById("summary");

    if (expensesTable && summaryBox) {
        const expenses = JSON.parse(localStorage.getItem("expenses")) || [];

        if (expenses.length === 0) {
            summaryBox.innerHTML = `<p>No expenses added yet.</p>`;
        } else {
            let total = 0;
            const categoryTotals = {};

            expenses.forEach((exp) => {
                total += parseFloat(exp.amount);

                // Category-wise totals
                if (!categoryTotals[exp.category]) {
                    categoryTotals[exp.category] = 0;
                }
                categoryTotals[exp.category] += parseFloat(exp.amount);

                // Add row to table
                const row = expensesTable.insertRow();
                row.insertCell(0).textContent = exp.date;
                row.insertCell(1).textContent = `₹${exp.amount}`;
                row.insertCell(2).textContent = exp.category;
                row.insertCell(3).textContent = exp.description;
            });

            // Build category summary
            let categoryHTML = "";
            for (const cat in categoryTotals) {
                categoryHTML += `<li>${cat}: ₹${categoryTotals[cat].toFixed(2)}</li>`;
            }

            // Add summary info
            summaryBox.innerHTML = `
                <p><strong>Total Spent:</strong> ₹${total.toFixed(2)}</p>
                <p><strong>By Category:</strong></p>
                <ul>${categoryHTML}</ul>
            `;
        }
    }
});
