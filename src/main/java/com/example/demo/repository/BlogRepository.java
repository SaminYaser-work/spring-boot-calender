package com.example.demo.repository;
import com.example.demo.model.Blog;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

//public interface BlogRepository extends JpaRepository<Blog, Long> {
//}

import com.example.demo.model.Blog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
//    @Query("SELECT b.content, b.title, b.date, u.username FROM Blog b LEFT JOIN b.user u")
//    @Query(value = "select b.content, b.title, b.date, u.username  from blog b left join users u on b.fk_blog_id = u.id",
//    nativeQuery = true)
//    @Query("SELECT b.content, b.title, b.date, u.username FROM Blog b LEFT JOIN User u ON b.fk_user_id = u.id")
    String q = "SELECT b.content, b.title, b.date, u.username FROM blog b LEFT JOIN users u ON b.fk_user_id = u.id";
    @Query(value = q, nativeQuery = true)
    List<Object[]> findAllBlogsWithUsernames();

}
