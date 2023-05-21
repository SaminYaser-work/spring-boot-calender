package com.example.demo.service;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.example.demo.dto.BlogDto;
import com.example.demo.dto.SearchBlogResponse;
import com.example.demo.model.Blog;
import com.example.demo.model.Blog_;
import com.example.demo.model.Topic;
import com.example.demo.repository.BlogRepository;
import com.example.demo.repository.specifications.BlogSpecification;
import com.example.demo.repository.TopicRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

    public Blog getBlogById(int id) {
        return blogRepository.findById(id).orElse(null);
    }

    public Page<SearchBlogResponse> searchByTopicTitleContent(
            List<Integer> topicId,
            String title,
            String content,
            Pageable pr
    ) {

        Page<Blog> res = blogRepository.findAll(
                where(BlogSpecification.hasTitle(title))
                        .and(where(BlogSpecification.hasTopic(topicId))),
                pr
        );

        return res.map(blog -> SearchBlogResponse.builder()
                .blogId(blog.getId())
                .title(blog.getTitle())
                .content(blog.getContent())
                .date(blog.getDate())
                .topicId(blog.getTopic().getId())
                .topicName(blog.getTopic().getTopicName())
//                .userId(blog.getUser().getId())
//                .username(blog.getUser().getUsername())
//                .isActive(blog.getUser().isActive())
                .build()
        );
    }

    public List<SearchBlogResponse> searchBlogCB(List<Integer> topicIds, String title, int start, int size) {
        return blogRepository.findByTopicAndTitleCB(topicIds, title, start, size);
    }

    public Page<SearchBlogResponse> searchBlogKeyword (
        List<Integer> topicIds,
        String title,
        String content,
        Pageable pr
    ) {
        List<Topic> topics = topicRepository.findAllById(topicIds);

        Page<Blog> res = blogRepository.findByTopicInOrTitleLikeIgnoreCaseAndContentLikeIgnoreCase(
                topics,
                title,
                content,
                pr
        );

        Page<SearchBlogResponse> searchBlogResponses = res.map(new Function<Blog, SearchBlogResponse>() {
            @Override
            public SearchBlogResponse apply(Blog blog) {
                return SearchBlogResponse.builder()
                        .blogId(blog.getId())
                        .title(blog.getTitle())
                        .content(blog.getContent())
                        .date(blog.getDate())
                        .topicId(blog.getTopic().getId())
                        .topicName(blog.getTopic().getTopicName())
//                        .userId(blog.getUser().getId())
//                        .username(blog.getUser().getUsername())
//                        .isActive(blog.getUser().isActive())
                        .build();
            }
        });

        return searchBlogResponses;
    }
}
