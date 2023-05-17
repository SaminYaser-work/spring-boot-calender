package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Blog;
import com.example.demo.model.User;
import com.example.demo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public List<Blog> getAllBlogs() {
        return blogService.getAllBlogs();
    }

//    @GetMapping
//    public List<Blog> getAllBlogs(@RequestParam(required = false) boolean getUser) {
//        return blogService.getBlogWithUser();
//    }


    @GetMapping("/page/{start}/{size}")
    public Page<Blog> findAll(@PathVariable int start, @PathVariable int size) {
        PageRequest pr = PageRequest.of(start, size);

        return blogService.getAllBlogs(pr);
    }

//    @GetMapping("/{id}/user")
//    public User getAuthorForBook(@PathVariable Long id) {
//        User user = blogService.getUserById(id);
//        return user;
//    }

//    @GetMapping("/user/{id}/blogs")
//    public List<Blog> getBlogsByUser(@PathVariable Long id) {
//        User user = blogService.getUserById(id);
//
//        return blogService.getAllBlogbyUser(user);
//    }



    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable int id) {
        Blog blog = blogService.getBlogById(id);

        if (blog == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(blog);
    }

    @PostMapping
    public ResponseEntity<Blog> saveBlog(@RequestBody BlogDto blogDto) {
        Blog blog = new Blog();

        blog.setTitle(blogDto.getTitle());
        blog.setContent(blogDto.getContent());
        blog.setDate(blogDto.getDate());

        blogService.createBlog(blog);
        return ResponseEntity.status(HttpStatus.CREATED).body(blog);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable int id, @RequestBody BlogDto blogDto) {
        blogService.updateBlog(id, blogDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable int id) {
        Blog existingBlog = blogService.getBlogById(id);

        if (existingBlog != null) {
            blogService.deleteBlog(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


