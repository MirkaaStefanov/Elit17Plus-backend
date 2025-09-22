package com.example.Elit17Plus_backend.services;

import com.example.Elit17Plus_backend.models.dto.auth.AuthenticationRequest;
import com.example.Elit17Plus_backend.models.dto.auth.AuthenticationResponse;
import com.example.Elit17Plus_backend.models.dto.auth.PublicUserDTO;
import com.example.Elit17Plus_backend.models.dto.auth.RegisterRequest;
import com.example.Elit17Plus_backend.models.entity.User;

import java.io.IOException;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    AuthenticationResponse refreshToken(String refreshToken) throws IOException;

    PublicUserDTO me(
            String jwtToken
    );

    void resetPassword(String token, String newPassword);

    void confirmRegistration(String verificationToken);

    User forgotPassword(String email);
}
