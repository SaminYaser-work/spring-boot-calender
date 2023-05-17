package com.example.demo.controller;

import com.example.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogDto {
    private String title;
    private String content;
    private String author;
    private Date date;
    private User user;
}
