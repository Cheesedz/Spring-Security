package com.Cheesedz.api.service;

import com.Cheesedz.api.payload.SignUpRequest;
import com.Cheesedz.api.payload.SigninRequest;

public interface AuthenticationService {
    Object signup(SignUpRequest signUpRequest);
    Object signin(SigninRequest signInRequest);
}
