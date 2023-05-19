package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "topic_name")
    private String topicName;
    
    @OneToMany(targetEntity = Blog.class, mappedBy = "topic", cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnoreProperties("topic")
    private List<Blog> blogs = new ArrayList();
}
