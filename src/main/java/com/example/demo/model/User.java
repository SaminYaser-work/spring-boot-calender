package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    private String username;
    private String password;
    private boolean active;
    private String roles;


    @OneToMany(targetEntity = Blog.class)
    @JoinColumn(name = "fk_blogId", referencedColumnName = "id")
//    @Column(name = "blogs_posts")
    private List<Blog> blogposts;

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
