// UserController.java
package com.iffactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = userService.getUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userService.addUser(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAge());
        return ResponseEntity.ok("User added successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeUser(@PathVariable int id) {
        userService.removeUser(id);
        return ResponseEntity.ok("User removed successfully");
    }

    @PutMapping("/{id}/firstName")
    public ResponseEntity<String> updateFirstName(@PathVariable int id, @RequestBody String firstName) {
        userService.updateFirstName(id, firstName);
        return ResponseEntity.ok("First name updated successfully");
    }

    @PutMapping("/{id}/lastName")
    public ResponseEntity<String> updateLastName(@PathVariable int id, @RequestBody String lastName) {
        userService.updateLastName(id, lastName);
        return ResponseEntity.ok("Last name updated successfully");
}


    @PutMapping("/{id}/email")
    public ResponseEntity<String> updateEmail(@PathVariable int id, @RequestBody String email) {
        userService.updateEmail(id, email);
        return ResponseEntity.ok("Email updated successfully");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllUsers() {
        userService.deleteAllUsers();
        return ResponseEntity.ok("All users deleted successfully");
    }

    @PostMapping("/test")
    public ResponseEntity<String> runTests() {
        // Implementation for running tests
        return ResponseEntity.ok("Tests completed successfully");
    }
}