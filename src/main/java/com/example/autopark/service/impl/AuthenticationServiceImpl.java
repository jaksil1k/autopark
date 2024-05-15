package com.example.autopark.service.impl;

import com.example.autopark.dto.authentication.AuthenticationDto;
import com.example.autopark.dto.authentication.LoginDto;
import com.example.autopark.dto.authentication.RegisterDto;
import com.example.autopark.exception.EmailAlreadyExistsException;
import com.example.autopark.model.Role;
import com.example.autopark.model.User;
import com.example.autopark.repository.UserRepository;
import com.example.autopark.service.AuthenticationService;
import com.example.autopark.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationDto register(RegisterDto registerDto) throws EmailAlreadyExistsException {
        var user = User.builder()
                .name(registerDto.getName())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .role(Role.ROLE_ADMIN)
                .build();

        var opt = userRepository.findByName(user.getName());
        if (opt.isPresent()) {
            throw new EmailAlreadyExistsException(
                    "User with this email already exists"
            );
        }
        userRepository.save(user);
        var jwtToken = jwtUtil.generateToken(user);
        return AuthenticationDto.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationDto login(LoginDto loginDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getName(),
                loginDto.getPassword()
        ));

        var user = userRepository.findByName(loginDto.getName())
                .orElseThrow();

        var token = jwtUtil.generateToken(user);

        return AuthenticationDto.builder()
                .token(token)
                .build();
    }
}
