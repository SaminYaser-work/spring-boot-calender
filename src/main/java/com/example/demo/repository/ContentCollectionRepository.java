package com.example.demo.repository;

import com.example.demo.model.Content;
import com.example.demo.model.Status.Status;
import com.example.demo.model.Type.Type;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentCollectionRepository {
    private final List<Content> contentList = new ArrayList<>();

    public ContentCollectionRepository() {}

    public List<Content> findAll() {
       return contentList;
    }

    public Optional<Content> findById(Integer id) {
        return contentList.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    @PostConstruct
    private void init() {
        Content content1 = new Content(
                1,
                "First post title",
                "First post",
                Status.IDEA,
                Type.ARTICLE,
                LocalDateTime.now(),
                null,
                ""
        );
        contentList.add(content1);

        Content content2 = new Content(
                2,
                "Second post title",
                "Second post",
                Status.COMPLETED,
                Type.VIDEO,
                LocalDateTime.now(),
                null,
                ""
        );
        contentList.add(content2);
    }
}
