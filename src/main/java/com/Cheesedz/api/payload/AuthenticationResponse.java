package com.Cheesedz.api.payload;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private String token;
    private String refreshToken;
}
