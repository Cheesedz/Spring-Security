package com.Cheesedz.api.service;

import com.Cheesedz.api.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;
import java.util.Map;

public interface JWTService {
    String extractUserName(String token);
    String generateToken(UserDetails userDetails);
    String generateRefreshToken(Map<String, Object> objectObjectHashMap, UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails);
}
