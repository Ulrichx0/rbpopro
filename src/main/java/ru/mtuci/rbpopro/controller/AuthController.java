package ru.mtuci.rbpopro.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.mtuci.rbpopro.model.User;
import ru.mtuci.rbpopro.repository.UserRepository;
import ru.mtuci.rbpopro.security.JwtTokenProvider;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtTokenProvider jwtTokenProvider,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public Map<String, String> authenticateUser(@RequestBody Map<String, String> credentials) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.get("username"),
                        credentials.get("password")
                )
        );

        String token = jwtTokenProvider.generateToken(authentication);
        return Map.of("token", token);
    }

    @PostMapping("/register")
    public Map<String, String> registerUser(@RequestBody User user) {

        // Check if the login already exists
        if (userRepository.existsByLogin(user.getLogin())) {
            return Map.of("error", "Login already exists");
        }

        // Check if the email already exists
        if (userRepository.existsByEmail(user.getEmail())) {
            return Map.of("error", "Email already exists");
        }

        // Set the default role to "USER" if no role is provided
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");
        } else {
            // Allow only "USER" or "ADMIN" roles
            if (!user.getRole().equalsIgnoreCase("USER") && !user.getRole().equalsIgnoreCase("ADMIN")) {
                return Map.of("error", "Invalid role. Allowed roles are 'USER' or 'ADMIN'.");
            }
            user.setRole(user.getRole().toUpperCase()); // Normalize role to uppercase
        }

        // Hash the password
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));

        // Save the user in the repository
        userRepository.save(user);

        return Map.of("message", "User registered successfully", "role", user.getRole());
    }

}
