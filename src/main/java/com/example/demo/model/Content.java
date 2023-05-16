package com.example.demo.model;

import com.example.demo.model.Status.Status;
import com.example.demo.model.Type.Type;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public class Content {
    private final Integer id;

    @NotEmpty
    @NotBlank
    private final String title;

    private final String desc;

    private final Status status;

    private final Type ContentType;

    private final LocalDateTime dateCreated;

    private final LocalDateTime dateUpdated;

    private final String url;

    public Content(Integer id, String title, String desc, Status status, Type contentType, LocalDateTime dateCreated, LocalDateTime dateUpdated, String url) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.status = status;
        ContentType = contentType;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public Status getStatus() {
        return status;
    }

    public Type getContentType() {
        return ContentType;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public String getUrl() {
        return url;
    }
}
