package com.example.demo.model;

import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
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


    @OneToMany(targetEntity = Blog.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name = "fk_user_id")
//    @Column(name = "blogs_posts")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private List<Blog> blogposts = new ArrayList<>();

    public User(String username, String password, boolean active, String roles, List<Blog> blogPosts){
        this.username = username;
        this.password = password;
        this.active = active;
        this.roles = roles;
        this.blogposts = blogPosts;
    }

    public void addBlogPost(Blog blogPost) {
        this.blogposts.add(blogPost);
    }

}
