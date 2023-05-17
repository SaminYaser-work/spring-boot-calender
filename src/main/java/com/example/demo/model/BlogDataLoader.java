package com.example.demo.model;

import com.example.demo.repository.BlogRepository;
import com.example.demo.repository.UserRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

@Component
public class BlogDataLoader implements CommandLineRunner {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();

        Set<Blog> blogPosts = new HashSet<>();

        // For loop to create 100 blogs
         for (int i = 0; i < 100; i++) {
             User user = new User(
                     faker.name().fullName(),
                     faker.internet().password(),
                     true,
                     "USER_ROLE",
                     blogPosts
             );

             Blog blog = new Blog(
                     faker.lorem().sentence(),
                     faker.lorem().paragraph(),
                     faker.date().between(new Date(System.currentTimeMillis() - 86400000L * 365), new Date()),
                     user
             );

             user.addBlogPost(blog);
             userRepository.save(user);
             blogRepository.save(blog);

         }

//        IntStream.rangeClosed(1, 100)
//                .mapToObj(i -> new Blog(
//                        faker.lorem().sentence(),
//                        faker.lorem().paragraph(),
//                        faker.name().fullName(),
//                        faker.date().between(new Date(System.currentTimeMillis() - 86400000L * 365), new Date())
//                ))
//                .forEach(blogRepository::save);
    }
}

