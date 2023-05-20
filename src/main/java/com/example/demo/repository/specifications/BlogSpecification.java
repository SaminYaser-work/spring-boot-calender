package com.example.demo.repository.specifications;

import com.example.demo.model.Blog;
import com.example.demo.model.Blog_;
import com.example.demo.model.Topic;
import com.example.demo.model.Topic_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.List;

@Component
public class BlogSpecification {

    public static Specification<Blog> hasTitle(String title) {
        return (blog, cq, cb) -> cb.like(blog.get(Blog_.TITLE), "%" + title + "%");
    }

    public static Specification<Blog> hasTopic(List<Integer> topicIds) {
        return (blog, cq, cb) -> {
            Join<Blog, Topic> topicJoin = blog.join(Blog_.TOPIC);
            return topicJoin.get(Topic_.ID).in(topicIds);
        };
    }

    public static Specification<Blog> hasContent(String content) {
        return (blog, cq, cb) -> cb.like(blog.get(Blog_.CONTENT), "%" + content + "%");
    }
}
