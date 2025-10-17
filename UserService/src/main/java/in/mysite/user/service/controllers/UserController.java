package in.mysite.user.service.controllers;

import in.mysite.user.service.entities.User;
import in.mysite.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //user creation
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    //access single user
    @GetMapping("/{id}")
    public ResponseEntity<User> getUsersById(@PathVariable String id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    //get all users
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User userDetails) {
        User updatedUser = userService.updateUserById(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
