package com.example.demo.model;

import com.example.demo.repository.UserRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserDataLoader implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();

        User user = new User();
        user.setUsername(faker.name().fullName());
        user.setPassword(faker.internet().password());
        user.setActive(true);
        user.setRoles("USER_ROLE");
    }
}

