package com.example.demo.repository;

import com.example.demo.dto.SearchBlogResponse;
import com.example.demo.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomBlogRepository {
    List<SearchBlogResponse> findByTopicAndTitleCB(List<Integer> topicIds, String title, int page, int size);
}
