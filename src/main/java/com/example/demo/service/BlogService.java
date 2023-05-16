package com.example.demo.service;
import java.util.List;
import java.util.Optional;

import com.example.demo.model.Blog;
import com.example.demo.model.User;
import com.example.demo.repository.BlogRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
//public class BlogService {
//
//    @Autowired
//    private BlogRepository blogRepository;
//
//    public List<Blog> getAllBlogs() {
//        return blogRepository.findAll();
//    }
//
//    public List<Blog> getAllBlogsPaging(Integer page) {
//        return blogRepository.getPage(page);
//    }
//
//    public Optional<Blog> getBlogById(Long id) {
//        return blogRepository.findById(id);
//    }
//
//    public void saveBlog(Blog blog) {
//        blogRepository.save(blog);
//    }
//
//    public void updateBlog(Blog blog, Long id) {
//        blogRepository.update(blog, id);
//    }
//
//    public void deleteBlog(Long id) {
//        blogRepository.deleteById(id);
//    }
//}

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    public List<User> getAllUsers() {
        return blogRepository.findAll();
    }

    public User createUser(User user) {
        return blogRepository.save(user);
    }

    public void deleteUser(Long id) {
        blogRepository.deleteById(id);
    }

    public Blog updateUser(Long id, Blog blog) {
        Blog existingBlog = blogRepository.findById(id).orElse(null);

        if (existingBlog != null) {
            existingBlog.setAuthor(blog.getAuthor());
            existingBlog.setTitle(blog.getTitle());
            existingBlog.setContent(blog.setContent());
            blogRepository.save(existingBlog);
        }

        return existingBlog;
    }

    public User getUserById(Long id) {
        return blogRepository.findById(id).orElse(null);
    }
}
