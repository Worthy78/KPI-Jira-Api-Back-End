package com.example.jira.auth.controller;

import com.example.jira.auth.exception.AppException;
import com.example.jira.auth.model.Role;
import com.example.jira.auth.model.RoleName;
import com.example.jira.auth.model.User;
import com.example.jira.auth.payload.ApiResponse;
import com.example.jira.auth.payload.JwtAuthenticationResponse;
import com.example.jira.auth.payload.LoginRequest;
import com.example.jira.auth.payload.SignUpRequest;
import com.example.jira.auth.repository.RoleRepository;
import com.example.jira.auth.repository.UserRepository;
import com.example.jira.auth.security.JwtTokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@Api( description="API pour la gestion des authentifications.")
@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @ApiOperation(value = "Operation de connexion")
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @ApiOperation(value = "Création Compte Admin")
    @PostMapping("/admin")
    public ResponseEntity<?> registerAdmin() {
        // Creating admin's account
        User admin = new User();
        admin.setName("administrator");
        admin.setUsername("admin");
        admin.setPassword("admin");
        // Verifying if the admin is already created
        if(userRepository.existsByUsername(admin.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Admin account already existed!"),
                    HttpStatus.BAD_REQUEST);
        }

        admin.setPassword(passwordEncoder.encode(admin.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                .orElseThrow(() -> new AppException("Admin Role not set."));

        admin.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(admin);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/admin/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "Admin registered successfully"));
    }

    @ApiOperation(value = "Operation de création de compte (inscription)")
    @PreAuthorize("hasRole('ROLE_ADMIN')") // Seul l'admin peut creer des comptes
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest /*, Authentication authentication*/) {
        /*
        log.info("CURRENT USER {}", authentication.getName());
        log.info("ROLES {}", authentication.getAuthorities());
        log.info("USERS {}", authentication.getDetails());

         */

        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
}