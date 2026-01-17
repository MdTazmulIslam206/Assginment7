import java.util.*;
import java.time.LocalDate;

public class FinanceTrackerApp {
    private static List<Transaction> transactions = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Personal Finance Tracker ---");
            System.out.println("1. Add Transaction");
            System.out.println("2. View All Transactions");
            System.out.println("3. Update Transaction");
            System.out.println("4. Delete Transaction");
            System.out.println("5. Calculate Balance");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            // Buffer clear
            sc.nextLine();

            switch (choice) {
                case 1 -> addTransaction();
                case 2 -> viewTransactions();
                case 3 -> updateTransaction();
                case 4 -> deleteTransaction();
                case 5 -> calculateBalance();
                case 6 -> {
                    System.out.println("Exiting... Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void addTransaction() {
        String id = String.valueOf(transactions.size() + 1);

        System.out.print("Enter Type (INCOME/EXPENSE): ");
        String type = sc.nextLine();

        double amount;
        while (true) {
            System.out.print("Enter Amount: ");
            amount = sc.nextDouble();
            if (amount > 0) break;
            System.out.println("Amount must be positive!");
        }
        sc.nextLine();

        System.out.print("Enter Description: ");
        String desc = sc.nextLine();

        String date = inputDate();

        transactions.add(new Transaction(id, type, amount, desc, date));
        System.out.println("Transaction added successfully!");
    }

    private static String inputDate() {
        System.out.println("Date Option: 1. Today's Date  2. Manual Entry (YYYY-MM-DD)");
        int choice = sc.nextInt();
        sc.nextLine();
        if (choice == 1) {
            return LocalDate.now().toString();
        } else {
            System.out.print("Enter Date (YYYY-MM-DD): ");
            return sc.nextLine();
        }
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

    private static void updateTransaction() {
        System.out.print("Enter ID to update: ");
        String id = sc.nextLine();
        for (Transaction t : transactions) {
            if (t.getId().equals(id)) {
                System.out.print("New Type (INCOME/EXPENSE): ");
                t.setType(sc.nextLine());
                System.out.print("New Amount: ");
                t.setAmount(sc.nextDouble());
                sc.nextLine();
                System.out.print("New Description: ");
                t.setDescription(sc.nextLine());
                t.setDate(inputDate());
                System.out.println("Updated successfully!");
                return;
            }
        }
        System.out.println("Transaction not found.");
    }

    private static void deleteTransaction() {
        System.out.print("Enter ID to delete: ");
        String id = sc.nextLine();
        boolean removed = transactions.removeIf(t -> t.getId().equals(id));
        if (removed) System.out.println("Deleted successfully!");
        else System.out.println("ID not found.");
    }

    private static void calculateBalance() {
        double income = 0, expense = 0;
        for (Transaction t : transactions) {
            if (t.getType().equals("INCOME")) income += t.getAmount();
            else if (t.getType().equals("EXPENSE")) expense += t.getAmount();
        }
        System.out.println("Total Income: " + income);
        System.out.println("Total Expense: " + expense);
        System.out.println("Current Balance: " + (income - expense));
    }
}