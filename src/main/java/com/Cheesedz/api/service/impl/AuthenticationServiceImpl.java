package com.Cheesedz.api.service.impl;

import com.Cheesedz.api.model.Role;
import com.Cheesedz.api.model.User;
import com.Cheesedz.api.payload.AuthenticationResponse;
import com.Cheesedz.api.payload.ExceptionResponse;
import com.Cheesedz.api.payload.SignUpRequest;
import com.Cheesedz.api.payload.SigninRequest;
import com.Cheesedz.api.repository.UserRepository;
import com.Cheesedz.api.service.AuthenticationService;
import com.Cheesedz.api.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    public Object signup(SignUpRequest signUpRequest) {
        Optional<User> foundUser = userRepository.findByEmail(signUpRequest.getEmail());
        if (foundUser.isPresent()) {
            return new ExceptionResponse("Not accepted", "Email already taken");
        } else {
            User user = new User();

            user.setEmail(signUpRequest.getEmail());
            user.setFirstname(signUpRequest.getFirstName());
            user.setUsername(signUpRequest.getEmail());
            user.setLastname(signUpRequest.getLastName());
            if (signUpRequest.getRoleId() == 0) {
                user.setRole(Role.USER);
            } else {
                user.setRole(Role.ADMIN);
            }
            user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

            return userRepository.save(user);
        }
    }

    public Object signin(SigninRequest signInRequest) {
        var user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("Invalid email or password.")
        );

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),
                signInRequest.getPassword()));

        String jwt = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();

        authenticationResponse.setToken(jwt);
        authenticationResponse.setRefreshToken(refreshToken);
        return authenticationResponse;
    }
}