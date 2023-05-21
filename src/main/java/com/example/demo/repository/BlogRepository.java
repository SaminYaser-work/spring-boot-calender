package com.example.demo.repository;
import com.example.demo.model.Blog;
import com.example.demo.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer>, JpaSpecificationExecutor<Blog>, CustomBlogRepository {
    String q = "SELECT b.title, u.username, b.content, b.date FROM blog b LEFT JOIN users u ON b.fk_user_id = u.id";
    @Query(value = q, nativeQuery = true)
    List<Object[]> findAllBlogsWithUsernames();

//    @Query("SELECT b FROM com.example.demo.model.Blog b WHERE b.topic.id = :topicId")
//    Page<Blog> findBlogWithTopic(@Param("topicId") Integer topicId, Pageable page);
//
//    @Query("SELECT b FROM com.example.demo.model.Blog b WHERE b.title LIKE %:title%")
//    Page<Blog> findByTitle(@Param("title") String title, Pageable page);
//
//    @Query("SELECT b FROM com.example.demo.model.Blog b WHERE b.topic.id = :topicId AND b.title LIKE %:title%")
//    Page<Blog> findBlogWithTopicAndTitle(
//            @Param("topicId") Integer topicId,
//            @Param("title") String title,
//            Pageable page
//    );

    Page<Blog> findByTopicInOrTitleLikeIgnoreCaseAndContentLikeIgnoreCase(
            List<Topic> topic,
            String title,
            String content,
            Pageable page);

}
