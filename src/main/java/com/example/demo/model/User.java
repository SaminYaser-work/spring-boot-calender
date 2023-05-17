package com.example.demo.model;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;
    private boolean active;
    private String roles;

    @OneToMany(mappedBy = "user")
    @Column(name = "blogs_posts")
    private Set<Blog> blogsPosts = new HashSet<>();

//
//    public List<Blog> getBlogs() {
//        return blogs;
//    }

//    public void setBlogs(List<Blog> blogs) {
//        this.blogs = blogs;
//    }

    public User() {}

    public User(String username, String password, boolean active, String roles, Set<Blog> blogsPosts) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.roles = roles;
        this.blogsPosts = blogsPosts;
    }

    public Set<Blog> getBlogsPosts() {
        return blogsPosts;
    }

    public void setBlogsPosts(Set<Blog> blogsPosts) {
        this.blogsPosts = blogsPosts;
    }

    public void addBlogPost(Blog blog) {
        this.blogsPosts.add(blog);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", roles='" + roles + '\'' +
                ", blogsPosts=" + blogsPosts +
                '}';
    }
}
