package com.example.demo.repository;

import com.example.demo.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicRepository extends JpaRepository<Topic, Integer> {

    @Query("SELECT t.id FROM com.example.demo.model.Topic t WHERE t.topicName LIKE :topicName")
    Integer findByTopicName(String topicName);
}
