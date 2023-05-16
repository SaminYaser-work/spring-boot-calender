package com.example.demo.repository;
import com.example.demo.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

//public interface BlogRepository extends JpaRepository<Blog, Long> {
//}

import com.example.demo.model.Blog;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class BlogRepository {

    private final JdbcTemplate jdbcTemplate;

    public BlogRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void save(Blog blog) {
        String sql = "INSERT INTO blog (title, content, author, date) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, blog.getTitle(), blog.getContent(), blog.getAuthor(), blog.getDate());
    }

    public List<Blog> findAll() {
        String sql = "SELECT * FROM blog";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Blog(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getString("author"),
                rs.getDate("date")
//                rs.getTimestamp("date").toLocalDateTime()
        ));
    }

    public List<Blog> findByAuthor(String author) {
        String sql = "SELECT * FROM blog WHERE author = ?";
        return jdbcTemplate.query(sql, new Object[]{author}, (rs, rowNum) -> new Blog(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getString("author"),
                rs.getDate("date")
//                rs.getTimestamp("date").toLocalDateTime()
        ));
    }

    public void update(Blog blog, Long id) {
        String sql = "UPDATE blog SET title = ?, content = ?, author = ?, date = ? WHERE id = ?";
        jdbcTemplate.update(sql, blog.getTitle(), blog.getContent(), blog.getAuthor(), blog.getDate(), id);
    }

    public void delete(Long id) {
        String sql = "DELETE FROM blog WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Optional<Blog> findById(Long id) {
        String sql = "SELECT * FROM blog WHERE id = ?";
        List<Blog> blogs = jdbcTemplate.query(sql, new Object[]{id}, (rs, rowNum) -> new Blog(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getString("author"),
                rs.getDate("date")
//                rs.getTimestamp("date").toLocalDateTime()
        ));
        return blogs.isEmpty() ? Optional.empty() : Optional.of(blogs.get(0));
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM blog WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public static List<Blog> getSubList(List<Blog> data, int itemsPerPage, int page) {
        int startIndex = (page - 1) * itemsPerPage;
        int endIndex = Math.min(startIndex + itemsPerPage, data.size());
        return data.subList(startIndex, endIndex);
    }

    public List<Blog> getPage(Integer page) {
        int itemsPerPage = 10;
        List<Blog> blogs = this.findAll();
        return getSubList(blogs, itemsPerPage, page);
    }
}


