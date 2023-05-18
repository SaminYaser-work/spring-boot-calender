//package com.example.demo.model;
//
//import com.example.demo.repository.BlogRepository;
//import com.example.demo.repository.TopicRepository;
//import com.example.demo.repository.UserRepository;
//import com.github.javafaker.Faker;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Component
//public class TopicDataLoader implements CommandLineRunner {
//
//    @Autowired
//    private TopicRepository topicRepository;
//
//    @Override
//    @Transactional
//    public void run(String... args) throws Exception {
//
//        Topic t1 = new Topic();
//        t1.setTopicName("Java");
//
//        Topic t2 = new Topic();
//        t2.setTopicName("Spring");
//
//        Topic t3 = new Topic();
//        t3.setTopicName("Hibernate");
//
//
//        topicRepository.save(t1);
//        topicRepository.save(t2);
//        topicRepository.save(t3);
//    }
//}
//
