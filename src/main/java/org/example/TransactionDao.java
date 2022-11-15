package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDao {

    private Connection connection;

    void save(Transaction transaction) {
        connection = connect();
        PreparedStatement preparedStatement = null;

        try {
            String sql = "INSERT INTO transactions(type, description, amount, date) VALUES(?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, transaction.getType());
            preparedStatement.setString(2, transaction.getDescription());
            preparedStatement.setDouble(3, transaction.getAmount());
            preparedStatement.setString(4, transaction.getDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Niepowodzenie podczas zapisu rekordu " + e.getMessage());;
        }
        close();
    }

    void update(Transaction transaction) {
        connection = connect();
        PreparedStatement preparedStatement = null;

        try {
            String sql = "UPDATE transactions SET type = ?, description = ?, amount = ?, date = ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, transaction.getType());
            preparedStatement.setString(2, transaction.getDescription());
            preparedStatement.setDouble(3, transaction.getAmount());
            preparedStatement.setString(4, transaction.getDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Niepowodzenie podczas uaktualniania rekordu " + e.getMessage());;
        }
        close();
    }

    public void deleteById(int id) {
        connection = connect();
        PreparedStatement preparedStatement = null;

        try {
            String sql = "DELETE FROM transactions WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Niepowodzenie podczas usuwania rekordu: " + e.getMessage());
        }
        close();
    }

    public List<Transaction> findByType(String type) {
        connection = connect();
        PreparedStatement preparedStatement = null;
        try {
            String sql = "SELECT * FROM transactions WHERE type = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, type);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Transaction> transactions = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String typeDb = resultSet.getString("type");
                String description = resultSet.getString("description");
                double amount = resultSet.getDouble("amount");
                String date = resultSet.getString("date");
                Transaction transaction = new Transaction(id, typeDb, description, amount, date);
                transactions.add(transaction);
            }
            return transactions;
        } catch (SQLException e) {
            System.out.println("Niepowodzenie podczas szukania rekordu: " + e.getMessage());
        }
        close();

        return null;
    }

    private Connection connect() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Problem ze strownikami: " + e.getMessage());
        }

        String url = "jdbc:mysql://localhost:3306/budget?serverTimezone=UTC";
        try {
            return DriverManager.getConnection(url, "root", "");
        } catch (SQLException e) {
            System.out.println("Problem z połączeniem: " + e.getMessage());
        }
        return null;
    }

    void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
