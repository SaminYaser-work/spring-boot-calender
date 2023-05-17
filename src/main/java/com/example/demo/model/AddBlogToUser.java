package com.example.demo.model;

import com.example.demo.repository.BlogRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
public class AddBlogToUser implements CommandLineRunner {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public void run(String... args) throws Exception {
        List<Blog> blogs = blogRepository.findAll();
        List<User> users = userRepository.findAll();
    }
}

