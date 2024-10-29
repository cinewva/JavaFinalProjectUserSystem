package com.iffactory;

// Class to represent a user
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;

    public User(int id, String firstName, String lastName, String email, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    // Getter for ID
    public int getId() {
        return id;
    }

    // Getter for first name
    public String getFirstName() {
        return firstName;
    }

    // Getter for last name
    public String getLastName() {
        return lastName;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Getter for age
    public int getAge() {
        return age;
    }

    // Setter for first name
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Setter for last name
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    // Setter for age
    public void setAge(int age) {
        this.age = age;
    }

    // toString method for printing user information
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
