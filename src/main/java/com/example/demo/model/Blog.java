package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;


    private Date date;

    // Null user
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class, cascade = CascadeType.ALL)
//    @JoinColumn(name = "author_id", nullable = false, referencedColumnName = "id")
//    private transient User user;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "author_id", nullable = false)
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    private User user;

//    public Blog(String title, String content, Date date) {
//        this.title = title;
//        this.content = content;
//        this.date = date;
//    }
}

