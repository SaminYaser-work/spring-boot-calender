package com.example.demo.controller;

import java.time.LocalDate;
import java.util.Date;

public class BlogDto {

    private String title;
    private String content;
    private String author;
    private Date date;

    public BlogDto() {
    }

    public BlogDto(String title, String content, String author, Date date) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.date = date;
    }

    // getters and setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
