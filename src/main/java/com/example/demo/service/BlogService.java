package com.example.demo.service;
import java.util.List;
import java.util.Optional;

import com.example.demo.controller.BlogDto;
import com.example.demo.model.Blog;
import com.example.demo.repository.BlogRepository;
import com.example.demo.repository.BlogSpecs;
import com.example.demo.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private TopicRepository topicRepository;

    public List<Blog> getAllBlogs() {
        return (List<Blog>) blogRepository.findAll();
    }

    public Page<Blog> getAllBlogs(PageRequest pr) {
        return blogRepository.findAll(pr);
    }

//    public List<Blog> getAllBlogsWithUsername(int id) {
//        return blogRepository.findAllBlogWithUsername(id);
//    }

    public void createBlog(Blog blog) {
        blogRepository.save(blog);
    }

    public void deleteBlog(int id) {
        blogRepository.deleteById(id);
    }

    public ResponseEntity<Object> updateBlog(int id, BlogDto blog) {

        Optional<Blog> blogRes = blogRepository.findById(id);

        if (blogRes.isPresent()) {
            Blog existingBlog = blogRes.get();
            existingBlog.setTitle(blog.getTitle());
            existingBlog.setContent(blog.getContent());
            blogRepository.save(existingBlog);
            return ResponseEntity.ok(existingBlog);
        }
        return ResponseEntity.notFound().build();
    }

    public List<Object[]> getAllBlogsWithUsername() {
        return blogRepository.findAllBlogsWithUsernames();
    }

    public Blog getBlogById(int id) {
        return blogRepository.findById(id).orElse(null);
    }

    public Page<Blog> searchByTopic(Integer topicId, Pageable pr) {
        return blogRepository.findBlogWithTopic(topicId, pr);
    }


//    public Page<Blog> searchByTitle(String title, Pageable pr) {
//        return blogRepository.findAll(
//                where(hasTitle(title)).or(hasContent(title)),
//                pr
//        );
//    }

    public Page<Blog> searchByTopicTitleContent(
            Integer topicId,
            String title,
            String content,
            Pageable pr
    ) {
//        Integer searchedTopicId = topicRepository.findByTopicName(topicId);
//        return blogRepository.findBlogWithTopicAndTitle(topicId, title, pr);

        return blogRepository.findAll(
                where(BlogSpecs.hasTopic(topicId))
                        .or(BlogSpecs.hasContent(content))
                        .or(BlogSpecs.hasTitle(title)),
                pr
        );
    }
}
