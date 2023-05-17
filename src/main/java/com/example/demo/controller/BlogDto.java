package com.example.demo.controller;

import com.example.demo.model.User;

import java.time.LocalDate;
import java.util.Date;

public class BlogDto {

    private String title;
    private String content;
    private String author;
    private Date date;

    private User user;

    public BlogDto() {
    }

    public BlogDto(String title, String content, Date date) {
        this.title = title;
        this.content = content;
        this.date = date;
//        this.user = user;
    }

    public BlogDto(String title, String content, Date date, User user) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }

}
