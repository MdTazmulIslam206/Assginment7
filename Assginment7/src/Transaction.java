public class Transaction {
    private final String id; // Immutable ID
    private String type;     // INCOME or EXPENSE
    private double amount;
    private String description;
    private String date;

    // No Arguments Constructor
    public Transaction() {
        this.id = "";
    }

    // All Arguments Constructor
    public Transaction(String id, String type, double amount, String description, String date) {
        this.id = id;
        setType(type);
        setAmount(amount);
        this.description = description;
        this.date = date;
    }

    // Getters
    public String getId() {
        return id;
    }
    public String getType() {
        return type;
    }
    public double getAmount() {
        return amount;
    }
    public String getDescription() {
        return description;
    }
    public String getDate() {
        return date;
    }

    // Setters with Validation
    public void setType(String type) {
        if (type.equalsIgnoreCase("INCOME") || type.equalsIgnoreCase("EXPENSE")) {
            this.type = type.toUpperCase();
        } else {
            this.type = "UNKNOWN";
        }
    }

    public void setAmount(double amount) {
        if (amount > 0) {
            this.amount = amount;
        } else {
            this.amount = 0;
        }
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Type: %-7s | Amount: %-8.2f | Date: %s | Desc: %s",
                id, type, amount, date, description);
    }
}