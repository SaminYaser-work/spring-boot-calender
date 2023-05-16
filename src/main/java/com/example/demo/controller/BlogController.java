package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Blog;
import com.example.demo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping
    public List<Blog> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    @GetMapping("/page/{start}")
    public List<Blog> getAllBlogsPaging(@PathVariable Integer start) {
        return blogService.getAllBlogsPaging(start);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable Long id) {
        Optional<Blog> blog = blogService.getBlogById(id);
        return blog.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Blog> saveBlog(@RequestBody BlogDto blogDto) {
        Blog blog = new Blog(blogDto.getTitle(), blogDto.getContent(),
                blogDto.getAuthor(), blogDto.getDate());
        blogService.saveBlog(blog);
        return ResponseEntity.status(HttpStatus.CREATED).body(blog);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable Long id, @RequestBody BlogDto blogDto) {
        Optional<Blog> existingBlog = blogService.getBlogById(id);
        if (existingBlog.isPresent()) {
            Blog blog = existingBlog.get();
            blog.setTitle(blogDto.getTitle());
            blog.setContent(blogDto.getContent());
            blog.setAuthor(blogDto.getAuthor());
            blog.setDate(blogDto.getDate());
            blogService.updateBlog(blog, id);
            return ResponseEntity.ok(blog);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long id) {
        Optional<Blog> existingBlog = blogService.getBlogById(id);
        if (existingBlog.isPresent()) {
            blogService.deleteBlog(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


