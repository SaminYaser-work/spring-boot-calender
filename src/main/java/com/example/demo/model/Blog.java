package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "blog")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Date date;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Topic.class)
//    @JoinColumn(referencedColumnName = "id", nullable = true)
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    @JsonBackReference
    @JsonIgnoreProperties({
            "blogs",
            "hibernateLazyInitializer",
            "handler"
    })
    private Topic topic;
}

