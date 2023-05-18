package com.example.demo.util;


import lombok.Data;

import java.util.Date;

@Data
public class BlogWithNamesResponse {
    private String title;
    private String author;
    private String content;
    private Date date;
}
