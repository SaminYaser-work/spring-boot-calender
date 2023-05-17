package com.example.demo.controller;

import java.util.List;

import com.example.demo.model.Blog;
import com.example.demo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

    @PersistenceContext
    private EntityManager entityManager;

//    @GetMapping("/with-users")
//    public List<Object[]> getAllBlogsWithUsername() {
//
//        String queryString = "SELECT b.content, b.title, b.date, u.username " +
//                "FROM com.example.demo.model.Blog b LEFT JOIN com.example.demo.model.User u " +
//                "b u";
//
////        String queryString = "SELECT b.content, b.title, b.date From com.example.demo.model.Blog b";
//
//        List<Object[]> res = entityManager.createQuery(queryString, Object[].class).getResultList();
//
//        return res;
//    }

    @GetMapping("/with-users")
    public List<Object[]> getAllBlogsWithUsername() {
        return blogService.getAllBlogsWithUsername();
    }

    @GetMapping("/page/{start}/{size}")
    public Page<Blog> findAll(@PathVariable int start, @PathVariable int size) {
        PageRequest pr = PageRequest.of(start, size);

        return blogService.getAllBlogs(pr);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable int id) {
        Blog blog = blogService.getBlogById(id);

        if (blog == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(blog);
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


