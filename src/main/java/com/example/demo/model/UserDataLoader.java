//package com.example.demo.model;
//
//import com.example.demo.repository.UserRepository;
//import com.github.javafaker.Faker;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.stream.IntStream;
//
//@Component
//public class UserDataLoader implements CommandLineRunner {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        Faker faker = new Faker();
//
//        IntStream.rangeClosed(1, 100)
//                .mapToObj(i -> new User(
//                        faker.name().fullName(),
//                        faker.pokemon().name(),
//                        true,
//                        "USER_ROLE"
//                ))
//                .forEach(userRepository::save);
//    }
//}
//
