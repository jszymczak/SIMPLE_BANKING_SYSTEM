package banking;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseProcessing {
    public static void connect() {
//        String url = "jdbc:sqlite:D:\\REPO\\SIMPLE_BANKING_SYSTEM\\db\\banking.db";
        String url = "jdbc:sqlite:db\\banking.db";

        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        try (Connection con = dataSource.getConnection()) {
            if (con.isValid(5)) {
                System.out.println("Connection is valid.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void createCardTable() {
        String url = "jdbc:sqlite:db\\banking.db";

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

    public static void insertNewCardValues(String cardNumber,
                                           String pinNumber) {
        String url = "jdbc:sqlite:db\\banking.db";
        String sql = "INSERT INTO card (id, number, pin, balance) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(url);
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, cardNumber);
            preparedStatement.setString(2, pinNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
