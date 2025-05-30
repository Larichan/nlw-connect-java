package net.larichan.nlw_connect.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import net.larichan.nlw_connect.model.User;
import net.larichan.nlw_connect.service.UserService;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<User> getUserById(@PathVariable Long id) {
    // Optional<User> user = userService.getUserById(id);
    // return user.map(ResponseEntity::ok).orElseGet(() ->
    // ResponseEntity.notFound().build());
    // }

    // @GetMapping
    // public ResponseEntity<Iterable<User>> getAllUsers() {
    // Iterable<User> users = userService.getAllUsers();
    // return ResponseEntity.ok(users);
    // }

    // @GetMapping("/email/{userEmail}")
    // public ResponseEntity<User> getUserByUserEmail(@PathVariable String
    // userEmail) {
    // return userService.getUserByUserEmail(userEmail).map(ResponseEntity::ok)
    // .orElse(ResponseEntity.notFound().build());
    // }

    // @PutMapping("/{id}")
    // public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody
    // User userDetails) {
    // User updatedUser = userService.updateUser(id, userDetails);
    // return updatedUser != null ? ResponseEntity.ok(updatedUser) :
    // ResponseEntity.notFound().build();
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    // userService.deleteUser(id);
    // return ResponseEntity.noContent().build();
    // }
}
