package com.Cheesedz.api.config;

import com.Cheesedz.api.model.Role;
import com.Cheesedz.api.model.User;
import com.Cheesedz.api.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfig {
    private static final Logger logger = LoggerFactory.getLogger(DBConfig.class);
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            User A = new User("Alex", "Firguson", "alex@gmail.com", "alex@gmail.com", "abc123", Role.USER);
            logger.info("inserted record: " + userRepository.save(A));
        };
    }
}
