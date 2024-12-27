package com.nirz.assessment.controller;


import com.nirz.assessment.model.User;
import com.nirz.assessment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // API to add a user
    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) throws Exception {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

 // API to get a user by email
    @GetMapping("/get/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userService.getUserByEmail(email);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get()); // Return 200 OK with the user details
        } else {
            return ResponseEntity.notFound().build(); // Return 404 Not Found if user is not present
        }
    }
}
