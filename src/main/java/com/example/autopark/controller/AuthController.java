package com.example.autopark.controller;

import com.example.autopark.dto.authentication.LoginDto;
import com.example.autopark.dto.authentication.RegisterDto;
import com.example.autopark.exception.EmailAlreadyExistsException;
import com.example.autopark.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/create-admin")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        try {
            var response = authenticationService.register(registerDto);
            return ResponseEntity.ok().body(response);
        } catch (EmailAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        try {
            var response = authenticationService.login(loginDto);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/greeting")
    public ResponseEntity<?> greeting() {
        return ResponseEntity.ok("greeting");
    }
}
