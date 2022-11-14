package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionDao {

    void save(Transaction transaction) {
        Connection connection = connect();
        PreparedStatement preparedStatement = null;

        try {
            String sql = "INSET INTO transactions(type, description, amount, date) VALUES(" +
                    transaction.getType() + ", " +
                    transaction.getDescription() + ", " +
                    transaction.getAmount() + ", " +
                    transaction.getDate() + ")";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Niepowodzenie podczas zapisu " + e.getMessage());;
        }
    }

    private Connection connect() {
        String url = "jdbc:mysql://localhost:3306/library?serverTimezone=UTC&characterEncoding=utf8";
        try {
            return DriverManager.getConnection(url, "root", " " );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
