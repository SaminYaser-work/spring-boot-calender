package com.example.demo.controller;

import com.example.demo.dto.AddNewUserRequest;
import com.example.demo.model.Blog;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {

//        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
//        Session s = sessionFactory.openSession();
//        Transaction tx = s.beginTransaction();

        List<User> users = userService.getAllUsers();

//        tx.commit();
//        s.close();




        return users;
    }

    @PostMapping
    public void createUser(@NotNull @RequestBody AddNewUserRequest req) {
        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(req.getPassword());
        user.setRoles(req.getRoles());
        userService.createUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Integer id, @RequestBody User user) {
        userService.updateUser(id, user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

}
