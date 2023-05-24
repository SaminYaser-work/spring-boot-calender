package com.example.demo.repository;

import com.example.demo.dto.SearchBlogResponse;
import com.example.demo.model.*;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomBlogRepositoryImpl implements CustomBlogRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public CustomBlogRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<SearchBlogResponse> findByTopicAndTitleCB(List<Integer> topicIds, String title, int start, int size) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Blog> cq = cb.createQuery(Blog.class);

        Root<Blog> root = cq.from(Blog.class);

        Join<Blog, Topic> topicJoin = root.join(Blog_.TOPIC, JoinType.INNER);

        Join<Blog, User> userJoin = root.join(Blog_.USER, JoinType.INNER);

        cq.select(root);

        cq.where(
                cb.and(
                        cb.equal(root.get("topic").get("id"), topicJoin.get("id")),
                        cb.equal(root.get("user").get("id"), userJoin.get("id"))
                )
        );

        cq.where(
                cb.and(
                        root.get("topic").in(topicIds),
                        cb.like(root.get("title"), "%" + title + "%")
                )
        );

        List<Blog> resultList = entityManager
                .createQuery(cq)
                .setFirstResult(start * size)
                .setMaxResults(size)
                .getResultList();

        return resultList.stream().map(blog -> {
            return SearchBlogResponse.builder()
                    .blogId(blog.getId())
                    .title(blog.getTitle())
                    .content(blog.getContent())
                    .date(blog.getDate())
                    .topicId(blog.getTopic().getId())
                    .topicName(blog.getTopic().getTopicName())
                    .userId(blog.getUser().getId())
                    .username(blog.getUser().getUsername())
                    .isActive(blog.getUser().isActive())
                    .build();
        }).collect(Collectors.toList());
    }
}
