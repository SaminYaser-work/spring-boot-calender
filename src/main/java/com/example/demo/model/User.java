package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;

    private String username;
    private String password;
    private boolean active;
    private String roles;

    @OneToMany(targetEntity = Blog.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
//    @JoinColumn(referencedColumnName = "id", name = "fk_user_id")
//    @Getter(AccessLevel.NONE) // Uncomment to lazy load
//    @Setter(AccessLevel.NONE)
    @JsonIgnoreProperties({
            "user",
            "hibernateLazyInitializer",
            "handler"
    })
    private List<Blog> blogposts = new ArrayList<>();

    public void addBlog(Blog blog) {
        if (!this.blogposts.contains(blog)) {
            this.blogposts.add(blog);
            blog.setUser(this);
        }
    }
}
