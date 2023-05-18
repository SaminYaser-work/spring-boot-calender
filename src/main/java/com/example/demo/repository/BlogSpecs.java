package com.example.demo.repository;

import com.example.demo.model.Blog;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class BlogSpecs {

    public static Specification<Blog> hasTitle(String title) {
        return (blog, cq, cb) -> cb.like(blog.get("title"), "%" + title + "%");
    }

    public static Specification<Blog> hasTopic(Integer topicId) {
        return (blog, cq, cb) -> cb.equal(blog.get("topic.id"), topicId);
    }

    public static Specification<Blog> hasContent(String content) {
        return (blog, cq, cb) -> cb.like(blog.get("content"), "%" + content + "%");
    }
}
