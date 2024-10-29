// UserService.java
package com.iffactory;

import org.springframework.stereotype.Service;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private Connection connection;

    public UserService() {
        connectToDatabase();
    }

    private void connectToDatabase() {
        try {
            String url = "jdbc:mysql://localhost:3306/UserManagementSystem";
            String username = "alinb";
            String password = "RootRoot1";
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to database: " + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                users.add(new User(
                    resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("email"),
                    resultSet.getInt("age")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving users: " + e.getMessage());
        }
        return users;
    }

    public User getUserById(int id) {
        try {
            String query = "SELECT * FROM users WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                return new User(
                    resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("email"),
                    resultSet.getInt("age")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving user: " + e.getMessage());
        }
    }

    public void addUser(int id, String firstName, String lastName, String email, int age) {
        try {
            String query = "INSERT INTO users (id, first_name, last_name, email, age) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setString(4, email);
            statement.setInt(5, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error adding user: " + e.getMessage());
        }
    }

    public void removeUser(int id) {
        try {
            String query = "DELETE FROM users WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error removing user: " + e.getMessage());
        }
    }

    public void updateFirstName(int id, String newFirstName) {
        try {
            String query = "UPDATE users SET first_name = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, newFirstName);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating first name: " + e.getMessage());
        }
    }

    public void updateEmail(int id, String newEmail) {
        try {
            String query = "UPDATE users SET email = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, newEmail);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating email: " + e.getMessage());
        }
    }

    public void deleteAllUsers() {
        try {
            String query = "DELETE FROM users";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting all users: " + e.getMessage());
        }
    }
}