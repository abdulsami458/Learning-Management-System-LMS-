package com.astra.learning.management.system.controller;

import com.astra.learning.management.system.Role.Role;
import com.astra.learning.management.system.dto.AuthRequest;
import com.astra.learning.management.system.dto.AuthResponse;
import com.astra.learning.management.system.dto.UserDto;
import com.astra.learning.management.system.model.User;
import com.astra.learning.management.system.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
        userService.register(userDto, Role.ROLE_STUDENT);
        return ResponseEntity.ok( "Registration Successful");
    }

    @PostMapping("/signUp")
    public ResponseEntity<String> registerInstructor(@RequestBody UserDto userDto) {
        userService.register(userDto, Role.ROLE_INSTRUCTOR);
        return ResponseEntity.ok("Registration Successful");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> getUsers(){
        List<User> user = userService.getUsers();
        return ResponseEntity.ok(user);
    }



}
