package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchBlogResponse {
    private int blogId;
    private String title;
    private String content;
    private Date date;

    private int topicId;
    private String topicName;

    private int userId;
    private String username;
    private boolean isActive;
}
