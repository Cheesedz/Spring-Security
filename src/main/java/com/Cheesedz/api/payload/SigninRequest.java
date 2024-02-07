package com.Cheesedz.api.payload;

import lombok.Data;

@Data
public class SigninRequest {
    private String email;
    private String password;
}
