package com.example.demo.util;

import com.example.demo.model.Blog;
import com.example.demo.model.Topic;
import com.example.demo.model.User;
import com.example.demo.repository.BlogRepository;
import com.example.demo.repository.TopicRepository;
import com.example.demo.repository.UserRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
public class BlogDataLoader implements CommandLineRunner {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        // Creating the topics
        Topic t1 = new Topic();
        t1.setTopicName("science");
        Topic t2 = new Topic();
        t2.setTopicName("news");
        Topic t3 = new Topic();
        t3.setTopicName("sports");
        topicRepository.save(t1);
        topicRepository.save(t2);
        topicRepository.save(t3);

        List<Topic> topicList = new ArrayList<>();
        topicList.add(t1);
        topicList.add(t2);
        topicList.add(t3);

        Faker faker = new Faker();

        List<Blog> blogPosts = new ArrayList<>();
        Random rand = new Random();

         for (int i = 0; i < 30; i++) {

             Blog blog = new Blog();
             blog.setTitle(faker.lorem().sentence());
             blog.setContent(faker.lorem().paragraph());
             blog.setDate(faker.date().between(new Date(System.currentTimeMillis() - 86400000L * 365), new Date()));

             if (rand.nextInt(5) > 1 ){
                 blog.setTopic(
                         topicList.get(rand.nextInt(topicList.size()))
                 );
             } else {
                 blog.setTopic(null);
             }

             User user = new User();
             user.setUsername(faker.name().username());
             user.setPassword(faker.internet().password());
             user.setActive(true);
             user.setRoles("USER_ROLE");
             user.addBlog(blog);

//             blogRepository.save(blog);
//
//             Blog savedBlog = blogRepository.findById(blog.getId()).get();
//
//             userRepository.save(user);
//
//             User savedUser = userRepository.findById(user.getId()).get();
//
//             savedUser.getBlogposts().add(savedBlog);

             userRepository.save(user);
         }


//        IntStream.rangeClosed(1, 100)
//                .mapToObj(i -> new Blog(
//                        faker.lorem().sentence(),
//                        faker.lorem().paragraph(),
//                        faker.name().fullName(),
//                        faker.date().between(new Date(System.currentTimeMillis() - 86400000L * 365), new Date())
//                ))
//                .forEach(blogRepository::save);
    }
}

