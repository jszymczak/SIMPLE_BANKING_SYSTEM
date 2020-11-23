package banking;

import org.sqlite.SQLiteDataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseProcessing {

    public static void connect(String url) {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        try (Connection con = dataSource.getConnection()) {
            if (con.isValid(5)) {
                System.out.println("Connected to database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void createCardTable(String url) {
        String sql = "CREATE TABLE IF NOT EXISTS card (\n"
                    + "id INTEGER PRIMARY KEY,\n"
                    + "number TEXT, "
                    + "pin TEXT, "
                    + "balance INTEGER DEFAULT 0); ";

        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertNewCardValues(String url, String cardNumber,
                                           String pinNumber) {
        String sql = "INSERT INTO card (number, pin) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(url);
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, cardNumber);
            preparedStatement.setString(2, pinNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void addIncome(String url, String cardNumber,
                                           String pinNumber, Integer income) {
        String sql = "UPDATE card SET balance = balance + ? WHERE number = ? AND pin = ?)";
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, income);
            preparedStatement.setString(2, cardNumber);
            preparedStatement.setString(3, pinNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
