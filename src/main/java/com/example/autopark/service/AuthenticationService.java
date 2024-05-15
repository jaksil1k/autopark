package com.example.autopark.service;

import com.example.autopark.dto.authentication.AuthenticationDto;
import com.example.autopark.dto.authentication.LoginDto;
import com.example.autopark.dto.authentication.RegisterDto;
import com.example.autopark.exception.EmailAlreadyExistsException;

public interface AuthenticationService {

    AuthenticationDto register(RegisterDto registerDto) throws EmailAlreadyExistsException;

    AuthenticationDto login(LoginDto loginDto);
}
