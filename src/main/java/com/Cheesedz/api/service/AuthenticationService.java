package com.Cheesedz.api.service;

import com.Cheesedz.api.model.User;
import com.Cheesedz.api.payload.AuthenticationResponse;
import com.Cheesedz.api.payload.SignUpRequest;
import com.Cheesedz.api.payload.SigninRequest;

public interface AuthenticationService {
    User signup(SignUpRequest signUpRequest);
    AuthenticationResponse signin(SigninRequest signInRequest);
}
