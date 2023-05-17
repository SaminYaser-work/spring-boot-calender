package com.example.demo.service;

import com.example.demo.dto.AddNewBlogRequest;
import com.example.demo.model.Blog;
import com.example.demo.model.User;
import com.example.demo.repository.BlogRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class BlogPostService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserService userService;

    public void postBlog(int userId, AddNewBlogRequest req) {
        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            Blog blog = new Blog();
            blog.setTitle(req.getBlogTitle());
            blog.setContent(req.getBlogContent());
            blog.setDate(new Date());
            blogRepository.save(blog);
            user.addBlogPost(blog);
            userRepository.save(user);
        }
    }
}
