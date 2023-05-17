package com.example.demo.model;

import com.example.demo.repository.BlogRepository;
import com.example.demo.repository.UserRepository;
import com.github.javafaker.Faker;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
public class BlogDataLoader implements CommandLineRunner {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional
    public void run(String... args) throws Exception {


        Faker faker = new Faker();

        List<Blog> blogPosts = new ArrayList<>();

         for (int i = 0; i < 30; i++) {

             String fakeName = faker.name().fullName();

             Blog blog = new Blog();
                blog.setTitle(faker.lorem().sentence());
                blog.setContent(faker.lorem().paragraph());
                blog.setDate(faker.date().between(new Date(System.currentTimeMillis() - 86400000L * 365), new Date()));
                blog.setAuthor(fakeName);


             User user = new User();
             user.setUsername(fakeName);
             user.setPassword(faker.internet().password());
             user.setActive(true);
             user.setRoles("USER_ROLE");


             blogRepository.save(blog);

             Blog savedBlog = blogRepository.findById(blog.getId()).get();

             userRepository.save(user);

             User savedUser = userRepository.findById(user.getId()).get();

             savedUser.addBlogPost(savedBlog);

//             savedBlog.setUser(savedUser);
//             blogRepository.save(savedBlog);

             userRepository.save(savedUser);

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

