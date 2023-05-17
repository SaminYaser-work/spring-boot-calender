package com.example.demo.service;
import java.util.List;
import java.util.Optional;

import com.example.demo.controller.BlogDto;
import com.example.demo.model.Blog;
import com.example.demo.model.User;
import com.example.demo.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    public List<Blog> getAllBlogs() {
        return (List<Blog>) blogRepository.findAll();
    }

    public Page<Blog> getAllBlogs(PageRequest pr) {
        return blogRepository.findAll(pr);
    }

    public void createBlog(Blog blog) {
        blogRepository.save(blog);
    }

    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

    public ResponseEntity<Object> updateBlog(Long id, BlogDto blog) {

        Optional<Blog> blogRes = blogRepository.findById(id);

        if (blogRes.isPresent()) {
            Blog existingBlog = blogRes.get();
//            existingBlog.setUser(blog.getUser());
            existingBlog.setTitle(blog.getTitle());
            existingBlog.setContent(blog.getContent());
            blogRepository.save(existingBlog);
            return ResponseEntity.ok(existingBlog);
        }
        return ResponseEntity.notFound().build();
    }

    public Blog getBlogById(Long id) {
        return blogRepository.findById(id).orElse(null);
    }

    public User getUserById(Long id) {
        Blog blog = getBlogById(id);
        return blog.getUser();
    }

//    public Blog getBlogWithUser(Long id) {
//        return blogRepository.findByIdWithAuthor(id);
//    }
}
