import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String url = "jdbc:mysql://localhost:3306/lenden";
    private static final String username = "root";
    private static final String password = "M1n8shkum1r@#";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            connection.setAutoCommit(false);  // Turn off auto-commit

            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Account no to debit from: ");
            int account_number_debit = sc.nextInt();
            System.out.println("Enter Amount: ");
            double amount = sc.nextDouble();

            // Check if sufficient balance is available
            if (isSufficient(connection, account_number_debit, amount)) {
                // If sufficient, perform the transaction
                String debit_query = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
                String credit_query = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";

                try (PreparedStatement debitPreparedStatement = connection.prepareStatement(debit_query);
                     PreparedStatement creditPreparedStatement = connection.prepareStatement(credit_query)) {

                    // Debit the account
                    debitPreparedStatement.setDouble(1, amount);
                    debitPreparedStatement.setInt(2, account_number_debit);
                    debitPreparedStatement.executeUpdate();

                    // Credit the account (e.g., to account number 102)
                    creditPreparedStatement.setDouble(1, amount);
                    creditPreparedStatement.setInt(2, 102);
                    creditPreparedStatement.executeUpdate();

                    // Commit the transaction if both updates succeed
                    connection.commit();
                    System.out.println("Transaction successfully completed.");
                } catch (SQLException e) {
                    // If any update fails, rollback the transaction
                    connection.rollback();
                    System.out.println("Transaction failed: " + e.getMessage());
                }
            } else {
                System.out.println("Insufficient balance, transaction cannot be completed.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    static boolean isSufficient(Connection connection, int account_number, double amount) {
        try {
            String query = "SELECT balance FROM accounts WHERE account_number = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, account_number);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                double current_balance = resultSet.getDouble("balance");
                return amount <= current_balance;  // Return true if sufficient balance
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
