package com.example.demo.controller;

import com.example.demo.dto.AddNewBlogRequest;
import com.example.demo.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    @PostMapping("/{id}")
    public void createPost(@PathVariable int id, @RequestBody AddNewBlogRequest req) {
        blogPostService.postBlog(id, req);
    }

}
