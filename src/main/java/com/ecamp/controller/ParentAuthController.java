package com.ecamp.controller;

import com.ecamp.model.Parent;
import com.ecamp.model.Role;
import com.ecamp.repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/parents")
public class ParentAuthController {

    @Autowired
    private ParentRepository repo;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/register")
    public ResponseEntity<String> registerParent(@RequestBody Parent parent) {
        // Encrypt password
        parent.setPassword(passwordEncoder.encode(parent.getPassword()));
        // Explicitly set role
        parent.setRole(Role.PARENT);
        // Save to database
        repo.save(parent);

        return ResponseEntity.ok("Parent registered successfully.");
    }


    // Stateless login returns { id, name, email }
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestParam String email,
            @RequestParam String phone
    ) {
        return repo.findByEmail(email)
                .map(parent -> {
                    if (parent.getPhone().equals(phone)) {
                        return ResponseEntity.ok(Map.of(
                                "id",    parent.getId(),
                                "name",  parent.getName(),
                                "email", parent.getEmail()
                        ));
                    } else {
                        return ResponseEntity
                                .status(HttpStatus.UNAUTHORIZED)
                                .body("Invalid credentials");
                    }
                })
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("User not found"));
    }
}
