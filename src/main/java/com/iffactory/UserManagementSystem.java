package com.iffactory;

import java.sql.*;
import java.util.Scanner;

// UserManagementSystem class to handle user management operations
class UserManagementSystem implements ForTesting {
    private Connection connection;
    private Scanner scanner;

    public Connection getConnection() {
        return this.connection;
    }

    public UserManagementSystem() {
        scanner = new Scanner(System.in);
        connectToDatabase();
    }

    // Method to connect to the database
    private void connectToDatabase() {
        try {
            // database connection credentials
            String url = "jdbc:mysql://localhost:3306/UserManagementSystem";
            String username = "alinb";
            String password = "RootRoot1";

            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database.");
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
    }

    // Method to close the database connection
    void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error closing the database connection: " + e.getMessage());
        }
    }

    // Method to display all users
    public void displayAllUsers() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

            if (!resultSet.isBeforeFirst()) {
                System.out.println("No users found.");
            } else {
                System.out.println("All users:");
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    String email = resultSet.getString("email");
                    int age = resultSet.getInt("age");

                    User user = new User(id, firstName, lastName, email, age);
                    System.out.println(user);
                }
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error executing the query: " + e.getMessage());
        }
    }

    // Method to add a user
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
            System.out.println("User added successfully.");
        } catch (SQLException e) {
            System.out.println("Error executing the query: " + e.getMessage());
        }
    }

    // Method to remove a user
    public void removeUser(int id) {
        try {
            String query = "DELETE FROM users WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("User removed successfully.");
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error executing the query: " + e.getMessage());
        }
    }

    // Method to update the first name of a user
    public void updateFirstName(int id, String newFirstName) {
        try {
            String query = "UPDATE users SET first_name = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, newFirstName);
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("First name updated successfully.");
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error executing the query: " + e.getMessage());
        }
    }

    // Method to update the email of a user
    public void updateEmail(int id, String newEmail) {
        try {
            String query = "UPDATE users SET email = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, newEmail);
            statement.setInt(2, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Email updated successfully.");
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error executing the query: " + e.getMessage());
        }
    }

    // Method to display user by ID
    public User displayUserById(int id) {
        try {
            String query = "SELECT * FROM users WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                int age = resultSet.getInt("age");
                System.out.println("ID: " + userId);
                System.out.println("First Name: " + firstName);
                System.out.println("Last Name: " + lastName);
                System.out.println("Email: " + email);
                System.out.println("Age: " + age);
                return new User(userId, firstName, lastName, email, age);
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error executing the query: " + e.getMessage());
        }
        return null;
    }

    // add many users for testing purposes
    @Override
    public void addUsers(int id, String firstName, String lastName, String email, int age) {
        try {
            String query = "INSERT INTO users (id, first_name, last_name, email, age) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setString(4, email);
            statement.setInt(5, age);
            statement.executeUpdate();
            System.out.println("User added successfully.");
        } catch (SQLException e) {
            System.out.println("Error executing the query: " + e.getMessage());
        }
    }

    // delete all users for testing purposes
    @Override
    public void deleteAllUsers() {
        String query = "DELETE FROM users";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
            System.out.println("All users deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting users: " + e.getMessage());
        }
    }
}
